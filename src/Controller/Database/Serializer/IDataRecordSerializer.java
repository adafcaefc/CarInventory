package Controller.Database.Serializer;

import Model.Model.IRecordData;

import java.util.HashMap;

public interface IDataRecordSerializer
{
    HashMap<String, String> serialize(IRecordData model);
}