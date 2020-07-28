/**
 *
 */
package unsw.enrolment;

import java.util.List;

public class SumMarks implements CalculatorHelper {

    @Override
    public int calculateMark(List<Mark> marks) {
        int sum = 0;
        for (Mark mark : marks)
            sum += mark.getMark();
        return sum;
    }

    @Override
    public int calculateMaximum(List<Mark> marks) {
        int sum = 0;
        for (Mark mark : marks)
            sum += mark.getMaximum();
        return sum;
    }

}
