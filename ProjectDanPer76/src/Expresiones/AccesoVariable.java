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
public class AccesoVariable extends Instruccion {
    public String Identificador;

    public AccesoVariable(String Identificador, int linea, int columna) {
        super(new Tipo(TipoDeDato.VOID), linea, columna);
        this.Identificador = Identificador;
    }

    @Override
    public Object Interpretar(Arbol arbol, TablaDeSimbolos tabla) {
        var valorObtenido=tabla.ObtenerVariable(this.Identificador);
        if(valorObtenido==null){
            return new Errores("Error Semántico", "No Existe La Variable",
                    this.linea, this.columna);
        }
        this.tipo.setTipo(valorObtenido.getTipo().getTipo());
        return valorObtenido.getValor();
    }
    
}
