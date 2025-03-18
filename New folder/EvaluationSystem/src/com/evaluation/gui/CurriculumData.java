//package com.evaluation.gui;
//
//import java.util.HashMap;
//import java.util.ArrayList;
//
//public class CurriculumData {
//    private static HashMap<String, Subject> subjects = new HashMap<>();
//
//    public static void initializeCurriculum() {
//        // First Year, First Semester
//        addSubject("ENGL 100", "Communication Arts", 3.0, new ArrayList<>(), 1, 1);
//        addSubject("SOCIO 102", "Gender and Society", 3.0, new ArrayList<>(), 1, 1);
//        addSubject("MATH 100", "College Mathematics", 3.0, new ArrayList<>(), 1, 1);
//        addSubject("PSYCH 101", "Understanding the Self", 3.0, new ArrayList<>(), 1, 1);
//        addSubject("CC-INTCOM11", "Introduction to Computing", 3.0, new ArrayList<>(), 1, 1);
//        addSubject("CC-COMPROG11", "Computer Programming 1", 3.0, new ArrayList<>(), 1, 1);
//        addSubject("IT-WEBDEV11", "Web Design & Development", 3.0, new ArrayList<>(), 1, 1);
//        addSubject("PE 101", "Movement Competency Training (PATHFit 1)", 2.0, new ArrayList<>(), 1, 1);
//        addSubject("NSTP 101", "National Service Training Program 1", 3.0, new ArrayList<>(), 1, 1);
//        // First Year, Second Semester
//        ArrayList<String> englPrereq = new ArrayList<>();
//        englPrereq.add("ENGL 100");
//        addSubject("ENGL 101", "Purposive Communication", 3.0, englPrereq, 1, 2);
//
//        ArrayList<String> mathPrereq = new ArrayList<>();
//        mathPrereq.add("MATH 100");
//        addSubject("MATH 101", "Mathematics in the Modern World", 3.0, mathPrereq, 1, 2);
//
//        //ArrayList<String> progPrereq = new ArrayList<>();
//       // progPrereq.add("CC-COMPROG11");
//        //addSubject("CC-COMPROG12", "Computer Programming 2", 3.0, progPrereq, 1, 2);
//        addSubject("HIST 101", "Readings in Philippine History", 3.0, new ArrayList<>(), 1, 2);
//        addSubject("HUM 101", "Art Appreciation", 3.0, new ArrayList<>(), 1, 2);
//        // Add more subjects following the curriculum data...
//    
//        ArrayList<String> prog2Prereq = new ArrayList<>();
//        prog2Prereq.add("CC-COMPROG11");
//        addSubject("CC-COMPROG12", "Computer Programming 2", 3.0, prog2Prereq, 1, 2);
//        ArrayList<String> discretePrereq = new ArrayList<>();
//        discretePrereq.add("CC-INTCOM11");
//        addSubject("CC-DISCRET12", "Discrete Structures", 3.0, discretePrereq, 1, 2);
//        ArrayList<String> pe2Prereq = new ArrayList<>();
//        pe2Prereq.add("PE 101");
//        addSubject("PE 102", "Exercise-based Fitness Activities (PATHFit 2)", 2.0, pe2Prereq, 1, 2);
//        ArrayList<String> nstp2Prereq = new ArrayList<>();
//        nstp2Prereq.add("NSTP 101");
//        addSubject("NSTP 102", "National Service Training Program 2", 3.0, nstp2Prereq, 1, 2);
//        // Second Year First Semester
//        addSubject("SOCIO 101", "The Contemporary World", 3.0, new ArrayList<>(), 2, 1);
//        addSubject("RIZAL 101", "Life, Works & Writings of Dr. Jose Rizal", 3.0, new ArrayList<>(), 2, 1);
//        ArrayList<String> digilogPrereq = new ArrayList<>();
//        digilogPrereq.add("CC-DISCRET12");
//        addSubject("CC-DIGILOG21", "Digital Logic Design", 3.0, digilogPrereq, 2, 1);
//        ArrayList<String> ooprogPrereq = new ArrayList<>();
//        ooprogPrereq.add("CC-COMPROG12");
//        addSubject("IT-OOPROG21", "Object Oriented Programming", 3.0, ooprogPrereq, 2, 1);
//        ArrayList<String> platechPrereq = new ArrayList<>();
//        platechPrereq.add("CC-DIGILOG21");
//        addSubject("IT-PLATECH22", "Platform Technologies w/ Op. Sys.", 3.0, platechPrereq, 2, 1);
//        ArrayList<String> sadPrereq = new ArrayList<>();
//        sadPrereq.add("CC-COMPROG12");
//        addSubject("IT-SAD21", "System Analysis & Design", 3.0, sadPrereq, 2, 1);
//        ArrayList<String> acctgPrereq = new ArrayList<>();
//        acctgPrereq.add("MATH 101");
//        addSubject("CC-ACCTG21", "Accounting for IT", 3.0, acctgPrereq, 2, 1);
//        ArrayList<String> twritePrereq = new ArrayList<>();
//        twritePrereq.add("ENGL 101");
//        twritePrereq.add("CC-INTCOM11");
//        addSubject("CC-TWRITE21", "Technical Writing & Presentation Skills in IT", 3.0, twritePrereq, 2, 1);
//        ArrayList<String> pe3Prereq = new ArrayList<>();
//        pe3Prereq.add("PE 102");
//        addSubject("PE 103", "Sports and Dance (PATHFit 3)", 2.0, pe3Prereq, 2, 1);
//    }
//        
//
//    private static void addSubject(String code, String desc, double units, 
//                                 ArrayList<String> prereqs, int year, int sem) {
//        subjects.put(code, new Subject(code, desc, units, prereqs, year, sem));
//    }
//
//    public static Subject getSubject(String courseCode) {
//        return subjects.get(courseCode);
//    }
//
//    public static ArrayList<Subject> getSubjectsForYearAndSem(int year, int semester) {
//        ArrayList<Subject> result = new ArrayList<>();
//        for (Subject subject : subjects.values()) {
//            if (subject.getYearLevel() == year && subject.getSemester() == semester) {
//                result.add(subject);
//            }
//        }
//        return result;
//    }
//
//    public static boolean hasPrerequisites(String courseCode) {
//        Subject subject = subjects.get(courseCode);
//        return subject != null && !subject.getPrerequisites().isEmpty();
//    }
//
//    public static ArrayList<String> getPrerequisites(String courseCode) {
//        Subject subject = subjects.get(courseCode);
//        return subject != null ? subject.getPrerequisites() : new ArrayList<>();
//    }
//}

