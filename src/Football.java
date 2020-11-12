public class Football extends GeneticAlgorithmAbstract {

   protected Football(Node[] nodes) {
      super(nodes);
   }

   @Override
   void mutation() {
      System.out.println("Football applied mutation on population!");
   }

   @Override
   void initializePopulation() {
      System.out.println("Football applied initalitation on population!");
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