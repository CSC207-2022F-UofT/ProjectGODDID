package controllers;

import useCases.SuccessfulRegister;

public class RegisterUIController {
    public void performRegister(String pWord, String name, String acc_type) {
        SuccessfulRegister successfulRegister = new SuccessfulRegister();
        successfulRegister.register(pWord, name, acc_type);
    }
}
