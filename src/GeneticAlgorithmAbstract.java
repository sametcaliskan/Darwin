import java.util.List;

public abstract class GeneticAlgorithmAbstract {
   private List<Node> population;
   private int numberOfCluster;
   private int[] populationArray;
   //refactor later
   private double turboMQ;

   protected GeneticAlgorithmAbstract(List<Node> population, int numberOfCluster) {
      this.population = population;
      this.numberOfCluster = numberOfCluster;
   }

   public List<Node> getPopulation() {
      return population;
   }

   public void setPopulation(List<Node> population) {
      this.population = population;
   }

   public int getNumberOfCluster() {
      return numberOfCluster;
   }

   public void setNumberOfCluster(int numberOfCluster) {
      this.numberOfCluster = numberOfCluster;
   }

   public int[] getPopulationArray() {
      return populationArray;
   }

   public void setPopulationArray(int[] populationArray) {
      this.populationArray = populationArray;
   }

   abstract void initializePopulation();

   abstract void fitnessFunction();

   abstract void selection();

   abstract void crossover();

   abstract void mutation();

   // template method
   public final void play() {

      // initialize the population
      initializePopulation();

      // calculate fitness of individuals
      fitnessFunction();

      // selection for evoluation
      selection();

      // crossover
      crossover();

      // mutation
      mutation();

   }

public double getTurboMQ() {
	return turboMQ;
}

public void setTurboMQ(double turboMQ) {
	this.turboMQ = turboMQ;
}
}