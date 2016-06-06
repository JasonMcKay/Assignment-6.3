package com.jasonmckay.assignment63.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

/*
 * This is a bound service, that is called to calculate
 * the cost of all bikes built. The need for using a local,
 * bound service is because the client will require some
 * data from this service.
*/

public class TotalCostBikeService extends Service {

    private final IBinder bikeCostBinder = new BikeCostBinder();
    private RepositoryManufacturer repository;

    public TotalCostBikeService() {
    }

    public float calculateBikeCost()
    {
        repository = new RepositoryManufacturerImpl(this.getApplicationContext());
        int amountOfBikes = 20;
        float costPerBike = 35000;

        return (amountOfBikes * costPerBike);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return bikeCostBinder;
    }

    public class BikeCostBinder extends Binder
    {
        TotalCostBikeService getService()
        {
            return TotalCostBikeService.this;
        }
    }
}
