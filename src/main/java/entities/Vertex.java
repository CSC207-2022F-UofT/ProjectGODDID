package entities;

public class Vertex {
    String user_name, account_type;
    User curr_user;
    public Vertex (String name, String account_type, User curr_user)
    {
        user_name = name;
        this.account_type = account_type;
        this.curr_user = curr_user;
    }
}
