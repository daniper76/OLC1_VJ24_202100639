/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simbolo;

/**
 *
 * @author danie
 */
public class Simbolo {
    private Tipo tipo;
    private String id;
    private Object valor;
    private String mutabilidad;

    public Simbolo(Tipo tipo, String id, Object valor,String mutabilidad) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.mutabilidad=mutabilidad;
    }
    
    public Simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
    }

    public String getMutabilidad() {
        return mutabilidad;
    }

    public void setMutabilidad(String mutabilidad) {
        this.mutabilidad = mutabilidad;
    }
    
    public Tipo getTipo() {
        return tipo;
    }

    public String getId() {
        return id;
    }

    public Object getValor() {
        return valor;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }
    
}
