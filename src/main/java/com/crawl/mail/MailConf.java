/**
 *
 * @author Thiago Puluceno <thiago.puluceno@grupopan.com>
 */
package com.crawl.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailConf {

	private Properties props;
	private Session session;

	private MailConf() {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		session = Session.getDefaultInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("puluceno", "909abc");
			}
		});

	}

	private static class MailConfHelper {

		private static final MailConf INSTANCE = new MailConf();
	}

	public static MailConf getInstance() {
		return MailConfHelper.INSTANCE;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
