package Controller.Database.Serializer;

import Model.Record.BrandRecord;
import Model.Record.DataRecord;
import Model.Pool.BrandPool;

import java.util.HashMap;

public class BrandSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(DataRecord component)
    {
        var brand = (BrandRecord) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = BrandPool.get().getIndexForComponent(brand);

        map.put("brandId", String.valueOf(objIndex));
        map.put("brandName", brand.getBrandName());

        return map;
    }
}
