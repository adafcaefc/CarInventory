package Model.List;

public class VehicleList extends IRecordList
{
    private static final VehicleList instance = new VehicleList();

    private VehicleList()
    {
        super(null);
    }

    public static VehicleList get()
    {
        return instance;
    }
}
