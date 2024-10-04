import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizTaker {

    public void takeQuiz() throws IOException, ParseException {
        String filepath = "./src/main/resources/quiz.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray quizArray = (JSONArray) jsonParser.parse(new FileReader(filepath));

        Scanner scanner = new Scanner(System.in);
        int score = 0;

        List<JSONObject> questions = (List<JSONObject>) (List<?>) quizArray;
        Collections.shuffle(questions);

        int totalQuestions = quizArray.size();

        for (int i = 0; i < totalQuestions; i++) {

            JSONObject questionObject = questions.get(i);

            System.out.println("[Question " + (i + 1) + "]: " + questionObject.get("question"));
            System.out.println("1. " + questionObject.get("option 1"));
            System.out.println("2. " + questionObject.get("option 2"));
            System.out.println("3. " + questionObject.get("option 3"));
            System.out.println("4. " + questionObject.get("option 4"));

            System.out.print("Your answer: ");
            String answer = scanner.nextLine();

            String correctAnswer = questionObject.get("answerkey").toString();

            if (answer.equals(correctAnswer)) {
                score++;
            }
            System.out.println();

        }

        System.out.println("Quiz complete! Your score is: " + score + "/" + totalQuestions);

        if (score >= totalQuestions * 0.8) {
            System.out.println("Excellent! You have got " + score + " out of " + totalQuestions);
        } else if (score >= totalQuestions * 0.5) {
            System.out.println("Good! You have got " + score + " out of " + totalQuestions);
        } else if (score >= totalQuestions * 0.2) {
            System.out.println("Very poor! You have got " + score + " out of " + totalQuestions);
        } else {
            System.out.println("Very Sorry, you have failed. You have got " + score + " out of " + totalQuestions);
        }

    }

    public void startQuiz() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            takeQuiz();

            System.out.println("Would you like to start again? Press 's' for start or 'q' for quit:");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                System.out.println("Thank you for participating!");
                break;
            } else if (!choice.equalsIgnoreCase("s")) {
                System.out.println("Start Again.");
            }
        }
        scanner.close();
    }

}

