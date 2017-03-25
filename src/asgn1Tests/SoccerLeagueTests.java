package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;


/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerLeagueTests {
	
	//Declare objects that needed for this unit test
	SoccerLeague soccerLeague;
	SoccerTeam team1;
	SoccerTeam team2;
	SoccerTeam team3;
	SoccerTeam team4;
	SoccerTeam team5;
	
	@Before
	public void construct() {
		soccerLeague = new SoccerLeague(4);
	
		//Initialize all the objects in order to reduce redundant work for the tests		 
		try {
			team1 = new SoccerTeam("A Team", "team1");
			team2 = new SoccerTeam("B Team", "team2");
			team3 = new SoccerTeam("C Team", "team3");
			team4 = new SoccerTeam("D Team", "team4");
			team5 = new SoccerTeam("E Team", "team5");
		} catch (TeamException e) {
			e.getMessage();
		}
	}
	
	/**
	 * To test whether register team will throw exception after the required teams amount is reached
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testRegisterTeamMoreThanRequiredTeamWillThrowException() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.registerTeam(team5);
	}
	
	/**
	 * To test whether registering same team will throw exception
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testRegisterTeamThatHasBeenRegisteredWillThrowException() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team1);
	}
	
	/**
	 * To test whether register team will throw exception after the league started
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testRegisterTeamWillThrowExceptionWhenTheLeagueHasStarted() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		assertEquals(false, soccerLeague.isOffSeason());
		soccerLeague.registerTeam(team5);
	}
	
	@Test
	public void testRegisterTeamWorking() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		assertEquals(true, soccerLeague.containsTeam("A Team"));
		assertEquals(true, soccerLeague.containsTeam("B Team"));
		assertEquals(true, soccerLeague.containsTeam("C Team"));
	}
	
	@Test (expected = LeagueException.class)
	public void testRemoveTeamWillGetExceptionWhenTheLeagueHasStarted() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		assertEquals(false, soccerLeague.isOffSeason());
		soccerLeague.removeTeam(team1);
	}
	
	@Test (expected = LeagueException.class)
	public void testRemoveTeamWillGetExceptionWhenTheTeamIsNotRegisteredToTheLeague() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.removeTeam(team3);
	}
	
	@Test
	public void testRemoveTeamWorking() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.removeTeam(team2);
		assertEquals(true, soccerLeague.containsTeam("A Team"));
		assertEquals(false, soccerLeague.containsTeam("B Team"));
	}
	
	@Test (expected = LeagueException.class)
	public void testStartNewSeasonThrowExceptionWhenRequiredTeamsNumberNotReach() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.startNewSeason();
	}
	
	@Test (expected = LeagueException.class)
	public void testStartNewSeasonThrowExceptionWhenTheLeagueHasStarted() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		soccerLeague.startNewSeason();
	}
	
	@Test
	public void testStartNewSeasonWorking() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		assertEquals(false, soccerLeague.isOffSeason());
	}
	
	@Test (expected = LeagueException.class)
	public void testEndSeasonThrowExceptionWhenTheLeagueIsNotStartedYet() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.endSeason();
	}
	
	@Test
	public void testEndSeasonWorking() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		soccerLeague.endSeason();
		assertEquals(true, soccerLeague.isOffSeason());
	}
	
	@Test (expected = LeagueException.class)
	public void testGetTeamByOfficialNameThrowExceptionWhenTeamIsNotRegistered() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.getTeamByOfficalName("D Team");
	}
	
	@Test
	public void testGetTeamByOfficialNameWorking() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		assertEquals(team1, soccerLeague.getTeamByOfficalName("A Team"));
		assertEquals(team2, soccerLeague.getTeamByOfficalName("B Team"));
	}
	
	@Test (expected = LeagueException.class)
	public void testGetTopTeamThrowExceptionWhenThereIsNoTeamInTheLeague() throws LeagueException {
		soccerLeague.getTopTeam();
	}
	
	@Test (expected = LeagueException.class)
	public void testGetTopTeamThrowExceptionWhenTheRequiredTeamsNumberNotReach() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.getTopTeam();
	}
	
	@Test
	public void testGetTopTeamWorkingForDefaultStandings() throws LeagueException {
		soccerLeague.registerTeam(team4);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team1);
		soccerLeague.startNewSeason();
		soccerLeague.sortTeams();
		assertEquals(team1, soccerLeague.getTopTeam());
	}
	
	@Test
	public void testGetTopTeamWorkingForAfterPlayingMatches() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		soccerLeague.playMatch("C Team", 5, "B Team", 3);
		soccerLeague.playMatch("D Team", 0, "A Team", 0);
		soccerLeague.sortTeams();
		assertEquals(team3, soccerLeague.getTopTeam());
	}
	
	@Test (expected = LeagueException.class)
	public void testGetBottomTeamThrowExceptionWhenThereIsNoTeamInTheLeague() throws LeagueException {
		soccerLeague.getBottomTeam();
	}
	
	@Test (expected = LeagueException.class)
	public void testGetBottomTeamThrowExceptionWhenTheRequiredTeamsNumberNotReach() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.getBottomTeam();
	}
	
	@Test
	public void testGetBottomTeamWorkingForDefaultStandings() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team4);
		soccerLeague.registerTeam(team3);
		soccerLeague.startNewSeason();
		soccerLeague.sortTeams();
		assertEquals(team4, soccerLeague.getBottomTeam());
	}
	
	@Test
	public void testGetBottomTeamWorkingAfterPlayingMatches() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		soccerLeague.playMatch("C Team", 5, "B Team", 3);
		soccerLeague.playMatch("D Team", 0, "A Team", 0);
		soccerLeague.sortTeams();
		assertEquals(team2, soccerLeague.getBottomTeam());
	}
	
	@Test (expected = LeagueException.class)
	public void testPlayMatchThrowExceptionIfTheLeagueHasNotStartedYet() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.playMatch("A Team", 0, "B Team", 3);
	}
	
	@Test (expected = LeagueException.class)
	public void testPlayMatchThrowExceptionWhenSameTeamPlayAgainstEachOther() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.playMatch("B Team", 0, "B Team", 3);
	}
	
	@Test
	public void testPlayMatchWorking() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		soccerLeague.playMatch("B Team", 2, "D Team", 0);
		soccerLeague.playMatch("C Team", 4, "A Team", 1);
	}
	
	@Test
	public void testSortTeamWithCompetitionPoints() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		// A: 9pts; B: 6pts; C:0pts; D:3pts
		soccerLeague.playMatch("A Team", 3, "B Team", 0);
		soccerLeague.playMatch("A Team", 2, "C Team", 0);
		soccerLeague.playMatch("D Team", 1, "A Team", 4);
		soccerLeague.playMatch("B Team", 1, "C Team", 0);
		soccerLeague.playMatch("B Team", 2, "D Team", 0);
		soccerLeague.playMatch("C Team", 0, "D Team", 2);
		soccerLeague.sortTeams();
		assertEquals("A Team", soccerLeague.getTopTeam().getOfficialName());
		assertEquals("C Team", soccerLeague.getBottomTeam().getOfficialName());
	}
	
	@Test
	public void testSortTeamWithGoalDifference() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		// A:7pts, 5gd; B:3pts, -4gd; C:0pts, -5gd; D:7pts, 4gd 
		soccerLeague.playMatch("A Team", 3, "B Team", 0);
		soccerLeague.playMatch("A Team", 2, "C Team", 0);
		soccerLeague.playMatch("D Team", 4, "A Team", 4);
		soccerLeague.playMatch("B Team", 1, "C Team", 0);
		soccerLeague.playMatch("B Team", 0, "D Team", 2);
		soccerLeague.playMatch("C Team", 0, "D Team", 2);
		soccerLeague.sortTeams();
		assertEquals("A Team", soccerLeague.getTopTeam().getOfficialName());
		assertEquals("C Team", soccerLeague.getBottomTeam().getOfficialName());
	}
	
	@Test
	public void testSortTeamWithAlphaberic() throws LeagueException {
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
		soccerLeague.startNewSeason();
		// A:7pts, 6gd; B:1pts, -6gd; C:1pts, -6gd; D:7pts, 6gd 
		soccerLeague.playMatch("A Team", 3, "B Team", 0);
		soccerLeague.playMatch("A Team", 3, "C Team", 0);
		soccerLeague.playMatch("D Team", 4, "A Team", 4);
		soccerLeague.playMatch("B Team", 1, "C Team", 1);
		soccerLeague.playMatch("B Team", 0, "D Team", 3);
		soccerLeague.playMatch("C Team", 0, "D Team", 3);
		soccerLeague.sortTeams();
		assertEquals("A Team", soccerLeague.getTopTeam().getOfficialName());
		assertEquals("C Team", soccerLeague.getBottomTeam().getOfficialName());
	}
}

