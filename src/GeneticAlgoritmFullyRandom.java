import java.util.List;

public class GeneticAlgoritmFullyRandom extends GeneticAlgorithmAbstract {
   protected GeneticAlgoritmFullyRandom() {
      Population population = new Population();
      super.setPopulation(population);
   }

   @Override
   void initializePopulation(int individualNumber, int numberOfCluester, List<Node> nodeList) {
      // initialize individuals
      int name = 0;
      for (int i = 0; i < 10; i++) {
         Individual individual = new Individual(nodeList, numberOfCluester, name + "");
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
      // select list of individuals
      System.out.println("RandomGa applied selection on population!");
   }

   @Override
   void crossover() {
      System.out.println("RandomGa applied crossover on population!");
   }

   @Override
   void mutation() {
      System.out.println("RandomGa applied mutation on population!");
   }

   @Override
   void fitnessFunction() {
      System.out.println("RandomGa applied fitness function on population!");
   }

}