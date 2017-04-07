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

	//default
	String name;
	//added variables
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
		this.numLeagues = numLeagues;
		soccerLeagues = new LinkedList<SoccerLeague>();
		for (int league = 0; league < numLeagues; league++) {
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
		for (SoccerLeague soccerLeague : soccerLeagues) {
			try {
				soccerLeague.startNewSeason();
			} catch (LeagueException e) {
				System.out.println(e.getMessage());
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
		//initialize temporary objects to hold the winner and loser teams
		SoccerTeam tempLoser = null;
		SoccerTeam tempWinner = null;
		SoccerTeam currentWinner = null;
		SoccerTeam prevLoser = null;
		for (int league = 0; league < numLeagues; league++) {
			if (numLeagues > 1) {
				if (league == 0) {
					try {
						tempLoser = soccerLeagues.get(league).getBottomTeam();
						tempWinner = soccerLeagues.get(league+1).getTopTeam();
						prevLoser = soccerLeagues.get(league).getBottomTeam();
						soccerLeagues.get(league).endSeason();
						soccerLeagues.get(league).removeTeam(tempLoser);
						soccerLeagues.get(league).registerTeam(tempWinner);
					} catch (LeagueException e) {
						System.out.println(e.getMessage());
					}
				} else if (league == (numLeagues - 1)) {
					try {
						currentWinner = soccerLeagues.get(league).getTopTeam();
						soccerLeagues.get(league).endSeason();
						soccerLeagues.get(league).removeTeam(currentWinner);
						soccerLeagues.get(league).registerTeam(prevLoser);
					} catch (LeagueException e) {
						System.out.println(e.getMessage());
					}
				} else {
					try {
						currentWinner = soccerLeagues.get(league).getTopTeam();
						tempLoser = soccerLeagues.get(league).getBottomTeam();
						tempWinner = soccerLeagues.get(league+1).getTopTeam();
						soccerLeagues.get(league).endSeason();
						soccerLeagues.get(league).removeTeam(currentWinner);
						soccerLeagues.get(league).registerTeam(prevLoser);
						soccerLeagues.get(league).removeTeam(tempLoser);
						soccerLeagues.get(league).registerTeam(tempWinner);
						prevLoser = tempLoser;
					} catch (LeagueException e) {
						System.out.println(e.getMessage());
					}
				}
						
			} else {
				try {
					soccerLeagues.get(league).endSeason();
				} catch (LeagueException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * For each league displays the competition standings.
	 */
	public void displayCompetitionStandings() {
		System.out.println("+++++" + this.name + "+++++");
		for (int league = 0; league < soccerLeagues.size(); league++) {
			System.out.println("---- League" + (league + 1) + " ----");
			System.out.println("Official Name" + '\t' + "Nick Name" + '\t' + "Form" + '\t' + "Played" + '\t'
					+ "Won" + '\t' + "Lost" + '\t' + "Drawn" + '\t' + "For" + '\t' + "Against" + '\t' + "GlDiff"
					+ '\t' + "Points");
			soccerLeagues.get(league).displayLeagueTable();
		}
	}

}
