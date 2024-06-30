/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simbolo;

import java.util.HashMap;

/**
 *
 * @author danie
 */
public class TablaDeSimbolos {
    private TablaDeSimbolos tablaAnterior;
    private HashMap<String,Object> tablaActual;
    private String nombre;

    public TablaDeSimbolos() {
        this.tablaActual=new HashMap();
        this.nombre="";
    }

    public TablaDeSimbolos(TablaDeSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
        this.tablaActual=new HashMap<>();
        this.nombre="";
    }

    public TablaDeSimbolos getTablaAnterior() {
        return tablaAnterior;
    }

    public HashMap<String, Object> getTablaActual() {
        return tablaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTablaAnterior(TablaDeSimbolos tablaAnterior) {
        this.tablaAnterior = tablaAnterior;
    }

    public void setTablaActual(HashMap<String, Object> tablaActual) {
        this.tablaActual = tablaActual;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean AgregarVariable(Simbolo simbolo){
        Simbolo existe= (Simbolo)this.tablaActual.get(simbolo.getId().toLowerCase());
        if(existe==null){
            this.tablaActual.put(simbolo.getId().toLowerCase(),simbolo);
            return true;
        }
        return false;
    }
    
    public Simbolo ObtenerVariable(String identificador){
        for(TablaDeSimbolos i=this;i !=null;i=i.getTablaAnterior()){
            Simbolo valorBuscado=(Simbolo) i.tablaActual.get(identificador.toLowerCase());
            if(valorBuscado != null){
                return valorBuscado;
            }
        }
        return null;
    }
    
    
    
}
