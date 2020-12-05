import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import pivot.Pivot;

public class App {
    public static void main(String[] args) throws IOException {
        // read files and create nodes
    	
    	File fout = new File("libs/outputs/result_MQs.txt");
        // fout.createNewFile();
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        long start,end,timeElapsed = 0;
        GeneticAlgorithmAbstract randomGa = new GeneticAlgoritmMutationVariance();
        for(int i = 0; i<1;i++) {
        	start = System.currentTimeMillis();
        	// ...
        	
        	System.out.println("Experiment "+(i+1));
        	randomGa.play();
        	end = System.currentTimeMillis();
            for (Double eachMQ : randomGa.getMaxTurboMQList() ) {
               bw.write(eachMQ+" ");       
            }
            System.out.println(randomGa.getMaxTurboMQList().get(randomGa.getMaxTurboMQList().size()-2)+
            		" , "+randomGa.getMaxTurboMQList().get(randomGa.getMaxTurboMQList().size()-1));
            bw.write(""+(end-start));
            bw.newLine();
            System.gc();
        } 
        bw.close();
        /*
    	for(int i=0; i<10;i++) {
	    	GeneticAlgorithmAbstract randomGa = new GeneticAlgoritmMutationVariance();
	    	randomGa.play();
	    	for (Double eachMQ : randomGa.getMaxTurboMQList() ) {
	            System.out.print(eachMQ+" ");       
	        }
	    	System.out.println("------------------");
	    	System.gc();
    	}*/
    	/*
    	System.out.println("-----------------------------------------------");
    	randomGa = new GeneticAlgoritmFullyRandom();
    	randomGa.play();
    	for (Double eachMQ : randomGa.getMaxTurboMQList() ) {
            System.out.println(eachMQ+" ");       
        }*/
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
