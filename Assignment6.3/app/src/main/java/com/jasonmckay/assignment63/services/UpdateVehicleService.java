package com.jasonmckay.assignment63.services;

import android.app.IntentService;
import android.content.Intent;
import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;


/**
 * This is an intent service that is called
 * to update a vehicle into the database.
 * I used an intent service because i do not
 * need a response from the service.
 */

public class UpdateVehicleService extends IntentService {

    RepositoryManufacturer repository;

    public UpdateVehicleService() {
        super("UpdateVehicleService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        repository = new RepositoryManufacturerImpl(this.getApplicationContext());

        String engineType = intent.getStringExtra("engineType");
        Manufacturer vehicle = new Manufacturer.Builder()
                .vehicleID(1)
                .vehicle("leaded", "two door")
                .build();

        Manufacturer modifiedVehicle = new Manufacturer.Builder()
                .copy(vehicle)
                .modifyVehicle(engineType)
                .build();
        repository.modifyVehicle(modifiedVehicle);
    }
}
