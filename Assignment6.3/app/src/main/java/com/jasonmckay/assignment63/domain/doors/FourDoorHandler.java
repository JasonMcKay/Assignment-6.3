package com.jasonmckay.assignment63.domain.doors;

import java.io.Serializable;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public class FourDoorHandler  extends DoorHandler  implements Serializable {
    @Override
    public String handleRequest(String request) {
        if(request.equalsIgnoreCase("Four door"))
        {
            return "Four door";
        }
        else
        {
            return successor.handleRequest(request);
        }
    }
}
