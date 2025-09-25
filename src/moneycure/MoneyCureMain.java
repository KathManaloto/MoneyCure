package moneycure;

import moneycure.controller.MoneyCureController;
import moneycure.view.MainFrame;
import moneycure.database.*;

import javax.swing.*;

public class MoneyCureMain {

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        SetupDatabase.createTables();

        SwingUtilities.invokeLater(()->{
            MainFrame mainFrame  = new MainFrame();
            new MoneyCureController(mainFrame, mainFrame.getAddData(),mainFrame.getDashboardPanel());
            mainFrame.setVisible(true);
        });
    }
}
