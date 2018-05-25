import compilador.lexer.Lexer;
import compilador.lexer.LexerException;

public class comentarioAninhado extends Lexer
{ 
	// Definimos o construtor
	public comentarioAninhado(java.io.PushbackReader in)
	{ 
		super(in);
	}

	// Definimos um filtro que reconhece comentarios aninhados
	@Override
	protected void filter() throws LexerException
	{ 
		//Coloque o código aqui...
		
	}
}
