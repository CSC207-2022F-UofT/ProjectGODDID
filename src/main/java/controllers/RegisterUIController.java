package controllers;

import useCases.SuccessfulRegister;

import java.io.IOException;

public class RegisterUIController {
    public void performRegister(String pWord, String name, String acc_type) throws IOException, ClassNotFoundException {
        SuccessfulRegister successfulRegister = new SuccessfulRegister();
        successfulRegister.register(pWord, name, acc_type);
    }
}
