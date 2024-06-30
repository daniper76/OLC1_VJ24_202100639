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
public class AsignacionVariable extends Instruccion {
    private String Identificador;
    private Instruccion nuevoValor;

    public AsignacionVariable(String Identificador, Instruccion nuevoValor, int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.Identificador = Identificador;
        this.nuevoValor = nuevoValor;
    }

    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var variable=tabla.ObtenerVariable(this.Identificador);
        if(variable==null){
            return new Errores("Error Semántico", "La Variable No Existe",
                    this.linea, this.columna);
            }
        var nuevo=this.nuevoValor.Interpretar(arbol, tabla);
        if(nuevo instanceof Errores){
            return nuevo;
        }
        if(variable.getTipo().getTipo() != this.nuevoValor.tipo.getTipo()){
            return new Errores("Error Semántico", "Tipos De Datos No Coinciden",
                    this.linea, this.columna);
        }
        variable.setValor(nuevo);
        return null;
     }
        
    
    
}
