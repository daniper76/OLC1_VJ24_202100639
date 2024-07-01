/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Instrucciones;

import Errores.Errores;
import Expresiones.DatoNativo;
import IntruccionAbstracta.Instruccion;
import Simbolo.Arbol;
import Simbolo.Simbolo;
import Simbolo.TablaDeSimbolos;
import Simbolo.Tipo;
import Simbolo.TipoDeDato;

/**
 *
 * @author danie
 */
public class DeclaracionPorDefecto extends Instruccion {
    public String nombreVariable;
    public String mutabilidad;

    public DeclaracionPorDefecto(String nombreVariable,Tipo tipo,String mutabilidad, int linea, int columna) {
        super(tipo, linea, columna);
        this.nombreVariable = nombreVariable;
        this.mutabilidad=mutabilidad;
    }

    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var tipoDeVariable=this.tipo.getTipo();
        switch(tipoDeVariable){
            case INT->{
                Simbolo simbolo=new Simbolo(this.tipo,this.nombreVariable,0,this.mutabilidad);
                boolean existe=tabla.AgregarVariable(simbolo);
                if(!existe){
                    return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);
                }
            }
            case DOUBLE->{
                Simbolo simbolo=new Simbolo(this.tipo,this.nombreVariable,0.0,this.mutabilidad);
                boolean existe=tabla.AgregarVariable(simbolo);
                if(!existe){
                    return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);
                }
            }
            case CHAR->{
                Simbolo simbolo=new Simbolo(this.tipo,this.nombreVariable,'a',this.mutabilidad);
                boolean existe=tabla.AgregarVariable(simbolo);
                if(!existe){
                    return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);
                }
            }
            case STRING->{
                Simbolo simbolo=new Simbolo(this.tipo,this.nombreVariable,"",this.mutabilidad);
                boolean existe=tabla.AgregarVariable(simbolo);
                if(!existe){
                    return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);
                }
            }
            case BOOLEAN->{
                Simbolo simbolo=new Simbolo(this.tipo,this.nombreVariable,true,this.mutabilidad);
                boolean existe=tabla.AgregarVariable(simbolo);
                if(!existe){
                    return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);
                }
            }
            default->{
                return new Errores("Error Semántico","No Existe El Tipo De Variable", this.linea,this.columna);     
            }
        }
        return null;
    }
}
