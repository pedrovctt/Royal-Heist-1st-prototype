import jplay.*;  // Import the JPlay library for game development

public class Main 
{
    // Main method - the starting point of the game
    public static void main(String[] args) 
    {
        // Create a window with size 800x600 pixels
        Window window = new Window(800, 600);
        
        // Load a generic AI generated image for the main menu
        GameImage gameFlat = new GameImage("src\\com\\resources\\sprites\\MainMenu.png");
        
        // Create a Keyboard object to get user input
        Keyboard keyboard = window.getKeyboard();
        
        // Game loop - runs over and over until the game closes
        while(true)
        {
            // Draw the main menu image on the window
            gameFlat.draw();
            
            // Update the window to show the image
            window.update();
            
            // If ESC key is pressed
            if(keyboard.keyDown(Keyboard.ESCAPE_KEY)) 
            {
                window.exit();  // Close the window and stop the game
            }
            // If ENTER key is pressed, start the game
            else if(keyboard.keyDown(Keyboard.ENTER_KEY))
            {
                // Start the first Scenario
                new Scenario1(window);
            }
        }
    }
}