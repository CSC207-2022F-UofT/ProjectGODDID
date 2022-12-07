package Interfaces;

import entities.Graph;

public interface ReadGraphInt {
    /**
     * Interface which is in between the read graph and account manager for dependency inversion
     */
    public Graph readobject();
}
