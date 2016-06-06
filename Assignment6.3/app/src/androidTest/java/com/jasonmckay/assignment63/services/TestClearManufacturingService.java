package com.jasonmckay.assignment63.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;
import com.jasonmckay.assignment63.services.ClearManufacturingService.*;

import junit.framework.Assert;

/**
 * Created by JasonMckay on 09-May-16.
 */
public class TestClearManufacturingService extends AndroidTestCase
{
    ClearManufacturingService myService;
    boolean isBound = false;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Intent intent = new Intent(this.getContext(), ClearManufacturingService.class);
        this.getContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
    }

    public ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            MyLocalBinder binder = (MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
            System.out.println("connected");
            int removedVehicles = myService.clearVehicles();
            Assert.assertTrue(removedVehicles > 0);
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
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
