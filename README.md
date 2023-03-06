# StarGamesApp
## Introduction:
This document presents the documentation for the final project, which involves developing a mobile game application for Android using Android Studio and the Java programming language. The project includes two games: 2048 and LightsOut, and allows users to view scores obtained in both modes, which are stored in a SQLite database.

In addition, to enhance the visual experience of the application, artificial intelligence has been used to create most of the images used in the games. Music and sounds obtained from the website freesound.org have also been incorporated, all under the Creative Commons license.

The games in the application are based on two classic games, 2048 and LightsOut. The game 2048 was created by Gabriele Cirulli in 2014 and involves moving number blocks on a 4x4 board, merging them to create higher value blocks and trying to reach the 2048 tile. The game LightsOut, on the other hand, was created by Tiger Electronics in 1995 and involves a board of lights that turn on and off when pressed, with the goal of turning off all the lights.

In addition, the LightsOut game includes a feature that shows hints for solving the board, which when activated, highlights in green all the squares that need to be pressed to solve the puzzle.

The application has a scoring system for each game, in the LightsOut game, the score is saved once the puzzle is completed and consists of the time taken to solve the puzzle, difficulty level, and whether hints were used or not. In the 2048 game, the score is saved whether you win or lose and consists of the total score achieved during the game, with each merge of squares adding to the score.

If no player name is specified when saving the score, the score is automatically saved under the name "Player".




# Documentation:
## General purpose classes:
### SplashActivity class:
The SplashActivity class is responsible for displaying a welcome screen with an animation while the application is loading. The animation is defined in the splash_animation.xml file and is loaded using the loadAnimation method of the AnimationUtils class. In addition, the screen orientation is set to portrait mode using the setRequestedOrientation method so as not to disrupt the animation. Finally, an AnimationListener is used to start the Menu activity once the animation is complete, and the current activity is finished so that the user cannot return to it.

### Menu class:
The Menu class is responsible for managing the main screen of the application, which presents options to play the 2048 game, the LightsOut game, and view scores. It also shows an animation of an alien flying over a space-themed background and plays background music.

In the onCreate method, the buttons are initialized and animations and images are loaded. The background music is also played and the animation on the alien image is repeated periodically. The buttons are configured so that clicking on them plays a sound and opens the corresponding activity.

The onResume, onPause, and onDestroy methods are responsible for managing the playback state of the background music.
















## 2048 Game Classes:

### Class Casilla2048:

The class "Casilla2048" represents an individual cell in the game 2048. It has a single attribute "valorCasilla", which represents the current value of the cell. The cell is initialized to zero when an instance of the class is created. The class has a constructor that initializes "valorCasilla" to zero, and two getter and setter methods to get and modify the value of the cell, respectively.

### Class Tablero2048:

The class "Tablero2048" is a class that represents the game board of the game 2048. The board is created as a matrix of "Casilla2048", which represent the cells of the board, and is initialized with all empty cells (with value 0). This class has methods to move the cells in four directions (up, down, left, and right), add a new random cell to the board, check if the user has lost the game (if all cells are filled and there are no more possible moves), check if the user has won the game (if any cell has a value of 2048), and get the user's current score.

In the "up()" method, the board is traversed from the second row to the last, from the first column to the last, and for each non-empty cell, an empty cell is searched in the same column above it. If an empty cell is found, the current cell is moved to that empty cell and a cell with the same value is searched in the previous cells in the same column. If a cell with the same value is found, they are merged and the score is added. If a cell with the same value is not found or the top edge of the board is reached, the search for that cell stops.

The "down()", "left()", and "right()" methods work similarly, but with the difference that the board is traversed in different directions (down, left, or right) and empty or same-value cells are searched in different positions in the same row or column.

The "addRandomCell()" method randomly generates the coordinates of an empty cell on the board and assigns a randomly selected value of 2 or 4 to it. If there are no empty cells, the method does nothing.

The "lost()" method checks if all cells on the board are filled and there are no more possible moves. If so, the game is marked as over and true is returned.

The "won()" method checks if any cell on the board has a value of 2048. If so, true is returned.

The "getScore()" method returns the user's current score.
### Class Controlador2048:

The Controlador2048 class is responsible for controlling the game logic and interaction with the graphical interface. To do this, it uses an object of the Tablero2048 class and a matrix of ImageView objects to represent the game cells in the view.

