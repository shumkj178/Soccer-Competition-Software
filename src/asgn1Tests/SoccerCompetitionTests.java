package asgn1Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerCompetition class
 *
 * @author Alan Woodley
 *
 */
public class SoccerCompetitionTests {

	SoccerCompetition soccerCompetition1;
	SoccerLeague soccerLeague1;
	SoccerTeam[] teams = new SoccerTeam[16];

	@Before
	public void construct() throws TeamException, LeagueException, CompetitionException {
		// initialize objects for tests
		soccerCompetition1 = new SoccerCompetition("Competition 1", 1, 4);
		soccerLeague1 = soccerCompetition1.getLeague(0);
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
	 * To test getLeague throws exception when input a larger value than league number - 1
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
			// TODO Auto-generated catch block
			assertEquals("No such league.", e.getMessage());
		}

	}

	/**
	 * To test startNewSeason working scenario
	 * 
	 */
	@Test
	public void testStartNewSeasonWorking() {
		soccerCompetition1.startSeason();
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
	public void testStartSeasonFailWhenTheSeasonHasStarted() throws CompetitionException {
		try {
			soccerCompetition1.getLeague(0).startNewSeason();
			soccerCompetition1.startSeason();
		} catch (LeagueException e) {
			assertEquals("The league has already started.", e.getMessage());
		}
	}
}
