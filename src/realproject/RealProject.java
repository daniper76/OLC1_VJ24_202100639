/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package realproject;

import abstracto.Instruccion;
import analisis.parser;
import analisis.scanner;
import excepciones.Errores;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.tablaSimbolos;

/**
 *
 * @author danie
 */
public class RealProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ventana entorno= new ventana();
        entorno.setVisible(true);
    }
    public static void Analizar(String entrada){
        try {

            scanner s = new scanner(new BufferedReader(new StringReader(entrada)));
            parser p = new parser(s);
            
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tabla = new tablaSimbolos();
            tabla.setNombre("GLOBAL");
            ast.setConsola("");
            for (var i: s.listaErrores){
                System.out.println(i);
            }
            for (var i: p.listaErrores){
                System.out.println(i);
            }
            for (var a : ast.getInstrucciones()) { 
                if(a==null){
                    continue;
                }
                var res = a.interpretar(ast, tabla);
                if(res instanceof Errores){
                    ManejoErrores.Error.GuardarError(((Errores) res).getTipo(),((Errores) res).getDesc(),String.valueOf(((Errores) res).getLinea()),String.valueOf(((Errores) res).getLinea()));
                }
            }
            cadena.cadena.cadenaConsola=ast.getConsola();
            System.out.println(ast.getConsola());
        } catch (Exception ex) {
            System.out.println("Algo salio mal");
            System.out.println(ex);
        }
    }

}
