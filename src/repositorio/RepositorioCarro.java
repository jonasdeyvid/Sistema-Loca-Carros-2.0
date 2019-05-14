package repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Query;

import Entidades.Carro;

public class RepositorioCarro {
	private static RepositorioCarro repositorio;
	private List<Carro> carros;
	protected EntityManager entityManager;

	private RepositorioCarro() {
		carros = new ArrayList<Carro>();
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("crud-hibernate");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public static RepositorioCarro getInstance() {
		if (repositorio == null) {
			return repositorio = new RepositorioCarro();
		}
		return repositorio;
	}

	public boolean addCarro(Carro carro) {
		if (buscarCarro(carro.getPlaca()) == null) {
			// bloco pro hibernate
			entityManager.getTransaction().begin();
			entityManager.persist(carro);
			entityManager.getTransaction().commit();
			return true;
		}
		return false;
	}

	public boolean removerCarro(String placa) {
		Carro carro = buscarCarro(placa);
		if (carro == null) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.remove(carro);
		entityManager.getTransaction().commit();
		return true;
	}

	public Carro buscarCarro(String placaBusca) {
		ArrayList<Carro> cs = new ArrayList<Carro>(getCarros());
		Carro c = null;
		for(Carro carro : cs) {
			if(carro.getPlaca().equals(placaBusca)) {
				c = carro;
			}
		}
		
				return c;
	}

	

	public boolean editarPrecoCarro(String placa, double novoPreco) {
		Carro carro = buscarCarro(placa);
		if(carro == null) {
			return false;
		}
		carro.setPrecoAluguel(novoPreco);
		entityManager.getTransaction().begin();
		entityManager.merge(carro);
		entityManager.getTransaction().commit();
		return true;
	}
	
	public boolean setCarroAlugado(Carro carro) {
		if(carro == null) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.merge(carro);
		entityManager.getTransaction().commit();
		return true;
	}

	public List<Carro> carrosDisponiveis(){
		List<Carro> carrosDoBD = getCarros();
		List<Carro> carrosDisponiveis = new ArrayList<Carro>();
		for (Carro carro : carrosDoBD) {
			if(!carro.isAlugado()) {
				carrosDisponiveis.add(carro);
			}
		}
		return carrosDisponiveis;
	}
	@SuppressWarnings("unchecked")
	public List<Carro> getCarros() {
		return entityManager.createQuery("FROM " + Carro.class.getName()).getResultList();
	}

}
