# Project Description and Information

#### Note
Merging issues so we had to push again that's why commit message is just try again.

## Description of app and how to use
Social media chatting app that allows users to randomly chat with someone in their contact list. Users can add and remove friends by username. They can chat with their friends to gain points based on the length of their conversation. They can also earn points by playing Connect 4.

### How to use the app <br/>
Go to the LoginPageMain file under src/main. Run the class LoginPageMain with user1 and user2 to test having an synchronous chat. Once done you will be prompted with a sign in / sign up screen where you can create a user with any username and password. Click sign up and then once shown the message on the GUI click login. From here you will reach the welcome page. There is a textbox at the bottom with an add friend button. Remove button next to add can be used for removing friends. Friends button is viewing friends which you don’t have an existing chat with and active chats is for viewing the active chats. Recommend friend recommends the friend with the highest mutual and recommend random recommends a random friend among the friends of the chosen friend. Match button gets you match with a friend start chat button starts the chat and skip button skips the matched user and gives a new match. GameMain class can also be used to test the game manually.

## List of features

### Mert - Game Feature

The game that we implemented is a Connect 4 game. In order to play the game from the user interface, first create an account and login. You will be directed to the welcome page. In order to play the game, first you should have a friend that you added among the other existing users. After you have friends in your friends list, you should press match to get matched with one of your friends. Then, press Start Chat and the chat user interface will be displaced to you. At the top of the chat user interface, there is a button called Game. Press that button to start the game. The game will continue between the two users until it ends. When one of the users win, the points of that user will be incremented by 15.

There are 4 main classes for the game: GameManager (Use case), MoveTracker (Use case), GameController (Controller), GameUI (UI)

GameManager class contains the check method which checks whether there is a winning move in the board so far. It takes an arraylist as an input an checks both X and O to see if one of them has a winning move. If there is a winning move, then the location of the winning move is returned with the player that won (X or O).

GameController calls the GameManager class’s check method and gets the returned value. Based on the returned value the the locations on the board that contains the winning move is changed to green to show the winning move and name of the winner player is displaced at the top of the screen. Moreover, GameController class calls the Points System Class (use case) to update the points of the winning user.

GameUI calls the GameController which follows the clean architecture flow. The game UI is created with 42 buttons, one for each grid using GridLayout. Game UI also uses MoveTracker which converts the moves on the UI to an arraylist so the check method in GameManager can check the winning moves. Other UI that were accessed before the game are LoginPage, WelcomePage, and FriendsPage. I worked on the UI design of these classes and collaborated with Arian, Ashvat and Brandon for connecting the Chat, AccountManager and FriendAdder and Remover to these UIs.

For storing the users I worked on Serialization of the data by working on ReadGraph and WriteGraph classes by collaborating with Ashvat.

The tests were written for all the win cases for X and O in the GameManagerTests. MoveTrackerTests test whether the arraylist representing the board was updated after X or O makes a move. GameController test tests whether the textfield at the to of the frame is updated after each move and when a player wins.


### Brandon - Friend System
The friend system involves the friendAdder, friendRemover, and friendRecommender use cases as well as the respective tests, controllers and friendFacade. The system also included the respective controllers like the addFriendController that interact with the UI and call upon the correct use cases and other packages. The friendAdder class interacts with the acccountManager use case and the user and graph entities, allowing a user to add friends. There is a lot of emphasis on utilizing the graph which uses a dictionary that maps username strings to users. The friendAdder class adds a selected user to the friends ArrayList in the user entity as well as adds an edge between users in the graph entity. Likewise, the friendRemover class removes users from the friends ArrayList and removes edges from the graph. The friendRecommender class provides two ways for users to get new friends. This class can randomly select 1 friend from one of the current user's friends and return this list of users as a recommendation. The class can also recommend a user based on a graph-based algorithm such that the most common friend amongst your current friends is recommended to you. This is done by searching the user's neighbors or friends in other words in the graph and then looking through their friends and counting the most common or interconnected one and returning this recommendation.
The friendAdderTest, friendRecommenderTest, and friendRemoverTest creates a set of users and tests their respective functions through asserting the correct users have been added or recommended as well as checking if the correct amount of users have been added.


