package com.jasonmckay.assignment63.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

/*
 * This is a bound service, that is called to calculate
 * the cost of all cars built. The need for using a local,
 * bound service is because the client will require some
 * data from this service.
*/

public class TotalCostCarService extends Service
{
    private final IBinder carCostBinder = new CarCostBinder();
    private RepositoryManufacturer repository;

    public TotalCostCarService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return carCostBinder;
    }

    public float calculateCarCost()
    {
        repository = new RepositoryManufacturerImpl(this.getApplicationContext());
        int amountOfCars = 50;
        float costPerCar = 65000;

        return (amountOfCars * costPerCar);
    }

    public class CarCostBinder extends Binder
    {
        TotalCostCarService getService()
        {
            return TotalCostCarService.this;
        }
    }
}
