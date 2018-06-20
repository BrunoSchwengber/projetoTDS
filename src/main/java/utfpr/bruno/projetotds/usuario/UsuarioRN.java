/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.bruno.projetotds.usuario;

import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author ALUNO
 */
public class UsuarioRN {
    private UsuarioDAO usuarioDAO;
    
    public UsuarioRN() {
        this.usuarioDAO = new UsuarioDAO();
    }
    public void salvar(Usuario usuario) throws MessagingException {
		Long codigo = usuario.getCodigo();
		if (codigo == null || codigo == 0) {
			usuario.getPermissao().add("ROLE_USUARIO"); 
			this.usuarioDAO.salvar(usuario);
                        //EmailUtil send = new EmailUtil(usuario.getEmail());
                        // ALTERADO APÓS CRIAÇÃO DE CATEGORIA
                        //CategoriaRN categoriaRN = new CategoriaRN();
                        //categoriaRN.salvaEstruturaPadrao(usuario);
		} else {
			//this.usuarioDAO.atualizar(usuario);
		}
	}
    public void excluir(Usuario usuario) {
            
            this.usuarioDAO.excluir(usuario);
	}
    public List<Usuario> listarTodos(){
        UsuarioDAO usuario = new UsuarioDAO();
        List<Usuario> list = new ArrayList<Usuario>();
        list = usuario.listarTodos();
        return list;
    }
}
