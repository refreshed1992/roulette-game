package model.enumeration;

import model.interfaces.Player;
import model.interfaces.Slot;

/**
 * Provided enum type for Further Programming representing a type of Bet<br>
 * See inline comments for where you need to provide additional code
 * 
 * @author Caspar Ryan
 * 
 */
public enum BetType
{
   RED
   {
      @Override
      public void applyWinLoss(Player player, Slot winSlot)
      {
    	  if ((player.getBetType() == BetType.RED) && (winSlot.getColor() == Color.RED))
    	  {
    		  player.setPoints(player.getPoints() + (player.getBet() * 2));
    	  }
    	  else
    	  {
    		  player.setPoints(player.getPoints() - player.getBet());
    	  }
      }
   },
   BLACK
   {
	   @Override
	   public void applyWinLoss(Player player, Slot winSlot) 
	   {
		   if ((player.getBetType() == BetType.BLACK) && (winSlot.getColor() == Color.BLACK))
	    	  {
	    		  player.setPoints(player.getPoints() + (player.getBet() * 2));
	    	  }
	    	  else
	    	  {
	    		  player.setPoints(player.getPoints() - player.getBet());
	    	  } 
	   }
	   
   },
   ZEROS
   {
	   @Override
	   public void applyWinLoss(Player player, Slot winSlot) 
	   {
		   if ((player.getBetType() == BetType.ZEROS) && 
				   (winSlot.getColor() == Color.GREEN0 || winSlot.getColor() == Color.GREEN00))
	    	  {
	    		  player.setPoints(player.getPoints() + (player.getBet() * (Slot.WHEEL_SIZE / 2)));
	    	  }
	    	  else
	    	  {
	    		  player.setPoints(player.getPoints() - player.getBet());
	    	  }
	   }   
   };
   public abstract void applyWinLoss(Player player, Slot winSlot);
}