package UsersUtils;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author SIBAJI KHATAI
 * This class represent mailing activity
 */
public class EmailUtil {
	static final Logger log = Logger.getLogger(EmailUtil.class);

	/**
	 * @author SIBAJI KHATAI
	 * it contains a session of mail
	 */
	private static Session mailSession = null;
	/**
	 * @author SIBAJI KHATAI
	 * it holds from mail address
	 */
	private static String fromMail = null;
	/**
	 * @author SIBAJI KHATAI
	 * it hold the password of from mail address
	 */
	private static String password = null;
	private static Properties mailProperties = null;
	/**
	 * @author SIBAJI KHATAI
	 * it holds host name
	 */
	private static String mailSmtpHost = null;
	/**
	 * @author SIBAJI KHATAI
	 * it holds port no
	 */
	private static String mailSmtpPort = null;
	/**
	 * @author SIBAJI KHATAI
	 * it holds authentication status
	 */
	private static String mailSmtpauth = null;
	/**
	 * @author SIBAJI KHATAI
	 * it holds starttls status
	 */
	private static String mailSmtpStarttlsEnable = null;

	static {
		// loading the mail properties file
		InputStream inputStream = EmailUtil.class.getClassLoader().getResourceAsStream("/src/main/java/UsersUtils/mail.properties");

		File f=new File("/src/main/java/UsersUtils/mail.properties");
		System.out.println("input mail is "+inputStream);
		
		mailProperties = new Properties();
		// load the stream
		try {
			mailProperties.load(inputStream);
			// getting the property from the email properties file
			fromMail = mailProperties.getProperty("emailID");
			password = mailProperties.getProperty("password");

			// getting mail properties
			mailSmtpauth = mailProperties.getProperty("mail.smtp.auth");
			mailSmtpStarttlsEnable = mailProperties.getProperty("mail.smtp.starttls.enable");
			mailSmtpHost = mailProperties.getProperty("mail.smtp.host");
			mailSmtpPort = mailProperties.getProperty("mail.smtp.port");
			
			System.out.println(fromMail+"---"+password+"---"+mailSmtpauth+"----"+mailSmtpStarttlsEnable);
			System.out.println(mailSmtpHost+"----"+mailSmtpPort);
			// putting the mail properties into mailProperties

			mailProperties.put("mail.smtp.auth", mailSmtpauth);
			mailProperties.put("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
			mailProperties.put("mail.smtp.host", mailSmtpHost);
			mailProperties.put("mail.smtp.port", mailSmtpPort);

			mailSession = Session.getInstance(mailProperties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromMail, password);
				}
			});

		} catch (IOException e) {
			log.error("Error in Emailutil:"+e);
		}

	}
/**
 * it is used for sending mail
 * @param toAddress
 * @param subject
 * @param messageDetails
 * @return boolean
 */
	public static boolean sendMail(String toAddress, String subject, String messageDetails) {

		try {

			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(fromMail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
			message.setSubject(subject);
			message.setText(messageDetails);
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			log.error("Error in Emailutil send mail():"+e);
			return false;
		}

	}

}
