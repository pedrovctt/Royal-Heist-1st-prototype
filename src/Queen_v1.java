/*
 * @author: Pedro Donato
 * @desc: This class represents the first version of the main character
 *        Queen.
 */

import java.awt.Point;
import java.util.Vector;
import jplay.*;

public class Queen_v1 extends Sprite
{
    private double speed = 1.0;  // Speed of the character
    private int direction = 3;  // Initial direction
    private boolean isMoving = false;  // To check if the character is moving

    // Creates the Queen at a given position (x, y)
    public Queen_v1(int x, int y)
    {
        super("src\\com\\resources\\sprites\\Queen_v1.png", 15);  // Load the Queen's sprite with 15 frames
        this.x = x;
        this.y = y;
        this.setTotalDuration(2000);  // Set the total animation duration to 2000 milliseconds
    }

    // Get the current x position
    public double getX()
    {
        return x;
    }

    // Get the current y position
    public double getY()
    {
        return y;
    }

    // Move the Queen based on keyboard input
    public void move(Window window, Keyboard keyboard)
    {
        // If no keyboard is provided, get it from the window
        if(keyboard == null)
        {
            keyboard = window.getKeyboard();
        }

        // Move left
        if(keyboard.keyDown(Keyboard.LEFT_KEY))
        {
            if(this.x > 0) // Check left boundary
            {
                this.x -= speed;
                System.out.println("X: " + getX() + " Y: " + getY());
            }
            if(direction != 1) // Update animation for her left movement
            {
                setSequence(3, 7);
                direction = 1;
            }
            isMoving = true;
        }
        // Move right
        else if(keyboard.keyDown(Keyboard.RIGHT_KEY))
        {
            if(this.x < window.getWidth() - 60) // Check right boundary
            {
                this.x += speed;
                System.out.println("X: " + getX() + " Y: " + getY());
            }
            if(direction != 2) // Update animation for right movement
            {
                setSequence(7, 11);
                direction = 2;
            }
            isMoving = true;
        }
        // Move up
        else if(keyboard.keyDown(Keyboard.UP_KEY))
        {
            if(getY() != 127.0)
            {
                if(this.y > 0) // Check upper boundary
                {
                    this.y -= speed;
                    System.out.println("X: " + getX() + " Y: " + getY());
                }
                if(direction != 4) // Update animation for upward movement
                {
                    setSequence(11, 15);
                    direction = 4;
                }
                isMoving = true;
            }
        }
        // Move down
        else if(keyboard.keyDown(Keyboard.DOWN_KEY))
        {
            if(this.y <= window.getHeight() - 60) // Check lower boundary
            {
                this.y += speed;
                System.out.println("X: " + getX() + " Y: " + getY());
            }
            if(direction != 5) // Update animation for downward movement
            {
                setSequence(0, 3);
                direction = 5;
            }
            isMoving = true;
        }

        // Update animation if moving
        if(isMoving)
        {
            update();  // Update the sprite animation
            isMoving = false;
        }
    }

    Control control = new Control();  // Create a control object for collision detection

    // Check for collisions with scene tiles
    public void wayControl(Scene scene) 
    {
        Point min = new Point((int)this.x,(int)this.y);  // Top-left corner
        Point max = new Point((int)this.x + this.width, (int)this.y + this.height);  // Bottom-right corner
        
        Vector<?> tiles = scene.getTilesFromPosition(min, max);  // Get tiles around the Queen
        
        for(int i = 0; i < tiles.size(); i++)
        {
            TileInfo tile = (TileInfo) tiles.elementAt(i);
            
            // Check if there's a collision with the tile
            if(control.collision(this, tile))
            {
                // Handle vertical collision
                if(verticalCollision(this, tile))
                {
                    if(tile.y + tile.height - 2 < this.y)
                    {
                        this.y = tile.y + tile.height;
                    }
                    else if(tile.y > this.y + this.height - 2)
                    {
                        this.y = tile.y - this.height;
                    }
                }
                
                // Handle horizontal collision
                if(horizontalCollision(this, tile))
                {
                    if(tile.x > this.x + this.width - 2)
                    {
                        this.x = tile.x - this.width;
                    }
                    else
                    {
                        this.x = tile.x + tile.width;
                    }
                }
            }
        }
    }

    // Check vertical collision
    private boolean verticalCollision(GameObject gameObj, GameObject gameObj2)
    {
        if(gameObj2.x + gameObj2.width <= gameObj.x) return false;
        if(gameObj.x + gameObj.width <= gameObj2.x) return false;
        return true;
    }
    
    // Check horizontal collision
    private boolean horizontalCollision(GameObject gameObj, GameObject gameObj2)
    {
        if(gameObj2.y + gameObj2.height <= gameObj.y) return false;
        if(gameObj.y + gameObj.height <= gameObj2.y) return false;
        return true;
    }
}
