package Interfaces;

/**
 * Interface that will be implemented by ChatScreen to be used by ChatManager so that a new ChatScreen UI can be
 * opened by ChatManager while maintaining clean architecture.
 *
 * @author Arian Khademi
 * @version 1.0
 * @since December 5th, 2022
 */
public interface ChatScreenInt {
    /**
     * Method to set the visibility of the ChatScreen GUI.
     *
     * @param setting the desired visibility setting of the GUI window.
     */
    void setVisible(boolean setting);
}