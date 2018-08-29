package modelo;

public class Error002 extends Exception {
    private String cartel=null;
    public Error002(String string, Throwable throwable, boolean b, boolean b1) {
        super(string, throwable, b, b1);
    }

    public Error002(Throwable throwable) {
        super(throwable);
    }

    public Error002(String string, Throwable throwable) {
        super(string, throwable);
    }

    public Error002(String string) {
        super(string);
        this.cartel=string;
    }

    public Error002() {
        super();
    }


    public String getCartel() {
        return cartel;
    }
}
