package utfpr.bruno.projetotds.web.util;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import utfpr.bruno.projetotds.web.util.Autenticacao;

public class EmailUtil {

	private static final String SERVIDOR_SMTP = "smtp.gmail.com";
	private static final String PORTA_SERVIDOR_SMTP = "587";
	private static final String EMAIL = "brunohenrique.schwengber@gmail.com";
	private static final String SENHA = "159753369";

        
    public EmailUtil(String emails) throws AddressException, MessagingException {
                Autenticacao autenticacao = new Autenticacao(EMAIL, SENHA); 
		Session session = Session.getInstance(getPropriedades(), autenticacao);
		session.setDebug(true); 

		MimeMessage email = new MimeMessage(session);
		email.setRecipient(Message.RecipientType.TO, new InternetAddress(emails));
		email.setFrom(new InternetAddress(EMAIL));
		email.setSubject("Teste de e-mail usando Gmail");
		email.setContent("Corpo da mensagem", "text/plain");
		email.setSentDate(new Date());
		Transport envios = session.getTransport("smtp");
		envios.connect(SERVIDOR_SMTP, EMAIL, SENHA);
		email.saveChanges(); 
		envios.sendMessage(email, email.getAllRecipients());
		envios.close();

		System.out.println("E-mail enviado com sucesso");
    }
        
	public static Properties getPropriedades() {
		Properties config = new Properties();
		config.setProperty("mail.transport.protocol", "smtp");
		config.setProperty("mail.smtp.starttls.enable", "true");
		config.setProperty("mail.smtp.host", SERVIDOR_SMTP); 
		config.setProperty("mail.smtp.auth", "true"); 
		config.setProperty("mail.smtp.user", EMAIL); 
		config.setProperty("mail.debug", "true");
		config.setProperty("mail.smtp.port", PORTA_SERVIDOR_SMTP); 
		config.setProperty("mail.smtp.socketFactory.port", PORTA_SERVIDOR_SMTP); 
		config.setProperty("mail.smtp.socketFactory.class",	"javax.net.ssl.SSLSocketFactory");
		config.setProperty("mail.smtp.socketFactory.fallback", "false");
		return config;
	}



    
}
