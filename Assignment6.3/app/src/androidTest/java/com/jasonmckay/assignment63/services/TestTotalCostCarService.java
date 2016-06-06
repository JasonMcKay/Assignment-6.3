package com.jasonmckay.assignment63.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;
import com.jasonmckay.assignment63.services.TotalCostCarService.*;
/**
 * Created by JasonMckay on 10-May-16.
 */
public class TestTotalCostCarService extends AndroidTestCase
{
    TotalCostCarService myService;
    boolean isBound = false;

    @Override
    public void setUp() throws Exception {
        System.out.println("in setup");
        super.setUp();

        Intent intent = new Intent(this.getContext(), TotalCostCarService.class);
        this.getContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    public ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("in onConnected");
            CarCostBinder myBinder = (CarCostBinder) service;
            myService = myBinder.getService();
            isBound = true;

            float costOfCars = myService.calculateCarCost();
            System.out.println("cost: " + costOfCars);
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
