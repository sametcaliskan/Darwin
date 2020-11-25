import java.io.BufferedWriter;
import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException; // Import this class to handle errors
import java.io.OutputStreamWriter;
import java.util.*;

public class Parser {

    private String path;
    private int numberOfCluster;
    private int index;

    public Parser(String path) {
        this.path = path;
        this.numberOfCluster = 0;
        this.index = 0;
    }
    
    public Parser() {
		this.numberOfCluster = 0;
	    this.index = 0;
    }

    public void generateDependencyRsf(List<Node> nodeList, String fileName) throws IOException {
        File fout = new File("libs/outputs/dependencies/" + fileName);
        // fout.createNewFile();
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (Node n : nodeList) {
            for (Node dependentNodes : n.getDependencies()) {
                bw.write("depends " + n.getName() + " " + dependentNodes.getName());
                bw.newLine();
            }
        }
        bw.close();
    }

    public void generateClusterRsf(List<Node> nodeList, String fileName) throws IOException {
        File fout = new File("libs/outputs/clusters/" + fileName);
        // fout.createNewFile();
        FileOutputStream fos = new FileOutputStream(fout);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        List<Node> sorted = sortByCluster(nodeList);

        for (Node n : sorted) {
            bw.write("contains "+n.getCluster() + " " + n.getName());
            bw.newLine();
        }
        bw.close();
    }

    private List<Node> sortByCluster(List<Node> nodeList) {
        List<Node> returnList = new ArrayList<Node>();
        for (int i = 1; i < 15; i++) {
            for (Node n : nodeList) {
                if (n.getCluster() == i) {
                    returnList.add(n);
                }
            }
        }
        return returnList;
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