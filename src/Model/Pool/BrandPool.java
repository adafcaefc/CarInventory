package Model.Pool;

public class BrandPool extends DataRecordPool
{
    private static final BrandPool instance = new BrandPool();

    private BrandPool()
    {
        super(ModelPool.get());
    }

    public static BrandPool get()
    {
        return instance;
    }
}
