package Model.Record.List;

public class ModelList extends IList
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
