package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entidades.AluguelCarro;
import entidades.Veiculo;
import servicos.ServicoAluguel;
import servicos.ServicoImpostoBrasil;

public class Programa {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Informe os dados da locação");
		System.out.print("Modelo do Carro: ");
		String modelo = sc.nextLine();
		
		System.out.print("Retirada (dd/mm/aaaa hh:mm): ");
		Date retirada = sdf.parse(sc.nextLine());
		System.out.print("Devolução (dd/mm/aaaa hh:mm): ");
		Date devolucao = sdf.parse(sc.nextLine());
		
		AluguelCarro aluguelCarro = new AluguelCarro(retirada, devolucao, new Veiculo(modelo));
		
		System.out.print("Informe o valor por hora: ");
		double valorHora = sc.nextDouble();
		System.out.print("Informe o valor por dia: ");
		double valorDia = sc.nextDouble();
		
		ServicoAluguel servicoAluguel = new ServicoAluguel(valorHora, valorDia, new ServicoImpostoBrasil());
		
		servicoAluguel.gerarFatura(aluguelCarro);
		
		System.out.println("FATURA: ");
		System.out.printf("Pagamento básico: %.2f\n", aluguelCarro.getFatura().getPagamentoBasico());
		System.out.printf("Imposto: %.2f\n", aluguelCarro.getFatura().getImposto());
		System.out.printf("Pagamento total: %.2f\n", aluguelCarro.getFatura().getPagamentoTotal());
			
		sc.close();
	}

}
