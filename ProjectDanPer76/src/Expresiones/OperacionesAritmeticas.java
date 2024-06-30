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
public class OperacionesAritmeticas extends Instruccion {
    private Instruccion numeroUno;
    private Instruccion numeroDos;
    private Instruccion numeroUnico;
    private SimbolosAritmeticos operacion;

    public OperacionesAritmeticas(Instruccion numeroUno, Instruccion numeroDos, SimbolosAritmeticos operacion, int linea, int columna) {
        super(new Tipo(TipoDeDato.INDEFINIDO), linea, columna);
        this.numeroUno = numeroUno;
        this.numeroDos = numeroDos;
        this.operacion = operacion;
    }

    public OperacionesAritmeticas(Instruccion numeroUnico, SimbolosAritmeticos operacion, int linea, int columna) {
        super(new Tipo(TipoDeDato.INDEFINIDO), linea, columna);
        this.numeroUnico = numeroUnico;
        this.operacion = operacion;
    }
    
    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        Object numeroIzquierdo=null;
        Object numeroDerecho=null;
        Object unicoNumero=null;
        if(this.numeroUnico!=null){
            unicoNumero=this.numeroUnico.Interpretar(arbol, tabla);
            if(unicoNumero instanceof Errores){
                return unicoNumero;
            }
        }else{
            numeroIzquierdo=this.numeroUno.Interpretar(arbol, tabla);
            if(numeroIzquierdo instanceof Errores){
                return numeroIzquierdo;
            }
            numeroDerecho=this.numeroDos.Interpretar(arbol, tabla);
            if(numeroDerecho instanceof Errores){
                return numeroDerecho;
            }
        }
        switch(operacion){
            case SUMA -> {
                return this.Suma(numeroIzquierdo, numeroDerecho);
            }
            case NEGACION -> {
                return this.Negacion(unicoNumero);
            }
            default ->{
                return new Errores("Error Semántico", "Operador Aritmetico Utilizado No Permitido", this.linea, this.columna);    
            }
                
        
        }
        
        
    }
    
    public Object Suma(Object sumando1, Object sumando2){
        var tipoSumando1=this.numeroUno.tipo.getTipo();
        var tipoSumando2=this.numeroDos.tipo.getTipo();
        switch(tipoSumando1){
            case INT ->{
                switch(tipoSumando2){
                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.INT);
                        return (int) sumando1 + (int) sumando2;
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return (int) sumando1 + (double) sumando2;
                    }
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.STRING);
                        return sumando1.toString()+sumando2.toString();
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
            
            }
            case DOUBLE ->{
                switch(tipoSumando2){
                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return (double) sumando1 + (int) sumando2;
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return (double) sumando1 + (double) sumando2;
                    }
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.STRING);
                        return sumando1.toString()+sumando2.toString();
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
           
            }
            case STRING ->{
                switch(tipoSumando2){
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.STRING);
                        return sumando1.toString()+sumando2.toString();
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho De Tipo Incorrecto",this.linea,this.columna);
                    }
                }
            }
            default -> {
                 return new Errores("Error Semántico","Sumando Izquierdo De Tipo Incorrecto",this.linea,this.columna);   
            }
        }
    }
    
    public Object Negacion(Object numero){
        var tipoNumero=this.numeroUnico.tipo.getTipo();
        switch(tipoNumero){
            case INT->{
                this.tipo.setTipo(TipoDeDato.INT);
                return (int) numero*-1;
            }
            case DOUBLE->{
                this.tipo.setTipo(TipoDeDato.DOUBLE);
                return (double) numero*-1;
            }
            default->{
                return new Errores("Error Semántico","Tipo De Dato Erroneo Para Obtener Su Negativo",this.linea,this.columna);
            }
        
        }
    }
}
