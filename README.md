## Darwin

Project is created to apply variants of clustering to genetic algorithm.  

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Dependency Management

The `JAVA DEPENDENCIES` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-pack/blob/master/release-notes/v0.9.0.md#work-with-jar-files-directly).

## Class Structure

# Node.java

Node includes dependencies, weight of dependencies, a unique nodeID and nodeName.
nodeName should be name of the class where it is written in .rsf files.
dependencies should include relations of the node with other nodes.
weights should be aligned respect to dependencies and it represents strong of relation.
nodeId should be unique.
