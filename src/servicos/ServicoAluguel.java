package servicos;

import entidades.AluguelCarro;
import entidades.Fatura;

public class ServicoAluguel {
	
	private Double valorPorHora;
	private Double valorPorDia;
	
	//Associações
	private ServicoImpostoBrasil servicoImposto;

	public ServicoAluguel(Double valorPorHora, Double valorPorDia, ServicoImpostoBrasil servicoImposto) {
		this.valorPorHora = valorPorHora;
		this.valorPorDia = valorPorDia;
		this.servicoImposto = servicoImposto;
	}
	
	public void gerarFatura(AluguelCarro aluguelCarro) {
		long t1 = aluguelCarro.getInicio().getTime();
		long t2 = aluguelCarro.getFim().getTime();
		double horas = (double) (t2 - t1) / 1000 / 60 / 60;
		
		double pagamentoBasico;
		
		if (horas <= 12.0) {
			pagamentoBasico = Math.ceil(horas) * valorPorHora;
		}
		else {
			pagamentoBasico = Math.ceil(horas / 24) * valorPorDia;			
		}
		
		double imposto = servicoImposto.imposto(pagamentoBasico);
		
		aluguelCarro.setFatura(new Fatura(pagamentoBasico, imposto));
	}
}
