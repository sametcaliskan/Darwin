package pivot;

import java.util.ArrayList;

public class Pivot {

	public static void main(String[] args) {
        try
        {
        	if(args.length < 3) {
        		System.out.println("usage:");
        		System.out.println("java Pivot.pivot *.rsf *.rsf ... *.rsf");
        		System.out.println("The last argument specifies the output file.");
        		System.out.println("All the other arguments specify clusterings to be aggregated.");
        		System.out.println("At least 3 file names required as arguments.");
        		return;
        	}
        	
        	int n =  args.length - 1;
        	String outFilename = args[n];
        			
        	System.out.println("# of clusters to be aggregated: " + n);
        	DisjSets [] clusters = new DisjSets[n];
        	
        	RsfParser map = new RsfParser(args[0]);
        	clusters[0] = map.getDSet();
    		
    		for (int i = 1; i < n; i++) {
    			map = new RsfParser(args[i]);
    			clusters[i] = map.getDSet();
			}
    		
    		Clustering c = new Clustering(clusters);
    		DisjSets result = c.getClusters();
    		map.setDSet(result);
    		map.writeToFile(outFilename);
        }
        catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
        }
    }

	public static void printCluster(DisjSets ds) {
		for (int i = 0; i < ds.getSize(); i++)
			System.out.println((i+1) + "," + ds.find(i));
	}
}
