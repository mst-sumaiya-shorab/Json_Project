import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuizManager {


    public void addQuestion() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        String filepath = "./src/main/resources/quiz.json";

        JSONParser jsonParser = new JSONParser();
        JSONArray quizArray = (JSONArray) jsonParser.parse(new FileReader(filepath));

        while (true) {

            JSONObject questionObject = new JSONObject();

            System.out.println("Input your question:");
            String question = scanner.nextLine();
            questionObject.put("question", question);

            System.out.println("Input option 1:");
            questionObject.put("option 1", scanner.nextLine());
            System.out.println("Input option 2:");
            questionObject.put("option 2", scanner.nextLine());
            System.out.println("Input option 3:");
            questionObject.put("option 3", scanner.nextLine());
            System.out.println("Input option 4:");
            questionObject.put("option 4", scanner.nextLine());

            System.out.println("What is the answer key? (1-4)");
            String answerKey = scanner.nextLine();
            questionObject.put("answerkey", Integer.parseInt(answerKey));

            quizArray.add(questionObject);
            System.out.println("Saved successfully!");

            System.out.println("Do you want to add more questions? (press s for start and q for quit)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            }
        }

        FileWriter fw = new FileWriter(filepath);
        fw.write(quizArray.toJSONString());
        fw.flush();
        fw.close();

        scanner.close();
    }
}
