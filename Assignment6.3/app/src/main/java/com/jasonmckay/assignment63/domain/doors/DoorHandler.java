package com.jasonmckay.assignment63.domain.doors;

import java.io.Serializable;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public abstract class DoorHandler implements Serializable {
    DoorHandler successor;

    public void setSuccessor(DoorHandler successor)
    {
        this.successor = successor;
    }

    public abstract String handleRequest(String request);
}
