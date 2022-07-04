package View.Form.Input;

import Controller.CRUD.UpdateListener;
import Model.RecordModel.DataRecordModel;
import Model.RecordList.DataRecordList;
import View.Form.BaseForm;

import javax.swing.*;
import java.awt.*;

public abstract class BaseInputForm extends BaseForm
{
    private final boolean updateRecord;
    private final DataRecordModel originalRecord;
    private final DataRecordList componentPool;
    private UpdateListener updateListener;

    public BaseInputForm(
            boolean updateRecord,
            DataRecordModel originalRecord,
            DataRecordList componentPool) throws HeadlessException
    {
        super();
        setModal(true);
        this.updateRecord = updateRecord;
        this.originalRecord = originalRecord;
        this.componentPool = componentPool;
    }

    protected DataRecordModel getOriginalRecord()
    {
        return originalRecord;
    }

    public abstract DataRecordModel getFinishedRecord() throws Exception;

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

    public final void commitRecord(DataRecordModel newRecord)
    {
        if (componentPool == null || newRecord == null) { return; }
        if (updateRecord) { componentPool.updateComponent(originalRecord, newRecord); }
        else { componentPool.registerComponent(newRecord); }
        if (updateListener != null) { updateListener.onDataModelsChanged(); }
        dispose();
    }

    public boolean isUpdateRecord() { return updateRecord; }
}
