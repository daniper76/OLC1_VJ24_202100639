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
public class For extends Instruccion {
    
    private Instruccion asignacion;
    private Instruccion condicion;
    private Instruccion actualizacion;
    private LinkedList<Instruccion> instrucciones;

    public For(Instruccion asignacion, Instruccion condicion, Instruccion actualizacion, LinkedList<Instruccion> instrucciones,int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.asignacion = asignacion;
        this.condicion = condicion;
        this.actualizacion = actualizacion;
        this.instrucciones = instrucciones;
    }
    
    
    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var nuevaTabla=new TablaDeSimbolos(tabla);
        var valorAsignacion=this.asignacion.Interpretar(arbol, nuevaTabla);
        if(valorAsignacion instanceof Error){
            return valorAsignacion;
        }
        var condicionFor=this.condicion.Interpretar(arbol, nuevaTabla);
        if(condicionFor instanceof Error){
            return condicionFor;
        }
        if(this.condicion.tipo.getTipo()!=TipoDeDato.BOOLEAN){
            return new Errores("Error Semántico","La Condición No Coincide Con El Tipo Booleano",this.linea,this.columna);
        }
        while ((boolean) this.condicion.Interpretar(arbol, nuevaTabla)){
            var tablaFor=new TablaDeSimbolos(nuevaTabla);
            for(var i: this.instrucciones){
                if(i instanceof Break){
                    return null;
                }
                var instruccionObtenida=i.Interpretar(arbol, tablaFor);
                if(instruccionObtenida instanceof Break){
                    return null;
                }
            }
            var actualizacionDeVariable=this.actualizacion.Interpretar(arbol, nuevaTabla);
            if(actualizacionDeVariable instanceof Errores){
                return actualizacionDeVariable;
            }
        }
        return null;
    }
    
}
