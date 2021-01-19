package services;

import sun.nio.cs.ext.Big5_Solaris;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ServicioArchivos {
  public List<String> getFlieLines(File file) {

    if (!file.exists()) {
      System.err.println("no se pudo encontrar el archivo dada la siguiente ruta: " + file.getPath());
    }
    try {
      return Files.lines(file.toPath()).collect(Collectors.toList());
    } catch (Exception e) {
      throw new RuntimeException("error al cargar archivo");
    }
  }
  public void saveFileText(String text, File file,String extension) {


    try {
      Files.write(Paths.get(file.getPath()+extension),text.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
