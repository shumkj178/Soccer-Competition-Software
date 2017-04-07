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
	
	/**
	 * To test creating object with empty official name and nick name will throw exception
	 * 
	 * @throws TeamException
	 */
	@Test (expected = TeamException.class)
	public void testCreatingTeamWithNullNamesWillThrowException() throws TeamException {
		soccerTeam3 = new SoccerTeam("", "");
	}
	
	/**
	 * To test creating object with empty official name will throw exception
	 * 
	 * @throws TeamException
	 */
	@Test (expected = TeamException.class)
	public void testCreatingTeamWithNullOfficialNameWillThrowException() throws TeamException {
		soccerTeam3 = new SoccerTeam("", "The Blues");
	}
	
	/**
	 * To test creating object with empty nick name will throw exception
	 * 
	 * @throws TeamException
	 */
	@Test (expected = TeamException.class)
	public void testCreatingTeamWithNullNickNameWillThrowException() throws TeamException {
		soccerTeam3 = new SoccerTeam("Chelsea FC", "");
	}
	
	/**
	 * To test creating object with white spaces names will throw exception
	 * 
	 * @throws TeamException
	 */
	@Test (expected = TeamException.class)
	public void TestWhiteSpacesNamesWillThrowException() throws TeamException {
		soccerTeam3 = new SoccerTeam(" ", " ");
	}
	
	/**
	 * To test creating object with white spaces official name will throw exception
	 * 
	 * @throws TeamException
	 */
	@Test (expected = TeamException.class)
	public void TestWhiteSpacesOfficialNameWillThrowException() throws TeamException {
		soccerTeam3 = new SoccerTeam(" ", "The Blues");
	}
	
	/**
	 * To test creating object with white spaces nick name will throw exception
	 * 
	 * @throws TeamException
	 */
	@Test (expected = TeamException.class)
	public void TestWhiteSpacesNickNameWillThrowException() throws TeamException {
		soccerTeam3 = new SoccerTeam("Chelsea FC", " ");
	}
	
	/**
	 * To test creating object successful scenario
	 */
	@Test
	public void testCreatingTeamFollowingRequirements() {
		try {
			soccerTeam3 = new SoccerTeam("Arsenal FC", "Gunners");
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals("Gunners", soccerTeam3.getNickName());
	}
	
	/**
	 * test getOfficialName
	 */
	@Test
	public void testGetOfficialName() {
		assertEquals("Liverpool FC", soccerTeam1.getOfficialName());
	}
	
	/**
	 * test getNickName
	 */
	@Test
	public void testGetNickName() {
		assertEquals("The Reds", soccerTeam1.getNickName());
	}
	
	/**
	 * test initial getGoalsScoredSeason 
	 */
	@Test
	public void testInitialGoalsScoredSeason() {
		assertEquals(0, soccerTeam1.getGoalsScoredSeason());
	}
	
	/**
	 * test getGoalsScoredSeason after playing 3 matches
	 */
	@Test
	public void testGoalsScoredSeasonAfter3Matches() {
		try {
			soccerTeam1.playMatch(1, 0);
			soccerTeam1.playMatch(2, 1);
			soccerTeam1.playMatch(3, 3);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}	
		assertEquals(6, soccerTeam1.getGoalsScoredSeason());
	}
	
	/**
	 * test initial getGoalsConcededSeason 
	 */
	@Test
	public void testInitialGoalsConcededSeason() {
		assertEquals(0, soccerTeam1.getGoalsConcededSeason());
	}
	
	/**
	 * test getGoalsConcededSeason after playing 3 matches
	 */
	@Test
	public void testGoalsConcededSeasonAfter3Matches() {
		try {
			soccerTeam1.playMatch(1, 0);
			soccerTeam1.playMatch(2, 1);
			soccerTeam1.playMatch(3, 3);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(4, soccerTeam1.getGoalsConcededSeason());
	}
	
	/**
	 * test initial getMatchesWon
	 */
	@Test
	public void testInitialGetMatchesWon() {
		assertEquals(0, soccerTeam1.getMatchesWon());
	}
	
	/**
	 * test getMatchesWon after playing 3 matches
	 */
	@Test
	public void testGetMatchesWonAfter3Matches() {
		try {
			soccerTeam1.playMatch(4, 0);
			soccerTeam1.playMatch(2, 0);
			soccerTeam1.playMatch(1, 0);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(3, soccerTeam1.getMatchesWon());
	}
	
	/**
	 * test initial getMatchesLost
	 */
	@Test
	public void testInitialGetMatchesLost() {
		assertEquals(0, soccerTeam1.getMatchesLost());
	}
	
	/**
	 * test getMatchesLost after playing 3 matches
	 */
	@Test
	public void testGetMatchesLostAfter3Matches() {
		try {
			soccerTeam2.playMatch(4, 5);
			soccerTeam2.playMatch(2, 0);
			soccerTeam2.playMatch(1, 3);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(2, soccerTeam2.getMatchesLost());
	}
	
	/**
	 * test initial getMatchesDrawn
	 */
	@Test
	public void testInitialGetMatchesDrawn() {
		assertEquals(0, soccerTeam1.getMatchesDrawn());
	}
	
	/**
	 * test getMatchesLost after playing 3 matches
	 */
	@Test
	public void testGetMatchesDrawnAfter3Matches() {
		try {
			soccerTeam2.playMatch(4, 4);
			soccerTeam2.playMatch(2, 2);
			soccerTeam2.playMatch(3, 3);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(3, soccerTeam2.getMatchesDrawn());
	}
	
	/**
	 * test initial getCompetitionPoints
	 */
	@Test
	public void testInitialGetCompetitionPoints() {
		assertEquals(0, soccerTeam1.getCompetitionPoints());
	}
	
	/**
	 * test getCompetitionPoints after playing 3 matches
	 */
	@Test
	public void testGetCompetitionPointsAfter3Matches() {
		try {
			soccerTeam1.playMatch(4, 2);
			soccerTeam1.playMatch(1, 0);
			soccerTeam1.playMatch(3, 0);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(9, soccerTeam1.getCompetitionPoints());
	}
	
	/**
	 * test initial getGoalDifference
	 */
	@Test
	public void testInitialGetGoalDifference() {
		assertEquals(0, soccerTeam1.getGoalDifference());
	}
	
	/**
	 * test getGoalDifference after playing 3 matches
	 */
	@Test
	public void testGetGoalDifferenceAfter3Matches() {
		try {
			soccerTeam1.playMatch(4, 2);
			soccerTeam1.playMatch(1, 0);
			soccerTeam1.playMatch(3, 0);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(6, soccerTeam1.getGoalDifference());
	}
	
	/**
	 * test initial getFormString
	 */
	@Test
	public void testInitialGetFormString() {
		assertEquals("-----", soccerTeam1.getFormString());
	}
	
	/**
	 * test getFormString with not exceeding five matches scenario
	 */
	@Test
	public void testGetFormStringIfNotExceedsFiveMatches() {
		try {
			soccerTeam1.playMatch(1, 0);
			soccerTeam1.playMatch(2, 1);
			soccerTeam1.playMatch(3, 3);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals("DWW--", soccerTeam1.getFormString());
	}
	
	/**
	 * test getFormString with exceeding five matches scenario
	 */
	@Test
	public void testGetFormStringWhenPlayMoreThanFiveMatches() {
		try {
			soccerTeam1.playMatch(6, 0);
			soccerTeam1.playMatch(2, 0);
			soccerTeam1.playMatch(0, 0);
			soccerTeam1.playMatch(4, 0);
			soccerTeam1.playMatch(0, 1);
			soccerTeam1.playMatch(2, 2);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}		
		assertEquals("DLWDW", soccerTeam1.getFormString());
	}
	
	/**
	 * test playMatch getting exception for negative value
	 */
	@Test (expected = TeamException.class)
	public void testPlayMatchGettingExceptionForValueLowerThan0() throws TeamException {
		soccerTeam1.playMatch(-1, 0);
	}
	
	/**
	 * test playMatch getting exception for value larger than 20
	 */
	@Test (expected = TeamException.class)
	public void testPlayMatchGettingExceptionForValueGreaterThan20() throws TeamException {
		soccerTeam1.playMatch(0, 21);
	}
	
	/**
	 * test playMatch getting exception message
	 */
	@Test
	public void testPlayMatchGettingCorrectExceptionMessage() {
		try {
			soccerTeam1.playMatch(0, 22);
		} catch (TeamException e) {
			assertEquals("Unrealistic number of goals!", e.getMessage());
		}
	}
	
	/**
	 * test playMatch when goalsFor is greater than goalsAgainst
	 */
	@Test
	public void testPlayMatchWhenGoalsForIsGreaterThanGoalsAgainst() {
		try {
			soccerTeam1.playMatch(4, 1);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(1, soccerTeam1.getMatchesWon());
		assertEquals(0, soccerTeam1.getMatchesDrawn());
		assertEquals(0, soccerTeam1.getMatchesLost());
		assertEquals(3, soccerTeam1.getCompetitionPoints());
		assertEquals(4, soccerTeam1.getGoalsScoredSeason());
		assertEquals(1, soccerTeam1.getGoalsConcededSeason());
		assertEquals(3, soccerTeam1.getGoalDifference());
		assertEquals("W----", soccerTeam1.getFormString());
	}
	
	/**
	 * test playMatch when goalsAgainst is greater than goalsFor
	 */
	@Test
	public void testPlayMatchWhenGoalsForIsLowerThanGoalsAgainst() {
		try {
			soccerTeam1.playMatch(0, 1);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(0, soccerTeam1.getMatchesWon());
		assertEquals(0, soccerTeam1.getMatchesDrawn());
		assertEquals(1, soccerTeam1.getMatchesLost());
		assertEquals(0, soccerTeam1.getCompetitionPoints());
		assertEquals(0, soccerTeam1.getGoalsScoredSeason());
		assertEquals(1, soccerTeam1.getGoalsConcededSeason());
		assertEquals(-1, soccerTeam1.getGoalDifference());
		assertEquals("L----", soccerTeam1.getFormString());
	}
	
	/**
	 * test playMatch when goalsFor is greater than goalsAgainst
	 */
	@Test
	public void testPlayMatchWhenGoalsForIsEqualThanGoalsAgainst() {
		try {
			soccerTeam1.playMatch(1, 1);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(0, soccerTeam1.getMatchesWon());
		assertEquals(1, soccerTeam1.getMatchesDrawn());
		assertEquals(0, soccerTeam1.getMatchesLost());
		assertEquals(1, soccerTeam1.getCompetitionPoints());
		assertEquals(1, soccerTeam1.getGoalsScoredSeason());
		assertEquals(1, soccerTeam1.getGoalsConcededSeason());
		assertEquals(0, soccerTeam1.getGoalDifference());
		assertEquals("D----", soccerTeam1.getFormString());
		
	}
	
	/**
	 * test playMatch getting correct results after playing 3 matches with all wins
	 */
	@Test
	public void testGettingCorrectResultsAfterWinning3Matches() {	
		try {
			soccerTeam1.playMatch(4, 1);
			soccerTeam1.playMatch(5, 0);
			soccerTeam1.playMatch(3, 1);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(3, soccerTeam1.getMatchesWon());
		assertEquals(0, soccerTeam1.getMatchesDrawn());
		assertEquals(0, soccerTeam1.getMatchesLost());
		assertEquals(9, soccerTeam1.getCompetitionPoints());
		assertEquals(12, soccerTeam1.getGoalsScoredSeason());
		assertEquals(2, soccerTeam1.getGoalsConcededSeason());
		assertEquals(10, soccerTeam1.getGoalDifference());
		assertEquals("WWW--", soccerTeam1.getFormString());
	}
	
	/**
	 * test playMatch getting correct results after playing 3 matches with all loses
	 */
	@Test
	public void testGettingCorrectResultAfterLosing3Matches() {
		try {
			soccerTeam1.playMatch(4, 5);
			soccerTeam1.playMatch(0, 2);
			soccerTeam1.playMatch(1, 4);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(0, soccerTeam1.getMatchesWon());
		assertEquals(0, soccerTeam1.getMatchesDrawn());
		assertEquals(3, soccerTeam1.getMatchesLost());
		assertEquals(0, soccerTeam1.getCompetitionPoints());
		assertEquals(5, soccerTeam1.getGoalsScoredSeason());
		assertEquals(11, soccerTeam1.getGoalsConcededSeason());
		assertEquals(-6, soccerTeam1.getGoalDifference());
		assertEquals("LLL--", soccerTeam1.getFormString());
	}
	
	/**
	 * test playMatch getting correct results after playing 3 matches with all draws
	 */
	@Test
	public void testGettingCorrectResultAfterDrawing3Matches() {
		try {
			soccerTeam1.playMatch(2, 2);
			soccerTeam1.playMatch(0, 0);
			soccerTeam1.playMatch(4, 4);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}	
		assertEquals(0, soccerTeam1.getMatchesWon());
		assertEquals(3, soccerTeam1.getMatchesDrawn());
		assertEquals(0, soccerTeam1.getMatchesLost());
		assertEquals(3, soccerTeam1.getCompetitionPoints());
		assertEquals(6, soccerTeam1.getGoalsScoredSeason());
		assertEquals(6, soccerTeam1.getGoalsConcededSeason());
		assertEquals(0, soccerTeam1.getGoalDifference());
		assertEquals("DDD--", soccerTeam1.getFormString());
	}
	
	/**
	 * test playMatch getting correct results after playing 10 random matches
	 */
	@Test
	public void testGettingCorrectResultAfterPlaying10randomMatches() {
		try {
			soccerTeam1.playMatch(4, 2); //W, +2
			soccerTeam1.playMatch(0, 0); //D, +0
			soccerTeam1.playMatch(2, 0); //W, +2
			soccerTeam1.playMatch(1, 2); //L, -1
			soccerTeam1.playMatch(2, 3); //L, -1
			soccerTeam1.playMatch(3, 1); //W, +2
			soccerTeam1.playMatch(5, 0); //W, +5
			soccerTeam1.playMatch(5, 0); //W, +5
			soccerTeam1.playMatch(3, 0); //W, +3
			soccerTeam1.playMatch(1, 0); //W, +1
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(7, soccerTeam1.getMatchesWon());
		assertEquals(1, soccerTeam1.getMatchesDrawn());
		assertEquals(2, soccerTeam1.getMatchesLost());
		assertEquals(22, soccerTeam1.getCompetitionPoints());
		assertEquals(26, soccerTeam1.getGoalsScoredSeason());
		assertEquals(8, soccerTeam1.getGoalsConcededSeason());
		assertEquals(18, soccerTeam1.getGoalDifference());
		assertEquals("WWWWW", soccerTeam1.getFormString());
	}
	
	/**
	 * test compareTo with same team
	 */
	@Test
	public void testCompareToWithSameTeam() {
		assertEquals(0, soccerTeam1.compareTo(soccerTeam1));
	}
	
	/**
	 * test compareTo in different points scenario
	 */
	@Test
	public void testCompareToWithDifferentPoints() {
		try {
			soccerTeam1.playMatch(3, 0);
			soccerTeam2.playMatch(0, 3);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}		
		assertEquals(-3, soccerTeam1.compareTo(soccerTeam2));
	}
	
	/**
	 * test compareTo same points different goal difference scenario
	 */
	@Test
	public void testCompareToWithSamePointsButDifferentGoalDifference() {
		try {
			soccerTeam1.playMatch(2, 0);
			soccerTeam2.playMatch(3, 0);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(1, soccerTeam1.compareTo(soccerTeam2));
	}
	
	/**
	 * test compareTo same points same goal difference scenario 
	 */
	@Test
	public void testCompareToWithSamePointsAndSameGoalDifference() {
		try {
			soccerTeam1.playMatch(3, 0);
			soccerTeam2.playMatch(3, 0);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(-1, soccerTeam1.compareTo(soccerTeam2));
	}
	
	/**
	 * test resetStats for competition points
	 * playMatch requires to create initial statistics
	 */
	@Test
	public void testResetStatsForCompetitionPoints() {
		try {
			soccerTeam1.playMatch(3, 0);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(3, soccerTeam1.getCompetitionPoints());
		soccerTeam1.resetStats();
		assertEquals(0, soccerTeam1.getCompetitionPoints());
	}
	
	/**
	 * test resetStats for goal scored
	 * playMatch requires to create initial statistics
	 */
	@Test
	public void testResetStatsForGoalScored() {
		try {
			soccerTeam1.playMatch(3, 0);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(3, soccerTeam1.getGoalsScoredSeason());
		soccerTeam1.resetStats();
		assertEquals(0, soccerTeam1.getGoalsScoredSeason());
	}
	
	/**
	 * test resetStats for goal conceded
	 * playMatch requires to create initial statistics
	 */
	@Test
	public void testResetStatsForGoalConceded() {
		try {
			soccerTeam1.playMatch(3, 2);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(2, soccerTeam1.getGoalsConcededSeason());
		soccerTeam1.resetStats();
		assertEquals(0, soccerTeam1.getGoalsConcededSeason());
	}
	
	/**
	 * test resetStats for matches won
	 * playMatch requires to create initial statistics
	 */
	@Test
	public void testResetStatsForMatchesWon() {
		try {
			soccerTeam1.playMatch(3, 2);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(1, soccerTeam1.getMatchesWon());
		soccerTeam1.resetStats();
		assertEquals(0, soccerTeam1.getMatchesWon());
	}
	
	/**
	 * test resetStats for matches drawn
	 * playMatch requires to create initial statistics
	 */
	@Test
	public void testResetStatsForMatchesDrawn() {
		try {
			soccerTeam1.playMatch(3, 3);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(1, soccerTeam1.getMatchesDrawn());
		soccerTeam1.resetStats();
		assertEquals(0, soccerTeam1.getMatchesDrawn());
	}
	
	/**
	 * test resetStats for matches lost
	 * playMatch requires to create initial statistics
	 */
	@Test
	public void testResetStatsForMatchesLost() {
		try {
			soccerTeam1.playMatch(1, 3);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals(1, soccerTeam1.getMatchesLost());
		soccerTeam1.resetStats();
		assertEquals(0, soccerTeam1.getMatchesLost());
	}
	
	/**
	 * test resetStats for team forms
	 * playMatch requires to create initial statistics
	 */
	@Test
	public void testResetStatsForTeamForms() {
		try {
			soccerTeam1.playMatch(3, 0);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
		assertEquals("W----", soccerTeam1.getFormString());
		soccerTeam1.resetStats();
		assertEquals("-----", soccerTeam1.getFormString());
	}
}
