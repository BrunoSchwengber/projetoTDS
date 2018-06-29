/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.bruno.projetotds.imovel;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import utfpr.bruno.projetotds.dao.ConexaoHibernate;
import utfpr.bruno.projetotds.usuario.Usuario;

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
    
    public List<Imovel> listar(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Imovel> u = cb.createQuery(Imovel.class);
        Root<Imovel> a = u.from(Imovel.class);
        u.select(a);
        
        TypedQuery<Imovel> query = this.manager.createQuery(u);
        List<Imovel> imoveis = query.getResultList();
        return imoveis;
    }

    public void excluir(Imovel imovel) {
       
        this.manager.getTransaction().begin();
        this.manager.remove(imovel);
        this.manager.getTransaction().commit();
    }

    
}
