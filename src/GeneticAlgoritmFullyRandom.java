import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import turbo.TurboMQ;

public class GeneticAlgoritmFullyRandom extends GeneticAlgorithmAbstract {
   List<Individual> operatedIndividualList;
   protected GeneticAlgoritmFullyRandom() {
	  operatedIndividualList = new ArrayList<>();
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
	  int selectedParents = (int) (individualList.size()*0.15);
	  Collections.sort(individualList);
	  Collections.reverse(individualList);
//	  for(Individual i:individualList) {
//		  System.out.println(i.getTurboMQ());
//	  }
	  for(int i =0; i<selectedParents;i++) {
		  operatedIndividualList.add(individualList.get(i));
	  }
	  super.getMaxTurboMQList().add(individualList.get(0).getTurboMQ());
      // select list of individuals
      System.out.println("RandomGa applied selection on population!");
   }

   @Override
   void crossover() {
	  int parentListSize = operatedIndividualList.size();
	  List<Individual> childList = new ArrayList<>();
	  for(int i=0; i<parentListSize;i++) {
		  Individual child = null;
		  for(int j=i+1; j<parentListSize;j++) {
			  child = new Individual(getPopulation().individualList.get(0).getNodeList(),
					  getPopulation().individualList.get(0).getNumberOfCluster(),
					  i+""+j);
			  for(int k=0; k<operatedIndividualList.get(i).getNodeList().size();k++) {
				  if(operatedIndividualList.get(i).getNodeList().get(k).getCluster()
						  ==operatedIndividualList.get(j).getNodeList().get(k).getCluster()) {
					  child.getNodeList().get(k).
					  setCluster(operatedIndividualList.get(i).getNodeList().get(k).getCluster());
				  }else {
					  int rnd = new Random().nextInt(2);
					  if(rnd ==0) {
						  child.getNodeList().get(k).
						  setCluster(operatedIndividualList.get(i).getNodeList().get(k).getCluster());
					  }else {
						  child.getNodeList().get(k).
						  setCluster(operatedIndividualList.get(j).getNodeList().get(k).getCluster());
					  }
				  }
			  }
			  childList.add(child);
		  }
	  }
	  operatedIndividualList = childList;
      System.out.println("RandomGa applied crossover on population!");
   }

   @Override
   void mutation() {
	  int candidateMutationSize = (int) (operatedIndividualList.get(0).getNodeList().size()*0.01);
	  for(Individual ind:operatedIndividualList) {
		  for(int i=0;i<candidateMutationSize;i++) {
			  int rndIndex = new Random().nextInt(operatedIndividualList.get(0).getNodeList().size());
			  int rndCluster = new Random().nextInt(operatedIndividualList.get(0).getNumberOfCluster()+1);
			  ind.getNodeList().get(rndIndex).setCluster(rndCluster+1);
		  }
	  }
	  super.getPopulation().setIndividualList(operatedIndividualList);
      System.out.println("RandomGa applied mutation on population! "+operatedIndividualList.size());
   }

   @Override
   void fitnessFunction() {
	  String[] argsForTurbo = new String[2]; 
	  List<Individual> individualList = super.getPopulation().getIndividualList();
	  int populationSize = individualList.size();
	  System.out.println(populationSize);
	  Parser parser = new Parser();
	  try {
		parser.generateDependencyRsf(individualList.get(0).getNodeList(), "all-dep.rsf");
		for(int i=0; i<populationSize; i++) {
			Individual eachIndividual = individualList.get(i);
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