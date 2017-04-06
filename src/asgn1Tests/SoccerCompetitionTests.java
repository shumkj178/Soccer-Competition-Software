package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;
import junit.framework.Assert;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerCompetition class
 *
 * @author Alan Woodley
 *
 */
public class SoccerCompetitionTests {

	//Declare objects need for unit test
	SoccerCompetition soccerCompetition1;
	SoccerLeague soccerLeague1;
	//Declare array to store teams
	SoccerTeam[] teams = new SoccerTeam[16];

	@Before
	public void construct() throws TeamException, LeagueException, CompetitionException {
		// initialize objects for tests
		soccerCompetition1 = new SoccerCompetition("Competition 1", 1, 4);
		soccerLeague1 = soccerCompetition1.getLeague(0);
		/* register 4 teams into 1 league only
		 * in order to shorten execution time as not every test needs 4 leagues with teams
		 */
		for (int i = 0; i < 16; i++) {
			teams[i] = new SoccerTeam("Team " + i, "team " + i);
			if (i <= 3) {
				soccerLeague1.registerTeam(teams[i]);
			}
		}
	}

	/**
	 * To test getLeague working scenario using assertions
	 * 
	 * @throws CompetitionException
	 */
	@Test
	public void testGetLeagueWorking() throws CompetitionException {
		SoccerLeague x = soccerCompetition1.getLeague(0);
		assertSame(soccerCompetition1.getLeague(0), x);
	}

	/**
	 * To test getLeague throws exception when input a larger value than league number = 1
	 * 
	 * @throws CompetitionException
	 */
	@Test(expected = CompetitionException.class)
	public void testGettingLeaugeWillThrowExceptionWhenInputLargerValue() throws CompetitionException {
		soccerCompetition1.getLeague(2);
	}

	/**
	 * To test getLeague throws exception when input a negative value
	 * 
	 * @throws CompetitionException
	 */
	@Test (expected = CompetitionException.class)
	public void testGettingLeagueWillThrowExceptionWhenInputNegativeValue() throws CompetitionException {
		soccerCompetition1.getLeague(-1);
	}
	
	/**
	 * To test getLeague throws exception message
	 * 
	 * @throws CompetitionException
	 */
	@Test
	public void testGetLeagueExceptionPromptMessage() {
		try {
			soccerCompetition1.getLeague(1);
		} catch (CompetitionException e) {
			assertEquals("No such league.", e.getMessage());
		}

	}

	/**
	 * To test startNewSeason working scenario
	 * @throws CompetitionException 
	 * 
	 */
	@Test
	public void testStartNewSeasonWorking() throws CompetitionException {
		soccerCompetition1.startSeason();
		assertFalse(soccerCompetition1.getLeague(0).isOffSeason());
	}

	/**
	 * To test startSeason fails when the league has not enough of teams Require
	 * Require to remove a team from the league to make the league insufficient of teams
	 * Use assertion to confirm whether the league has started or not
	 * 
	 * @throws CompetitionException
	 * @throws LeagueException
	 */
	@Test
	public void testStartSeasonFailWhenTeamsAreNotEnough() throws LeagueException, CompetitionException {
		soccerCompetition1.getLeague(0).removeTeam(teams[2]);
		soccerCompetition1.startSeason();
		assertTrue(soccerCompetition1.getLeague(0).isOffSeason());
	}

	/**
	 * To test startSeason fails when the league has already started
	 * Require to use startNewSeason method from soccerLeague class to start the season first
	 * Use assertion to confirm the exception is threw
	 * 
	 * @throws CompetitionException
	 * @throws LeagueException
	 */
	@Test
	public void testStartSeasonFailsWhenTheSeasonHasStarted() throws CompetitionException {
		try {
			soccerCompetition1.getLeague(0).startNewSeason();
			soccerCompetition1.startSeason();
		} catch (LeagueException e) {
			assertEquals("The league has already started.", e.getMessage());
		}
	}
	
