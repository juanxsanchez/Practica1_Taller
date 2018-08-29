package modelo;

public class Estudio {
    private String tipo;
    private double valor;
    public Estudio() {
        super();
    }

    public Estudio(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        String res= "Tipo de estudio: "+this.tipo+" Valor: "+this.valor;
        return res;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
