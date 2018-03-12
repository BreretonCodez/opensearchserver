/*
 * Copyright 2017-2018 Emmanuel Keller / Jaeksoft
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.jaeksoft.opensearchserver.crawler.web;

import com.qwazr.utils.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class WebAfterSession extends WebAbstractEvent {

	@Override
	public boolean run(final EventContext context) throws Exception {

		final URL indexServiceUrl = toUrl(context.crawlSession.getVariable(INDEX_SERVICE_URL, String.class));
		final String accountId = context.crawlSession.getVariable(ACCOUNT_ID, String.class);
		final String indexName = context.crawlSession.getVariable(INDEX_NAME, String.class);

		try {
			context.sessionStore.index(indexServiceUrl, accountId, indexName);
		} finally {
			context.sessionStore.close();
		}
		return true;
	}

	private static URL toUrl(final String url) throws MalformedURLException {
		return StringUtils.isBlank(url) ? null : new URL(url);
	}
}