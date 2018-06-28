package startup;
import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import util.GUIUtil;
import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
public class Bootstrap {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        GUIUtil.useLNF();
        SwingUtilities.invokeAndWait(() -> {
            System.out.println("test");
            MainFrame.instance.setVisible(true);
            MainPanel.instance.workingPanel.show(SpendPanel.instance);
        });
    }
}
