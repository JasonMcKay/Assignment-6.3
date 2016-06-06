package com.jasonmckay.assignment63.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;
import com.jasonmckay.assignment63.services.VehicleSearchService.*;

import junit.framework.Assert;

/**
 * Created by JasonMckay on 09-May-16.
 */
public class TestVehicleSearchService extends AndroidTestCase
{
    VehicleSearchService myService;
    boolean isBound = false;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Intent intent = new Intent(this.getContext(), VehicleSearchService.class);
        this.getContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    public ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalSearchBinder binder = (LocalSearchBinder) service;
            myService = binder.getService();
            isBound = true;
            System.out.println("connected");
            boolean vehicleExists = myService.vehicleExists(132);
            Assert.assertEquals(false, vehicleExists);
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
            System.out.println("Service finished: " + isBound);
        }
    }
}
