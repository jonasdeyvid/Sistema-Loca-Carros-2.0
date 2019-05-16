package povoamento;

import Entidades.Carro;
import Entidades.Cliente;
import repositorio.RepositorioCarro;
import repositorio.RepositorioCliente;

public class PovoaBanco {

	public static void main(String[] args) {
	
		//script para add carro
		String modelo = "carro";
		String placa = "qwe";
		String cor = "branco";
		int ano = 2014;
		double precoAluguel = 30;
		Carro c;
		
		for(int i = 0; i < 100; i++){
			if(i < 10) 
				c = new Carro(modelo+i, placa+"00"+i, cor, ano, precoAluguel);
			else
				c = new Carro(modelo+i, placa+"0"+i, cor, ano, precoAluguel);
			
			RepositorioCarro.getInstance().addCarro(c);	
		}
		
		String nome = "cliente";
		String endereco = "endereco";
		int contato = 12345678;
		String cpf = "123456789";
		Cliente cliente;
		for(int i = 0; i < 100; i++) {
			if(i < 10) {
				cliente = new Cliente(nome+i, endereco+i, contato, cpf+"0"+i);
			}else {
				cliente = new Cliente(nome+i, endereco+i, contato, cpf+i);
			}
			
			RepositorioCliente.getInstance().addCliente(cliente);
		}

	}

}
