package views.Panels;

import Models.OptionsDTO;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {

  public CenterPanel(OptionsDTO optionsDTO) {
    optionsDTO.setEntradaFile(false);
    final Color COLOR_INPUT = new Color(40, 255, 20);
    final Color COLOR_ARCHIVO = new Color(40, 100, 255);
    final String TEXTO_ARCHIVO="Cambiar a introducir texto";
    final String TEXTO_INPUT="Cambiar a modo seleccionar archivo";




    setBackground(COLOR_INPUT);
    setVisible(true);

    JButton jButton = new JButton(TEXTO_INPUT);

    jButton.addActionListener(
        e -> {
          optionsDTO.setEntradaFile(!optionsDTO.isEntradaFile());
          jButton.setText(optionsDTO.isEntradaFile() ?  TEXTO_ARCHIVO:TEXTO_INPUT );
          setBackground(optionsDTO.isEntradaFile() ? COLOR_ARCHIVO: COLOR_INPUT );
        });

    add(jButton);

  }
}
