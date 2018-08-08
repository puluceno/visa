/**
 *
 * @author Thiago Puluceno <thiago.puluceno@grupopan.com>
 */
package com.crawl.engine;

import java.util.logging.Level;

import com.crawl.mail.MailMessage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class Crawler {

	private Crawler() {
	}

	private static class CrawlerHelper {

		private static final Crawler INSTANCE = new Crawler();
	}

	public static Crawler getInstance() {
		return CrawlerHelper.INSTANCE;
	}

	public void crawl(String caseNumber) {
		try (final WebClient webClient = init()) {

			final HtmlPage page1 = webClient.getPage("https://egov.uscis.gov");

			final HtmlForm form = page1.getFormByName("caseStatusForm");

			final HtmlSubmitInput button = form.getInputByName("initCaseSearch");
			final HtmlTextInput textField = form.getInputByName("appReceiptNum");

			textField.type(caseNumber);

			final HtmlPage page2 = button.click();

			String asXml = page2.asXml();
			if (!asXml.contains("<div class=\"rows text-center\">")
					&& asXml.contains("<h1>Case Was Received</h1>")) {
				System.out.println("Send Email");
			} else {
				System.out.println("No changes detected");
				MailMessage message = new MailMessage();
				message.send(message.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static WebClient init() {
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
		java.util.logging.Logger.getLogger("org.apache.http.client.protocol.ResponseProcessCookies")
				.setLevel(Level.OFF);

		final WebClient webClient = new WebClient();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		return webClient;
	}
}
