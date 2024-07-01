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
            case RESTA -> {
                return this.Resta(numeroIzquierdo, numeroDerecho);
            }
            case MULTIPLICACION -> {
                return this.Multiplicacion(numeroIzquierdo, numeroDerecho);
            }
            case DIVISION -> {
                return this.Division(numeroIzquierdo, numeroDerecho);
            }
            case POTENCIA -> {
                return this.Potencia(numeroIzquierdo, numeroDerecho);
            }
            case MODULO -> {
                return this.Modulo(numeroIzquierdo, numeroDerecho);
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
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.INT);
                        char caracter=sumando2.toString().charAt(0);
                        return (int) sumando1 + (int) caracter;
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
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=sumando2.toString().charAt(0);
                        return (double) sumando1 + (double)((int)caracter);
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
           
            }
            case STRING ->{
                switch(tipoSumando2){
                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.STRING);
                        return sumando1.toString()+sumando2.toString();
                    }
                    case DOUBLE -> {
                        this.tipo.setTipo(TipoDeDato.STRING);
                        return sumando1.toString()+sumando2.toString();
                    }
                    case CHAR-> {
                        this.tipo.setTipo(TipoDeDato.STRING);
                        return sumando1.toString()+sumando2.toString();
                    }
                    case BOOLEAN-> {
                        this.tipo.setTipo(TipoDeDato.STRING);
                        return sumando1.toString()+sumando2.toString();
                    }
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.STRING);
                        return sumando1.toString()+sumando2.toString();
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho De Tipo Incorrecto",this.linea,this.columna);
                    }
                }
            }
            case BOOLEAN ->{
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
            case CHAR ->{
                switch(tipoSumando2){
                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.INT);
                        char caracter=sumando1.toString().charAt(0);
                        return (int) caracter + (int) sumando2;
                    }
                    case DOUBLE -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=sumando1.toString().charAt(0);
                        return (int) caracter + (double)sumando2;
                    }
                    case CHAR-> {
                        this.tipo.setTipo(TipoDeDato.STRING);
                        return sumando1.toString()+sumando2.toString();
                    }
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
    
    public Object Resta(Object restando1, Object restando2){
        var tipoRestando1=this.numeroUno.tipo.getTipo();
        var tipoRestando2=this.numeroDos.tipo.getTipo();
        switch(tipoRestando1){

            case INT ->{
                switch(tipoRestando2){

                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.INT);
                        return (int) restando1 - (int) restando2;
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return (int) restando1 - (double) restando2;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.INT);
                        char caracter=restando2.toString().charAt(0);
                        return (int) restando1 - (int) caracter;
                    }
                    default ->{
                        return new Errores("Error Semántico","Restando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
            
            }
            case DOUBLE ->{
                switch(tipoRestando2){

                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return (double) restando1 - (int) restando2;
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return (double) restando1 - (double) restando2;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=restando2.toString().charAt(0);
                        return (double) restando1 - (double)((int)caracter);
                    }
                    default ->{
                        return new Errores("Error Semántico","Restando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
           
            }
            case CHAR ->{
                switch(tipoRestando2){

                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.INT);
                        char caracter=restando1.toString().charAt(0);
                        return (int) caracter - (int) restando2;
                    }
                    case DOUBLE -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=restando1.toString().charAt(0);
                        return (int) caracter - (double)restando2;
                    }
                    default ->{
                        return new Errores("Error Semántico","Restando Derecho De Tipo Incorrecto",this.linea,this.columna);
                    }
                }
            }
            default -> {
                 return new Errores("Error Semántico","Restando Izquierdo De Tipo Incorrecto",this.linea,this.columna);   
            }
        }
    }
    
    public Object Multiplicacion(Object multiplicando1, Object multiplicando2){
        var tipoMultiplicando1=this.numeroUno.tipo.getTipo();
        var tipoMultiplicando2=this.numeroDos.tipo.getTipo();
        switch(tipoMultiplicando1){
            case INT ->{
                switch(tipoMultiplicando2){
                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.INT);
                        return (int) multiplicando1 * (int) multiplicando2;
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return (int) multiplicando1 * (double) multiplicando2;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.INT);
                        char caracter=multiplicando2.toString().charAt(0);
                        return (int) multiplicando1 * (int) caracter;
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
            
            }
            case DOUBLE ->{
                switch(tipoMultiplicando2){


                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return (double) multiplicando1 * (int) multiplicando2;
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return (double) multiplicando1 * (double) multiplicando2;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=multiplicando2.toString().charAt(0);
                        return (double) multiplicando1 * (double)((int)caracter);
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
           
            }
            case CHAR ->{
                switch(tipoMultiplicando2){


                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.INT);
                        char caracter=multiplicando1.toString().charAt(0);
                        return (int) caracter * (int) multiplicando2;
                    }
                    case DOUBLE -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=multiplicando1.toString().charAt(0);
                        return (int) caracter * (double)multiplicando2;
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
    
    public Object Division(Object dividendo, Object divisor){
        var tipoDividendo=this.numeroUno.tipo.getTipo();
        var tipoDivisor=this.numeroDos.tipo.getTipo();
        switch(tipoDividendo){

            case INT ->{
                switch(tipoDivisor){

                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return ((double)((int) dividendo)) / ((double)((int) divisor));
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return ((double)((int) dividendo)) / ((double) divisor);
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=divisor.toString().charAt(0);
                        return ((double)((int) dividendo)) / ((double)((int) caracter));
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
            
            }
            case DOUBLE ->{
                switch(tipoDivisor){



                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return ((double) dividendo) / ((double)((int) divisor));
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return ((double) dividendo) / ((double) divisor);
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=divisor.toString().charAt(0);
                        return ((double) dividendo) / ((double)((int)caracter));
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
           
            }
            case CHAR ->{
                switch(tipoDivisor){



                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=dividendo.toString().charAt(0);
                        return ((double)((int) caracter)) / ((double)((int) divisor));
                    }
                    case DOUBLE -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        char caracter=dividendo.toString().charAt(0);
                        return ((double)((int) caracter)) / ((double)divisor);
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
    
    public Object Potencia(Object Potencia1, Object Potencia2){
        var tipoPotencia1=this.numeroUno.tipo.getTipo();
        var tipoPotencia2=this.numeroDos.tipo.getTipo();
        switch(tipoPotencia1){


            case INT ->{
                switch(tipoPotencia2){


                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.INT);
                        return (int)(Math.pow(((double)((int) Potencia1)), ((double)((int) Potencia2))));
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return Math.pow(((double)((int) Potencia1)), ((double)Potencia2));
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
            
            }
            case DOUBLE ->{
                switch(tipoPotencia2){
                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return Math.pow(((double)Potencia1), ((double)((int) Potencia2)));
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return Math.pow(((double)Potencia1), ((double)Potencia2));
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
            }
            default -> {
                 return new Errores("Error Semántico","Sumando Izquierdo De Tipo Incorrecto",this.linea,this.columna);   
            }
        }
    }
    
        public Object Modulo(Object Modulo1, Object Modulo2){
        var tipoModulo1=this.numeroUno.tipo.getTipo();
        var tipoModulo2=this.numeroDos.tipo.getTipo();
        switch(tipoModulo1){



            case INT ->{
                switch(tipoModulo2){
                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return ((double)((int) Modulo1))%((double)((int) Modulo2));
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return ((double)((int) Modulo1))%((double)Modulo2);
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
                    }
                }
            
            }
            case DOUBLE ->{
                switch(tipoModulo2){

                    case INT -> {
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return ((double)Modulo1)%((double)((int) Modulo2));
                    }
                    case DOUBLE ->{
                        this.tipo.setTipo(TipoDeDato.DOUBLE);
                        return ((double)Modulo1)%((double)Modulo2);
                    }
                    default ->{
                        return new Errores("Error Semántico","Sumando Derecho Incorrecto",this.linea,this.columna);
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
