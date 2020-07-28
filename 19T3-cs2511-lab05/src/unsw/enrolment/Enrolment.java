package unsw.enrolment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Enrolment {

    private CourseOffering offering;
    private Student student;
    private List<Session> sessions;

    private MarkCalculated marks;

    public Enrolment(CourseOffering offering, Student student,
            Session... sessions) {
        this.offering = offering;
        this.student = student;
        student.addEnrolment(this);
        offering.addEnrolment(this);
        this.sessions = new ArrayList<>();
        for (Session session : sessions) {
            this.sessions.add(session);
        }
        this.marks = new MarkCalculated("course", new SumMarks());
    }

    public Course getCourse() {
        return offering.getCourse();
    }

    public String getTerm() {
        return offering.getTerm();
    }

    public boolean hasPassed() {
        return getGrade() != null && getGrade().isPassing();
    }

    private Grade getGrade() {
        return new Grade(marks.getMark());
    }

    /**
     * Manually assign a mark for a given assessment. This method assumes that
     * already manual marks can be updated but that calculated marks can't be
     * converted into manual ones.
     *
     * @param assessment
     * @param mark
     * @param max
     */
    public void assignMark(String assessment, int mark, int max) {
        if (!marks.updateMark(assessment, mark, max)) {
            MarkManual newMark = new MarkManual(assessment, mark, max);
            logChanges(newMark);
            marks.addSubmark(newMark);
        }
    }

    private void logChanges(MarkManual mark) {
        mark.registerObserver(new Observer() {
            @Override
            public void update(Subject subject) {
                String filename = offering.getCourse().getCourseCode() + "-" +
                        offering.getTerm() + "-" + student.getZID();
                try {
                    File file = new File(filename);
                    PrintStream out = new PrintStream(new FileOutputStream(file, true));
                    out.println(LocalDateTime.now() + " -- " +
                            mark.getAssessment() + " = " + mark.getMark() +
                            "/" + mark.getMaximum());
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Add a calculated mark based on the average of the given submarks. This
     * method assumes a calculated mark for that assessment doesn't already
     * exist.
     *
     * @param assessment
     * @param submarks
     */
    public void addAverageMark(String assessment, String... submarks) {
        addMarkCalculated(assessment, new SumMarks(), submarks);
    }

    /**
     * Add a calculated mark based on the sum of the given submarks. This
     * method assumes a calculated mark for that assessment doesn't already
     * exist.
     *
     * @param assessment
     * @param submarks
     */
    public void addSumMark(String assessment, String... submarks) {
        addMarkCalculated(assessment, new SumMarks(), submarks);
    }

    private void addMarkCalculated(String assessment, SumMarks calculator,
            String... submarks) {
        MarkCalculated newMark = new MarkCalculated(assessment, calculator);
        for (String submark : submarks) {
            Mark mark = marks.removeMark(submark);
            if (mark != null)
                newMark.addSubmark(mark);
        }
        marks.addSubmark(newMark);
    }

    // Whole course marks can no longer be assigned this way.
    // public void assignMark(int mark) {
    // grade = new Grade(mark);
    // }

}
