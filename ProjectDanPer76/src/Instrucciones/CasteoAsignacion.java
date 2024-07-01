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
import Simbolo.TipoDeDato;

/**
 *
 * @author danie
 */
public class CasteoAsignacion extends Instruccion {
    private String Identificador;
    private Instruccion nuevoValor;
    private Tipo tipoCasteo;

    public CasteoAsignacion(String Identificador, Instruccion nuevoValor, Tipo tipoCasteo, int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.Identificador = Identificador;
        this.nuevoValor = nuevoValor;
        this.tipoCasteo = tipoCasteo;
    }
    
    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var valorInterpretado=this.nuevoValor.Interpretar(arbol, tabla);
        if(valorInterpretado instanceof Errores){
            return valorInterpretado;
        }
        Object valorCasteado=null;
        if(this.nuevoValor.tipo.getTipo()==TipoDeDato.INT){
            var tipo=this.tipoCasteo.getTipo();
            switch(tipo){
                case INT->{
                    var variable=tabla.ObtenerVariable(this.Identificador);
                    if(variable==null){
                        return new Errores("Error Semántico", "La Variable No Existe",
                                this.linea, this.columna);
                        }
                    var nuevo=this.nuevoValor.Interpretar(arbol, tabla);
                    if(nuevo instanceof Errores){
                        return nuevo;
                    }
                    valorCasteado=(int)nuevo;
                    if(variable.getTipo().getTipo() != TipoDeDato.INT){
                        return new Errores("Error Semántico", "Tipos De Datos No Coinciden",
                                this.linea, this.columna);
                    }
                    if(variable.getMutabilidad().equalsIgnoreCase("const")){
                        return new Errores("Error Semántico", "No Se Puede Modificar Una Variable Const", this.linea, this.columna);
                    }
                    variable.setValor(valorCasteado);
                    return null;
                }
                case DOUBLE->{
                    var variable=tabla.ObtenerVariable(this.Identificador);
                    if(variable==null){
                        return new Errores("Error Semántico", "La Variable No Existe",
                                this.linea, this.columna);
                        }
                    var nuevo=this.nuevoValor.Interpretar(arbol, tabla);
                    if(nuevo instanceof Errores){
                        return nuevo;
                    }
                    valorCasteado=(double)((int)nuevo);
                    if(variable.getTipo().getTipo() != TipoDeDato.DOUBLE){
                        return new Errores("Error Semántico", "Tipos De Datos No Coinciden",
                                this.linea, this.columna);
                    }
                    if(variable.getMutabilidad().equalsIgnoreCase("const")){
                        return new Errores("Error Semántico", "No Se Puede Modificar Una Variable Const", this.linea, this.columna);
                    }
                    variable.setValor(valorCasteado);
                    return null;
                }
                case CHAR->{
                    var variable=tabla.ObtenerVariable(this.Identificador);
                    if(variable==null){
                        return new Errores("Error Semántico", "La Variable No Existe",
                                this.linea, this.columna);
                        }
                    var nuevo=this.nuevoValor.Interpretar(arbol, tabla);
                    if(nuevo instanceof Errores){
                        return nuevo;
                    }
                    valorCasteado=(char)((int)nuevo);
                    if(variable.getTipo().getTipo() != TipoDeDato.CHAR){
                        return new Errores("Error Semántico", "Tipos De Datos No Coinciden",
                                this.linea, this.columna);
                    }
                    if(variable.getMutabilidad().equalsIgnoreCase("const")){
                        return new Errores("Error Semántico", "No Se Puede Modificar Una Variable Const", this.linea, this.columna);
                    }
                    variable.setValor(valorCasteado);
                    return null;
                }
                default->{
                    return new Errores("Error Semántico","Valor No Puede Ser Casteado A Este Tipo",this.linea,this.columna);
                }         
            }
        }else if(this.nuevoValor.tipo.getTipo()==TipoDeDato.DOUBLE){
            var tipo=this.tipoCasteo.getTipo();
            switch(tipo){
                case INT->{
                    var variable=tabla.ObtenerVariable(this.Identificador);
                    if(variable==null){
                        return new Errores("Error Semántico", "La Variable No Existe",
                                this.linea, this.columna);
                        }
                    var nuevo=this.nuevoValor.Interpretar(arbol, tabla);
                    if(nuevo instanceof Errores){
                        return nuevo;
                    }
                    valorCasteado=(int)((double)nuevo);
                    if(variable.getTipo().getTipo() != TipoDeDato.INT){
                        return new Errores("Error Semántico", "Tipos De Datos No Coinciden",
                                this.linea, this.columna);
                    }
                    if(variable.getMutabilidad().equalsIgnoreCase("const")){
                        return new Errores("Error Semántico", "No Se Puede Modificar Una Variable Const", this.linea, this.columna);
                    }
                    variable.setValor(valorCasteado);
                    return null;
                }
                case DOUBLE->{
                    var variable=tabla.ObtenerVariable(this.Identificador);
                    if(variable==null){
                        return new Errores("Error Semántico", "La Variable No Existe",
                                this.linea, this.columna);
                        }
                    var nuevo=this.nuevoValor.Interpretar(arbol, tabla);
                    if(nuevo instanceof Errores){
                        return nuevo;
                    }
                    valorCasteado=(double)nuevo;
                    if(variable.getTipo().getTipo() != TipoDeDato.DOUBLE){
                        return new Errores("Error Semántico", "Tipos De Datos No Coinciden",
                                this.linea, this.columna);
                    }
                    if(variable.getMutabilidad().equalsIgnoreCase("const")){
                        return new Errores("Error Semántico", "No Se Puede Modificar Una Variable Const", this.linea, this.columna);
                    }
                    variable.setValor(valorCasteado);
                    return null;
                }
                default->{
                    return new Errores("Error Semántico","Valor No Puede Ser Casteado A Este Tipo",this.linea,this.columna);
                }
            } 
        }else if(this.nuevoValor.tipo.getTipo()==TipoDeDato.CHAR){
            var tipo=this.tipoCasteo.getTipo();
            switch(tipo){
                case INT->{
                    var variable=tabla.ObtenerVariable(this.Identificador);
                    if(variable==null){
                        return new Errores("Error Semántico", "La Variable No Existe",
                                this.linea, this.columna);
                        }
                    var nuevo=this.nuevoValor.Interpretar(arbol, tabla);
                    if(nuevo instanceof Errores){
                        return nuevo;
                    }
                    valorCasteado=(int)(nuevo.toString().charAt(0));
                    if(variable.getTipo().getTipo() != TipoDeDato.INT){
                        return new Errores("Error Semántico", "Tipos De Datos No Coinciden",
                                this.linea, this.columna);
                    }
                    if(variable.getMutabilidad().equalsIgnoreCase("const")){
                        return new Errores("Error Semántico", "No Se Puede Modificar Una Variable Const", this.linea, this.columna);
                    }
                    variable.setValor(valorCasteado);
                    return null;
                }
                case DOUBLE->{
                    var variable=tabla.ObtenerVariable(this.Identificador);
                    if(variable==null){
                        return new Errores("Error Semántico", "La Variable No Existe",
                                this.linea, this.columna);
                        }
                    var nuevo=this.nuevoValor.Interpretar(arbol, tabla);
                    if(nuevo instanceof Errores){
                        return nuevo;
                    }
                    valorCasteado=(double)((int)(nuevo.toString().charAt(0)));
                    if(variable.getTipo().getTipo() != TipoDeDato.DOUBLE){
                        return new Errores("Error Semántico", "Tipos De Datos No Coinciden",
                                this.linea, this.columna);
                    }
                    if(variable.getMutabilidad().equalsIgnoreCase("const")){
                        return new Errores("Error Semántico", "No Se Puede Modificar Una Variable Const", this.linea, this.columna);
                    }
                    variable.setValor(valorCasteado);
                    return null;
                }
                case CHAR->{
                    var variable=tabla.ObtenerVariable(this.Identificador);
                    if(variable==null){
                        return new Errores("Error Semántico", "La Variable No Existe",
                                this.linea, this.columna);
                        }
                    var nuevo=this.nuevoValor.Interpretar(arbol, tabla);
                    if(nuevo instanceof Errores){
                        return nuevo;
                    }
                    valorCasteado=nuevo.toString().charAt(0);
                    if(variable.getTipo().getTipo() != TipoDeDato.CHAR){
                        return new Errores("Error Semántico", "Tipos De Datos No Coinciden",
                                this.linea, this.columna);
                    }
                    if(variable.getMutabilidad().equalsIgnoreCase("const")){
                        return new Errores("Error Semántico", "No Se Puede Modificar Una Variable Const", this.linea, this.columna);
                    }
                    variable.setValor(valorCasteado);
                    return null;
                }
                default->{
                    return new Errores("Error Semántico","Valor No Puede Ser Casteado A Este Tipo",this.linea,this.columna);
                }
            }
        }else{
            return new Errores("Error Semántico","Valor Casteado No Admitido",this.linea,this.columna);
        }
    }
    
}
