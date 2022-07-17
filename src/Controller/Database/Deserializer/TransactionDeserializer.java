package Controller.Database.Deserializer;

import Model.Exception.InvalidData;
import Model.Record.Data.IData;
import Model.Record.Data.TransactionData;
import Model.Record.Data.VehicleData;
import Model.Record.List.VehicleList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class TransactionDeserializer implements IDeserializer
{
    public IData deserialize(ResultSet rs) throws SQLException, InvalidData
    {
        VehicleData parentObj = (VehicleData) VehicleList.get().getComponentAt(rs.getInt("parentVehicleId"));
        var transactionData = new TransactionData(parentObj);

        transactionData.setPaidAmount(rs.getInt("paidAmount"));
        transactionData.getDateOfTransaction().setTime(rs.getDate("dateOfTransaction"));

        return transactionData;
    }
}