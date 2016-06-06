package com.jasonmckay.assignment63.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;
import com.jasonmckay.assignment63.services.TotalCostBikeService.*;

/**
 * Created by JasonMckay on 10-May-16.
 */

public class TestTotalCostBikeService extends AndroidTestCase
{
    TotalCostBikeService myService;
    boolean isBound = false;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Intent intent = new Intent(this.getContext(), TotalCostBikeService.class);
        this.getContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    public ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BikeCostBinder binder = (BikeCostBinder) service;
            myService = binder.getService();
            isBound = true;

            float costOfBikes = myService.calculateBikeCost();
            System.out.println("Cost: " + costOfBikes);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        if(isBound)
        {
            this.getContext().unbindService(myConnection);
            isBound = false;
        }
    }
}
