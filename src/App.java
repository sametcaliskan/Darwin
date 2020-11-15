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

        // calculate basicMQ of clusters
        BasicMQ basicMQ = new BasicMQ();
        basicMQ.calculateBasicMQ(popList, numberOfCluester);

    }
}
