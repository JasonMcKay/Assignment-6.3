package com.jasonmckay.assignment63.domain.engine;

import java.io.Serializable;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public class EngineFactory implements Serializable
{
    private static EngineFactory engineFactory = null;

    public EngineFactory() {
    }

    public static EngineFactory getEngineFactoryInstance()
    {
        if(engineFactory == null)
        {
            return new EngineFactory();
        }
        return engineFactory;
    }

    public EngineType getEngineType(String fuelType)
    {
        System.out.println("********** " + fuelType + "***********");
        if(fuelType.equalsIgnoreCase("Diesel"))
        {
            return new Diesel();
        }
        else if(fuelType.equalsIgnoreCase("unleaded"))
        {
            return new UnleadedPetrol();
        }
        else if(fuelType.equalsIgnoreCase("leaded"))
        {
            return new LeadedPetrol();
        }

        return null;
    }
}
