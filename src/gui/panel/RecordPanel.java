package gui.panel;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import service.RecordService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class RecordPanel extends WorkingPanel{
    static {
        GUIUtil.useLNF();
    }
    public static RecordPanel instance = new RecordPanel();

    JLabel lSpend = new JLabel("花费(￥)");
    JLabel lCategory = new JLabel("分类");
    JLabel lComment = new JLabel("备注");
    JLabel lDate = new JLabel("日期");

    public JTextField tfSpend= new JTextField("0");


    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();
    public JComboBox<String> cbCategory = new JComboBox(cbModel);
    public JTextField tfComment = new JTextField();
    public JXDatePicker datePicker = new JXDatePicker(new Date());

    JButton bSubmit = new JButton("记一笔");

    public RecordPanel(){
        GUIUtil.setColor(ColorUtil.grayColor,lSpend,lCategory,lComment,lDate);
        GUIUtil.setColor(ColorUtil.blueColor,bSubmit);
        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();

        int gap = 40;
        pInput.setLayout(new GridLayout(4,2,gap,gap));
        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tfComment);
        pInput.add(lDate);
        pInput.add(datePicker);
        pSubmit.add(bSubmit);
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.NORTH);
        this.add(pSubmit,BorderLayout.CENTER);
        addListener();
    }

    public Category getSelectedItem(){
        return (Category)cbCategory.getSelectedItem();
    }
    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.instance);
    }


    @Override
    public void updateData() {
        System.out.println("cbmd更新了");
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();
        tfSpend.grabFocus();
        reset();
    }
    private void reset() {
            tfSpend.setText("0");
            tfComment.setText("");
            if(0!=cbModel.cs.size()){
                cbCategory.setSelectedIndex(0);
            }
            datePicker.setDate(new Date());
    }

    @Override
    public void addListener() {
        bSubmit.addActionListener(new RecordListener());
    }
}
