public abstract class GeneticAlgorithmAbstract {
    abstract void initializePopulation();
    abstract void fitnessFunction();
    abstract void selection();
    abstract void crossover();
    abstract void mutation();
   
    //template method
    public final void play(){
 
       //initialize the population
       initializePopulation();

       //calculate fitness of individuals
       fitnessFunction();

       //selection for evoluation
       selection();

       //crossover
       crossover();
 
       //mutation
       mutation();

       
    }
 }