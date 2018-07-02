/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.bruno.projetotds.web.util;


import utfpr.bruno.projetotds.dao.ConexaoHibernate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.primefaces.model.StreamedContent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.*;
import org.primefaces.model.DefaultStreamedContent;


/**
 *
 * @author BRUNO
 */
public class RelatorioUtil {
    private Connection connection;
    
    public StreamedContent geraRelatorio(HashMap parametrosRelatorio, String nomeRelatorioJasper,
		String nomeRelatorioSaida) throws UtilException { 
		StreamedContent arquivoRetorno = null;
		try {
			Connection conexao = this.getConexao(); 
			FacesContext contextoFaces = FacesContext.getCurrentInstance();
			ExternalContext contextoExterno = contextoFaces.getExternalContext();
			ServletContext contextoServlet = (ServletContext) contextoExterno.getContext();

			String caminhoRelatorios ="/home/bruno/NetBeansProjects/ProjetoTDS/relatorios/";//contextoServlet.getRealPath("/relatorios"); 
			String caminhoArquivoJasper = caminhoRelatorios + File.separator + nomeRelatorioJasper 
					+ ".jasper"; 
			String caminhoArquivoRelatorio = caminhoRelatorios + File.separator + nomeRelatorioSaida;

			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoJasper); 
			JasperPrint impressoraJasper = JasperFillManager
				.fillReport(relatorioJasper, parametrosRelatorio, conexao); 

			String extensao = null;
			File arquivoGerado = null;

			
                        JRPdfExporter pdfExportado = new JRPdfExporter(); 
                        extensao = "pdf";
                        arquivoGerado = new java.io.File(caminhoArquivoRelatorio + "." + extensao);
                        pdfExportado.setExporterInput(new SimpleExporterInput(	impressoraJasper));
                        pdfExportado.setExporterOutput(new SimpleOutputStreamExporterOutput(arquivoGerado));
                        pdfExportado.exportReport();
                        arquivoGerado.deleteOnExit(); 
					
						

			InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
			arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/" 
				+ extensao, nomeRelatorioSaida + "." + extensao); 
		} catch (JRException e) {
			throw new UtilException("N�o foi poss�vel gerar o relat�rio.", e);
		} catch (FileNotFoundException e) {
			throw new UtilException("Arquivo do relat�rio n�o encontrado.", e);
		}
		return arquivoRetorno;
	}
    
    
    private Connection getConexao() throws UtilException { 
            try {
                    EntityManager manager = ConexaoHibernate.getInstance();
                    Session session = manager.unwrap(Session.class);

                    session.doWork(
                    new Work() {
                        @Override
                        public void execute(Connection connection) throws SQLException 
                        { 
                            setConnection(connection);
                        }
                        }
                    );

                return this.connection;
            } catch (Exception e) {
                    throw new UtilException("Não foi possível conectar com o banco de dados para o relatório.", e);
            } 
	}
        
        private void setConnection(Connection connection) {
            this.connection = connection;
        }
}
