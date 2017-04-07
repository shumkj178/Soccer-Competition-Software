package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1SoccerCompetition.SportsTeamForm;
import asgn1SportsUtils.WLD;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerTeamForm class
 *
 * @author Alan Woodley
 *
 */
public class SportsTeamFormTests {

	private SportsTeamForm sportsTeamForms;
	
	@Before
	public void construct() {
		sportsTeamForms = new SportsTeamForm();
	}
	
	/**
	 * test addResultToForm with different scenarios
	 * Assertion 1: when added 4 times (result = WWWW-)
	 * Assertion 2: when added 5 times (result = DWWWW)
	 * Assertion 3: when added 6 times which exceeds max length (result = LDWWW)
	 */
	@Test
	public void testAddResultToFormWorking() {
		sportsTeamForms.addResultToForm(WLD.WIN);
		sportsTeamForms.addResultToForm(WLD.WIN);
		sportsTeamForms.addResultToForm(WLD.WIN);
		sportsTeamForms.addResultToForm(WLD.WIN);		
		assertEquals("WWWW-", sportsTeamForms.toString());
		sportsTeamForms.addResultToForm(WLD.DRAW);
		assertEquals("DWWWW", sportsTeamForms.toString());
		sportsTeamForms.addResultToForm(WLD.LOSS);
		assertEquals("LDWWW", sportsTeamForms.toString());
	}
	
	/**
	 * test resetForm will reset all the data/stats to initial value
	 * first assertion: to ensure forms have been added
	 * second assertion: to test out the forms have been reset
	 */
	@Test
	public void testResetFormWorking() {
		sportsTeamForms.addResultToForm(WLD.DRAW);
		sportsTeamForms.addResultToForm(WLD.LOSS);
		sportsTeamForms.addResultToForm(WLD.WIN);
		assertEquals("WLD--", sportsTeamForms.toString());
		sportsTeamForms.resetForm();
		assertEquals("-----", sportsTeamForms.toString());
	}
	
	/**
	 * test inital toString
	 */
	@Test
	public void testToStringWhenNoGameIsPlayed() {
		assertEquals("-----", sportsTeamForms.toString());
	}
	
	/**
	 * test toString when one game is played
	 */
	@Test
	public void testToStringWhenOneGameWasPlayed() {
		sportsTeamForms.addResultToForm(WLD.DRAW);
		assertEquals("D----", sportsTeamForms.toString());
	}
	
	/**
	 * test toString when six games are played
	 */
	@Test
	public void testToStringWhenSixGamesWerePlayed() {
		sportsTeamForms.addResultToForm(WLD.LOSS);
		sportsTeamForms.addResultToForm(WLD.LOSS);
		sportsTeamForms.addResultToForm(WLD.LOSS);
		sportsTeamForms.addResultToForm(WLD.LOSS);
		sportsTeamForms.addResultToForm(WLD.LOSS);
		sportsTeamForms.addResultToForm(WLD.DRAW);
		assertEquals("DLLLL", sportsTeamForms.toString());
	}
	
	/**
	 * test toString after resetting forms
	 */
	@Test
	public void testToStringAfterResetForm() {
		sportsTeamForms.addResultToForm(WLD.LOSS);
		sportsTeamForms.addResultToForm(WLD.DRAW);
		sportsTeamForms.resetForm();
		assertEquals("-----", sportsTeamForms.toString());
	}
	
	/**
	 * test getNumGames when no game is played
	 */
	@Test
	public void testGetNumGamesWhenNoGameIsPlayed() {
		assertEquals(0, sportsTeamForms.getNumGames());
	}
	
	/**
	 * test getNumGames when only one game was played
	 */
	@Test
	public void testGetNumGamesWhenOneGameWasPlayed() {
		sportsTeamForms.addResultToForm(WLD.WIN);
		assertEquals(1, sportsTeamForms.getNumGames());
	}
	
	/**
	 * test getNumGames when six games were played = 5 (maximum length)
	 */
	@Test
	public void testGetNumGamesWhenSixGamesWerePlayed() {
		sportsTeamForms.addResultToForm(WLD.WIN);
		sportsTeamForms.addResultToForm(WLD.WIN);
		sportsTeamForms.addResultToForm(WLD.WIN);
		sportsTeamForms.addResultToForm(WLD.WIN);
		sportsTeamForms.addResultToForm(WLD.WIN);
		sportsTeamForms.addResultToForm(WLD.WIN);
		assertEquals(5, sportsTeamForms.getNumGames());
	}
	
	/**
	 * test getNumGames after resetting forms
	 */
	@Test
	public void testGetNumGamesAfterResetForm() {
		sportsTeamForms.addResultToForm(WLD.WIN);
		sportsTeamForms.addResultToForm(WLD.LOSS);
		sportsTeamForms.resetForm();
		assertEquals(0, sportsTeamForms.getNumGames());
	}
}
