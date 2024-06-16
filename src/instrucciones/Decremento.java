/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instrucciones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author danie
 */
public class Decremento extends Instruccion {
    private String id;

    public Decremento(String id,int linea, int col) {
        super(new Tipo(tipoDato.VOID), linea, col);
        this.id = id;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        //variable exista
        var variable = tabla.getVariable(id);
        if (variable == null) {
            return new Errores("SEMANTICO", "Variable no exitente",
                    this.linea, this.col);
        }

        // interpretar el nuevo valor a asignar
        var newValor=variable.getValor();

        //validar tipos
        if (variable.getTipo().getTipo()==tipoDato.ENTERO) {
             newValor=(int) variable.getValor()-1;
        }
        if (variable.getTipo().getTipo()==tipoDato.DECIMAL) {
             newValor=(double) variable.getValor()-1.0;
        }
        if (variable.getMutabilidad().toLowerCase().equalsIgnoreCase("const")) {
            return new Errores("SEMANTICO", "No se asigna variable",
                    this.linea, this.col);
        }
        //this.tipo.setTipo(variable.getTipo().getTipo());
        variable.setValor(newValor);
        return null;
    }
}
