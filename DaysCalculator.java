import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class DaysCalculator {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.print("Enter the first date (dd-MM-yyyy): ");
        String firstDateInput = scanner.nextLine();
        LocalDate firstDate = LocalDate.parse(firstDateInput, formatter);   
        System.out.print("Enter the second date (dd-MM-yyyy): ");
        String secondDateInput = scanner.nextLine();
        LocalDate secondDate = LocalDate.parse(secondDateInput, formatter);
        long daysBetween = ChronoUnit.DAYS.between(firstDate, secondDate);
        System.out.println("Number of days between the two dates: " + Math.abs(daysBetween));
        
    }
}
