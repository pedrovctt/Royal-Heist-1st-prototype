/*
 * @author: Pedro Donato
 * @desc: This class creates the first game scenario with a player (Queen) 
 *        and an enemy (Thief). It controls their movement and handles 
 *        scene interactions.
 */

import jplay.*;

public class Scenario1
{
	private Window window;  // Game window
	private Scene scene;    // Game scene
	private Keyboard keyboard;  // Keyboard input
	private Queen_v1 player;  // Player character
	private Thief thief;  // Enemy character
	
	// Constructor - Sets up the scenario
	public Scenario1(Window window)
	{
		this.window = window;
		scene = new Scene();
		keyboard = window.getKeyboard();
		player = new Queen_v1(25, 343);  // Player starts at (25, 343)
		thief = new Thief(300, 300);  // Thief starts at (300, 300)
		scene.loadFromFile("src\\com\\resources\\scn\\Scenario1.scn");  // Load scene
		run();  // Start the game loop
	}
	
	// Get player X position
	public double getPlayerX()
	{
		return player.getX();
	}
	
	// Get player Y position
	public double getPlayerY()
	{
		return player.getY();
	}

	// Game loop: Runs the logic
	public void run() 
	{
		while(true)
		{
			// Move scene with the player
			scene.moveScene(player);
			
			// Update player position with scene offset
			player.x += scene.getXOffset();
			player.y += scene.getYOffset();
			
			// Check player collisions and draw
			player.wayControl(scene);
			player.draw();
		    player.move(window, keyboard);
		    
		    // Update thief position with scene offset
		    thief.x += scene.getXOffset();
		    thief.y += scene.getYOffset();
		    
		    // Check thief collisions and draw
		    thief.wayControl(scene);
			thief.draw();
			thief.move(window, keyboard);
			
		    // Update window and add a small delay
		    window.update();
		    window.delay(2);
		}
	}
}
