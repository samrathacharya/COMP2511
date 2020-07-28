package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

/**
 * A mark that is entered in manually.
 *
 * @author Robert Clifton-Everest
 *
 */
public class MarkManual implements Mark, Subject {

    private String assessment;
    private int mark;

    private int maximum;

    private List<Observer> observers;

    /**
     * Create a ManualMark.
     * @param assessment
     * @param mark
     * @param maximum
     */
    public MarkManual(String assessment, int mark, int maximum) {
        this.assessment = assessment;
        this.mark = mark;
        this.maximum = maximum;
        observers = new ArrayList<>();
    }

    @Override
    public String getAssessment() {
        return assessment;
    }

    @Override
    public int getMark() {
        return mark;
    }

    @Override
    public int getMaximum() {
        return maximum;
    }

    @Override
    public boolean updateMark(String assessment, int mark, int max) {
        if (this.assessment.equals(assessment)) {
            this.mark = mark;
            this.maximum = max;
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Mark removeMark(String assessment) {
        return null;
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers)
            o.update(this);
    }

}
