package model;

import model.enumeration.Color;
import model.interfaces.Slot;

/**
 * @author Sam Breznikar
 *
 */
public class SlotImpl implements Slot 
{
	private int position;
	private Color color;
	private int number;
	
	public static final int WHEEL_SIZE = 38;
	
	public SlotImpl(int position, Color color, int number) 
	{
		this.position = position; 
		this.color = color;
		this.number = number;
	}

	@Override
	public String toString() 
	{
		// format the colour string correctly
		String slotColor = this.color.name();
		slotColor = slotColor.toLowerCase();
		slotColor = slotColor.toUpperCase().charAt(0)+slotColor.substring(1);
		
		return String.format(
				"Position: %d, Color: %s, Number: %d", 
				this.position, slotColor, this.number);
	}
	
	@Override
	public boolean equals(Slot slot) 
	{
		// return true if color and number are same
        if (this.color == slot.getColor() && this.number == slot.getNumber())
        {
        	return true;
        }
        else
        {
        	return false;	
        }
	}

	@Override
	public boolean equals(Object slot) 
	{
		if (slot instanceof Slot)
		{
			return equals((Slot) slot);			
		}
		else
		{
			return false;
		}
	}

	@Override
	public int hashCode() 
	{
		int result = 1;
		int prime = 37;
		result = prime * result + color.hashCode();
		result = prime * result + number;
		return result;
	}
	
	@Override
	public int getPosition() 
	{
		return position;
	}

	@Override
	public int getNumber() 
	{
		return number;
	}
	
	@Override
	public Color getColor() 
	{
		return color;
	}
}
