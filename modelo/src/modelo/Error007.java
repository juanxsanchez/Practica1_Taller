package modelo;

public class Error007 extends Exception {
    
    private String cartel=null;
    public Error007(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public Error007(Throwable throwable) {
        super(throwable);
    }

    public Error007(String string, Throwable throwable) {
        super(string, throwable);
    }

    public Error007(String string) {
        super(string);
        this.cartel=string;
    }

    public Error007() {
        super();
    }
    public String getCartel() {
        return cartel;
    }
}
