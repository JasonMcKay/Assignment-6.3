package com.jasonmckay.assignment63.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;
/*
*  This is a bound service, that is called to delete all
*  vehicles, and indicate to the user how many vehicles
*  were deleted. The need for using a local, bound service
*  is because the client will require some data from this
*  service (amount of vehicles removed).
* */

public class ClearManufacturingService extends Service
{
    private final IBinder manufacturingBinder = new MyLocalBinder();
    private RepositoryManufacturer repository;

    public ClearManufacturingService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return  manufacturingBinder;
    }

    public int clearVehicles()
    {
        repository = new RepositoryManufacturerImpl(this.getApplicationContext());
        int rowsDeleted = repository.deleteVehicles();
        return rowsDeleted;
    }

    public class MyLocalBinder extends Binder
    {
        ClearManufacturingService getService()
        {
            return ClearManufacturingService.this;
        }
    }
}
