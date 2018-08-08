/**
 *
 * @author Thiago Puluceno <thiago.puluceno@grupopan.com>
 */
package com.crawl.main;

import com.crawl.engine.Crawler;

public class Main {

	private static final Crawler crawler = Crawler.getInstance();

	public static void main(String[] args) {
		crawler.crawl(args[0]);
	}
}