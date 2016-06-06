package com.jasonmckay.assignment63.factories.manufacturer;

import com.jasonmckay.assignment63.factories.vehicle.*;
import java.io.Serializable;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public class Manufacturer implements Serializable {

    private VehicleBuilder vehicle;
    private Vehicle currentVehicle;
    private long vehicleId;

    public void print()
    {
        currentVehicle = vehicle.getVehicle();
        System.out.println(currentVehicle.toString());
    }

    public String getEngineType()
    {
        currentVehicle = vehicle.getVehicle();
        String type1 = currentVehicle.getFuelType();
        return type1;
    }

    public String getDoorType()
    {
        currentVehicle = vehicle.getVehicle();
        String type = currentVehicle.getDoors();
        return type;
    }

    public long getVehicleID()
    {
        return vehicleId;
    }

    public Manufacturer(Builder builder)
    {
        this.vehicle = builder.vehicle;
        this.vehicleId = builder.vehicleId;
    }

    public static class Builder
    {
        private VehicleBuilder vehicle = new VehicleBuilder();;
        private long vehicleId;

        public Builder vehicleID(long id)
        {
            this.vehicleId = id;
            return this;
        }

        public Builder vehicle(String engineType, String doorType)
        {
            this.vehicle.buildEngine(engineType);
            this.vehicle.buildDoors(doorType);
            return this;
        }

        public Builder modifyVehicle(String fuelType)
        {
            this.vehicle.modifyEngine(fuelType);
            return this;
        }

        public Builder copy(Manufacturer manufacturer)
        {
            this.vehicle = manufacturer.vehicle;
            this.vehicleId = manufacturer.vehicleId;
            return this;
        }

        public Manufacturer build()
        {
            return new Manufacturer(this);
        }
    }
}
