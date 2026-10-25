public interface TokenNames {
    /* terminals */
    public static final int EOF = 0;

    // Operators
    public static final int PLUS = 1;
    public static final int MINUS = 2;
    public static final int TIMES = 3;
    public static final int DIVIDE = 4;
    public static final int EQ = 5;
    public static final int LT = 6;
    public static final int GT = 7;
    public static final int AND = 8;
    public static final int OR = 9;
    public static final int ASSIGN = 10;

    // Punctuation
    public static final int LPAREN = 11;
    public static final int RPAREN = 12;
    public static final int LBRACK = 13;
    public static final int RBRACK = 14;
    public static final int LBRACE = 15;
    public static final int RBRACE = 16;
    public static final int COMMA = 17;
    public static final int DOT = 18;
    public static final int SEMICOLON = 19;

    // Keywords
    public static final int ARRAY = 20;
    public static final int CLASS = 21;
    public static final int EXTENDS = 22;
    public static final int NEW = 23;
    public static final int NIL = 24;
    public static final int IF = 25;
    public static final int ELSE = 26;
    public static final int WHILE = 27;
    public static final int RETURN = 28;
    public static final int TYPE_INT = 29;
    public static final int TYPE_STRING = 30;
    public static final int TYPE_VOID = 31;

    // Tokens with values
    public static final int ID = 32;
    public static final int INT = 33; 
    public static final int STRING = 34;
    public static final int ERROR = 35;
}