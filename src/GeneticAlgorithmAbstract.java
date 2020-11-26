import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GeneticAlgorithmAbstract {
   private Population population;
   private List<Double> maxTurboMQList;

   protected GeneticAlgorithmAbstract() {
	   setMaxTurboMQList(new ArrayList<>());
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
	  Parser parser = new Parser("libs/sample-projects/bash");
      List<Node> nodes = null;
	  try {
		  nodes = parser.getNodes();
	  } catch (IOException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
      int numberOfCluester = parser.getNumberOfCluster();

      int individualNumber = 100;
      initializePopulation(individualNumber, numberOfCluester, nodes);


      // calculate fitness of individuals
	  fitnessFunction();
	  for(int i=0; i<10; i++) {
		 System.out.println("evoluation: "+i);
	      // selection for evoluation
	      selection();
	      // crossover
	      crossover();
	      // mutation
	      mutation();
	  
	      fitnessFunction();
	  }
	  
	  for(Double d: getMaxTurboMQList())
		  System.out.print(d+" ");
   }

public List<Double> getMaxTurboMQList() {
	return maxTurboMQList;
}

public void setMaxTurboMQList(List<Double> maxTurboMQList) {
	this.maxTurboMQList = maxTurboMQList;
}
}