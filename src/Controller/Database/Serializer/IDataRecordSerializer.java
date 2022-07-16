package Controller.Database.Serializer;

import Model.Model.IRecordDataModel;

import java.util.HashMap;

public interface IDataRecordSerializer
{
    HashMap<String, String> serialize(IRecordDataModel model);
}