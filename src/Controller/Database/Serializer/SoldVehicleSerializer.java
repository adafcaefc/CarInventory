package Controller.Database.Serializer;

import Model.RecordModel.DataRecordModel;
import Model.RecordModel.SoldVehicleRecordModel;
import Model.RecordList.SoldVehicleList;

import java.util.Calendar;
import java.util.HashMap;

public class SoldVehicleSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(DataRecordModel component)
    {
        var soldVehicle = (SoldVehicleRecordModel) component;
        HashMap<String, String> map = new HashMap<>();
        int objIndex = SoldVehicleList.get().getIndexForComponent(soldVehicle);

        map.put("soldVehicleId", String.valueOf(objIndex));

        map.put("brandName", soldVehicle.getBrandName());

        map.put("modelName", soldVehicle.getModelName());
        map.put("modelYear", String.valueOf(soldVehicle.getModelYear()));
        map.put("hasSunroof", String.valueOf(soldVehicle.getHasSunroof() ? 1 : 0));
        map.put("doorCount", String.valueOf(soldVehicle.getDoorCount()));
        map.put("seatCount", String.valueOf(soldVehicle.getSeatCount()));
        map.put("fuelCapacity", String.valueOf(soldVehicle.getFuelCapacity()));

        map.put("VIN", soldVehicle.getVIN());
        map.put("licensePlate", soldVehicle.getLicensePlate());
        map.put("color", soldVehicle.getColor());
        map.put("mileage", String.valueOf(soldVehicle.getMileage()));

        map.put("paidAmount", String.valueOf(soldVehicle.getPaidAmount()));

        map.put("dateOfSaleDate", String.valueOf(soldVehicle.getDateOfSale().get(Calendar.DAY_OF_MONTH)));
        map.put("dateOfSaleMonth", String.valueOf(soldVehicle.getDateOfSale().get(Calendar.MONTH)));
        map.put("dateOfSaleYear", String.valueOf(soldVehicle.getDateOfSale().get(Calendar.YEAR)));

        return map;
    }
}
