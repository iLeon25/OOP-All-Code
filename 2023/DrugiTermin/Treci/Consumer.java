package hr.fer.oop;

import java.util.Objects;

public class Consumer {
	private String firstName, lastName;
	private int rewardPoints, membershipYears;
	
	public Consumer(String firstName, String lastName, int rewardPoints, int membershipPoints) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.rewardPoints = rewardPoints;
		this.membershipYears = membershipPoints;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public int getMembershipYears() {
		return membershipYears;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " [points: " + rewardPoints
				+ ", years: " + membershipYears + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumer other = (Consumer) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}
	
}
