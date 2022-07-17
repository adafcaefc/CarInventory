package Controller.Database.Serializer;

import Model.Record.Data.IData;

import java.util.HashMap;

public interface ISerializer
{
    HashMap<String, String> serialize(IData model);
}