	/**
	 * To test endSeason working scenario
	 * Require to use startSeason in order to make the season start first
	 * 
	 * @throws CompetitionException
	 */
	@Test
	public void testEndSeasonWorking() throws CompetitionException {
		soccerCompetition1.startSeason();
		soccerCompetition1.endSeason();
		assertTrue(soccerCompetition1.getLeague(0).isOffSeason());
	}
	
	/**
	 * To test endSeason fails when the season has not started yet
	 * 
	 * @throws CompetitionException
	 */
	@Test
	public void testEndSeasonFailsWhenTheSeasonHasNotStartedYet() throws CompetitionException {
		soccerCompetition1.endSeason();
		assertTrue(soccerCompetition1.getLeague(0).isOffSeason());
	}
	
	/**
	 * To test endSeason will handle the promotion and relegation when there is 1 league
	 * startSeason required as to start off the season
	 * playMatch is used to generate match statistics
	 * sortTeams is needed to sort the teams according the ranking
	 * containsTeam is used to check whether the team has been promoted or relegated
	 * 
	 * @throws CompetitionException
	 * @throws LeagueException
	 */
	@Test
	public void testEndSeasonPromotionAndRelagationTakePlaceWith1League() throws CompetitionException, LeagueException {
		soccerCompetition1.startSeason();
		soccerLeague1.playMatch("Team 1", 5, "Team 2", 0);
		soccerLeague1.sortTeams();
		soccerCompetition1.endSeason();
		//After ending season, Team 2 will expected to be remained at the league
		assertTrue(soccerLeague1.containsTeam("Team 2"));
	}
	
	/**
	 * To test endSeason will handle the promotion and relegation when there is 2 leagues
	 * registerTeam method of soccerLeague class is used to register team into leagues
	 * startSeason required as to start off the season
	 * playMatch is used to generate match statistics
	 * sortTeams is needed to sort the teams according the ranking
	 * containsTeam is used to check whether the team has been promoted or relegated
	 * 
	 * @throws CompetitionException
	 * @throws LeagueException
	 */
	@Test
	public void testEndSeasonPromotionAndRelagationTakePlaceWith2Leagues() throws CompetitionException, LeagueException {
		SoccerCompetition soccerCompetition2 = new SoccerCompetition("La Liga", 2, 4);
		SoccerLeague soccerLeague_A = soccerCompetition2.getLeague(0);
		SoccerLeague soccerLeague_B = soccerCompetition2.getLeague(1);
		for (int i = 0; i < 8; i++) {
			if (i <= 3) {
				soccerLeague_A.registerTeam(teams[i]);
			} else {
				soccerLeague_B.registerTeam(teams[i]);
			}
		}
		soccerCompetition2.startSeason();
		soccerLeague_A.playMatch("Team 1", 5, "Team 2", 0);
		soccerLeague_B.playMatch("Team 5", 8, "Team 6", 3);
		soccerLeague_A.sortTeams();
		soccerLeague_B.sortTeams();
		soccerCompetition2.endSeason();
		//After ending season, Team 5 will be expected to get promotion while Team 2 will be relegated
		assertTrue(soccerLeague_A.containsTeam("Team 5"));
		assertTrue(soccerLeague_B.containsTeam("Team 2"));
	}
	
