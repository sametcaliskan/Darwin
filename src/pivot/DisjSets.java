package pivot;

public class DisjSets {

	private int s[];
	private int noOfElements;
	
	public DisjSets(int noOfElements) {
		this.noOfElements = noOfElements;
		s = new int[noOfElements];
		for (int i = 0; i < s.length; i++)
			s[i] = -1;
	}
	
	public void unionSets(int root1, int root2) {
		if(s[root2] < s[root1])
			s[root1] = root2;
		else {
			if(s[root1] == s[root2])
				s[root1]--;
			s[root2] = root1;
		}
	}
	
	public int find (int x) {
		if(s[x] < 0)
			return x;
		else
			return s[x] = find(s[x]);
	}
	
	public void merge(int x, int y) {
		int set1 = find(x);
		int set2 = find(y);
		if(set1 != set2)
			unionSets(set1, set2);
	}
	
	public void print() {
		for (int i = 0; i < s.length; i++) {
			System.out.print(s[i] + " ");
		}
		System.out.println();
	}
	
	public int getSetCount() {
		int count = 0;
		for (int i = 0; i < s.length; i++) 
			if(s[i] < 0)
				count++;
		return count;
	}
	
	public int getSize() {
		return noOfElements;
	}
}

