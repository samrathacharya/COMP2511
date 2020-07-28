package unsw.enrolment;

import java.util.ArrayList;
import java.util.List;

public class MarkCalculated implements Mark {

    private String assessment;
    private List<Mark> subjectMarks;

    private CalculatorHelper calculator;

    /**
     * Create a calculated mark for the given assessment and calculation
     * strategy.
     */
    public MarkCalculated(String assessment, CalculatorHelper calculator) {
        this.assessment = assessment;
        this.calculator = calculator;
        this.subjectMarks = new ArrayList<>();
    }
    
    //Remoce mark for assessment
    @Override
    public Mark removeMark(String assessment) {
        Mark removedMark = null;
        for (Mark marks : subjectMarks) {
        	removedMark = marks.removeMark(assessment);
            if (removedMark != null)
                break;
            if (marks.getAssessment().equals(assessment)) {
            	removedMark = marks;
                subjectMarks.remove(removedMark);
                break;
            }
        }
        return removedMark;
    }

    //Update mark for ass
    @Override
    public boolean updateMark(String assessment, int mark, int max) {
        for (Mark marks : subjectMarks)
            if (marks.updateMark(assessment, mark, max))
                return true;
        return false;
    }
    

    
    //GETTERS

    @Override
    public String getAssessment() {
        return assessment;
    }

    @Override
    public int getMark() {
        return calculator.calculateMark(subjectMarks);
    }

    @Override
    public int getMaximum() {
        return calculator.calculateMaximum(subjectMarks);
    }

    public void addSubmark(Mark mark) {
        this.subjectMarks.add(mark);
    }


}
