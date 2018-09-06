import compilador.lexer.LexerException;
import compilador.node.Token;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

public class AnalisadorLexico {
  private static void imprimirFormatadoToken(Token token) {
    switch (token.getClass().getSimpleName()) {
      case "TEnter":
        System.out.print("\n");
        break;
      case "TEspaco":
        System.out.print(" ");
        break;
      case "TTab":
        System.out.print("\t");
        break;
      default:
        System.out.print(token.getClass().getSimpleName() + ' ');
        break;
    }
  }

  private static void imprimirErro(Token token) {
    if (token.getClass().getSimpleName().equals("TComentarioFimErro")) {
      System.out.print(
          "Erro de comentario de bloco. Linha: "
              + token.getLine()
              + ", posicao:"
              + token.getPos()
              + ". :"
              + token.getClass().getSimpleName());
    }
  }

  private static boolean isEOF(Token token) {
    return token.getClass().getSimpleName().equals("EOF");
  }

  private static boolean isInvalidToken(Token token) {
    String tokenName = token.getClass().getSimpleName();
    return tokenName.equals("TComentarioFimErro") || tokenName.equals("EOF");
  }

  public static void verificarArquivo(String arquivo) throws FileNotFoundException {
    Token token;

    ComentarioAninhado lexer =
        new ComentarioAninhado(new PushbackReader(new FileReader(arquivo), 1024));

    try {
      token = lexer.next();
      while (!isEOF(token)) {
        if (!isInvalidToken(token)) {
          imprimirFormatadoToken(token);
        } else {
          imprimirErro(token);
        }
        token = lexer.next();
      }
    } catch (LexerException | IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    try {
      verificarArquivo(args[0]);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
