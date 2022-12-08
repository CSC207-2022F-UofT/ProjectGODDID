import Databases.ReadGraph;
import UI.LoginPage;
import entities.Graph;
import entities.User;

import java.io.IOException;

public class secondlog {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ReadGraph rg = new ReadGraph();

        Graph user_graph = rg.readobject();

        User b = user_graph.accounts.get("ee");

        int s = b.getNum_strikes();
        String r = String.valueOf(s);

        System.out.println(r);
    }
}
