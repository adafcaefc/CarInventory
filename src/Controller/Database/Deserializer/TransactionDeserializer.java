package Controller.Database.Deserializer;

import Model.Model.*;
import Model.Exception.DataNotBoundToList;
import Model.List.VehicleList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class TransactionDeserializer implements IDataRecordDeserializer
{
    public IRecordData deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        VehicleData parentObj = (VehicleData) VehicleList.get().getComponentAt(rs.getInt("parentVehicleId"));
        var transactionData = new TransactionData(parentObj);

        transactionData.setPaidAmount(rs.getInt("paidAmount"));

        transactionData.setDateOfTransaction(new GregorianCalendar(
                rs.getInt("dateOfTransactionYear"),
                rs.getInt("dateOfTransactionMonth"),
                rs.getInt("dateOfTransactionDate")));

        return transactionData;
    }
}