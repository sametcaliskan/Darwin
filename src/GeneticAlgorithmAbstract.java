import java.util.List;

public abstract class GeneticAlgorithmAbstract {
   private Population population;
   private List<Individual> selectedIndividuals;

   protected GeneticAlgorithmAbstract() {
   }

   public Population getPopulation() {
      return population;
   }

   public void setPopulation(Population population) {
      this.population = population;
   }

   abstract void initializePopulation(int individualNumber, int numberOfCluester, List<Node> nodeList);

   abstract void fitnessFunction();

   abstract void selection();

   abstract void crossover();

   abstract void mutation();

   // template method
   public final void play() {

      // initialize the population
      // initializePopulation();

      // calculate fitness of individuals
      fitnessFunction();

      // selection for evoluation
      selection();

      // crossover
      crossover();

      // mutation
      mutation();

   }

   public List<Individual> getSelectedIndividuals() {
      return selectedIndividuals;
   }

   public void setSelectedIndividuals(List<Individual> selectedIndividuals) {
      this.selectedIndividuals = selectedIndividuals;
   }
}