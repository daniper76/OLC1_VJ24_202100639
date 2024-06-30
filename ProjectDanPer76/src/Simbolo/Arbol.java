/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simbolo;

import Errores.Errores;
import IntruccionAbstracta.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class Arbol {
    private LinkedList<Instruccion> instrucciones;
    private String cadenaResultado;
    private TablaDeSimbolos tablaGlobal;
    private LinkedList<Errores> errores;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.cadenaResultado="";
        this.tablaGlobal=new TablaDeSimbolos();
        this.errores= new LinkedList<>();
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public String getCadenaResultado() {
        return cadenaResultado;
    }

    public TablaDeSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public void setCadenaResultado(String cadenaResultado) {
        this.cadenaResultado = cadenaResultado;
    }

    public void setTablaGlobal(TablaDeSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }
        public void CrearCadenaConsola(String valor) {
        this.cadenaResultado += valor + "\n";
    }
    
}
