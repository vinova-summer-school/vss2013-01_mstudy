package RssReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class RssParser {
	public List<Article> parse(InputStream inputStream) throws XmlPullParserException, IOException
	{
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(inputStream, null);
			parser.nextTag();
			return readFeed(parser);
		}
		finally
		{
			inputStream.close();
		}
		
	}

	private List<Article> readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, "rss");
		String title = null;
		String link = null;
		List<Article> articleList = new ArrayList<Article>();
		while (parser.next() != XmlPullParser.END_DOCUMENT) 
		{
			if (parser.getEventType() != XmlPullParser.START_TAG)
			{
				continue;
			}
			if (parser.getName().equalsIgnoreCase("title"))
			{
				title = readTitle(parser);
			}
			if (parser.getName().equalsIgnoreCase("link"))
			{
				link = readLink(parser);
			}
			if (title != null && link != null)
			{
				Article article = new Article(title, link);
				articleList.add(article);
				title = null;
				link = null;
			}
		}
		return articleList;
	}

	private String readLink(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, "link");
		String link = readText(parser);
		parser.require(XmlPullParser.END_TAG, null, "link");
		return link;
	}

	private String readText(XmlPullParser parser) throws XmlPullParserException, IOException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT)
		{
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}

	private String readTitle(XmlPullParser parser) throws XmlPullParserException, IOException {
		parser.require(XmlPullParser.START_TAG, null, "title");
		String title = readText(parser);
		parser.require(XmlPullParser.END_TAG, null, "title");
		return title;
	}
}
