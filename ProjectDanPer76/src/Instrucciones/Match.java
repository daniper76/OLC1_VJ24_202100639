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
public class Match extends Instruccion {
    private Instruccion condicion;
    private LinkedList<ObjetoMatch> opciones;

    public Match(Instruccion condicion, LinkedList<ObjetoMatch> opciones, int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.condicion = condicion;
        this.opciones = opciones;
    }

    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var condicionMatch=this.condicion.Interpretar(arbol, tabla);
        if (condicionMatch instanceof Errores){
            return condicionMatch;
        }
        var NuevaTabla= new TablaDeSimbolos(tabla);
        for(var i:this.opciones){
           var opcionComparar=i.getOpcion().Interpretar(arbol, NuevaTabla);
           if(condicionMatch.toString().equalsIgnoreCase(opcionComparar.toString())){
               var instruccionesMatch=i.getInstrucciones();
               for(var j:instruccionesMatch){
                    var matchvariable=j.Interpretar(arbol, NuevaTabla);
                    if(matchvariable instanceof Errores){
                    arbol.getErrores().add((Errores)matchvariable);
                    }
                }
            return null;
            }
        }
        return null;
    } 
}
