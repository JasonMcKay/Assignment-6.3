package com.jasonmckay.assignment63.services;

import android.content.Intent;
import android.test.AndroidTestCase;

/**
 * Created by JasonMckay on 10-May-16.
 */
public class TestUpdateVehicleService extends AndroidTestCase
{
    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), UpdateVehicleService.class);
        intent.putExtra("engineType", "Leaded");
        this.getContext().startService(intent);
    }
}
