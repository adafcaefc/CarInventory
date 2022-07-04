package Model.RecordList;

import Model.RecordModel.IDataRecordModel;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class IDataRecordList implements Iterable<IDataRecordModel>
{
    @Override
    public Iterator<IDataRecordModel> iterator() { return componentObjects.iterator(); }
    private final ArrayList<IDataRecordModel> componentObjects = new ArrayList<>();
    private final IDataRecordList nextPool;

    public IDataRecordList(IDataRecordList nextPool)
    {
        this.nextPool = nextPool;
    }

    public void registerComponent(IDataRecordModel object)
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
            IDataRecordModel childComponent = nextPool.getComponentAt(i);
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

    public void unregisterComponent(IDataRecordModel object)
    {
        if (componentObjects.contains(object))
        {
            object.removeFromParent();
            object.removeAllChildrenRecursively();
            componentObjects.remove(object);
        }
        cleanupOrphanedChildren();
    }

    public void updateComponent(IDataRecordModel oldObject, IDataRecordModel newObject)
    {
        final int oldObjectIndex = componentObjects.indexOf(oldObject);
        if (oldObjectIndex == -1) { return; }
        for (int i = 0; i < oldObject.countChildren(); i++)
        {
            IDataRecordModel child = oldObject.getChildAt(i);
            newObject.addChild(child);
        }
        var parent = oldObject.getParent();
        if (parent != null) { parent.addChild(newObject); }
        componentObjects.set(oldObjectIndex, newObject);
        oldObject.removeAllChildren();
        oldObject.removeFromParent();
    }

    public IDataRecordModel getComponentAt(int index)
    {
        return componentObjects.get(index);
    }

    public int getIndexForComponent(IDataRecordModel component)
    {
        return componentObjects.indexOf(component);
    }

    public int countRegisteredComponents()
    {
        return componentObjects.size();
    }

    public boolean componentIsRegisteredAtPool(IDataRecordModel object) { return componentObjects.contains(object); }
}
