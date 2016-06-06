package com.jasonmckay.assignment63.services;

import android.content.Intent;
import android.test.AndroidTestCase;

/**
 * Created by JasonMckay on 12-May-16.
 */
public class TestVehicleBuildService extends AndroidTestCase
{
    @Override
    public void setUp() throws Exception {
        super.setUp();

        Intent intent = new Intent(this.getContext(), VehicleBuildService.class);
        this.getContext().startService(intent);
    }
}
