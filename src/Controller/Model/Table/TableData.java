package Controller.Model.Table;

import java.util.ArrayList;

public class TableData
{
    String header;
    ITableAction action;

    public String getHeader()
    {
        return header;
    }

    public void setHeader(String header)
    {
        this.header = header;
    }

    public ITableAction getAction()
    {
        return action;
    }

    public void setAction(ITableAction action)
    {
        this.action = action;
    }

    public TableData(String header, ITableAction action)
    {
        this.header = header;
        this.action = action;
    }

    public static String[] getHeader(ArrayList<TableData> arrayList)
    {
        String[] result = new String[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
        {
            result[i] = arrayList.get(i).getHeader();
        }
        return result;
    }

    public static ITableAction[] getTableData(ArrayList<TableData> arrayList)
    {
        ITableAction[] result = new ITableAction[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
        {
            result[i] = arrayList.get(i).getAction();
        }
        return result;
    }
}
