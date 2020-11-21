import java.util.List;
import java.util.Random;

public class GeneticAlgoritmFullyRandom extends GeneticAlgorithmAbstract {
   private Node firstNode;
   private Node secondNode;

   protected GeneticAlgoritmFullyRandom(List<Node> population, int cluster) {
      super(population, cluster);
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
   void mutation() {
      int range = super.getPopulation().size() - 1;
      int selectedNodeIndex = getRandomNumberInRange(0, range);
      Node firstNode = super.getPopulation().get(selectedNodeIndex);
      // System.out.println("Football applied mutation on population!");
      if (firstNode.getDependencies().size() != 0) {
         // System.out.println("crssoever");
         int[] dependencyClusterNames = new int[firstNode.getDependencies().size()];
         int[] dependencyClusterCount = new int[firstNode.getDependencies().size()];
         for (int i = 0; i < firstNode.getDependencies().size(); i++) {
            dependencyClusterCount[i] = 0;
            dependencyClusterNames[i] = 0;
         }
         for (Node firstDependency : firstNode.getDependencies()) {
            for (int i = 0; i < dependencyClusterNames.length; i++) {
               if (dependencyClusterNames[i] != 0 && firstDependency.getCluster() == dependencyClusterNames[i]) {
                  dependencyClusterCount[i] = dependencyClusterCount[i] + 1;
               } else {
                  dependencyClusterNames[i] = firstDependency.getCluster();
                  dependencyClusterCount[i] = dependencyClusterCount[i] + 1;
               }
            }
         }
         int largestDependencyIndex = getLargest(dependencyClusterCount);
         int largestDependecyName = dependencyClusterNames[largestDependencyIndex];
         int oldClusterName = firstNode.getCluster();
         // System.out.println("oldCluster:" + oldClusterName + " new cluster:" +
         // largestDependecyName);
         firstNode.setCluster(largestDependecyName);
      }
      generatePopulationArray();
   }

   @Override
   void crossover() {
      if (firstNode.getDependencies().size() != 0) {
         // System.out.println("crssoever");
         int[] dependencyClusterNames = new int[firstNode.getDependencies().size()];
         int[] dependencyClusterCount = new int[firstNode.getDependencies().size()];
         for (int i = 0; i < firstNode.getDependencies().size(); i++) {
            dependencyClusterCount[i] = 0;
            dependencyClusterNames[i] = 0;
         }
         for (Node firstDependency : firstNode.getDependencies()) {
            for (int i = 0; i < dependencyClusterNames.length; i++) {
               if (dependencyClusterNames[i] != 0 && firstDependency.getCluster() == dependencyClusterNames[i]) {
                  dependencyClusterCount[i] = dependencyClusterCount[i] + 1;
               } else {
                  dependencyClusterNames[i] = firstDependency.getCluster();
                  dependencyClusterCount[i] = dependencyClusterCount[i] + 1;
               }
            }
         }
         int largestDependencyIndex = getLargest(dependencyClusterCount);
         int largestDependecyName = dependencyClusterNames[largestDependencyIndex];
         int oldClusterName = firstNode.getCluster();
         // System.out.println("oldCluster:" + oldClusterName + " new cluster:" +
         // largestDependecyName);
         firstNode.setCluster(largestDependecyName);
         secondNode.setCluster(oldClusterName);
      }
      // System.out.println("Football applied crossover on population!");
      generatePopulationArray();
   }

   private void generatePopulationArray() {
      int popSize = super.getPopulation().size();
      int[] populationArray = new int[popSize];
      for (int i = 0; i < popSize; i++) {
         int cluster = super.getPopulation().get(i).getCluster();
         populationArray[i] = cluster;
      }
      setPopulationArray(populationArray);
   }

   private int getLargest(int[] a) {
      int temp = 0;
      for (int i = 0; i < a.length; i++) {
         for (int j = i + 1; j < a.length; j++) {
            if (a[i] > a[j]) {
               temp = i;
            }
         }
      }
      return temp;
   }

   @Override
   void fitnessFunction() {
      System.out.println("Football applied fitness function on population!");

   }

   @Override
   void selection() {
      int range = super.getPopulation().size() - 1;
      int firstSelectedNode = getRandomNumberInRange(0, range);
      int secondSelectedNode = getRandomNumberInRange(0, range);
      // System.out.println(firstSelectedNode + " " + secondSelectedNode);
      while (firstSelectedNode == secondSelectedNode) {
         secondSelectedNode = getRandomNumberInRange(0, range);
         // System.out.println("selection loop" + firstSelectedNode + " " +
         // secondSelectedNode);
      }
      firstNode = this.getPopulation().get(firstSelectedNode);
      secondNode = this.getPopulation().get(secondSelectedNode);
      // System.out.println("Football applied selection on population!");

   }

   private int getRandomNumberInRange(int min, int max) {

      if (min >= max) {
         throw new IllegalArgumentException("max must be greater than min");
      }

      Random r = new Random();
      return r.nextInt((max - min) + 1) + min;
   }
}