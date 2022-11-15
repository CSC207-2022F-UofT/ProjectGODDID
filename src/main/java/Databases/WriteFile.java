package Databases;

import entities.User;

import java.io.*;
import java.util.ArrayList;

public class WriteFile implements Serializable{
    public void writefile(User user) throws IOException, ClassNotFoundException {

        ArrayList<User> users = new ArrayList<User>();

        FileOutputStream fos = new FileOutputStream("Users.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        try {
            ReadFile reader = new ReadFile();
            users = reader.readObject();
            users.add(user);
            oos.writeObject(users);

            oos.close();
            fos.close();
        } catch (EOFException e){
            users.add(user);

            oos.writeObject(users);

            oos.close();
            fos.close();
        }


    }
}
