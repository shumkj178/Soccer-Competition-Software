package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerTeam;



/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 *
 */
public class SoccerTeamTests {

	SoccerTeam soccerTeam1;
	SoccerTeam soccerTeam2;
	
	@Before
	public void construct() throws TeamException {
		soccerTeam1 = new SoccerTeam("Liverpool FC", "The Reds");
	}
	
	@Test (expected = TeamException.class)
	public void testCreatingTeamWithNullOfficialNameWillThrowException() throws TeamException {
		soccerTeam2 = new SoccerTeam("Chelsea FC", "");
	}
	
	@Test
	public void testGetOfficialName() {
		assertEquals("Liverpool FC", soccerTeam1.getOfficialName());
	}
	
	@Test
	public void testGetNickName() {
		assertEquals("The Reds", soccerTeam1.getNickName());
	}
	
	@Test
	public void testGetFormString() throws TeamException {
		soccerTeam1.playMatch(1, 0);
		soccerTeam1.playMatch(2, 1);
		assertEquals("WW---", soccerTeam1.getFormString());
	}
	
	@Test
	public void testGetFormStringWhenPlayMoreThanFiveMatches() throws TeamException {
		soccerTeam1.playMatch(6, 0);
		soccerTeam1.playMatch(2, 0);
		soccerTeam1.playMatch(0, 0);
		soccerTeam1.playMatch(4, 0);
		soccerTeam1.playMatch(0, 1);
		soccerTeam1.playMatch(2, 2);
		assertEquals("DLWDW", soccerTeam1.getFormString());
	}
	
	@Test (expected = TeamException.class)
	public void testPlayMatchGettingException() throws TeamException {
		soccerTeam1.playMatch(-1, 0);
	}
	
	@Test
	public void testGettingCorrectResultsAfterPlayMatch() throws TeamException {
		soccerTeam1.playMatch(4, 1);
		soccerTeam1.playMatch(5, 0);
		assertEquals(6, soccerTeam1.getCompetitionPoints());
		assertEquals(9, soccerTeam1.getGoalsScoredSeason());
		assertEquals(1, soccerTeam1.getGoalsConcededSeason());
		assertEquals(8, soccerTeam1.getGoalDifference());
	}
}
