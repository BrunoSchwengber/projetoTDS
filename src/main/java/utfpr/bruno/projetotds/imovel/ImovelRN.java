/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.bruno.projetotds.imovel;

import java.util.List;

/**
 *
 * @author ALUNO
 */
public class ImovelRN {
    private ImovelDAO imovelDAO;
    
    public ImovelRN(){
        this.imovelDAO = new ImovelDAO();
    }
    
    public void salvar(Imovel imovel) {
		
		//if (codigo == null || codigo == 0) {
			//usuario.getPermissao().add("ROLE_USUARIO"); 
			this.imovelDAO.salvar(imovel);
                       
		//} else {
			//this.usuarioDAO.atualizar(usuario);
		//}
	}
    public List<Imovel> listar(){
        this.imovelDAO = new ImovelDAO();
        return imovelDAO.listar();
    }

    public void excluir(Imovel imovel) {
        this.imovelDAO = new ImovelDAO();
        imovelDAO.excluir(imovel);
    }
}
