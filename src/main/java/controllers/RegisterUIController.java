package controllers;

import useCases.SuccessfulRegister;

public class RegisterUIController {
    public void performRegister(String pWord, String name, String mail) {
        SuccessfulRegister successfulRegister = new SuccessfulRegister();
        successfulRegister.register(name, pWord, mail);
    }
}
