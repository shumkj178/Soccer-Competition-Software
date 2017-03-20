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
	
	@Before
	public void consturct() {
		soccerCompetition1 = new SoccerCompetition("League1", 1, 4);
		soccerCompetition2 = new SoccerCompetition("League2", 2, 4);
		soccerCompetition3 = new SoccerCompetition("League3", 3, 4);
	}
}

