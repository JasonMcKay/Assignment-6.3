package com.jasonmckay.assignment63;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;
import java.util.ArrayList;
import java.util.Set;

public class ListVehicles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vehicles);
        final TextView lblList = (TextView) findViewById(R.id.list);
        RepositoryManufacturer repo = new RepositoryManufacturerImpl(this);
        Set<Manufacturer> list = repo.findAllVehicles();

        ArrayList<Manufacturer> list2 = new ArrayList<>(list);
        String[] vehicleList = new String[list2.size()];

        for (int x = 0; x < list2.size(); x++)
        {
            lblList.append("ID: " + list2.get(x).getVehicleID() + "\nEngine: " + list2.get(x).getEngineType() + "\nDoors: " + list2.get(x).getDoorType() + "\n\n");
        }
    }

    public void home(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}


