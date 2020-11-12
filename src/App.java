public class App {
    public static void main(String[] args) {

        Node[] allNodes = Parser.getNodes();

        GeneticAlgorithmAbstract cricket = new Cricket(allNodes);
        cricket.play();

    }
}
