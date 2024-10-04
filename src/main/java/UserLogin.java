import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserLogin {

    public static void main(String[] args) throws IOException, ParseException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username:  ");
        String username = scanner.nextLine();
        System.out.println("Enter your password:  ");
        String password = scanner.nextLine();
        String role = getUserRole(username, password);

        if (role != null) {
            System.out.println("Login successful! You are logged in as " + role + ".");
            if (role.equals("admin")) {
                System.out.println("Welcome admin! Please create new questions in the question bank.");
                // Call QuizManager to add questions
                QuizManager quizManager = new QuizManager();
                quizManager.addQuestion();

            } else if (role.equals("student")) {

                System.out.println("Welcome to quiz!\nEach MCQ mark is 1 and no negative marking. \nAre you ready? Press 's' for start.");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("s")) {
                    QuizTaker quizTaker = new QuizTaker();
                    quizTaker.startQuiz();
                }

            }
        } else {

            System.out.println("Invalid username or password. Please try again.");
        }
        scanner.close();
    }

    public static String getUserRole(String username, String password) throws IOException, ParseException {
        String filepath = "./src/main/resources/users.json";
        try {
            JSONParser parser = new JSONParser();
            JSONArray userArray = (JSONArray) parser.parse(new FileReader(filepath));

            for (Object obj : userArray) {
                JSONObject user = (JSONObject) obj;
                String Username = user.get("username").toString();
                String Password = user.get("password").toString();
                String role = user.get("role").toString();
                if (Username.equals(username) && Password.equals(password)) {
                    return role;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

