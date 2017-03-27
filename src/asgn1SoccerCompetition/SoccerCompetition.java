/**
 * 
 */
package asgn1SoccerCompetition;

import java.util.LinkedList;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;

/**
 * A class to model a soccer competition. The competition contains one or more
 * number of leagues, each of which contain a number of teams. Over the course a
 * season matches are played between teams in each league. At the end of the
 * season a premier (top ranked team) and wooden spooner (bottom ranked team)
 * are declared for each league. If there are multiple leagues then relegation
 * and promotions occur between the leagues.
 * 
 * @author Alan Woodley
 * @version 1.0
 *
 */
public class SoccerCompetition implements SportsCompetition {

	String name;
	//
	private int numLeagues;
	private LinkedList<SoccerLeague> soccerLeagues;

	/**
	 * Creates the model for a new soccer competition with a specific name,
	 * number of leagues, and number of games to display to indicate the teams
	 * form.
	 * 
	 * @param name
	 *            The name of the competition.
	 * @param numLeagues
	 *            The number of leagues in the competition.
	 * @param numTeams
	 *            The number of teams in each league.
	 */
	public SoccerCompetition(String name, int numLeagues, int numTeams) {
		this.name = name;
		// TO DO Complete
		this.numLeagues = numLeagues;
		soccerLeagues = new LinkedList<SoccerLeague>();
		for (int i = 0; i < numLeagues; i++) {
			soccerLeagues.add(new SoccerLeague(numTeams));
		}
	}

	/**
	 * Retrieves a league with a specific number (indexed from 0). Returns an
	 * exception if the league number is invalid.
	 * 
	 * @param leagueNum
	 *            The number of the league to return.
	 * @return A league specified by leagueNum.
	 * @throws CompetitionException
	 *             if the specified league number is less than 0. or equal to or
	 *             greater than the number of leagues in the competition.
	 */
	public SoccerLeague getLeague(int leagueNum) throws CompetitionException {
		// TO DO
		if (leagueNum < 0 || leagueNum >= numLeagues) {
			throw new CompetitionException("No such league.");
		} else {
			return soccerLeagues.get(leagueNum);
		}
	}

	/**
	 * Starts a new soccer season for each league in the competition.
	 */
	public void startSeason() {
		// TO DO
		for (SoccerLeague soccerLeague : soccerLeagues) {
			try {
				soccerLeague.startNewSeason();
			} catch (LeagueException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}
	}

	/**
	 * Ends the season of each of the leagues in the competition. If there is
	 * more than one league then it handles promotion and relegation between the
	 * leagues.
	 * 
	 */
	public void endSeason() {
		// TO DO
		SoccerTeam tempLoser = null;
		SoccerTeam tempWinner = null;
		SoccerTeam currentWinner = null;
		SoccerTeam prevLoser = null;
		for (int i = 0; i < numLeagues; i++) {
			if (numLeagues > 1) {
				if (i == 0) {
					try {
						tempLoser = soccerLeagues.get(i).getBottomTeam();
						tempWinner = soccerLeagues.get(i+1).getTopTeam();
						prevLoser = soccerLeagues.get(i).getBottomTeam();
						soccerLeagues.get(i).endSeason();
						soccerLeagues.get(i).removeTeam(tempLoser);
						soccerLeagues.get(i).registerTeam(tempWinner);
					} catch (LeagueException e) {
						// TODO Auto-generated catch block
						e.getMessage();
					}
				} else if (i == (numLeagues - 1)) {
					try {
						currentWinner = soccerLeagues.get(i).getTopTeam();
						soccerLeagues.get(i).endSeason();
						soccerLeagues.get(i).removeTeam(currentWinner);
						soccerLeagues.get(i).registerTeam(prevLoser);
					} catch (LeagueException e) {
						// TODO Auto-generated catch block
						e.getMessage();
					}
				} else {
					try {
						currentWinner = soccerLeagues.get(i).getTopTeam();
						tempLoser = soccerLeagues.get(i).getBottomTeam();
						tempWinner = soccerLeagues.get(i+1).getTopTeam();
						soccerLeagues.get(i).endSeason();
						soccerLeagues.get(i).removeTeam(currentWinner);
						soccerLeagues.get(i).registerTeam(prevLoser);
						soccerLeagues.get(i).removeTeam(tempLoser);
						soccerLeagues.get(i).registerTeam(tempWinner);
						prevLoser = tempLoser;
					} catch (LeagueException e) {
						// TODO Auto-generated catch block
						e.getMessage();
					}
				}
						
			} else {
				try {
					soccerLeagues.get(i).endSeason();
				} catch (LeagueException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
			}
		}
	}

	/**
	 * For each league displays the competition standings.
	 */
	public void displayCompetitionStandings() {
		System.out.println("+++++" + this.name + "+++++");

		// TO DO (optional)
		// HINT The heading for each league is

		// System.out.println("---- League" + (i +1) + " ----");
		// System.out.println("Official Name" + '\t' + "Nick Name" + '\t' +
		// "Form" + '\t' + "Played" + '\t' + "Won" + '\t' + "Lost" + '\t' +
		// "Drawn" + '\t' + "For" + '\t' + "Against" + '\t' + "GlDiff" + '\t' +
		// "Points");
		for (int i = 0; i < soccerLeagues.size(); i++) {
			System.out.println("---- League" + (i + 1) + " ----");
			System.out.println("Official Name" + '\t' + "Nick Name" + '\t' + "Form" + '\t' + "Played" + '\t' + "Won"
					+ '\t' + "Lost" + '\t' + "Drawn" + '\t' + "For" + '\t' + "Against" + '\t' + "GlDiff" + '\t'
					+ "Points");
			soccerLeagues.get(i).displayLeagueTable();
		}
	}

}
