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
public class Decremento extends Instruccion {
    private String Identificador;

    public Decremento(String Identificador, int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.Identificador = Identificador;
    }
    
    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var variable=tabla.ObtenerVariable(this.Identificador);
        if(variable==null){
            return new Errores("Error Semántico", "La Variable No Existe", this.linea, this.columna);
        }
        if((variable.getTipo().getTipo() != TipoDeDato.INT)&&(variable.getTipo().getTipo() != TipoDeDato.DOUBLE)){
            return new Errores("Error Semántico", "Tipos De Datos No Coinciden", this.linea, this.columna);
        }
        if(variable.getMutabilidad().equalsIgnoreCase("const")){
            return new Errores("Error Semántico", "No Se Puede Modificar Una Variable Const", this.linea, this.columna);
        }
        Object nuevo=null;
        if(variable.getTipo().getTipo() == TipoDeDato.INT){
            nuevo=(int)variable.getValor()-(int)1;
        }
        if(variable.getTipo().getTipo() == TipoDeDato.DOUBLE){
            nuevo=(double)variable.getValor()-(double)1;
        }
        variable.setValor(nuevo);
        return null;
    }
    
}
