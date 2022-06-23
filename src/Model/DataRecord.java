package Model;

import java.util.ArrayList;

public class DataRecord
{
    private final ArrayList<DataRecord> children = new ArrayList<>();
    private DataRecord parent = null;

    public DataRecord getParent()
    {
        return parent;
    }
    public void setParent(DataRecord newParent)
    {
        parent = newParent;
    }
}
