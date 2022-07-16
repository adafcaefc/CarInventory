package Model.ArraySingleton;

public class ModelArraySingleton extends IRecordArraySingleton
{
    private static final ModelArraySingleton instance = new ModelArraySingleton();

    private ModelArraySingleton()
    {
        super(VehicleArraySingleton.get());
    }

    public static ModelArraySingleton get()
    {
        return instance;
    }
}
