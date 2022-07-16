package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.List.VehicleList;
import Model.Model.IRecordDataModel;
import Model.Model.TransactionDataModel;
import Model.Model.VehicleDataModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class TransactionDeserializer implements IDataRecordDeserializer
{
    public IRecordDataModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        VehicleDataModel parentObj = (VehicleDataModel) VehicleList.get().getComponentAt(rs.getInt("parentVehicleId"));
        var transactionData = new TransactionDataModel(parentObj);

        transactionData.setPaidAmount(rs.getInt("paidAmount"));

        transactionData.setDateOfTransaction(new GregorianCalendar(
                rs.getInt("dateOfTransactionYear"),
                rs.getInt("dateOfTransactionMonth"),
                rs.getInt("dateOfTransactionDate")));

        return transactionData;
    }
}