### Ashvat - AccountManager

The AccountManager contains the addUser, removeUser, addFriend, removeFriend methods that are used by the FriendAdder, FriendRemover, UserCreator, UserRemover use cases. The accountmanager uses a static graph which stores all the users in the form of a hashmap that maps the user’s username to the User object. This graph is kept static to ensure that all uses of the accountmanager in various other use cases, refer to the same graph. We update the graph in the Graph.ser file each time a change is made to it i.e. each time a user is added or removed, a friend is added or removed, we update the graph in the file. The methods in the accountmanager handle edge cases as well, such as logging into an account which does not exist.
The FriendAdder and FriendRemover are 2 use cases that are responsible for adding and removing friends of the users respectively.
The 2 entities are the Graph and the User. The User contains the respective attributes such as username, password and a list of friends. The graph contains a hashmap that maps the username to the User, as mentioned earlier.
The database package contains the ReadGraph and WriteGraph classes that are responsible for reading the graph from the ser file and writing a graph into the ser file, respectively.

The FriendFacade is created to implement the facade design pattern. The facade class contains the add friend and remove friend methods of the accountmanager and the facade is called whenever these methods need to be implemented


### Manit - Report Button
The report feature helps make the chatting app a place of mutual respect and ensures standards are met in terms of language used and keeping it a friendly safe place where nobody should be offended. The report button in the chat allows user’s to report to the Admin if they believe the opposite person has been offensive towards them in some sort. The chat is then sent to an algorithm to help identify hatespeech used.

There is a report controller also added in between the Report Use Case and the Chat UI to follow clean architecture and created tests to check if the controller is also working as we want.

There are functions created within the Chat Class (this was done in collaboration with Arian) to store the messages within a txt file. In order to keep unique files for each chat in the system we defined a method to keep the file name a concatenation of the two usernames and when reading from files check the order of the concatenation of the file name to not have duplicate files for the same chat. The methods to read chat is crucial as the chats get updated simultaneously as two users are chatting.

The report algorithm is broken down into parts which are defined by helper function. Each helper function has a definitive task and you can read about it in more depth in the java docstring in the Report Class. Essentially the report class aims to not just cover the cases for if a particular word is used, but also cover test cases of using the word within words, using character replacement to saya  particular word etc.

<u> Testing the Report Class (ReportTest): </u> <br/>
The report tests include all the methods in Report Class while also some methods and features from other use cases. For example attributes from User Class, blockUser, removeFriend, removeUser from AccountManager, features of graph and the FriendRemover class. There are functions in accountManager and users which I worked on that which the Report Class was solely dependent on.

### Arian - Chat System

<u> ChatManager: </u> <br/>
The chat management file is used to help match the user to either a random user or a preselected user, depending on what method is called. Initially the randomMatch method is called when the user clicks the match button on WelcomePage, and the matched user is printed to console (and in the future submission will also be displayed as a pop up or label to the user so they can actually see the user they are matched with). However, if a user is not satisfied with the user they are matched with they can click the skip button on WelcomePage which matches them with a new user. This is accomplished by calling the skipMatch method which uses a while loop to keep randomly matching the user to a new user until the new user is not the previously selected user. Additionally a user can select a friend they want to speak with from their friends page and this will use the choseMatch method which, as the name suggests, sets the matched user to the user that had been chosen. Since there are a lot of functions which can change the user that somebody gets matched with, and the WelcomePage also needs to know what the matched user is, there is also a getter which returns who the matched user is. Finally, there is a button on WelcomePage for the user to start a chat once they are satisfied with the user they have been matched with. This button calls the openChat method of ChatManager, which is a method that starts off by searching to check whether there is an existing file that has the conversation between the two users or if there needs to be a new file created (which was done in collaboration with Manit). Once there is for certain a file (either new or old) containing the conversation between the two users, it calls the ChatScreen file to create a new chat between the two users, and display the GUI for the chat. The WelcomePage and ChatManager link was done in collaboration with Mert as I created ChatManager and Mert incorporated the ChatManager in the WelcomePage’s code. Additionally, Manit and Ashvat assisted with debugging some errors we came across when linking these two files together.

