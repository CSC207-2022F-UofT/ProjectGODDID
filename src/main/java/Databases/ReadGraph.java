package Databases;

import entities.Graph;
import Interfaces.ReadGraphInt;

import java.io.*;

public class ReadGraph implements Serializable, ReadGraphInt {

    /**
     * @return Graph
     */

    /**
     * returns the saved graph containing the users by reading from the ser file
     */
    @Override
    public Graph readobject() {
        Graph users = new Graph();
        try {
            FileInputStream fis = new FileInputStream("src/Users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            users = (Graph) ois.readObject();

            ois.close();
            fis.close();
            return users;
        } catch (IOException | ClassNotFoundException e){
            //users = new Graph();
            return users;
        }
    }


}
