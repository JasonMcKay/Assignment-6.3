package com.jasonmckay.assignment63.repository;

import android.test.AndroidTestCase;

import com.jasonmckay.assignment63.config.database.DBContract;
import com.jasonmckay.assignment63.config.util.App;
import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.impl.RepositoryManufacturerImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public class TestDatabase extends AndroidTestCase
{
    RepositoryManufacturer repository;

    public void testDatabase() throws Exception
    {
        //Create and initialize database
        repository = new RepositoryManufacturerImpl(this.getContext());

        //Create and Insert an entity
        Manufacturer insertEntity = new Manufacturer.Builder()
                .vehicle("leaded", "four door")
                .build();
        Manufacturer insertData = repository.saveVehicle(insertEntity);
        long id = insertData.getVehicleID();
        System.out.println("ID: " + id);
        insertData.print();
        Assert.assertNotNull(insertData);


        //***************************************************************************
        //***************************************************************************
        Manufacturer insertEntity2 = new Manufacturer.Builder()
                .vehicle("diesel", "two door")
                .build();
        Manufacturer insertData2 = repository.saveVehicle(insertEntity2);
        long id2 = insertData2.getVehicleID();
        System.out.println("ID: " + id2);
        insertData2.print();
        Assert.assertNotNull(insertData2);
        //***************************************************************************
        //***************************************************************************

        //Find an entity and save its result
        Manufacturer foundEntity = repository.findVehicle(id);
        Assert.assertNotNull(foundEntity);

        //Select all data from table
        Set<Manufacturer> manufacturerList = repository.findAllVehicles();
        Assert.assertTrue(manufacturerList.size() > 0);

        //Update an entity
        Manufacturer updateEntity = new Manufacturer.Builder()
                .copy(foundEntity)
                .modifyVehicle("unleaded")
                .build();
        repository.modifyVehicle(updateEntity);

        Manufacturer entity = repository.findVehicle(id);
        entity.print();
        Assert.assertTrue("unleaded".equalsIgnoreCase(entity.getEngineType()) );

        //Delete an entity
        repository.deleteVehicle(updateEntity);
        Manufacturer deleteEntity = repository.findVehicle(id);
        Assert.assertNull(deleteEntity);

        //Delete table
        int rowsDeleted = repository.deleteVehicles();
        Assert.assertTrue(rowsDeleted > 0);
    }

    @Override
    public void tearDown() throws Exception {
        // Delete database after every execution to avoid errors while testing
        this.getContext().deleteDatabase(DBContract.DATABASE_NAME);
        super.tearDown();
    }
}
