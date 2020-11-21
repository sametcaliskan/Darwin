import java.util.ArrayList;
import java.util.List;

public class Population {
	List<Individual> individualList;
	private int numberOfCluster;
	public Population(int numberOfCluster) {
		this.numberOfCluster = numberOfCluster;
		individualList = new ArrayList<>();
	}
	
	public List<Individual> initializePopulation(List<Node> nodeList,int populationSize){
		for(int i=0; i<populationSize; i++) {
			individualList.add(new Individual(nodeList, numberOfCluster, i+""));
		}
		
		return individualList;
	}
}
