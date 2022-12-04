package useCases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import entities.User;


public class Report {
    public User user1;
    public User user2;

    public Report(User mainUser, User matchedUser) {
        this.user1 = mainUser;
        this.user2 = matchedUser;
    }

    public ArrayList<String> readFiles() {
        ArrayList<String> list_of_messages = new ArrayList<String>();
        String s = "src/" + this.user1.getUsername() + this.user2.getUsername() + ".txt";
        String s1 = "src/" + this.user2.getUsername() + this.user1.getUsername() + ".txt";
        File f = new File(s);
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    list_of_messages.add(data);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            try {
                File file = new File(s1);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String data = sc.nextLine();
                    list_of_messages.add(data);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return list_of_messages;
    }

    /* Helper method to convert the hatewords in keywords.txt (file downloaded from a repository online) as well and
    some generic hatewords into a List<String> so it can itterated through in the report checkreport function. */
    public ArrayList<String> hateWords() throws FileNotFoundException {
        ArrayList<String> hate_words = new ArrayList<String>();
        File f = new File("keywords.txt");
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()){
            String data = sc.nextLine();
            if (!data.startsWith("#")) {
                hate_words.add(data);
            }
        }
        String[] array = {"fuck", "shit", "bitch", "pussy", "cunt", "dick", "twat", "cock", "cuck"};
        hate_words.addAll(Arrays.asList(array));
        return hate_words;
    }

    /* Helper method to remove all weird characters that can be sued to replace alphabets such as using  a dollar sign
    in place of s */
    public String removeWeirdCharacters(String s){
        s = s.replace("$", "s");
        s = s.replace("4", "a");
        s = s.replace("3", "e");
        s = s.replace("1", "i");
        s = s.replace(" ", "");
        return s;
    }

    /* This helper method is used to convert a string to a list of its individual characters */
    public ArrayList<String> convertToListOfStrings(String s){
        ArrayList<String> chars = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            chars.add(Character.toString(ch));
        }
        return chars;
    }

    /* Helper method to check if any of the messages sent by the reported user is offensive or not. This is done by
    going through the string of messages and checking is any offensive word is used */
    public boolean checkOffensive(String s) throws FileNotFoundException {
        ArrayList<String> hate_words = hateWords();
        String s1 = removeWeirdCharacters(s);
        String s2 = s1.toLowerCase();
        ArrayList<String> message = convertToListOfStrings(s2);
        for (String cuss_word : hate_words) {
            ArrayList<String> cuss_word_array = convertToListOfStrings(cuss_word);
            int length_of_cuss_word = cuss_word_array.size();
            int count = 0;
            for (String value : message) {
                if (Objects.equals(cuss_word_array.get(count), value)) {
                    count = count + 1;
                    if (count == length_of_cuss_word) {
                        return true;
                    }
                }
                else {
                    count = 0;
                }
            }
        }
        return false;
    }

    /* Helper method to check is the user who is reported has less than 3 strikes. If the user has 3 strikes then
    user is banned */
    public boolean checkBan(User user1){
        int number_stikes = user1.getNum_strikes();
        return number_stikes == 3;

    }

    /* The main method in Report class that is called in ChatUI to check is the report is valid or not and take a
    suitable action. First the messages file is read and then messages sent by matched user is checked to see if it is
     offensive or not. If any message is ofensive then strike is added to the matched user, he/she is blocked and
     removed as friend and if there are more than 3 strikes then matched user is removed from graph (banned).*/
    public void checkReport() throws IOException, ClassNotFoundException {
        ArrayList<String> all_messages = readFiles();
        int index = all_messages.size() - 1;
        while (index >= 0) {
            String s = all_messages.get(index);
            String[] arr = s.split(":", 2);
            String name = arr[0];
            if (Objects.equals(name, this.user2.getUsername())) {
                boolean bool = checkOffensive(s);
                if (bool) {
                    AccountManager manager = new AccountManager();
                    FriendRemover remover = new FriendRemover();
                    remover.remove(this.user1, this.user2);
                    manager.blockUser(this.user1, this.user2);
                    this.user2.addStrike();  //strike added to user 2 for vulgar language
                    boolean is_ban = checkBan(this.user2);
                    if (is_ban){
                        // user2 is banned//
                        manager.removeUser(user2);
                    }
                    break;
                }
            }
            index = index - 1;
        }

    }
}
