package Interfaces;

import entities.Graph;

import java.io.IOException;

public interface WriteGraphInt {

    /**
     * Interface which is in between the write graph and account manager for dependency inversion
     */
    public void writeGraph(Graph users) throws IOException;

}
