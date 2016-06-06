package com.jasonmckay.assignment63.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

/*
*  This is a bound service, that is called to see if or not
*  manufacturing is needed, if so then how mush. The reason
*  for using a local, bound service is because the client will
*  require some data from this service.
*/

public class ManufacturingService extends Service
{
    private final IBinder manufacturingBinder = new MyLocalBinder();

    RepositoryManufacturer repository;

    public ManufacturingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return manufacturingBinder;
    }

    public int getVehicleAmount()
    {
        repository = new RepositoryManufacturerImpl(this.getApplicationContext());
        int vehicles = repository.findAllVehicles().size();
        return vehicles;
    }

    public class MyLocalBinder extends Binder
    {
        public ManufacturingService getService()
        {
            return ManufacturingService.this;
        }
    }
}
