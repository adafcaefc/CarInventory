package Controller.Database.Serializer;

import Model.Model.DataRecord;

import java.util.HashMap;

public interface DataRecordSerializer
{
    HashMap<String, String> serialize(DataRecord model);
}