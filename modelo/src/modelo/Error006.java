package modelo;

public class Error006 extends Exception {
    private String cartel=null;
    public Error006(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public Error006(Throwable throwable) {
        super(throwable);
    }

    public Error006(String string, Throwable throwable) {
        super(string, throwable);
    }

    public Error006(String string) {
        super(string);
        this.cartel=string;
    }

    public String getCartel() {
        return cartel;
    }

    public Error006() {
        super();
    }
}
