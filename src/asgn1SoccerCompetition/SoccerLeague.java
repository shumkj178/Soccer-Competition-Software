package asgn1SoccerCompetition;

import java.util.Collections;
//import java.util.Comparator;
import java.util.LinkedList;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;

/**
 * A class to model a soccer league. Matches are played between teams and points awarded for a win,
 * loss or draw. After each match teams are ranked, first by points, then by goal difference and then
 * alphabetically. 
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */
public class SoccerLeague implements SportsLeague{
	// Specifies the number of team required/limit of teams for the league
	private int requiredTeams;
	// Specifies is the league is in the off season
	private boolean offSeason = true;
	//
	private LinkedList<SoccerTeam> soccerTeams;
	
	/**
	 * Generates a model of a soccer team with the specified number of teams. 
	 * A season can not start until that specific number of teams has been added. 
	 * One that number of teams has been research no more teams can be added unless
	 * a team is first removed. 
	 * 
	 * @param requiredTeams The number of teams required/limit for the league.
	 */
	public SoccerLeague (int requiredTeams){
		this.requiredTeams = requiredTeams;
		//My
		soccerTeams = new LinkedList<SoccerTeam>();
	}

	
	/**
	 * Registers a team to the league.
	 * 
	 * @param team Registers a team to play in the league.
	 * @throws LeagueException If the season has already started, if the maximum number of 
	 * teams allowed to register has already been reached or a team with the 
	 * same official name has already been registered.
	 */
	public void registerTeam(SoccerTeam team) throws LeagueException {
		// TO DO
		if (!isOffSeason()) {
			throw new LeagueException("The league has already started.");
		} else if (soccerTeams.size() >= requiredTeams) {
			throw new LeagueException("The league is full.");
		} else if (soccerTeams.contains(team)) {
			throw new LeagueException("The team has already registered to the league.");
		} else {
			soccerTeams.add(team);
		}
	}
	
	/**
	 * Removes a team from the league.
	 * 
	 * @param team The team to remove
	 * @throws LeagueException if the season has not ended or if the team is not registered into the league.
	 */
	public void removeTeam(SoccerTeam team) throws LeagueException{
		// TO DO
		if (!isOffSeason()) {
			throw new LeagueException("The league has already started.");
		} else if (!soccerTeams.contains(team)) {
			throw new LeagueException("The team has not registered to the league.");
		} else {
			soccerTeams.remove(team);
		}
	}
	
	/** 
	 * Gets the number of teams currently registered to the league
	 * 
	 * @return the current number of teams registered
	 */
	public int getRegisteredNumTeams(){
		// TO DO
		return soccerTeams.size();
	}
	
	/**
	 * Gets the number of teams required for the league to begin its 
	 * season which is also the maximum number of teams that can be registered
	 * to a league.

	 * @return The number of teams required by the league/maximum number of teams in the league
	 */
	public int getRequiredNumTeams(){
		return requiredTeams;
	}
	
	/** 
	 * Starts a new season by reverting all statistics for each times to initial values.
	 * 
	 * @throws LeagueException if the number of registered teams does not equal the required number of teams or if the season has already started
	 */
	public void startNewSeason() throws LeagueException{
		// TO DO
		if (soccerTeams.size() < requiredTeams) {
			throw new LeagueException("The league does not have enough of teams.");
		} else if (!isOffSeason()) {
			throw new LeagueException("The league has already started.");
		} else {
			for (SoccerTeam soccerTeam : soccerTeams) {
				soccerTeam.resetStats();
			}
			offSeason = false;
		}
	}
	

	/**
	 * Ends the season.
	 * 
	 * @throws LeagueException if season has not started
	 */
	public void endSeason() throws LeagueException{
		// TO DO 
		if (isOffSeason()) {
			throw new LeagueException("The league has already ended or the league has not started yet.");
		} else {
			offSeason = true;
		}
	}
	
	/**
	 * Specifies if the league is in the off season (i.e. when matches are not played).
	 * @return True If the league is in its off season, false otherwise.
	 */
	public boolean isOffSeason(){
		return this.offSeason;
	}
	
	
	
