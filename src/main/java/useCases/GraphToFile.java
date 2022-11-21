package useCases;

import entities.Graph;

import java.io.*;

public class GraphToFile {

    public void AddToFile(Graph graph) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("graph.dat"));
            out.writeObject(graph);
            out.close();
        }
        catch (IOException ioe){
            System.err.println("Error saving to file");
        }
    }

    public Graph ReadFromFile() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream fis = new ObjectInputStream(new FileInputStream("graph.dat"));
            Graph temp =  (Graph) fis.readObject();
            fis.close();
            return temp;
        }
        catch(IOException ioe)
        {
            System.err.println("Error saving to file");
            return null;
        }
        catch(ClassNotFoundException cnfe)
        {
            System.err.println("Object read is not a graph");
            return null;
        }
    }
}


//class AddGraphToFileDemo{
//    public static void main(String[] args) {
//
//    }
//}
