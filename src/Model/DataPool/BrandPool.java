package Model.DataPool;

public class BrandPool extends DataRecordPool
{
    private static final BrandPool intsance = new BrandPool();

    private BrandPool()
    {
        super(null);
    }

    public static BrandPool get()
    {
        return intsance;
    }
}
