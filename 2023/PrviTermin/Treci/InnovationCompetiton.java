package hr.fer.oop;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;

public class InnovationCompetition {
	private List<Employee> employees = new LinkedList<Employee>();
	
	public void addEmployee (String fn, String ln, int pi, int ii) {
		employees.add(new Employee(fn, ln, pi, ii));
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void removeEmployees(List<String> removalNames) {
		
		employees.removeIf((a) -> removalNames.contains(a.getLastName()));
    //Ili alternativno:
//		Iterator<Employee> it = employees.iterator();
//		
//		while (it.hasNext()) {
//			Employee e = it.next();
//			
//			if (removalNames.contains(e.getLastName())) {
//				it.remove();
//			}
//		}
	}
	
	public static Comparator<Employee> implementedInnovationsComparator = (a, b) -> b.getImplementedInnovations() - a.getImplementedInnovations();
	public static Comparator<Employee> proposedInnovationsComparator = (a, b) -> b.getProposedInnovations() - a.getProposedInnovations();
	public static Comparator<Employee> byLastNameComparator = (a, b) -> a.getLastName().compareTo(b.getLastName());
	
	public void rankEmployees () {
		class Comparing implements Comparator<Employee> {

			@Override
			public int compare(Employee o1, Employee o2) {
				int r = implementedInnovationsComparator.thenComparing(proposedInnovationsComparator)
						.thenComparing(byLastNameComparator).compare(o1, o2);
				return r;
			}
			
		}
		Collections.sort(employees, new Comparing());
	}
}
