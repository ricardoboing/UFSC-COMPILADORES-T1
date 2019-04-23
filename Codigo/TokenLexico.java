import java.util.HashMap;
import java.util.Iterator;

import org.antlr.v4.runtime.Token;

public class TokenLexico {
	private String nome;
	private HashMap<String, Lexema> conjuntoDeLexema;
	
	public TokenLexico(String nome) {
		this.nome = nome;
		this.conjuntoDeLexema = new HashMap<String, Lexema>();
	}
	public void addLexema(Token tokenAntlr) {
		String nomeDoLexema;
		nomeDoLexema = tokenAntlr.getText();
		
		int linhaDaOcorrencia, colunaDaOcorrencia;
		linhaDaOcorrencia = tokenAntlr.getLine();
		colunaDaOcorrencia = tokenAntlr.getCharPositionInLine();
		
		Lexema lexema;
		lexema = this.conjuntoDeLexema.get(nomeDoLexema);
		
		if (lexema == null) {
			lexema = new Lexema(nomeDoLexema);
		}
		lexema.addOcorrencia(linhaDaOcorrencia, colunaDaOcorrencia);
		
		this.conjuntoDeLexema.put(nomeDoLexema, lexema);
	}
	public Lexema getLexema(String nomeDoLexema) {
		return this.conjuntoDeLexema.get(nomeDoLexema);
	}
	public String getNome() {
		return this.nome;
	}
	public void print() {
		Iterator<Lexema> iteratorDoConjuntoDeToken;
		iteratorDoConjuntoDeToken = this.conjuntoDeLexema.values().iterator();
		
		while (iteratorDoConjuntoDeToken.hasNext()) {
			Lexema lexema;
			lexema = iteratorDoConjuntoDeToken.next();
			lexema.print();
		}
	}
	
	public Iterator<Lexema> getIterator() {
		return this.conjuntoDeLexema.values().iterator();
	}
}
