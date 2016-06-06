package com.jasonmckay.assignment63.services;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.test.AndroidTestCase;
import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.services.ListVehicleService.*;
import java.util.Set;

/**
 * Created by JasonMckay on 11-May-16.
 */

public class TestListVehicleService extends AndroidTestCase
{
    ListVehicleService myService;
    boolean isBound = false;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Intent intent = new Intent(this.getContext(), ListVehicleService.class);
        this.getContext().bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(listReceiver, new IntentFilter("vehicleList"));

    }

    public ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocalListBinder binder = (LocalListBinder) service;
            myService = binder.getService();
            isBound = true;

            myService.sendList();
            System.out.println("connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            System.out.println("disconnected1");
        }
    };

    private BroadcastReceiver listReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Set<Manufacturer> list = (Set<Manufacturer>) intent.getSerializableExtra("list");
            System.out.println("test size: " + list.size());
        }
    };

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

        if(isBound)
        {
            this.getContext().unbindService(myConnection);
            isBound = false;
            System.out.println("disconnected2");
        }
        LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(listReceiver);
    }
}
