package example;

/**
 * Average example
 * @author Aarthi
 */
public class Average {

        /**
         * Returns the average of an array of numbers
         * @param the array of integer numbers
         * @return the average of the numbers
         */
        public float average(int[] nums) {
            float result = 0;
            // Add your code
            for (int i=0; i<nums.length; i++) {
            	result += nums[i];
            }
            result = result/nums.length;
            return result;
        }

        public static void main(String[] args) {
            // Add your code
        	int [] nums = {1,2,3,4};
        	Average a = new Average();
        	float result = a.average(nums);
        	System.out.print(result);
        }
}
