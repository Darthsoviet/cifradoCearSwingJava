package views.Panels;

import Models.OptionsDTO;

import javax.swing.*;
import java.awt.*;

public class WestPanel extends JPanel {

  public WestPanel(OptionsDTO optionsDTO) {
    optionsDTO.setCifrar(true);
    final Color COLOR_CIFRAR = new Color(40, 255, 20);
    final Color COLOR_DECIFRAR = new Color(40, 100, 255);
    final String TEXTO_CIFRAR="Cambiar a modo decifrar";
    final String TEXTO_DECIFRAR="Cambiar a modo cifrar";


    setBackground(COLOR_CIFRAR);
    setVisible(true);

    JButton jButton = new JButton(TEXTO_CIFRAR);
    jButton.addActionListener(
        e -> {
          optionsDTO.setCifrar(!optionsDTO.isCifrar());
          setBackground(optionsDTO.isCifrar() ? COLOR_CIFRAR : COLOR_DECIFRAR);
          jButton.setText(optionsDTO.isCifrar() ? TEXTO_CIFRAR : TEXTO_DECIFRAR);
        });

    add(jButton);

  }
}
