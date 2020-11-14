import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        // read files and create nodes
        Parser parser = new Parser("C:/Users/temas/Desktop/Git/sample-projects/bash");
        List<Node> nodes = parser.getNodes();
        int numberOfCluester = parser.getNumberOfCluster();

        // give nodes to GA to generate a population
        GeneticAlgorithmAbstract football = new Football(nodes, numberOfCluester);
        football.initializePopulation();
        List<Node> popList = football.getPopulation();
        int[] population = football.getPopulationArray();
        // System.out.println(Arrays.toString(population));

        // calculate statistics of clusters
        calculateInterAndIntraEdgesOfClusters(popList, numberOfCluester);
    }

    public static void calculateInterAndIntraEdgesOfClusters(List<Node> nodes, int numberOfCluester) {
        int[] inter = new int[numberOfCluester];
        int[] intra = new int[numberOfCluester];
        for (int i = 0; i < numberOfCluester; i++) {
            int numberOfIndividuals = 0;
            int clusterName = i + 1;
            int interSum = 0;
            int intraSum = 0;
            for (Node n : nodes) {
                if (n.getCluster() == (i + 1)) {
                    numberOfIndividuals++;
                    for (Node dep : n.getDependencies()) {
                        if (dep.getCluster() == n.getCluster()) {
                            // get just one relation as intra
                            if (dep.getId() > n.getId()) {
                                intraSum++;
                            }
                        } else {
                            interSum++;
                        }
                    }
                }
            }
            double basicMq = calculateBasicMq(numberOfIndividuals, intraSum, interSum);
            inter[i] = interSum;
            intra[i] = intraSum;
            System.out.println("cluster: " + clusterName + " [ number of individuals: " + numberOfIndividuals
                    + " inter edges:" + interSum + ", intra edges:" + intraSum + ", BasicMQ: " + basicMq + " ]");
        }

        for (int i = 0; i < numberOfCluester; i++) {
            int clusterName = i + 1;
            System.out.print(
                    "cluster " + clusterName + " [inter edges :" + inter[i] + ", intra edges :" + intra[i] + "]");
            System.out.println("");
        }
    }

    public static double calculateBasicMq(int numberOfIndividuals, int intraSum, int interSum) {
        return ((intraSum / numberOfIndividuals)
                - (2 * interSum / (numberOfIndividuals * numberOfIndividuals - numberOfIndividuals)));
    }
}
