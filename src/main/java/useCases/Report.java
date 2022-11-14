package useCases;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import entities.User;


public class Report {

    public User account_checking;

    public List<String> hatewords(){
        List<String> hate_words = new ArrayList<String>();
        try {
            File file = new File("hate_keywords.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String data = sc.nextLine();
                hate_words.add(data);
            }
        } catch (FileNotFoundException error){
            error.printStackTrace();
        }
        String[] array = {"fuck", "shit", "bitch", "pussy", "cunt", "dick", "twat", "cock", "cuck"};
        for(String lang:array) {
            hate_words.add(lang);
        }
        return hate_words;
    }

    public String removeWeirdCharacters(String s){
        s.replace("$", "s");
        s.replace("4", "a");
        s.replace("3", "e");
        s.replace("1", "i");
        return s;
    }

    public List<String> ConvertToLostOfStrings(String s){
        String[] array1 = s.split("");
        List<String> message = new ArrayList<String>(Arrays.asList(array1));
        return message;
    }

    public boolean checkOffensive(String s){
        List<String> hate_words = hatewords();
        String s1 = removeWeirdCharacters(s);
        String s2 = s1.toLowerCase();
        List<String> message = ConvertToLostOfStrings(s2);
        int length = hate_words.size();
        for (int index = 0; index < length; index++){
            String cuss_word = hate_words.get(index);
            List<String> cuss_word_array = ConvertToLostOfStrings(cuss_word);
            int length_of_cuss_word = cuss_word_array.size();
            int count = 0;
            for (int j_index = 0; j_index < message.size(); j_index++){
                if (Objects.equals(cuss_word_array.get(count), message.get(j_index))){
                    count = count + 1;
                    if (count == length_of_cuss_word){
                        return true;
                    }
                count = 0;
                }
            }
        }
        return false;
    }

    public void CheckReport(String filename){
        ReadFile file = new ReadFile(filename);
        ArrayList<String> all_messages = file.ReadFiles();
        int index = all_messages.size() - 1;
        while (index >= 0) {
            String s = all_messages.get(index);
            String arr[] = s.split(":", 2);
            String name = arr[0];
            if (Objects.equals(name, account_checking.getUsername())) {
                boolean is_ban = checkOffensive(s);
                if (is_ban) {
                    /// The user should now be completely booted of platform
                    ///
                    break;
                }
            }
            index = index - 1;
        }

    }
}
