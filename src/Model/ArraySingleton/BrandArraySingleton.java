package Model.ArraySingleton;

public class BrandArraySingleton extends IRecordArraySingleton
{
    private static final BrandArraySingleton instance = new BrandArraySingleton();

    private BrandArraySingleton()
    {
        super(ModelArraySingleton.get());
    }

    public static BrandArraySingleton get()
    {
        return instance;
    }
}