The class constructor takes the ImageView matrix as input and creates a Tablero2048 object of the corresponding size. Next, it adds a random cell to the board and updates the view.

The move() method is responsible for processing the user's movement, which is specified as a string indicating the direction ("up", "down", "left" or "right"). Depending on the direction, it calls the corresponding method in the Tablero2048 object.

The updateView() method updates the view to reflect the current state of the game. It loops through the ImageView matrix and sets the corresponding image based on the value of the corresponding cell in the Tablero2048 object.

The hasWon() and hasLost() methods verify if the player has won or lost the game, respectively, and return a boolean value indicating the result.

The addRandomCell() method adds a new random cell to the board.

Finally, the getPuntuacion() method returns the current score of the player.


### Class Juego2048:

The Juego2048 class is the activity that represents the 2048 game. The game consists of a 4x4 board with pieces with numbers that can be moved in four directions. When two pieces with the same number are joined, they are added and form a new piece with twice the value.

The class has as attributes several elements of the game's graphical interface, such as ImageView to represent the pieces on the board, Toast to show pop-up messages, and TextView to show the score. It also has a Controlador2048 object to handle the game logic, a DataBase to save the players' scores, and several MediaPlayer objects to play sounds and background music.

In the onCreate() method, the class's attributes are initialized and the game interface is established. An animation is created and applied to two buttons. MediaPlayer objects are created from sound files in wav format, and one of them is set to play in a loop as background music. A Controlador2048 object is created and associated with the ImageView elements of the interface. The reestablecerPuntuacion() method is called to set the score to zero, and an OnSwipeTouchListener is set on the ConstraintLayout containing the ImageView elements. This OnSwipeTouchListener allows detecting the user's movements on the touch screen and calling the Controlador2048's realizarMovimiento() method.

The realizarMovimiento() method receives a string as an argument that indicates the direction of the movement (up, down, left, or right) and calls the Controlador2048's move() method to update the position of the pieces. Then a movement sound is played, a new piece is added to the board, the interface is updated, and the comprobarWinLose() method is called to verify if the player has won or lost.

The comprobarWinLose() method verifies if the player has won or lost the game using the Controlador2048's hasGanado() and hasPerdido() methods. If the player has won, a Toast pop-up message is shown, a victory sound is played, and an AlertDialog.Builder dialog box is created that allows the player to enter their name to save their score in the database. If the player has lost, a Toast pop-up message is shown, a defeat sound is played, and a dialog box similar to the previous one is created to save the player's score.

The onClick() method is called when a button on the interface is pressed. Depending on the button pressed, the Controlador2048 method is called to start a new game or another activity is changed using Intent.

The reestablecerPuntuacion(), onResume(), onPause(), and onDestroy() methods are activity lifecycle methods that are responsible for resetting the score, playing or pausing background music, and releasing resources used by MediaPlayer objects. The reestablecerPuntuacion() method simply sets the score to zero. The onResume(), onPause(), and onDestroy() methods are responsible for playing, pausing, and stopping background music, respectively, using the mediaPlayerSong MediaPlayer object. In the onDestroy() method, the mediaPlayerSong object is also released from memory.


### Class OnSwipeTouchListener:

The OnSwipeTouchListener class is a helper class that detects swipe gestures on the application screen. This class implements the OnTouchListener interface so that it can be registered as a touch listener on a view of the application.

In the constructor, a new GestureDetector object is created and it is specified to use a new instance of the GestureListener class that implements the necessary methods to detect swipe gestures.

The onTouch method is responsible for receiving touch events and delegating the event to the gestureDetector object for processing.

The inner class GestureListener is an implementation of the SimpleOnGestureListener class, which is a helper base class that implements the GestureDetector.OnGestureListener and GestureDetector.OnDoubleTapListener interfaces. In this implementation, the necessary methods to detect swipe gestures are overridden.

The onSwipeRight, onSwipeLeft, onSwipeTop, and onSwipeBottom methods are empty methods that must be implemented by the class that uses this helper class to perform an action in response to a swipe gesture in a specific direction.

### Layout Juego2048:

This layout is the visual representation of a user interface for the game. It is designed using a ConstraintLayout, which allows setting constraints for elements within the screen and achieving proper distribution of them.

