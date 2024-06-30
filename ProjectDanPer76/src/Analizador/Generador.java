/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizador;

/**
 *
 * @author danie
 */
public class Generador {
    
    public static void main(String[] args) {
        generarCompilador();
    }

    public static void generarCompilador() {
        try{
           String ruta = "src/Analizador/";
           String Flex[] = {ruta + "Lexer.jflex", "-d", ruta};
           jflex.Main.generate(Flex);
           String Cup[] = { "-destdir", ruta, "-parser", "parser", ruta + "Parser.cup" };
           java_cup.Main.main(Cup);
        }catch(Exception e){
           e.printStackTrace();
        }
    }
}
