package View.Form.Input;

import Controller.Model.Listener.IUpdateListener;
import Model.Record.Data.IData;
import Model.Record.List.IList;
import View.Form.IBaseForm;

import javax.swing.*;
import java.awt.*;

public abstract class IBaseInputForm extends IBaseForm
{
    private final boolean updateRecord;
    private final IData originalRecord;
    private final IList componentPool;
    private IUpdateListener updateListener;

    public IBaseInputForm(
            boolean updateRecord,
            IData originalRecord,
            IList componentPool) throws HeadlessException
    {
        super();
        setModal(true);
        this.updateRecord = updateRecord;
        this.originalRecord = originalRecord;
        this.componentPool = componentPool;
    }

    protected IData getOriginalRecord()
    {
        return originalRecord;
    }

    public abstract IData getFinishedRecord() throws Exception;

    public abstract boolean validateInputs();

    public final void bindUpdateListener(IUpdateListener listener)
    {
        updateListener = listener;
    }

    @Override
    public void bindButtons(JButton okButton, JButton cancelButton)
    {
        okButton.addActionListener(e ->
        {
            try
            {
                if (validateInputs())
                {
                    JOptionPane.showMessageDialog(
                            null, "Data updated!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    commitRecord(getFinishedRecord());
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(
                        null, ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());
    }

    public final void commitRecord(IData newRecord)
    {
        if (componentPool == null || newRecord == null) { return; }
        if (updateRecord) { componentPool.updateComponent(originalRecord, newRecord); }
        else { componentPool.registerComponent(newRecord); }
        if (updateListener != null) { updateListener.onUpdateRecord(); }
        dispose();
    }

    public boolean isUpdateRecord() { return updateRecord; }
}
