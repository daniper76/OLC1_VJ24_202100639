package Analizador;

import java_cup.runtime.Symbol;
import java.util.LinkedList;
import Errores.Errores;

%%

%{
    public LinkedList<Errores> listaErrores = new LinkedList<>();
%}

%init{
    yyline = 1;
    yycolumn = 1;
    listaErrores = new LinkedList<>();
%init}

%cup
%class scanner
%public
%line
%char
%column
%full
//%debug
%ignorecase

PARIZQ="("
PARDER=")"
LLAVEIZQ="{"
LLAVEDER="}"
PYC=";"
MAS="+"
MENOS="-"
MENOR="<"
MAYOR=">"
IGUAL="="
OMITIR=[\ \r\t\f\n]+
INT=[0-9]+
DOUBLE=[0-9]+"."[0-9]+
ID=[a-zA-z][a-zA-Z0-9_]*
STRING = [\"]([^\"])*[\"]

PRINT="println"
ENTERO="int"
DECIMAL="double"
CADENA="string"
IF="if"
FOR="for"
WHILE="while"
BREAK="break"
ELSE="else"
MATCH="match"
TRUE="true"
FALSE="false"
BOOL="bool"


%%
<YYINITIAL> {PRINT} {return new Symbol(sym.PRINT, yyline, yycolumn,yytext());}
<YYINITIAL> {ENTERO} {return new Symbol(sym.ENTERO, yyline, yycolumn,yytext());}
<YYINITIAL> {DECIMAL} {return new Symbol(sym.DECIMAL, yyline, yycolumn,yytext());}
<YYINITIAL> {CADENA} {return new Symbol(sym.CADENA, yyline, yycolumn,yytext());}
<YYINITIAL> {TRUE} {return new Symbol(sym.TRUE, yyline, yycolumn,yytext());}
<YYINITIAL> {FALSE} {return new Symbol(sym.FALSE, yyline, yycolumn,yytext());}
<YYINITIAL> {FOR} {return new Symbol(sym.FOR, yyline, yycolumn,yytext());}
<YYINITIAL> {WHILE} {return new Symbol(sym.WHILE, yyline, yycolumn,yytext());}
<YYINITIAL> {ELSE} {return new Symbol(sym.ELSE, yyline, yycolumn,yytext());}
<YYINITIAL> {BREAK} {return new Symbol(sym.BREAK, yyline, yycolumn,yytext());}
<YYINITIAL> {MATCH} {return new Symbol(sym.MATCH, yyline, yycolumn,yytext());}
<YYINITIAL> {IF} {return new Symbol(sym.IF, yyline, yycolumn,yytext());}
<YYINITIAL> {BOOL} {return new Symbol(sym.BOOL, yyline, yycolumn,yytext());}
<YYINITIAL> {ID} {return new Symbol(sym.ID, yyline, yycolumn,yytext());}
<YYINITIAL> {INT} {return new Symbol(sym.INT, yyline, yycolumn,yytext());}
<YYINITIAL> {DOUBLE} {return new Symbol(sym.DOUBLE, yyline, yycolumn,yytext());}
<YYINITIAL> {STRING} {
    String cadena = yytext();
    cadena = cadena.substring(1, cadena.length()-1);
    return new Symbol(sym.STRING, yyline, yycolumn,cadena);
    }

<YYINITIAL> {PYC} {return new Symbol(sym.PYC, yyline, yycolumn,yytext());}
<YYINITIAL> {PARIZQ} {return new Symbol(sym.PARIZQ, yyline, yycolumn,yytext());}
<YYINITIAL> {PARDER} {return new Symbol(sym.PARDER, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVEIZQ} {return new Symbol(sym.LLAVEIZQ, yyline, yycolumn,yytext());}
<YYINITIAL> {LLAVEDER} {return new Symbol(sym.LLAVEDER, yyline, yycolumn,yytext());}
<YYINITIAL> {MAS} {return new Symbol(sym.MAS, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOS} {return new Symbol(sym.MENOS, yyline, yycolumn,yytext());}
<YYINITIAL> {IGUAL} {return new Symbol(sym.IGUAL, yyline, yycolumn,yytext());}
<YYINITIAL> {MENOR} {return new Symbol(sym.MENOR, yyline, yycolumn,yytext());}
<YYINITIAL> {MAYOR} {return new Symbol(sym.MAYOR, yyline, yycolumn,yytext());}
<YYINITIAL> {OMITIR} {}
<YYINITIAL> .        { listaErrores.add(new Errores("LEXICO","El caracter "+yytext()+" NO pertenece al lenguaje", yyline, yycolumn)); }