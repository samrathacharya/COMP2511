package example;
import java.util.Scanner;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
        // Add your code
        Scanner s = new Scanner(System.in); 
        String l = s.nextLine();
        String [] splitLine = l.split(" ");
        for (int i=0; i<splitLine.length; i++) {
        	System.out.println(splitLine[i]);
        }
    }
}
