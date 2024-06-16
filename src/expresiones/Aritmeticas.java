/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package expresiones;

import abstracto.Instruccion;
import excepciones.Errores;
import simbolo.Arbol;
import simbolo.Tipo;
import simbolo.tablaSimbolos;
import simbolo.tipoDato;

/**
 *
 * @author danie
 */
public class Aritmeticas extends Instruccion {
    private Instruccion operando1;
    private Instruccion operando2;
    private OperadoresAritmeticos operacion;
    private Instruccion operandoUnico;

    //negacion 
    public Aritmeticas(Instruccion operandoUnico, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operacion = operacion;
        this.operandoUnico = operandoUnico;
    }

    //cualquier operacion menos negacion
    public Aritmeticas(Instruccion operando1, Instruccion operando2, OperadoresAritmeticos operacion, int linea, int col) {
        super(new Tipo(tipoDato.ENTERO), linea, col);
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operacion = operacion;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object opIzq = null, opDer = null, Unico = null;
        if (this.operandoUnico != null) {
            Unico = this.operandoUnico.interpretar(arbol, tabla);
            if (Unico instanceof Errores) {
                return Unico;
            }
        } else {
            opIzq = this.operando1.interpretar(arbol, tabla);
            if (opIzq instanceof Errores) {
                return opIzq;
            }
            opDer = this.operando2.interpretar(arbol, tabla);
            if (opDer instanceof Errores) {
                return opDer;
            }
        }

        return switch (operacion) {
            case SUMA ->
                this.suma(opIzq, opDer);
            case RESTA ->
                this.resta(opIzq, opDer);
            case MULTIPLICACION ->
                this.multiplicacion(opIzq, opDer);
            case DIVISION ->
                this.division(opIzq, opDer);
            case POTENCIA ->
                this.potencia(opIzq, opDer);
            case MODULO ->
                this.modulo(opIzq, opDer);
            case NEGACION ->
                this.negacion(Unico);
            default ->
                new Errores("SEMANTICO", "Operador invalido", this.linea, this.col);
        };
    }

public Object suma(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 + (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 + (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return (int) op1 + (int) caracter;
                    }

                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 + (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter=op2.toString().charAt(0);
                        return (double) op1 + (int) caracter;
                    }
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter + (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter + (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.CADENA);
                        return op1.toString() + op2.toString();
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CADENA -> {
                this.tipo.setTipo(tipoDato.CADENA);
                return op1.toString() + op2.toString();
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }

    public Object negacion(Object op1) {
        var opU = this.operandoUnico.tipo.getTipo();
        switch (opU) {
            case ENTERO -> {
                this.tipo.setTipo(tipoDato.ENTERO);
                return (int) op1 * -1;
            }
            case DECIMAL -> {
                this.tipo.setTipo(tipoDato.DECIMAL);
                return (double) op1 * -1;
            }
            default -> {
                return new Errores("SEMANTICO", "Negacion erronea", this.linea, this.col);
            }
        }
    }
    public Object resta(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 - (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 - (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return (int) op1 - (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 - (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 - (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter=op2.toString().charAt(0);
                        return (double) op1 - (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter - (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter - (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }
    
    public Object multiplicacion(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return (int) op1 * (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (int) op1 * (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return (int) op1 * (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 * (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return (double) op1 * (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter=op2.toString().charAt(0);
                        return (double) op1 * (int) caracter;
                    }
                   
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter * (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter * (double) op2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }

    public Object division(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) / Double.parseDouble(op2.toString());
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) / Double.parseDouble(op2.toString());
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return Double.parseDouble(op1.toString()) / (double)((int) caracter);
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) / Double.parseDouble(op2.toString());
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString()) / Double.parseDouble(op2.toString());
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter=op2.toString().charAt(0);
                        return Double.parseDouble(op1.toString()) / (double)((int) caracter);
                    }
                   
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter=op1.toString().charAt(0);
                        return (double)((int) caracter)/Double.parseDouble(op2.toString());
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        char caracter=op1.toString().charAt(0);
                        return (double)((int) caracter)/Double.parseDouble(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }
    
    public Object potencia(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.ENTERO);
                        return Math.pow(Double.parseDouble(op1.toString()), Double.parseDouble(op2.toString()));
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Math.pow(Double.parseDouble(op1.toString()), Double.parseDouble(op2.toString()));
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Math.pow(Double.parseDouble(op1.toString()), Double.parseDouble(op2.toString()));
                    }
                    case DECIMAL -> {
                       this.tipo.setTipo(tipoDato.DECIMAL);
                       return Math.pow(Double.parseDouble(op1.toString()), Double.parseDouble(op2.toString()));
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }
    
       public Object modulo(Object op1, Object op2) {
        var tipo1 = this.operando1.tipo.getTipo();
        var tipo2 = this.operando2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString())%Double.parseDouble(op2.toString());
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString())%Double.parseDouble(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.DECIMAL);
                        return Double.parseDouble(op1.toString())%Double.parseDouble(op2.toString());
                    }
                    case DECIMAL -> {
                       this.tipo.setTipo(tipoDato.DECIMAL);
                       return Double.parseDouble(op1.toString())%Double.parseDouble(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }


    
}