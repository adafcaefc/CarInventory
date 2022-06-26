package Model.Pool;

import Model.Record.DataRecord;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class DataRecordPool implements Iterable<DataRecord>
{
    @Override
    public Iterator<DataRecord> iterator() { return componentObjects.iterator(); }
    private final ArrayList<DataRecord> componentObjects = new ArrayList<>();
    private final DataRecordPool nextPool;

    public DataRecordPool(DataRecordPool nextPool)
    {
        this.nextPool = nextPool;
    }

    public void registerComponent(DataRecord object)
    {
        if (!componentObjects.contains(object))
        {
            componentObjects.add(object);
        }
    }

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

    public void updateComponent(DataRecord oldObject, DataRecord newObject)
    {
        final int oldObjectIndex = componentObjects.indexOf(oldObject);
        if (oldObjectIndex == -1) { return; }
        for (int i = 0; i < oldObject.countChildren(); i++)
        {
            DataRecord child = oldObject.getChildAt(i);
            newObject.addChild(child);
        }
        var parent = oldObject.getParent();
        if (parent != null) { parent.addChild(newObject); }
        componentObjects.set(oldObjectIndex, newObject);
        oldObject.removeAllChildren();
        oldObject.removeFromParent();
    }

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
}
