package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import model.enumeration.BetType;
import model.enumeration.Color;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.Slot;
import view.interfaces.GameEngineCallback;

/**
 * @author Sam Breznikar
 *
 */
public class GameEngineImpl implements GameEngine 
{
	private ArrayList<Player> players;
	private HashMap<Integer, Slot> wheelSlots;
	private ArrayList<GameEngineCallback> callbacks;
	private Random random;
	
	public GameEngineImpl() 
	{
		wheelSlots = buildWheel();
		players = new ArrayList<Player>();
		callbacks = new ArrayList<GameEngineCallback>();
		random = new Random();
	}
	
	@Override
	public void spin(int initialDelay, int finalDelay, int delayIncrement) 
	{
		// select a random slot (ball enters the wheel here)
		int key = random.nextInt(Slot.WHEEL_SIZE);
		Slot startingSlot = wheelSlots.get(key);
		
		// iterate from the start slot until wheel stops spinning
		while (initialDelay < finalDelay)
		{		
			initialDelay += delayIncrement;	
			
			// call nextSlot each time a new slot is passed 
			for(GameEngineCallback g : callbacks)
			{
				g.nextSlot(startingSlot, this);
			}
					
			// increment the slot each iteration
			key += 1;
			if (key == Slot.WHEEL_SIZE)
			{
				key = 0;
			}
			startingSlot = wheelSlots.get(key);

			// sleep for delay duration to mimic the wheel slowing down
			try 
			{
				java.lang.Thread.sleep(initialDelay);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			};
		}		
		
		// log final player point balances
		for(GameEngineCallback g : callbacks)
		{
			g.result(startingSlot, this);
		}
	}

	@Override
	public void calculateResult(Slot winningSlot) 
	{
		for (Player p : players)
		{
			// apply win/loss and update points
			p.getBetType().applyWinLoss(p, winningSlot);
		}
	}

	@Override
	public void addPlayer(Player player) 
	{
		// add immediately if player list is empty
		if ( players.isEmpty() )
		{
			players.add(player);
		} 
		else 
		{
			// if not empty, remove conflicting players and add new player
			Iterator<Player> iterator = players.iterator();
			while ( iterator.hasNext() )
			{
				if ( iterator.next().getPlayerId() == player.getPlayerId() )
				{
					iterator.remove();		
				}
			}
			players.add(player);
		}
	}

	@Override
	public Player getPlayer(String id) 
	{
		for ( Player player : players )
		{
			// return player if playerId matches
			if ( player.getPlayerId().equals(id) )
			{
				return player;
			}
		}
		return null;			
	}

	@Override
	public boolean removePlayer(Player player) 
	{
		for ( Player p : players )
		{
			if ( p == player )
			{
				// remove player if it matches/exists
				players.remove(players.indexOf(p));
				return true;
			}
		}
		return false;			
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		callbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) 
	{
		for ( GameEngineCallback c : callbacks )
		{
			if ( c == gameEngineCallback )
			{
				// remove callback if it exists in Collection
				callbacks.remove(callbacks.indexOf(c));
				return true;
			}
		}
		return false;			
	}
	
	@Override
	public Collection<Player> getAllPlayers() 
	{	
		// create shallow and unmodifiable copy of player collection
		Collection<Player> allPlayers = new ArrayList<Player>(players);
		allPlayers = Collections.unmodifiableCollection(allPlayers);
		return allPlayers;
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) 
	{	
		player.setBetType(betType);

		// return true if bet > 0 & player had enough points to place bet
		if ((bet > 0) && player.setBet(bet))
		{
			return true;
		}
		return false;
	}

	@Override
	public Collection<Slot> getWheelSlots() 
	{	
		// return only the values (Slots) of the hashmap's contents
		return wheelSlots.values();
	}
	
	private HashMap<Integer, Slot> buildWheel() 
	{
		HashMap<Integer, Slot> wheel = new HashMap<Integer, Slot>(Slot.WHEEL_SIZE);
		
		// populate "wheel" with appropriately keyed and numbered slots
		wheel.put(0, new SlotImpl(0, Color.GREEN00, 00));
		wheel.put(1, new SlotImpl(1, Color.RED, 27));
		wheel.put(2, new SlotImpl(2, Color.BLACK, 10));
		wheel.put(3, new SlotImpl(3, Color.RED, 25));
		wheel.put(4, new SlotImpl(4, Color.BLACK, 29));
		wheel.put(5, new SlotImpl(5, Color.RED, 12));
		wheel.put(6, new SlotImpl(6, Color.BLACK, 8));
		wheel.put(7, new SlotImpl(7, Color.RED, 19));
		wheel.put(8, new SlotImpl(8, Color.BLACK, 31));
		wheel.put(9, new SlotImpl(9, Color.RED, 18));
		wheel.put(10, new SlotImpl(10, Color.BLACK, 6));
		wheel.put(11, new SlotImpl(11, Color.RED, 21));
		wheel.put(12, new SlotImpl(12, Color.BLACK, 33));
		wheel.put(13, new SlotImpl(13, Color.RED, 16));
		wheel.put(14, new SlotImpl(14, Color.BLACK, 4));
		wheel.put(15, new SlotImpl(15, Color.RED, 23));
		wheel.put(16, new SlotImpl(16, Color.BLACK, 35));
		wheel.put(17, new SlotImpl(17, Color.RED, 14));
		wheel.put(18, new SlotImpl(18, Color.BLACK, 2));
		wheel.put(19, new SlotImpl(19, Color.GREEN0, 0));
		wheel.put(20, new SlotImpl(20, Color.BLACK, 28));
		wheel.put(21, new SlotImpl(21, Color.RED, 9));
		wheel.put(22, new SlotImpl(22, Color.BLACK, 26));
		wheel.put(23, new SlotImpl(23, Color.RED, 30));
		wheel.put(24, new SlotImpl(24, Color.BLACK, 11));
		wheel.put(25, new SlotImpl(25, Color.RED, 7));
		wheel.put(26, new SlotImpl(26, Color.BLACK, 20));
		wheel.put(27, new SlotImpl(27, Color.RED, 32));
		wheel.put(28, new SlotImpl(28, Color.BLACK, 17));
		wheel.put(29, new SlotImpl(29, Color.RED, 5));
		wheel.put(30, new SlotImpl(30, Color.BLACK, 22));
		wheel.put(31, new SlotImpl(31, Color.RED, 34));
		wheel.put(32, new SlotImpl(32, Color.BLACK, 15));
		wheel.put(33, new SlotImpl(33, Color.RED, 3));
		wheel.put(34, new SlotImpl(34, Color.BLACK, 24));
		wheel.put(35, new SlotImpl(35, Color.RED, 36));
		wheel.put(36, new SlotImpl(36, Color.BLACK, 13));
		wheel.put(37, new SlotImpl(37, Color.RED, 1));

		return wheel;	
	}
}
