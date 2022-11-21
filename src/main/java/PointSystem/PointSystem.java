package PointSystem;

import entities.User;

public abstract class PointSystem {
    /**
     * Abstract parent class for the two different PointSystem classes .
     */

    public abstract void adjustPoints(User X, String spendCase);
}
