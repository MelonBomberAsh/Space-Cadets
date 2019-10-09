import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SCChallengeEmail {

//    Returns all user input until the next new line using a Scanner() on System.in
    private static String getInput() {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a person's username: ");
        String input = keyboard.nextLine();
        keyboard.close();
        return input;
    }

//    Builds and returns a string containing each line of HTML from the input String URL using a BufferedReader()
    private static String getSource(String inputURL) throws IOException {
        URL url = new URL(inputURL);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

//    Uses a regular expression to search the HTML source of the concatenated URL for the name of the person with an input username
    public static void main(String[] args) throws IOException {
        String username = getInput();
        String pageSource = getSource("https://www.ecs.soton.ac.uk/people/" + username);
        Pattern searchPattern = Pattern.compile("property=\"name\">(.*?)<");
        Matcher matches = searchPattern.matcher(pageSource);
        if (matches.find()) {
            System.out.println(matches.group(1));
        } else {
            System.out.println("No matches found");
        }
    }
}
