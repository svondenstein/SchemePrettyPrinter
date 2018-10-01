/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;
import java.io.*;

class Scanner {
    private PushbackInputStream in;
    private byte[] buf = new byte[1000];
    private Token putBack = null;

    Scanner(InputStream i) {
        in = new PushbackInputStream(i);
    }

    void putBack(Token token) {
        putBack = token;
    }

    private boolean isInt(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private boolean isInitialIdent(char ch) {
        return  (ch >= 'A' && ch <= 'Z') ||
                (ch >= 'a' && ch <= 'z') ||
                ch == '!' ||
                ch == '$' ||
                ch == '%' ||
                ch == '&' ||
                ch == '*' ||
                ch == '/' ||
                ch == ':' ||
                ch == '<' ||
                ch == '=' ||
                ch == '>' ||
                ch == '?' ||
                ch == '^' ||
                ch == '_' ||
                ch == '~' ||
                ch == '+' ||
                ch == '-';
    }

    private boolean isSubsequentIdent(char ch) {
        return  isInitialIdent(ch) ||
                isInt(ch) ||
                ch == '.' ||
                ch == '@';
    }

    // Returns a copy of the buffer with the exact length of the information it contains
    private String bufToString(int strLen) {
        byte[] ret = new byte[strLen+1];
        for(int j = 0; j <= strLen; j++) {
            ret[j] = buf[j];
        }
        // Reset buffer
        buf = new byte[1000];
        return new String(ret);
    }

    private int bufToInt(int numLen) {
        int ret = 0;
        // Loops along the number storing each value and multiplying it by 10 every time.
        for (int i = 0; i <= numLen; i++) {
            ret *= 10;
            ret += buf[i] - 48;
        }
        // Reset buffer
        buf = new byte[1000];
        return ret;
    }

    Token getNextToken() {
        // Check for putback tokens
        if (putBack != null) {
            Token ret = putBack;
            putBack = null;
            return ret;
        }
        int bite = -1;

        // It would be more efficient if we'd maintain our own input buffer
        // and read characters out of that buffer, but reading individual
        // characters from the input stream is easier.
        try {
            bite = in.read();
        } catch (IOException e) {
            System.err.println("We fail: " + e.getMessage());
        }
        if (bite == -1)
            return null;

        // Ignore white space
        while (bite == 32 || bite == 10 || bite == 12 || bite == 13) {
            try {
                bite = in.read();
            } catch (IOException e) {
                System.err.println("We fail: " + e.getMessage());
            }
        }

        // Ignore comments
        if (bite == 59) {
            try {
                while(in.read() != 10);
                bite = in.read();
            } catch (IOException e) {
                System.err.println("We fail: " + e.getMessage());
                bite = -1;
            }
        }

        char ch = (char) bite;

        // Special characters
        if (ch == '\'')
            return new Token(Token.QUOTE);
        else if (ch == '(')
            return new Token(Token.LPAREN);
        else if (ch == ')')
            return new Token(Token.RPAREN);
        else if (ch == '.')
            // We ignore the special identifier `...'.
            return new Token(Token.DOT);

        // Boolean constants
        else if (ch == '#') {
            try {
                bite = in.read();
            } catch (IOException e) {
                System.err.println("We fail: " + e.getMessage());
            }
            if (bite == -1) {
                System.err.println("Unexpected EOF following #");
                return null;
            }
            ch = (char) bite;
            if (ch == 't')
                return new Token(Token.TRUE);
            else if (ch == 'f')
                return new Token(Token.FALSE);
            else {
                System.err.println("Illegal character '" + ch + "' following #");
                return getNextToken();
            }
        }

        //String constants
        else if (ch == '"') {
            int len;
            for (len = 0; len < buf.length; len++){
                try {
                    ch = (char) in.read();
                    if (ch == '\\') {
                        buf[len] = (byte) '\\';
                        len++;
                        ch = (char) in.read();
                        buf[len] = (byte) ch;
                        continue;
                    }
                    if (ch == '"') {
                        break;
                    }
                    buf[len] = (byte) ch;
                } catch (IOException e) {
                    System.err.println("We fail: " + e.getMessage());
                }
            }
            return new StrToken(bufToString(len-1));
        }

        //Integer constants
        else if (isInt(ch)) {
            int len = 0;
            try {
                for(len = 0; len < buf.length; len++) {
                    buf[len] = (byte) bite;
                    bite = in.read();
                    ch = (char) bite;
                    if (isInt(ch))
                        continue;
                    else if (isInitialIdent(ch) || isSubsequentIdent(ch)) {
                        throw new IOException("invalid input; expected integer, not \'" + ch + "\'");
                    } else {
                        in.unread(bite);
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("We fail: " + e.getMessage());
            }
            return new IntToken(bufToInt(len));
        }

        //Identifiers
        else if (isInitialIdent(ch)) {
            int len;
            for (len = 0; len < buf.length; len++) {
                buf[len] = (byte) bite;
                try {
                    bite = in.read();
                } catch (IOException e) {
                    System.err.println("We fail: " + e.getMessage());
                }
                if (!isSubsequentIdent((char) bite)) {
                    try {
                        in.unread(bite);
                    } catch (IOException e) {
                        System.err.println("We fail " + e.getMessage());
                    }
                    break;
                }
            }
            return new IdentToken(bufToString(len).toLowerCase());
        }


        //Illegal character
        else {
            System.err.println("Illegal input character '" + ch + "\'");
            return getNextToken();
        }
    }
}
