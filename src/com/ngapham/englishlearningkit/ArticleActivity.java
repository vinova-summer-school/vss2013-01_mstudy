package com.ngapham.englishlearningkit;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class ArticleActivity extends FragmentActivity {
	
	private TextView txtTitle;
	private TextView txtDescription;
	private Button btnLookup;
	private Intent callerIntent;
	//FragmentManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article);
		
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtDescription = (TextView) findViewById(R.id.txtDescription);
		btnLookup = (Button) findViewById(R.id.btnLookup);
		//manager = getFragmentManager();
		
//		txtTitle.setText("title here");
//		txtDescription.setText("description here");
		
		//Lay ra goi bundle My Package
		callerIntent = getIntent();
		Bundle packageFromCaller = callerIntent.getBundleExtra("myPackage");
		String title = packageFromCaller.getString("title");
		txtTitle.setText((CharSequence) title);
		String description = packageFromCaller.getString("description");
		txtDescription.setText((CharSequence) description);
		
		
		// Mo fragment
		btnLookup.setOnClickListener(new OnClickListener() {

			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(View arg0) {
				/*android.app.FragmentTransaction transaction = manager.beginTransaction();
				transaction.add(R.id.container, new MyFragment(), "myFragment");
				transaction.addToBackStack(null);
				transaction.commit();*/
			}
			
		});
	}
}
