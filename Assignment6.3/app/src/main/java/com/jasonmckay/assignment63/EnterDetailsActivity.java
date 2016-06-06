package com.jasonmckay.assignment63;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;

public class EnterDetailsActivity extends AppCompatActivity
{
    private String engineType = null;
    private String doorType = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_details);
    }



    public void goNext(View v)
    {
        if(engineType == null || doorType == null)
        {
            Toast.makeText(this, "An engine and a door type is required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Manufacturer vehicle = new Manufacturer.Builder().vehicle(engineType, doorType).build();
            Intent intent = new Intent(this, ConfirmDetails.class);
            intent.putExtra("vehicle", vehicle);
            startActivity(intent);
        }
    }

    public void goBack(View v)
    {

    }

    public void selectEngineType(View v)
    {
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId())
        {
            case R.id.diesel:
                if(checked)
                {
                    engineType = "diesel";
                    System.out.println("Diesel");
                }
                break;
            case R.id.unleaded:
                if(checked)
                {
                    engineType = "unleaded";
                }
                break;
            case R.id.leaded:
                if(checked)
                {
                    engineType = "leaded";
                }
                break;
        }
    }

    public void selectDoorType(View v)
    {
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId())
        {
            case R.id.fourDoor:
                if(checked)
                {
                    doorType = "four door";
                    System.out.println("fordoor");
                }
                break;
            case R.id.twoDoor:
                if(checked)
                {
                    doorType = "two door";
                }
                break;
            case R.id.noDoor:
                if(checked)
                {
                    doorType = "no door";
                }
                break;
        }
    }
}
