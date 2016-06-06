package com.jasonmckay.assignment63.repository.repositoryManufacturer.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jasonmckay.assignment63.config.database.DBContract;
import com.jasonmckay.assignment63.factories.manufacturer.Manufacturer;
import com.jasonmckay.assignment63.repository.repositoryManufacturer.RepositoryManufacturer;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public class RepositoryManufacturerImpl extends SQLiteOpenHelper implements RepositoryManufacturer
{
    private SQLiteDatabase database;

    public String CREATE_QUERY = "CREATE TABLE " + DBContract.TABLE_NAME + "(" +
            DBContract.VEHICLE_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, " +
            DBContract.ENGINE_TYPE + " TEXT NOT NULL, " +
            DBContract.DOOR_TYPE + " TEXT NOT NULL );";

    public RepositoryManufacturerImpl(Context context) {
        super(context, DBContract.DATABASE_NAME, null, DBContract.DATABASE_VERSION);

    }

    public void openConnectionWritable() throws SQLException
    {
        database = this.getWritableDatabase();
    }

    public void closeConnection()
    {
        this.close();
    }

    @Override
    public Manufacturer saveVehicle(Manufacturer vehicle) {
        openConnectionWritable();
        ContentValues values = new ContentValues();
        values.put(DBContract.ENGINE_TYPE, vehicle.getEngineType());
        values.put(DBContract.DOOR_TYPE, vehicle.getDoorType());
        long id = database.insertOrThrow(DBContract.TABLE_NAME, null, values);
        Log.d("Database Operations", "Row Inserted");
        Manufacturer insertData = new Manufacturer.Builder()
                .copy(vehicle)
                .vehicleID(new Long(id))
                .build();
        return insertData;
    }

    @Override
    public Manufacturer modifyVehicle(Manufacturer vehicle) {
        openConnectionWritable();
        ContentValues values = new ContentValues();
        //values.put(DBContract.VEHICLE_ID, vehicle.getVehicleID());
        values.put(DBContract.ENGINE_TYPE, vehicle.getEngineType());
        values.put(DBContract.DOOR_TYPE, vehicle.getDoorType());
        database.update(DBContract.TABLE_NAME,
                values,
                DBContract.VEHICLE_ID + " =? ",
                new String[] {String.valueOf(vehicle.getVehicleID())});
        Log.d("Database Operations", "Row Updated");
        return vehicle;
    }

    @Override
    public Manufacturer deleteVehicle(Manufacturer vehicle) {
        openConnectionWritable();
        database.delete(DBContract.TABLE_NAME,
                DBContract.VEHICLE_ID + " =? ",
                new String[]{String.valueOf(vehicle.getVehicleID())});
        Log.d("Database Operations", "Row Deleted");
        return vehicle;
    }

    @Override
    public int deleteVehicles() {
        openConnectionWritable();
        int vehiclesDeleted = database.delete(DBContract.TABLE_NAME, null, null);
        closeConnection();
        Log.d("Database Operations", "All Rows Deleted");
        return vehiclesDeleted;
    }

    @Override
    public Manufacturer findVehicle(Long itemNumber) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.query(DBContract.TABLE_NAME,
                new String[]{
                        DBContract.VEHICLE_ID,
                        DBContract.ENGINE_TYPE,
                        DBContract.DOOR_TYPE},
                DBContract.VEHICLE_ID + " =? ",
                new String[]{String.valueOf(itemNumber)},
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst())
        {
            final Manufacturer manufacturer;
            if(cursor.getString(cursor.getColumnIndex(DBContract.ENGINE_TYPE)).equalsIgnoreCase("car"))
            {
                manufacturer = new Manufacturer.Builder()
                        .vehicleID(cursor.getLong(cursor.getColumnIndex(DBContract.VEHICLE_ID)))
                        .vehicle(cursor.getString(cursor.getColumnIndex(DBContract.ENGINE_TYPE)), cursor.getString(cursor.getColumnIndex(DBContract.DOOR_TYPE)))
                        .build();
                return manufacturer;
            }
            else
            {
                manufacturer = new Manufacturer.Builder()
                        .vehicleID(cursor.getLong(cursor.getColumnIndex(DBContract.VEHICLE_ID)))
                        .vehicle(cursor.getString(cursor.getColumnIndex(DBContract.ENGINE_TYPE)), cursor.getString(cursor.getColumnIndex(DBContract.DOOR_TYPE)))
                        .build();
                return manufacturer;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public Set<Manufacturer> findAllVehicles() {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Set<Manufacturer> manufacturerList = new HashSet<>();
        openConnectionWritable();
        Cursor cursor = readableDatabase.query(DBContract.TABLE_NAME, null, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do{
                final Manufacturer manufacturer = new Manufacturer.Builder()
                        .vehicleID(cursor.getLong(cursor.getColumnIndex(DBContract.VEHICLE_ID)))
                        .vehicle(cursor.getString(cursor.getColumnIndex(DBContract.ENGINE_TYPE)), cursor.getString(cursor.getColumnIndex(DBContract.DOOR_TYPE)))
                        .build();
                manufacturerList.add(manufacturer);
            }while(cursor.moveToNext());
        }
        System.out.println("Size of SET : " + manufacturerList.size());
        return manufacturerList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database Operations", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DBContract.TABLE_NAME);
        Log.d("Database Operations", "Table Dropped");
    }

}
