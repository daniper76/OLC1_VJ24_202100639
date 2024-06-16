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
public class Relacionales extends Instruccion {
    private Instruccion cond1;
    private Instruccion cond2;
    private OperadoresRelacionales relacional;
    private Instruccion condicionUnica;
    
    public Relacionales(Instruccion condicionUnica, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.relacional = relacional;
        this.condicionUnica = condicionUnica;
    }

    public Relacionales(Instruccion cond1, Instruccion cond2, OperadoresRelacionales relacional, int linea, int col) {
        super(new Tipo(tipoDato.BOOLEANO), linea, col);
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.relacional = relacional;
    }

    @Override
    public Object interpretar(Arbol arbol, tablaSimbolos tabla) {
        Object condIzq = null, condDer = null, condUnico = null;
        if (this.condicionUnica != null) {
            condUnico = this.condicionUnica.interpretar(arbol, tabla);
            if (condUnico instanceof Errores) {
                return condUnico;
            }
        } else {
            condIzq = this.cond1.interpretar(arbol, tabla);
            if (condIzq instanceof Errores) {
                return condIzq;
            }
            condDer = this.cond2.interpretar(arbol, tabla);
            if (condDer instanceof Errores) {
                return condDer;
            }
        }
       

        return switch (relacional) {
            case EQUALS ->
                this.equals(condIzq, condDer);
            case DIFERENTE->
                this.diferente(condIzq, condDer);
            case MAYOR->
                this.mayor(condIzq, condDer);
            case MENOR->
                this.menor(condIzq, condDer);
            case MAYORIGUAL->
                this.mayorIgual(condIzq, condDer);
            case MENORIGUAL->
                this.menorIgual(condIzq, condDer);
            case AND->
                this.andCondicion(condIzq, condDer);
            case XOR->
                this.xorCondicion(condIzq, condDer);
            case OR->
                this.orCondicion(condIzq, condDer);
            case NOT->
                this.notCondicion(condUnico);
            default ->
                new Errores("SEMANTICO", "Relacional Invalido", this.linea, this.col);
        };
    }
    
        public Object equals(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 == (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 == (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return (int) op1 == (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 == (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 == (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op2.toString().charAt(0);
                        return (double) op1 == (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter ==(int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter == (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter1=op1.toString().charAt(0);
                        char caracter2=op1.toString().charAt(0);
                        return (int) caracter1 == (double) caracter2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return Boolean.parseBoolean(op1.toString())==Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CADENA -> {
                switch (tipo2) {
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return op1.toString().equalsIgnoreCase(op2.toString());
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
        public Object diferente(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 != (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 != (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return (int) op1 != (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 != (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 != (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op2.toString().charAt(0);
                        return (double) op1 != (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter !=(int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter != (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter1=op1.toString().charAt(0);
                        char caracter2=op1.toString().charAt(0);
                        return (int) caracter1 != (double) caracter2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return Boolean.parseBoolean(op1.toString())!=Boolean.parseBoolean(op2.toString());
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CADENA -> {
                switch (tipo2) {
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return !(op1.toString().equalsIgnoreCase(op2.toString()));
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

        public Object mayor(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 > (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 > (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return (int) op1 > (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 > (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 > (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op2.toString().charAt(0);
                        return (double) op1 > (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter >(int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter > (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter1=op1.toString().charAt(0);
                        char caracter2=op1.toString().charAt(0);
                        return (int) caracter1 > (double) caracter2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        int valor1 = Boolean.parseBoolean(op1.toString()) ? 1 : 0;
                        int valor2 = Boolean.parseBoolean(op2.toString()) ? 1 : 0;
                        return valor1>valor2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CADENA -> {
                switch (tipo2) {
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return op1.toString().length()>op2.toString().length();
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
        
     public Object menor(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 < (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 < (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return (int) op1 < (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 < (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 < (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op2.toString().charAt(0);
                        return (double) op1 < (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter <(int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter < (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter1=op1.toString().charAt(0);
                        char caracter2=op1.toString().charAt(0);
                        return (int) caracter1 < (double) caracter2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        int valor1 = Boolean.parseBoolean(op1.toString()) ? 1 : 0;
                        int valor2 = Boolean.parseBoolean(op2.toString()) ? 1 : 0;
                        return valor1<valor2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CADENA -> {
                switch (tipo2) {
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return op1.toString().length()<op2.toString().length();
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
     
      public Object mayorIgual(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 >= (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 >= (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return (int) op1 >= (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 >= (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 >= (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op2.toString().charAt(0);
                        return (double) op1 >= (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter >=(int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter >= (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter1=op1.toString().charAt(0);
                        char caracter2=op1.toString().charAt(0);
                        return (int) caracter1 >= (double) caracter2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        int valor1 = Boolean.parseBoolean(op1.toString()) ? 1 : 0;
                        int valor2 = Boolean.parseBoolean(op2.toString()) ? 1 : 0;
                        return valor1>=valor2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CADENA -> {
                switch (tipo2) {
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return op1.toString().length()>=op2.toString().length();
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
     
        public Object menorIgual(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        switch (tipo1) {
            case ENTERO -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 <= (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (int) op1 <= (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter;
                        caracter = op2.toString().charAt(0);
                        return (int) op1 <= (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case DECIMAL -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 <= (int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return (double) op1 <= (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op2.toString().charAt(0);
                        return (double) op1 <= (int) caracter;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CARACTER -> {
                switch (tipo2) {
                    case ENTERO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter <=(int) op2;
                    }
                    case DECIMAL -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter=op1.toString().charAt(0);
                        return (int) caracter <= (double) op2;
                    }
                    case CARACTER -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        char caracter1=op1.toString().charAt(0);
                        char caracter2=op1.toString().charAt(0);
                        return (int) caracter1 <= (double) caracter2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        int valor1 = Boolean.parseBoolean(op1.toString()) ? 1 : 0;
                        int valor2 = Boolean.parseBoolean(op2.toString()) ? 1 : 0;
                        return valor1<=valor2;
                    }
                    default -> {
                        return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);
                    }
                }
            }
            case CADENA -> {
                switch (tipo2) {
                    case CADENA -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return op1.toString().length()<=op2.toString().length();
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
        
        public Object andCondicion(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        switch (tipo1) {
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return Boolean.parseBoolean(op1.toString()) && Boolean.parseBoolean(op2.toString());
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
        
    public Object orCondicion(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        switch (tipo1) {
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return Boolean.parseBoolean(op1.toString()) || Boolean.parseBoolean(op2.toString());
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

    public Object notCondicion(Object op1) {
        var tipo1 = this.cond1.tipo.getTipo();
        switch (tipo1) {
            case BOOLEANO -> {
                this.tipo.setTipo(tipoDato.BOOLEANO);
                return !(Boolean.parseBoolean(op1.toString()));
            }
            default -> {
                return new Errores("SEMANTICO", "Suma erronea", this.linea, this.col);

            }
        }
    }

        public Object xorCondicion(Object op1, Object op2) {
        var tipo1 = this.cond1.tipo.getTipo();
        var tipo2 = this.cond2.tipo.getTipo();
        switch (tipo1) {
            case BOOLEANO -> {
                switch (tipo2) {
                    case BOOLEANO -> {
                        this.tipo.setTipo(tipoDato.BOOLEANO);
                        return Boolean.parseBoolean(op1.toString()) ^ Boolean.parseBoolean(op2.toString());
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