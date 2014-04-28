package com.example.playerstats;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends ActionBarActivity {
	private final String TAG = "enter";
	private TextView text1, text2, text3, text4, text5, text6, text7, text8, text9, text10;
	private TextView text11, text12, text13, text14, text15, text16, text17, text18, text19, text20;
	private TextView text21, text22, summonname;
	
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
			String[] results = new String[30];
			
			results[0] = summonerName;
			
			try {
				StatsPull stats = new StatsPull(summonerName);
				
				
				results[1] = stats.getSummonerID();
				
				//putting 5v5 stats into array
				results[2] = stats.getwins5v5();
				results[3] = stats.getKills5v5();
				results[4] = stats.getAssists5v5();
				results[5] = stats.getMinionkills5v5();
				results[6] = stats.getNeutralminionkills5v5();
				results[7] = stats.getTurretsdestroyed5v5();
				
				//putting dominion stats into array
				results[8] = stats.getWinsdom();
				results[9] = stats.getKillsdom();
				results[10] = stats.getMostkillsdom();
				results[11] = stats.getAssistsdom();
				results[12] = stats.getMostassistsdom();
				results[13] = stats.getHighestscoredom();
				results[14] = stats.getNodescaptureddom();
				results[15] = stats.getMostnodescaptureddom();
				results[16] = stats.getNodesneutralizeddom();
				results[17] = stats.getMostnodesneutralizeddom();
				
				//putting 3v3 stats into array
				results[18] = stats.getWins3v3();
				results[19] = stats.getKills3v3();
				results[20] = stats.getAssists3v3();
				results[21] = stats.getMinionkills3v3();
				results[22] = stats.getNeutralminionkills3v3();
				results[23] = stats.getTurretsdestroyed3v3();
				
			} catch (IOException e) {
				results[4] = "failure";
			}
			return results;
		}
		
		@Override
        protected void onPostExecute(String[] results) {
			
			summonname = (TextView) findViewById(R.id.summonname);
			
			//creating TextViews for 5v5 stats
			text1 	= (TextView) findViewById(R.id.wins5v5);
			text2 	= (TextView) findViewById(R.id.kills5v5);
			text3 	= (TextView) findViewById(R.id.assists5v5);
			text4 	= (TextView) findViewById(R.id.minionkills5v5);
			text5 	= (TextView) findViewById(R.id.neutralminionkills5v5);
			text6 	= (TextView) findViewById(R.id.turretsdestroyed5v5);
			
			//creating Textviews for dominion stats
			text7 	= (TextView) findViewById(R.id.winsdom);
			text8 	= (TextView) findViewById(R.id.killsdom);
			text9 	= (TextView) findViewById(R.id.mostkillsdom);
			text10 	= (TextView) findViewById(R.id.assistsdom);
			text11 	= (TextView) findViewById(R.id.mostassistsdom);
			text12 	= (TextView) findViewById(R.id.highestscoredom);
			text13 	= (TextView) findViewById(R.id.nodescaptureddom);
			text14 	= (TextView) findViewById(R.id.mostnodescaptureddom);
			text15 	= (TextView) findViewById(R.id.nodesneutralizeddom);
			text16 	= (TextView) findViewById(R.id.mostnodesneutralizeddom);
			
			//creating Textviews for 3v3 stats
			text17 	= (TextView) findViewById(R.id.wins3v3);
			text18 	= (TextView) findViewById(R.id.kills3v3);
			text19 	= (TextView) findViewById(R.id.assists3v3);
			text20 	= (TextView) findViewById(R.id.minionkills3v3);
			text21 	= (TextView) findViewById(R.id.neutralminionkills3v3);
			text22 	= (TextView) findViewById(R.id.turretsdestroyed3v3);
			
			summonname.setText(results[0]);
			//setting 5v5 stats for the TextViews
			text1.setText(results[2]);
			text2.setText(results[3]);
			text3.setText(results[4]);
			text4.setText(results[5]);
			text5.setText(results[6]);
			text6.setText(results[7]);
			
			//setting dominion stats for the TextViews
			text7.setText(results[8]);
			text8.setText(results[9]);
			text9.setText(results[10]);
			text10.setText(results[11]);
			text11.setText(results[12]);
			text12.setText(results[13]);
			text13.setText(results[14]);
			text14.setText(results[15]);
			text15.setText(results[16]);
			text16.setText(results[17]);
			
			//setting 3v3 stats for the TextViews
			text17.setText(results[18]);
			text18.setText(results[19]);
			text19.setText(results[20]);
			text20.setText(results[21]);
			text21.setText(results[22]);
			text22.setText(results[23]);
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
