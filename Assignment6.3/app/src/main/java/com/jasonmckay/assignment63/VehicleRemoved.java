package com.jasonmckay.assignment63;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;

public class VehicleRemoved extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_removed);

        final TextView lblDeletedVehicle = (TextView)findViewById(R.id.lblDeletedVehicle);

        Bundle bundle = getIntent().getExtras();

        if(bundle == null)
        {
            return;
        }

        Manufacturer vehicleId = (Manufacturer) bundle.get("deletedVehicle");

        lblDeletedVehicle.setText("ID: " + vehicleId.getVehicleID() + "\nEngine: " + vehicleId.getEngineType() + "\nDoors: " + vehicleId.getDoorType());
    }

    public void home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
