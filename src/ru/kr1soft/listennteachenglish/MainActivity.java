package ru.kr1soft.listennteachenglish;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData.Item;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {

	private Button btnFindTranslate;
	private EditText etWord;
	private EditText etTranslateResult;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		btnFindTranslate = (Button)findViewById(R.id.btnFindTranslate);
		btnFindTranslate.setOnClickListener(btnFindTranslateListner);
		
		etWord = (EditText)findViewById(R.id.etInputWord);
		etTranslateResult = (EditText)findViewById(R.id.etTranslate);
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private OnClickListener btnFindTranslateListner = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Translater t = new Translater();
			String res = t.GetTranslate(etWord.getText().toString());
			etTranslateResult.setText(res);
		}
	};

}
