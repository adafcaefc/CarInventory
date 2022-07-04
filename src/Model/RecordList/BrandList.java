package Model.RecordList;

public class BrandList extends DataRecordList
{
    private static final BrandList instance = new BrandList();

    private BrandList()
    {
        super(ModelList.get());
    }

    public static BrandList get()
    {
        return instance;
    }
}
