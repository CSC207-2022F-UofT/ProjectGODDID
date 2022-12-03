package Interfaces;

import entities.User;

import java.io.IOException;

// To be accessed by the welcome page when finding matches and opening a chat
// TODO: documentation
public interface ChatManagerInt {
    void randomMatch();
    void skipMatch(User otherUser);
    void choseMatch(User otherUser);
    User getMatchedUser();
    void openChat() throws IOException;
}
