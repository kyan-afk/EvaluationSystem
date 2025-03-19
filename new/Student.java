import java.util.HashMap;
import java.util.ArrayList;

public class Student {
    private String name;
    private String idNumber;
    private String program;
    private int yearLevel;
    private int semester;
    private String email;
    private String password;
    private HashMap<String, Double> grades;
    private ArrayList<String> enrolledSubjects;

    public Student(String name, String idNumber, String program, int yearLevel, 
                  int semester, String email, String password) {
        this.name = name;
        this.idNumber = idNumber;
        this.program = program;
        this.yearLevel = yearLevel;
        this.semester = semester;
        this.email = email;
        this.password = password;
        this.grades = new HashMap<>();
        this.enrolledSubjects = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getIdNumber() { return idNumber; }
    public String getProgram() { return program; }
    public int getYearLevel() { return yearLevel; }
    public int getSemester() { return semester; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public void addGrade(String courseCode, double grade) {
        grades.put(courseCode, grade);
        if (!enrolledSubjects.contains(courseCode)) {
            enrolledSubjects.add(courseCode);
        }
    }

    public Double getGrade(String courseCode) {
        return grades.get(courseCode);
    }

    public void enrollSubject(String courseCode) {
        if (!enrolledSubjects.contains(courseCode)) {
            enrolledSubjects.add(courseCode);
        }
    }

    public void removeSubject(String courseCode) {
        enrolledSubjects.remove(courseCode);
        grades.remove(courseCode);
    }

    public ArrayList<String> getEnrolledSubjects() {
        return new ArrayList<>(enrolledSubjects);
    }

    public boolean hasPassedSubject(String courseCode) {
        Double grade = grades.get(courseCode);
        return grade != null && grade >= 1.0 && grade <= 3.0;
    }

    public boolean hasFailedSubject(String courseCode) {
        Double grade = grades.get(courseCode);
        return grade != null && grade > 3.0 && grade <= 5.0;
    }

    public HashMap<String, Double> getAllGrades() {
        return new HashMap<>(grades);
    }

    public int getNextSemester() {
        return semester == 1 ? 2 : 1;
    }

    public int getNextYearLevel() {
        return semester == 2 ? yearLevel + 1 : yearLevel;
    }
}
