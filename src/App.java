import java.io.IOException;
import pivot.Pivot;

public class App {
    public static void main(String[] args) throws IOException {
        // read files and create nodes
    	
/*        Parser parser = new Parser("libs/sample-projects/bash");
        List<Node> nodes = parser.getNodes();
        int numberOfCluester = parser.getNumberOfCluster();

        int individualNumber = 10;*/
        GeneticAlgorithmAbstract randomGa = new GeneticAlgoritmFullyRandom();
        randomGa.play();
       /* randomGa.initializePopulation(individualNumber, numberOfCluester, nodes);

        // generate rsf files for turboMQ and pivot crossover
        generateRsfFiles();*/
    }

    public static void generateRsfFiles() {
        /**
         * String fileName = ""; String[] argsForTurbo = new String[2]; String[]
         * crossOver = new String[11]; int name = 0;
         * 
         * fileName = "individual-" + i + "-dep.rsf"; //
         * parser.generateDependencyRsf(individual.getNodeList(), fileName); //
         * parser.generateClusterRsf(individual.getNodeList(), fileName);
         * 
         * argsForTurbo[0] = "libs/outputs/dependencies/" + fileName; argsForTurbo[1] =
         * 
         * "libs/outputs/clusters/" + fileName; crossOver[i] = argsForTurbo[1]; // //
         * eachPopulation.setTurboMQ(TurboMQ.main(argsForTurbo));
         * 
         * GeneticAlgorithmAbstract worst; double compare = 1; for
         * (GeneticAlgorithmAbstract ga : allPopulation) { if (ga.getTurboMQ() <
         * compare) { worst = ga; compare = ga.getTurboMQ(); } }
         */
        String[] crossOver = new String[3];
        crossOver[0] = "/libs/outputs/clusters/population-5-dep.rsf";
        crossOver[1] = "/libs/outputs/clusters/population-6-dep.rsf";
        crossOver[2] = "/libs/outputs/test.rsf";

        Pivot.main(crossOver);

    }
}
