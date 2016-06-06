package com.jasonmckay.assignment63.services;

import android.content.Intent;
import android.test.AndroidTestCase;

/**
 * Created by JasonMckay on 10-May-16.
 */
public class TestRemoveVehicleService extends AndroidTestCase
{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), RemoveVehicleService.class);
        intent.putExtra("engineType", "Leaded");
        intent.putExtra("doorType", "four door");
        this.getContext().startService(intent);
    }


}
