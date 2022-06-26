package Controller.Database.Serializer;

import Model.Record.DataRecord;

import java.util.HashMap;

public interface DataRecordSerializer
{
    HashMap<String, String> serialize(DataRecord model);
}