package modelo;

public class Error005 extends Exception {
    private String cartel=null;
    public Error005(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public Error005(Throwable throwable) {
        super(throwable);
    }

    public Error005(String string, Throwable throwable) {
        super(string, throwable);
    }

    public Error005(String string) {
        super(string);
        this.cartel=string;
    }

    public String getCartel() {
        return cartel;
    }

    public Error005() {
        super();
    }
}
