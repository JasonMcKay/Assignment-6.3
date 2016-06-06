package com.jasonmckay.assignment63.domain.doors;

import java.io.Serializable;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public class NoDoorHandler extends DoorHandler  implements Serializable {
    @Override
    public String handleRequest(String request) {
        if(request.equalsIgnoreCase("No door"))
        {
            return "No door";
        }
        else
        {
            return successor.handleRequest(request);
        }
    }
}
