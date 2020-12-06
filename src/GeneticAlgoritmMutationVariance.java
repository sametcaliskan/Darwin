import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import turbo.TurboMQ;

public class GeneticAlgoritmMutationVariance extends GeneticAlgorithmAbstract {
	List<Individual> selectedIndividualList;

	protected GeneticAlgoritmMutationVariance() {
		selectedIndividualList = new ArrayList<>();
		Population population = new Population();
		super.setPopulation(population);
	}

	@Override
	void initializePopulation(int individualNumber, int numberOfCluester, List<Node> nodeList) {
		// initialize individuals
		// int name = 0;
		
		for (int i = 0; i < individualNumber; i++) {
			List<Node> cloneList=getCloneNodeList(nodeList);
			Individual individual = new Individual(cloneList, numberOfCluester, i + "");
			super.getPopulation().addIndividual(individual);
		}
		// give cluster name randomly
		int cluster;
		for (Individual individual : super.getPopulation().getIndividualList()) {
			for (Node node : individual.getNodeList()) {
				cluster = new Random().nextInt(numberOfCluester)+1;
				node.setCluster(cluster);
			}
		}
		System.out.println("Population is initializated!");
	}

	@Override
	void selection() {
		selectedIndividualList.clear();
		List<Individual> individualList = super.getPopulation().getIndividualList();
		int selectedParentsNumber = (int) (individualList.size() * 0.15);
		Collections.sort(individualList);
		Collections.reverse(individualList);
		// for(Individual i:individualList) {
		// System.out.println(i.getTurboMQ());
		// }
		for (int i = 0; i < selectedParentsNumber; i++) {
			selectedIndividualList.add(individualList.get(i));
		}
		super.getMaxTurboMQList().add(individualList.get(0).getTurboMQ());
		// select list of individuals
		//System.out.println("MutationGa applied selection on population!");
	}

	@Override
	void crossover() {
		int parentListSize = selectedIndividualList.size();
		List<Individual> childList = new ArrayList<>();
		List<Node> nodeList = super.getPopulation().getIndividualList().get(0).getNodeList();
		int cluster = getPopulation().getIndividualList().get(0).getNumberOfCluster();
		for (int i = 0; i < parentListSize; i++) {
			Individual child = null;
			for (int j = i + 1; j < parentListSize; j++) {
				List<Node> cloneList=getCloneNodeList(nodeList);
				child = new Individual(cloneList, cluster, i + "" + j);
				// compare cluster of every node
				for (int k = 0; k < nodeList.size(); k++) {
					int compareClusterA = selectedIndividualList.get(i).getNodeList().get(k).getCluster();
					int compareClusterB = selectedIndividualList.get(j).getNodeList().get(k).getCluster();
					if (compareClusterA == compareClusterB) {
						child.getNodeList().get(k).setCluster(compareClusterA);
					} else {
						int rnd = new Random().nextInt(2);
						if (rnd == 0) {
							child.getNodeList().get(k).setCluster(compareClusterA);
						} else {
							child.getNodeList().get(k).setCluster(compareClusterB);
						}
					}
				}
				childList.add(child);
			}
		}
		selectedIndividualList = childList;
	}

	@Override
	void mutation() {
		int candidateMutationSize = (int) (selectedIndividualList.get(0).getNodeList().size() * 0.01);
		for (Individual ind : selectedIndividualList) {
			List<Node> cloneList = getCloneNodeListWithCluster(ind.getNodeList());
			Collections.sort(cloneList);
			Collections.reverse(cloneList);
			for (int i = 0; i < candidateMutationSize; i++) {
				ind.getNodeByName(cloneList.get(i).getName()).setCluster(cloneList.get(i).getCandidateCluster());
			}
			cloneList = null;
		}
		super.getPopulation().setIndividualList(getCloneIndividualList(selectedIndividualList));
	}
	
	public List<Node> getInterIntraRatio(int candidateSize,Individual ind) {
		List<Node> topNodeList = new ArrayList<>();
		return topNodeList;
	}

	@Override
	void fitnessFunction() {
		String[] argsForTurbo = new String[2];
		List<Individual> individualList = super.getPopulation().getIndividualList();
		int populationSize = individualList.size();
		//System.out.println("Individual size of population:" + populationSize);
		Parser parser = new Parser();
		try {
			parser.generateDependencyRsf(individualList.get(0).getNodeList(), "all-dep.rsf");
			for (int i = 0; i < populationSize; i++) {
				Individual eachIndividual = individualList.get(i);
				// System.out.println("each individual node list size:"
				// +eachIndividual.getNodeList().size());
				parser.generateClusterRsf(eachIndividual.getNodeList(), "cluster.rsf");
				argsForTurbo[0] = "libs/outputs/dependencies/all-dep.rsf";
				argsForTurbo[1] = "libs/outputs/clusters/cluster.rsf";

				eachIndividual.setTurboMQ(TurboMQ.main(argsForTurbo));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println("RandomGa applied fitness function on population!");
	}

	private List<Individual> getCloneIndividualList(List<Individual> list){
		List<Individual> cloneIndividualList= new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			List<Node> cloneList=new ArrayList<>();
			for(Node node:list.get(i).getNodeList()) {
				Node cloneNode=new Node(node.getName(),node.getId());
				cloneNode.setDependencies(node.getDependencies());
				cloneNode.setCluster(node.getCluster());
				cloneList.add(cloneNode);
			}
			Individual individual = new Individual(cloneList, list.get(i).getNumberOfCluster(), i + "");
			cloneIndividualList.add(individual);
	}
		return cloneIndividualList;
	}
}