package Model.Record;

import java.util.ArrayList;

public abstract class DataRecord
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

    public void addChild(DataRecord child)
    {
        if (child.parent != this)
        {
            child.setParent(this);
            children.add(child);
        }
    }

    public void removeChild(DataRecord child)
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
            DataRecord targetChild = children.get(0);
            targetChild.removeAllChildrenRecursively();
            targetChild.removeFromParent();
        }
    }

    public DataRecord getChildAt(int index)
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
        for (DataRecord child : children)
        {
            totalChildren += child.countChildrenRecursively();
        }
        return totalChildren;
    }
}
