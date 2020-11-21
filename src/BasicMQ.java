import java.util.List;

public class BasicMQ {

    public BasicMQ() {
        // empty contructor
    }

    private double calculate(int numberOfIndividuals, int intraSum, int interSum) {
        return ((intraSum / numberOfIndividuals)
                - (2 * interSum / (numberOfIndividuals * numberOfIndividuals - numberOfIndividuals)));
    }

    public void calculateBasicMQ(List<Node> nodes, int numberOfCluester) {
        for (int i = 0; i < numberOfCluester; i++) {
            int numberOfIndividuals = 0;
            int clusterName = i + 1;
            int interSum = 0;
            int intraSum = 0;
            for (Node n : nodes) {
                if (n.getCluster() == (i + 1)) {
                    numberOfIndividuals++;
                    interSum += n.getInterDependencyCount();
                    intraSum += n.getIntraDependencyCount();
                }
            }
            double basicMq = calculate(numberOfCluester, intraSum, interSum);
            System.out.println("cluster: " + clusterName + " [ number of individuals: " + numberOfIndividuals
                    + " inter edges:" + interSum + ", intra edges:" + intraSum + ", BasicMQ: " + basicMq + " ]");
        }
    }
}
