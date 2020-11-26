import java.util.ArrayList;
import java.util.List;

public class Individual implements Comparable<Individual>{
	private List<Node> nodeList;
	private int numberOfCluster;
	private String name;
	private double turboMQ;

	public Individual(List<Node> nodeList, int numberOfCluster, String name) {
		this.setName(name);
		this.nodeList = nodeList;
		this.numberOfCluster = numberOfCluster;
	}
	
	@Override
	public int compareTo(Individual u) {
		if(this.getTurboMQ()>u.getTurboMQ())
			return 1;
		else if(this.getTurboMQ()==u.getTurboMQ())
			return 0;
		else
			return -1;
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

	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public int getNumberOfCluster() {
		return numberOfCluster;
	}

	public void setNumberOfCluster(int numberOfCluster) {
		this.numberOfCluster = numberOfCluster;
	}
}
