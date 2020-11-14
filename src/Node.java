import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a Node in a Graph. Nodes contain an array of integers
 * that represents the Node's dependencies (edges or connections with other
 * nodes) an array of integers which represent the weights of that dependencies,
 * a name and the number of its current cluster (as a back pointer for some
 * optimization method that might require that information, such as the GA
 * optimization method).
 * <P>
 * The structure of the two arrays is as follows:
 * <P>
 * 
 * <pre>
 * dependencies = 1 2 5
 * weights      = 2 1 1
 * </pre>
 * 
 * This means that the current node has two connections to node 1, one
 * connection to node 2 and one connection to node five. Connections are only be
 * specified in the node from which they originate.
 *
 * inspired from Diego Doval
 * 
 * @version 1.0
 */
public class Node implements java.io.Serializable {

  private static final long serialVersionUID = 1234L;
  /**
   * The array of dependencies (edges) to other nodes
   */
  private List<Node> dependencies;
  /**
   * Unique node id
   */
  private int nodeID;
  /**
   * The string's name
   */
  private String name;
  /**
   * The Node's current cluster
   */
  private int cluster;

  private int interDependencyCount;

  private int intraDependencyCount;

  /**
   * Empty contructor
   */
  public Node() {

  }

  /**
   * Node constructor that receives a node's name, an array of dependencies and an
   * array of weights.
   *
   * @param name   the node's name
   * @param nodeID the unique node id
   */
  public Node(String name, int nodeID) {
    setName(name);
    setNodeID(nodeID);
    setCluster(0);
    this.dependencies = new ArrayList<>();
    this.interDependencyCount = 0;
    this.intraDependencyCount = 0;
  }

  public int getCluster() {
    return cluster;
  }

  public void setCluster(int c) {
    cluster = c;
  }

  public int getId() {
    return nodeID;
  }

  public void setNodeID(int c) {
    nodeID = c;
  }

  /**
   * Converts this node's information into human-readable format (useful for
   * debugging).
   *
   * @return a string with the node's information
   */
  public String toString() {
    StringBuilder str = new StringBuilder();
    str.append("\n" + this.name + " = ");
    for (Node n : dependencies) {
      str.append(n.getName() + " / ");
    }
    return str.toString();
  }

  /**
   * Obtains this Node's name.
   *
   * @return the node's name as a string
   * @see #setName(String)
   */
  public String getName() {
    return name;
  }

  /**
   * Sets this Node's name
   *
   * @param name the new node's name
   * @see #getName()
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the list of dependencies (nodes) for this Node.
   *
   * @param node the object of node
   * @see #getDependencies()
   */
  public void setDependencies(Node node) {
    if (!isNodeInDependenciesList(node.getName())) {
      dependencies.add(node);
    }
  }

  /**
   * Obtains the list of dependencies (nodes) for this Node
   *
   * @return the list of dependencies (nodes)
   * @see #setDependencies(Node)
   */
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
    setDependencyCounts();
    return this.interDependencyCount;
  }

  public int getIntraDependencyCount() {
    setDependencyCounts();
    return this.intraDependencyCount;
  }

  private void setDependencyCounts() {
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
}