The screen is divided into three main sections: the top section, the board section, and the bottom section.

In the top section, there is a LinearLayout that contains an image with the game logo. The LinearLayout has a specific width constraint (379dp) and adjusts to the size of the parent ConstraintLayout in terms of height.

In the board section, there is a GridLayout that represents the game board. The GridLayout has a constraint to be below the top LinearLayout and above the bottom LinearLayout. The board has a colorful background and is divided into 16 equally sized cells, each of which is represented by an ImageView with an empty image. The ImageViews corresponding to the board cells have specific constraints to be located properly within the GridLayout.

In the bottom section, there is another LinearLayout that contains two buttons: the "returnButton" button that allows returning to the previous screen and the "newGame" button that starts a new game. Both buttons have stylized background and text and are spaced using an invisible space.









## LightsOut Game classes:

### Class CasillaLightsOut:

The CasillaLightsOut class represents a cell in the Lights Out game. The class has two private properties, estaEncendida and esPista, which indicate whether the cell is lit and whether it is a hint, respectively.

The constructor of the class initializes both properties as false, meaning that the cell is off and not a hint. The class has two methods, cambiarEstado() and cambiarEstadoPista(), which change the state of the cell and the hint, respectively.

The method estaEncendida() returns the current value of the estaEncendida property, and the method esPista() returns the current value of the esPista property. The setEncendida(boolean on) and setPista(boolean pista) methods modify the values of the estaEncendida and esPista properties, respectively.

### Class TableroLightsOut:

The TableroLightsOut class represents the game board. The constructor of the class receives two parameters that define the height and width of the board and calls the crearTablero method to initialize the board with all lights off.

The class also has several methods for performing actions on the board. The click method receives the coordinates of a cell and changes its state, as well as the adjacent cells to the selected one. The ganar method checks if all cells are off to determine if the game has been won. The lucesAleatorias method turns on two random lights on the board and turns on a random number of additional lights, until it is ensured that the board is solvable. To do this, it uses the esTableroResoluble method that checks if the board has a solution and returns a boolean.

To do this, the esTableroResoluble method first creates an integer matrix that represents the current board, where each cell of the board is represented with a 1 if it is lit and with a 0 if it is off.

Next, the number of inversions in the matrix is ​​calculated. An inversion occurs when a larger number is in a position before a smaller number. To calculate the number of inversions, the matrix is ​​traversed from left to right, top to bottom, and each number is compared to all the numbers that come after it. If a larger number appears after a smaller number, it is counted as an inversion.

Then, the parity of the rows and columns of the matrix is ​​verified. If a row or column has an odd number of ones, it is said to have "odd parity." If all rows and columns have even parity, then the board is solvable.

Finally, if the number of inversions in the matrix is ​​even and both the rows and columns have even parity, then true is returned, indicating that the board is solvable. If any of these conditions are not met, then false is returned, indicating that the board is not solvable.

In addition to this, the class has methods to access the properties of the board, such as the state of a cell or the number of rows and columns. There are also methods to update the view of the board, such as the actualizarPistas method, which changes the state of the hints of the cells that have been modified, and the dejarDeMostrarPistas method, which deactivates the hints in all cells of the board.




### ControladorLightsOut Class:

The ControladorLightsOut  class is responsible for handling the game logic in the user interface. It manages the game board and updates the application view according to user actions.

The constructor of the class receives as parameters a matrix of ImageButton and an instance of the LightsOut class, which represents the game. In the constructor, an instance of the TableroLightsOut class is created, which represents the game board, with the dimensions of the button matrix received as a parameter. In addition, random lights are generated on the board and the view is updated with the call to the updateView() method.

The click() method is responsible for handling user clicks on the buttons. If the game has not ended, it calls the click() method of the TableroLightsOut class to change the state of the light at the indicated position. If the hint display mode is enabled, it also changes the state of the hint at the indicated position.

The mostrarPistas() method is responsible for activating or deactivating the hint display mode. If it is activated, it calls the dejarDeMostrarPistas() method of the TableroLightsOut class to deactivate the hints and updates the view. If it is deactivated, it activates the hint mode and updates the view.

The updateView() method updates the application view with the current state of the game board. It iterates through the button matrix, updating the image and visibility of each button according to the state of the light and hints at the corresponding position on the board.

