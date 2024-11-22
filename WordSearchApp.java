import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordSearchApp {

    public static void main(String[] args) {

        String path = "D:\\Example\\Example";
        String textName = "Example.txt";
        String searchWord = "Example";

        File file = new File(path, textName);

        if (!file.exists()) { // Gives an error message if the path is not suitable and the text name is wrong
            System.out.println("File not found: " + file.getAbsolutePath());
            return;
        }

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));


            String line;
            int sentenceNumber = 1; // Renamed to sentenceNumber for clarity
            boolean wordFound = false;

            while ((line = reader.readLine()) != null) { // This line was the cause of the infinite loop

                String[] sentences = line.split("[.!?]"); // Regular expression should be enclosed in quotes

                for (String sentence : sentences) {

                    if (sentence.trim().toLowerCase().contains(searchWord.toLowerCase())) { // trim() ensures that the search is not affected by unnecessary spaces

                        System.out.println("Sentence " + sentenceNumber + ": " + sentence.trim());
                        sentenceNumber++;
                        wordFound = true;
                    }
                }
            }

            reader.close(); // Close the reader after use to prevent resource leak

            if (!wordFound) {
                System.out.println("Searched word \"" + searchWord + "\" not found in text.");
            }

        } catch (IOException e) { // Throws an exception if the file cannot be accessed due to security ,permission issues or another reason

            System.out.println("An error occurred while accessing the file: " + e.getMessage());
        }
    }
}