package Model.RecordList;

public class ModelList extends IDataRecordList
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
