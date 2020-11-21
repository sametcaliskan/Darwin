import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pivot.Pivot;
import turbo.TurboMQ;

public class App {
    public static void main(String[] args) throws IOException {
        // read files and create nodes
        Parser parser = new Parser("libs/sample-projects/bash");
        List<Node> nodes = parser.getNodes();
        int numberOfCluester = parser.getNumberOfCluster();

        // give nodes to GA to generate a population
        List<GeneticAlgorithmAbstract> allPopulation = new ArrayList<GeneticAlgorithmAbstract>();
        String fileName = "";
        String[] argsForTurbo = new String[2];
        String[] crossOver = new String[11];
        for(int i=0; i<10;i++) {
        	GeneticAlgorithmAbstract eachPopulation = new GeneticAlgoritmFullyRandom(nodes, numberOfCluester);
        	eachPopulation.initializePopulation();
        	allPopulation.add(eachPopulation);
        	fileName = "population-"+i+"-dep.rsf";
        	parser.generateDependencyRsf(eachPopulation.getPopulation(), fileName);
            parser.generateClusterRsf(eachPopulation.getPopulation(),fileName);
            
            argsForTurbo[0]="libs/outputs/dependencies/"+fileName;
            argsForTurbo[1]="libs/outputs/clusters/"+fileName;
            crossOver[i] = argsForTurbo[1];
            eachPopulation.setTurboMQ(TurboMQ.main(argsForTurbo));
        }
        
        GeneticAlgorithmAbstract worst;
        double compare = 1;
        for(GeneticAlgorithmAbstract ga:allPopulation) {
        	if(ga.getTurboMQ()<compare) {
        		worst = ga;
        		compare = ga.getTurboMQ();
        	}
        }
        
        //crossOver[2] = "/libs/outputs/test.rsf";
        
        Pivot.main(Arrays.copyOfRange(crossOver, 0, 3));
        
       
        
        /*
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
        */
    }
}
