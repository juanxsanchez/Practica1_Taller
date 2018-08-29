package modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Analisis {
    private String id,fecha,apellido,nombre,domicilio,medico;
    private ArrayList <Estudio> estudios=new ArrayList<Estudio>();
    public Analisis() {
        super();
    }

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getMedico() {
        return medico;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }


    public void setEstudios(ArrayList<Estudio> estudios) {
        this.estudios = estudios;
    }

    public ArrayList<Estudio> getEstudios() {
        return estudios;
    }

    public Analisis(String id, String fecha, String apellido, String nombre, String domicilio, String medico,ArrayList<Estudio>estudios) {
        this.id = id;
        this.fecha = fecha;
        this.apellido = apellido;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.medico = medico;
        this.estudios=estudios;
    }

    @Override
    public String toString() {
        String res= "Analisis: \n"+" Id: "+this.id+" Fecha: "+this.fecha+" Apellido: "+this.apellido+" Nombre: "+this.nombre+" Domicilio: "+this.domicilio+" Medico: "+this.medico+"\n"+"Estudios: "+"\n";
        Iterator it=this.estudios.iterator();
        while(it.hasNext()){
            Estudio estudio=(Estudio)it.next();
            res+="Tipo: "+estudio.getTipo()+" Valor: "+estudio.getValor()+"\n";
            }
        return res;
    }
}
