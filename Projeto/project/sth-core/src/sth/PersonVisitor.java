package sth;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class PersonVisitor{
    String showPerson(Person p){
        return p.getId() + "|" + p.getPhoneNumber() + "|" +  p.getName();
    }

    String showStudent(Student student){
        String s = ((Person)student).accept(this);
		ArrayList<String> disciplines = new ArrayList<String>();
		for (int i=0; i < student.getDisciplinesNumber() ; i++){
            Discipline d = student.getDiscipline(i);
            disciplines.add(d.toString());
		}
		Collections.sort(disciplines, Collator.getInstance());
		for (String d : disciplines) //disciplines is already sorted
			s+= "\n* " + student.getCourse().toString() + " - " + d;
		return s; 

    }

    String showProfessor(Professor professor){
        String s = "DOCENTE|" + ((Person)professor).accept(this);
		
		ArrayList<String> disciplines = new ArrayList<String>();
		for (Map.Entry<String, ArrayList<Discipline>> entry: professor.getDisciplines().entrySet()) {
			for (Discipline discipline: entry.getValue()) {
				disciplines.add("* " + entry.getKey() + " - " + discipline.getDisciplineName());
			}
		}
		Collections.sort(disciplines, Collator.getInstance());
		for (String str: disciplines) {
			s += "\n" + str;
		}
		return s;

    }
    String showAdministrative(Administrative administrative){
        return "FUNCIONÁRIO|"+  ((Person)administrative).accept(this); 
        
    }
}