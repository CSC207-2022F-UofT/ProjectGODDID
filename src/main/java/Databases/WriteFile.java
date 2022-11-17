package Databases;

import entities.Graph;
import entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WriteFile implements Serializable{
    public void writefile(Graph users) throws IOException, ClassNotFoundException {

            FileOutputStream fos = new FileOutputStream("Users.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(users);

            oos.close();
            fos.close();
        }


    }
