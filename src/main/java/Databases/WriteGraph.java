package Databases;

import entities.Graph;

import java.io.*;

public class WriteGraph implements Serializable{
    public void writefile(Graph users) throws IOException, ClassNotFoundException {

        FileOutputStream fos = new FileOutputStream("Users.ser", false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(users);

        oos.close();
        fos.close();
    }


}
