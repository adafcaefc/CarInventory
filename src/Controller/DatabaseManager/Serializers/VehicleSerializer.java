package Controller.DatabaseManager.Serializers;

import Model.DataModel.DataRecord;
import Model.DataModel.Vehicle;
import Model.DataPool.ModelPool;
import Model.DataPool.VehiclePool;

import java.util.HashMap;

public class VehicleSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(DataRecord component)
    {
        var vehicle = (Vehicle) component;
        HashMap<String, String> map = new HashMap<>();
        int objIndex = VehiclePool.get().getIndexForComponent(vehicle);
        int parentObjIndex = ModelPool.get().getIndexForComponent(vehicle.getModel());
        map.put("vehicleId", String.valueOf(objIndex));
        map.put("parentModelId", String.valueOf(parentObjIndex));
        map.put("VIN", vehicle.getVIN());
        map.put("licensePlate", vehicle.getLicensePlate());
        map.put("color", vehicle.getColor());
        map.put("mileage", String.valueOf(vehicle.getMileage()));
        return map;
    }
}
