package com.jasonmckay.assignment63;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

public class EnterRemoveDetails extends AppCompatActivity {

    private Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_remove_details);
    }

    public void searchID(View view)
    {
        final TextView txtID = (TextView) findViewById(R.id.txtVehicleId);

        if(txtID.getText() != null)
        {
            try {
                Long id = Long.parseLong(txtID.getText().toString());

                RepositoryManufacturer repo = new RepositoryManufacturerImpl(this);
                Manufacturer vehicle = repo.findVehicle(id);

                if (vehicle == null)
                {
                    Toast.makeText(this, "Vehicle does not exist.", Toast.LENGTH_LONG).show();
                    txtID.setText("");
                }
                else
                {
                    Intent intent = new Intent(this, ConfirmRemove.class);
                    intent.putExtra("removeVehicle", vehicle);
                    startActivity(intent);
                }
            }catch(Exception e)
            {
                Toast.makeText(this, "Vehicle ID must be a number", Toast.LENGTH_SHORT).show();
                txtID.setText("");
            }
        }
    }
}
