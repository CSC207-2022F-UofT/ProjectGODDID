package entities;
import java.util.*;

public class Graph{
    public Map<Vertex, List<Vertex>> accounts = new HashMap<>();

    public void addVertex(String name, String account_type, User curr_user)
    {
        accounts.putIfAbsent(new Vertex(name, account_type, curr_user), new ArrayList<>());
    }

    public void removeVertex(String name, String account_type, User curr_user)
    {
        Vertex v = new Vertex(name, account_type, curr_user);
        accounts.values().forEach(e -> e.remove(v));
        accounts.remove(new Vertex(name, account_type, curr_user));
    }

    public void addEdge(String user1, String a1, User u1, String user2, String a2, User u2)
    {
        Vertex v1 = new Vertex(user1, a1, u1);
        Vertex v2 = new Vertex(user2, a2, u2);
        accounts.get(v1).add(v2);
        accounts.get(v2).add(v1);
    }

    public void removeEdge(String user1, String a1, User u1, String user2, String a2, User u2)
    {
        Vertex v1 = new Vertex(user1, a1, u1);
        Vertex v2 = new Vertex(user2, a2, u2);
        List<Vertex> v1_neighbours = accounts.get(v1);
        List<Vertex> v2_neighbours = accounts.get(v2);
        if (v1_neighbours != null)
            v1_neighbours.remove(v2);
        if (v2_neighbours != null)
            v2_neighbours.remove(v1);

    }

    public Set<Vertex> getVertices()
    {
        return this.accounts.keySet();
    }

}