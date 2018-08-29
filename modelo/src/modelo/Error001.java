package modelo;

public class Error001 extends Exception {
    private String cartel=null;
    public Error001(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public Error001(Throwable throwable) {
        super(throwable);
    }

    public Error001(String string, Throwable throwable) {
        super(string, throwable);
    }

    public Error001(String string) {
        super(string);
        this.cartel=string;
    }

    public Error001() {
        super();
    }

    public String getCartel() {
        return cartel;
    }
}
