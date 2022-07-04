package Model.RecordModel;

import java.util.ArrayList;

public abstract class DataRecordModel
{
    private final ArrayList<DataRecordModel> children = new ArrayList<>();

    private DataRecordModel parent = null;

    public DataRecordModel getParent()
    {
        return parent;
    }

    public void setParent(DataRecordModel newParent)
    {
        parent = newParent;
    }

    public void addChild(DataRecordModel child)
    {
        if (child.parent != this)
        {
            child.setParent(this);
            children.add(child);
        }
    }

    public void removeChild(DataRecordModel child)
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
            DataRecordModel targetChild = children.get(0);
            targetChild.removeAllChildrenRecursively();
            targetChild.removeFromParent();
        }
    }

    public DataRecordModel getChildAt(int index)
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
        for (DataRecordModel child : children)
        {
            totalChildren += child.countChildrenRecursively();
        }
        return totalChildren;
    }
}
