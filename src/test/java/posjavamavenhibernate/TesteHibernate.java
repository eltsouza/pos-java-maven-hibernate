package posjavamavenhibernate;

import org.junit.Test;

import dao.DaoGeneric;
import model.UsuarioPessoa;

public class TesteHibernate {
	
	@Test
	public void testeHibernateUtil() {
		//HibernateUtil.geEntityManager();
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
		
		pessoa.setIdade(32);
		pessoa.setNome("Helen");
		pessoa.setSobrenome("Martins Varjao");
		pessoa.setEmail("souzaconsultoria@gmail.com");
		pessoa.setLogin("admin");
		pessoa.setSenha("admin");
		
		daoGeneric.salva(pessoa);
				
	}
	
	@Test
	public void testeBuscar() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();
        pessoa.setId(5L);        
        pessoa = daoGeneric.pesquisar(pessoa);        
        System.out.println(pessoa);
	}
	
	@Test
	public void testeBuscar2() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = daoGeneric.pesquisar(5L, UsuarioPessoa.class);        
        System.out.println(pessoa);
	}
	
	@Test
	public void testeUpdate() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = daoGeneric.pesquisar(6L, UsuarioPessoa.class);        
        pessoa.setIdade(100);
        pessoa.setNome("Elton Lindão");        
        pessoa = daoGeneric.updateMerge(pessoa);        
        System.out.println(pessoa);
	}
	
	@Test
	public void testeDelete() {
		
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
        UsuarioPessoa pessoa = daoGeneric.pesquisar(16L, UsuarioPessoa.class);        
        daoGeneric.deletarPorId(pessoa);
	}
	

}
