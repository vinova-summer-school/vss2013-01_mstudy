package com.example.mstudy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private ImageView imgNews;
	private ImageView imgDict;
	private ImageView imgSetting;
	
	private Intent myInt;
	private View.OnClickListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgNews = (ImageView) findViewById(R.id.imgNews);
		imgDict = (ImageView) findViewById(R.id.imgDict);
		imgSetting = (ImageView) findViewById(R.id.imgSetting);
		
		openNewsActivity();
		openDictActivity();
		openSettingActivity();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Mo layout News
	public void openNewsActivity()
	{
		
		listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doOpenNewsActivity();
			}
		};
		imgNews.setOnClickListener(listener);
	}
	
	public void doOpenNewsActivity()
	{
		myInt = new Intent(this, NewsActivity.class);
		startActivity(myInt);
		//finish();
	}
	
	//Mo layout Dictionary
	public void openDictActivity()
	{
		
		listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doOpenDictActivity();
			}
		};
		imgDict.setOnClickListener(listener);
	}
	
	public void doOpenDictActivity()
	{
		myInt = new Intent(this, DictActivity.class);
		startActivity(myInt);
		//finish();
	}
	
	//Mo layout Setting
	public void openSettingActivity()
	{
		
		listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				doOpenSettingActivity();
			}
		};
		imgSetting.setOnClickListener(listener);
	}
	
	public void doOpenSettingActivity()
	{
		myInt = new Intent(this, SettingActivity.class);
		startActivity(myInt);
		//finish();
	}
}
