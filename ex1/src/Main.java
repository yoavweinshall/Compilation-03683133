import java.io.*;
import java.io.PrintWriter;
import java_cup.runtime.Symbol;

public class Main {

    // Helper function to convert the Token ID to its string representation for printing
    private static String getTokenName(int sym) {
        switch (sym) {
            case TokenNames.PLUS: return "PLUS";
            case TokenNames.MINUS: return "MINUS";
            case TokenNames.TIMES: return "TIMES";
            case TokenNames.DIVIDE: return "DIVIDE";
            case TokenNames.EQ: return "EQ";
            case TokenNames.LT: return "LT";
            case TokenNames.GT: return "GT";
            case TokenNames.AND: return "AND";
            case TokenNames.OR: return "OR";
            case TokenNames.ASSIGN: return "ASSIGN";
            case TokenNames.LPAREN: return "LPAREN";
            case TokenNames.RPAREN: return "RPAREN";
            case TokenNames.LBRACK: return "LBRACK";
            case TokenNames.RBRACK: return "RBRACK";
            case TokenNames.LBRACE: return "LBRACE";
            case TokenNames.RBRACE: return "RBRACE";
            case TokenNames.COMMA: return "COMMA";
            case TokenNames.DOT: return "DOT";
            case TokenNames.SEMICOLON: return "SEMICOLON";
            case TokenNames.ARRAY: return "ARRAY";
            case TokenNames.CLASS: return "CLASS";
            case TokenNames.EXTENDS: return "EXTENDS";
            case TokenNames.NEW: return "NEW";
            case TokenNames.NIL: return "NIL";
            case TokenNames.IF: return "IF";
            case TokenNames.ELSE: return "ELSE";
            case TokenNames.WHILE: return "WHILE";
            case TokenNames.RETURN: return "RETURN";
            case TokenNames.TYPE_INT: return "TYPE_INT";
            case TokenNames.TYPE_STRING: return "TYPE_STRING";
            case TokenNames.TYPE_VOID: return "TYPE_VOID";
            case TokenNames.ID: return "ID";
            case TokenNames.INT: return "INT";
            case TokenNames.STRING: return "STRING";
            case TokenNames.EOF: return "EOF";
            case TokenNames.ERROR: return "ERROR";
            default: return "UNKNOWN";
        }
    }

    static public void main(String argv[]) {
        Lexer l;
        Symbol s;
        FileReader fileReader;
        PrintWriter fileWriter;
        String inputFileName = argv[0];
        String outputFileName = argv[1];
        
        try {
            fileReader = new FileReader(inputFileName);
            fileWriter = new PrintWriter(outputFileName);
            l = new Lexer(fileReader);
            s = l.next_token();

            boolean isFirstToken = true;

            while (s.sym != TokenNames.EOF) {
                // Lexical error handling mechanism: Truncate file and write ERROR
                if (s.sym == TokenNames.ERROR) {
                    fileWriter.close();
                    fileWriter = new PrintWriter(outputFileName);
                    fileWriter.print("ERROR"); // Removed newline
                    fileWriter.close();
                    return; 
                }

                String tokenName = getTokenName(s.sym);
                String outputLine = "";

                // Print format for tokens with values (e.g., ID, INT, STRING)
                if (s.sym == TokenNames.ID || s.sym == TokenNames.INT || s.sym == TokenNames.STRING) {
                    outputLine = tokenName + "(" + s.value + ")[" + l.getLine() + "," + l.getTokenStartPosition() + "]";
                } 
                // Print format for tokens without values
                else {
                    outputLine = tokenName + "[" + l.getLine() + "," + l.getTokenStartPosition() + "]";
                }

                System.out.println(outputLine);
                
                // Add newline before printing to prevent a trailing blank line at EOF
                if (!isFirstToken) {
                    fileWriter.print("\n");
                }
                fileWriter.print(outputLine);
                isFirstToken = false;
                
                s = l.next_token();
            }
            
            l.yyclose();
            fileWriter.close();
        } catch (Exception e) {
            // Fallback error handling in case of unexpected exceptions
            try {
                PrintWriter errorWriter = new PrintWriter(outputFileName);
                errorWriter.print("ERROR"); // Removed newline
                errorWriter.close();
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            }
        }
    }
}