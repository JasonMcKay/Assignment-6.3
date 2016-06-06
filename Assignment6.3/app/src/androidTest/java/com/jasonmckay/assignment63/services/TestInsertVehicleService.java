package com.jasonmckay.assignment63.services;

import android.content.Intent;
import android.test.AndroidTestCase;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;

/**
 * Created by JasonMckay on 10-May-16.
 */
public class TestInsertVehicleService extends AndroidTestCase
{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), InsertVehicleService.class);
        intent.putExtra("engineType", "Leaded");
        intent.putExtra("doorType", "four door");
        this.getContext().startService(intent);
    }
}
