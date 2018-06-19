/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.bruno.projetotds.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import utfpr.bruno.projetotds.dao.ConexaoHibernate;
import utfpr.bruno.projetotds.imovel.Imovel;
import utfpr.bruno.projetotds.imovel.ImovelRN;
import utfpr.bruno.projetotds.usuario.Usuario;
import utfpr.bruno.projetotds.usuario.UsuarioRN;

/**
 *
 * @author BRUNO
 */
@ManagedBean(name = "imovelBean")
@RequestScoped
public class ImovelBean {
    private Imovel imovel = new Imovel();
    private String destinoSalvar = "cadastroImovel.jsf";
    private EntityManager manager = ConexaoHibernate.getInstance();

    public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		ImovelRN imovelRN = new ImovelRN();
		imovelRN.salvar(this.imovel);

		
		return this.destinoSalvar;
	}
    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
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
