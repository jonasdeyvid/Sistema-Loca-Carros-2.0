package Controles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Entidades.Carro;
import repositorio.RepositorioCarro;

public class ControladorCarro {
	private static ControladorCarro controler;
	
	private ControladorCarro() {
		
	}
	
	public static ControladorCarro getInstance() {
		if(controler == null ) {
			return new ControladorCarro();
		}
		return controler;
	}
	
	public boolean addCarro(String modelo, String placa, String cor, int ano, double precoAluguel) {
		if(modelo ==  null || modelo.equals(""))  return false ; 
		if(!(modelo.substring(0, 3).matches("[A-Z a-z]*")) )return false;
		if(placa ==  null)  return false;
		if(placa.equals("")) return false;
		if(!(placa.substring(0, 3).matches("[A-Z a-z]*")) )return false;
		if(placa.equals("")) return false;
		if(cor ==  null)  return false;
		if(cor.equals("")) return false;
		if(!(cor.substring(0, 3).matches("[A-Z a-z]*")) )return false;
		if(ano > LocalDate.now().getYear() || ano < 1880) return false; // tem que melhorar isso aqui 
		if(precoAluguel <= 0) return false;
		
		Carro carro = new Carro(modelo, placa, cor, ano, precoAluguel);
		RepositorioCarro.getInstance().addCarro(carro);
		return true;
	}
	
	public boolean removerCarro(String placa) {
		if(placa ==  null)  throw new IllegalArgumentException("placa nao pode ser nula");
		if(RepositorioCarro.getInstance().removerCarro(placa)) {
			return true;
		}
		return false;
	}
	
	public boolean editarPrecoCarro(String placa, double novoPreco) {
		if(novoPreco < 0) return false;
		if(RepositorioCarro.getInstance().editarPrecoCarro(placa, novoPreco)){
			return true;
		}
		return false;
	}
	
	public List<Carro> carrosDisponiveis(){
		return RepositorioCarro.getInstance().carrosDisponiveis();
	}
	public boolean alugarCarro(String placa) {
		Carro carro = RepositorioCarro.getInstance().buscarCarro(placa);
			if(carro != null && carro.isAlugado() == false){
				carro.setAlugado(true);
				RepositorioCarro.getInstance().setCarroAlugado(carro);
				return true;
			
		}
		return false;
	}
	public boolean devolverCarro(String placa) {
		for (Carro carro : RepositorioCarro.getInstance().getCarros()) {
			if(carro.getPlaca().equals(placa)) {
				if(carro.isAlugado() == true) {
					carro.setAlugado(false);
					return true;
				}
			}
			
		}
		return false;
	}
	public Carro buscarCarro(String placa) {
		return RepositorioCarro.getInstance().buscarCarro(placa);
	}
	
	public List<Carro> getCarros(){
		return RepositorioCarro.getInstance().getCarros();
	}
	
	
}
