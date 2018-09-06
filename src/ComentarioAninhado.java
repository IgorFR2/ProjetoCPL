import compilador.lexer.Lexer;
import compilador.lexer.LexerException;
import compilador.node.*;

public class ComentarioAninhado extends Lexer {
  private int count;
  private TComentario comment;
  private StringBuffer text;

  // Definimos o construtor
  public ComentarioAninhado(java.io.PushbackReader in) {
    super(in);
  }

  // Definimos um filtro que reconhece comentarios aninhados
  @Override
  protected void filter() throws LexerException {
    // Coloque o código aqui...
    { // if we are in the comment state
      if (state.equals(State.COMENTARIO)) { // if we are just entering this state
        if (comment == null) { // The token is supposed to be a comment.
          // We keep a reference to it and set the count to one
          comment = (TComentario) token;
          text = new StringBuffer(comment.getText());
          count = 1;
          token = null; // continue to scan the input.
        } else { // we were already in the comment state
          text.append(token.getText()); // accumulate the text.
          if (token instanceof TComentario) count++;
          else if (token instanceof TComentarioFim) count--;
          if (count != 0) token = null; // continue to scan the input.
          else { // comment.setText(text.toString());
            token = comment; // return a comment with the full text.
            state = State.NORMAL; // go back to normal.
            comment = null; // We release this reference.
          }
        }
      }
    }
  }
}
