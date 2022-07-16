package Model.Record.List;

import Model.Record.Data.IData;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class IList implements Iterable<IData>
{
    private final ArrayList<IData> componentObjects = new ArrayList<>();
    private final IList nextPool;

    public IList(IList nextPool)
    {
        this.nextPool = nextPool;
    }

    @Override
    public Iterator<IData> iterator() { return componentObjects.iterator(); }

    public void registerComponent(IData object)
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
            IData childComponent = nextPool.getComponentAt(i);
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

    public void unregisterComponent(IData object)
    {
        if (componentObjects.contains(object))
        {
            object.removeFromParent();
            object.removeAllChildrenRecursively();
            componentObjects.remove(object);
        }
        cleanupOrphanedChildren();
    }

    public void updateComponent(IData oldObject, IData newObject)
    {
        final int oldObjectIndex = componentObjects.indexOf(oldObject);
        if (oldObjectIndex == -1) { return; }
        for (int i = 0; i < oldObject.countChildren(); i++)
        {
            IData child = oldObject.getChildAt(i);
            newObject.addChild(child);
        }
        var parent = oldObject.getParent();
        if (parent != null) { parent.addChild(newObject); }
        componentObjects.set(oldObjectIndex, newObject);
        oldObject.removeAllChildren();
        oldObject.removeFromParent();
    }

    public IData getComponentAt(int index)
    {
        return componentObjects.get(index);
    }

    public int getIndexForComponent(IData component)
    {
        return componentObjects.indexOf(component);
    }

    public int countRegisteredComponents()
    {
        return componentObjects.size();
    }

    public boolean componentIsRegisteredAtPool(IData object) { return componentObjects.contains(object); }
}
