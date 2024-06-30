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
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class Elif extends Instruccion {

    private Instruccion condicion;
    private LinkedList<Instruccion> instruccionesIf;
    private Instruccion instruccionesElse;

    public Elif(Instruccion condicion, LinkedList<Instruccion> instruccionesIf,Instruccion instruccionesElse,int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instruccionesIf = instruccionesIf;
        this.instruccionesElse = instruccionesElse;
    }
    
    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var condicionIf=this.condicion.Interpretar(arbol, tabla);
        if (condicionIf instanceof Errores){
            return condicionIf;
        }
        if (this.condicion.tipo.getTipo() != TipoDeDato.BOOLEAN){
            return new Errores("Error Semántico","Expresión invalida para condicion en sentencia IF ELSE",this.linea,this.columna);
        }
        var NuevaTabla= new TablaDeSimbolos(tabla);
        if((boolean) condicionIf){
            for(var i:this.instruccionesIf){
                if(i instanceof Break){
                    return i;
                }
                var resultado=i.Interpretar(arbol, NuevaTabla);
                if(resultado instanceof Break){
                    return resultado;
                }
                if(resultado instanceof Errores){
                    arbol.getErrores().add((Errores)resultado);
                }
            }
            
        }else{
            var resultado=instruccionesElse.Interpretar(arbol, NuevaTabla);
            if(resultado instanceof Errores){
                    arbol.getErrores().add((Errores)resultado);
            }
         }
        return null;
    }
    
    
}
