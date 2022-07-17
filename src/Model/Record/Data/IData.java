package Model.Record.Data;

import java.util.ArrayList;

public abstract class IData
{
    private final ArrayList<IData> children = new ArrayList<>();

    private IData parent = null;

    public IData getParent()
    {
        return parent;
    }

    public void setParent(IData newParent)
    {
        parent = newParent;
    }

    public void addChild(IData child)
    {
        if (child.parent != this)
        {
            child.setParent(this);
            children.add(child);
        }
    }

    public void removeChild(IData child)
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
            IData targetChild = children.get(0);
            targetChild.removeAllChildrenRecursively();
            targetChild.removeFromParent();
        }
    }

    public IData getChildAt(int index)
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
        for (IData child : children)
        {
            totalChildren += child.countChildrenRecursively();
        }
        return totalChildren;
    }
}
