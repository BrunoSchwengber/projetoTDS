/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.bruno.projetotds.web;

import static java.util.Collections.list;
import utfpr.bruno.projetotds.dao.ConexaoHibernate;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import org.primefaces.model.StreamedContent;
import utfpr.bruno.projetotds.usuario.Usuario;
import utfpr.bruno.projetotds.usuario.UsuarioDAO;
import utfpr.bruno.projetotds.usuario.UsuarioRN;
import utfpr.bruno.projetotds.web.util.RelatorioUtil;

/**
 *
 * @author BRUNO
 */
@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
    private Usuario usuario = new Usuario();
    private String confirmarSenha;
    private List<Usuario> lista;
    private String destinoSalvar;
    private StreamedContent arquivoRetorno;
    private EntityManager manager;
    private List<Usuario> listaUsuarios;
    private Usuario userSelected = new Usuario();
    
    public String novo() {
		this.destinoSalvar = "index";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
                this.manager = ConexaoHibernate.getInstance();
		return "cadastroUsuario";
	}
    
    public String salvar() throws MessagingException {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage(
					"A senha não foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);

		/*if (this.conta.getDescricao() != null) { 
			this.conta.setUsuario(this.usuario); 
			this.conta.setFavorita(true); 
			ContaRN contaRN = new ContaRN();
			contaRN.salvar(this.conta);
		}*/
		
		return this.destinoSalvar;
	}
    public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.userSelected);
		return "listagem";
	}

    public StreamedContent getArquivoRetorno() {
        FacesContext context = FacesContext.getCurrentInstance();
        String nomeRelatorioJasper = "usuarios";
        String nomeRelatorioSaida = "usuariosCadastrados";
        RelatorioUtil relatorioUtil = new RelatorioUtil();
        return arquivoRetorno;
    }

    public Usuario getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(Usuario userSelected) {
        this.userSelected = userSelected;
    }
    

    public List<Usuario> getListaUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        List<Usuario> list = dao.listarTodos();
        return list;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void setArquivoRetorno(StreamedContent arquivoRetorno) {
        this.arquivoRetorno = arquivoRetorno;
    }
    
    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        return lista;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public String getDestinoSalvar() {
        return destinoSalvar;
    }

    public void setDestinoSalvar(String destinoSalvar) {
        this.destinoSalvar = destinoSalvar;
    }

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }
    
    
}
