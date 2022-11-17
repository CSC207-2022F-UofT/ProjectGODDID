package Databases;

import entities.Graph;
import entities.User;
import useCases.UserCreator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadFile implements Serializable{

    public Graph readobject() throws IOException, ClassNotFoundException {
        Graph users = new Graph();
        try {
            FileInputStream fis = new FileInputStream("Users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            users = (Graph) ois.readObject();

            ois.close();
            fis.close();
        } catch (EOFException e){
            users = new Graph();
            return users;
        }


        return users;



    }
}