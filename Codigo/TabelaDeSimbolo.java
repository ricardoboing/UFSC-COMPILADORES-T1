import java.util.HashMap;
import java.util.Iterator;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.Vocabulary;

public class TabelaDeSimbolo {
	private Vocabulary vocabularioAntlr;
	
	private HashMap<String, TokenLexico> conjuntoDeToken;
	private HashMap<String, TokenLexico> conjuntoDeTokenPorLexema;
	
	private int lengthDoMaiorToken, lengthDoMaiorLexema;
	
	public TabelaDeSimbolo(Vocabulary vocabularioAntlr) {
		this.conjuntoDeToken = new HashMap<String, TokenLexico>();
		this.conjuntoDeTokenPorLexema = new HashMap<String, TokenLexico>();
		
		this.vocabularioAntlr = vocabularioAntlr;
		
		this.lengthDoMaiorToken = 0;
		this.lengthDoMaiorLexema = 0;
	}
	
	public void addLexema(Token tokenAntlr) {
		String nomeDoToken, nomeDoLexema;
		nomeDoLexema = tokenAntlr.getText();
		nomeDoToken = vocabularioAntlr.getSymbolicName(tokenAntlr.getType());
		
		if (nomeDoToken.length() > lengthDoMaiorToken) {
			lengthDoMaiorToken = nomeDoToken.length();
		}
		if (nomeDoLexema.length() > lengthDoMaiorLexema) {
			lengthDoMaiorLexema = nomeDoLexema.length();
		}
		
		TokenLexico tokenLexico;
		tokenLexico = this.conjuntoDeToken.get(nomeDoToken);
		
		if (tokenLexico == null) {
			tokenLexico = new TokenLexico(nomeDoToken);
			this.conjuntoDeToken.put(nomeDoToken, tokenLexico);
		}
		
		tokenLexico.addLexema(tokenAntlr);
		this.conjuntoDeTokenPorLexema.put(nomeDoLexema, tokenLexico);
	}
	
	public TokenLexico getToken(String nomeDoToken) {
		return this.conjuntoDeToken.get(nomeDoToken);
	}
	public TokenLexico getToken(Lexema lexema) {
		String nomeDoLexema;
		nomeDoLexema = lexema.getNome();
		
		return this.conjuntoDeTokenPorLexema.get(nomeDoLexema);
	}
	public Lexema getLexema(String nomeDoLexema) {
		return this.conjuntoDeTokenPorLexema.get(nomeDoLexema).getLexema(nomeDoLexema);
	}
	
	public String getToString() {
		String espacoVazioToken, espacoVazioLexema;
		espacoVazioLexema = "";
		espacoVazioToken = "";
		
		for (int c = 0; c < this.lengthDoMaiorLexema; c++) {
			espacoVazioLexema += " ";
		}
		for (int c = 0; c < this.lengthDoMaiorToken; c++) {
			espacoVazioToken += " ";
		}
		
		Iterator<TokenLexico> iteratorDoConjuntoDeToken;
		iteratorDoConjuntoDeToken = this.getIterator();
		
		String nomeDoToken, nomeDoLexema;
		nomeDoToken = "TOKENS";
		nomeDoLexema = "LEXEMAS";
		
		String print;
		print = nomeDoToken;
		print += espacoVazioToken.substring(nomeDoToken.length());
		print += " | ";
		print += nomeDoLexema;
		print += espacoVazioLexema.substring(nomeDoLexema.length());
		print += " | ";
		print += "OCORRENCIAS (linha:coluna)";
		print += "\n";
		
		while (iteratorDoConjuntoDeToken.hasNext()) {
			TokenLexico tokenLexico;
			tokenLexico = iteratorDoConjuntoDeToken.next();
			nomeDoToken = tokenLexico.getNome();
			
			Iterator<Lexema> iteratorDoConjuntoDeLexema;
			iteratorDoConjuntoDeLexema = tokenLexico.getIterator();
			
			while (iteratorDoConjuntoDeLexema.hasNext()) {
				Lexema lexema;
				lexema = iteratorDoConjuntoDeLexema.next();
				nomeDoLexema = lexema.getNome();
				
				Iterator<OcorrenciaLexema> iteratorDoConjuntoDeOcorrencia;
				iteratorDoConjuntoDeOcorrencia = lexema.getIterator();
				
				while (iteratorDoConjuntoDeOcorrencia.hasNext()) {
					OcorrenciaLexema ocorrenciaDeLexema;
					ocorrenciaDeLexema = iteratorDoConjuntoDeOcorrencia.next();
					
					print += nomeDoToken;
					print += espacoVazioToken.substring(nomeDoToken.length());
					print += " | ";
					print += nomeDoLexema;
					print += espacoVazioLexema.substring(nomeDoLexema.length());
					print += " | ";
					print += ocorrenciaDeLexema.getLinha()+":"+ocorrenciaDeLexema.getColuna();
					print += "\n";
				}
			}
		}
		
		return print;
	}
	
	public Iterator<TokenLexico> getIterator() {
		return this.conjuntoDeToken.values().iterator();
	}
}
