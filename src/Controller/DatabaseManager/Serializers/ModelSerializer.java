package Controller.DatabaseManager.Serializers;

import Model.DataModel.DataRecord;
import Model.DataModel.Model;
import Model.DataPool.BrandPool;
import Model.DataPool.ModelPool;

import java.util.HashMap;

public class ModelSerializer implements DataRecordSerializer {
    @Override
    public HashMap<String, String> serialize(DataRecord component) {
        var model = (Model) component;
        HashMap<String, String> map = new HashMap<>();

        int objIndex = ModelPool.get().getIndexForComponent(model);
        int parentObjIndex = BrandPool.get().getIndexForComponent(model.getBrand());

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
