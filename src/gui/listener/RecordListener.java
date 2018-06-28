package gui.listener;

import entity.Category;
import gui.panel.*;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class RecordListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel p = RecordPanel.instance;
        if(0 == p.cbModel.getSize()){
            JOptionPane.showMessageDialog(p,"未添加分类，请添加分类");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }
        if(!GUIUtil.checkZero(p.tfSpend,"消费金额"))
            return;
        int spend = Integer.parseInt(p.tfSpend.getText());
        String comment = p.tfComment.getText();
        Category category = p.getSelectedItem();
         Date date = p.datePicker.getDate();
        new RecordService().add(spend,category,comment,date);
        JOptionPane.showMessageDialog(p,"添加成功");
        MainPanel.instance.workingPanel.show(SpendPanel.instance);

    }


}
