/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instrucciones;

import Errores.Errores;
import IntruccionAbstracta.Instruccion;
import Simbolo.Arbol;
import Simbolo.TablaDeSimbolos;
import Simbolo.Tipo;
import Simbolo.TipoDeDato;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class Default extends Instruccion {
    private LinkedList<Instruccion> instruccionesDefault;

    public Default(LinkedList<Instruccion> instruccionesDefault,int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.instruccionesDefault = instruccionesDefault;
    }

    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var NuevaTabla= new TablaDeSimbolos(tabla);
        for(var j:instruccionesDefault){
            var matchvariable=j.Interpretar(arbol, NuevaTabla);
            if(matchvariable instanceof Errores){
                arbol.getErrores().add((Errores)matchvariable);
            }
        }
       return null;
    }   
}
