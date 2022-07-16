package Model.Model;

import java.util.ArrayList;

public abstract class IRecordData
{
    private final ArrayList<IRecordData> children = new ArrayList<>();

    private IRecordData parent = null;

    public IRecordData getParent()
    {
        return parent;
    }

    public void setParent(IRecordData newParent)
    {
        parent = newParent;
    }

    public void addChild(IRecordData child)
    {
        if (child.parent != this)
        {
            child.setParent(this);
            children.add(child);
        }
    }

    public void removeChild(IRecordData child)
    {
        children.remove(child);
    }

    public void removeFromParent()
    {
        if (parent != null)
        {
            parent.removeChild(this);
            parent = null;
        }
    }

    public void removeAllChildren()
    {
        children.clear();
    }

    public void removeAllChildrenRecursively()
    {
        for (int i = children.size(); i > 0; i--)
        {
            IRecordData targetChild = children.get(0);
            targetChild.removeAllChildrenRecursively();
            targetChild.removeFromParent();
        }
    }

    public IRecordData getChildAt(int index)
    {
        return children.get(index);
    }

    public int countChildren()
    {
        return children.size();
    }

    public int countChildrenRecursively()
    {
        int totalChildren = children.size();
        for (IRecordData child : children)
        {
            totalChildren += child.countChildrenRecursively();
        }
        return totalChildren;
    }
}
