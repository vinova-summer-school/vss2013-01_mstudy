package com.ngapham.englishlearningkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import RssReader.Article;
import RssReader.RssFragment;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.XmlDom;
import com.ngapham.englishlearningkit.R;

public class NewsActivity extends FragmentActivity {
	
	private List<String> titles;
	private List<String> links;
	private List<String> descriptions;
	private ListView list;
	public String currentTitle;
	public String currentDescription;
	
	private List<Article> myArticles; 
	private Article currentArticle;
	private List<XmlDom> items;
	private AQuery aq;
	//private AdapterView.OnItemClickListener listener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		
		titles = new ArrayList<String>();
		links = new ArrayList<String>();
		descriptions = new ArrayList<String>();
		
		/*myArticles = new ArrayList<Article>();
		// initialize AQuery
		aq = new AQuery(this);*/
		
		//list = (ListView) findViewById(R.id.list);
		
		if (savedInstanceState == null)
		{
			addRssFragment();
		}
	}
	//add RssFragment vao activity
	private void addRssFragment() {
		FragmentManager fManager = getSupportFragmentManager();
		FragmentTransaction fTransaction = fManager.beginTransaction();
		RssFragment fragment = new RssFragment();
		fTransaction.add(R.id.fragment_container, fragment);
		fTransaction.commit();
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("fragment added", true);
	}
	/*
	public void doParseXMLFile()
	{
		try {
			URL url = new URL("http://feeds.bbci.co.uk/news/world/rss.xml");
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		    factory.setNamespaceAware(false);
		    XmlPullParser xpp = factory.newPullParser();
		    
		    //Lay XML tu mot input stream
		    xpp.setInput(connect(url), "UTF_8");
		     De parse XML content phai tim den cac <title>, <link> trong cac <item>
		     * Tuy nhien trong the <channel> cung co <title>,
		     * De bo qua <title> trong <channel> va tim den <title> trong <item>
		     * Ta su dung bien danh dau insideItem
		     
		    boolean insideItem = false;
		    
		    int eventType = xpp.getEventType();		    
		    while (eventType != XmlPullParser.END_DOCUMENT) {
		        if (eventType == XmlPullParser.START_TAG) {
		 
		            if (xpp.getName().equalsIgnoreCase("item")) {
		                insideItem = true;
		            } else if (xpp.getName().equalsIgnoreCase("title")) {
		                if (insideItem) {
		                    titles.add(xpp.nextText()); //lay ra tieu de bai bao
		                }
		            } else if (xpp.getName().equalsIgnoreCase("link")) {
		                if (insideItem) {
		                    links.add(xpp.nextText()); //lay ra link bai bao
		                }
		            } else if (xpp.getName().equalsIgnoreCase("description"))
		            {
		            	if (insideItem) {
		            		descriptions.add(xpp.nextText()); //lay ra description
		            	}
		            }
		            
		        }else if(eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
		            insideItem = false;
		        }
		        eventType = xpp.next(); //move to next element
		    }
		    
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public InputStream connect(URL url)
	{
		try
		{
			return url.openConnection().getInputStream();
		}
		catch (Exception e)
		{
			return null;
		}
	}*/
	/*
	public void getRssFeed(String url) {
		aq.ajax(url, XmlDom.class, new AjaxCallback<XmlDom>(){
			@Override
			public void callback(String url, XmlDom xml, AjaxStatus status) {
				items = xml.tags("item");
				for (XmlDom item: items) {
					currentArticle.setTitle(item.text("title"));
					currentArticle.setLink(item.text("link"));
					currentArticle.setDescription(item.text("description"));
					titles.add(currentArticle.getTitle());
					links.add(currentArticle.getLink());
					myArticles.add(currentArticle);
				}
				
			}
		});
		
	}*/
	/*
	public void bindData()
	{
		ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, titles);
		list.setAdapter(adapter);
	}*/
//	//Su kien click vao 1 item de mo link bai bao tuong ung
//	public void openArticle()
//	{
//		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				String urlLink = (String) links.get(arg2);
//				Intent i = new Intent(Intent.ACTION_VIEW);
//				i.setData(Uri.parse(urlLink));
//				startActivity(i);
//			}
//		});
//	}
	/*public void openArticle()
	{
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				currentTitle = (String) titles.get(arg2);
				currentDescription = (String) descriptions.get(arg2);
				doOpenArticle();
			}
		});
	}
	public void doOpenArticle()
	{
		Intent myInt = new Intent(this, ArticleActivity.class);
		//Dua du lieu vao bundle
		Bundle myBundle = new Bundle();
		myBundle.putString("title", currentTitle);
		myBundle.putString("description", currentDescription);
		myInt.putExtra("myPackage", myBundle);
		
		startActivity(myInt);
	}*/
	
}
