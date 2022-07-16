package Controller.Database.Serializer;

import Model.ArraySingleton.ModelArraySingleton;
import Model.ArraySingleton.UserArraySingleton;
import Model.ArraySingleton.VehicleArraySingleton;
import Model.Model.IRecordDataModel;
import Model.Model.VehicleDataModel;

import java.util.HashMap;

public class VehicleSerializer implements IDataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IRecordDataModel component)
    {
        var vehicle = (VehicleDataModel) component;
        HashMap<String, String> map = new HashMap<>();
        int objIndex = VehicleArraySingleton.get().getIndexForComponent(vehicle);
        int parentObjIndex = ModelArraySingleton.get().getIndexForComponent(vehicle.getModel());

        int buyerObjIndex = -1;
        int sellerObjIndex = -1;

        if (vehicle.getBuyer() != null) { buyerObjIndex = UserArraySingleton.get().getIndexForComponent(vehicle.getBuyer()); }
        if (vehicle.getSeller() != null) { sellerObjIndex = UserArraySingleton.get().getIndexForComponent(vehicle.getSeller()); }

        map.put("vehicleId", String.valueOf(objIndex));
        map.put("parentModelId", String.valueOf(parentObjIndex));
        map.put("VIN", vehicle.getVIN());
        map.put("licensePlate", vehicle.getLicensePlate());
        map.put("color", vehicle.getColor());
        map.put("mileage", String.valueOf(vehicle.getMileage()));

        map.put("discount", String.valueOf(vehicle.getDiscount()));
        map.put("price", String.valueOf(vehicle.getPrice()));
        map.put("buyerUserId", String.valueOf(buyerObjIndex));
        map.put("sellerUserId", String.valueOf(sellerObjIndex));

        return map;
    }
}
