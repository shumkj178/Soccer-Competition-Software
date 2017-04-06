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
	
	/**
	 * Construct the objects needed for the tests
	 * Register teams into the league for the tests
	 * 
	 * @throws LeagueException
	 * @throws TeamException
	 */
	@Before
	public void construct() throws LeagueException, TeamException {
		soccerLeague = new SoccerLeague(4);
	
		//Initialize all the objects in order to reduce redundant works for the tests		 
		team1 = new SoccerTeam("A Team", "team1");
		team2 = new SoccerTeam("B Team", "team2");
		team3 = new SoccerTeam("C Team", "team3");
		team4 = new SoccerTeam("D Team", "team4");
		team5 = new SoccerTeam("E Team", "team5");
		
		//Register 4 teams into the league to avoid redundant lines and works
		soccerLeague.registerTeam(team1);
		soccerLeague.registerTeam(team2);
		soccerLeague.registerTeam(team3);
		soccerLeague.registerTeam(team4);
	}
	
	/**
	 * To test whether register team will throw exception after the required teams amount is reached
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testRegisterTeamMoreThanRequiredTeamWillThrowException() throws LeagueException {	
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
	}
	
	/**
	 * To test whether register team will throw exception after the league started
	 * Use startNewSeason method to start the league
	 * Use isOffSeason method to confirm the league has started
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testRegisterTeamWillThrowExceptionWhenTheLeagueHasStarted() throws LeagueException {
		soccerLeague.startNewSeason();
		assertFalse(soccerLeague.isOffSeason());
		soccerLeague.registerTeam(team5);
	}
	
	/**
	 * To test the working scenario for registerTeam
	 * Use containsTeam method for assertions to ensure the teams are registered to the league
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testRegisterTeamWorking() throws LeagueException {
		assertEquals(true, soccerLeague.containsTeam("A Team"));
		assertEquals(true, soccerLeague.containsTeam("B Team"));
		assertEquals(true, soccerLeague.containsTeam("C Team"));
	}
	
	/**
	 * To test removeTeam will throw exception after the league has started
	 * Use startNewSeason method to start the league
	 * Use isOffSeason method to confirm the league has started
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testRemoveTeamWillThrowExceptionWhenTheLeagueHasStarted() throws LeagueException {
		soccerLeague.startNewSeason();
		assertFalse(soccerLeague.isOffSeason());
		soccerLeague.removeTeam(team1);
	}
	
	/**
	 * To test removeTeam throws exception when the team is not in the league
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testRemoveTeamWillGetExceptionWhenTheTeamIsNotRegisteredToTheLeague() throws LeagueException {
		soccerLeague.removeTeam(team5);
	}
	
	/**
	 * To test removeTeam working scenario
	 * Use containsTeam for the assertions to check the team is in league or not
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testRemoveTeamWorking() throws LeagueException {
		soccerLeague.removeTeam(team2);
		assertEquals(true, soccerLeague.containsTeam("A Team"));
		assertEquals(false, soccerLeague.containsTeam("B Team"));
	}
	
	/**
	 * test initial getRegisteredNumTeams
	 * removeTeam is required to make the league back to initial state
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testInitialGetRegisteredNumTeams() throws LeagueException {
		soccerLeague.removeTeam(team1);
		soccerLeague.removeTeam(team2);
		soccerLeague.removeTeam(team3);
		soccerLeague.removeTeam(team4);
		assertEquals(0, soccerLeague.getRegisteredNumTeams());
	}
	
	/**
	 * test getRegisteredNumTeams after register 4 teams to the league
	 */
	@Test
	public void testGetRegisteredNumTeamsWorkingAfterRegister() {
		assertEquals(4, soccerLeague.getRegisteredNumTeams());
	}
	
	/**
	 * test getRequiredNumTeams is returning correct value
	 */
	@Test
	public void testGetRequiredNumTeams() {
		//soccerLeague requires 4 teams
		assertEquals(4, soccerLeague.getRequiredNumTeams());
	}
	
	/**
	 * test getRequiredNumTeams for another league which has different number of required teams
	 */
	@Test
	public void testGetRequiredNumTeamsForNewlyCreatedLeauge() {
		SoccerLeague temp = new SoccerLeague(2);
		assertEquals(2, temp.getRequiredNumTeams());
	}
	
	/**
	 * To test startNewSeason throws exception when the league has not enough of teams
	 * removeTeam is required in order to make the league having insufficient teams
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testStartNewSeasonThrowExceptionWhenRequiredTeamsNumberNotReach() throws LeagueException {
		soccerLeague.removeTeam(team4);
		soccerLeague.startNewSeason();
	}
	
	/**
	 * To test startNewSeason throws exception when the league has already started
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testStartNewSeasonThrowExceptionWhenTheLeagueHasStarted() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.startNewSeason();
	}
	
	/**
	 * To test startNewSeason working scenario
	 * Use isOffSeason for assertion to check the league has started
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testStartNewSeasonWorking() throws LeagueException {
		soccerLeague.startNewSeason();
		assertFalse(soccerLeague.isOffSeason());
	}
	
	/**
	 * To test endSeason throws exception when the league is not even started
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testEndSeasonThrowExceptionWhenTheLeagueIsNotStartedYet() throws LeagueException {
		soccerLeague.endSeason();
	}
	
	/**
	 * To test working scenario for endSeason
	 * Use isOffSeason to ensure the league has ended
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testEndSeasonWorking() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.endSeason();
		assertTrue(soccerLeague.isOffSeason());
	}
	
	/**
	 * test initial isOffSeason returning correct boolean output
	 */
	@Test
	public void testInitialIsOffSeason() {
		assertTrue(soccerLeague.isOffSeason());
	}
	
	/**
	 * test isOffSeason after season has started
	 * use startNewSeason to make the season starts
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testIsOffSeasonAfterSeasonStarted() throws LeagueException {
		soccerLeague.startNewSeason();
		assertFalse(soccerLeague.isOffSeason());
	}
	
	/**
	 * test isOffSeason after season has ended
	 * use startNewSeason to start the season first then use endSeason to end it
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testIsOffSeasonAfterSeasonHasEnded() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.endSeason();
		assertTrue(soccerLeague.isOffSeason());
	}
	
	/**
	 * To test getTeamByOfficialName throw exception when the team is not registered into the league
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testGetTeamByOfficialNameThrowExceptionWhenTeamIsNotRegistered() throws LeagueException {
		soccerLeague.getTeamByOfficalName("E Team");
	}
	
	/**
	 * To test working scenario for getTeamByOfficialName method
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testGetTeamByOfficialNameWorking() throws LeagueException {
		assertEquals(team1, soccerLeague.getTeamByOfficalName("A Team"));
		assertEquals(team2, soccerLeague.getTeamByOfficalName("B Team"));
	}
	
	/**
	 * To test getTopTeam throws exception when there is no team in the league
	 * removeTeam method is used to make the league without any team
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testGetTopTeamThrowExceptionWhenThereIsNoTeamInTheLeague() throws LeagueException {
		soccerLeague.removeTeam(team1);
		soccerLeague.removeTeam(team2);
		soccerLeague.removeTeam(team3);
		soccerLeague.removeTeam(team4);
		soccerLeague.getTopTeam();
	}
	
	/**
	 * To test getTopTeam throws exception when the required number of teams not reach
	 * removeTeam is used to make the league having insufficient teams
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testGetTopTeamThrowExceptionWhenTheRequiredTeamsNumberNotReach() throws LeagueException {
		soccerLeague.removeTeam(team1);
		soccerLeague.getTopTeam();
	}
	
	/**
	 * To test getTopTeam initial default standings
	 * sortTeams is used for sorting the teams in order to get correct top team
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testGetTopTeamWorkingForDefaultStandings() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.sortTeams();
		assertEquals(team1, soccerLeague.getTopTeam());
	}
	
	/**
	 * To test working scenario for getTopTeam after playing matches
	 * playMatch is used for the teams to play match
	 * sortTeams is used for sorting the teams in order to get correct top team
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testGetTopTeamWorkingForAfterPlayingMatches() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.playMatch("C Team", 5, "B Team", 3);
		soccerLeague.playMatch("D Team", 0, "A Team", 0);
		soccerLeague.sortTeams();
		assertEquals(team3, soccerLeague.getTopTeam());
	}
	
	/**
	 * To test getBottomTeam throws exception when there is no team in the league
	 * removeTeam method is used to make the league without any team
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testGetBottomTeamThrowExceptionWhenThereIsNoTeamInTheLeague() throws LeagueException {
		soccerLeague.removeTeam(team1);
		soccerLeague.removeTeam(team2);
		soccerLeague.removeTeam(team3);
		soccerLeague.removeTeam(team4);
		soccerLeague.getBottomTeam();
	}
	
	/**
	 * To test getBottomTeam throws exception when the required number of teams not reach
	 * removeTeam is used to make the league having insufficient teams
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testGetBottomTeamThrowExceptionWhenTheRequiredTeamsNumberNotReach() throws LeagueException {
		soccerLeague.removeTeam(team1);
		soccerLeague.getBottomTeam();
	}
	
	/**
	 * To test getBottomTeam initial default standings
	 * sortTeams is used for sorting the teams in order to get correct bottom team
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testGetBottomTeamWorkingForDefaultStandings() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.sortTeams();
		assertEquals(team4, soccerLeague.getBottomTeam());
	}
	
	/**
	 * To test working scenario for getBottomTeam after playing matches
	 * playMatch is used for the teams to play match
	 * sortTeams is used for sorting the teams in order to get correct bottom team
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testGetBottomTeamWorkingAfterPlayingMatches() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.playMatch("C Team", 5, "B Team", 3);
		soccerLeague.playMatch("D Team", 0, "A Team", 0);
		soccerLeague.sortTeams();
		assertEquals(team2, soccerLeague.getBottomTeam());
	}
	
	/**
	 * To test playMatch throws exception if the league has not started
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testPlayMatchThrowExceptionIfTheLeagueHasNotStartedYet() throws LeagueException {
		soccerLeague.playMatch("A Team", 0, "B Team", 3);
	}
	
	/**
	 * To test playMatch throws exception if the same team is competing each other
	 * 
	 * @throws LeagueException
	 */
	@Test (expected = LeagueException.class)
	public void testPlayMatchThrowExceptionWhenSameTeamPlayAgainstEachOther() throws LeagueException {
		soccerLeague.playMatch("B Team", 0, "B Team", 3);
	}
	
	/**
	 * To test playMatch working scenario
	 * check using getCompetitionPoints
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testPlayMatchWorking() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.playMatch("B Team", 2, "D Team", 0);
		soccerLeague.playMatch("C Team", 4, "A Team", 1);
		assertEquals(3 ,soccerLeague.getTeamByOfficalName("B Team").getCompetitionPoints());
	}
	
	/**
	 * test playMatch after each team played 5 games
	 * check using getCompetitionPoints and getGoalDifference
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testPlayMatchAfterEachTeamPlayed5Games() throws LeagueException {
		soccerLeague.startNewSeason();
		//A:3pts, -1gd; B:4pts, -8gd; C:13pts, +8gd; D:8pts, +1gd;
		soccerLeague.playMatch("A Team", 8, "B Team", 2); //A:W, B:L
		soccerLeague.playMatch("C Team", 4, "B Team", 1); //C:W, B:L
		soccerLeague.playMatch("D Team", 2, "B Team", 2); //D:D, B:D
		soccerLeague.playMatch("A Team", 2, "C Team", 3); //A:L, C:W
		soccerLeague.playMatch("A Team", 1, "D Team", 2); //A:L, D:W
		soccerLeague.playMatch("B Team", 1, "C Team", 2); //B:L, C:W
		soccerLeague.playMatch("D Team", 2, "C Team", 2); //D:d, C:d
		soccerLeague.playMatch("C Team", 3, "A Team", 0); //C:W, A:L
		soccerLeague.playMatch("D Team", 4, "A Team", 2); //D:W, A:L
		soccerLeague.playMatch("B Team", 4, "D Team", 2); //B:W, D:L
		assertEquals(3 ,soccerLeague.getTeamByOfficalName("A Team").getCompetitionPoints());
		assertEquals(-1 ,soccerLeague.getTeamByOfficalName("A Team").getGoalDifference());
		assertEquals(4 ,soccerLeague.getTeamByOfficalName("B Team").getCompetitionPoints());
		assertEquals(-8 ,soccerLeague.getTeamByOfficalName("B Team").getGoalDifference());
		assertEquals(13 ,soccerLeague.getTeamByOfficalName("C Team").getCompetitionPoints());
		assertEquals(8 ,soccerLeague.getTeamByOfficalName("C Team").getGoalDifference());
		assertEquals(8 ,soccerLeague.getTeamByOfficalName("D Team").getCompetitionPoints());
		assertEquals(1 ,soccerLeague.getTeamByOfficalName("D Team").getGoalDifference());
	}
	
	/**
	 * To test sortTeams sorting with competition points
	 * Use playMatch in order to create scores
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testSortTeamWithCompetitionPoints() throws LeagueException {
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
	
	/**
	 * To test sortTeams sorting with goal difference
	 * Use playMatch in order to create scores
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testSortTeamWithGoalDifference() throws LeagueException {
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
	
	/**
	 * To test sortTeams sorting alphabetically
	 * Use playMatch in order to create scores
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testSortTeamWAlphabetically() throws LeagueException {
		soccerLeague.startNewSeason();
		// A:7pts, 6gd; B:1pts, -6gd; C:1pts, -6gd; D:7pts, 6gd;
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
	
	/**
	 * test sortTeam after each team played 5 games
	 * 
	 * @throws LeagueException
	 */
	@Test
	public void testSortTeamAfterEachTeamPlaying5Games() throws LeagueException {
		soccerLeague.startNewSeason();
		//A:3pts, -1gd; B:4pts, -8gd; C:13pts, +8gd; D:8pts, +1gd;
		soccerLeague.playMatch("A Team", 8, "B Team", 2); 
		soccerLeague.playMatch("C Team", 4, "B Team", 1); 
		soccerLeague.playMatch("D Team", 2, "B Team", 2); 
		soccerLeague.playMatch("A Team", 2, "C Team", 3); 
		soccerLeague.playMatch("A Team", 1, "D Team", 2); 
		soccerLeague.playMatch("B Team", 1, "C Team", 2);
		soccerLeague.playMatch("D Team", 2, "C Team", 2);
		soccerLeague.playMatch("C Team", 3, "A Team", 0);
		soccerLeague.playMatch("D Team", 4, "A Team", 2);
		soccerLeague.playMatch("B Team", 4, "D Team", 2);
		soccerLeague.sortTeams();
		assertEquals("C Team", soccerLeague.getTopTeam().getOfficialName());
		assertEquals("A Team", soccerLeague.getBottomTeam().getOfficialName());
	}
	
	/**
	 * To test containsTeam working scenario
	 * 
	 * @return true
	 */
	@Test
	public void testContainsTeamWorkingTrueScenario() {
		assertTrue(soccerLeague.containsTeam("A Team"));
	}
	
	/**
	 * To test containsTeam working scenario
	 * 
	 * @return false
	 */
	@Test
	public void testContainsTeamWorkingFalseScenario() {
		assertFalse(soccerLeague.containsTeam("E Team"));
	}
}

