package RssReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

public class RssService extends IntentService {

	public static final String RECEIVER = "receiver";
	protected static final String ARTICLES = "articles";
	public static final String RSS_LINK = "http://feeds.bbci.co.uk/news/world/rss.xml";

	public RssService() {
		super("RSS Service");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("Handle intent", "Service started");
		List<Article> articleList = null;
		RssParser parser = new RssParser();
		try {
			articleList = parser.parse(getInputStream(RSS_LINK));
		} catch (XmlPullParserException e) {
			Log.w(e.getMessage(), e);
		} catch (IOException e) {
			Log.w(e.getMessage(), e);
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
		catch (IOException e) {
			Log.w("IOException", "Exception while retrieving the input stream", e);
            return null;
		}
		
	}
	
	
}
