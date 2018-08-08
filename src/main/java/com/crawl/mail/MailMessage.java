/**
 *
 * @author Thiago Puluceno <thiago.puluceno@grupopan.com>
 */
package com.crawl.mail;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailMessage {

	private MimeMessage message;

	public MailMessage() throws MessagingException {
		message = new MimeMessage(MailConf.getInstance().getSession());
		message.setFrom(new InternetAddress("puluceno@gmail.com"));
		message.setRecipients(RecipientType.TO, InternetAddress.parse("puluceno@gmail.com"));
		message.setSubject("Status changed");

		String msg = "Go verify what happpened now!";

		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);

		message.setContent(multipart);
	}

	public void send(Message message) throws MessagingException {
		Transport.send(message);
	}

	public MimeMessage getMessage() {
		return message;
	}

	public void setMessage(MimeMessage message) {
		this.message = message;
	}

}
