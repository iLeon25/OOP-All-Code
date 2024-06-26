package hr.fer.oop.ZI2024.zad3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class STEMGames {
	private List<Student> students = new LinkedList<Student>();
	
	
	public void addStudent(String n, int pe, int pm) {
		students.add(new Student(n, pe, pm));
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public int getTopEngStudentCount(int threshold) {
		int counter = 0;
		for (Student s : students) {
			if (s.getPointsEng() >= threshold) 
				counter++;
		}
		return counter;
	}
	
	public int getTopMathStudentCount(int threshold) {
		int counter = 0;
		for (Student s : students) {
			if (s.getPointsMath() >= threshold)
				counter++;
		}
		return counter;
	}
	
	public int getTopTotalStudentCount(int threshold) {
		int counter = 0;
		for (Student s : students) {
			if (s.getTotalPoints() >= threshold)
				counter++;
			
		}
		return counter;
	}
	
	public Map<String, List<String>> getTopStudents() {
		List<String> math = new LinkedList<>();
		List<String> eng = new LinkedList<>();
		List<String> total = new LinkedList<>();
		int mathThreshold = 95, engThreshold = 95, totalThreshold = 190;
		
		for (Student s : students) {
			if (s.getPointsEng() >= engThreshold) {
				eng.add(s.getName());
			}
			
			if (s.getPointsMath() >= mathThreshold) {
				math.add(s.getName());
			}
			if (s.getTotalPoints() >= totalThreshold) {
				total.add(s.getName());
			}
		}
		
		Map<String, List<String>> outputMap = new HashMap<String, List<String>>();
		outputMap.put("eng", eng);
		outputMap.put("math", math);
		outputMap.put("total", total);
		
		return outputMap;
	}
}
