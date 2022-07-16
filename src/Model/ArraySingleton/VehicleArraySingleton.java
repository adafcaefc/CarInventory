package Model.ArraySingleton;

public class VehicleArraySingleton extends IRecordArraySingleton
{
    private static final VehicleArraySingleton instance = new VehicleArraySingleton();

    private VehicleArraySingleton()
    {
        super(TransactionArraySingleton.get());
    }

    public static VehicleArraySingleton get()
    {
        return instance;
    }
}
