package com.jasonmckay.assignment63;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

public class ConfirmRemove extends AppCompatActivity {

    private Manufacturer vehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_remove);

        final TextView lblEngine = (TextView) findViewById(R.id.lblEngine);
        final TextView lblDoors = (TextView) findViewById(R.id.lblDoors);

        Bundle extra = getIntent().getExtras();
        if(extra == null)
        {
            return;
        }
        vehicle = (Manufacturer) extra.get("removeVehicle");

        lblEngine.setText(vehicle.getEngineType());
        lblDoors.setText(vehicle.getDoorType());
    }

    public void confirmRemove(View view) {
        RepositoryManufacturer repo = new RepositoryManufacturerImpl(this);
        Manufacturer deletedVehicle = repo.deleteVehicle(vehicle);
        Intent intent = new Intent(this, VehicleRemoved.class);
        intent.putExtra("deletedVehicle", deletedVehicle);
        startActivity(intent);
    }
}
