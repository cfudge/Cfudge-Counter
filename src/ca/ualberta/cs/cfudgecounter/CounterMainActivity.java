package ca.ualberta.cs.cfudgecounter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
//import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.view.Menu;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class CounterMainActivity extends Activity
{

	private ListView countersView;
//	private ArrayList<String> countersList;
	private ArrayAdapter<String> adapter;
	private static final String COUNTERFILE = "counters.sav";
	private static final CounterController counterController = new CounterController();
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		countersView = (ListView) findViewById(R.id.CounterList);
	}
	
	protected void onStart(){
		
		super.onStart(); 
		String[] countersString = loadFromFile();
		String[] counterFields;
		int counterValue;
		String counterName;
		
		for(int i=0; i < countersString.length; i++)
		{
			counterFields = countersString[i].split("\\|");
			System.out.print(counterFields[0]+" value: "+counterFields[1]);
			counterName = counterFields[0];
			counterValue = Integer.parseInt(counterFields[1]);
			counterController.addCounter(counterValue, counterName);
		}
		adapter = new ArrayAdapter<String>(this, // adapters adapt basic java data structures (like an array) to connect with a GUI element;
																		// notifyDataSetChanged() tells the GUI that something changed and it needs to be updated
				R.layout.count_list_element, countersString);
		countersView.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	protected void onStart(){
//		super.onStart();
//		String[] counters = loadFromFile();
//		
//	}
	
//	private String[] loadFromFile()
//	{
//		FileInputStream CountersIn = openFileInput("counters.sav");
//		
//		
//	}

	public void addCounter(View countersView){
		
		
	
	
	AlertDialog.Builder alert = new AlertDialog.Builder(this);

	alert.setTitle("Add Counter");
	alert.setMessage("Type Counter Name");

	final EditText counterText = new EditText(this);
	alert.setView(counterText);
	
	alert.setPositiveButton("Add Counter", new DialogInterface.OnClickListener() {
//	@SuppressWarnings("null")
	public void onClick(DialogInterface dialog, int whichButton) {
	Editable counterName = counterText.getText();
	String counterNameString = counterName.toString();
	saveInFile(counterName.toString()+"\\|0");
	counterController.addCounter(0, counterNameString);
	 }
	});

	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int whichButton) {
	}
	});

	 alert.show();
	 
}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
			case R.id.add_counter:
				addCounter(countersView);
			default:
				return super.onOptionsItemSelected(item);
		}
		
	}
	
	public String[] loadFromFile(){
		ArrayList<String> counterStrings = new ArrayList<String>();
		String[] counterFields;
		String counterName;
		int counterValue;
		try {
			FileInputStream CounterFileStream = openFileInput(COUNTERFILE);
			BufferedReader CountersIn = new BufferedReader(new InputStreamReader(CounterFileStream));
			String line = CountersIn.readLine();
			while (line != null) {
				counterFields = line.split("\\|");
				counterName = counterFields[0];
				counterValue = Integer.parseInt(counterFields[1]);
				counterController.addCounter(counterValue, counterName);
				counterStrings.add(line);
				line = CountersIn.readLine();
				}
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			return counterStrings.toArray(new String[counterStrings.size()]);
		}
	
	private void saveInFile(String text) {
		try {
			FileOutputStream CountersOut = openFileOutput(COUNTERFILE,
					Context.MODE_APPEND);
			CountersOut.write(new String("\n"+text) 
					.getBytes());
			CountersOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}