package views;


import views.Panels.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {

  public MainFrame() throws HeadlessException {

    setIconImage(
            Toolkit.getDefaultToolkit()
                    .getImage(
                            "src"
                                    + File.separator
                                    + "main"
                                    + File.separator
                                    + "resources"
                                    + File.separator
                                    + "images"
                                    + File.separator
                                    + "EscudoUNAM.png"));
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(screenSize.width/4,screenSize.height/4,screenSize.width/2,screenSize.height/2);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Cifrado Cesar Fes Aragon");
    add(new MainPanel());
    setVisible(true);

  }

}
