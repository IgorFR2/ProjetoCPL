import compilador.parser.*;
import compilador.lexer.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

public class Sintatico {
  public static void main(String[] args) throws IOException, ParserException, LexerException {
    ComentarioAninhado lexer =
        new ComentarioAninhado(new PushbackReader(new FileReader(args[0]), 1024));
    Parser p = new Parser(lexer);
    System.out.println(p.parse().toString());
  }
}
