package Model.DataPool;

import Model.DataModel.DataRecord;

import java.util.ArrayList;

public class DataRecordPool
{
    private final ArrayList<DataRecord> componentObjects = new ArrayList<>();;
    private final DataRecordPool nextPool;

    public DataRecordPool(DataRecordPool nextPool)
    {
        this.nextPool = nextPool;
    }
}
