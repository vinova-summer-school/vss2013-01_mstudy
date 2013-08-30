package com.ngapham.englishlearningkit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class ArticleWebViewActivity extends Activity {
	private WebView webView;
	private String url;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_webview);
		
		Intent callerIntent = getIntent();
		Bundle packageFromCaller = callerIntent.getBundleExtra("myPackage");
		url = callerIntent.getStringExtra("myLink");
		Log.i("url", url);
		
		webView = (WebView) findViewById(R.id.articleWebview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl(url);
	}
	
	public void startIntent()
	{
		
	}
}
