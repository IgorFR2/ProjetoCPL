import java.io.FileReader;
import java.io.PushbackReader;

public class Main {
  public static boolean escreva(int lin, int pos, String token) {
    try {
      switch (token) {
        case "TEnter":
          System.out.print("\n");
          return true;
        case "TEspaco":
          System.out.print(" ");
          return true;
        case "TTab":
          System.out.print("\t");
          return true;
        case "TComentarioFimErro":
          System.out.print(
              "Erro de comentario de bloco. Linha: " + lin + ", posicao:" + pos + ". :" + token);
          return false;
        case "EOF":
          return false;
        default:
          System.out.print(token + ' ');
          return true;
      }
    } catch (Exception error) {
      System.out.print(error.getMessage());
      return true;
    }
  }

  public static void main(String[] args) {
    boolean eof = true;
    if (args.length > 0) {
      try {
        /* Form our AST */
        ComentarioAninhado lexer =
            new ComentarioAninhado(new PushbackReader(new FileReader(args[0]), 1024));

        while (eof) {
          try {
            eof =
                escreva(
                    lexer.peek().getLine(),
                    lexer.peek().getPos(),
                    lexer.next().getClass().getSimpleName());
          } catch (Exception error) {
            System.out.print(error.getMessage() + '\n');
          }
        }
      } catch (Exception e) {
        System.out.println("batata");
      }
    } else {
      System.err.println("usage: java simpleAdder inputFile");
      System.exit(1);
    }
  }
}
