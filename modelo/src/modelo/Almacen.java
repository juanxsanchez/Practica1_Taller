package modelo;

import java.beans.XMLEncoder;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Almacen {
    private HashMap<String,Analisis>analisis= new HashMap<String,Analisis>();
    private ArrayList<Analisis>analisisLista= new ArrayList<Analisis>();
    private String nombre;
    
    public Almacen(String nombre) {
        super();
        this.nombre=nombre;
    }
    public Almacen(){};
    
    public void insertar(String[] c) throws Error007 {
       //puede venir un dato XML que no exista
       String lineaXML=c[1];
       parserxml p=new parserxml();
        System.out.println("PRUEBA"+c[1]);
       
        try {
            String[][] lista = p.leoArgumento(lineaXML);
            if(!this.analisis.containsKey(lista[0][2])){
            System.out.println(lista[7][2]);
            ArrayList<Estudio>estudios=new ArrayList<Estudio>();
            for(int i=0;i<Integer.parseInt(lista[6][2]);i++)
            {   
                estudios.add(new Estudio(lista[7+2*i][2],Integer.parseInt(lista[8+2*i][2])));   
                System.out.println(lista[7+2*i][2]+" "+Integer.parseInt(lista[8+2*i][2]));
               
                System.out.println("Se inserto correctamente.");
            }
            Analisis analisis=new Analisis(lista[0][2],lista[1][2],lista[2][2],lista[3][2],lista[4][2],lista[5][2],estudios);
            this.analisis.put(analisis.getId(), analisis);
            }
                else{throw new Error007("Analisis ya existe");}
            }
         catch (IOException | ParserConfigurationException | SAXException e) {
        }
        
       
        
        
    }

    public ArrayList<Analisis> getAnalisisLista() {
        return analisisLista;
    }

    public void eliminar(String[] c) throws Error005 {
       
        if(this.analisis.containsKey(c[1])){
             this.analisis.remove(c[1]);
            System.out.println("Se elimino correctamente.");
        }
        else
            {
            throw new Error005("Error 005: dato inexistente");
            }
                
    }
    
    public String consultar(String[] c) throws Error002 {
        double valor;
        String consulta=null;
        if(c.length==4 || c.length==6)
        {
            System.out.println("puede consultar");
            System.out.println(c[2]);
            if(c[2].equals("==") || c[2].equals("!=") || c[2].equals(">") || c[2].equals("<") || c[2].equals(">=") || c[2].equals("<=") )
            {
                
                if(this.esNumeroValido(c[3]))
                {
                    valor= Double.parseDouble(c[3]);
                    System.out.println("Valor a comparar para consulta: "+valor);
                    //consulta += "Valor a comparar para consulta: "+valor;
                    Iterator it=this.analisis.entrySet().iterator();
                    while(it.hasNext())
                    {
                        Map.Entry map=(Map.Entry)it.next();
                        Analisis analisis=(Analisis)map.getValue();
                        Iterator itEst=analisis.getEstudios().iterator();
                        while(itEst.hasNext()){
                            Estudio estudio=(Estudio)itEst.next();
                        if(c[1].equalsIgnoreCase(estudio.getTipo()))
                        {
                            System.out.println("coinciden en estudios");
                            
                            if(c[2].equals("==")){
                                if(valor == estudio.getValor()){
                                    System.out.println("coinciden en valor");
                                    if(consulta==null)
                                        consulta="Estudio: "+estudio.getTipo()+" con valores iguales a: "+valor+"\n";
                                    consulta+=analisis.getApellido()+"  "+analisis.getNombre()+"\n";
                                }
                            }
                            else{
                                if(c[2].equals("!=")){
                                    if(valor != estudio.getValor()){
                                        if(consulta==null)
                                            consulta="Estudio: "+estudio.getTipo()+" con valores distintos a: "+valor+"\n";
                                        consulta+=analisis.getApellido()+"  "+analisis.getNombre()+"\n";
                                    }
                                }
                            
                                else{                            
                                    if(c[2].equals(">")){
                                        if(estudio.getValor()>valor){
                                            if(consulta==null)
                                                consulta="Estudio: "+estudio.getTipo()+" con valores mayores a: "+valor+"\n";
                                            consulta+=analisis.getApellido()+"  "+analisis.getNombre()+"\n";
                                        }
                                     }
                                    else
                                    {
                                        if(c[2].equals("<")){
                                            if(estudio.getValor()<valor){
                                                if(consulta==null)
                                                    consulta="Estudio: "+estudio.getTipo()+" con valores menores a: "+valor+"\n";
                                                consulta+=analisis.getApellido()+"  "+analisis.getNombre()+"\n";
                                            }
                                        }
                                        else
                                        {
                                            if(c[2].equals(">=")){
                                                if(estudio.getValor()>=valor){
                                                    if(consulta==null)
                                                        consulta="Estudio: "+estudio.getTipo()+" con valores mayores o iguales a: "+valor+"\n";
                                                    consulta+=analisis.getApellido()+"  "+analisis.getNombre()+"\n";
                                                }
                                            }
                                            else
                                            {
                                                if(estudio.getValor()<=valor)
                                                { 
                                                    if(consulta==null)
                                                        consulta="Estudio: "+estudio.getTipo()+" con valores menores o iguales a: "+valor+"\n";
                                                    consulta+=analisis.getApellido()+"  "+analisis.getNombre()+"\n";
                                                }
                                            }
                                        }
                                    }
                                    }
                                    
                                    
                            }   
                        }
                        }
                    }
                    if(consulta!=null)
                        if(c.length==6)
                        {
                            if(c[4].equalsIgnoreCase("tofile"))
                            {
                                this.serializarConsulta(consulta, c[5]);
                            }
                            else
                                throw new Error002("Consulta mal construida");
                        
                        }
                }
                else {
                        throw new Error002("Consulta mal construida");
                    }//no se ingreso un valor numerico
            }
            else{
                    throw new Error002("Consulta mal construida");
                }//operacion desconocido
        }
        else throw new Error002("Consulta mal construida");
        System.out.println(consulta);
        return consulta;
    }

    public void setAnalisis(HashMap<String, Analisis> analisis) {
        this.analisis = analisis;
    }

    public void setAnalisisLista(ArrayList<Analisis> analisisLista) {
        this.analisisLista = analisisLista;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void serializarConsulta(String s,String nombreArch)
        {
            XMLEncoder encoder=null;
            try
            {
                encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nombreArch + ".xml")));
               
                encoder.writeObject(s);
                encoder.close();
                System.out.print("se almaceno la consulta\n");
                
            } catch (FileNotFoundException e)
            {
                System.out.print("no se almaceno la consulta");
            }
        }

    public HashMap<String, Analisis> getAnalisis() {
        return analisis;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esNumeroValido(String cad)
     {
     try
     {
       Double.parseDouble(cad);
       return true;
     }
     catch(NumberFormatException nfe)
     {
       return false;
     }
     }
}
