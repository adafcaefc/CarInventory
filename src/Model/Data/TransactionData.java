package Model.Data;

import Model.Exception.DataNotBoundToList;
import Model.List.TransactionList;
import Model.List.VehicleList;

import java.util.GregorianCalendar;

public class TransactionData extends IRecordData implements Cloneable
{
    private GregorianCalendar dateOfTransaction;
    private int paidAmount;

    public TransactionData(VehicleData sourceVehicleRecord) throws DataNotBoundToList
    {
        if (sourceVehicleRecord == null) { return; }
        if (!VehicleList.get().componentIsRegisteredAtPool(sourceVehicleRecord))
        {
            throw new DataNotBoundToList("The 'transaction' object passed to bindToVehicle does not exist inside TransactionPool");
        }
        sourceVehicleRecord.addChild(this);
    }

    public GregorianCalendar getDateOfTransaction()
    {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(GregorianCalendar dateOfTransaction) { this.dateOfTransaction = dateOfTransaction; }

    public int getPaidAmount()
    {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount)
    {
        this.paidAmount = paidAmount;
    }

    public VehicleData getVehicle() { return (VehicleData) getParent(); }
}
