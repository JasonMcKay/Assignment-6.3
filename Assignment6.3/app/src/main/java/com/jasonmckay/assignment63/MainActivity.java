package com.jasonmckay.assignment63;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addVehicle(View v)
    {
        Intent intent = new Intent(this, EnterDetailsActivity.class);
        startActivity(intent);
    }

    public void removeVehicle(View v)
    {
        Intent intent = new Intent(this, EnterRemoveDetails.class);
        startActivity(intent);
    }

    public void updateVehicle(View v)
    {
        Intent intent = new Intent(this, EnterUpdateDetails.class);
        startActivity(intent);
    }

    public void listVehicles (View v)
    {
        Intent intent = new Intent(this, ListVehicles.class);
        startActivity(intent);
    }


}
