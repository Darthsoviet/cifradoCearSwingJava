package Models;

import java.io.Serializable;

public class OptionsDTO implements Serializable {
    private boolean cifrar;
    private Integer key;
    private boolean entradaFile;
    private boolean salidaFile;
    private String entrada;
    private String resultado;

    public boolean isCifrar() {
        return cifrar;
    }

    public void setCifrar(boolean cifrar) {
        this.cifrar = cifrar;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public boolean isEntradaFile() {
        return entradaFile;
    }

    public void setEntradaFile(boolean entradaFile) {
        this.entradaFile = entradaFile;
    }

    public boolean isSalidaFile() {
        return salidaFile;
    }

    public void setSalidaFile(boolean salidaFile) {
        this.salidaFile = salidaFile;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "OptionsDTO{" +
                "cifrar=" + cifrar +
                ", key=" + key +
                ", entradaFile=" + entradaFile +
                ", salidaFile=" + salidaFile +
                ", entrada='" + entrada + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
