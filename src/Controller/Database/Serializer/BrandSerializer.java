package Controller.Database.Serializer;

import Model.Data.BrandData;
import Model.Data.IRecordData;
import Model.List.BrandList;

import java.util.HashMap;

public class BrandSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IRecordData component)
    {
        var brand = (BrandData) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = BrandList.get().getIndexForComponent(brand);

        map.put("brandId", String.valueOf(objIndex));
        map.put("brandName", brand.getBrandName());

        return map;
    }
}
