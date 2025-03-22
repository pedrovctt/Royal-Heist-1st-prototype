/*
 * @author: Pedro Donato
 * @desc: This class represents the character Thief.
 */

import java.awt.Point;
import java.util.Vector;
import jplay.*;

public class Thief extends Sprite
{
    // Thief's movement speed
    private double speed = 1.5;
    private int direction = 3;
    private boolean isMoving;

    // Constructor to initialize Thief's position and sprite
    public Thief(int x, int y)
    {
        super("src\\com\\resources\\sprites\\Thief.png", 15);
        this.x = x;
        this.y = y;
        setTotalDuration(2000);
    }

    // Method to control the Thief's movement
    public void move(Window window, Keyboard keyboard)
    {
        if(keyboard == null)
        {
            keyboard = window.getKeyboard();
        }
        
        // Move left
        if(keyboard.keyDown(Keyboard.LEFT_KEY))
        {
            if(this.x > 0)
            {
                this.x -= speed;
            }
            // Update sprite animation for left movement
            if(direction != 1)
            {
                setSequence(3, 7);
                direction = 1;
            }
            isMoving = true;
        }
        // Move right
        else if(keyboard.keyDown(Keyboard.RIGHT_KEY))
        {
            if(this.x < window.getWidth() - 60)
            {
                this.x += speed;
            }
            // Update sprite animation for right movement
            if(direction != 2)
            {
                setSequence(7, 11);
                direction = 2;
            }
            isMoving = true;
        }
        // Move up
        else if(keyboard.keyDown(Keyboard.UP_KEY))
        {
            if(this.y > 0)
            {
                this.y -= speed;
            }
            // Update sprite animation for upward movement
            if(direction != 4)
            {
                setSequence(11, 15);
                direction = 4;
            }
            isMoving = true;
        }
        // Move down
        else if(keyboard.keyDown(Keyboard.DOWN_KEY))
        {
            if(this.y < window.getHeight() - 60)
            {
                this.y += speed;
            }
            // Update sprite animation for downward movement
            if(direction != 5)
            {
                setSequence(0, 3);
                direction = 5;
            }
            isMoving = true;
        }
        
        // Update sprite animation if moving
        if(isMoving)
        {
            update();
            isMoving = false;
        }
    }

    Control control = new Control();

    // Controls Thief's collision with scene tiles
    public void wayControl(Scene scene) 
    {
        Point min = new Point((int)this.x,(int)this.y);
        Point max = new Point((int)this.x + this.width, (int)this.y + this.height);
        
        Vector<?>tiles = scene.getTilesFromPosition(min, max);
        
        for(int i = 0; i < tiles.size(); i++)
        {
            TileInfo tile = (TileInfo) tiles.elementAt(i);
            
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

    // Checks for vertical collision between two objects
    private boolean verticalCollision(GameObject gameObj, GameObject gameObj2)
    {
        if(gameObj2.x + gameObj2.width <= gameObj.x) return false;
        if(gameObj.x + gameObj.width <= gameObj2.x) return false;
        return true;
    }
    
    // Checks for horizontal collision between two objects
    private boolean horizontalCollision(GameObject gameObj, GameObject gameObj2)
    {
        if(gameObj2.y + gameObj2.height <= gameObj.y) return false;
        if(gameObj.y + gameObj.height <= gameObj2.y) return false;
        return true;
    }
}
