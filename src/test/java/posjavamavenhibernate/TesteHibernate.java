package posjavamavenhibernate;

import java.util.List;

import org.junit.Test;

import dao.DaoGeneric;
import model.TelefoneUser;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		//HibernateUtil.geEntityManager();
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(122313);
		pessoa.setNome("Usuario Rolback 4");
		pessoa.setSobrenome("Transacao com rolback");
		pessoa.setEmail("souzaconsultoria@gmail.com");
		pessoa.setLogin("admin");
		pessoa.setSenha("admin");
		
		//daoGeneric.salva(pessoa);
				
	}
	
	@Test
	public void testeBuscar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
        pessoa.setId(5L);        
        pessoa = daoGeneric.pesquisar(pessoa);        
       //System.out.println(pessoa);
	}
	
	@Test
	public void testeBuscar2() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = daoGeneric.pesquisar(5L, UsuarioPessoa.class);        
       // System.out.println(pessoa);
	}
	
	@Test
	public void testeUpdate() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = daoGeneric.pesquisar(6L, UsuarioPessoa.class);        
        pessoa.setIdade(100);
        pessoa.setNome("Elton Lindão");        
        pessoa = daoGeneric.updateMerge(pessoa);        
       // System.out.println(pessoa);
	}
	
	@Test
	public void testeDelete() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = daoGeneric.pesquisar(17L, UsuarioPessoa.class);        
        //daoGeneric.deletarPorId(pessoa);
	}
		
    @Test
	public void testeConsultar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);
	    System.out.println(" ");
	    System.out.println("=====Retorno consultar todos========");
    	System.out.println(" ");
        for (UsuarioPessoa usuarioPessoa : list) {
        	System.out.println(usuarioPessoa);
        }
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryList() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	    List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = 'Elton Lindão'").getResultList();	
	    System.out.println(" ");
	    System.out.println("=====Retorno Query List========");
    	System.out.println(" ");
	    for (UsuarioPessoa usuarioPessoa : list) {
	    	System.out.println(usuarioPessoa);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryListMaxResult() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	    List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa order by id").setMaxResults(5).getResultList();	
	    System.out.println(" ");
	    System.out.println("=====Retorno lista com MaxResult========");
    	System.out.println(" ");
    	for (UsuarioPessoa usuarioPessoa : list) {
	    	System.out.println(usuarioPessoa);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryListParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	    List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery(" from UsuarioPessoa where nome = :nome or sobrenome = :sobrenome")
	    		.setParameter("nome", "Usuario Rolback")
	    		.setParameter("sobrenome", "Transacao com rolback")
	    		.getResultList();	
    	System.out.println(" ");
    	System.out.println("=====Retorno lista com parâmetro========");
    	System.out.println(" ");
	    for (UsuarioPessoa usuarioPessoa : list) {
	    	System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeQuerySomaIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        
		Long somaIdade = (Long) daoGeneric.getEntityManager()
				.createQuery("select sum(u.idade) from UsuarioPessoa u").getSingleResult();
	
    	System.out.println(" ");
    	System.out.println("=====Retorno Soma Idade========");
    	System.out.println("Soma de todas as idade = " + somaIdade);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeNamedQuery() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	    List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.findAll").getResultList();
    	System.out.println(" ");
    	System.out.println("=====Retorno lista com NamedQuery========");
    	System.out.println(" ");
	    for (UsuarioPessoa usuarioPessoa : list) {
	    	System.out.println(usuarioPessoa);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeNamedQueryBuscaPorNome() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	    List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorNome")
	    		.setParameter("nome", "Elton Lindão")
	    		.getResultList();
    	System.out.println(" ");
    	System.out.println("=====Retorno lista com NamedQuery Busca Por Nome========");
    	System.out.println(" ");
	    for (UsuarioPessoa usuarioPessoa : list) {
	    	System.out.println(usuarioPessoa);
		}
	}
	
	@Test
	public void testeGravaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();
		
        UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(5L, UsuarioPessoa.class);
        
        TelefoneUser telefoneUser = new TelefoneUser();
        
        telefoneUser.setTipo("Trabalho");
        telefoneUser.setNumero("(11)23123231");
        telefoneUser.setUsuarioPessoa(pessoa);        
        
        daoGeneric.salva(telefoneUser);
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testeConsultaTelefone() {
		DaoGeneric daoGeneric = new DaoGeneric();
        UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisar(5L, UsuarioPessoa.class);
    	System.out.println(" ");
    	System.out.println("=====Retorno lista telefones pessoa========");
    	System.out.println(" ");
		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuarioPessoa());			
		}
	}
	
	

}
