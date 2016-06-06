package com.jasonmckay.assignment63;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

public class EnterUpdateDetails extends AppCompatActivity {

    private Long id;
    private String engineType = null;
    private RadioGroup engineGroup ;
    private TextView changeLable;
    private Button btnNext;
    private RepositoryManufacturer repo;
    private Manufacturer vehicle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_update_details);

        engineGroup = (RadioGroup) findViewById(R.id.engineGroup);
        changeLable = (TextView) findViewById(R.id.textView14);
        btnNext = (Button) findViewById(R.id.button10);
        engineGroup.setVisibility(View.INVISIBLE);
        changeLable.setVisibility(View.INVISIBLE);
        btnNext.setVisibility(View.INVISIBLE);
    }

    public void searchEditID(View view)
    {
        final EditText txtID = (EditText) findViewById(R.id.txtEditID);

        final RadioButton rbD = (RadioButton) findViewById(R.id.rbDiesel);
        final RadioButton rbU = (RadioButton) findViewById(R.id.rbUnleaded);
        final RadioButton rbL = (RadioButton) findViewById(R.id.rbLeaded);

        if(txtID.getText() != null)
        {
            try {
                Long id = Long.parseLong(txtID.getText().toString());

                repo = new RepositoryManufacturerImpl(this);
                vehicle = repo.findVehicle(id);

                if (vehicle == null)
                {
                    Toast.makeText(this, "Vehicle does not exist.", Toast.LENGTH_LONG).show();
                    txtID.setText("");
                }
                else
                {
                    engineGroup.setVisibility(View.VISIBLE);
                    changeLable.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);

                    if(vehicle.getEngineType().equalsIgnoreCase("unleaded")) {
                        rbU.setChecked(true);
                    }

                    if(vehicle.getEngineType().equalsIgnoreCase("diesel")) {
                        rbD.setChecked(true);
                    }

                    if(vehicle.getEngineType().equalsIgnoreCase("leaded")) {
                        rbL.setChecked(true);
                    }
                }
            }catch(Exception e)
            {
                Toast.makeText(this, "Vehicle ID must be a number", Toast.LENGTH_SHORT).show();
                txtID.setText("");
            }
        }
    }

    public void editEngine(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId())
        {
            case R.id.rbDiesel:
                if(checked)
                {
                    engineType = "diesel";
                }
                break;
            case R.id.rbUnleaded:
                if(checked)
                {
                    engineType = "unleaded";
                }
                break;
            case R.id.rbLeaded:
                if(checked)
                {
                    engineType = "leaded";
                }
                break;
        }
    }

    public void updateVehicle(View view)
    {
        Manufacturer updateVehicle = new Manufacturer.Builder()
                .copy(vehicle)
                .modifyVehicle(engineType)
                .build();

        Intent intent = new Intent(this, ConfirmUpdate.class);
        intent.putExtra("updateVehicle", updateVehicle);
        startActivity(intent);
    }
}
