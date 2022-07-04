package Controller.Database.Serializer;

import Model.RecordModel.IDataRecordModel;

import java.util.HashMap;

public interface DataRecordSerializer
{
    HashMap<String, String> serialize(IDataRecordModel model);
}