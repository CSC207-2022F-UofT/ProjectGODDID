package useCases;

import entities.Graph;

import java.io.*;

public class AddGraphToFile {
    public AddGraphToFile(){

    }

    public void AddToFile(Graph graph) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/Graph.txt", false));
        out.writeObject(graph);
    }

    public Graph ReadFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("src/Graph.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Graph) ois.readObject();
    }
}


