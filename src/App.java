import java.io.IOException;
import java.util.Arrays;
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
        for (Node node : popList) {
            node.calculateDependency();
        }
        BasicMQ basicMQ = new BasicMQ();
        basicMQ.calculateBasicMQ(popList, numberOfCluester);
        for (int i = 0; i < 100; i++) {
            popList = football.getPopulation();
            for (Node node : popList) {
                node.calculateDependency();
            }
            int[] population = football.getPopulationArray();
            // System.out.println(Arrays.toString(population));

            football.selection();
            football.crossover();
            football.mutation();
        }
        System.out.println("------------------------------");
        // calculate basicMQ of clusters
        basicMQ = new BasicMQ();
        basicMQ.calculateBasicMQ(popList, numberOfCluester);
    }
}
