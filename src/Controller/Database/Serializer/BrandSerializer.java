package Controller.Database.Serializer;

import Model.ArraySingleton.BrandArraySingleton;
import Model.Model.BrandDataModel;
import Model.Model.IRecordDataModel;

import java.util.HashMap;

public class BrandSerializer implements IDataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IRecordDataModel component)
    {
        var brand = (BrandDataModel) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = BrandArraySingleton.get().getIndexForComponent(brand);

        map.put("brandId", String.valueOf(objIndex));
        map.put("brandName", brand.getBrandName());

        return map;
    }
}
