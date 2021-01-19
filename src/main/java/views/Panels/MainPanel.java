package views.Panels;

import Models.OptionsDTO;
import services.ServicioArchivos;
import services.ServicioCifrado;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MainPanel extends JPanel {
  Dimension screen;

  private final ServicioArchivos servicioArchivos = new ServicioArchivos();
  private final ServicioCifrado servicioCifrado = new ServicioCifrado();
  private final Color DARK_GREY = new Color(30, 30, 30);
  private final Font VERDANA = new Font("Verdana", 1, 12);




  public MainPanel() {
    OptionsDTO optionsDTO = new OptionsDTO();
    WestPanel westPanel = new WestPanel(optionsDTO);
    CenterPanel centerPanel = new CenterPanel(optionsDTO);
    EastPanel eastPanel = new EastPanel(optionsDTO);
    setBackground(DARK_GREY);

    this.screen = Toolkit.getDefaultToolkit().getScreenSize();

    JButton botonIncio = new JButton("iniciar cifrado");
    botonIncio.addActionListener((e) -> iniciar(optionsDTO));

    setLayout(new BorderLayout(20,20));
    JPanel jPanelHeader = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
    jPanelHeader.add(westPanel);
    jPanelHeader.add(centerPanel);
    jPanelHeader.add(eastPanel);
    jPanelHeader.setBackground(Color.DARK_GRAY);

    JPanel jPanelSouth = new JPanel(new FlowLayout());
    jPanelSouth.setBackground(DARK_GREY);
    jPanelSouth.add(botonIncio);

    add(jPanelHeader,BorderLayout.NORTH);

    add(jPanelSouth, BorderLayout.SOUTH);
    setVisible(true);
  }

  private void iniciar(OptionsDTO optionsDTO) {
    if ((seleccionarLlave(optionsDTO) && selecionarEntrada(optionsDTO))) {
      procesarEntrada(optionsDTO);
      seleccionarSalida(optionsDTO);
    }
  }

  private void seleccionarSalida(OptionsDTO optionsDTO) {
    if (optionsDTO.isSalidaFile()) {
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.showOpenDialog(this);
      File selectedFile = jFileChooser.getSelectedFile();
      if (Objects.nonNull(selectedFile)) {
        servicioArchivos.saveFileText(optionsDTO.getResultado(), selectedFile,".cifrado");
      }
    } else {
      showOutputPanel(this,optionsDTO);
    }
  }

  private void procesarEntrada(OptionsDTO optionsDTO) {
    if (optionsDTO.isCifrar()) {

      optionsDTO.setResultado(
          servicioCifrado.cifrarCadena(optionsDTO.getEntrada(), optionsDTO.getKey()));
    } else {
      optionsDTO.setResultado(
          servicioCifrado.decifrarCadena(optionsDTO.getEntrada(), optionsDTO.getKey()));
    }
  }

  private boolean seleccionarLlave(OptionsDTO optionsDTO) {
    while (true) {
      try {
        String key = JOptionPane.showInputDialog(this, "inserte una llave numerica");
        if (Objects.isNull(key)) {
          return false;
        }
        optionsDTO.setKey(Integer.parseInt(key));
        return true;
      } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "debe de ser un valor numerico");
      }
    }
  }

  private boolean selecionarEntrada(OptionsDTO optionsDTO) {

    if (optionsDTO.isEntradaFile()) {
      Optional<File> file = showFileInput();
      if (file.isPresent()) {
        List<String> flieLines = servicioArchivos.getFlieLines(file.get());
        optionsDTO.setEntrada(String.join("\n", flieLines));
      } else {
        return false;
      }
    } else {
      Optional<String> inputDialog = showInput(optionsDTO);
      if (inputDialog.isPresent()) {
        optionsDTO.setEntrada(inputDialog.get());
      } else {
        return false;
      }
    }
    return true;
  }

  private Optional<String> showInput(OptionsDTO optionsDTO) {
    return Optional.ofNullable(
        JOptionPane.showInputDialog(
            this,
            String.format(
                "inserte mensaje a %s para %s",
                optionsDTO.isCifrar() ? "cifrar" : "decifrar",
                optionsDTO.isSalidaFile() ? "guardar en un archivo" : "mostrar por pantalla")));
  }

  private Optional<File> showFileInput() {
    JOptionPane.showMessageDialog(this,"selecciona un archivo .csv ,.log,.txt o .html o casi cualquier no binario");
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setFileFilter(
        new FileNameExtensionFilter(
            "seleccione un archivo de texto no binario", "cifrado","csv", "log", "txt","html","htm","java","js","jsx",
                "css","xml","json","yaml","c","cpp","go","py"));
    jFileChooser.showOpenDialog(this);
    return Optional.ofNullable(jFileChooser.getSelectedFile());
  }
  private void showOutputPanel(Component component,OptionsDTO optionsDTO){
    JTextArea textArea = new JTextArea(optionsDTO.getResultado());
    JScrollPane scrollPane = new JScrollPane(textArea);
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);
    textArea.setBackground(DARK_GREY);
    textArea.setForeground(Color.WHITE);
    textArea.setFont(VERDANA);
    scrollPane.setPreferredSize( new Dimension( 1000, 1000 ) );
    JOptionPane.showMessageDialog(this, scrollPane, "texto procesado",
            JOptionPane.INFORMATION_MESSAGE);
  }
}
