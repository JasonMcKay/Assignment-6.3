package com.jasonmckay.assignment63.domain.engine;

import java.io.Serializable;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public class UnleadedPetrol implements EngineType, Serializable
{
    private String fuelType;
    private double capacity = 1200;
    private double oilInLiters = 1.6;

    public void setFuelType(String fuelType)
    {
        this.fuelType = fuelType;
    }

    public String fuelType() {
        return "Unleaded";
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getOilInLiters() {
        return oilInLiters;
    }

    public void setOilInLiters(double oilInLiters) {
        this.oilInLiters = oilInLiters;
    }

    public String toString()
    {
        return "Engine:"+"\nSize(cc): "+capacity+"\nLiters: "+oilInLiters;
    }
}
