package modelo;

public class Error003 extends Exception {
    private String cartel=null;
    public Error003(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public Error003(Throwable throwable) {
        super(throwable);
    }

    public Error003(String string, Throwable throwable) {
        super(string, throwable);
    }

    public Error003(String string) {
        super(string);
        this.cartel=string;
    }

    public String getCartel() {
        return cartel;
    }

    public Error003() {
        super();
    }
}
