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
	SoccerTeam soccerTeam3;
	
	@Before
	public void construct() throws TeamException {
		soccerTeam1 = new SoccerTeam("Liverpool FC", "The Reds");
		soccerTeam2 = new SoccerTeam("Manchester United FC", "Red Devils");
	}
	
	@Test (expected = TeamException.class)
	public void testCreatingTeamWithNullOfficialNameWillThrowException() throws TeamException {
		soccerTeam3 = new SoccerTeam("Chelsea FC", "");
	}
	
	@Test
	public void testCreatingTeamFollowingRequirements() throws TeamException {
		soccerTeam3 = new SoccerTeam("Arsenal FC", "Gunners");
	}
	
	@Test
	public void testGetFormStringIfNotExceedsFiveMatches() throws TeamException {
		soccerTeam1.playMatch(1, 0);
		soccerTeam1.playMatch(2, 1);
		soccerTeam1.playMatch(3, 3);
		assertEquals("DWW--", soccerTeam1.getFormString());
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
	public void testPlayMatchGettingExceptionForValueLowerThan0() throws TeamException {
		soccerTeam1.playMatch(-1, 0);
	}
	
	@Test (expected = TeamException.class)
	public void testPlayMatchGettingExceptionForValueGreaterThan20() throws TeamException {
		soccerTeam1.playMatch(0, 21);
	}
	
	@Test
	public void testPlayMatchGettingCorrectExceptionMessage() {
		try {
			soccerTeam1.playMatch(0, 22);
		} catch (TeamException e) {
			// TODO Auto-generated catch block
			assertEquals("", e.getMessage());
		}
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
	
	@Test
	public void testCompareToMethodReturningCorrectValue() throws TeamException {
		soccerTeam1.playMatch(2, 0);
		soccerTeam2.playMatch(0, 2);
		assertEquals(-3, soccerTeam1.compareTo(soccerTeam2));
	}
	
	@Test
	public void testResetStats() throws TeamException {
		soccerTeam1.playMatch(3, 0);
		assertEquals(3, soccerTeam1.getCompetitionPoints());
		soccerTeam1.resetStats();
		assertEquals(0, soccerTeam1.getCompetitionPoints());
	}
	
	@Test
	public void testResetStatsForTeamForms() throws TeamException {
		soccerTeam1.playMatch(2, 0);
		assertEquals("W----", soccerTeam1.getFormString());
		soccerTeam1.resetStats();
		assertEquals("-----", soccerTeam1.getFormString());
	}
}
