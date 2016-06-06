package com.jasonmckay.assignment63.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

/*
*  This is a bound service, that is called to see if or not
*  ma vehicle exists, if so then return true otherwise return
*  false. The reason for using a local, bound service is because
*  the client will require some data from this service.
* */

public class VehicleSearchService extends Service
{
    private final IBinder findVehicleBinder = new LocalSearchBinder();
    private RepositoryManufacturer repository;

    public VehicleSearchService()
    {
    }

    public boolean vehicleExists(long id)
    {
        repository = new RepositoryManufacturerImpl(this.getApplicationContext());
        if(repository.findVehicle(id) != null) {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return findVehicleBinder;
    }

    public class LocalSearchBinder extends Binder
    {
        public VehicleSearchService getService()
        {
            return VehicleSearchService.this;
        }
    }
}
