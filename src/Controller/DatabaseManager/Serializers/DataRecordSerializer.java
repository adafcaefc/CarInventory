package Controller.DatabaseManager.Serializers;

import Model.DataModel.DataRecord;

import java.util.HashMap;

public interface DataRecordSerializer
{
    HashMap<String, String> serialize(DataRecord model);
}