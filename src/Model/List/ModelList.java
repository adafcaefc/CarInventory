package Model.List;

public class ModelList extends IRecordList
{
    private static final ModelList instance = new ModelList();

    private ModelList()
    {
        super(VehicleList.get());
    }

    public static ModelList get()
    {
        return instance;
    }
}
