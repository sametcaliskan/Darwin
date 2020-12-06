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
        for(int i = 0; i<30;i++) {
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
}
