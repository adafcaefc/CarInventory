package Model.Model;

import Model.Exception.DataNotBoundToList;
import Model.List.VehicleList;

import java.util.GregorianCalendar;

public class TransactionDataModel extends IRecordDataModel implements Cloneable
{
    private GregorianCalendar dateOfTransaction;
    private int paidAmount;

    public TransactionDataModel(VehicleDataModel sourceVehicleRecord) throws DataNotBoundToList
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

    public VehicleDataModel getVehicle() { return (VehicleDataModel) getParent(); }
}
