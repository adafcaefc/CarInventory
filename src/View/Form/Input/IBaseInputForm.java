package View.Form.Input;

import Controller.CRUD.UpdateListener;
import Model.RecordModel.IDataRecordModel;
import Model.RecordList.IDataRecordList;
import View.Form.IBaseForm;

import javax.swing.*;
import java.awt.*;

public abstract class IBaseInputForm extends IBaseForm
{
    private final boolean updateRecord;
    private final IDataRecordModel originalRecord;
    private final IDataRecordList componentPool;
    private UpdateListener updateListener;

    public IBaseInputForm(
            boolean updateRecord,
            IDataRecordModel originalRecord,
            IDataRecordList componentPool) throws HeadlessException
    {
        super();
        setModal(true);
        this.updateRecord = updateRecord;
        this.originalRecord = originalRecord;
        this.componentPool = componentPool;
    }

    protected IDataRecordModel getOriginalRecord()
    {
        return originalRecord;
    }

    public abstract IDataRecordModel getFinishedRecord() throws Exception;

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

    public final void commitRecord(IDataRecordModel newRecord)
    {
        if (componentPool == null || newRecord == null) { return; }
        if (updateRecord) { componentPool.updateComponent(originalRecord, newRecord); }
        else { componentPool.registerComponent(newRecord); }
        if (updateListener != null) { updateListener.onDataModelsChanged(); }
        dispose();
    }

    public boolean isUpdateRecord() { return updateRecord; }
}
