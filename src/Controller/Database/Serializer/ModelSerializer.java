package Controller.Database.Serializer;

import Model.ArraySingleton.BrandArraySingleton;
import Model.ArraySingleton.ModelArraySingleton;
import Model.Model.IRecordDataModel;
import Model.Model.ModelDataModel;

import java.util.HashMap;

public class ModelSerializer implements IDataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IRecordDataModel component)
    {
        var model = (ModelDataModel) component;
        HashMap<String, String> map = new HashMap<>();

        int objIndex = ModelArraySingleton.get().getIndexForComponent(model);
        int parentObjIndex = BrandArraySingleton.get().getIndexForComponent(model.getBrand());

        map.put("modelId", String.valueOf(objIndex));
        map.put("parentBrandId", String.valueOf(parentObjIndex));
        map.put("modelName", model.getModelName());
        map.put("modelYear", String.valueOf(model.getModelYear()));
        map.put("hasSunroof", String.valueOf(model.getHasSunroof() ? 1 : 0));
        map.put("doorCount", String.valueOf(model.getDoorCount()));
        map.put("seatCount", String.valueOf(model.getSeatCount()));
        map.put("fuelCapacity", String.valueOf(model.getFuelCapacity()));

        return map;
    }
}
