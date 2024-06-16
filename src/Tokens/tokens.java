/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tokens;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class tokens {
        public String lexema, tipo, columna, fila;
    public static LinkedList<tokens> lista_tokens = new LinkedList<>();
    
    public tokens(String lexema, String tipo, String columna,String fila) {
        this.lexema = lexema;
        this.tipo=tipo;
        this.columna=columna;
        this.fila=fila;
    }
    
    public String DevolverLexema() {
        return lexema;
    }
    
    public String DevolverTipo() {
        return tipo;
    }
    public String DevolverColumna() {
        return columna;
    }
    public String DevolverFila(){
        return fila;
    }
    
    public static void GuardarTokes(String lexema, String tipo, String columna,String fila){
        tokens nuevo=new tokens(lexema,tipo,columna,fila);
        lista_tokens.add(nuevo);
    }
    
    public static void MostrarTokens() throws IOException{
            FileWriter archivo = null;
            PrintWriter escritor = null;
            int conteo=1;
            archivo = new FileWriter("tablaTokens.html");
            escritor= new PrintWriter(archivo);
            escritor.println("<html>");
            escritor.println("<head><title>Tokens</title></head>");    
            escritor.println("<body style=\"background-color:beige;\">");
            escritor.println("<table align=\"center\" border=\"1\">" );
            escritor.println("<tr>");
            escritor.println("<td style=\"text-align: center;\">#</td>");
            escritor.println("<td style=\"text-align: center;\">Lexema</td>");
            escritor.println("<td style=\"text-align: center;\">Tipo</td>");
            escritor.println("<td style=\"text-align: center;\">Fila</td>");
            escritor.println("<td style=\"text-align: center;\">Columna</td>");
            escritor.println("</tr>");
            for(tokens token:lista_tokens){
                escritor.println("<tr>");
                escritor.println("<td style=\"text-align: center;\">"+String.valueOf(conteo)+"</td>");
                escritor.println("<td style=\"text-align: center;\">"+token.DevolverLexema()+"</td>");
                escritor.println("<td style=\"text-align: center;\">"+token.DevolverTipo()+"</td>");
                escritor.println("<td style=\"text-align: center;\">"+token.DevolverFila()+"</td>");
                escritor.println("<td style=\"text-align: center;\">"+token.DevolverColumna()+"</td>");
                escritor.println("</tr>");
                conteo=conteo+1;
            }
            escritor.println("</table>");
            escritor.println("</body>");
            escritor.println("</html>");
            escritor.close();
            System.out.println("Generado exitosamente");
    }
}
