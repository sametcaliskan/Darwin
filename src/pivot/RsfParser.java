package pivot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class RsfParser {

	private ArrayList<ArrayList<String>> clusteredItems;
	private ArrayList<String> items;
	private HashMap<String, Integer> name2ID;
	private DisjSets dset;
	private int totalItemCount;
	
	public RsfParser(String filename) {
		
		clusteredItems = new ArrayList<ArrayList<String>>();
		name2ID = new HashMap<String,Integer>();
		items = new ArrayList<String>();
		totalItemCount = 0;
				
		parseInputFile(filename);
		initDSet();
	}

	private void parseInputFile(String filename) {
		try {
			 File f = new File(".//" + filename);
			 BufferedReader reader = new BufferedReader(new FileReader(f));
			 String readLine = "";
			 String clusterName = "";
			 String currentCluster = "";
			 String itemName = "";
			 int clusterCount = 0;
			 
			 while ((readLine = reader.readLine()) != null) {
				 StringTokenizer tokenizer = new StringTokenizer(readLine); 
				 
				 tokenizer.nextToken(); // contains
				 
				 clusterName = tokenizer.nextToken();
				 if(!currentCluster.equals(clusterName)) {
					 currentCluster = clusterName;
					 clusterCount++;
					 clusteredItems.add(new ArrayList<String>());	 
				 } 
				 
				 itemName = tokenizer.nextToken();
				 clusteredItems.get(clusterCount-1).add(itemName);
				 name2ID.put(itemName, totalItemCount);
				 items.add(itemName);
				 totalItemCount++;
			 }
			 reader.close();
		} catch (IOException e) {
			System.out.println("Error while reading an input file!");
			e.printStackTrace();
	    }
	}

	private void initDSet() {
		dset = new DisjSets(totalItemCount);
		for(int i = 0; i < clusteredItems.size(); i++) {
			ArrayList<String> cluster = clusteredItems.get(i);
			int clusterRepresentative = name2ID.get(cluster.get(0));
			for(int j = 1; j < cluster.size(); j++)
				dset.unionSets(clusterRepresentative, name2ID.get(cluster.get(j)));
		}
	}
	
	public DisjSets getDSet() {
		return this.dset;
	}
	
	public void setDSet(DisjSets dset) {
		this.dset = dset;
		clusteredItems = new ArrayList<ArrayList<String>>();
		HashMap<Integer, Integer> clusters = new HashMap<Integer, Integer>();
		
		int clusterCount = 0;
		for (int i = 0; i < dset.getSize(); i++) {
			int clusterID = dset.find(i);
			int clusterIndex = 0;
			if(clusters.containsKey(clusterID))
				clusterIndex = clusters.get(clusterID);
			else {
				clusteredItems.add(new ArrayList<String>());
				clusterIndex = clusterCount;
				clusters.put(clusterID, clusterCount);
				clusterCount++;
			}
			clusteredItems.get(clusterIndex).add(items.get(i));
		}	
	}
	
	public void writeToFile(String filename) {
		try {
			File file = new File(".//" + filename);
			file.createNewFile();
			FileWriter writer = new FileWriter(file,false);
			for(int i = 0; i < clusteredItems.size(); i++) {
				ArrayList<String> cluster = clusteredItems.get(i);
				for(int j = 0; j < cluster.size(); j++)
					writer.write("contain " + i + " " + cluster.get(j) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Error while trying to create the output file!");
			e.printStackTrace();
		}
	}
}
