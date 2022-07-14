package Controller.Database;

import Controller.Database.Deserializer.*;
import Controller.Database.Serializer.*;
import Model.Exception.DataNotBoundToList;
import Model.List.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager
{
    Connection conn;

    public DatabaseManager(String url, String username, String password)
    {
        try
        {
            conn = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "Unexpected error:  " + ex.getMessage(),
                    "Could not connect to SQL server",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveStatement(
            String tableName,
            IRecordList pool,
            IDataRecordSerializer serializer)
    throws SQLException
    {
        conn.createStatement().executeUpdate(String.format("TRUNCATE TABLE `%s`;", tableName));

        for (var obj : pool)
        {
            var map = serializer.serialize(obj);

            StringBuilder vars = new StringBuilder();
            StringBuilder values = new StringBuilder();

            for (var set : map.entrySet())
            {
                if (vars.length() > 0) { vars.append(", "); }
                vars.append(String.format("`%s`", set.getKey()));
                if (values.length() > 0) { values.append(", "); }
                values.append(String.format("'%s'", set.getValue()));
            }
            conn.createStatement().executeUpdate(String.format("INSERT INTO `%s` (%s) VALUES (%s);", tableName, vars, values));
        }
    }

    public void saveData()
    {
        try
        {
            saveStatement("brands", BrandList.get(), new BrandSerializer());
            saveStatement("models", ModelList.get(), new ModelSerializer());
            saveStatement("vehicles", VehicleList.get(), new VehicleSerializer());
            saveStatement("soldvehicles", SoldVehicleList.get(), new TransactionSerializer());
            saveStatement("users", UserList.get(), new UserSerializer());
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "Unexpected error:  " + ex.getMessage(),
                    "Could not export data",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadStatement(
            String query,
            IRecordList pool,
            IDataRecordDeserializer deserializer)
    throws SQLException,
            IndexOutOfBoundsException,
            DataNotBoundToList
    {

        ResultSet rs = conn.createStatement().executeQuery(query);
        while (rs.next())
        {
            pool.registerComponent(deserializer.deserialize(rs));
        }
    }

    private void loadStatement(
            String tableName,
            String primaryKeyName,
            IRecordList pool,
            IDataRecordDeserializer deserializer)
    throws  SQLException,
            IndexOutOfBoundsException,
            DataNotBoundToList
    {
        loadStatement(String.format("SELECT * FROM `%s` ORDER BY `%s`;", tableName, primaryKeyName), pool, deserializer);
    }

    public void loadData()
    {
        try
        {
            loadStatement("brands", "brandId", BrandList.get(), new BrandDeserializer());
            loadStatement("models", "modelId", ModelList.get(), new ModelDeserializer());
            loadStatement("vehicles", "vehicleId", VehicleList.get(), new VehicleDeserializer());
            loadStatement("soldVehicles", "soldVehicleId", SoldVehicleList.get(), new TransactionDeserializer());
            loadStatement("users", "userId", UserList.get(), new UserDeserializer());
        }
        catch (DataNotBoundToList | SQLException | IndexOutOfBoundsException ex)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "Unexpected error:  " + ex.getMessage(),
                    "Could not load data",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}