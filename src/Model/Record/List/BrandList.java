package Model.Record.List;

public class BrandList extends IList
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
