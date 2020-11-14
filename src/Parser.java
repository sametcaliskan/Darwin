import java.io.File; // Import the File class
import java.io.IOException; // Import this class to handle errors
import java.util.*;

public class Parser {

    private final String path;
    private int numberOfCluster;
    private int index;

    public Parser(String path) {
        this.path = path;
        this.numberOfCluster = 0;
        this.index = 0;
    }

    // TODO: should be refactored
    public List<Node> getNodes() throws IOException {
        List<Node> nodeList = new ArrayList<>();
        File folder = new File(this.path);
        Scanner scanner = null;
        String fileName;
        for (File fileEntry : folder.listFiles()) {
            fileName = fileEntry.getName();
            scanner = new Scanner(fileEntry);
            if (fileName.contains("-dep.rsf")) {
                String line;
                String[] eachString;
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    eachString = line.split(" ");
                    if (eachString[1].contains(".def") && !(eachString[2].contains(".def"))) {
                        getNodeByName(eachString[2], nodeList);
                        continue;
                    } else if (eachString[2].contains(".def") && !(eachString[1].contains(".def"))) {
                        getNodeByName(eachString[1], nodeList);
                        continue;
                    } else if (eachString[1].contains(".def") && eachString[2].contains(".def")) {
                        continue;
                    }
                    Node n1 = getNodeByName(eachString[1], nodeList);
                    Node n2 = getNodeByName(eachString[2], nodeList);
                    n1.setDependencies(n2);
                }
            } else if (fileName.contains("-gt.rsf")) {
                Set<String> gtClusterNameSet = new HashSet<>();
                while (scanner.hasNextLine()) {
                    gtClusterNameSet.add(scanner.nextLine().split(" ")[1]);
                }
                this.numberOfCluster = gtClusterNameSet.size();
            }
            scanner.close();
        }
        return nodeList;
    }

    public int getNumberOfCluster() {
        return this.numberOfCluster;
    }

    private Node getNodeByName(String name, List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        Node newNode = new Node(name, index++);
        nodeList.add(newNode);
        return newNode;
    }
}