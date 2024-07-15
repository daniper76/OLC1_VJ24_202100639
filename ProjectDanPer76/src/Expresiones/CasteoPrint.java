/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Expresiones;

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
public class CasteoPrint extends Instruccion{
    private Instruccion valor;
    private Tipo tipoCasteo;
    
    public CasteoPrint(Instruccion valor, Tipo tipoCasteo,int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.valor = valor;
        this.tipoCasteo = tipoCasteo;
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
                    this.tipo.setTipo(TipoDeDato.INT);
                    return valorCasteado;
                }
                case DOUBLE->{
                    valorCasteado=(double) ((int)this.valor.Interpretar(arbol, tabla));
                    this.tipo.setTipo(TipoDeDato.DOUBLE);
                    return valorCasteado;
                }
                case CHAR->{
                    valorCasteado=(char)((int)this.valor.Interpretar(arbol, tabla));
                    this.tipo.setTipo(TipoDeDato.CHAR);
                    return valorCasteado;
                }
                default->{
                    return new Errores("Error Semántico","Valor Int No Puede Ser Casteado A Este Tipo",this.linea,this.columna);
                }         
            }
        }else if(this.valor.tipo.getTipo()==TipoDeDato.DOUBLE){
            var tipo=this.tipoCasteo.getTipo();
            switch(tipo){
                case INT->{
                    valorCasteado=(int) ((double)this.valor.Interpretar(arbol, tabla));
                    this.tipo.setTipo(TipoDeDato.INT);
                    return valorCasteado;
                }
                case DOUBLE->{
                    valorCasteado=(double)(this.valor.Interpretar(arbol, tabla));
                    this.tipo.setTipo(TipoDeDato.DOUBLE);
                    return valorCasteado;
                }
                default->{
                    return new Errores("Error Semántico","Valor Double No Puede Ser Casteado A Este Tipo",this.linea,this.columna);
                }
            } 
        }else if(this.valor.tipo.getTipo()==TipoDeDato.CHAR){
            var tipo=this.tipoCasteo.getTipo();
            switch(tipo){
                case INT->{
                    valorCasteado=(int)(this.valor.Interpretar(arbol, tabla).toString().charAt(0));
                    this.tipo.setTipo(TipoDeDato.INT);
                    return valorCasteado;
                }
                case DOUBLE->{
                    valorCasteado=(double) ((int)(this.valor.Interpretar(arbol, tabla).toString().charAt(0)));
                    this.tipo.setTipo(TipoDeDato.DOUBLE);
                    return valorCasteado;
                }
                case CHAR->{
                    valorCasteado=this.valor.Interpretar(arbol, tabla).toString().charAt(0); 
                    this.tipo.setTipo(TipoDeDato.CHAR);
                    return valorCasteado;
                }
                default->{
                    return new Errores("Error Semántico","Valor Char No Puede Ser Casteado A Este Tipo",this.linea,this.columna);
                }
            }
        }else{
            return new Errores("Error Semántico","Valor Casteado No Admitido",this.linea,this.columna);
        }
    }

}
