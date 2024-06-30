/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Simbolo;

/**
 *
 * @author danie
 */
public class Tipo {
    private TipoDeDato Tipo;

    public Tipo(TipoDeDato Tipo) {
        this.Tipo = Tipo;
    }

    public TipoDeDato getTipo() {
        return Tipo;
    }

    public void setTipo(TipoDeDato Tipo) {
        this.Tipo = Tipo;
    }

    @Override
    public String toString() {
        return "Tipo{" + "Tipo=" + Tipo + '}';
    }
    
    
}
