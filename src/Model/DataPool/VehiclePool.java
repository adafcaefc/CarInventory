package Model.DataPool;

public class VehiclePool extends DataRecordPool
{
    private static final VehiclePool instance = new VehiclePool();

    private VehiclePool()
    {
        super(null);
    }

    public static VehiclePool get()
    {
        return instance;
    }
}