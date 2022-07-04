package Model.RecordModel;

import java.util.ArrayList;

public abstract class IDataRecordModel
{
    private final ArrayList<IDataRecordModel> children = new ArrayList<>();

    private IDataRecordModel parent = null;

    public IDataRecordModel getParent()
    {
        return parent;
    }

    public void setParent(IDataRecordModel newParent)
    {
        parent = newParent;
    }

    public void addChild(IDataRecordModel child)
    {
        if (child.parent != this)
        {
            child.setParent(this);
            children.add(child);
        }
    }

    public void removeChild(IDataRecordModel child)
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
            IDataRecordModel targetChild = children.get(0);
            targetChild.removeAllChildrenRecursively();
            targetChild.removeFromParent();
        }
    }

    public IDataRecordModel getChildAt(int index)
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
        for (IDataRecordModel child : children)
        {
            totalChildren += child.countChildrenRecursively();
        }
        return totalChildren;
    }
}
