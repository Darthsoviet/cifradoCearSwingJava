package services;

import Models.ArgumentosCifradoDTO;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServicioCifrado {

  public String cifrarCadena(String cadena, int key) {
    key=key%26;
    return cifradoCommons(cadena, key, this::cifrarByte);
  }


  public String cifrarCadena(List<String> cadenas, int key) {
    return cadenas.stream().map(s -> cifrarCadena(s, key)).collect(Collectors.joining("\n"));
  }

  public String decifrarCadena(String cadena, int key) {
    key=key%26;
    return cifradoCommons(cadena, key, this::decifrarByte);
  }


  public String decifrarCadena(List<String> cadenas, int key) {
    return cadenas.stream().map(s -> decifrarCadena(s, key)).collect(Collectors.joining("\n"));
  }

  private byte cifrarByte(ArgumentosCifradoDTO argumentosCifradoDTO) {
    int nuevoCodigoAscii =
        (argumentosCifradoDTO.getCurrentCharCode()
                    - argumentosCifradoDTO.getValorInicial()
                    + argumentosCifradoDTO.getKey())
                % 26
            + argumentosCifradoDTO.getValorInicial();
    return (byte) nuevoCodigoAscii;
  }

  private byte decifrarByte(ArgumentosCifradoDTO argumentosCifradoDTO) {
    int nuevoCodigoAscii =
        (argumentosCifradoDTO.getCurrentCharCode()
                    - argumentosCifradoDTO.getValorInicial()
                    - argumentosCifradoDTO.getKey())
            + argumentosCifradoDTO.getValorInicial();
    nuevoCodigoAscii+=nuevoCodigoAscii<argumentosCifradoDTO.getValorInicial()?26:0;
    return (byte) nuevoCodigoAscii;
  }
  private String cifradoCommons(
          String cadena, int key, Function<ArgumentosCifradoDTO, Byte> funcionRealizar) {
    final int PRIMER_VALOR_ASCII_ALFABETO_MAYUSCULA = 65;
    final int PRIMER_VALOR_ASCII_ALFABETO_MINUSCULA = 97;
    byte[] bytes = cadena.getBytes(StandardCharsets.UTF_8);
    byte[] newBytes = new byte[bytes.length];
    for (int i = 0; i < bytes.length; i++) {
      byte currentByte = bytes[i];
      if (Character.isAlphabetic(currentByte)) {
        if (Character.isUpperCase(currentByte)) {
          newBytes[i] =
                  funcionRealizar.apply(
                          new ArgumentosCifradoDTO(
                                  currentByte, key, PRIMER_VALOR_ASCII_ALFABETO_MAYUSCULA));
        } else {
          newBytes[i] =
                  funcionRealizar.apply(
                          new ArgumentosCifradoDTO(
                                  currentByte, key, PRIMER_VALOR_ASCII_ALFABETO_MINUSCULA));
        }
      } else {
        newBytes[i] = currentByte;
      }
    }
    return new String(newBytes);
  }

}
