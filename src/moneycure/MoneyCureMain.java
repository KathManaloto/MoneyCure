package moneycure;

import moneycure.controller.MoneyCureController;
import moneycure.view.MainFrame;
import moneycure.database.*;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoneyCureMain {

    private static  final Logger LOGGER = Logger.getLogger(MoneyCureMain.class.getName());

    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING,"Failed to set Nimbus look and feel, using default.",e);
        }

        SetupDatabase.createTables();

        SwingUtilities.invokeLater(()->{
            MainFrame mainFrame  = new MainFrame();
            new MoneyCureController(mainFrame, mainFrame.getAddData(),mainFrame.getDashboardPanel());
            mainFrame.setVisible(true);
        });
    }
}