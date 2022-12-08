package Databases;

import entities.Graph;
import entities.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WriteGraph implements Serializable{
    public void writeGraph(Graph users) throws IOException{

        FileOutputStream fos = new FileOutputStream("Users.ser", false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(users);

        oos.close();
        fos.close();
    }


}
