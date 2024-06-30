/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instrucciones;

import IntruccionAbstracta.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class ObjetoMatch {
    private Instruccion opcion;
    private LinkedList<Instruccion> instrucciones;

    public ObjetoMatch(Instruccion opcion, LinkedList<Instruccion> instrucciones) {
        this.opcion = opcion;
        this.instrucciones = instrucciones;
    }

    public Instruccion getOpcion() {
        return opcion;
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setOpcion(Instruccion opcion) {
        this.opcion = opcion;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }
    
}
