package Controller.Database.Deserializer;

import Model.RecordModel.IDataRecordModel;
import Model.RecordModel.SoldVehicleModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class SoldVehicleDeserializer implements DataRecordDeserializer
{
    public IDataRecordModel deserialize(ResultSet rs) throws SQLException
    {
        var soldVehicle = new SoldVehicleModel();

        soldVehicle.setBrandName(rs.getString("brandName"));

        soldVehicle.setVIN(rs.getString("VIN"));
        soldVehicle.setLicensePlate(rs.getString("licensePlate"));
        soldVehicle.setColor(rs.getString("color"));
        soldVehicle.setMileage(rs.getDouble("mileage"));

        soldVehicle.setModelName(rs.getString("modelName"));
        soldVehicle.setModelYear(rs.getInt("modelYear"));
        soldVehicle.setHasSunroof(rs.getInt("hasSunroof") == 1);
        soldVehicle.setDoorCount(rs.getInt("doorCount"));
        soldVehicle.setSeatCount(rs.getInt("seatCount"));
        soldVehicle.setFuelCapacity(rs.getDouble("fuelCapacity"));

        soldVehicle.setPaidAmount(rs.getDouble("paidAmount"));

        soldVehicle.setDateOfSale(new GregorianCalendar(
                rs.getInt("dateOfSaleYear"),
                rs.getInt("dateOfSaleMonth"),
                rs.getInt("dateOfSaleDate")));

        return soldVehicle;
    }
}