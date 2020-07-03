package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import posjavamavenhibernate.HibernateUtil;

//<E> significa entidade

//Operador diamond < > foi adicionado no Java a partir da versão 1.5 da linguagem 
//para contemplar o conceito de Generics. O Class representa uma classe genérica, 
//onde o tipo da classe informada deverá ser do mesmo tipo da classe parametrizada! 

public class DaoGeneric<E> {

	private EntityManager entityManager = HibernateUtil.geEntityManager();
	
	
	public void salva(E entidade) {
		try {
     		EntityTransaction transaction = entityManager.getTransaction();
		    transaction.begin();
		    entityManager.persist(entidade);
	    	transaction.commit();
		}catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public E updateMerge(E entidade) { //Salva ou atualiza
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeSalva = entityManager.merge(entidade);
		transaction.commit();
		return entidadeSalva;
	}	
	
	@SuppressWarnings("unchecked")
	public E pesquisar(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);
		E e = (E) entityManager.find(entidade.getClass(), id);
		return e;
	}
	public E pesquisar(Long id, Class<E> entidade) {
		E e = (E) entityManager.find(entidade, id);
		return e;
	}
	public void deletarPorId(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createNativeQuery("delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id = " + id).executeUpdate(); //faz o delete do registro
	    transaction.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<E> listar(Class<E> entidade){
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		List<E> lista = entityManager.createQuery("from " + entidade.getName()).getResultList();
		transaction.commit();
		return lista;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	
}
