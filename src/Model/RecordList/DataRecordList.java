package Model.RecordList;

import Model.RecordModel.DataRecordModel;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class DataRecordList implements Iterable<DataRecordModel>
{
    @Override
    public Iterator<DataRecordModel> iterator() { return componentObjects.iterator(); }
    private final ArrayList<DataRecordModel> componentObjects = new ArrayList<>();
    private final DataRecordList nextPool;

    public DataRecordList(DataRecordList nextPool)
    {
        this.nextPool = nextPool;
    }

    public void registerComponent(DataRecordModel object)
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
            DataRecordModel childComponent = nextPool.getComponentAt(i);
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

    public void unregisterComponent(DataRecordModel object)
    {
        if (componentObjects.contains(object))
        {
            object.removeFromParent();
            object.removeAllChildrenRecursively();
            componentObjects.remove(object);
        }
        cleanupOrphanedChildren();
    }

    public void updateComponent(DataRecordModel oldObject, DataRecordModel newObject)
    {
        final int oldObjectIndex = componentObjects.indexOf(oldObject);
        if (oldObjectIndex == -1) { return; }
        for (int i = 0; i < oldObject.countChildren(); i++)
        {
            DataRecordModel child = oldObject.getChildAt(i);
            newObject.addChild(child);
        }
        var parent = oldObject.getParent();
        if (parent != null) { parent.addChild(newObject); }
        componentObjects.set(oldObjectIndex, newObject);
        oldObject.removeAllChildren();
        oldObject.removeFromParent();
    }

    public DataRecordModel getComponentAt(int index)
    {
        return componentObjects.get(index);
    }

    public int getIndexForComponent(DataRecordModel component)
    {
        return componentObjects.indexOf(component);
    }

    public int countRegisteredComponents()
    {
        return componentObjects.size();
    }

    public boolean componentIsRegisteredAtPool(DataRecordModel object) { return componentObjects.contains(object); }
}
