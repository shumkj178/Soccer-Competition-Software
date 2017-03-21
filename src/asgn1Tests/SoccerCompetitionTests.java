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
	SoccerCompetition soccerCompetition3;
	SoccerCompetition soccerCompetition4;
	
	@Before
	public void consturct() {
		//create object for 1 league
		soccerCompetition1 = new SoccerCompetition("League1", 1, 4);
		//create object for 2 leagues
		soccerCompetition2 = new SoccerCompetition("League2", 2, 4);
		//create object for 3 leagues
		soccerCompetition3 = new SoccerCompetition("League3", 3, 4);
		//create object for 4 leagues
		soccerCompetition4 = new SoccerCompetition("League4", 4, 4);
	}
	
	@Test
	public void testGettingCorrectAndIncorrectLeague() throws CompetitionException {
		SoccerLeague x = soccerCompetition3.getLeague(1);
		SoccerLeague y = soccerCompetition3.getLeague(2);
		assertNotSame(soccerCompetition3.getLeague(0), x);
		assertSame(soccerCompetition3.getLeague(2), y);
	}
	
	@Test (expected = CompetitionException.class)
	public void testGettingNotExistingLeagueWillThrowException() throws CompetitionException {
		soccerCompetition2.getLeague(2);
	}
	
	@Test
	public void testGetLeagueExceptionPromptMessage() {
		try {
			soccerCompetition1.getLeague(1);
		} catch (CompetitionException e) {
			// TODO Auto-generated catch block
			assertEquals("No such league.", e.getMessage());
		}
		
	}
}

