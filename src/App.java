public class App {
    public static void main(String[] args) throws Exception {
        GeneticAlgorithmAbstract cricket = new Cricket();
        cricket.play();
        System.out.println();
        GeneticAlgorithmAbstract football= new Football();
        football.play();
    }
}
