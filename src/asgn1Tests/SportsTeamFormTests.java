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
	 * eg1: when added 4 times (result = WWWW-)
	 * eg2: when added 5 times (result = DWWWW)
	 * eg3: when added 6 times which exceeds max length (result = LDWWW)
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
	 * test resetForm will reset all the data/stats to initiate value
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
}
