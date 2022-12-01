package Databases;

import entities.Graph;
import useCases.WriteGraphInt;

import java.io.*;

public class WriteGraph implements Serializable, WriteGraphInt {
//    public void writeGraph(Graph users) throws IOException{
//
//        FileOutputStream fos = new FileOutputStream("Users.ser", false);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//        oos.writeObject(users);
//
//        oos.close();
//        fos.close();
//    }


    @Override
    public void writeGraph(Graph users) throws IOException {
        FileOutputStream fos = new FileOutputStream("Users.ser", false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(users);

        oos.close();
        fos.close();
    }
}
