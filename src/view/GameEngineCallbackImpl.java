package view;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behavior
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

   public GameEngineCallbackImpl()
   {
	  // manually enable Level.FINE logging
	  ConsoleHandler handler = new ConsoleHandler();
      handler.setLevel(Level.FINE);
      logger.addHandler(handler);
      logger.setUseParentHandlers(false);
      
      // FINE shows wheel spinning output, INFO only shows result
	  logger.setLevel(Level.FINE);
   }

   @Override
   public void nextSlot(Slot slot, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
	  logger.log(Level.FINE, "Next slot: " + slot.toString());
   }

   @Override
   public void result(Slot result, GameEngine engine)
   {
	   // final results logged at Level.INFO
	   logger.log(Level.INFO, "RESULT=" + result.toString() + "\n");
	      
	   // calculate player betting results
      engine.calculateResult(result);    
      String playerString = "";
      for (Player p : engine.getAllPlayers())
      {
    	playerString = playerString + "\n" + engine.getPlayer(p.getPlayerId()).toString();  
      }
      logger.log(Level.INFO, "FINAL PLAYER POINT BALANCES");
      logger.log(Level.INFO, playerString);

      // reset all player bets ready for next spin
      for (Player p : engine.getAllPlayers())
      {
    	  p.resetBet();
      }    
   }
}
