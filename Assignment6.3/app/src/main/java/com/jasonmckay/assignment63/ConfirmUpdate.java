package com.jasonmckay.assignment63;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

public class ConfirmUpdate extends AppCompatActivity {

    private Manufacturer vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_update);

        final TextView lblEngine = (TextView) findViewById(R.id.lblUpdateEngine);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }

        vehicle = (Manufacturer) bundle.get("updateVehicle");
        lblEngine.setText(vehicle.getEngineType());
    }

    public void confirmUpdate(View view)
    {
        RepositoryManufacturer repo = new RepositoryManufacturerImpl(this);
        Manufacturer updated = repo.modifyVehicle(vehicle);
        Intent intent = new Intent(this, VehicleUpdate.class);
        intent.putExtra("updatedVeh", updated);
        startActivity(intent);
    }
}
