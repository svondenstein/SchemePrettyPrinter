/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
import java.io.*;

class Scanner {
    private PushbackInputStream in;
    private byte[] buf = new byte[1000];

    public Scanner(InputStream i) {
        in = new PushbackInputStream(i);
    }

    private boolean isSpecialInitial(char ch) {
        if (ch == '!' || ch == '$' || ch == '%' || ch == '&' || ch == '*' || ch == '/' || ch == ':' || ch == '<' || ch == '=' || ch == '>' || ch == '?' || ch == '^' || ch == '_' || ch == '~')
            return true;
        else
            return false;
    }

    private boolean isALetter(char ch) {
        if (ch >= 'A' && ch <= 'Z')
            return true;
        else if (ch >= 'a' && ch <= 'z')
            return true;
        else
            return false;
    }

    private boolean isADigit(char ch) {
        if (ch >= '0' && ch <= '9')
            return true;
        else
            return false;
    }

    private boolean isSpecialSubsequent(char ch) {
        if (ch == '+' || ch == '-' || ch == '.' || ch == '@')
            return true;
        else
            return false;
    }

    private boolean isInitial(char ch) {
        if (isALetter(ch) || isSpecialInitial(ch))
            return true;
        else
            return false;
    }

    private boolean isSubsequent(char ch) {
        if (isInitial(ch) || isADigit(ch) || isSpecialSubsequent(ch))
            return true;
        else
            return false;
    }

    private boolean isPeculiarIdentifier(char ch) {
        if (ch == '+' || ch == '-')
            return true;
        else
            return false;
    }

    private byte[] bufCleaner() {
        int i = 0;
        while(!(buf[i] == 0)) {
            i++;
        }
        byte[] ret = new byte[i];
        for(int j = 0; j < i; j++) {
            ret[j] = buf[j];
        }
        return ret;
    }

    public Token getNextToken() {
        int bite = -1;
        //buf = new byte[1000];
        for (int count = 0; count < 1000; count++) {
            buf[count] = 0;
        }

        // It would be more efficient if we'd maintain our own input buffer
        // and read characters out of that buffer, but reading individual
        // characters from the input stream is easier.
        try {
            bite = in.read();
        } catch (IOException e) {
            System.err.println("We fail: " + e.getMessage());
        }

        while (bite == 32 || bite == 10 || bite == 12 || bite == 13) {
            try {
                bite = in.read();
            } catch (IOException e) {
                System.err.println("We fail: " + e.getMessage());
            }
        }

        if (bite == 59) {
            while (bite != 10) {
                try {
                    bite = in.read();
                } catch (IOException e) {
                    System.err.println("We fail: " + e.getMessage());
                }
            }
            try {
                bite = in.read();
            } catch (IOException e) {
                System.err.println("We fail: " + e.getMessage());
            }
        }


        if (bite == -1)
            return null;

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
                System.err.println("Illegal character '" + (char) ch + "' following #");
                return getNextToken();
            }
        }

        // String constants
        else if (ch == '"') {
            int i = 0;
            try {
                bite = in.read();
            } catch (IOException e) {
                System.err.println("We fail: " + e.getMessage());
            }
            while (bite != 34) {
                buf[i] = (byte) bite;
                try {
                    bite = in.read();
                } catch (IOException e) {
                    System.err.println("We fail: " + e.getMessage());
                }
                i++;
            }
            return new StrToken(new String(buf));
        }

        // Integer constants
        else if (isADigit(ch)) {
            int i = 0;
            int value = 0;

            while (bite >= 48 && bite <= 57) {
                buf[i] = (byte) bite;
                try {
                    bite = in.read();
                } catch (IOException e) {
                    System.err.println("We fail: " + e.getMessage());
                }
                i++;
            }
            try {
                in.unread(bite);
            } catch (IOException e) {
                System.err.println("We fail: " + e.getMessage());
            }

            //Saves the length of the number which is currently where they index i lies
            int numberLength = i;
            i = 0;
            //Loops along the number storing each value and multiplying it by 10 every time.
            while(i < numberLength){
                value *= 10;
                value += buf[i] - 48;
                i++;
            }

            return new IntToken(value);
        }

        // Identifiers
        else if (isInitial(ch) || isPeculiarIdentifier(ch)) {
            if (isInitial(ch)) {
                int i = 0;
                while (isSubsequent((char) bite)) {
                    buf[i] = (byte) bite;
                    try {
                        bite = in.read();
                    } catch (IOException e) {
                        System.err.println("We fail: " + e.getMessage());
                    }
                    i++;
                }
                // put the character after the identifier back into the input
                // in->putback(ch);
                try {
                    in.unread(bite);
                } catch (IOException e) {
                    System.err.println("We fail: " + e.getMessage());
                }
            } else {
                buf[0] = (byte) bite;
            }
            String result = new String(bufCleaner()).toLowerCase();
            return new IdentToken(result);
        }

        // Illegal character
        else {
            System.err.println("Illegal input character '" + (char) ch + '\'');
            return getNextToken();
        }
    };
}
