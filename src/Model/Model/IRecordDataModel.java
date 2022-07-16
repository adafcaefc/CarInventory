package Model.Model;

import java.util.ArrayList;

public abstract class IRecordDataModel
{
    private final ArrayList<IRecordDataModel> children = new ArrayList<>();

    private IRecordDataModel parent = null;

    public IRecordDataModel getParent()
    {
        return parent;
    }

    public void setParent(IRecordDataModel newParent)
    {
        parent = newParent;
    }

    public void addChild(IRecordDataModel child)
    {
        if (child.parent != this)
        {
            child.setParent(this);
            children.add(child);
        }
    }

    public void removeChild(IRecordDataModel child)
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
            IRecordDataModel targetChild = children.get(0);
            targetChild.removeAllChildrenRecursively();
            targetChild.removeFromParent();
        }
    }

    public IRecordDataModel getChildAt(int index)
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
        for (IRecordDataModel child : children)
        {
            totalChildren += child.countChildrenRecursively();
        }
        return totalChildren;
    }
}
