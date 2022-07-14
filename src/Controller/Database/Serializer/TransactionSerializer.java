package Controller.Database.Serializer;

import Model.Data.IRecordData;
import Model.Data.TransactionData;
import Model.List.TransactionList;
import Model.List.VehicleList;

import java.util.Calendar;
import java.util.HashMap;

public class TransactionSerializer implements IDataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IRecordData component)
    {
        var transactionData = (TransactionData) component;
        HashMap<String, String> map = new HashMap<>();
        int objIndex = TransactionList.get().getIndexForComponent(transactionData);
        int parentObjIndex = VehicleList.get().getIndexForComponent(transactionData.getParent());

        map.put("transactionId", String.valueOf(objIndex));
        map.put("parentVehicleId", String.valueOf(parentObjIndex));
        map.put("paidAmount", String.valueOf(transactionData.getPaidAmount()));

        map.put("dateOfTransactionDate", String.valueOf(transactionData.getDateOfTransaction().get(Calendar.DAY_OF_MONTH)));
        map.put("dateOfTransactionMonth", String.valueOf(transactionData.getDateOfTransaction().get(Calendar.MONTH)));
        map.put("dateOfTransactionYear", String.valueOf(transactionData.getDateOfTransaction().get(Calendar.YEAR)));

        return map;
    }
}
