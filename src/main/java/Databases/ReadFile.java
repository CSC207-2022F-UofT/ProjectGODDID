package Databases;

import entities.User;
import useCases.UserCreator;

import java.io.*;
import java.util.ArrayList;

public class ReadFile implements Serializable{

    public ArrayList<User> readObject() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("Users.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<User> users;

        users = (ArrayList<User>) ois.readObject();
        ois.close();
        fis.close();

        return users;



    }
}