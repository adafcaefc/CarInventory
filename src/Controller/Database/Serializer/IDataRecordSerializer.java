package Controller.Database.Serializer;

import Model.Data.IRecordDataModel;

import java.util.HashMap;

public interface IDataRecordSerializer
{
    HashMap<String, String> serialize(IRecordDataModel model);
}