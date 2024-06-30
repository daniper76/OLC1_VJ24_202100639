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

/**
 *
 * @author danie
 */
public class Prinln extends Instruccion {
    private Instruccion expresion;

    public Prinln(Instruccion expresion,int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.expresion = expresion;
    }

    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
       var valorObtenido=this.expresion.Interpretar(arbol, tabla);
       if(valorObtenido instanceof Errores){
           return valorObtenido;
       }
       arbol.CrearCadenaConsola(valorObtenido.toString());
       return null;
    }
    
}
