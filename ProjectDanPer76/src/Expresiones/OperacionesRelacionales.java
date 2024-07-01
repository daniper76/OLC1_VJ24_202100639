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
public class OperacionesRelacionales extends Instruccion {
    private Instruccion condicion1;
    private Instruccion condicion2;
    private Instruccion condicionUnica;
    private SimbolosRelacionales operacion;

    public OperacionesRelacionales(Instruccion condicion1, Instruccion condicion2, SimbolosRelacionales operacion,int linea, int columna) {
        super(new Tipo(TipoDeDato.BOOLEAN), linea, columna);
        this.condicion1 = condicion1;
        this.condicion2 = condicion2;
        this.operacion = operacion;
    }

    public OperacionesRelacionales(Instruccion condicionUnica, SimbolosRelacionales operacion,int linea, int columna) {
        super(new Tipo(TipoDeDato.BOOLEAN), linea, columna);
        this.condicionUnica = condicionUnica;
        this.operacion = operacion;
    }
    
    
    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        Object condicionIzquierda=null;
        Object condicionDerecha=null;
        Object expresionUnica=null;
        if(this.condicionUnica!=null){
            expresionUnica=this.condicionUnica.Interpretar(arbol, tabla);
            if(expresionUnica instanceof Errores){
                return expresionUnica;
            }
        }else{
            condicionIzquierda=this.condicion1.Interpretar(arbol, tabla);
            if(condicionIzquierda instanceof Errores){
                return condicionIzquierda;
            }
            condicionDerecha=this.condicion2.Interpretar(arbol, tabla);
            if(condicionDerecha instanceof Errores){
                return condicionDerecha;
            }   
                
        }
        switch(operacion){
            case IGUAL->{
                return this.Igualdad(condicionIzquierda, condicionDerecha);
            }
            case MENOR->{
                return this.Menor(condicionIzquierda, condicionDerecha);
            }
            case NOIGUAL->{
                return this.NoIgual(condicionIzquierda, condicionDerecha);
            }
            case MAYOR->{
                return this.Mayor(condicionIzquierda, condicionDerecha);
            }
            case MENORIGUAL->{
                return this.MenorIgual(condicionIzquierda, condicionDerecha);
            }
            case MAYORIGUAL->{
                return this.MayorIgual(condicionIzquierda, condicionDerecha);
            }
            case AND->{
                return this.And(condicionIzquierda, condicionDerecha);
            }
            case OR->{
                return this.Or(condicionIzquierda, condicionDerecha);
            }
            case XOR->{
                return this.Xor(condicionIzquierda, condicionDerecha);
            }
            case NOT->{
                return this.Not(expresionUnica);
            }
            default ->{
                return new Errores("Error Semántico","Operador No Coincide Con Ninguna Operacion Relacional Establecida",this.linea,this.columna);
            }
        }
    }
    
    public Object Igualdad(Object condicionIzquierda, Object condicionDerecha){
        var tipoCondicionIzquierda=this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha=this.condicion2.tipo.getTipo();
        switch(tipoCondicionIzquierda){
            case INT->{
                switch(tipoCondicionDerecha){
                    case INT->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda==(int)condicionDerecha;
                    }
                    case DOUBLE->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda==(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (int) condicionIzquierda==(int) caracter;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Int",this.linea,this.columna);
                    }
                }
                
            }
            case DOUBLE->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda==(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda==(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (double) condicionIzquierda==(int) caracter;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Double",this.linea,this.columna);
                    }
                }
            }
            case CHAR->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter==(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter==(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracterIzquierda=condicionIzquierda.toString().charAt(0);
                        char caracterDerecha=condicionDerecha.toString().charAt(0);
                        return caracterIzquierda==caracterDerecha;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Char",this.linea,this.columna);
                    }
                }
            }
            case STRING->{
                switch(tipoCondicionDerecha){
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return condicionIzquierda.toString().equalsIgnoreCase(condicionDerecha.toString());
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo String",this.linea,this.columna);
                    }
                }
            }
            case BOOLEAN->{
                switch(tipoCondicionDerecha){
                    case BOOLEAN->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (boolean)condicionIzquierda==(boolean)condicionDerecha;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Boolean",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación De Comparación",this.linea,this.columna);
            }
        }
    }
    
    public Object NoIgual(Object condicionIzquierda, Object condicionDerecha){
        var tipoCondicionIzquierda=this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha=this.condicion2.tipo.getTipo();
        switch(tipoCondicionIzquierda){
            case INT->{
                switch(tipoCondicionDerecha){
                    case INT->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda!=(int)condicionDerecha;
                    }
                    case DOUBLE->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda!=(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (int) condicionIzquierda!=(int) caracter;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Int",this.linea,this.columna);
                    }
                }
                
            }
            case DOUBLE->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda!=(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda!=(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (double) condicionIzquierda!=(int) caracter;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Double",this.linea,this.columna);
                    }
                }
            }
            case CHAR->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter!=(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter!=(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracterIzquierda=condicionIzquierda.toString().charAt(0);
                        char caracterDerecha=condicionDerecha.toString().charAt(0);
                        return caracterIzquierda!=caracterDerecha;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Char",this.linea,this.columna);
                    }
                }
            }
            case STRING->{
                switch(tipoCondicionDerecha){
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return !(condicionIzquierda.toString().equalsIgnoreCase(condicionDerecha.toString()));
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo String",this.linea,this.columna);
                    }
                }
            }
            case BOOLEAN->{
                switch(tipoCondicionDerecha){
                    case BOOLEAN->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (boolean)condicionIzquierda!=(boolean)condicionDerecha;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Boolean",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación De Comparación",this.linea,this.columna);
            }
        }
    }
    
     public Object Menor(Object condicionIzquierda, Object condicionDerecha) {
        var tipoCondicionIzquierda=this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha=this.condicion2.tipo.getTipo();
        switch(tipoCondicionIzquierda){
            case INT->{
                switch(tipoCondicionDerecha){
                    case INT->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda<(int)condicionDerecha;
                    }
                    case DOUBLE->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda<(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (int) condicionIzquierda<(int) caracter;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Int",this.linea,this.columna);
                    }
                }
                
            }
            case DOUBLE->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda<(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda<(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (double) condicionIzquierda<(int) caracter;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Double",this.linea,this.columna);
                    }
                }
            }
            case CHAR->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter<(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter<(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracterIzquierda=condicionIzquierda.toString().charAt(0);
                        char caracterDerecha=condicionDerecha.toString().charAt(0);
                        return caracterIzquierda<caracterDerecha;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Char",this.linea,this.columna);
                    }
                }
            }
            case STRING->{
                switch(tipoCondicionDerecha){
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return condicionIzquierda.toString().length()<condicionDerecha.toString().length();
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo String",this.linea,this.columna);
                    }
                }
            }
            case BOOLEAN->{
                switch(tipoCondicionDerecha){
                    case BOOLEAN->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        int valorIzquierda=0;
                        int valorDerecha=0;
                        if((boolean)condicionIzquierda==true){
                            valorIzquierda=1;
                        }else{
                            valorIzquierda=0;
                        }
                        if((boolean)condicionDerecha==true){
                            valorDerecha=1;
                        }else{
                            valorDerecha=0;
                        }
                        return valorIzquierda<valorDerecha;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Boolean",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación De Comparación",this.linea,this.columna);
            }
        }
    }
     
    public Object Mayor(Object condicionIzquierda, Object condicionDerecha) {
        var tipoCondicionIzquierda=this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha=this.condicion2.tipo.getTipo();
        switch(tipoCondicionIzquierda){
            case INT->{
                switch(tipoCondicionDerecha){
                    case INT->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda>(int)condicionDerecha;
                    }
                    case DOUBLE->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda>(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (int) condicionIzquierda>(int) caracter;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Int",this.linea,this.columna);
                    }
                }
                
            }
            case DOUBLE->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda>(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda>(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (double) condicionIzquierda>(int) caracter;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Double",this.linea,this.columna);
                    }
                }
            }
            case CHAR->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter>(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter>(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracterIzquierda=condicionIzquierda.toString().charAt(0);
                        char caracterDerecha=condicionDerecha.toString().charAt(0);
                        return caracterIzquierda>caracterDerecha;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Char",this.linea,this.columna);
                    }
                }
            }
            case STRING->{
                switch(tipoCondicionDerecha){
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return condicionIzquierda.toString().length()>condicionDerecha.toString().length();
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo String",this.linea,this.columna);
                    }
                }
            }
            case BOOLEAN->{
                switch(tipoCondicionDerecha){
                    case BOOLEAN->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        int valorIzquierda=0;
                        int valorDerecha=0;
                        if((boolean)condicionIzquierda==true){
                            valorIzquierda=1;
                        }else{
                            valorIzquierda=0;
                        }
                        if((boolean)condicionDerecha==true){
                            valorDerecha=1;
                        }else{
                            valorDerecha=0;
                        }
                        return valorIzquierda>valorDerecha;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Boolean",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación De Comparación",this.linea,this.columna);
            }
        }
    }
    
    public Object MayorIgual(Object condicionIzquierda, Object condicionDerecha) {
        var tipoCondicionIzquierda=this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha=this.condicion2.tipo.getTipo();
        switch(tipoCondicionIzquierda){
            case INT->{
                switch(tipoCondicionDerecha){
                    case INT->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda>=(int)condicionDerecha;
                    }
                    case DOUBLE->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda>=(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (int) condicionIzquierda>=(int) caracter;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Int",this.linea,this.columna);
                    }
                }
                
            }
            case DOUBLE->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda>=(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda>=(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (double) condicionIzquierda>=(int) caracter;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Double",this.linea,this.columna);
                    }
                }
            }
            case CHAR->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter>=(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter>=(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracterIzquierda=condicionIzquierda.toString().charAt(0);
                        char caracterDerecha=condicionDerecha.toString().charAt(0);
                        return caracterIzquierda>=caracterDerecha;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Char",this.linea,this.columna);
                    }
                }
            }
            case STRING->{
                switch(tipoCondicionDerecha){
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return condicionIzquierda.toString().length()>=condicionDerecha.toString().length();
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo String",this.linea,this.columna);
                    }
                }
            }
            case BOOLEAN->{
                switch(tipoCondicionDerecha){
                    case BOOLEAN->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        int valorIzquierda=0;
                        int valorDerecha=0;
                        if((boolean)condicionIzquierda==true){
                            valorIzquierda=1;
                        }else{
                            valorIzquierda=0;
                        }
                        if((boolean)condicionDerecha==true){
                            valorDerecha=1;
                        }else{
                            valorDerecha=0;
                        }
                        return valorIzquierda>=valorDerecha;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Boolean",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación De Comparación",this.linea,this.columna);
            }
        }
    }
    public Object MenorIgual(Object condicionIzquierda, Object condicionDerecha) {
        var tipoCondicionIzquierda=this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha=this.condicion2.tipo.getTipo();
        switch(tipoCondicionIzquierda){
            case INT->{
                switch(tipoCondicionDerecha){
                    case INT->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda<=(int)condicionDerecha;
                    }
                    case DOUBLE->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (int)condicionIzquierda<=(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (int) condicionIzquierda<=(int) caracter;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Int",this.linea,this.columna);
                    }
                }
                
            }
            case DOUBLE->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda<=(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      return (double)condicionIzquierda<=(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracter=condicionDerecha.toString().charAt(0);
                        return (double) condicionIzquierda<=(int) caracter;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Double",this.linea,this.columna);
                    }
                }
            }
            case CHAR->{
                switch(tipoCondicionDerecha){
                    case INT->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter<=(int)condicionDerecha;
                    }
                    case DOUBLE->{
                      this.tipo.setTipo(TipoDeDato.BOOLEAN);
                      char caracter=condicionIzquierda.toString().charAt(0);
                      return (int)caracter<=(double)condicionDerecha;
                    }
                    case CHAR->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        char caracterIzquierda=condicionIzquierda.toString().charAt(0);
                        char caracterDerecha=condicionDerecha.toString().charAt(0);
                        return caracterIzquierda<=caracterDerecha;
                    }
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Char",this.linea,this.columna);
                    }
                }
            }
            case STRING->{
                switch(tipoCondicionDerecha){
                    case STRING->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return condicionIzquierda.toString().length()<=condicionDerecha.toString().length();
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo String",this.linea,this.columna);
                    }
                }
            }
            case BOOLEAN->{
                switch(tipoCondicionDerecha){
                    case BOOLEAN->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        int valorIzquierda=0;
                        int valorDerecha=0;
                        if((boolean)condicionIzquierda==true){
                            valorIzquierda=1;
                        }else{
                            valorIzquierda=0;
                        }
                        if((boolean)condicionDerecha==true){
                            valorDerecha=1;
                        }else{
                            valorDerecha=0;
                        }
                        return valorIzquierda<=valorDerecha;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Boolean",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación De Comparación",this.linea,this.columna);
            }
        }
    }
    
    public Object And(Object condicionIzquierda, Object condicionDerecha){
        var tipoCondicionIzquierda=this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha=this.condicion2.tipo.getTipo();
        switch(tipoCondicionIzquierda){
            case BOOLEAN->{
                switch(tipoCondicionDerecha){
                    case BOOLEAN->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (boolean)condicionIzquierda && (boolean)condicionDerecha;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Operación AND",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación AND",this.linea,this.columna);
            }
        }
    }
    public Object Or(Object condicionIzquierda, Object condicionDerecha){
        var tipoCondicionIzquierda=this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha=this.condicion2.tipo.getTipo();
        switch(tipoCondicionIzquierda){
            case BOOLEAN->{
                switch(tipoCondicionDerecha){
                    case BOOLEAN->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (boolean)condicionIzquierda || (boolean)condicionDerecha;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Operación OR",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación OR",this.linea,this.columna);
            }
        }
    }
    public Object Xor(Object condicionIzquierda, Object condicionDerecha){
        var tipoCondicionIzquierda=this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha=this.condicion2.tipo.getTipo();
        switch(tipoCondicionIzquierda){
            case BOOLEAN->{
                switch(tipoCondicionDerecha){
                    case BOOLEAN->{
                        this.tipo.setTipo(TipoDeDato.BOOLEAN);
                        return (boolean)condicionIzquierda ^ (boolean)condicionDerecha;
                    }
                    default->{
                        return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Operación XOR",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación XOR",this.linea,this.columna);
            }
        }
    }
     public Object Not(Object condicionUnica){
        var tipoCondicionUnica=this.condicionUnica.tipo.getTipo();
        switch(tipoCondicionUnica){
            case BOOLEAN->{
                this.tipo.setTipo(TipoDeDato.BOOLEAN);
                return !((boolean)condicionUnica);
            }
            default ->{
                return new Errores("Error Semántico","Dato No Apto Para Operación NOT",this.linea,this.columna);
            }
        }
    }
    
}
