package useCases;


import java.io.IOException;

public class SuccessfulRegister {
    public SuccessfulRegister(){
    }

    public void register(String pWord, String name, String acc_type) throws IOException, ClassNotFoundException {
        AccountManager temp = new AccountManager();
        //temp.addUser(name, pWord, acc_type);
    }
}
