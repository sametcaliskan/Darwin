import java.util.ArrayList;
import java.util.List;

public class Individual {
	private List<Node> nodeList;
	private int numberOfCluster;
	private String name;
	private double turboMQ;
	
	public Individual(List<Node> nodeList,int numberOfCluster,String name) {
		this.setName(name);
		this.nodeList = new ArrayList<Node>(nodeList);
		this.numberOfCluster = numberOfCluster;
		initializePopulation();
	}
	
	private void initializePopulation() {
	      
	      int popSize = nodeList.size();
	 
	      int cluster;
	      for (int i = 0; i < popSize; i++) {
	         cluster = (int) (Math.random() * numberOfCluster) + 1;
	         nodeList.get(i).setCluster(cluster);
	      }
	      
	      System.out.println("Football applied initialitation on population!");
	  }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTurboMQ() {
		return turboMQ;
	}

	public void setTurboMQ(double turboMQ) {
		this.turboMQ = turboMQ;
	}
}
