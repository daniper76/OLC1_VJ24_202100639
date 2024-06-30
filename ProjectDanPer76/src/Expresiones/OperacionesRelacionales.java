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
    private SimbolosRelacionales operacion;

    public OperacionesRelacionales(Instruccion condicion1, Instruccion condicion2, SimbolosRelacionales operacion,int linea, int columna) {
        super(new Tipo(TipoDeDato.BOOLEAN), linea, columna);
        this.condicion1 = condicion1;
        this.condicion2 = condicion2;
        this.operacion = operacion;
    }
    
    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var condicionIzquierda=this.condicion1.Interpretar(arbol, tabla);
        if(condicionIzquierda instanceof Errores){
            return condicionIzquierda;
        }
        var condicionDerecha=this.condicion2.Interpretar(arbol, tabla);
        if(condicionDerecha instanceof Errores){
            return condicionDerecha;
        }
        switch(operacion){
            case IGUAL->{
                return this.Igualdad(condicionIzquierda, condicionDerecha);
            }
            case MENOR->{
                return this.Menor(condicionIzquierda, condicionDerecha);
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
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Double",this.linea,this.columna);
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
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación De Comparación",this.linea,this.columna);
            }
        }
    }
     public Object Menor(Object condicionIzquierda, Object condicionDerecha) {
        var tipoCondicionIzquierda = this.condicion1.tipo.getTipo();
        var tipoCondicionDerecha = this.condicion2.tipo.getTipo();
        
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
                    default->{
                       return new Errores("Error Semántico","Dato Comparado Derecho No Apto Para Comparación con Dato Tipo Double",this.linea,this.columna);
                    }
                }
            }
            default ->{
                return new Errores("Error Semántico","Dato Comparado Izquierdo No Apto Para Operación De Comparación",this.linea,this.columna);
            }
        }
    }
    
    
}
