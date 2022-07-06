package View.Form.Input;

import Controller.Model.Listener.UpdateListener;
import Model.Data.IRecordData;
import Model.List.IRecordList;
import View.Form.IBaseForm;

import javax.swing.*;
import java.awt.*;

public abstract class IBaseInputForm extends IBaseForm
{
    private final boolean updateRecord;
    private final IRecordData originalRecord;
    private final IRecordList componentPool;
    private UpdateListener updateListener;

    public IBaseInputForm(
            boolean updateRecord,
            IRecordData originalRecord,
            IRecordList componentPool) throws HeadlessException
    {
        super();
        setModal(true);
        this.updateRecord = updateRecord;
        this.originalRecord = originalRecord;
        this.componentPool = componentPool;
    }

    protected IRecordData getOriginalRecord()
    {
        return originalRecord;
    }

    public abstract IRecordData getFinishedRecord() throws Exception;

    public abstract boolean validateInputs();

    public final void bindUpdateListener(UpdateListener listener)
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

    public final void commitRecord(IRecordData newRecord)
    {
        if (componentPool == null || newRecord == null) { return; }
        if (updateRecord) { componentPool.updateComponent(originalRecord, newRecord); }
        else { componentPool.registerComponent(newRecord); }
        if (updateListener != null) { updateListener.onUpdateRecord(); }
        dispose();
    }

    public boolean isUpdateRecord() { return updateRecord; }
}
