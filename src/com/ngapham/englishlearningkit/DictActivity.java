package com.ngapham.englishlearningkit;

import java.util.ArrayList;
import java.util.List;

import com.ngapham.englishlearningkit.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

public class DictActivity extends Activity implements TextWatcher {
	
	private AutoCompleteTextView txtSearch;
	private List<Word> listWord;		//Danh sach tu vung lay tu CSDL tu dien
	private List<String> listWordString;	//Danh sach tu dang string
	private List<String> listSuggestion;	//Danh sach suggestion cho txtSearch
	private WordDatabase myDbAdapter;
	private ListView list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_layout);
		//txtSelection = (TextView) findViewById(R.id.txtSelection);
		txtSearch = (AutoCompleteTextView) findViewById(R.id.txtSearch);
		list = (ListView) findViewById(R.id.listWord);
		myDbAdapter = new WordDatabase(this);
		
		listWordString = new ArrayList<String>();
		listSuggestion = new ArrayList<String>();
		listWord = myDbAdapter.getAllWord();
		for (Word word : listWord)
		{
			listWordString.add(word.toString());
			listSuggestion.add(word.getName());
		}
		//Tao bo lang nghe su kien Text Changed
		txtSearch.addTextChangedListener(this);
		
		bindData();
		
		
	}
	
	//Thiet lap ArrayAdapter de bind data
	public void bindData()
	{
		ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1, listWordString);
		list.setAdapter(adapter);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listSuggestion);
		txtSearch.setAdapter(adapter);
	}
	
	@Override
	public void afterTextChanged(Editable arg0) {
		
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		//txtSelection.setText(txtSearch.getText());
	}
}
