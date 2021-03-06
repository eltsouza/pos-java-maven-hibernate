package managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.TelefoneDAO;
import dao.DaoUsuario;
import model.TelefoneUser;
import model.UsuarioPessoa;

@ManagedBean(name = "telefoneBean")
@ViewScoped
public class TelefoneBean implements Serializable {
	
	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	private TelefoneUser telefoneUser = new TelefoneUser();	
	private DaoUsuario<UsuarioPessoa> usuarioDAO = new DaoUsuario<>();
	private TelefoneDAO<TelefoneUser> telefoneDAO = new TelefoneDAO<TelefoneUser>();			
	
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init() {
		String codUser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigouser");		
		usuarioPessoa = usuarioDAO.pesquisar(Long.parseLong(codUser), UsuarioPessoa.class);
	}
	
	public String salvar() {
		telefoneUser.setUsuarioPessoa(usuarioPessoa);
		telefoneDAO.salva(telefoneUser);
		telefoneUser = new TelefoneUser();
		usuarioPessoa = usuarioDAO.pesquisar(usuarioPessoa.getId(), UsuarioPessoa.class);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));
		return "";
	}
	
	public String removerTelefone() throws Exception {
		telefoneDAO.deletarPorId(telefoneUser);
		usuarioPessoa = usuarioDAO.pesquisar(usuarioPessoa.getId(), UsuarioPessoa.class);
		telefoneUser = new TelefoneUser();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso!"));
		return "";
	}
	
	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}
	
	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}
	
	public void setTelefoneDAO(TelefoneDAO<TelefoneUser> telefoneDAO) {
		this.telefoneDAO = telefoneDAO;
	}
	
	public TelefoneDAO<TelefoneUser> getTelefoneDAO() {
		return telefoneDAO;
	}	
	
	public void setTelefoneUser(TelefoneUser telefoneUser) {
		this.telefoneUser = telefoneUser;
	}
	
	public TelefoneUser getTelefoneUser() {
		return telefoneUser;
	}

}
