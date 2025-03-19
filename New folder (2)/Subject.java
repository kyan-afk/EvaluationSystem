

import java.util.ArrayList;

public class Subject {
    private String courseCode;
    private String description;
    private double units;
    private ArrayList<String> prerequisites;
    private int yearLevel;
    private int semester;

    public Subject(String courseCode, String description, double units, 
                  ArrayList<String> prerequisites, int yearLevel, int semester) {
        this.courseCode = courseCode;
        this.description = description;
        this.units = units;
        this.prerequisites = prerequisites;
        this.yearLevel = yearLevel;
        this.semester = semester;
    }

    // Getters
    public String getCourseCode() { return courseCode; }
    public String getDescription() { return description; }
    public double getUnits() { return units; }
    public ArrayList<String> getPrerequisites() { return prerequisites; }
    public int getYearLevel() { return yearLevel; }
    public int getSemester() { return semester; }

    @Override
    public String toString() {
        return courseCode + " - " + description;
    }
}
