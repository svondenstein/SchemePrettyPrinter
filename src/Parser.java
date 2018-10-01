/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;
// Parser.java -- the implementation of class Parser
// Defines
//
//   class Parser;
//
// Parses the language
//
//   exp  ->  ( rest
//         |  #f
//         |  #t
//         |  ' exp
//         |  integer_constant
//         |  string_constant
//         |  identifier
//    rest -> )
//         |  exp+ [. exp] )
//
// and builds a parse tree.  Lists of the form (rest) are further
// `parsed' into regular lists and special forms in the constructor
// for the parse tree node class Cons.  See Cons.parseList() for
// more information.
//
// The parser is implemented as an LL(0) recursive descent parser.
// I.e., parseExp() expects that the first token of an exp has not
// been read yet.  If parseRest() reads the first token of an exp
// before calling parseExp(), that token must be put back so that
// it can be reread by parseExp() or an alternative version of
// parseExp() must be called.
//
// If EOF is reached (i.e., if the scanner returns a NULL) token,
// the parser returns a NULL tree.  In case of a parse error, the
// parser discards the offending token (which probably was a DOT
// or an RPAREN) and attempts to continue parsing with the next token.
class Parser {
    private Scanner scanner;

    Parser(Scanner s) {
        scanner = s;
    }

    Node parseExp() {
        Token token = scanner.getNextToken();
        if (token == null)
            return null;
        int tt = token.getType();
        switch (tt) {
            case TokenType.LPAREN: return parseRest();
            case TokenType.FALSE: return new BooleanLit(false);
            case TokenType.TRUE: return new BooleanLit(true);
            case TokenType.QUOTE: return new Cons(new Ident("\'"), new Cons(parseExp(), new Nil()));
            case TokenType.INT: return new IntLit(token.getIntVal());
            case TokenType.STRING: return new StrLit(token.getStrVal());
            case TokenType.IDENT: {
                if(token.getName().compareTo("quote") == 0)
                    return new Cons(new Ident("\'"), new Cons(parseExp(), new Nil()));
                else return new Ident(token.getName());
            }
            case TokenType.RPAREN: {
                System.err.println("Parsing Error. Unexpected Token of Type: )");
                return parseExp();
            }
            case TokenType.DOT: {
                System.err.println("Parsing Error. Unexpected Token of Type: .");
                return parseExp();
            }
            default: {
                System.err.println("Parsing Error. Unexpected Token of Type: " + token.getName());
                return parseExp();
            }
        }
    }

    private Node parseRest() {
        Token token = scanner.getNextToken();
        int tt = token.getType();
        if(tt == TokenType.RPAREN) {
            return new Nil();
        } else if (tt == TokenType.DOT) {
            token = scanner.getNextToken();
            tt = token.getType();
            if (tt != TokenType.RPAREN) {
                scanner.putBack(token);
                return new Cons(parseExp(), new Nil());
            } else {
                System.err.println("Parsing Error. Unexpected Token of Type: )");
                return parseExp();
            }
        }
        else {
            scanner.putBack(token);
            return new Cons(parseExp(), parseRest());
        }
    }
}
