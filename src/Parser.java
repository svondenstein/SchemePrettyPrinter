// Parser.java -- the implementation of class Parser
//
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

  public Parser(Scanner s) { scanner = s; }
  
  public Node parseExp() {
    Token token = scanner.getNextToken();
    if(token == null){
        return null;
    }
    else if(token.getType() == TokenType.LPAREN){
        return parseRest();
    }
    else if(token.getType() == TokenType.FALSE){
        return new BooleanLit(false);
    }
    else if(token.getType() == TokenType.TRUE){
        return new BooleanLit(true);
    }
    else if(token.getType() == TokenType.QUOTE){
        return new Cons(new Ident("\'"), new Cons(parseExp(), new Nil()));
    }
    else if(token.getType() == TokenType.INT){
        return new IntLit(token.getIntVal());
    }
    else if(token.getType() == TokenType.STRING){
        return new StrLit(token.getStrVal());
    }
    else if(token.getType() == TokenType.RPAREN){
        System.err.println("Parsing Error. Unexpected Token of Type: " + token.getName());
        return new Nil();
    }
    else if(token.getType() == TokenType.DOT) {
        System.err.println("Parsing Error. Unexpected Token of Type: " + token.getName());
        return new Nil();
    }
    else if(token.getType() == TokenType.IDENT){
        return new Ident(token.getName());
    }
    else {
        System.err.println("Parsing Error. Unexpected Token of Type: " + token.getName());
        return new Nil();

        //dot and right paren need to return error illegal expression. DONE
        //one thing we can do is return the empty list to somehow print some of the expression
        //Either return nil or empty list.
        //parentheses are not stored in the parse tree. we can figure out where the parentheses go based off of the special flag
        // off the cons node.
        //nil is the end of file.
        //special are the objects that represent the printing strategy
        //We need to implement the if then else chain on parseList
            //if the car is anything but an identifier then we need to figure out what it is using string comparison
            //add a method to class Ident to extract the name from the Ident node that gets the name from the Ident token.
    }
  }


    protected Node parseExp(Token token) {
        if(token == null){
            return new Nil();
        }
        else if(token.getType() == TokenType.LPAREN){
            return parseRest();
        }
        else if(token.getType() == TokenType.FALSE){
            return new BooleanLit(false);
        }
        else if(token.getType() == TokenType.TRUE){
            return new BooleanLit(true);
        }
        else if(token.getType() == TokenType.QUOTE){
            return new Cons(new Ident("\'"), new Cons(parseExp(), new Nil()));
        }
        else if(token.getType() == TokenType.INT){
            return new IntLit(token.getIntVal());
        }
        else if(token.getType() == TokenType.STRING){
            return new StrLit(token.getStrVal());
        }
        else if(token.getType() == TokenType.RPAREN){
            System.err.println("Parsing Error. Unexpected Token of Type: " + token.getName());
            return new Nil();
        }
        else if(token.getType() == TokenType.DOT) {
            System.err.println("Parsing Error. Unexpected Token of Type: " + token.getName());
            return new Nil();
        }
        else if(token.getType() == TokenType.IDENT){
            return new Ident(token.getName());
        }
        else {
            System.err.println("Parsing Error. Unexpected Token of Type: " + token.getName());
            return new Nil();
        }
    }
  
  protected Node parseRest() {
      //Here we either need to left factor this grammar or nest the if statements to get around the common left factor.
      // this can either be
      //Basically we need to expand the grammar past exp+ [. exp*] )
      Token token = scanner.getNextToken();
      if(token.getType() == TokenType.RPAREN) {
          return new Nil();
      }
      else {
          return new Cons(parseExp(token), parseMore());
      }

  }

  /* More is a new non terminal that we added to get rid of the common left factor and clear up the rest
  grammar.
      More -> rest
          |  . exp )
   */

  protected Node parseMore(){
      Token token = scanner.getNextToken();
      if(token.getType() == TokenType.DOT){
          token  = scanner.getNextToken();
          if(token.getType() != TokenType.RPAREN){
              return new Cons(parseExp(token), new Nil());
          }
          else{
              System.err.println("Parse Error. Unexpected Token of Type: " + token.getName());
              return new Nil();
          }
      }
      else {
          return parseRest(token);
      }
  }
    //Same as parseRest, but this handles the case when it is called from look ahead.
    protected Node parseRest(Token token) {
        if(token.getType() == TokenType.RPAREN) {
            return new Nil();
        }
        else {
            return new Cons(parseExp(token), parseMore());
        }
    }
}
