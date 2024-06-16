/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManejoErrores;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class Error {
    public String error,detalle, columna, fila;
    public static LinkedList<Error> lista_errores = new LinkedList<>();
    
    public Error(String error, String detalle, String columna,String fila) {
        this.error=error;
        this.detalle=detalle;
        this.columna=columna;
        this.fila=fila;
    }
    
    public String DevolverError() {
        return error;
    }
    
    public String DevolverDetalle() {
        return detalle;
    }
    public String DevolverColumna() {
        return columna;
    }
    public String DevolverFila(){
        return fila;
    }
    
    public static void GuardarError(String error, String detalle, String columna,String fila){
        Error nuevo=new Error(error,detalle,columna,fila);
        lista_errores.add(nuevo);
    }
    
    public static void MostrarErrores() throws IOException{
            FileWriter archivo = null;
            PrintWriter escritor = null;
            int conteo=1;
            archivo = new FileWriter("tablaErrores.html");
            escritor= new PrintWriter(archivo);
            escritor.println("<html>");
            escritor.println("<head><title>Errores</title></head>");    
            escritor.println("<body style=\"background-color:beige;\">");
            escritor.println("<table align=\"center\" border=\"1\">" );
            escritor.println("<tr>");
            escritor.println("<td style=\"text-align: center;\">#</td>");
            escritor.println("<td style=\"text-align: center;\">Error</td>");
            escritor.println("<td style=\"text-align: center;\">Detalle</td>");
            escritor.println("<td style=\"text-align: center;\">Fila</td>");
            escritor.println("<td style=\"text-align: center;\">Columna</td>");
            escritor.println("</tr>");
            for(Error error_encontrado:lista_errores){
                escritor.println("<tr>");
                escritor.println("<td style=\"text-align: center;\">"+String.valueOf(conteo)+"</td>");
                escritor.println("<td style=\"text-align: center;\">"+error_encontrado.DevolverError()+"</td>");
                escritor.println("<td style=\"text-align: center;\">"+error_encontrado.DevolverDetalle()+"</td>");
                escritor.println("<td style=\"text-align: center;\">"+error_encontrado.DevolverFila()+"</td>");
                escritor.println("<td style=\"text-align: center;\">"+error_encontrado.DevolverColumna()+"</td>");
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
