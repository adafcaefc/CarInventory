package Controller.DatabaseManager.Serializers;

import Model.DataModel.Brand;
import Model.DataModel.DataRecord;
import Model.DataPool.BrandPool;

import java.util.HashMap;

public class BrandSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(DataRecord component)
    {
        var brand = (Brand) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = BrandPool.get().getIndexForComponent(brand);

        map.put("brandId", String.valueOf(objIndex));
        map.put("brandName", brand.getBrandName());

        return map;
    }
}
