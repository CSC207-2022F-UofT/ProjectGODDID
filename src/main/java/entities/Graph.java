package entities;
import java.io.Serializable;
import java.util.*;

//public class Graph  implements Serializable {
//    public Map<Vertex, List<Vertex>> accounts = new HashMap<>();
//
//    public void addVertex(String name, String account_type, User curr_user)
//    {
//        accounts.putIfAbsent(new Vertex(name, account_type, curr_user), new ArrayList<>());
//    }
//
//    public void removeVertex(String name, String account_type, User curr_user)
//    {
//        Vertex v = new Vertex(name, account_type, curr_user);
//        accounts.values().forEach(e -> e.remove(v));
//        accounts.remove(new Vertex(name, account_type, curr_user));
//    }
//
//    //public void addEdge(String user1, String a1, User u1, String user2, String a2, User u2)
//    public void addEdge(Vertex v1, Vertex v2)
//    {
//        //user 1 adds user 2 as a friend.
//        //Vertex v1 = new Vertex(user1, a1, u1);
//        //Vertex v2 = new Vertex(user2, a2, u2);
//        accounts.get(v1).add(v2);
//    }
//
//    public void removeEdge(Vertex v1, Vertex v2)
//    {
//        //user 1 removes user 2 as a friend.
//        //Vertex v1 = new Vertex(user1, a1, u1);
//        //Vertex v2 = new Vertex(user2, a2, u2);
//        List<Vertex> v1_neighbours = accounts.get(v1);
//        //List<Vertex> v2_neighbours = accounts.get(v2);
//        if (v1_neighbours != null)
//            v1_neighbours.remove(v2);
//    }
//
//    public ArrayList<Vertex> getVertices()
//    {
//        return new ArrayList<>(this.accounts.keySet());
//    }
//}


public class Graph  implements Serializable {
    public Map<User, List<User>> accounts = new HashMap<>();

    public void addVertex(User u1)
    {
        accounts.putIfAbsent(u1, new ArrayList<>());
    }

    public void removeVertex(User curr_user)
    {
        accounts.values().forEach(e -> e.remove(curr_user));
        accounts.remove(curr_user);
    }

    //public void addEdge(String user1, String a1, User u1, String user2, String a2, User u2)
    public void addEdge(User u1, User u2)
    {
        //user 1 adds user 2 as a friend.
        //Vertex v1 = new Vertex(user1, a1, u1);
        //Vertex v2 = new Vertex(user2, a2, u2);
        accounts.get(u1).add(u2);
        accounts.get(u2).add(u1);
    }

    // dont know wtf this function is
    public void removeEdge(User u1, User u2)
    {
        //user 1 removes user 2 as a friend.
        //Vertex v1 = new Vertex(user1, a1, u1);
        //Vertex v2 = new Vertex(user2, a2, u2);
        List<User> v1_neighbours = accounts.get(u1);  //List of friends of u1
        List<User> v2_neighbours = accounts.get(u2);  //list of friends of u2
        //List<Vertex> v2_neighbours = accounts.get(v2);
        if (v1_neighbours != null)
            v1_neighbours.remove(u2);
        if (v2_neighbours != null)
            v2_neighbours.remove(u1);
    }

    public ArrayList<User> getVertices()
    {
        return new ArrayList<>(this.accounts.keySet());
    }
}