package posjavamavenhibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

	//metodo static pois o projeto só pode ler o arquivo de autenticaçã
	//persistence.xml 1 vez.
	public static EntityManagerFactory factory = null;
	
	static {
	   init();
	}
	
	private static void init() {
		try {
			if(factory ==null) {
				factory = Persistence.createEntityManagerFactory("pos-java-maven-hibernate");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static EntityManager geEntityManager() {
		return factory.createEntityManager(); //prove a parte de persistencia
	}
	
	public static Object getPrimaryKey(Object entity) { //Retorna a primary key da entidade
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
	
}
