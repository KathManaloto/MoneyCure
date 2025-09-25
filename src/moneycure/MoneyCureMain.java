package moneycure;

import moneycure.controller.MoneyCureController;
import moneycure.view.MainFrame;
import moneycure.database.*;

import javax.swing.*;
import java.util.logging.*;

public class MoneyCureMain {

    private static final Logger LOGGER = Logger.getLogger(MoneyCureMain.class.getName());

    public static void main(String[] args) {

        // ===== UI MANAGER =====
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

        // ===== SWING =====
        SwingUtilities.invokeLater(()->{

            // Create splash window
            JWindow splash = new JWindow();
            splash.add(new JLabel("Initializing database...",SwingConstants.CENTER));
            splash.setSize(300, 100);
            splash.setLocationRelativeTo(null);
            splash.setVisible(true);

            // Run database setup in background
            SwingWorker<Void, Void> worker = new SwingWorker<>(){

                @Override
                protected Void doInBackground(){
                    SetupDatabase.createTables();
                    return null;
                }

                protected void done(){
                    splash.dispose();   // Close splash when DB setup is done

                    try{
                        get();          // rethrow exception if createTables() failed

                        // Launch main application
                        MainFrame mainFrame = new MainFrame();
                        new MoneyCureController(mainFrame, mainFrame.getAddData(), mainFrame.getDashboardPanel());
                        mainFrame.setVisible(true);

                    } catch (Exception e){
                        LOGGER.log(Level.SEVERE, "Database setup failed", e);
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to initialize the database. The application will exit.",
                                "Database Error", JOptionPane.ERROR_MESSAGE
                        );
                        System.exit(1);
                    }
                }
            };
            worker.execute(); // Start the background task
        });
    }
}