	/**
	 * Returns a team with a specific name.
	 * 
	 * @param name The official name of the team to search for.
	 * @return The team object with the specified official name.
	 * @throws LeagueException if no team has that official name.
	 */
	public SoccerTeam getTeamByOfficalName(String name) throws LeagueException{		
		// TO DO
		for (SoccerTeam soccerTeam : soccerTeams) {
			if (soccerTeam.getOfficialName().equals(name)) {
				return soccerTeam;
			}
		}
		throw new LeagueException("The team is not registered to the league.");
	}
		
	/**
	 * Plays a match in a specified league between two teams with the respective goals. After each match the teams are
	 * resorted.
     *
	 * @param homeTeamName The name of the home team.
	 * @param homeTeamGoals The number of goals scored by the home team.
	 * @param awayTeamName The name of the away team.
	 * @param awayTeamGoals The number of goals scored by the away team.
	 * @throws LeagueException If the season has not started or if both teams have the same official name. 
	 */
	public void playMatch(String homeTeamName, int homeTeamGoals, String awayTeamName, int awayTeamGoals) throws LeagueException{
		// TO DO
		if (isOffSeason()) {
			throw new LeagueException("The league has not started yet.");
		} else if (homeTeamName.equals(awayTeamName)) {
			throw new LeagueException("Cannot compete with same team!");
		} else {
			for (SoccerTeam soccerTeam : soccerTeams) {
				if (soccerTeam.getOfficialName().equals(homeTeamName)) {
					try {
						soccerTeam.playMatch(homeTeamGoals, awayTeamGoals);
					} catch (TeamException e) {
						e.getMessage();
					}
				} else if (soccerTeam.getOfficialName().equals(awayTeamName)) {
					try {
						soccerTeam.playMatch(awayTeamGoals, homeTeamGoals);
					} catch (TeamException e) {
						e.getMessage();
					}
					
				}
			}
		}
	}
	
	/**
	 * Displays a ranked list of the teams in the league  to the screen.
	 */
	public void displayLeagueTable(){
		// TO DO (optional)
		sortTeams();
		for (SoccerTeam soccerTeam : soccerTeams) {
			soccerTeam.displayTeamDetails();
		}
	}
	
	/**
	 * Returns the highest ranked team in the league.
     *
	 * @return The highest ranked team in the league. 
	 * @throws LeagueException if the number of teams is zero or less than the required number of teams.
	 */
	public SoccerTeam getTopTeam() throws LeagueException{
		// TO DO
		if (soccerTeams.size()== 0) {
			throw new LeagueException("There is no team in the league.");
		} else if (soccerTeams.size() < requiredTeams) {
			throw new LeagueException("There is insufficient number of teams in the league.");
		} else {
			SoccerTeam temp = soccerTeams.getFirst();
			return temp;
		}
	}

	/**
	 * Returns the lowest ranked team in the league.
     *
	 * @return The lowest ranked team in the league. 
	 * @throws LeagueException if the number of teams is zero or less than the required number of teams.
	 */
	public SoccerTeam getBottomTeam() throws LeagueException{
		// TO DO
		if (soccerTeams.size()== 0) {
			throw new LeagueException("There is no team in the league.");
		} else if (soccerTeams.size() < requiredTeams) {
			throw new LeagueException("There is insufficient number of teams in the league.");
		} else {
			SoccerTeam temp = soccerTeams.getLast();
			return temp;
		}
	}

	/** 
	 * Sorts the teams in the league.
	 */
    public void sortTeams(){		
		// TO DO
    	//old methods with comparator
//    	Collections.sort(soccerTeams, new Comparator<SoccerTeam>(){
//
//			@Override
//			public int compare(SoccerTeam o1, SoccerTeam o2) {
//				// TODO Auto-generated method stub
//				return o1.compareTo(o2);
//			}});
    	Collections.sort(soccerTeams);
    }
    
    /**
     * Specifies if a team with the given official name is registered to the league.
     * 
     * @param name The name of a team.
     * @return True if the team is registered to the league, false otherwise. 
     */
    public boolean containsTeam(String name){
		// TO DO
    	for (SoccerTeam soccerTeam : soccerTeams) {
			if (soccerTeam.getOfficialName().equals(name)) {
				return true;
			}
		}
    	return false;
    }
    
}
