package modelo;

import java.util.ArrayList;

public class ListaAlmacenes
{
    private static ArrayList<String>almacenes;
    
    public ListaAlmacenes()
    {  
        super();
        this.almacenes = new ArrayList<String>();
    }
    
    public void cargarListaAlmacenes(String nombre)
    {
        this.almacenes.add(nombre);
    }

    public static void setAlmacenes(ArrayList<String> almacenes)
    {
        ListaAlmacenes.almacenes = almacenes;
    }

    public static ArrayList<String> getAlmacenes()
    {
        return almacenes;
    }
}
