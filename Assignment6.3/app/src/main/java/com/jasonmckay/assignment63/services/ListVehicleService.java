package com.jasonmckay.assignment63.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;
import java.io.Serializable;
import java.util.Set;

/*
 * This is a bound service, that is called to return a list
 * of all vehicles. The need for using a local, bound
 * service is because the client will require some data
 * from this service. I also had to include a broadcast
 * messenger and receiver to return a Set object.
*/

public class ListVehicleService extends Service
{
    private final IBinder listBinder = new LocalListBinder();
    RepositoryManufacturer repository;

    public ListVehicleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return listBinder;
    }

    public void sendList()
    {
        sendBroadcast();
    }

    private void sendBroadcast()
    {
        Intent intent = new Intent("vehicleList");
        repository = new RepositoryManufacturerImpl(this.getApplicationContext());
        Set<Manufacturer> list = repository.findAllVehicles();
        intent.putExtra("list", (Serializable) list);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    public class LocalListBinder extends Binder
    {
        ListVehicleService getService()
        {
            return ListVehicleService.this;
        }
    }
}
