package com.jasonmckay.assignment63.domain.engine;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public interface EngineType
{
    public abstract String fuelType();

    public abstract double getCapacity();

    public abstract void setCapacity(double capacity);

    public abstract double getOilInLiters();

    public abstract void setOilInLiters(double oilInLiters);

    public abstract String toString();
}
