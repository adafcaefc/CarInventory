package Controller.Database;

import Controller.Database.Deserializer.*;
import Controller.Database.Serializer.*;
import Model.Exception.InvalidData;
import Model.Record.List.*;

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
            IList pool,
            ISerializer serializer)
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
            saveStatement("users", UserList.get(), new UserSerializer());
            saveStatement("brands", BrandList.get(), new BrandSerializer());
            saveStatement("models", ModelList.get(), new ModelSerializer());
            saveStatement("vehicles", VehicleList.get(), new VehicleSerializer());
            saveStatement("transactions", TransactionList.get(), new TransactionSerializer());
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
            IList pool,
            IDeserializer deserializer)
    throws SQLException,
            IndexOutOfBoundsException,
            InvalidData
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
            IList pool,
            IDeserializer deserializer)
    throws SQLException,
            IndexOutOfBoundsException,
            InvalidData
    {
        loadStatement(String.format("SELECT * FROM `%s` ORDER BY `%s`;", tableName, primaryKeyName), pool, deserializer);
    }

    public void loadData()
    {
        try
        {
            loadStatement("users", "userId", UserList.get(), new UserDeserializer());
            loadStatement("brands", "brandId", BrandList.get(), new BrandDeserializer());
            loadStatement("models", "modelId", ModelList.get(), new ModelDeserializer());
            loadStatement("vehicles", "vehicleId", VehicleList.get(), new VehicleDeserializer());
            loadStatement("transactions", "transactionId", TransactionList.get(), new TransactionDeserializer());
        }
        catch (InvalidData | SQLException | IndexOutOfBoundsException ex)
        {
            JOptionPane.showMessageDialog(
                    null,
                    "Unexpected error:  " + ex.getMessage(),
                    "Could not load data",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}