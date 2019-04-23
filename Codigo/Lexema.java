import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lexema {
	private String nome;
	private List<OcorrenciaLexema> ocorrencias;
	
	public Lexema(String nome) {
		this.nome = nome;
		this.ocorrencias = new ArrayList<OcorrenciaLexema>();
	}
	public String getNome() {
		return this.nome;
	}
	public void addOcorrencia(int linha, int coluna) {
		this.ocorrencias.add( new OcorrenciaLexema(linha, coluna) );
	}
	
	public void print() {
		Iterator<OcorrenciaLexema> iteratorDoConjuntoDeToken;
		iteratorDoConjuntoDeToken = this.ocorrencias.iterator();
		
		while (iteratorDoConjuntoDeToken.hasNext()) {
			OcorrenciaLexema ocorrenciaDeLexema;
			ocorrenciaDeLexema = iteratorDoConjuntoDeToken.next();
			
			System.out.print(ocorrenciaDeLexema.getLinha()+":"+ocorrenciaDeLexema.getColuna()+", ");
		}
	}
	
	public Iterator<OcorrenciaLexema> getIterator() {
		return this.ocorrencias.iterator();
	}
}
