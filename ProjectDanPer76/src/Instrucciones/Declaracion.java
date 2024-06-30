/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instrucciones;

import Errores.Errores;
import IntruccionAbstracta.Instruccion;
import Simbolo.Arbol;
import Simbolo.Simbolo;
import Simbolo.TablaDeSimbolos;
import Simbolo.Tipo;

/**
 *
 * @author danie
 */
public class Declaracion extends Instruccion {
    public String nombreVariable;
    public Instruccion valor;

    public Declaracion(String nombreVariable, Instruccion valor, Tipo tipo, int linea, int columna) {
        super(tipo, linea, columna);
        this.nombreVariable = nombreVariable;
        this.valor = valor;
    }

    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var valorInterpretado=this.valor.Interpretar(arbol, tabla);
        if(valorInterpretado instanceof Errores){
            return valorInterpretado;
        }
        if(this.valor.tipo.getTipo() != this.tipo.getTipo()){
            return new Errores("Error Semántico","Declaración De Variable Errónea, No Coinciden Tipo De Variable Con Tipo De Valor", this.linea,this.columna);
        }
        Simbolo simbolo=new Simbolo(this.tipo,this.nombreVariable,valorInterpretado);
        boolean existe=tabla.AgregarVariable(simbolo);
        if(!existe){
            return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);

        }
        return null;
    }
    
    
}
