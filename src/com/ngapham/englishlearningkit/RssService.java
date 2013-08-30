package com.ngapham.englishlearningkit;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

public class RssService extends IntentService {
	
	public static final String RSS_LINK = "http://www.pcworld.com/index.rss";
	public static final String RECEIVER = "receiver";
	public static final String ARTICLES = "articles";

	public RssService() {
		super("RSS Service");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		List<Article> articleList = null;
		
		try {
			RssParser parser = new RssParser();
			articleList = parser.parse(getInputStream(RSS_LINK));
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bundle bundle = new Bundle();
		bundle.putSerializable(ARTICLES, (Serializable) articleList);
		ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
		receiver.send(0, bundle);
	}

	public InputStream getInputStream(String rssLink) {
		try {
			URL url = new URL(rssLink);
			return url.openConnection().getInputStream();
		} catch (IOException e) {
			Log.w("IOException", "Exception while retrieving the input stream", e);
			e.printStackTrace();
            return null;
		}
		
	}
	
	
}
