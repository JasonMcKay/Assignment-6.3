package com.jasonmckay.assignment63.factories.vehicle;

import com.jasonmckay.assignment63.domain.doors.*;
import com.jasonmckay.assignment63.domain.engine.*;

import java.io.Serializable;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public class Vehicle implements Serializable
{
    private EngineType engine;
    private DoorHandler doors1 = new FourDoorHandler();
    private DoorHandler doors2 = new TwoDoorHandler();
    private DoorHandler doors3 = new NoDoorHandler();
    private String doors;

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public String getDoors() {
        return doors;
    }

    public String getFuelType()
    {
        String fuelType = engine.fuelType();
        return fuelType;
    }

    public void setDoors(String doorType) {
        doors1.setSuccessor(doors2);
        doors2.setSuccessor(doors3);

        this.doors = doors1.handleRequest(doorType);
    }

    public String toString() {
        if (doors.equalsIgnoreCase("no doors")) {
            return "Bike built!!!!\n" + engine.toString() + "\nFuel type: " + engine.fuelType() + "\nDoors: " + doors;
        } else {
            return "Car built!!!!\n" + engine.toString() + "\nFuel type: " + engine.fuelType() + "\nDoors: " + doors;
        }
    }
}
