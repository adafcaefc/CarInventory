package Controller.Database.Serializer;

import Model.Record.Data.IData;
import Model.Record.Data.ModelData;
import Model.Record.List.BrandList;
import Model.Record.List.ModelList;

import java.util.HashMap;

public class ModelSerializer implements ISerializer
{
    @Override
    public HashMap<String, String> serialize(IData component)
    {
        var model = (ModelData) component;
        HashMap<String, String> map = new HashMap<>();

        int objIndex = ModelList.get().getIndexForComponent(model);
        int parentObjIndex = BrandList.get().getIndexForComponent(model.getBrand());

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
