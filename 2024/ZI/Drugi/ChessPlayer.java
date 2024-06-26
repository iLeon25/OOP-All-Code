package hr.fer.oop.ZI2024.zad2;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

public class ChessPlayer implements Comparable<ChessPlayer>{
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

	@Override
	public int hashCode() {
		return Objects.hash(firstname, lastname, rating);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChessPlayer other = (ChessPlayer) obj;
		return Objects.equals(firstname, other.firstname) && Objects.equals(lastname, other.lastname)
				&& rating == other.rating;
	}
	
	
	public final static Comparator<Object> hrComparator = Collator.getInstance(Locale.forLanguageTag("hr"));
	public final static Comparator<ChessPlayer> BY_LASTNAME = (p1, p2) -> hrComparator.compare(p1.getLastname(), p2.getLastname());
	public final static Comparator<ChessPlayer> BY_FIRSTNAME = (p1, p2) -> p1.getFirstname().compareTo(p2.getFirstname());
	public final static Comparator<ChessPlayer> BY_RATING = (p1, p2) -> Integer.compare(p1.getRating(), p2.getRating());
	

	@Override
	public int compareTo(ChessPlayer o) {
		return BY_LASTNAME.thenComparing(BY_FIRSTNAME).thenComparing(BY_RATING.reversed()).compare(this, o);
	}
	
	
}
