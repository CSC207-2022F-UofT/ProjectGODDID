package Databases;

import entities.User;

import java.io.*;
import java.util.ArrayList;

public class WriteFile implements Serializable{
    public void writefile(User user) throws IOException, ClassNotFoundException {

        ArrayList<User> users = new ArrayList<User>();



        try {
            ReadFile reader = new ReadFile();
            users = reader.readobject();
            users.add(user);

            FileOutputStream fos = new FileOutputStream("Users.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);

            oos.close();
            fos.close();
        } catch (EOFException e){
            FileOutputStream fos = new FileOutputStream("Users.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            users.add(user);

            oos.writeObject(users);

            oos.close();
            fos.close();
        }


    }
}