package com.evaluation.gui;

import java.util.HashMap;
import java.util.ArrayList;

public class CurriculumData {
    private static HashMap<String, Subject> subjects = new HashMap<>();

    public static void initializeCurriculum() {
        // First Year, First Semester
        addSubject("ENGL 100", "Communication Arts", 3.0, new ArrayList<>(), 1, 1);
        addSubject("SOCIO 102", "Gender and Society", 3.0, new ArrayList<>(), 1, 1);
        addSubject("MATH 100", "College Mathematics", 3.0, new ArrayList<>(), 1, 1);
        addSubject("PSYCH 101", "Understanding the Self", 3.0, new ArrayList<>(), 1, 1);
        addSubject("CC-INTCOM11", "Introduction to Computing", 3.0, new ArrayList<>(), 1, 1);
        addSubject("CC-COMPROG11", "Computer Programming 1", 3.0, new ArrayList<>(), 1, 1);
        addSubject("IT-WEBDEV11", "Web Design & Development", 3.0, new ArrayList<>(), 1, 1);
        addSubject("PE 101", "Movement Competency Training (PATHFit 1)", 2.0, new ArrayList<>(), 1, 1);
        addSubject("NSTP 101", "National Service Training Program 1", 3.0, new ArrayList<>(), 1, 1);

        // First Year, Second Semester
        ArrayList<String> englPrereq = new ArrayList<>();
        englPrereq.add("ENGL 100");
        addSubject("ENGL 101", "Purposive Communication", 3.0, englPrereq, 1, 2);

        ArrayList<String> mathPrereq = new ArrayList<>();
        mathPrereq.add("MATH 100");
        addSubject("MATH 101", "Mathematics in the Modern World", 3.0, mathPrereq, 1, 2);

        addSubject("HIST 101", "Readings in Philippine History", 3.0, new ArrayList<>(), 1, 2);
        addSubject("HUM 101", "Art Appreciation", 3.0, new ArrayList<>(), 1, 2);
    
        ArrayList<String> prog2Prereq = new ArrayList<>();
        prog2Prereq.add("CC-COMPROG11");
        addSubject("CC-COMPROG12", "Computer Programming 2", 3.0, prog2Prereq, 1, 2);

        ArrayList<String> discretePrereq = new ArrayList<>();
        discretePrereq.add("CC-INTCOM11");
        addSubject("CC-DISCRET12", "Discrete Structures", 3.0, discretePrereq, 1, 2);

        ArrayList<String> pe2Prereq = new ArrayList<>();
        pe2Prereq.add("PE 101");
        addSubject("PE 102", "Exercise-based Fitness Activities (PATHFit 2)", 2.0, pe2Prereq, 1, 2);

        ArrayList<String> nstp2Prereq = new ArrayList<>();
        nstp2Prereq.add("NSTP 101");
        addSubject("NSTP 102", "National Service Training Program 2", 3.0, nstp2Prereq, 1, 2);

        // Second Year, First Semester
        addSubject("SOCIO 101", "The Contemporary World", 3.0, new ArrayList<>(), 2, 1);
        addSubject("RIZAL 101", "Life, Works & Writings of Dr. Jose Rizal", 3.0, new ArrayList<>(), 2, 1);

        ArrayList<String> digilogPrereq = new ArrayList<>();
        digilogPrereq.add("CC-DISCRET12");
        addSubject("CC-DIGILOG21", "Digital Logic Design", 3.0, digilogPrereq, 2, 1);

        ArrayList<String> ooprogPrereq = new ArrayList<>();
        ooprogPrereq.add("CC-COMPROG12");
        addSubject("IT-OOPROG21", "Object Oriented Programming", 3.0, ooprogPrereq, 2, 1);

        ArrayList<String> platechPrereq = new ArrayList<>();
        platechPrereq.add("CC-DIGILOG21");
        addSubject("IT-PLATECH22", "Platform Technologies w/ Op. Sys.", 3.0, platechPrereq, 2, 1);

        ArrayList<String> sadPrereq = new ArrayList<>();
        sadPrereq.add("CC-COMPROG12");
        addSubject("IT-SAD21", "System Analysis & Design", 3.0, sadPrereq, 2, 1);

        ArrayList<String> acctgPrereq = new ArrayList<>();
        acctgPrereq.add("MATH 101");
        addSubject("CC-ACCTG21", "Accounting for IT", 3.0, acctgPrereq, 2, 1);

        ArrayList<String> twritePrereq = new ArrayList<>();
        twritePrereq.add("ENGL 101");
        twritePrereq.add("CC-INTCOM11");
        addSubject("CC-TWRITE21", "Technical Writing & Presentation Skills in IT", 3.0, twritePrereq, 2, 1);

        ArrayList<String> pe3Prereq = new ArrayList<>();
        pe3Prereq.add("PE 102");
        addSubject("PE 103", "Sports and Dance (PATHFit 3)", 2.0, pe3Prereq, 2, 1);

        // Second Year, Second Semester
        addSubject("STS 101", "Science, Technology & Society", 3.0, new ArrayList<>(), 2, 2);
        addSubject("PHILO 101", "Ethics", 3.0, new ArrayList<>(), 2, 2);

        ArrayList<String> discretPrereq = new ArrayList<>();
        discretPrereq.add("CC-INTCOM11");
        addSubject("CC-DISCRET12", "Discrete Structures", 3.0, discretPrereq, 2, 2);

        ArrayList<String> quantPrereq = new ArrayList<>();
        quantPrereq.add("CC-DISCRET12");
        addSubject("CC-QUAMETH22", "Quantitative Methods w/ Prob. Stat.", 3.0, quantPrereq, 2, 2);

        ArrayList<String> dataStructuresPrereq = new ArrayList<>();
        dataStructuresPrereq.add("IT-OOPROG21");
        addSubject("CC-DASTRUC22", "Data Structures & Algorithms", 3.0, dataStructuresPrereq, 2, 2);

        ArrayList<String> pe4Prereq = new ArrayList<>();
        pe4Prereq.add("PE 103");
        addSubject("PE 104", "Sports/Outdoor Adventure (PATHFit 4)", 2.0, pe4Prereq, 2, 2);

        // Third Year, First Semester
        addSubject("IT-IMDBSYS31", "Information Management (DB Sys. 1)", 3.0, new ArrayList<>(), 3, 1);
        addSubject("IT-NETWORK31", "Computer Networks", 3.0, new ArrayList<>(), 3, 1);
        addSubject("IT-TESTQUA31", "Testing & Quality Assurance", 3.0, new ArrayList<>(), 3, 1);

        addSubject("CC-HCI31", "Human Computer Interaction", 3.0, new ArrayList<>(), 3, 1);
        addSubject("CC-RESCOM31", "Methods of Research in Computing", 3.0, new ArrayList<>(), 3, 1);
        addSubject("IT-INFOSEC32", "Information Assurance & Security", 3.0, new ArrayList<>(), 3, 1);
    }
    
    private static void addSubject(String code, String desc, double units, 
                                 ArrayList<String> prereqs, int year, int sem) {
        subjects.put(code, new Subject(code, desc, units, prereqs, year, sem));
    }

    public static Subject getSubject(String courseCode) {
        return subjects.get(courseCode);
    }

    public static ArrayList<Subject> getSubjectsForYearAndSem(int year, int semester) {
        ArrayList<Subject> result = new ArrayList<>();
        for (Subject subject : subjects.values()) {
            if (subject.getYearLevel() == year && subject.getSemester() == semester) {
                result.add(subject);
            }
        }
        return result;
    }

    public static boolean hasPrerequisites(String courseCode) {
        Subject subject = subjects.get(courseCode);
        return subject != null && !subject.getPrerequisites().isEmpty();
    }

    public static ArrayList<String> getPrerequisites(String courseCode) {
        Subject subject = subjects.get(courseCode);
        return subject != null ? subject.getPrerequisites() : new ArrayList<>();
    }
}

