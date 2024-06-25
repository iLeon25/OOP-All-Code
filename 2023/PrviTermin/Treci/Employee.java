package hr.fer.oop;

import java.util.Objects;

public class Employee {
	private String firstName, lastName;
	private int proposedInnovations, implementedInnovations;
	
	
	public Employee(String firstName, String lastName, int proposedInnovations, int implementedInnovations) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.proposedInnovations = proposedInnovations;
		this.implementedInnovations = implementedInnovations;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getProposedInnovations() {
		return proposedInnovations;
	}

	public void setProposedInnovations(int proposedInnovations) {
		this.proposedInnovations = proposedInnovations;
	}

	public int getImplementedInnovations() {
		return implementedInnovations;
	}

	public void setImplementedInnovations(int implementedInnovations) {
		this.implementedInnovations = implementedInnovations;
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
		Employee other = (Employee) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}


	@Override
	public String toString() {
		return firstName + " " + lastName + " [proposed: "
				+ proposedInnovations + ", implemented: " + implementedInnovations + "]";
	}
}
