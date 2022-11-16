package useCases;


public class SuccessfulRegister {
    public SuccessfulRegister(){
    }

    public void register(String pWord, String name, String mail, String acc_type){
        AccountManager temp = new AccountManager();
        temp.addUser(name, mail, pWord, acc_type);
    }
}
