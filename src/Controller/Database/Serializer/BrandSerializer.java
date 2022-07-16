package Controller.Database.Serializer;

import Model.Record.Data.BrandData;
import Model.Record.Data.IData;
import Model.Record.List.BrandList;

import java.util.HashMap;

public class BrandSerializer implements ISerializer
{
    @Override
    public HashMap<String, String> serialize(IData component)
    {
        var brand = (BrandData) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = BrandList.get().getIndexForComponent(brand);

        map.put("brandId", String.valueOf(objIndex));
        map.put("brandName", brand.getBrandName());

        return map;
    }
}