<br/>
UPDATE: There have now been changes to improve clean architecture. There is now a controller for ChatManager called ChatManagerController which is what is used by WelcomePage and FriendsPage to achieve the same effects as previously desired (matching the user with either a random user, a new user if they choose to skip their previous match, or a chosen user if they want to choose the user they want to talk to. I have also created a test file for ChatManagerController to ensure that the controller’s methods are currently achieving the desired results from the inputs. There have also been two new interfaces added: CreatChatInt, and ChatScreenInt. Since ChatManager is in the use case layer and it wants to access CreateChatText which is in the database layer, there is an interface for CreateChatText to implement and for ChatManager to refer to so that it can create a text file for the chat between the users. Additionally, since ChatManager is a use case and ChatScreen is a GUI, there is also an interface for ChatScreen to implement which ChatManager refers to so that a ChatScreen can be created and opened once the user is satisfied with who they are matched with and want to start a chat with them. Documentation for these files have also been updated and any unnecessary comments or commented out code has been removed to improve clarity and organization.
<br/>

<br/>
<u> ChatScreen: </u> <br/>
The chat system allows two users to interact with one another and uses a timer to display messages to each user in real time. The chat also incorporates Manit’s report system through a report button, as well as Mert’s game system through a game button. When the report button has been clicked and the report system has been tripped, the chat between the two users is ended. In the chat there is also a send button as well as a key listener which checks if the enter key has been pressed so that the user can either click send or press enter and message the user they are matched with. The ChatScreen then uses the addToTextFile method which adds the message that the user had written in the text field to the file containing the conversation between the two users. Manit helped with the chat system by aiding to create the addToTextFile, and readFromTextFile methods as well as the code to delete the text file in the endChat method. The display panel which shows the messages previously sent in the chat to each user is a text area with a scroll pane and is constantly updated (using the timer) by reading from the text file and appending to the text area. After the 20 message limit has been reached or a report has been created endChat is called which stops the timer that is used to update the display, then creates a new Event object as well as PointSystemR object and calls execute on the event object with the PointSystemR object as a parameter (Malhar created the PointSystem and Event classes). The Event and PointSystemR objects are responsible for updating the points of the users in the chat as well as the event log. Afterwards, the chat file is deleted and the chat window is closed. The two classes testUser1 and testUser2 exist to create a conversation between two users “Manit” and “Arian” where each test has either of the users as their main user and the other as the matched user. These two test classes can be run simultaneously and can demonstrate the chat system by mimicking a conversation between two users
<br/>

<br/>
UPDATE: ChatScreen now uses the ReportController instead of referring to Report directly. This helps with clean architecture as ChatScreen is a GUI and Report is a use case, and therefore ChatScreen should not be accessing Report directly. Other than this, slight changes have been made to the code to improve organization and clarity. For example, all commented out code and TODO’s have been deleted as well as any unnecessary documentation. There has also been a new method added into the file to set the visibility of the GUI. Other than small changes, ChatScreen mostly works the same way still.
<br/>

### Malhar - PointSystem and Event Class/EventLog
The PointSystem is responsible for the gamification of networking behaviour, relying on both Arian's Chat Manager and Mert's Game feature. In a nutshell, a user can earn points in two ways (for now): maintaining a chat streak and winning a game. They can spend points in three ways: skip an assigned chat, extend the current chat, or choose a person to chat with. Each of these cases have rewards/costs associated with them that are factored into a given User's points at any given time. The PointSystem handles point manipulation by accessing the User graph and computing on each User's Points attribute.

<u> Event Class/EventLog: </u> <br/> 
Each time a behavior occurs that involves any manipulation of points (spending/renewing), an Event object is created with the specifics of the occurrence, such as the User(s) involved and what kind of occurrence it is. A PointSystem object is passed in to the .execute() method of the Event; execution hands the baton to PointSystem's adjustPoints method, which effects the necessary changes to a User's points. 
The EventLog was conceptualized as an afterthought to the Event class for debugging and testing purposes. If the code jammed somewhere, the Event Log would stop at the last executed Event object, making it easier to identify the Event that caused the disruption. As such, it isn't actively utilized elsewhere in the app framework, and exists only for development purposes.

<u> PointSystem: </u> <br/> 
PointSystem is an abstract class that consists of two concrete subclasses: PointSystemR (for renewing/earning points) and PointSystemS (for spending points). It contains an abstract adjustPoints method. Both of the subclasses implement this method in different ways to facilitate addition/deduction of points. 
Each time a PointSystemR object is created, its constructor loads in the different possible cases for renewal, and the points associated with each of them (in a dictionary called earnCases). The same goes for PointSystemS as well (with spendCases).
Creating an inheritance hierarchy for PointSystem allowed Event.execute() to be modelled as polymorphic method, thereby eliminating bloated code for the different point manipulation cases.


#### Github Features
<ul>
<li>Github actions autotesting in used in the classroom workflow</li>

<li>Pull requests and branching</li>
<ul><li>Used for code organization, pull requests, merging into main, and etc
</ul>
<li>Issues</li> 
<ul><li>Used to keep track of features, known bugs, and next steps</li></ul>
</ul>


#### Testing
<ul>
<li>At least 80% testing coverage for most layers unless further specified</li>
<li>Testing UI not necessary</li>
</ul>


#### Clean architecture, SOLID, and use of design patterns
<ul>
<li>Properly using dependency layers of clean architecture</li>
<li>Abstraction and inheritance</li>
<ul>
<li>PointSystem is an abstract class with two subclasses PointSystemR (for earning/renewing points) and PointSystemS (for spending points). Both of the subclasses manipulate points in different ways.</li>
<li>Utilized inheritance to implement an adjustPoints interface</li>
</ul>
<li>Packaging</li>
<ul>
<li>Done by clean architecture layers</li>
</ul>
<li>Serialization</li>
<ul>
<li>Used to store user objects and graph in the .ser file</li>
</ul>
<li>SOLID</li>
<ul>
<li>Single responsibility - Implemented by every class</li>
<li>Open and Closed - Seen when classes use abstraction or inheritance. For example, the PointsSystem.</li>
<li>Liskov Substitution - Use of inheritance in PointSystem as well as general use of large potential parent class in the User allow for easy substitution if needed. We can easily pass in new types of users as parameters if needed.</li>
<li>Dependency Inversion - Found in game features and Interfaces added between ChatScreen and ChatManager called ChatScreenInt and CreateChatInt to fix dependency issue</li>
</ul>
<li>Design Patterns</li>
<ul>
<li>Graph pattern - Implemented in the AccountManager use case. Stores all the users.</li>
<li>Factory Pattern - Implemented in the UserFactory class that the AccountManager class calls. It is responsible for creating users.</li>
<li>Facade Pattern - Implemented in FriendFacade. Since multiple actors can access the add or remove friend use cases (ReportManager and AccountManager), a facade is implemented to keep access to these functions private.</li>
<li>Dependency Injection</li>
<ul>
<li>Used in PointSystem so that point storage and manipulation is  independent of persistence mechanisms and gateways </li>
<li>Implemented in FriendRecommender, FriendAdder, FriendRemover so that the most general user class is used and can easily be modified for subclasses of users</li>
</ul>
</ul>
</ul>
