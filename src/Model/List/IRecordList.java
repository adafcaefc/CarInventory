package Model.List;

import Model.Model.IRecordDataModel;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class IRecordList implements Iterable<IRecordDataModel>
{
    @Override
    public Iterator<IRecordDataModel> iterator() { return componentObjects.iterator(); }
    private final ArrayList<IRecordDataModel> componentObjects = new ArrayList<>();
    private final IRecordList nextPool;

    public IRecordList(IRecordList nextPool)
    {
        this.nextPool = nextPool;
    }

    public void registerComponent(IRecordDataModel object)
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
            IRecordDataModel childComponent = nextPool.getComponentAt(i);
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

    public void unregisterComponent(IRecordDataModel object)
    {
        if (componentObjects.contains(object))
        {
            object.removeFromParent();
            object.removeAllChildrenRecursively();
            componentObjects.remove(object);
        }
        cleanupOrphanedChildren();
    }

    public void updateComponent(IRecordDataModel oldObject, IRecordDataModel newObject)
    {
        final int oldObjectIndex = componentObjects.indexOf(oldObject);
        if (oldObjectIndex == -1) { return; }
        for (int i = 0; i < oldObject.countChildren(); i++)
        {
            IRecordDataModel child = oldObject.getChildAt(i);
            newObject.addChild(child);
        }
        var parent = oldObject.getParent();
        if (parent != null) { parent.addChild(newObject); }
        componentObjects.set(oldObjectIndex, newObject);
        oldObject.removeAllChildren();
        oldObject.removeFromParent();
    }

    public IRecordDataModel getComponentAt(int index)
    {
        return componentObjects.get(index);
    }

    public int getIndexForComponent(IRecordDataModel component)
    {
        return componentObjects.indexOf(component);
    }

    public int countRegisteredComponents()
    {
        return componentObjects.size();
    }

    public boolean componentIsRegisteredAtPool(IRecordDataModel object) { return componentObjects.contains(object); }
}
