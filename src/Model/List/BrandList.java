package Model.List;

public class BrandList extends IRecordList
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
