package hr.fer.oop.ZI2024.zad4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessPlayer {
	private String firstname, lastname;
	private Country country;
	private int rating, birthyear;
	
	public ChessPlayer(String firstname, String lastname, Country country, int rating, int birthyear) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.country = country;
		this.rating = rating;
		this.birthyear = birthyear;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public Country getCountry() {
		return country;
	}
	public int getRating() {
		return rating;
	}
	public int getBirthyear() {
		return birthyear;
	}

	public String toString() {
		return String.format("(%s) %s %s %d: %d", country, firstname, lastname, birthyear, rating);
	}

	public static Stream<ChessPlayer> getGoodChessPlayers(List<ChessPlayer> lplayers, int ratingthreshold) {
		return lplayers.stream().filter((p) -> p.getRating() > ratingthreshold).sorted((p1, p2) -> p1.getBirthyear() - p2.getBirthyear());
		
	}
	
	public static double getAvgRating(Stream<ChessPlayer> cpstream, Country country) {
		
		List<ChessPlayer> fromCountry = cpstream.filter((p) -> p.getCountry() == country).collect(Collectors.toList());
		int zbroj = 0, counter = 0;
		
		if (fromCountry.size() == 0) {
			return 0.;
		}
		
		for (ChessPlayer cs : fromCountry) {
			zbroj += cs.getRating();
			counter++;
			
		}
		
		return (double)zbroj / counter;
	}
	
	public static String getFilteredPlayers(Stream<ChessPlayer> cpstream, Predicate<ChessPlayer> filter) {
		List<ChessPlayer> list = cpstream.filter(filter).collect(Collectors.toList());
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < list.size(); i++) {
			if (i == (list.size() - 1)) {
				sb.append(String.format("%s, %s", list.get(i).getLastname(), list.get(i).getFirstname()));
				
			} else {
				sb.append(String.format("%s, %s; ", list.get(i).getLastname(), list.get(i).getFirstname()));

			}
		}
		
		
		return sb.toString();
	}
	
	public static Map<Country, Integer> getMaxRatingForCountry(Stream<ChessPlayer> cpstream) {
		Map<Country, Integer> returnMap = new HashMap<Country, Integer>();
		List<ChessPlayer> list = cpstream.toList();
		
		for (ChessPlayer chessPlayer : list) {
			returnMap.compute(chessPlayer.country, (key, value) -> {
												if (value == null) {
													value = chessPlayer.getRating();
												} else {
													if (value < chessPlayer.getRating()) {
														value = chessPlayer.getRating();
													}
 												}
												return value;
			});
		}
		
		return returnMap;
	}

}
