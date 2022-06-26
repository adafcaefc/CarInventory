package Model.Pool;

public class ModelPool extends DataRecordPool
{
    private static final ModelPool instance = new ModelPool();

    private ModelPool()
    {
        super(VehiclePool.get());
    }

    public static ModelPool get()
    {
        return instance;
    }
}
