/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.bruno.projetotds.usuario;

import java.util.List;
import utfpr.bruno.projetotds.dao.ConexaoHibernate;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ALUNO
 */
public class UsuarioDAO {
    private EntityManager manager;

    public UsuarioDAO() {
        this.manager = ConexaoHibernate.getInstance();
    }
    
    
    
    public void salvar(Usuario usuario) {
        //this.session.save(usuario);
        this.manager.getTransaction().begin();
        this.manager.persist(usuario);
        this.manager.getTransaction().commit();
    }
    public void excluir(Usuario usuario) {
        //this.session.delete(usuario);
        this.manager.getTransaction().begin();
        this.manager.remove(usuario);
        this.manager.getTransaction().commit();
    }
    public List<Usuario> listarTodos(){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Usuario> u = cb.createQuery(Usuario.class);
        Root<Usuario> a = u.from(Usuario.class);
        u.select(a);
        
        TypedQuery<Usuario> query = this.manager.createQuery(u);
        List<Usuario> usuarios = query.getResultList();
        return usuarios;
    }
}