The ganar() method is responsible for verifying if the player has won the game by calling the ganar() method of the TableroLightsOut class.



### Class LightsOut:

The "LightsOut" class is the activity that represents the "Lights Out" game, where the objective is to turn off all the lights on a board. The class contains variables to store game information, such as the buttons on the board, the game controller, the difficulty level, the elapsed time, and the score database. There are also variables to handle game sound and to control the game state, such as whether the game has ended or if a hint has been used.

In the "onCreate" method, the activity is configured depending on the chosen difficulty level. For level 1, the "lightsout3x3.xml" view is loaded, the buttons are initialized, and the game controller is created. A timer is also started to keep track of elapsed time, and background music is played. For levels 2 and 3, the same logic is applied, but for 4x4 and 5x5 boards respectively.

The "onClick" method handles events when a button on the board or a UI button (such as the "new game" or "hint" button) is clicked. Depending on the difficulty level, the corresponding actions for the pressed button are executed. If the game is won, a dialog is displayed to allow the player to save their score to the database.

Additionally, the class includes methods to start a new game, start the timer, get elapsed time, reset elapsed time, play sounds, and get and set difficulty level. Methods are also included to control background music playback when the activity is paused or destroyed.

### Class LightsOutMenu:

This class is the menu activity of the "Lights Out" game. When the application is opened, a screen with three buttons representing different difficulty levels (3x3, 4x4, and 5x5) is displayed. Clicking on one of the buttons starts the game with the corresponding difficulty level.

In the onCreate method, the buttons are initialized and an animation is added to them. The alien image is also initialized with an animation, and background music is played. Additionally, a timer is set up to restart the image animation every 10 seconds.

Each button has a listener that calls an intent to start the LightsOut game activity. A Bundle object is created that is used to pass information about the selected difficulty level to the game activity. A button sound effect is played when each of the buttons is clicked.

The class also has three lifecycle methods that handle pausing, resuming, and destroying background music when the activity is paused, resumed, or destroyed, respectively.


### Layouts 3x3, 4x4, and 5x5 for the LightsOut class:

These XML layout files define the structure and appearance of the "LightsOut" activity interface. A ConstraintLayout is used to position elements on the screen based on the constraints set between them.

The layout consists of several elements, including LinearLayouts, TextViews, ImageButtons, and Buttons. The LinearLayout is a container that organizes elements in a row or column. TextViews are used to display text on the screen, such as labels and timer. ImageButtons are used to represent the lights that can be turned on and off during the game. Buttons are used for navigation buttons such as "New game" and "Hints".

At the top of the layout, there is a LinearLayout containing a background image and a game header image (lightsout). Below this is a LinearLayout containing two TextViews displaying the timer label and current game time. Underneath this is the main game square, which is a LinearLayout with several ImageButtons arranged in a grid. The ImageButtons represent each of the game lights that can be turned on and off.

At the bottom of the layout, there is a LinearLayout containing three Buttons: "Back", "Hints", and "New game". These buttons are used for application navigation and game control.

















## Score classes:

### DataBase class:

The DataBase class is a helper class for working with a SQLite database.

The class has several constants, including the name of the database, the names of the tables, and the columns to be used in the database.

In the class constructor, the parent class SQLiteOpenHelper's constructor is called with the database name and database version passed to it.

The onCreate() method is used to create the database tables. This method is automatically called by the system when the database is first created.

The class also has several methods for working with the database, such as newScore2048() and newScoreLightsOut(), which are used to insert new scores into the corresponding database table.

It also has query2048() and queryLightsOut() methods, which are used to query scores for the corresponding games through a SQL query.

Finally, the class has the count() method, which is used to count the number of records in a given table.


### AdaptadorPuntuaciones2048 Class:

This class is an adapter for displaying the scores of the 2048 game in a list with a RecyclerView structure. The adapter is responsible for creating the view for each item in the list and assigning the corresponding data to each view.

The AdaptadorPuntuaciones2048 class extends the RecyclerView.Adapter class and uses the ScoreViewHolder class, which is also defined within the AdaptadorPuntuaciones2048 class. The ScoreViewHolder class extends the RecyclerView.ViewHolder class and defines the view elements that will be used to display each player's scores in the list, including their name, score, and difficulty.

