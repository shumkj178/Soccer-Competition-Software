package asgn1Tests;

import static org.junit.Assert.*;

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
	SoccerCompetition soccerCompetition2;
	SoccerLeague soccerLeague1;
	SoccerLeague soccerLeague2;
	SoccerTeam soccerTeam1;
	SoccerTeam soccerTeam2;
	SoccerTeam soccerTeam3;
	SoccerTeam soccerTeam4;
	
	@Before
	public void consturct() throws TeamException, LeagueException, CompetitionException {
		//initialize objects for tests
		soccerCompetition1 = new SoccerCompetition("Competition 1", 1, 4);
		soccerCompetition2 = new SoccerCompetition("Competition 2", 2, 4);
		soccerLeague1 = new SoccerLeague(4);
		soccerLeague2 = new SoccerLeague(4);
		soccerTeam1 = new SoccerTeam("A Team", "Team 1");
		soccerTeam2 = new SoccerTeam("B Team", "Team 2");
		soccerTeam3 = new SoccerTeam("C Team", "Team 3");
		soccerTeam4 = new SoccerTeam("D Team", "Team 4");
		soccerLeague1.registerTeam(soccerTeam1);
		soccerLeague1.registerTeam(soccerTeam2);
		soccerLeague1.registerTeam(soccerTeam3);
		soccerLeague1.registerTeam(soccerTeam4);
		soccerCompetition1.getLeague(0).registerTeam(soccerTeam1);
	}
	
	/**
	 * To test getLeague working scenario using assertions
	 * 
	 * @throws CompetitionException
	 */
	@Test 
	public void testGetLeagueWorking() throws CompetitionException {
		SoccerLeague x = soccerCompetition1.getLeague(0);
		SoccerLeague y = soccerCompetition2.getLeague(1);
		assertSame(soccerCompetition1.getLeague(0), x);
		assertSame(soccerCompetition2.getLeague(1), y);
	}
	
	/**
	 * To test getLeague throws exception when the league is not existed
	 * 
	 * @throws CompetitionException
	 */
	@Test (expected = CompetitionException.class)
	public void testGettingNotExistingLeagueWillThrowException() throws CompetitionException {
		soccerCompetition2.getLeague(2);
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
	 * To test startNewSeason fails when the league has not enough of teams
	 * @throws CompetitionException 
	 * @throws LeagueException 
	 */
	@Test
	public void testStartNewSeasonFailWhenTeamsAreNotEnough() throws LeagueException, CompetitionException {
		soccerCompetition1.startSeason();
		assertEquals("A Team", soccerCompetition1.getLeague(0).getTeamByOfficalName("A Team"));
	}
}

