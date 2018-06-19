/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.bruno.projetotds.imovel;

import javax.persistence.EntityManager;
import utfpr.bruno.projetotds.dao.ConexaoHibernate;

/**
 *
 * @author ALUNO
 */
public class ImovelDAO {
    private EntityManager manager;

    public ImovelDAO() {
        this.manager = ConexaoHibernate.getInstance();
    }
    
    
    public void salvar(Imovel imovel) {
        //this.session.save(usuario);
        this.manager.getTransaction().begin();
        this.manager.persist(imovel);
        this.manager.flush();
        this.manager.getTransaction().commit();
        this.manager.close();
    }
}
