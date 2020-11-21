package pivot;

import java.util.ArrayList;

public class Clustering {
	
	int dsm[][];
	DisjSets [] clusters;
		
	public Clustering(DisjSets [] clusters) {
		this.clusters = clusters;
		int noOfElements = clusters[0].getSize();
		
		dsm = new int[noOfElements][noOfElements];
		
		for (int i = 0; i < noOfElements; i++)
			for (int j = 0; j < noOfElements; j++)
				dsm[i][j] = dsm[j][i] = similarity(i,j);
	}
	
	private int similarity(int i, int j) {
		int count = 0;		
		for (int k = 0; k < clusters.length; k++) {
			DisjSets ds = clusters[k];
			if(ds.find(i) == ds.find(j))
				count++;
		}
		return count;
	}
	
	public DisjSets getClusters() {
		
		int threshold = clusters.length/2;
		DisjSets ds = new DisjSets(dsm.length);
		ArrayList<Integer> list = getRandomPermutation(dsm.length);
		
		while(list.size() > 0) {
			int v = list.get(0);
			list.remove(0);
			for (int i = 0; i < list.size(); i++) {
				if(dsm[v][list.get(i)] >= threshold) {
					ds.merge(v, list.get(i));
					list.remove(i);
					i--;
				}
			}
		}
		return ds;
	}

	private static ArrayList<Integer> getRandomPermutation(int length) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < length; i++)
			list.add(i);
		java.util.Collections.shuffle(list);
		return list;
	}
}
