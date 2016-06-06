package com.jasonmckay.assignment63.repository;

import java.util.Set;

/**
 * Created by JasonMckay on 19-Apr-16.
 */
public interface Repository<ManufacturerEntity, ManufacturerKey>
{
    ManufacturerEntity saveVehicle(ManufacturerEntity vehicle);
    ManufacturerEntity modifyVehicle(ManufacturerEntity vehicle);
    ManufacturerEntity deleteVehicle(ManufacturerEntity vehicle);
    int deleteVehicles();
    ManufacturerEntity findVehicle(ManufacturerKey itemNumber);
    Set<ManufacturerEntity> findAllVehicles();
}
