package mailing;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;


public class SendMail {
	public static boolean sendMail(String subject,String message,String to) {
		boolean isSent=false;
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthentication("nisarmahar8157@gmail.com", "hakimALI");
		email.setSSLOnConnect(true);
		try {
			
			email.setFrom("nisarmahar8157@gmail.com");
			email.setSubject(subject);
			email.setMsg(message);
			email.addTo(to);
			email.send();
			isSent=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
}
