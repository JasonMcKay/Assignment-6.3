package com.jasonmckay.assignment63.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;

/**
 * This is an intent service that is called
 * to build a vehicle for use.
 * I used an intent service because i do not
 * need a response from the service.
 */

public class VehicleBuildService extends IntentService {

    public VehicleBuildService() {
        super("VehicleBuildService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Manufacturer vehicle = new Manufacturer.Builder()
                .vehicle("leaded", "two door")
                .build();
        System.out.println("Vehicle: " + vehicle.toString());
    }

}
