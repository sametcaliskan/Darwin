import java.util.ArrayList;
import java.util.List;

public class Node implements java.io.Serializable {

  private static final long serialVersionUID = 1234L;
  private List<Node> dependencies;
  private int nodeID;
  private String name;
  private int cluster;
  private int interDependencyCount;
  private int intraDependencyCount;

  public Node() {
  }

  public Node(String name, int nodeID) {
    setName(name);
    setNodeID(nodeID);
    setCluster(0);
    setDependencies(new ArrayList<>());
    setIntraDependencyCount(0);
    setInterDependencyCount(0);
  }

  public int getCluster() {
    return cluster;
  }

  public void setCluster(int c) {
    cluster = c;
    calculateDependency();
  }

  public int getId() {
    return nodeID;
  }

  public void setNodeID(int c) {
    nodeID = c;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDependencies(Node node) {
    if (!isNodeInDependenciesList(node.getName())) {
      dependencies.add(node);
    }
  }

  public List<Node> getDependencies() {
    return dependencies;
  }

  private boolean isNodeInDependenciesList(String name) {
    for (Node node : dependencies) {
      if (node.getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  public int getInterDependencyCount() {
    return this.interDependencyCount;
  }

  public int getIntraDependencyCount() {
    return this.intraDependencyCount;
  }

  public void calculateDependency() {
    this.intraDependencyCount = 0;
    this.interDependencyCount = 0;
    for (Node node : this.dependencies) {
      if (node.getCluster() == this.getCluster()) {
        incrementIntraDependencyCount();
      } else {
        incrementInterDependencyCount();
      }
    }
  }

  private void incrementInterDependencyCount() {
    this.interDependencyCount += 1;
  }

  private void incrementIntraDependencyCount() {
    this.intraDependencyCount += 1;
  }

  public void setDependencies(List<Node> dependencies) {
    this.dependencies = dependencies;
  }

  public void setInterDependencyCount(int interDependencyCount) {
    this.interDependencyCount = interDependencyCount;
  }

  public void setIntraDependencyCount(int intraDependencyCount) {
    this.intraDependencyCount = intraDependencyCount;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("\n" + this.name + " = ");
    for (Node n : dependencies) {
      str.append(n.getName() + " / ");
    }
    return str.toString();
  }
}