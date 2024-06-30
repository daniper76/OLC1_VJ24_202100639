/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projectdanper76;

import Analizador.parser;
import Analizador.scanner;
import Errores.Errores;
import IntruccionAbstracta.Instruccion;
import Simbolo.Arbol;
import Simbolo.TablaDeSimbolos;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class ProjectDanPer76 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
            String texto = ""
                    + "println(\"true\"==\"true\");  println(2+2); println(\"hola\"==\"HoLa\");"
                    + "string a=\"hola\";"
                    + "double b=3.4;"
                    + "int numero=0;"
                    + "int numero2=0;"
                    + "int numero3=0;"
                    + "int numero4=0;"
                    + "b=b+15;"
                    + "println(B);"
                    + "bool c=true;"
                    + "bool d=TRUE;"
                    + "bool z=false;"
                    + "int i=0;"
                    + "for(i=1;i<10;i=i+1){"
                    + "     println(i);"
                    + "     println(\"gatito\");"
                    + "     if(i==7){"
                    + "         BREAK;"
                    + "     }"
                    + "}"
                    + "if(c){"  
                    + "     int a=20;"
                    + "     println(a);"
                    + "     if(d){"
                    + "         BREAK;"
                    + "         int a=30;"
                    + "         println(a);"
                    + "     }"
                    + "}"
                    + "while(numero<10){"  
                    + "     println(numero);"
                    + "     numero=numero+1;"
                    + "     if(d){"
                    + "         while(numero2<10){"
                    + "            println(numero2);"
                    + "            numero2=numero2+1;"
                    + "            if(numero2==1){"
                    + "               BREAK;"
                    + "             }"
                    + "         }"
                    + "     }"
                    + "     if(numero==3){"  
                    + "        int a=20;"
                    + "        println(a);"
                    + "     }else{"
                    + "        println(\"Funciona el else papi\");"
                    + "     }"
                    + "}"
                    + "if(z){"
                    + "  println(\"Funciona el primer if papi\");"
                    + "}else if(c){"
                    + "        println(\"Esto no sale en el if\");"
                    + "        while(numero3<10){"
                    + "            println(numero3);"
                    + "            numero3=numero3+1;"
                    + "            if(numero3==3){"
                    + "               BREAK;"
                    + "             }"
                    + "         }"
                    + "      }else if(c){"
                    + "        println(\"Funciona correctmente papi\");"
                    + "      }"
                    + "println(a);"
                    + "println(i);"
                    + "int opcion=2;"
                    + "match opcion {"
                    + "     1+2=>{"
                    +"          println(\"Esta es la opcion 2\");"
                    + "         while(numero4<10){"
                    + "            println(numero4);"
                    + "            numero4=numero4+1;"
                    + "            if(numero4==3){"
                    + "               BREAK;"
                    + "             }"
                    + "         }"
                    + "     }"
                    +"      1+1=>{"
                    +"          println(\"Esta es la opcion 1\");"
                    + "     }"
                    + "     3=>{"
                    +"          println(\"Esta es la opcion 3\");"
                    + "     }"
                    + "}";
            scanner s = new scanner(new BufferedReader(new StringReader(texto)));
            parser p = new parser(s);
            var resultado = p.parse();
            var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
            var tablaDeSimbolos = new TablaDeSimbolos();
            tablaDeSimbolos.setNombre("GLOBAL");
            ast.setCadenaResultado("");
            LinkedList<Errores> lista = new LinkedList<>();
            lista.addAll(s.listaErrores);
            lista.addAll(p.listaErrores);
            for (var a : ast.getInstrucciones()) {
                if (a == null) {
                    continue;
                }
                var res=a.Interpretar(ast, tablaDeSimbolos);
                if (res instanceof Errores) {
                    lista.add((Errores) res);
                }
            }
            
            System.out.println(ast.getCadenaResultado());
            for (var i : lista) {
                System.out.println(i);
            }
            } catch (Exception ex) {
            System.out.println("Algo salio mal");
            System.out.println(ex);
        }
     }
    
}
