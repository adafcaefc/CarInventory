package Controller.Database.Serializer;

import Model.RecordModel.IDataRecordModel;
import Model.RecordModel.VehicleModel;
import Model.RecordList.ModelList;
import Model.RecordList.VehicleList;

import java.util.HashMap;

public class VehicleSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IDataRecordModel component)
    {
        var vehicle = (VehicleModel) component;
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
