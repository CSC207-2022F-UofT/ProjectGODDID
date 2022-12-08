package Databases;

import entities.Graph;
import Interfaces.WriteGraphInt;

import java.io.*;

public class WriteGraph implements Serializable, WriteGraphInt {

    /**
     * @param users
     * @throws IOException
     */

    /**
     * Writes to the existing Users.ser file to update the changes in the users
     */
    @Override
    public void writeGraph(Graph users) throws IOException {
        FileOutputStream fos = new FileOutputStream("src/Users.ser", false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(users);

        oos.close();
        fos.close();
    }
}
