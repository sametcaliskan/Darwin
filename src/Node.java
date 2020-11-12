/**
 * A class that represents a Node in a Graph. Nodes contain an array of
 * integers that represents the Node's dependencies (edges or connections with
 * other nodes) an array of integers which represent the weights of that
 * dependencies, a name and the number of its current cluster (as a back pointer
 * for some optimization method that might require that information, such as the
 * GA optimization method).<P>
 * The structure of the two arrays is as follows:<P>
 * <pre>
 * dependencies = 1 2 5
 * weights      = 2 1 1
 * </pre>
 * This means that the current node has two connections to node 1, one
 * connection to node 2 and one connection to node five. Connections are only
 * be specified in the node from which they originate.
 *
 * inspired from Diego Doval
 * @version 1.0
 */
public
class Node  implements java.io.Serializable
{

    private static final long serialVersionUID = 1234L;
/**
 * The array of dependencies (edges) to other nodes
 */
public int[] dependencies;
/**
 * The array of weights
 */
public int[] weights;
/**
 * Unique node id
 */
public int nodeID;
/**
 * The string's name
 */
String nodeName;
/**
 * The Node's current cluster
 */
int cluster;
/**
 * Empty contructor
 */
public Node(){

}
/**
 * Node constructor that receives a node's name, an array of dependencies and
 * an array of weights.
 *
 * @param name the node's name
 * @param deps the list of dependencies (edges) for this node
 * @param weights the list of weights for each of the dependencies for this node
 * @param nodeID the unique node id
 */
public
Node(String name, int[] deps, int[] weights, int nodeID)
{
  setName(name);
  setDependencies(deps);
  setWeights(weights);
  setNodeID(nodeID);
}

/**
 * Node constructor that receives a node's name, an array of dependencies and
 * an array of weights. This constructor uses a weight of 1 for each dependency
 * as default.
 *
 * @param name the node's name
 * @param deps the list of dependencies (edges) for this node
 * @param nodeID the unique node id
 */
public
Node(String name, int[] deps, int nodeID)
{
  setDependencies(deps);
  int[] ws = new int[deps.length];
  for (int i=0; i<deps.length; ++i) {
    ws[i] = 1;
  }
  setWeights(ws);
  setName(name);
  setNodeID(nodeID);
}

public int getCluster()
{ return cluster; }

public void setCluster(int c)
{ cluster = c; }

public int getId()
{ return nodeID;  }

public void setNodeID(int c)
{ nodeID = c; }


/**
 * Converts this node's information into human-readable format (useful for
 * debugging).
 *
 * @return a string with the node's information
 */
public
String
toString()
{
    String str = new String();
    str += "\n"+nodeName + " = ";
    for (int i=0; i<dependencies.length; ++i) {
        str += dependencies[i] + " / ";
    }
    return str;
}

/**
 * Obtains this Node's name.
 *
 * @return the node's name as a string
 * @see #setName(java.lang.String)
 */
public
String
getName()
{
    return nodeName;
}

/**
 * Sets this Node's name
 *
 * @param name the new node's name
 * @see #getName()
 */
public
void
setName(String name)
{
    nodeName = name;
}

/**
 * Sets the array of dependencies (edges) for this Node.
 *
 * @param deps the array of dependencies (edges)
 * @see #getDependencies()
 * @see #weights
 */
public
void
setDependencies(int[] deps)
{
    dependencies = deps;
}

/**
 * Obtains the array of dependencies (edges) for this Node
 *
 * @return the array of dependencies (edges)
 * @see #setDependencies(int[])
 * @see #weights
 */
public
int[]
getDependencies()
{
    return dependencies;
}

/**
 * Sets the array of weights for this Node's connections with other
 * nodes.
 *
 * @param ws the array of weights to set
 * @see #getWeights()
 * @see #dependencies
 */
public
void
setWeights(int[] ws)
{
    weights = ws;
}

/**
 * Obtains the array of weights for this Node's connections with other
 * nodes.
 *
 * @return the array of weights
 * @see #setWeights(int[])
 * @see #dependencies
 */
public
int[]
getWeights()
{
    return weights;
}

/**
 * Returns a copy of the current node.
 *
 * @return a copy of this node
 */
public
Node
cloneNode()
{
  Node n = new Node();
  n.setName(getName());
  n.cluster = cluster;
  if (dependencies != null) {
    n.dependencies = new int[dependencies.length];
    System.arraycopy(dependencies, 0, n.dependencies, 0, dependencies.length);
  }
  if (weights != null) {
    n.weights = new int[weights.length];
    System.arraycopy(weights, 0, n.weights, 0, weights.length);
  }

  n.nodeID = nodeID;
  return n;
}
}