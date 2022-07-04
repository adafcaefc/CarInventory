package Controller.Database.Serializer;

import Model.Data.IRecordData;
import Model.Data.VehicleData;
import Model.List.ModelList;
import Model.List.VehicleList;

import java.util.HashMap;

public class VehicleSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IRecordData component)
    {
        var vehicle = (VehicleData) component;
        HashMap<String, String> map = new HashMap<>();
        int objIndex = VehicleList.get().getIndexForComponent(vehicle);
        int parentObjIndex = ModelList.get().getIndexForComponent(vehicle.getModel());
        map.put("vehicleId", String.valueOf(objIndex));
        map.put("parentModelId", String.valueOf(parentObjIndex));
        map.put("VIN", vehicle.getVIN());
        map.put("licensePlate", vehicle.getLicensePlate());
        map.put("color", vehicle.getColor());
        map.put("mileage", String.valueOf(vehicle.getMileage()));
        return map;
    }
}
