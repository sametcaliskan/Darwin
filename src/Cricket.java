import java.util.List;

public class Cricket extends GeneticAlgorithmAbstract {
   protected Cricket(List<Node> population, int cluster) {
      super(population, cluster);
   }

   @Override
   void mutation() {
      System.out.println("Cricket applied mutation on population!");
   }

   @Override
   void initializePopulation() {
      System.out.println("Cricket applied initalitation on population!");
   }

   @Override
   void crossover() {
      System.out.println("Cricket applied crossover on population!");
   }

   @Override
   void fitnessFunction() {
      System.out.println("Cricket applied fitness function on population!");

   }

   @Override
   void selection() {
      System.out.println("Cricket applied selection on population!");
   }

}