The adapter also uses an instance of the DataBase class to retrieve players' scores and display them in the list. In the onCreateViewHolder method, the adapter inflates the view for each item in the list using the "formato_puntuaciones_2048.xml" layout file. In the onBindViewHolder method, the adapter retrieves the data for each position and assigns it to the corresponding view elements.

Finally, the getItemCount method returns the number of items that should be displayed in the list, which is obtained from the number of scores recorded in the database for the 2048 game.

### AdaptadorPuntuacionesLightsOut Class:

The AdaptadorPuntuacionesLightsOut class is an adapter for displaying the scores of the "Lights Out" game in a RecyclerView list of views on the screen. The class extends the RecyclerView.Adapter class and defines a ScoreViewHolder subclass that inherits from RecyclerView.ViewHolder.

The class constructor takes as arguments a context, an instance of the DataBase class, and a string that indicates the game.

The onCreateViewHolder method is responsible for creating a new view to display the data. The method inflates the view design formato_puntuaciones_lightsout and returns a new ScoreViewHolder object that contains the views to display the score information.

The onBindViewHolder method is called every time a view in the RecyclerView needs to be updated. This method retrieves the data from the database for the score at the specified position and sets it in the corresponding fields of the view.

The getItemCount method returns the number of items in the score list, which is obtained by calling the count method of the instance of the DataBase class.

The adapter also includes logic to change the color of the text in the difficulty view based on the score difficulty. The color is obtained from a color resource based on the difficulty and set in the difficulty view.

### Class Puntuaciones2048:

This class is a model class that represents a score in the game of 2048. It has three private fields: "id", "nombreJugador" and "puntuacion", which are accessible through their respective getter and setter methods.

The "id" variable is used to uniquely identify a particular score in the database. The "nombreJugador" variable stores the name of the player who achieved the score, while "puntuacion" stores the score itself.

The class does not have a constructor that accepts parameters, meaning that a Puntuaciones2048 object must be created and its values set through the setter methods.

This class is used to represent a game score in an application. The data stored in an object of this class can be used to display score information in a user interface or to store scores in a database.

### Class PuntuacionesLightsOut:

The PuntuacionesLightsOut class is a Java class that defines the attributes and methods necessary to represent the score in the Lights Out game.

The class has four attributes: id, nombreJugador, puntuacion and dificultad. The first three attributes represent the unique identifier of the score, the name of the player who achieved it, and the score itself, respectively. The fourth attribute, dificultad, represents the difficulty at which the score was achieved.

The class has an empty constructor and methods to access and modify the values of the attributes. The getId(), getNombreJugador() and getPuntuacion() methods allow you to obtain the values of the corresponding attributes, while the setId(), setNombreJugador() and setPuntuacion() methods allow you to set the values of these attributes.

The class also has the getDificultad() and setDificultad() methods, which allow you to obtain and set the value of the difficulty attribute.

### Class PuntuacionActivity:

The PuntuacionActivity class is the activity that displays the scores of the two games, Lights Out and 2048, using two custom adapters called AdaptadorPuntuacionesLightsOut and AdaptadorPuntuaciones2048.

In the onCreate method, the activity layout is configured and a DataBase object is created to interact with the database where game scores are stored. Then, an instance of MediaPlayer is initialized and played with a looping song.

Next, two RecyclerViews are set up and assigned their respective adapters, which are created by passing a reference to the current activity, the DataBase object, and the name of the game. Then, a LinearLayoutManager is assigned to each RecyclerView.

In the onResume method, it is verified that the music player is not null and music playback is resumed if it is paused. In the onPause method, it is checked whether the music player is not null and music playback is stopped if it is playing. Finally, in the onDestroy method, the MediaPlayer instance is stopped and released if it is not null.



### PuntuacionActivity Layout:

This XML file defines the layout of the PuntuacionActivity screen in the application using ConstraintLayout.

The ConstraintLayout tag is the root of the file and contains several child elements that are arranged in relation to each other through constraints.

The first child element is an ImageView with id "ranking" that occupies the entire top of the screen and displays an image with the text "Ranking".

Then there are two more ImageView elements, each with a unique id, that display images for different games.

Finally, there are two RecyclerViews, each with a unique id, that display user scores for each game. The RecyclerViews are configured to have a width of 184dp and a height of 383dp, and are separated by a space of 4dp. They also have a transparent background with a semi-transparent background color over it to add a shadow effect.

