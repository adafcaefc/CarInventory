package Controller.Database.Serializer;

import Model.RecordModel.BrandRecordModel;
import Model.RecordModel.DataRecordModel;
import Model.RecordList.BrandList;

import java.util.HashMap;

public class BrandSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(DataRecordModel component)
    {
        var brand = (BrandRecordModel) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = BrandList.get().getIndexForComponent(brand);

        map.put("brandId", String.valueOf(objIndex));
        map.put("brandName", brand.getBrandName());

        return map;
    }
}
