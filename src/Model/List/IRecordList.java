package Model.List;

import Model.Data.IRecordData;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class IRecordList implements Iterable<IRecordData>
{
    @Override
    public Iterator<IRecordData> iterator() { return componentObjects.iterator(); }
    private final ArrayList<IRecordData> componentObjects = new ArrayList<>();
    private final IRecordList nextPool;

    public IRecordList(IRecordList nextPool)
    {
        this.nextPool = nextPool;
    }

    public void registerComponent(IRecordData object)
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
            IRecordData childComponent = nextPool.getComponentAt(i);
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

    public void unregisterComponent(IRecordData object)
    {
        if (componentObjects.contains(object))
        {
            object.removeFromParent();
            object.removeAllChildrenRecursively();
            componentObjects.remove(object);
        }
        cleanupOrphanedChildren();
    }

    public void updateComponent(IRecordData oldObject, IRecordData newObject)
    {
        final int oldObjectIndex = componentObjects.indexOf(oldObject);
        if (oldObjectIndex == -1) { return; }
        for (int i = 0; i < oldObject.countChildren(); i++)
        {
            IRecordData child = oldObject.getChildAt(i);
            newObject.addChild(child);
        }
        var parent = oldObject.getParent();
        if (parent != null) { parent.addChild(newObject); }
        componentObjects.set(oldObjectIndex, newObject);
        oldObject.removeAllChildren();
        oldObject.removeFromParent();
    }

    public IRecordData getComponentAt(int index)
    {
        return componentObjects.get(index);
    }

    public int getIndexForComponent(IRecordData component)
    {
        return componentObjects.indexOf(component);
    }

    public int countRegisteredComponents()
    {
        return componentObjects.size();
    }

    public boolean componentIsRegisteredAtPool(IRecordData object) { return componentObjects.contains(object); }
}
