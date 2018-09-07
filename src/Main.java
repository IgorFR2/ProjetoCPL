import compilador.lexer.Lexer;
import compilador.lexer.LexerException;
import compilador.node.Start;
import compilador.parser.Parser;
import compilador.parser.ParserException;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

public class Main {
  public static void main(String[] args) throws IOException, ParserException, LexerException {
    ComentarioAninhado lexer =
        new ComentarioAninhado(new PushbackReader(new FileReader(args[0]), 1024));
    Parser parser = new Parser(lexer);
    Start ast = parser.parse();

    Tradutor translator = new Tradutor();
    ast.apply(translator);
  }
}
