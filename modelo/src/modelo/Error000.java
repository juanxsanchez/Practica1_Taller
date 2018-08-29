package modelo;

public class Error000 extends Exception {
    private String cartel=null;
    public Error000(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public Error000(Throwable throwable) {
        super(throwable);
    }

    public Error000(String string, Throwable throwable) {
        super(string, throwable);
    }

    public Error000(String string) {
        super(string);
        this.cartel=string;
    }

    public Error000() {
        super();
    }

    public String getCartel() {
        return cartel;
    }
}
