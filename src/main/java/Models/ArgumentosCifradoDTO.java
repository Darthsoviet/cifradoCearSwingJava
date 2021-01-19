package Models;

public class ArgumentosCifradoDTO {
    private int currentCharCode;
    private int key;
    private int valorInicial;

    public int getCurrentCharCode() {
        return currentCharCode;
    }

    public void setCurrentCharCode(int currentCharCode) {
        this.currentCharCode = currentCharCode;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(int valorInicial) {
        this.valorInicial = valorInicial;
    }

    public ArgumentosCifradoDTO(int currentCharCode, int key, int valorInicial) {
        this.currentCharCode = currentCharCode;
        this.key = key;
        this.valorInicial = valorInicial;
    }
}
