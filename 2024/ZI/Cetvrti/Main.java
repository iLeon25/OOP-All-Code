package hr.fer.oop.ZI2024.zad4;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<ChessPlayer> players = ChessPlayerData.loadChessPlayersTop();
		
		Stream<ChessPlayer> cpstream = ChessPlayer.getGoodChessPlayers(players, 2770);
		System.out.println("Chess players with rating over 2770:");
		cpstream.forEach(cp -> System.out.println(cp));

		double avgratingusa =  ChessPlayer.getAvgRating(players.stream(), Country.USA);
		double avgratinggermany =  ChessPlayer.getAvgRating(players.stream(), Country.GERMANY);

		System.out.printf("\nAverage rating in USA: %.2f", avgratingusa);
		System.out.printf("\nAverage rating in Germany: %.2f", avgratinggermany);

		//String filteredplayers = ChessPlayer.getFilteredPlayers(players.stream(), cp -> cp.country == Country.CHINA);
		//System.out.println("\n\nPlayers from China: " + filteredplayers);

		System.out.println("\nCountry map:");
		Map<Country, Integer> cplayers = ChessPlayer.getMaxRatingForCountry(players.stream());
		cplayers.forEach((k,v) -> System.out.println(k + ": " + v));
	}

}
