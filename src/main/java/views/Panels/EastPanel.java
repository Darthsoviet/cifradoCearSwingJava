package views.Panels;

import Models.OptionsDTO;

import javax.swing.*;
import java.awt.*;

public class EastPanel extends JPanel {

  public EastPanel(OptionsDTO optionsDTO) {
    optionsDTO.setSalidaFile(false);
    final Color COLOR_ARCHIVO = new Color(40, 100, 255);
    final Color COLOR_INPUT = new Color(40, 255, 20);
    final String TEXTO_ARCHIVO="cambiar a modo mostrar salida en texto";
    final String TEXTO_INPUT="cambiar modo guardar salida en archivo";




    setBackground(COLOR_INPUT);
    setVisible(true);

    JButton jButton = new JButton(TEXTO_INPUT);

    jButton.addActionListener(
        e -> {
          optionsDTO.setSalidaFile(!optionsDTO.isSalidaFile());
          jButton.setText(optionsDTO.isSalidaFile() ?  TEXTO_ARCHIVO:TEXTO_INPUT );
          setBackground(optionsDTO.isSalidaFile() ?   COLOR_ARCHIVO:COLOR_INPUT);
        });

    add(jButton);

  }
}
