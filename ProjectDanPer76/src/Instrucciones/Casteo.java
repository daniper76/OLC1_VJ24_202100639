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
public class Casteo extends Instruccion {
    private String nombreDeVariable;
    private Instruccion valor;
    private Tipo tipoCasteo;
    private String mutabilidad;

    public Casteo(String nombreDeVariable, Instruccion valor, Tipo tipoCasteo, Tipo tipo,String mutabilidad, int linea, int columna) {
        super(tipo, linea, columna);
        this.nombreDeVariable = nombreDeVariable;
        this.valor = valor;
        this.tipoCasteo = tipoCasteo;
        this.mutabilidad=mutabilidad;
    }
    
    
    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var valorInterpretado=this.valor.Interpretar(arbol, tabla);
        if(valorInterpretado instanceof Errores){
            return valorInterpretado;
        }
        Object valorCasteado=null;
        if(this.valor.tipo.getTipo()==TipoDeDato.INT){
            var tipo=this.tipoCasteo.getTipo();
            switch(tipo){
                case INT->{
                    valorCasteado=(int)(this.valor.Interpretar(arbol, tabla));
                    if(this.tipoCasteo.getTipo() != this.tipo.getTipo()){
                        return new Errores("Error Semántico","Declaración De Variable Errónea, No Coinciden Tipo De Variable Con Tipo De Valor", this.linea,this.columna);
                    }
                    Simbolo simbolo=new Simbolo(this.tipo,this.nombreDeVariable,valorCasteado,this.mutabilidad);
                    boolean existe=tabla.AgregarVariable(simbolo);
                    if(!existe){
                        return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);

                    }
                    return null;
                }
                case DOUBLE->{
                    valorCasteado=(double) ((int)this.valor.Interpretar(arbol, tabla));
                    if(this.tipoCasteo.getTipo() != this.tipo.getTipo()){
                        return new Errores("Error Semántico","Declaración De Variable Errónea, No Coinciden Tipo De Variable Con Tipo De Valor", this.linea,this.columna);
                    }
                    Simbolo simbolo=new Simbolo(this.tipo,this.nombreDeVariable,valorCasteado,this.mutabilidad);
                    boolean existe=tabla.AgregarVariable(simbolo);
                    if(!existe){
                        return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);

                    }
                    return null;
                }
                case CHAR->{
                    valorCasteado=(char)((int)this.valor.Interpretar(arbol, tabla));
                    if(this.tipoCasteo.getTipo() != this.tipo.getTipo()){
                        return new Errores("Error Semántico","Declaración De Variable Errónea, No Coinciden Tipo De Variable Con Tipo De Valor", this.linea,this.columna);
                    }
                    Simbolo simbolo=new Simbolo(this.tipo,this.nombreDeVariable,valorCasteado,this.mutabilidad);
                    boolean existe=tabla.AgregarVariable(simbolo);
                    if(!existe){
                        return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);

                    }
                    return null;
                }
                default->{
                    return new Errores("Error Semántico","Valor No Puede Ser Casteado A Este Tipo",this.linea,this.columna);
                }         
            }
        }else if(this.valor.tipo.getTipo()==TipoDeDato.DOUBLE){
            var tipo=this.tipoCasteo.getTipo();
            switch(tipo){
                case INT->{
                    valorCasteado=(int) ((double)this.valor.Interpretar(arbol, tabla));
                    if(this.tipoCasteo.getTipo() != this.tipo.getTipo()){
                        return new Errores("Error Semántico","Declaración De Variable Errónea, No Coinciden Tipo De Variable Con Tipo De Valor", this.linea,this.columna);
                    }
                    Simbolo simbolo=new Simbolo(this.tipo,this.nombreDeVariable,valorCasteado,this.mutabilidad);
                    boolean existe=tabla.AgregarVariable(simbolo);
                    if(!existe){
                        return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);

                    }
                    return null;
                }
                case DOUBLE->{
                    valorCasteado=(double)(this.valor.Interpretar(arbol, tabla));
                    if(this.tipoCasteo.getTipo() != this.tipo.getTipo()){
                        return new Errores("Error Semántico","Declaración De Variable Errónea, No Coinciden Tipo De Variable Con Tipo De Valor", this.linea,this.columna);
                    }
                    Simbolo simbolo=new Simbolo(this.tipo,this.nombreDeVariable,valorCasteado,this.mutabilidad);
                    boolean existe=tabla.AgregarVariable(simbolo);
                    if(!existe){
                        return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);

                    }
                    return null;
                }
                default->{
                    return new Errores("Error Semántico","Valor No Puede Ser Casteado A Este Tipo",this.linea,this.columna);
                }
            } 
        }else if(this.valor.tipo.getTipo()==TipoDeDato.CHAR){
            var tipo=this.tipoCasteo.getTipo();
            switch(tipo){
                case INT->{
                    valorCasteado=(int)(this.valor.Interpretar(arbol, tabla).toString().charAt(0));
                    if(this.tipoCasteo.getTipo() != this.tipo.getTipo()){
                        return new Errores("Error Semántico","Declaración De Variable Errónea, No Coinciden Tipo De Variable Con Tipo De Valor", this.linea,this.columna);
                    }
                    Simbolo simbolo=new Simbolo(this.tipo,this.nombreDeVariable,valorCasteado,this.mutabilidad);
                    boolean existe=tabla.AgregarVariable(simbolo);
                    if(!existe){
                        return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);

                    }
                    return null;
                }
                case DOUBLE->{
                    valorCasteado=(double) ((int)(this.valor.Interpretar(arbol, tabla).toString().charAt(0)));
                    if(this.tipoCasteo.getTipo() != this.tipo.getTipo()){
                        return new Errores("Error Semántico","Declaración De Variable Errónea, No Coinciden Tipo De Variable Con Tipo De Valor", this.linea,this.columna);
                    }
                    Simbolo simbolo=new Simbolo(this.tipo,this.nombreDeVariable,valorCasteado,this.mutabilidad);
                    boolean existe=tabla.AgregarVariable(simbolo);
                    if(!existe){
                        return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);

                    }
                    return null;
                }
                case CHAR->{
                    valorCasteado=this.valor.Interpretar(arbol, tabla).toString().charAt(0); 
                    if(this.tipoCasteo.getTipo() != this.tipo.getTipo()){
                        return new Errores("Error Semántico","Declaración De Variable Errónea, No Coinciden Tipo De Variable Con Tipo De Valor", this.linea,this.columna);
                    }
                    Simbolo simbolo=new Simbolo(this.tipo,this.nombreDeVariable,valorCasteado,this.mutabilidad);
                    boolean existe=tabla.AgregarVariable(simbolo);
                    if(!existe){
                        return new Errores("Error Semántico","Declaración No Completa, Nombre De Variable Ya Utilizado", this.linea,this.columna);

                    }
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
