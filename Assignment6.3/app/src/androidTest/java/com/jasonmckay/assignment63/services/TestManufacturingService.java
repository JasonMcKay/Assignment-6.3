package com.jasonmckay.assignment63.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;
import com.jasonmckay.assignment63.services.ManufacturingService.*;

import junit.framework.Assert;

/**
 * Created by JasonMckay on 09-May-16.
 */


public class TestManufacturingService extends AndroidTestCase
{
    ManufacturingService myCountService;
    boolean isBound = false;


    @Override
    public void setUp() throws Exception {
        super.setUp();

        Intent intent = new Intent(this.getContext(), ManufacturingService.class);
        this.getContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection myConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            MyLocalBinder binder = (MyLocalBinder) service;
            myCountService = binder.getService();
            isBound = true;
            System.out.println("Service started: " + isBound);
            int vehicleAmount = myCountService.getVehicleAmount();
            Assert.assertEquals(0, vehicleAmount);
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            myCountService.unbindService(myConnection);
            isBound = false;
            System.out.println("Service finished: " + isBound);
        }
    };

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        if(isBound)
        {
            this.getContext().unbindService(myConnection);
            isBound = false;
            System.out.println("Service finished: " + isBound);
        }
    }
}

























