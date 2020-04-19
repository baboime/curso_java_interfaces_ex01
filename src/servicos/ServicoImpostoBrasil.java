package servicos;

public class ServicoImpostoBrasil implements ServicoImposto {
	
	public double imposto(double montante){
		if (montante > 100.0) {
			return montante * 0.15;
		}
		else {
			return montante * 0.20;
		}
	}
}
