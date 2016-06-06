package com.jasonmckay.assignment63;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;
import com.jasonmckay.assignment63.services.InsertVehicleService;

public class ConfirmDetails extends AppCompatActivity {
    private Manufacturer vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_details);

        final TextView lblEngineType = (TextView) findViewById(R.id.lblEngineType);
        final TextView lblDoorType = (TextView) findViewById(R.id.lblDoorType);
        final TextView lblVehicleType = (TextView) findViewById(R.id.lblVehicleType);

        Bundle extra = getIntent().getExtras();
        if(extra == null)
        {
            return;
        }

        vehicle = (Manufacturer) extra.get("vehicle");

        if(vehicle.getDoorType().equalsIgnoreCase("no door")) {
            lblVehicleType.setText("Confirm new bike");
        }
        else
        {
            lblVehicleType.setText("Confirm new car");
        }
        lblEngineType.setText(vehicle.getEngineType());
        lblDoorType.setText(vehicle.getDoorType());
    }

    public void ConfirmVehicle(View v)
    {
        RepositoryManufacturer repo = new RepositoryManufacturerImpl(this);
        Manufacturer insertedVehicle = repo.saveVehicle(vehicle);
        Intent intent = new Intent(this, VehicleInserted.class);
        long vehicleId = insertedVehicle.getVehicleID();
        intent.putExtra("vehicleId", vehicleId);
        startActivity(intent);
    }

    public void GoBack(View v)
    {

    }
}
