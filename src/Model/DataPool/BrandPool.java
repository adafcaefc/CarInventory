package Model.DataPool;

public class BrandPool extends DataRecordPool
{
    private static final BrandPool instance = new BrandPool();

    private BrandPool()
    {
        super(null);
    }

    public static BrandPool get()
    {
        return instance;
    }
}
