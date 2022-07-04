package Controller.Database.Serializer;

import Model.Data.IRecordData;

import java.util.HashMap;

public interface DataRecordSerializer
{
    HashMap<String, String> serialize(IRecordData model);
}