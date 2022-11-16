package entities;

<<<<<<<<< Temporary merge branch 1

public class Vertex{
    String user_name, account_type;
    User curr_user;
=========
public class Vertex {
    public String user_name, account_type;
    public User curr_user;
>>>>>>>>> Temporary merge branch 2
    public Vertex (String name, String account_type, User curr_user)
    {
        user_name = name;
        this.account_type = account_type;
        this.curr_user = curr_user;
    }
}
