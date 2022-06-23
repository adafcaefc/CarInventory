package Model.DataPool;

import Model.DataModel.DataRecord;

import java.util.ArrayList;

public class DataRecordPool
{
    private final ArrayList<DataRecord> componentObjects = new ArrayList<>();;
    private final DataRecordPool nextPool;

    public DataRecord getComponentAt(int index)
    {
        return componentObjects.get(index);
    }

    public int getIndexForComponent(DataRecord component)
    {
        return componentObjects.indexOf(component);
    }

    public int countRegisteredComponents()
    {
        return componentObjects.size();
    }

    public boolean componentIsRegisteredAtPool(DataRecord object) { return componentObjects.contains(object); }
    protected void cleanupOrphanedChildren()
    {
        if (nextPool == null) { return; }
        for (int i = 0; i < nextPool.countRegisteredComponents(); )
        {
            DataRecord childComponent = nextPool.getComponentAt(i);
            if (!componentIsRegisteredAtPool(childComponent.getParent()))
            {
                nextPool.unregisterComponent(childComponent);
            }
            else
            {
                i++;
            }
        }
        nextPool.cleanupOrphanedChildren();
    }
    public void registerComponent(DataRecord object)
    {
        if (!componentObjects.contains(object))
        {
            componentObjects.add(object);
        }
    }

    public void unregisterComponent(DataRecord object)
    {
        if (componentObjects.contains(object))
        {
            object.removeFromParent();
            object.removeAllChildrenRecursively();
            componentObjects.remove(object);
        }
        cleanupOrphanedChildren();
    }
    public DataRecordPool(DataRecordPool nextPool)
    {
        this.nextPool = nextPool;
    }
}