	/**
	 * To test endSeason will handle the promotion and relegation when there is 3 leagues
	 * registerTeam method of soccerLeague class is used to register team into leagues
	 * startSeason required as to start off the season
	 * playMatch is used to generate match statistics
	 * sortTeams is needed to sort the teams according the ranking
	 * containsTeam is used to check whether the team has been promoted or relegated
	 * 
	 * @throws CompetitionException
	 * @throws LeagueException
	 */
	@Test
	public void testEndSeasonPromotionAndRelagationTakePlaceWith3Leagues() throws CompetitionException, LeagueException {
		SoccerCompetition soccerCompetition3 = new SoccerCompetition("Serie A", 3, 4);
		SoccerLeague soccerLeague_A = soccerCompetition3.getLeague(0);
		SoccerLeague soccerLeague_B = soccerCompetition3.getLeague(1);
		SoccerLeague soccerLeague_C = soccerCompetition3.getLeague(2);
		for (int i = 0; i < 12; i++) {
			if (i <= 3) {
				soccerLeague_A.registerTeam(teams[i]);
			} else if (i <= 7) {
				soccerLeague_B.registerTeam(teams[i]);
			} else {
				soccerLeague_C.registerTeam(teams[i]);
			}
		}
		soccerCompetition3.startSeason();
		soccerLeague_A.playMatch("Team 1", 5, "Team 2", 0);
		soccerLeague_B.playMatch("Team 5", 8, "Team 6", 3);
		soccerLeague_C.playMatch("Team 10", 2, "Team 11", 0);
		soccerLeague_A.sortTeams();
		soccerLeague_B.sortTeams();
		soccerLeague_C.sortTeams();
		soccerCompetition3.endSeason();
		//After ending season, Team 5 and Team 10 will be expected to get promotion 
		//while Team 2 and Team 6 will be relegated
		assertTrue(soccerLeague_A.containsTeam("Team 5"));
		assertTrue(soccerLeague_B.containsTeam("Team 2"));
		assertTrue(soccerLeague_B.containsTeam("Team 10"));
		assertTrue(soccerLeague_C.containsTeam("Team 6"));
	}
	
	/**
	 * To test endSeason will handle the promotion and relegation when there is 4 leagues
	 * registerTeam method of soccerLeague class is used to register team into leagues
	 * startSeason required as to start off the season
	 * playMatch is used to generate match statistics
	 * sortTeams is needed to sort the teams according the ranking
	 * containsTeam is used to check whether the team has been promoted or relegated
	 * 
	 * @throws CompetitionException
	 * @throws LeagueException
	 */
	@Test
	public void testEndSeasonPromotionAndRelagationTakePlaceWith4Leagues() throws CompetitionException, LeagueException {
		SoccerCompetition soccerCompetition4 = new SoccerCompetition("Ligue 1", 4, 4);
		SoccerLeague soccerLeague_A = soccerCompetition4.getLeague(0);
		SoccerLeague soccerLeague_B = soccerCompetition4.getLeague(1);
		SoccerLeague soccerLeague_C = soccerCompetition4.getLeague(2);
		SoccerLeague soccerLeague_D = soccerCompetition4.getLeague(3);
		for (int i = 0; i < 16; i++) {
			if (i <= 3) {
				soccerLeague_A.registerTeam(teams[i]);
			} else if (i <= 7) {
				soccerLeague_B.registerTeam(teams[i]);
			} else if (i <= 11) {
				soccerLeague_C.registerTeam(teams[i]);
			} else {
				soccerLeague_D.registerTeam(teams[i]);
			}
		}
		soccerCompetition4.startSeason();
		soccerLeague_A.playMatch("Team 1", 5, "Team 2", 0);
		soccerLeague_B.playMatch("Team 5", 8, "Team 6", 3);
		soccerLeague_C.playMatch("Team 10", 2, "Team 11", 0);
		soccerLeague_D.playMatch("Team 13", 3, "Team 14", 0);
		soccerLeague_A.sortTeams();
		soccerLeague_B.sortTeams();
		soccerLeague_C.sortTeams();
		soccerLeague_D.sortTeams();
		soccerCompetition4.endSeason();
		//After ending season, Team 5, Team 10 and Team 13 will be expected to get promotion 
		//while Team 2, Team 6 and Team 11 will be relegated
		assertTrue(soccerLeague_A.containsTeam("Team 5"));
		assertTrue(soccerLeague_B.containsTeam("Team 2"));
		assertTrue(soccerLeague_B.containsTeam("Team 10"));
		assertTrue(soccerLeague_C.containsTeam("Team 6"));
		assertTrue(soccerLeague_C.containsTeam("Team 13"));
		assertTrue(soccerLeague_D.containsTeam("Team 11"));
	}
	
	
}
