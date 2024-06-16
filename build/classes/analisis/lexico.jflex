package analisis;

//importaciones
import java_cup.runtime.Symbol;
import java.util.LinkedList;
import excepciones.Errores;

%%

//codigo de usuario
%{
    public LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
%init}

//caracteristicas de jflex
%cup
%class scanner
%public
%line
%char
%column
%full
//%debug
%ignorecase

//simbolos del sistema
PAR1="("
PAR2=")"
MAS="+"
MENOS="-"
MULT="*"
POTENCIA="**"
INCREMENTO="++"
DECREMENTO="--"
DIFERENTE="!="
MENORIGUAL="<="
MAYORIGUAL=">="
MENOR="<"
MAYOR=">"
NOT="!"
AND="&&"
OR="||"
DOSPUNTOS=":"
MODULO="%"
DIV="/"
IGUAL="="
FINCADENA=";"
LLAVE1="{"
LLAVE2="}"
BLANCOS=[\ \r\t\f\n]+
ENTERO=[0-9]+
DECIMAL=[0-9]+"."[0-9]+
ID=[a-zA-z][a-zA-Z0-9_]*
CADENA = [\"]([^\"])*[\"]
CARACTER = [\'][^\\'][\']|[\'][\\][^][\']
COMENTARIOS =[\#][^\n]*[\n]
//palabras reservadas
IMPRIMIR="println"
CONST="const"
VAR="var"
INT="int"
DOUBLE="double"
STRING="string"
IF="if"
TRUE="true"
FALSE="false"
BOOL="bool"

%%
<YYINITIAL> {IMPRIMIR} {return new Symbol(sym.IMPRIMIR, yyline, yycolumn,yytext());}
<YYINITIAL> {CONST} {return new Symbol(sym.CONST, yyline, yycolumn,yytext());}
<YYINITIAL> {VAR} {return new Symbol(sym.VAR, yyline, yycolumn,yytext());}
<YYINITIAL> {INT} {return new Symbol(sym.INT, yyline, yycolumn,yytext());}
<YYINITIAL> {DOUBLE} {return new Symbol(sym.DOUBLE, yyline, yycolumn,yytext());}
<YYINITIAL> {STRING} {return new Symbol(sym.STRING, yyline, yycolumn,yytext());}
<YYINITIAL> {TRUE} {return new Symbol(sym.TRUE, yyline, yycolumn,yytext());}
<YYINITIAL> {FALSE} {return new Symbol(sym.FALSE, yyline, yycolumn,yytext());}
<YYINITIAL> {IF} {return new Symbol(sym.IF, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOL} {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}

<YYINITIAL> {ID} {
Tokens.tokens.GuardarTokes(String.valueOf(yytext()),"ID",String.valueOf(yycolumn),String.valueOf(yyline));
return new Symbol(sym.ID, yyline, yycolumn,yytext());}

<YYINITIAL> {DECIMAL} {
Tokens.tokens.GuardarTokes(String.valueOf(yytext()),"double",String.valueOf(yycolumn),String.valueOf(yyline));
return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {ENTERO} {
Tokens.tokens.GuardarTokes(String.valueOf(yytext()),"int",String.valueOf(yycolumn),String.valueOf(yyline));
return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}

<YYINITIAL> {CADENA} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
Tokens.tokens.GuardarTokes(String.valueOf(yytext()),"string",String.valueOf(yycolumn),String.valueOf(yyline));
    return new Symbol(sym.CADENA, yyline, yycolumn,cadena);
    }
<YYINITIAL> {CARACTER} {
    String caracter = yytext();
    caracter = caracter.substring(1, caracter.length()-1);
    if (caracter.equals("\\")) {
            caracter.replace("\\", "");
            Tokens.tokens.GuardarTokes(String.valueOf(yytext()),"char",String.valueOf(yycolumn),String.valueOf(yyline));
            return new Symbol(sym.CARACTER, yyline, yycolumn,caracter);
        } else if (caracter.equals("\'")) {
            caracter.replace("\\", "");
            Tokens.tokens.GuardarTokes(String.valueOf(yytext()),"char",String.valueOf(yycolumn),String.valueOf(yyline));
            return new Symbol(sym.CARACTER, yyline, yycolumn,caracter);
        }
    return new Symbol(sym.CARACTER, yyline, yycolumn,caracter);
    }

<YYINITIAL> {FINCADENA} {return new Symbol(sym.FINCADENA, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR1} {return new Symbol(sym.PAR1, yyline, yycolumn,yytext());}
<YYINITIAL> {PAR2} {return new Symbol(sym.PAR2, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE1} {return new Symbol(sym.LLAVE1, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVE2} {return new Symbol(sym.LLAVE2, yyline, yycolumn,yytext());}
<YYINITIAL> {INCREMENTO} {return new Symbol(sym.INCREMENTO, yyline, yycolumn,yytext());}
<YYINITIAL> {DECREMENTO} {return new Symbol(sym.DECREMENTO, yyline, yycolumn,yytext());}
<YYINITIAL> {MAS} {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> {MODULO} {return new Symbol(sym.MODULO, yyline, yycolumn,yytext());}
<YYINITIAL> {MULT} {return new Symbol(sym.MULT, yyline, yycolumn,yytext());}
<YYINITIAL> {DIV} {return new Symbol(sym.DIV, yyline, yycolumn,yytext());}
<YYINITIAL> {POTENCIA} {return new Symbol(sym.POTENCIA, yyline, yycolumn,yytext());}
<YYINITIAL> {IGUAL} {return new Symbol(sym.IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {DIFERENTE} {return new Symbol(sym.DIFERENTE, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYORIGUAL} {return new Symbol(sym.MAYORIGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {MENORIGUAL} {return new Symbol(sym.MENORIGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOR} {return new Symbol(sym.MENOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYOR} {return new Symbol(sym.MAYOR, yyline, yycolumn,yytext());}
<YYINITIAL> {NOT} {return new Symbol(sym.NOT, yyline, yycolumn,yytext());}
<YYINITIAL> {OR} {return new Symbol(sym.OR, yyline, yycolumn,yytext());}
<YYINITIAL> {DOSPUNTOS} {return new Symbol(sym.DOSPUNTOS, yyline, yycolumn,yytext());}
<YYINITIAL> {AND} {return new Symbol(sym.AND, yyline, yycolumn,yytext());}
<YYINITIAL> {BLANCOS} {}
<YYINITIAL> {COMENTARIOS} {}
<YYINITIAL> . {
                listaErrores.add(new Errores("LEXICO","El caracter "+
                yytext()+" NO pertenece al lenguaje", yyline, yycolumn));
                ManejoErrores.Error.GuardarError(String.valueOf(yytext()),"Error Léxico: Carácter no reconocido",String.valueOf(yycolumn),String.valueOf(yyline)); System.out.println("Error Lexico: " + yytext() + " | Fila:" + yyline + " | Columna: " + yycolumn);
}
