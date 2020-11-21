import java.util.ArrayList;
import java.util.List;

public class Individual {
	private List<Node> nodeList;
	private int numberOfCluster;
	private String name;
	private double turboMQ;

	public Individual(List<Node> nodeList, int numberOfCluster, String name) {
		this.setName(name);
		this.nodeList = new ArrayList<Node>(nodeList);
		this.numberOfCluster = numberOfCluster;
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
