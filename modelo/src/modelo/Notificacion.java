package modelo;

public class Notificacion {
    private int bandera;
    private String mensaje;
    public Notificacion() {
        super();
    }

    public Notificacion(int bandera, String consulta) {
        this.bandera = bandera;
        this.mensaje = consulta;
    }

    public int getBandera() {
        return bandera;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
