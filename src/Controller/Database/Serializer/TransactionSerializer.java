package Controller.Database.Serializer;

import Model.Record.Data.IData;
import Model.Record.Data.TransactionData;
import Model.Record.List.TransactionList;
import Model.Record.List.VehicleList;

import java.text.SimpleDateFormat;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class TransactionSerializer implements ISerializer
{
    @Override
    public HashMap<String, String> serialize(IData component)
    {
        var transactionData = (TransactionData) component;
        HashMap<String, String> map = new HashMap<>();
        int objIndex = TransactionList.get().getIndexForComponent(transactionData);
        int parentObjIndex = VehicleList.get().getIndexForComponent(transactionData.getParent());

        map.put("transactionId", String.valueOf(objIndex));
        map.put("parentVehicleId", String.valueOf(parentObjIndex));
        map.put("paidAmount", String.valueOf(transactionData.getPaidAmount()));

        map.put("dateOfTransaction", new SimpleDateFormat("yyyy-MM-dd").format(transactionData.getDateOfTransaction().getTime()));

        return map;
    }
}
