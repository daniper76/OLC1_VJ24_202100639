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
            Interfaz entorno= new Interfaz();
            entorno.setVisible(true);
            String texto = ""
                    + "println(\"true\"==\"true\");  println(2+2); println(\"hola\"==\"HoLa\");"
                    + "var a:string=\"hola\";"
                    + "var b:double=3.4;"
                    + "var numero:int=0;"
                    + "var numero2:int=0;"
                    + "var numero3:int=0;"
                    + "var numero4:int=0;"
                    + "var numerodo:int=0;"
                    + "b=b+15;"
                    + "println(B);"
                    + "var c:bool=true;"
                    + "var d:bool=TRUE;"
                    + "var z:bool=false;"
                    + "var i:int=0;"
                    + "for(i=1;i<10;i++){"
                    + "     println(\"-------INCIA FOR----------\");"
                    + "     println(i);"
                    + "     println(\"gatito\");"
                    + "     if(i==8){"
                    + "         BREAK;"
                    + "     }"
                    + "     println(\"-------FINALIZA FOR----------\");"
                    + "}"
                    + "i++"
                    + "println(i);"
                    + "i--"
                    + "println(i);"
                    + "if(c){"  
                    + "     var a:int=20;"
                    + "     println(a);"
                    + "     if(d){"
                    + "         BREAK;"
                    + "         var a:int=30;"
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
                    + "        var a:int=20;"
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
                    + "var opcion:int=2;"
                    + "match opcion {"
                    + "     2=>{"
                    +"          println(\"Esta es la opcion 2\");"
                    + "         while(numero4<10){"
                    + "            println(numero4);"
                    + "            numero4=numero4+1;"
                    + "            if(numero4==3){"
                    + "               BREAK;"
                    + "             }"
                    + "         }"
                    + "     }"
                    +"      1=>{"
                    +"          println(\"Esta es la opcion 1\");"
                    + "     }"
                    + "     3=>{"
                    +"          println(\"Esta es la opcion 3\");"
                    + "     }"
                    + "}"
                    + "Do{" 
                    + "     println(\"Este es el ciclo do-while\");"
                    + "     println(numerodo);"
                    + "     println(numero2);"
                    + "     println(numero);"
                    + "     numerodo=numerodo+1;"
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
                    + "        var a:int=20;"
                    + "        println(a);"
                    + "     }else{"
                    + "        println(\"Funciona el else del do-while papi\");"
                    + "     }"
                    + "}while(numerodo<10);"
                    + "match opcion {"
                    + "     1+3=>{"
                    +"          println(\"Esta es la opcion 2\");"
                    + "         while(numero4<10){"
                    + "            println(numero4);"
                    + "            numero4=numero4+1;"
                    + "            if(numero4==3){"
                    + "               BREAK;"
                    + "             }"
                    + "         }"
                    + "     }"
                    +"      1+6=>{"
                    +"          println(\"Esta es la opcion 1\");"
                    + "     }"
                    + "     7=>{"
                    +"          println(\"Esta es la opcion 2\");"
                    + "     }"
                    + "     _=>{"
                    +"          println(\"Esta es la opcion default \");"
                    + "     }"
                    + "}"
                    + "match opcion {"
                    + "     _=>{"
                    + "          println(\"Esta es la opcion default \");"
                    + "     }"
                    + "}"
                    + "var hola:char='u';"
                    + "println(hola);"
                    + "hola=(char)67;"
                    + "println(hola);"
                    + "var casteoInt:int=(int)'c';"
                    + "println(casteoInt);"
                    + "casteoInt=(int)93;"
                    + "println(casteoInt);"
                    + "var gatillo:char=(char)98;"
                    + "println(gatillo);"
                    + "var casteoDouble:double=(double)'c';"
                    + "println(casteoDouble);"
                    + "casteoDouble=(double)98;"
                    + "println(casteoDouble);"
                    + "println(3+3.0+'3'+\" gato\");"
                    + "println(-4+'c'+'3'-3+\" gato\");"
                    + "println(-4*'c'*-4-4.0*'c'*4);"
                    + "println(4.5/2*2-4.5/2*2);"
                    + "println(4.5/2*2-3.0**4.0-4.5/2*2-2**3);"
                    + "var primero1:double=-1 + 20 - 30 % 10 + 5 * 52 / 26 + 2 ** 5;"
                    + "println(primero1);"
                    + "var aritmetica2:double = 2 + 5 - 9 * 8 + 3 / 2 + 6 - 4 * 5 / 5 + 982 * 12 / 100;"
                    + "println(aritmetica2);"
                    + "var aritmetica3:double = 123 * 6 + 7 -(4 / 2) + 65 - 812 - 42 / 1 + 32 % 2 * 2 ** 10;"
                    + "println(3==3.0);"
                    + "println(\"3.0\"+true);"
                    + "println(\"dd\"==\"dd\");"
                    + "println(aritmetica3);"
                    + "println(\"dd\"!=\"dd\");"
                    + "println(FALSE!=(3<9));"
                    + "println(True<False);"
                    + "println(('C'-999)>(3-2));"
                    + "println(\"dd\"<=\"dssd\");"
                    + "println('c'<=9.3);"
                    + "println(12 + 5 * 3 >= 56 * 5 / 21);"
                    + "println((17 * 86 - 25 + 22 / 11) == 20 < true);"
                    + "println('a' != 20 * 4);"
                    + "println(!!!!!!!!!!!!!!!!!!(!false) == true);"
                    + "println(((true && true) || ((false && false) && (false == true))) || (!true));"
                    + "println((true || false) && true && false ^ false);"
                    + "println((true || false) && true && false ^ false);"
                    + "println(!(!(true)||(3==3)^('e'>34)));"
                    + "const nop:double=3.4;"
                    + "PRintln(nop);"
                    + "nop=aritmetica3+23;"
                    + "PRintln(nop);"
                    + "var algo:string;"
                    +"""
                      var aritmetica1: double;
                      var aritmetica23: DoUbLe;//gatillosddddddddddddddddd 
                      var relacional3: bool;//MISHITOS
                      var relacional4:bool;
                      var logica1: bool;
                      var logica3: bool;//gatilslslslsllslslslslslslls
                      var total:double;/*fassfasfada
                      ddddddddddddddddd*/
                      aritmetica1 = -1 + 20 - 30 % 10 + 5 * 52 / 26 + 2 ** 5;//gatos
                      aritmetica23 = 2 + 5 - 9 * 8 + 3 / 2 + 6 - 4 * 5 / 5 + 982 * 12 / 100;//Esto da algo pero no recuerdo
                      var aritmetica33:double=123*6+7-4/2+65-812-42/1+32%2*2**10;
                      relacional3 = (17 * 86 - 25 + 22 / 11) == 20 < true;
                      var relacional1:bool=12+5*3>=56*5/21;
                     /*aqui vienen mas cosas uwuw*/
                      var relacional2:bool=aritmetica1+aritmetica33==aritmetica2*5;
                      relacional4='a'!=20*4;
                      logica1 = !!!!!!!!!!!!!!!!!!(!false) == true;
                      //figura (while e if)
                      var logica2: bool = ((true && true) || ((false && false) && (false == true))) || (!true);
                      logica3 = (true || false) && relacional1 && false ^ false;
                      println("Esta es el valor de aritmetica1->"+aritmetica1);
                      println("Esta es el valor de aritmetica2->"+aritmetica23);
                      println("Esta es el valor de aritmetica33->"+aritmetica3);
                     /*aqui se imprimen muchas cosas uwuwuw
                     ddddddddddddddddddddllllll
                     */
                      println("Esta es el valor de relacional1->"+relacional1);
                      println("Esta es el valor de relacional3->"+relacional3);
                      println("Esta es el valor de relacional2->"+relacional2);
                      println("Esta es el valor de relacional4->"+relacional4);
                      println("Esta es el valor de logica1->"+logica1);
                      println("Esta es el valor de logica2->"+logica2);
                      println("Esta es el valor de logica3->"+logica3);
                      println("Aritmetica1 "+(aritmetica1==61));
                      println("Aritmetica2 "+(aritmetica23==56.34));
                      println("Aritmetica3 "+(aritmetica33==-46));
                      println("Relacional1 "+relacional1);
                      println("Relacional2 "+relacional2);
                      println("Relacional3 "+relacional3);
                      println("Relacional4 "+relacional4);
                      println("Logica1 "+logica1);
                      println("Logica2 "+logica2);
                      println("Logica3 "+logica3);
                      println("valor por defecto"+(total==0));
                     """;
     }
    
    public static void Analizar(String entrada){
        try{
            scanner s = new scanner(new BufferedReader(new StringReader(entrada)));
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
                ast.CrearCadenaConsola(i.toString());
            }
            cadena.cadena.cadenaConsola=ast.getCadenaResultado();
            } catch (Exception ex) {
            System.out.println("Algo salio mal");
            System.out.println(ex);
            }
    
    }
    
}
