/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simbolo;

import Errores.Errores;
import IntruccionAbstracta.Instruccion;
import java.util.LinkedList;

/**
 *
 * @author danie
 */
public class Arbol {
    private LinkedList<Instruccion> instrucciones;
    private String cadenaResultado;
    private TablaDeSimbolos tablaGlobal;
    private LinkedList<Errores> errores;

    public Arbol(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
        this.cadenaResultado="";
        this.tablaGlobal=new TablaDeSimbolos();
        this.errores= new LinkedList<>();
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public String getCadenaResultado() {
        return cadenaResultado;
    }

    public TablaDeSimbolos getTablaGlobal() {
        return tablaGlobal;
    }

    public LinkedList<Errores> getErrores() {
        return errores;
    }

    public void setInstrucciones(LinkedList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public void setCadenaResultado(String cadenaResultado) {
        this.cadenaResultado = cadenaResultado;
    }

    public void setTablaGlobal(TablaDeSimbolos tablaGlobal) {
        this.tablaGlobal = tablaGlobal;
    }

    public void setErrores(LinkedList<Errores> errores) {
        this.errores = errores;
    }
    public void CrearCadenaConsola(String valor) {
        int tamanioCadena=valor.length();
        int siglas=0;
        String cadenaEvaluar=valor;
        
        for(int i=0;i<tamanioCadena;i++){
            switch(cadenaEvaluar.charAt(i)){
                case '\\'->{
                    switch(cadenaEvaluar.charAt(i+1)){
                        case 'n'->{
                            
                            if(i==0){
                                this.cadenaResultado+="\n";
                                valor=valor.substring(2);
                                siglas=siglas+2;
                            }else if(i==tamanioCadena-2){
                                this.cadenaResultado+=valor.substring(0,i-siglas)+"\n";
                                valor="";
                            }else{
                                this.cadenaResultado+=valor.substring(0,i-siglas)+"\n";
                                int quitado=valor.substring(0,i-siglas+2).length();
                                valor=valor.substring(i-siglas+2);
                                siglas+=quitado;
                            }
                            break;

                        }
                        case 't'->{
                            if(i==0){
                                this.cadenaResultado+="\t";
                                valor=valor.substring(2);
                                siglas=siglas+2;
                            }else if(i==tamanioCadena-2){
                                this.cadenaResultado+=valor.substring(0,i-siglas)+"\t";
                                valor="";
                            }else{
                                this.cadenaResultado+=valor.substring(0,i-siglas)+"\t";
                                int quitado=valor.substring(0,i-siglas+2).length();
                                valor=valor.substring(i-siglas+2);
                                siglas+=quitado;
                            }
                            break;

                        }
                        case 'r'->{
                            if(i==0){
                                this.cadenaResultado+="\r";
                                valor=valor.substring(2);
                                siglas=siglas+2;
                            }else if(i==tamanioCadena-2){
                                this.cadenaResultado+=valor.substring(0,i-siglas)+"\r";
                                valor="";
                            }else{
                                this.cadenaResultado+=valor.substring(0,i-siglas)+"\r";
                                int quitado=valor.substring(0,i-siglas+2).length();
                                valor=valor.substring(i-siglas+2);
                                siglas+=quitado;
                            }
                            break;
                        }                
                    }
                }
                default->{
                    
                    
                    break;
                }
                
            }
            
        }
        
        this.cadenaResultado += valor + "\n";
    }
    
}
