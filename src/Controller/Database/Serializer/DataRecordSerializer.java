package Controller.Database.Serializer;

import Model.RecordModel.DataRecordModel;

import java.util.HashMap;

public interface DataRecordSerializer
{
    HashMap<String, String> serialize(DataRecordModel model);
}