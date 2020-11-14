import java.util.List;

public class Football extends GeneticAlgorithmAbstract {

   protected Football(List<Node> population, int cluster) {
      super(population, cluster);
   }

   @Override
   void mutation() {
      System.out.println("Football applied mutation on population!");
   }

   @Override
   void initializePopulation() {
      List<Node> population = getPopulation();
      int popSize = population.size();
      int numberOfCluester = getNumberOfCluster();
      int[] populationArray = new int[popSize];
      int cluster;
      for (int i = 0; i < popSize; i++) {
         cluster = (int) (Math.random() * numberOfCluester) + 1;
         populationArray[i] = cluster;
         population.get(i).setCluster(cluster);
      }
      setPopulationArray(populationArray);
      System.out.println("Football applied initialitation on population!");
   }

   @Override
   void crossover() {
      System.out.println("Football applied crossover on population!");
   }

   @Override
   void fitnessFunction() {
      System.out.println("Football applied fitness function on population!");

   }

   @Override
   void selection() {
      System.out.println("Football applied selection on population!");

   }
}