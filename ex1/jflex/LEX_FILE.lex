/***************************/
/* FILE NAME: LEX_FILE.lex */
/***************************/

import java_cup.runtime.*;

%%

%class Lexer
%line
%column
%cupsym TokenNames
%cup

%{
	private Symbol symbol(int type)               {return new Symbol(type, yyline, yycolumn);}
	private Symbol symbol(int type, Object value) {return new Symbol(type, yyline, yycolumn, value);}

	public int getLine() { return yyline + 1; } 
	public int getTokenStartPosition() { return yycolumn + 1; } 
%}

LineTerminator  = \r|\n|\r\n
WhiteSpace      = {LineTerminator} | [ \t]
INTEGER         = 0 | [1-9][0-9]*
ID              = [a-zA-Z][a-zA-Z0-9]*
STRING          = \"[a-zA-Z]*\"

// הגדרת קבוצת התווים המותרים בתוך הערות לפי איור 2
CommentChar     = [a-zA-Z0-9\ \t\r\n\(\)\[\]\{\}\?\!\+\-\*\/\.\;]

// מצבים לקסיקליים לטיפול בהערות
%state LINE_COMMENT
%state BLOCK_COMMENT

%%

<YYINITIAL> {
    // מעברים למצבי הערות
    "//"                { yybegin(LINE_COMMENT); }
    "/*"                { yybegin(BLOCK_COMMENT); }

    // Operators & Punctuation
    "+"                 { return symbol(TokenNames.PLUS);}
    "-"                 { return symbol(TokenNames.MINUS);}
    "*"                 { return symbol(TokenNames.TIMES);}
    "/"                 { return symbol(TokenNames.DIVIDE);}
    "="                 { return symbol(TokenNames.EQ);}
    "<"                 { return symbol(TokenNames.LT);}
    ">"                 { return symbol(TokenNames.GT);}
    ":="                { return symbol(TokenNames.ASSIGN);}
    "("                 { return symbol(TokenNames.LPAREN);}
    ")"                 { return symbol(TokenNames.RPAREN);}
    "["                 { return symbol(TokenNames.LBRACK);}
    "]"                 { return symbol(TokenNames.RBRACK);}
    "{"                 { return symbol(TokenNames.LBRACE);}
    "}"                 { return symbol(TokenNames.RBRACE);}
    ","                 { return symbol(TokenNames.COMMA);}
    "."                 { return symbol(TokenNames.DOT);}
    ";"                 { return symbol(TokenNames.SEMICOLON);}

    // Keywords
    "class"             { return symbol(TokenNames.CLASS);}
    "nil"               { return symbol(TokenNames.NIL);}
    "array"             { return symbol(TokenNames.ARRAY);}
    "while"             { return symbol(TokenNames.WHILE);}
    "int"               { return symbol(TokenNames.TYPE_INT);}
    "void"              { return symbol(TokenNames.TYPE_VOID);}
    "extends"           { return symbol(TokenNames.EXTENDS);}
    "return"            { return symbol(TokenNames.RETURN);}
    "new"               { return symbol(TokenNames.NEW);}
    "if"                { return symbol(TokenNames.IF);}
    "else"              { return symbol(TokenNames.ELSE);}
    "string"            { return symbol(TokenNames.TYPE_STRING);}
    "and"               { return symbol(TokenNames.AND);}
    "or"                { return symbol(TokenNames.OR);}

    // Literals and Identifiers
    {STRING}            { return symbol(TokenNames.STRING, yytext()); }
    {INTEGER}           { 
                            long val = Long.parseLong(yytext());
                            if (val > 32767) { 
                                return symbol(TokenNames.ERROR);
                            }
                            return symbol(TokenNames.INT, Integer.valueOf(yytext()));
                        }
    {ID}                { return symbol(TokenNames.ID, yytext());}
    {WhiteSpace}        { /* just skip */ }

    <<EOF>>             { return symbol(TokenNames.EOF);}
    [^]                 { return symbol(TokenNames.ERROR);}
}

<LINE_COMMENT> {
    {LineTerminator}    { yybegin(YYINITIAL); /* חזרה למצב רגיל בסוף שורה */ }
    {CommentChar}       { /* התעלמות מתווים חוקיים */ }
    [^]                 { return symbol(TokenNames.ERROR); /* תו לא חוקי בהערה */ }
    <<EOF>>             { return symbol(TokenNames.EOF); }
}

<BLOCK_COMMENT> {
    "*/"                { yybegin(YYINITIAL); /* סיום הערת בלוק */ }
    {CommentChar}       { /* התעלמות מתווים חוקיים */ }
    [^]                 { return symbol(TokenNames.ERROR); /* תו לא חוקי בהערה */ }
    <<EOF>>             { return symbol(TokenNames.ERROR); /* שגיאה: הגענו לסוף קובץ וההערה לא נסגרה */ }
}