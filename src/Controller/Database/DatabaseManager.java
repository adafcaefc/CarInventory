package Controller.Database;

import Controller.Database.Deserializer.*;
import Controller.Database.Serializer.*;
import Model.Exception.DataNotBoundToPool;
import Model.Pool.*;

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
            DataRecordPool pool,
            DataRecordSerializer serializer)
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
            saveStatement("brands", BrandPool.get(), new BrandSerializer());
            saveStatement("models", ModelPool.get(), new ModelSerializer());
            saveStatement("vehicles", VehiclePool.get(), new VehicleSerializer());
            saveStatement("soldvehicles", SoldVehiclePool.get(), new SoldVehicleSerializer());
            saveStatement("users", UserPool.get(), new UserSerializer());
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
            DataRecordPool pool,
            DataRecordDeserializer deserializer)
    throws SQLException,
            IndexOutOfBoundsException,
            DataNotBoundToPool
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
            DataRecordPool pool,
            DataRecordDeserializer deserializer)
    throws  SQLException,
            IndexOutOfBoundsException,
            DataNotBoundToPool
    {
        loadStatement(String.format("SELECT * FROM `%s` ORDER BY `%s`;", tableName, primaryKeyName), pool, deserializer);
    }

    public void loadData()
    {
        try
        {
            loadStatement("brands", "brandId", BrandPool.get(), new BrandDeserializer());
            loadStatement("models", "modelId", ModelPool.get(), new ModelDeserializer());
            loadStatement("vehicles", "vehicleId", VehiclePool.get(), new VehicleDeserializer());
            loadStatement("soldVehicles", "soldVehicleId", SoldVehiclePool.get(), new SoldVehicleDeserializer());
            loadStatement("users", "userId", UserPool.get(), new UserDeserializer());
        }
        catch (DataNotBoundToPool | SQLException | IndexOutOfBoundsException ex)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "Unexpected error:  " + ex.getMessage(),
                    "Could not load data",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}