Additionally, it features a landscape version that allows viewing the activity in Landscape mode.

### formato_puntuaciones_2048 Layout:

This layout is the design of two TextViews (name and score) within a vertical LinearLayout. The layout_width and layout_height attributes have been set to "wrap_content", which means the layout size will automatically adjust to fit the TextViews size.

The orientation attribute is set to "vertical", which means the TextViews will be stacked on top of each other instead of side by side.

The layout_gravity attribute is set to "center_horizontal", which means the LinearLayout will be centered horizontally in its container.

The layout_marginBottom attribute is set to "20dp", which means there will be a 20-pixel margin at the bottom of the LinearLayout.

The two TextViews inside the LinearLayout have different text sizes, the first one is larger (32sp) than the second one (20sp). They also have different text style attributes, such as textStyle and textColor.




### formato_puntuaciones_lightsout Layout:

This XML file defines the layout of the LightsOut game score interface, which consists of a LinearLayout container that contains three TextView elements.

The xmlns:android attribute indicates that the namespace used in this file is provided by Android.

The layout_width attribute defines the width of the layout, in this case, wrap_content is used, which means the width will adjust to the content of the elements contained in it.

The layout_height attribute defines the height of the layout, in this case, wrap_content is also used.

The orientation attribute indicates that the elements inside the layout should be stacked vertically.

The layout_gravity attribute sets the position of the layout within its main container, in this case, it is set to center horizontally.

The layout_marginBottom attribute defines a bottom margin for the layout of 20dp.

The first TextView element with id name displays bold text with a font size of 32sp and a white text color (#ffffff).

The second TextView element with id difficulty also displays bold text, but with a slightly smaller font size of 23sp.

The third TextView element with id score displays text with a font size of 20sp and a white text color.

















## Animation files:

### animacion_arco:

This file defines an animation using a <set> tag that groups several animations. Each of the animations defines a specific transformation that will be applied to a view or set of views.

The animation begins with a translation animation on the X axis that moves the view from its original position to 100% to the left. Next, a translation animation on the Y axis is performed, moving the view down 100% from its original position. Then, another translation animation on the X axis moves the view to the right 100% from its original position. Finally, a translation animation on the Y axis moves the view up 100% from its original position.

In addition to the translation animations, a rotation animation and a scaling animation are included. The rotation animation rotates the view around its center by 360 degrees. The scaling animation increases the size of the view by a factor of 2 in both axes.
Each animation includes attributes such as fromXDelta, toXDelta, duration, and startOffset, which define the direction and duration of the animation, as well as the delay before the animation starts.

This animation is used to move the alien image in the Menu and LightsOutMenu classes.

### animation_set:

This animation file is a set of animations applied to a view in the application. The animation in question is a scaling animation that gradually increases and decreases the size of the view in a continuous cycle.

The file begins with the <set> tag, which is the root tag for all animations in Android. Within this tag, a single scaling animation is defined using the <scale> tag.

The scaling animation specifies a set of attributes that control how the animation will be applied. In this case, the animation has a duration of 3 seconds and a pivot point is defined at the center of the view (50% in both directions), meaning the view will increase and decrease in size around this central point. The fromXScale and fromYScale values define the initial size of the view (90% of its original size), and the toXScale and toYScale values define its final size (110% of its original size).

Finally, the repeatCount and repeatMode attributes control how the animation will repeat. In this case, the animation will repeat infinitely (infinite) and will reverse each time it completes (reverse), meaning the view will continuously increase and decrease in size while the animation is running.

This animation file is used for all buttons in the application.

### splash_animation:

This animation file defines a composite animation consisting of three transformations: a translation, a scaling, and a rotation.

The translation moves the element from a position (-100%, -100%) to the final position (0%, 0%) over a duration of 2000 milliseconds.

The scaling defines how the element should be scaled. It begins with a scale value of 0.0 in both dimensions and then expands to a scale value of 1.0 in both dimensions over 2000 milliseconds. A pivot point for the scale is also set at the center of the element (50%, 50%).

The rotation defines how the element should rotate during the animation. It begins with a rotation of 0 degrees and ends with a full rotation of 360 degrees.

Together, these transformations result in an animation that moves the element from the top left position of the screen, expands and rotates over 2 seconds.

This animation is applied to the application logo in the SplashAnimation activity.


