/*
 * @authors Thomas Johnson, Stephen Vondenstein
 * Course: CSC 4101
 */
package edu.lsu.CSC4101.SchemePrettyPrinter;

class Parser {
  private Scanner scanner;

  public Parser(Scanner s) {
      scanner = s;
  }
  
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
        if(token.getName().compareTo("quote") == 0)
            return new Ident("\'");
        else
            return new Ident(token.getName());
    }
    else {
        System.err.println("Parsing Error. Unexpected Token of Type: " + token.getName());
        return new Nil();
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
            if(token.getName().compareTo("quote") == 0)
                return new Ident("\'");
            else
                return new Ident(token.getName());
        }
        else {
            System.err.println("Parsing Error. Unexpected Token of Type: " + token.getName());
            return new Nil();
        }
    }
  
  protected Node parseRest() {
      Token token = scanner.getNextToken();
      if(token.getType() == TokenType.RPAREN) {
          return new Nil();
      }
      else {
          return new Cons(parseExp(token), parseMore());
      }

  }

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

    protected Node parseRest(Token token) {
        if(token.getType() == TokenType.RPAREN) {
            return new Nil();
        }
        else {
            return new Cons(parseExp(token), parseMore());
        }
    }
}
