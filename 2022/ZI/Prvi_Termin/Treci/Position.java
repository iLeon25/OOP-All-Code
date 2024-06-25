package hr.fer.oop.zi.zad3;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;

public class Position {
    private final String name; 
    private List<RequiredSkill> requiredSkills = new ArrayList<>();
    private List<Application> applications = new LinkedList<Application>();
    
    public Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Position addRequiredSkill(RequiredSkill skill) {
    	this.requiredSkills.add(skill);
        return this;
    }

    public void addApplication(Applicant applicant) {
        double score = 0;
        boolean flag = false;
        
        for (RequiredSkill rs : requiredSkills) {
        	score += (applicant.getRequiredSkillScore(rs.getName())) * rs.getMultiplier();
        	
        	if (applicant.getRequiredSkillScore(rs.getName()) < rs.getMinimum()) 
        		flag = true;
        }
        if (flag == false) {
        	 applications.add(new Application(applicant, score));
        } else {
        	 applications.add(new Application(applicant, 0));
        }
        
    }
    
    public Collection<Application> getApplications() {
        return applications;
    }

    public String summarizeApplications() {
        StringBuilder builder = new StringBuilder(this.name);
        builder.append(':');
        
        Collections.sort(applications, new Comparator<Application>() {

		@Override
		public int compare(Application o1, Application o2) {
			double r = 0;
			
			if (o2.getScore() == o1.getScore()) {
				r = o2.getApplicant().getId() - o1.getApplicant().getId();
			} else {
				r = o2.getScore() - o1.getScore();
			}
			
			if (r > 0) {
				return 1;
			} else if (r < 0) {
				return -1;
			}
			
			return 0;
		}
	});

        
        for (Application app : applications) {
        	if (app.getScore() != 0) {
        		builder.append(String.format("\n\t%s - %.2f", app.getApplicant().getName(), app.getScore()));
        	} else {
            	builder.append(String.format("\n\t%s - %s", app.getApplicant().getName(), "Did not meet requirements"));
        	}
        }
        // TODO: Iterate over applications and add lines for all of the
        // applications. Applications have to be ordered from the one with the greates score
        // to the one with the lowest.
        return builder.toString();
    }

}
