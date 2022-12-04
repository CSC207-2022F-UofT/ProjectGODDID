package Databases;

import entities.Graph;
import Interfaces.ReadGraphInt;

import java.io.*;

public class ReadGraph implements Serializable, ReadGraphInt {

    @Override
    public Graph readobject() {
        Graph users = new Graph();
        try {
            FileInputStream fis = new FileInputStream("Users.ser");
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
