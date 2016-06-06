package com.jasonmckay.assignment63.services;

import android.app.IntentService;
import android.content.Intent;
import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

/**
 * This is an intent service that is called
 * to delete a vehicle into the database.
 * I used an intent service because i do not
 * need a response from the service.
 */

public class RemoveVehicleService extends IntentService {

    RepositoryManufacturer repository;

    public RemoveVehicleService() {
        super("RemoveVehicleService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        repository = new RepositoryManufacturerImpl(this.getApplicationContext());

        String engineType = intent.getStringExtra("engineType");
        String doorType = intent.getStringExtra("doorType");
        Manufacturer vehicle = new Manufacturer.Builder()
                .vehicleID(1)
                .vehicle(engineType, doorType)
                .build();
        repository.deleteVehicle(vehicle);
    }
}
