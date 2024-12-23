import java.io.*;
import java.util.*;

public class RiddleGame {
    public static void main(String[] args) {
        List<Riddle> riddles = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Load riddles from the text file
        try (BufferedReader reader = new BufferedReader(new FileReader("riddles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Riddle")) {
                    String question = line.split(": ")[1];
                    String hint = reader.readLine().split(": ")[1];
                    String options = reader.readLine().split(": ")[1];
                    String correctAnswer = reader.readLine().split(": ")[1];
                    riddles.add(new Riddle(question, hint, options, correctAnswer));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the riddles file: " + e.getMessage());
            return;
        }

        // Shuffle riddles and select 5 random ones
        Collections.shuffle(riddles);
        List<Riddle> selectedRiddles = riddles.subList(0, Math.min(5, riddles.size()));

        int score = 0;

        // Ask the selected riddles
        for (Riddle riddle : selectedRiddles) {
            System.out.println("\n" + riddle.getQuestion());

            // Ask if the user wants a hint
            System.out.print("Do you want a hint? (yes/no): ");
            String wantsHint = scanner.nextLine();
            if (wantsHint.equalsIgnoreCase("yes")) {
                System.out.println("Hint: " + riddle.getHint());
            }

            // Show options and get the user's answer
            System.out.println("Options: " + riddle.getOptions());
            System.out.print("Your answer: ");
            String userAnswer = scanner.nextLine().trim();

            // Extract only the letter from the correct answer for case-insensitive comparison
            String correctOption = riddle.getCorrectAnswer().substring(0, 1);

            // Check the answer (case-insensitive)
            if (userAnswer.equalsIgnoreCase(correctOption)) {
                System.out.println("Correct! Well done.");
                score++;
            } else {
                System.out.println("Wrong! The correct answer is: " + riddle.getCorrectAnswer());
            }
        }

        // Display the user's score
        System.out.println("\nGame Over! Your score: " + score + "/5");

        scanner.close();
    }
}

// Riddle class to store riddle details
class Riddle {
    private String question;
    private String hint;
    private String options;
    private String correctAnswer;

    public Riddle(String question, String hint, String options, String correctAnswer) {
        this.question = question;
        this.hint = hint;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getHint() {
        return hint;
    }

    public String getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}