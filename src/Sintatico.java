import compilador.parser.*;
import compilador.lexer.*;
import compilador.node.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

public class Sintatico {
  public static void main(String[] args) throws IOException, ParserException, LexerException {
    comentarioAninhado lexer =
        new comentarioAninhado(new PushbackReader(new FileReader(args[0]), 1024));
    Parser p = new Parser(lexer);
    System.out.println(p.parse().toString());
  }
}
