package com.example.playerstats;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	private final String TAG = "enter";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pullStats();
		
	}
	
	private void pullStats(){
		Button enter = (Button) findViewById(R.id.enter);
		
		// Code that is executed when the button is pressed is housed inside onClick
		View.OnClickListener myListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new StatsTask().execute();
			};
			
		};
		enter.setOnClickListener(myListener);
	}
	
	private class StatsTask extends AsyncTask<String, Void, String[]>{
		@Override
		protected String[] doInBackground(String... params){
    		EditText edit1 = (EditText) findViewById(R.id.editText1);
			String summonerName = edit1.getText().toString();
			String[] results = new String[10];
			for(int i=0; i<10; i++){
				results[i] = "test";
			}
			results[0] = summonerName;
			
			try {
				StatsPull stats = new StatsPull(summonerName);
				
				results[1] = stats.getSummonerID();
				results[2] = stats.getRankedSolo5x5Wins();
				results[3] = stats.getRankedPremade5x5Wins();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// SOMEONE HELP ME FIGURE OUT HOW TO HANDLE THIS
				// I think this is for when it cannot set up the URL
				//Toast.makeText(MainActivity.this, "UHOH", Toast.LENGTH_LONG).show();

				//e.printStackTrace();
			}
			return results;
		}
		
		@Override
        protected void onPostExecute(String[] results) {
    		TextView text1 = (TextView) findViewById(R.id.textView1);
    		TextView text2 = (TextView) findViewById(R.id.textView2);
    		TextView text3 = (TextView) findViewById(R.id.textView3);
    		TextView text4 = (TextView) findViewById(R.id.textView4);
    		TextView text5 = (TextView) findViewById(R.id.textView5);
    		TextView text6 = (TextView) findViewById(R.id.textView6);
    		TextView text7 = (TextView) findViewById(R.id.textView7);
    		TextView text8 = (TextView) findViewById(R.id.textView8);
    		TextView text9 = (TextView) findViewById(R.id.textView9);
    		TextView text10 = (TextView) findViewById(R.id.textView10);
    		TextView text11 = (TextView) findViewById(R.id.textView11);
    		TextView text12 = (TextView) findViewById(R.id.textView12);
    		TextView text13 = (TextView) findViewById(R.id.textView13);
    		TextView text14 = (TextView) findViewById(R.id.textView14);
    		TextView text15 = (TextView) findViewById(R.id.textView15);
    		TextView text16 = (TextView) findViewById(R.id.textView16);
    		TextView text17 = (TextView) findViewById(R.id.textView17);
    		TextView text18 = (TextView) findViewById(R.id.textView18);
    		TextView text19 = (TextView) findViewById(R.id.textView19);
    		TextView text20 = (TextView) findViewById(R.id.textView20);
    		text4.setText(results[0]);
			text1.setText(results[1]);  
			text2.setText(results[2]);
			text3.setText(results[3]);
        }

        @Override
        protected void onPreExecute() {
        	Toast.makeText(MainActivity.this, "Retrieving Summoner Stats", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
