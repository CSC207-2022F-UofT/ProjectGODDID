package PointSystem;

import entities.User;

import java.io.IOException;

public abstract class PointSystem {
    /**
     * Abstract parent class for the two different PointSystem classes .
     */

    /**
     * abstract method that must be implemented by the subclasses, allows polymorphism when an Event is executed
     * @param X
     * @param spendCase
     * @throws IOException
     */
    public abstract void adjustPoints(User X, String spendCase) throws IOException;
}
