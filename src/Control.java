/*
 * @author: Pedro Donato
 * @desc: class Control represents a system which helps with the sprite (Queen and Thief)
 *        collisions.
 */

import jplay.*;

public class Control
{
	public boolean collision(GameObject gameObj, TileInfo tile)
	{
		if((tile.id >= 4 || tile.id == 0) && gameObj.collided(tile))
		{
			return true;
		}
		return false;
	}
}
