package Controlador;

import Vista.Ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Observable;
import java.util.Observer;

import modelo.Almacen;
import modelo.Error001;
import modelo.Error006;
import modelo.Notificacion;
import modelo.Utilidad;

public class Controlador implements ActionListener, Observer{
    private Almacen almacenActivo;
    private Ventana ventana=new Ventana();  
    private Observable observable;
    public static final String COMENZAR="COMENZAR";
    
    public Controlador() {
        super();
        this.ventana.addActionListener(this);
        this.agregarObservable(Utilidad.getUtilidad());
        System.out.println("Se creo el controlador");    
    }

    public void agregarObservable(Observable o)
    {
        if (this.observable==null)
        {
            this.observable=o;
            o.addObserver(this);
            System.out.println("Se agrego a utilidad como observable");
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals(Controlador.COMENZAR))
        {
            System.out.println("Se pulso el boton comenzar");
            System.out.println(this.ventana.sentencias());
            Utilidad.getUtilidad().crearComandos(this.ventana.sentencias());
            this.ventana.getTextErrores().setText(" ");
            this.ventana.getTextResultados().setText(" ");
            try {
                Utilidad.getUtilidad().scan();
            } catch (Error001 e) {
                this.ventana.mostrarError(e.getCartel());
            } catch (Error006 e) {
                this.ventana.mostrarError(e.getCartel());
            }
        }
    }

@Override
    public void update(Observable observable, Object object) {
        System.out.println("llego el mensaje de mostrar consulta o error");
        if(observable==Utilidad.getUtilidad())
        {
            Notificacion noti=(Notificacion)object;
            if(noti.getBandera()==Utilidad.BIEN){
                System.out.println("imprimir consuulta"+noti.getMensaje());
                this.ventana.mostrarConsulta(noti.getMensaje());
            }
            else
                this.ventana.mostrarError(noti.getMensaje());
        }
    }

public static void terminar()
{
    Utilidad.serializarLista();
    System.exit(0);
}

}
