package com.example.playerstats;

import java.net.*;
import java.io.*;

import org.json.JSONException;

public class StatsPull {
	private String summonerName, summonerID;
	private SummonerStats summonerStats;
	private String apikey = "?api_key=f685bd80-568e-4ff3-9d20-4bde13ab7106";
	
	//  ---===== Player Statistics data =====---
	// Wins
	private String wins5v5, kills5v5, assists5v5,   minionkills5v5, neutralminionkills5v5, turretsdestroyed5v5;
	private String winsdom, killsdom, mostkillsdom, assistsdom,     mostassistsdom, highestscoredom, nodescaptureddom, mostnodescaptureddom, nodesneutralizeddom,mostnodesneutralizeddom;
	private String wins3v3, kills3v3, assists3v3,   minionkills3v3, neutralminionkills3v3,turretsdestroyed3v3;

	//--------------------------------------------------
	// Takes in summoner name and returns summonerID
	//--------------------------------------------------
	private void retrieveSummonerID() throws IOException{
		String summonerInfo, newSummonerInfo, actualSummonerID;
		int index = 0;
		String summonerinfo = "https://prod.api.pvp.net/api/lol/na/v1.3/summoner/by-name/"+summonerName+apikey;
		
		URL info = new URL(summonerinfo);
		URLConnection yc1 = info.openConnection();
		BufferedReader summonerInfoReader = new BufferedReader(new InputStreamReader(yc1.getInputStream()));
		summonerInfo = summonerInfoReader.readLine();
		
		// Parse summoner Info to extract summonerID
		newSummonerInfo = summonerInfo.substring(summonerName.length() + 9);
		index = newSummonerInfo.indexOf(",");	
		actualSummonerID = newSummonerInfo.substring(1, index);
		this.summonerID = actualSummonerID;
	}
	
	//--------------------------------------------------
	// Uses summoner ID to pull summoner statistics
	//--------------------------------------------------
	private void retrieveSummonerStats() throws IOException, JSONException {
		String statsrequest = "https://prod.api.pvp.net/api/lol/na/v1.2/stats/by-summoner/" + summonerID + "/summary" + apikey;
		URL oracle = new URL(statsrequest);
		URLConnection yc = oracle.openConnection();
		this.summonerStats = new SummonerStats(summonerName, yc.getInputStream());
		statsExtract();
	}
	
	private void statsExtract() throws JSONException {
		
		//getting 5v5 stat values
		try {
			this.wins5v5 					= summonerStats.getSummary("Unranked").getField("wins");
			this.kills5v5 					= summonerStats.getSummary("Unranked").getAggregatedStat("totalChampionKills");
			this.assists5v5 				= summonerStats.getSummary("Unranked").getAggregatedStat("totalAssists");
			this.minionkills5v5 			= summonerStats.getSummary("Unranked").getAggregatedStat("totalMinionKills");
			this.neutralminionkills5v5 		= summonerStats.getSummary("Unranked").getAggregatedStat("totalNeutralMinionsKilled");
			this.turretsdestroyed5v5 		= summonerStats.getSummary("Unranked").getAggregatedStat("totalTurretsKilled");
		} catch (JSONException e) {
			this.wins5v5 = "5v5 Stats Unavailable.";
		}
		
		//getting dominion stat values
		try {
			this.winsdom 					= summonerStats.getSummary("OdinUnranked").getField("wins");
			this.killsdom 					= summonerStats.getSummary("OdinUnranked").getAggregatedStat("totalChampionKills");
			this.mostkillsdom 				= summonerStats.getSummary("OdinUnranked").getAggregatedStat("maxChampionsKilled");
			this.assistsdom 				= summonerStats.getSummary("OdinUnranked").getAggregatedStat("totalAssists");
			this.mostassistsdom  			= summonerStats.getSummary("OdinUnranked").getAggregatedStat("maxAssists");
			this.highestscoredom 			= summonerStats.getSummary("OdinUnranked").getAggregatedStat("maxObjectivePlayerScore");
			this.nodescaptureddom 			= summonerStats.getSummary("OdinUnranked").getAggregatedStat("totalNodeCapture");
			this.mostnodescaptureddom 		= summonerStats.getSummary("OdinUnranked").getAggregatedStat("maxNodeCapture");
			this.nodesneutralizeddom 		= summonerStats.getSummary("OdinUnranked").getAggregatedStat("totalNodeNeutralize");
			this.mostnodesneutralizeddom 	= summonerStats.getSummary("OdinUnranked").getAggregatedStat("maxNodeNeutralize");
		} catch (JSONException e){
			this.winsdom = "Dominion Stats Unavailable.";
		}
		
		//getting 3v3 stat values
		try {
			this.wins3v3 					= summonerStats.getSummary("Unranked3x3").getField("wins");
			this.kills3v3 					= summonerStats.getSummary("Unranked3x3").getAggregatedStat("totalChampionKills");
			this.assists3v3 				= summonerStats.getSummary("Unranked3x3").getAggregatedStat("totalAssists");
			this.minionkills3v3 			= summonerStats.getSummary("Unranked3x3").getAggregatedStat("totalMinionKills");
			this.neutralminionkills3v3 		= summonerStats.getSummary("Unranked3x3").getAggregatedStat("totalNeutralMinionsKilled");
			this.turretsdestroyed3v3 		= summonerStats.getSummary("Unranked3x3").getAggregatedStat("totalTurretsKilled");
		} catch (JSONException e) {
			this.wins3v3 = "3v3 Stats Unavailable.";
		}
	}
	
	public StatsPull(String givenSummonerName) throws IOException{
		this.summonerName = givenSummonerName;
		this.retrieveSummonerID();
		try {
			this.retrieveSummonerStats();
		} catch (JSONException e) {
			System.err.println(e);
			System.exit(1);
		}
	}
	
	// Getter Methods
	public String getwins5v5(){
		return wins5v5;
	}
	public String getSummonerName() {
		return summonerName;
	}

	public String getSummonerID() {
		return summonerID;
	}

	public SummonerStats getSummonerStats() {
		return summonerStats;
	}

	public String getWins5v5() {
		return wins5v5;
	}

	public String getKills5v5() {
		return kills5v5;
	}

	public String getAssists5v5() {
		return assists5v5;
	}

	public String getMinionkills5v5() {
		return minionkills5v5;
	}

	public String getNeutralminionkills5v5() {
		return neutralminionkills5v5;
	}

	public String getTurretsdestroyed5v5() {
		return turretsdestroyed5v5;
	}

	public String getWinsdom() {
		return winsdom;
	}

	public String getKillsdom() {
		return killsdom;
	}

	public String getMostkillsdom() {
		return mostkillsdom;
	}

	public String getAssistsdom() {
		return assistsdom;
	}

	public String getMostassistsdom() {
		return mostassistsdom;
	}

	public String getHighestscoredom() {
		return highestscoredom;
	}

	public String getNodescaptureddom() {
		return nodescaptureddom;
	}

	public String getMostnodescaptureddom() {
		return mostnodescaptureddom;
	}

	public String getNodesneutralizeddom() {
		return nodesneutralizeddom;
	}

	public String getMostnodesneutralizeddom() {
		return mostnodesneutralizeddom;
	}

	public String getWins3v3() {
		return wins3v3;
	}

	public String getKills3v3() {
		return kills3v3;
	}

	public String getAssists3v3() {
		return assists3v3;
	}

	public String getMinionkills3v3() {
		return minionkills3v3;
	}

	public String getNeutralminionkills3v3() {
		return neutralminionkills3v3;
	}

	public String getTurretsdestroyed3v3() {
		return turretsdestroyed3v3;
	}



}
