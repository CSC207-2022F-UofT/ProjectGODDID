package useCases;

import entities.User;

public class SuccessfulRegister {
    public SuccessfulRegister(){

    }

    public void register(String pWord, String name, String mail){
        User user = new User();
        user.setPassword(pWord);
        user.setUsername(name);
        user.setEmail(mail);
        System.out.println("User made: " + user.getEmail());
    }


}
