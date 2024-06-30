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

    public Simbolo(Tipo tipo, String id, Object valor) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
    }

    public Simbolo(Tipo tipo, String id) {
        this.tipo = tipo;
        this.id = id;
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