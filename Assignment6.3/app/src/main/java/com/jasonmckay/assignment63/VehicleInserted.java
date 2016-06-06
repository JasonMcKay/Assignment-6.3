package com.jasonmckay.assignment63;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;
import com.jasonmckay.assignment63.services.VehicleSearchService;

import junit.framework.Assert;

public class VehicleInserted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_inserted);

        final TextView lblVehicleDetails = (TextView) findViewById(R.id.lblVehicleDetails);

        Bundle bundle = getIntent().getExtras();

        if(bundle == null)
        {
            return;
        }

        long vehicleId = bundle.getLong("vehicleId");

        RepositoryManufacturer repo = new RepositoryManufacturerImpl(this);
        Manufacturer vehicle = repo.findVehicle(vehicleId);
        lblVehicleDetails.setText("ID: " + vehicle.getVehicleID() + "\nEngine: " + vehicle.getEngineType() + "\nDoors: " + vehicle.getDoorType());
    }

    public void BackToMenu(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
