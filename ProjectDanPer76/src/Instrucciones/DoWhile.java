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
public class DoWhile extends Instruccion {
    private Instruccion condicion;
    private LinkedList<Instruccion> instrucciones;

    public DoWhile(Instruccion condicion, LinkedList<Instruccion> instrucciones, int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.condicion = condicion;
        this.instrucciones = instrucciones;
    }
    
    
    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var condicionWhile=this.condicion.Interpretar(arbol, tabla);
        if (condicionWhile instanceof Errores){
            return condicionWhile;
        }
        if (this.condicion.tipo.getTipo() != TipoDeDato.BOOLEAN){
            return new Errores("Error Semántico","Expresión Inválida Para Ciclo Do-While",this.linea,this.columna);
        }
        var NuevaTabla= new TablaDeSimbolos(tabla);
        do{
            var NuevaTabla2=new TablaDeSimbolos(NuevaTabla);
            for(var i: this.instrucciones){
                if(i instanceof Break){
                    return null;
                }
                var instruccionObtenida=i.Interpretar(arbol, NuevaTabla2);
                if(instruccionObtenida instanceof Break){
                    return null;
                }
                if(instruccionObtenida instanceof Errores){
                    arbol.getErrores().add((Errores)instruccionObtenida);
                }
            }
        }while ((boolean) this.condicion.Interpretar(arbol, NuevaTabla));
        return null;
    }
}
