import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import turbo.TurboMQ;

public class GeneticAlgoritmFullyRandom extends GeneticAlgorithmAbstract {
   List<Individual> selectedIndividualList;
   protected GeneticAlgoritmFullyRandom() {
	  selectedIndividualList = new ArrayList<>();
      Population population = new Population();
      super.setPopulation(population);
   }

   @Override
   void initializePopulation(int individualNumber, int numberOfCluester, List<Node> nodeList) {
      // initialize individuals
      //int name = 0;
      for (int i = 0; i < individualNumber; i++) {
         Individual individual = new Individual(nodeList, numberOfCluester, i + "");
         super.getPopulation().addIndividual(individual);
      }
      // give cluster name randomly
      int cluster;
      for (Individual individual : super.getPopulation().getIndividualList()) {
         for (Node node : individual.getNodeList()) {
            cluster = (int) (Math.random() * individual.getNumberOfCluster()) + 1;
            node.setCluster(cluster);
         }
      }
      System.out.println("Population is initializated!");
   }

   @Override
   void selection() {
	  List<Individual> individualList = super.getPopulation().getIndividualList();
	  int selectedParentsNumber = (int) (individualList.size()*0.15);
	  Collections.sort(individualList);
	  Collections.reverse(individualList);
//	  for(Individual i:individualList) {
//		  System.out.println(i.getTurboMQ());
//	  }
	  for(int i =0; i<selectedParentsNumber;i++) {
		  selectedIndividualList.add(individualList.get(i));
	  }
	  super.getMaxTurboMQList().add(individualList.get(0).getTurboMQ());
      // select list of individuals
      System.out.println("RandomGa applied selection on population!");
   }

   @Override
   void crossover() {
	  int parentListSize = selectedIndividualList.size();
	  List<Individual> childList = new ArrayList<>();
	  List<Node> nodeList=super.getPopulation().individualList.get(0).getNodeList();
	  int cluster=getPopulation().individualList.get(0).getNumberOfCluster();
	  for(int i=0; i<parentListSize;i++) {
		  Individual child = null;
		  for(int j=i+1; j<parentListSize;j++) {
			  child = new Individual(nodeList,cluster,i+""+j);
			  // compare cluster of every node
			  for(int k=0; k<nodeList.size();k++) {
				  int compareClusterA=selectedIndividualList.get(i).getNodeList().get(k).getCluster();
				  int compareClusterB=selectedIndividualList.get(j).getNodeList().get(k).getCluster();
				  if(compareClusterA==compareClusterB) {
					  child.getNodeList().get(k).
					  setCluster(compareClusterA);
				  }else {
					  int rnd = new Random().nextInt(2);
					  if(rnd ==0) {
						  child.getNodeList().get(k).
						  setCluster(compareClusterA);
					  }else {
						  child.getNodeList().get(k).
						  setCluster(compareClusterB);
					  }
				  }
			  }
			  childList.add(child);
		  }
	  }
	  selectedIndividualList = childList;
      System.out.println("RandomGa applied crossover on population!");
   }

   @Override
   void mutation() {
	  int candidateMutationSize = (int) (selectedIndividualList.get(0).getNodeList().size()*0.01);
	  for(Individual ind:selectedIndividualList) {
		  for(int i=0;i<candidateMutationSize;i++) {
			  int rndIndex = new Random().nextInt(selectedIndividualList.get(0).getNodeList().size());
			  int rndCluster = new Random().nextInt(selectedIndividualList.get(0).getNumberOfCluster()+1);
			  ind.getNodeList().get(rndIndex).setCluster(rndCluster+1);
		  }
	  }
	  super.getPopulation().setIndividualList(selectedIndividualList);
      System.out.println("RandomGa applied mutation on population! ");
   }

   @Override
   void fitnessFunction() {
	  String[] argsForTurbo = new String[2]; 
	  List<Individual> individualList = super.getPopulation().getIndividualList();
	  int populationSize = individualList.size();
	  System.out.println("Individual size of population:"+populationSize);
	  Parser parser = new Parser();
	  try {
		parser.generateDependencyRsf(individualList.get(0).getNodeList(), "all-dep.rsf");
		for(int i=0; i<populationSize; i++) {
			Individual eachIndividual = individualList.get(i);
			//System.out.println("each individual node list size:" +eachIndividual.getNodeList().size());
			parser.generateClusterRsf(eachIndividual.getNodeList(),"cluster.rsf");
			argsForTurbo[0] = "libs/outputs/dependencies/all-dep.rsf"; 
			argsForTurbo[1] = "libs/outputs/clusters/cluster.rsf"; 
			
			eachIndividual.setTurboMQ(TurboMQ.main(argsForTurbo));
		}
	  } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	 
      System.out.println("RandomGa applied fitness function on population!");
   }

}