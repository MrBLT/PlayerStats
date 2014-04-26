package com.example.playerstats;

import java.net.*;
import java.io.*;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StatsPull {
	private String summonerName, summonerID;
	private SummonerStats summonerStats;
	private String apikey = "?api_key=f685bd80-568e-4ff3-9d20-4bde13ab7106";
	
	//  ---===== Player Statistics data =====---
	// Wins
	private String rankedPremade3x3Wins, rankedPremade5x5Wins, rankedTeam3x3Wins, rankedTeam5x5Wins, rankedSolo5x5Wins;
	private String unrankedWins, coopVsAIWins;
	// Normal Stats
	private String nChampKills, nAssists, nMinionKills, nNeutralMonsterKills, nTotalTurretKills; 
	// Ranked Solo 5x5 Stats
	private String rChampKills, rAssists, rMinionKills, rNeutralMonsterKills, rTotalTurretKills;
	// Normal 3x3 Stats
	private String tvtChampKills, tvtAssists, tvtMinionKills, tvtNeutralMonsterKills, tvtTotalTurretKills;

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
	private void retrieveSummonerStats() throws IOException{
		String statsrequest = "https://prod.api.pvp.net/api/lol/na/v1.2/stats/by-summoner/" + summonerID + "/summary" + apikey;
		System.out.println("love is empty");
		URL oracle = new URL(statsrequest);
		URLConnection yc = oracle.openConnection();
		BufferedReader summonerStatsReader = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		this.summonerStats = new SummonerStats(summonerName, summonerStatsReader);
	}
	
	private void StatsExtract(){
		// Win Stats
		this.rankedPremade3x3Wins = summonerStats.getSummaries().get("RankedPremade3x3").getStats().get("wins").toString();
		this.rankedPremade5x5Wins = summonerStats.getSummaries().get("RankedPremade5x5").getStats().get("wins").toString();
		this.rankedTeam3x3Wins    = summonerStats.getSummaries().get("RankedTeam3x3").getStats().get("wins").toString();
		this.rankedTeam5x5Wins    = summonerStats.getSummaries().get("RankedTeam5x5").getStats().get("wins").toString();
		this.rankedSolo5x5Wins    = summonerStats.getSummaries().get("RankedSolo5x5").getStats().get("wins").toString();
		this.unrankedWins         = summonerStats.getSummaries().get("Unranked").getStats().get("wins").toString();
		this.coopVsAIWins         = summonerStats.getSummaries().get("CoopVsAI").getStats().get("wins").toString();
		
		// Normal Stats
		this.nAssists             = summonerStats.getSummaries().get("Unranked").getStats().get("aggregatedStats").get("totalAssists").toString();
		this.nChampKills          = summonerStats.getSummaries().get("Unranked").getStats().get("aggregatedStats").get("totalChampionKills").toString();
		this.nMinionKills		  = summonerStats.getSummaries().get("Unranked").getStats().get("aggregatedStats").get("totalMinionKills").toString();
		this.nNeutralMonsterKills = summonerStats.getSummaries().get("Unranked").getStats().get("aggregatedStats").get("totalNeutralMinionsKilled").toString();
		this.nTotalTurretKills    = summonerStats.getSummaries().get("Unranked").getStats().get("aggregatedStats").get("totalTurretsKilled").toString();
		
		// Ranked 5v5
		this.rAssists             = summonerStats.getSummaries().get("RankedSolo5x5").getStats().get("aggregatedStats").get("totalAssists").toString();
		this.rChampKills          = summonerStats.getSummaries().get("RankedSolo5x5").getStats().get("aggregatedStats").get("totalChampionKills").toString();
		this.rMinionKills		  = summonerStats.getSummaries().get("RankedSolo5x5").getStats().get("aggregatedStats").get("totalMinionKills").toString();
		this.rNeutralMonsterKills = summonerStats.getSummaries().get("RankedSolo5x5").getStats().get("aggregatedStats").get("totalNeutralMinionsKilled").toString();
		this.rTotalTurretKills    = summonerStats.getSummaries().get("RankedSolo5x5").getStats().get("aggregatedStats").get("totalTurretsKilled").toString();
		
		// Normal 3v3
		this.tvtAssists             = summonerStats.getSummaries().get("Unranked3x3").getStats().get("aggregatedStats").get("totalAssists").toString();
		this.tvtChampKills          = summonerStats.getSummaries().get("Unranked3x3").getStats().get("aggregatedStats").get("totalChampionKills").toString();
		this.tvtMinionKills		    = summonerStats.getSummaries().get("Unranked3x3").getStats().get("aggregatedStats").get("totalMinionKills").toString();
		this.tvtNeutralMonsterKills = summonerStats.getSummaries().get("Unranked3x3").getStats().get("aggregatedStats").get("totalNeutralMinionsKilled").toString();
		this.tvtTotalTurretKills    = summonerStats.getSummaries().get("Unranked3x3").getStats().get("aggregatedStats").get("totalTurretsKilled").toString();
	}
	
	public StatsPull(String givenSummonerName) throws IOException{
		this.summonerName = givenSummonerName;
		this.retrieveSummonerID();
		this.retrieveSummonerStats();
	}
	
	// Getter Methods
	public String getSummonerName() {
		return summonerName;
	}

	public String getSummonerID() {
		return summonerID;
	}

	public SummonerStats getSummonerStats() {
		return summonerStats;
	}

	public String getRankedPremade3x3Wins() {
		return rankedPremade3x3Wins;
	}

	public String getRankedPremade5x5Wins() {
		return rankedPremade5x5Wins;
	}

	public String getRankedTeam3x3Wins() {
		return rankedTeam3x3Wins;
	}

	public String getRankedTeam5x5Wins() {
		return rankedTeam5x5Wins;
	}

	public String getRankedSolo5x5Wins() {
		return rankedSolo5x5Wins;
	}

	public String getUnrankedWins() {
		return unrankedWins;
	}

	public String getCoopVsAIWins() {
		return coopVsAIWins;
	}

	public String getnChampKills() {
		return nChampKills;
	}

	public String getnAssists() {
		return nAssists;
	}

	public String getnMinionKills() {
		return nMinionKills;
	}

	public String getnNeutralMonsterKills() {
		return nNeutralMonsterKills;
	}

	public String getnTotalTurretKills() {
		return nTotalTurretKills;
	}

	public String getrChampKills() {
		return rChampKills;
	}

	public String getrAssists() {
		return rAssists;
	}

	public String getrMinionKills() {
		return rMinionKills;
	}

	public String getrNeutralMonsterKills() {
		return rNeutralMonsterKills;
	}

	public String getrTotalTurretKills() {
		return rTotalTurretKills;
	}

	public String getTvtChampKills() {
		return tvtChampKills;
	}

	public String getTvtAssists() {
		return tvtAssists;
	}

	public String getTvtMinionKills() {
		return tvtMinionKills;
	}

	public String getTvtNeutralMonsterKills() {
		return tvtNeutralMonsterKills;
	}

	public String getTvtTotalTurretKills() {
		return tvtTotalTurretKills;
	}
}
