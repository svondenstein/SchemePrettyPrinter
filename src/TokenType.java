/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
interface TokenType {
    int QUOTE = 0;			// '
    int LPAREN = 1;			// (
    int RPAREN = 2;                 // )
    int DOT = 3;			// .
    int TRUE = 4;			// #t
    int FALSE = 5;			// #f
    int INT = 6;			// integer constant
    int STRING = 7;			// string constant
    int IDENT = 8;			// identifier
}
