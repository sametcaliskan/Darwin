public class App {
    public static void main(String[] args) throws Exception {

        Node[] allNodes = Parser.getNodes();

        GeneticAlgorithmAbstract cricket = new Cricket(allNodes);
        cricket.play();

    }
}
