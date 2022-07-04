package Controller.Database.Serializer;

import Model.RecordModel.DataRecordModel;
import Model.RecordModel.ModelRecordModel;
import Model.RecordList.BrandList;
import Model.RecordList.ModelList;

import java.util.HashMap;

public class ModelSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(DataRecordModel component)
    {
        var model = (ModelRecordModel) component;
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
