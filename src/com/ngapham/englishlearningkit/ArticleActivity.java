package com.ngapham.englishlearningkit;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ArticleActivity extends FragmentActivity {
	
	private TextView txtTitle;
	private TextView txtDescription;
	private Button btnLookup;
	FragmentManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article);
		
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtDescription = (TextView) findViewById(R.id.txtDescription);
		btnLookup = (Button) findViewById(R.id.btnLookup);
		manager = getFragmentManager();
		
		btnLookup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				/*android.app.FragmentTransaction transaction= manager.beginTransaction();
                transaction.add(R.id.container, new MyFragment(), "myFragment");
                transaction.addToBackStack(null);
                transaction.commit();*/
			}
			
		});
	}
}
