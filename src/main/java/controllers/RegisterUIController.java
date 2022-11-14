package controllers;

import useCases.SuccessfulRegister;

public class RegisterUIController {
    public void performRegister(String pWord, String name, String mail, String acc_type) {
        SuccessfulRegister successfulRegister = new SuccessfulRegister();
        successfulRegister.register(pWord, name, mail, acc_type);
    }
}
