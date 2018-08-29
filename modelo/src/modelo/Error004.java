package modelo;

public class Error004 extends Exception {
    private String cartel=null;
    public Error004(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public Error004(Throwable throwable) {
        super(throwable);
    }

    public Error004(String string, Throwable throwable) {
        super(string, throwable);
    }

    public Error004(String string) {
        super(string);
        this.cartel=string;
    }

    public String getCartel() {
        return cartel;
    }

    public Error004() {
        super();
    }
}
