package com.example.todoapp;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoActivity extends Activity {
	
	private ArrayList<String> todoItems =new ArrayList<String>();
	private ArrayAdapter<String> aTodoItems;
	private ListView lvitems;
	private EditText etNewItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		etNewItem = (EditText) findViewById(R.id.etNewItem);
		readItems();
		lvitems = (ListView) findViewById(R.id.lvItems);
		aTodoItems = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
		lvitems.setAdapter(aTodoItems);
		setupListViewListener();
	}
	
	private void setupListViewListener() {
		// TODO Auto-generated method stub
		lvitems.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int pos, long id) {
				// TODO Auto-generated method stub
				todoItems.remove(pos);
				writeItems();
				aTodoItems.notifyDataSetChanged();
				return false;
			}
			
		});
		
	}
	
	private void readItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			todoItems = new ArrayList<String>(FileUtils.readLines(todoFile));
		} catch(IOException e) {
			todoItems = new ArrayList<String>();
		}
		
	}

	
	private void writeItems() {
		File filesDir = getFilesDir();
		File todoFile = new File(filesDir, "todo.txt");
		try {
			FileUtils.writeLines(todoFile, todoItems);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onAddedNewItem(View v) {
		// TODO Auto-generated method stub
        String newItem = etNewItem.getText().toString();
        todoItems.add(newItem);
        etNewItem.setText("");
        writeItems();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo, menu);
		return true;
	}

}
