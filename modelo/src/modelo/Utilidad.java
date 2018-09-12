package modelo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.Observable;


public class Utilidad extends Observable{
    private String[] listaComandos;
    public static String CREAR="CREAR";
    public static String CARGAR="CARGAR";
    public static String GUARDAR="GUARDAR";
    public static String INSERTAR="INSERTAR";
    public static String ELIMINAR="ELIMINAR";
    public static String CONSULTAR="CONSULTAR";
    public static final int ERROR=2;
    public static final int BIEN=1;
    public static String SERIALIZAR_LISTA="LISTA_DE_ALMACENES.xml";
    private Almacen almacen;
    private static ListaAlmacenes listaAlmacenes = new ListaAlmacenes();
    private static Utilidad instance=null;
    
    private Utilidad(){
            this.deserializarLista();
    }
    
    public static Utilidad getUtilidad()
    {
        Utilidad res=null;
        if(Utilidad.instance==null)
        {
            Utilidad.instance=new Utilidad();
            res=Utilidad.instance;
        }
        else 
        {
            res=Utilidad.instance;
        }
        return res;
    }
    public void crearComandos(String s)
    {
        this.listaComandos= s.split("\n");
    }
    
    public void scan() throws Error001, Error006 {
        int comandos=0;
        String[] comandoActual;
        
        System.out.println("cantidad de comandos: "+this.listaComandos.length);
        while(comandos<this.listaComandos.length){
        comandoActual=this.listaComandos[comandos].split(" ");
        System.out.println(comandoActual[0]);
        if(comandoActual[0].equalsIgnoreCase(Utilidad.CARGAR))
        {
            try {
                this.cargar(comandoActual);
            } catch (Error004 e) {
                setChanged();
                notifyObservers(new Notificacion(Utilidad.ERROR,e.getCartel()));
            } catch (Error000 e) {
                    setChanged();
                    notifyObservers(new Notificacion(Utilidad.ERROR,e.getCartel()));
                }
            }
        else{    
            if(comandoActual[0].equalsIgnoreCase(Utilidad.CONSULTAR))
                {
                if(this.almacen!=null){
                    try {
                        System.out.println("trata de consultar");
                        String consulta=this.almacen.consultar(comandoActual);
                        Notificacion noti=new Notificacion(Utilidad.BIEN,consulta);
                        setChanged();
                        notifyObservers(noti);
                        System.out.println("ya notifico al observer");                          
                    } catch (Error002 e) {
                        setChanged();
                        notifyObservers(new Notificacion(Utilidad.ERROR,e.getCartel()));
                    }
                        }
                        else{throw new Error006("No hay almacen abierto");}
                        }
                
            else{ 
                if(comandoActual[0].equalsIgnoreCase(Utilidad.CREAR))
                {
                    try {
                        this.crearAlmacen(comandoActual);
                    } catch (Error000 e) {
                        setChanged();
                        notifyObservers(new Notificacion(Utilidad.ERROR,e.getCartel()));
                    } catch (Error004 e) {
                        setChanged();
                        notifyObservers(new Notificacion(Utilidad.ERROR,e.getCartel()));
                    }
                 }
                else {
                    if(comandoActual[0].equalsIgnoreCase(Utilidad.ELIMINAR))
                    {
                         if(this.almacen!=null){
                            try {
                                this.almacen.eliminar(comandoActual);
                            } catch (Error005 e) {
                                setChanged();
                                notifyObservers(new Notificacion(Utilidad.ERROR,e.getCartel()));
                            }
                            }
                        else{throw new Error006("No hay almacen abierto");}
                                                 
                    }
                    else {
                        if(comandoActual[0].equalsIgnoreCase(Utilidad.GUARDAR))
                        {
                            if(this.almacen!=null){
                                this.guardarAlmacen();
                            }
                            else{throw new Error006("No hay almacen abierto");}
                        }
                        else {
                            if(comandoActual[0].equalsIgnoreCase(Utilidad.INSERTAR))
                            {
                                if(this.almacen!=null){
                                        try {
                                            this.almacen.insertar(comandoActual);
                                        } catch (Error007 e) {
                                            setChanged();
                                            notifyObservers(new Notificacion(Utilidad.ERROR,e.getCartel()));
                                        }
                                    }
                                else{throw new Error006("No hay almacen abierto");}
                            }
                            else throw new Error001("Operacion no conocida");
                        }
                    }
                }
            }
        }
        comandos++;
        }
    }
    
    public void crearAlmacen(String[] c) throws Error000, Error004 {
        
        if(c.length==2 && c[1]!= null  ){
            if(!Utilidad.listaAlmacenes.getAlmacenes().contains(c[1]) || Utilidad.listaAlmacenes.getAlmacenes().isEmpty())
            {
                this.almacen=new Almacen(c[1]);
                System.out.println("Se creo el almacen");
            }
                
            else{
                System.out.println("No se creo el almacen");
                throw new Error004("Operacion no realizable, ya exite el almacen");
            }
        }
        else throw new Error000("Comando mal formado");
    }
    public void guardarAlmacen()
    { 
        Utilidad.listaAlmacenes.cargarListaAlmacenes(this.almacen.getNombre());
        this.serializarAlmacen();
       
    }
    public void cargar(String[] c) throws Error004, Error000 {
        if(c.length==2 && c[1]!= null  ){
        if(Utilidad.listaAlmacenes.getAlmacenes().contains(c[1])){
            try {
                this.almacen = this.deserializarAlmacen(c[1]);
                System.out.println("Se deserealizo la lista");
            } catch (Error003 e) {
                setChanged();
                notifyObservers(new Notificacion(Utilidad.ERROR,e.getCartel()));
            }
        }
        else
            {
                throw new Error004("Operacion no realizable, no se encuentra el Almacen");
            }
        }
        else throw new Error000("Comando mal formado");
    }
    public void serializarAlmacen()
    {
        XMLEncoder encoder=null;
        try
        {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(this.almacen.getNombre()+".xml")));
           
            encoder.writeObject(this.almacen);
            encoder.close();
            System.out.print("se guardo el almacen\n");
            
        } catch (FileNotFoundException e)
        {
            System.out.print("no se guardo el almacen \n");
        }
    }
    public Almacen deserializarAlmacen(String nombre) throws Error003 {
           Almacen resp=null;
           XMLDecoder decoder=null;
           try
           {
               decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nombre+".xml")));
               resp=(Almacen)decoder.readObject();
               decoder.close();
               System.out.println("Se deserializo");
           } catch (FileNotFoundException e)
               {
                   System.out.println("No se cargo");
                   throw new Error003("Archivo Inexistente");
             
               }
           return resp;
       }
    public static void serializarLista()
    {
        XMLEncoder encoder=null;
        try
        {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(Utilidad.SERIALIZAR_LISTA)));
           
            encoder.writeObject(Utilidad.listaAlmacenes.getAlmacenes());
            encoder.close();
            System.out.print("se guardo el listado de almacenes\n");
            
        } catch (FileNotFoundException e)
        {
            System.out.print("no se guardo el listado de almacenes \n");
        }
    }
    public void deserializarLista() {
           XMLDecoder decoder=null;
           try
           {
               decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(Utilidad.SERIALIZAR_LISTA)));
               
               Utilidad.listaAlmacenes.setAlmacenes((ArrayList)decoder.readObject());
               decoder.close();
       } catch (FileNotFoundException e)
           {
           }
    }
}
