package model;

import model.enumeration.BetType;
import model.interfaces.Player;

/**
 * @author Sam Breznikar
 *
 */
public class SimplePlayer implements Player
{

	private String playerName;
	private String playerId;
	private int points;
	private int bet;
	private BetType betType;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) 
	{
		this.playerId = playerId;
		this.playerName = playerName;
		this.points = initialPoints;
	}

	@Override
	public String getPlayerName() 
	{
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) 
	{
		this.playerName = playerName;
	}

	@Override
	public int getPoints() 
	{
		return points;
	}

	@Override
	public void setPoints(int points) 
	{
		this.points = points;
	}

	@Override
	public String getPlayerId() 
	{
		return playerId;
	}

	@Override
	public boolean setBet(int bet) 
	{
		if (bet == 0)
		{
			resetBet();			
		}
		
		// return true and set new bet if bet is valid 
		if ((bet > 0) && (this.points >= bet))
		{
		    this.bet = bet;
		    return true;
		}
		else
		{
		    return false;
				
		}
	}

	@Override
	public int getBet() 
	{
		return bet;
	}

	@Override
	public void setBetType(BetType betType) 
	{
		this.betType = betType;
	}

	@Override
	public BetType getBetType() 
	{
		return this.betType;
	}

	@Override
	public void resetBet() 
	{
		this.bet = 0;
	}
	
	@Override
	public String toString() 
	{
		return String.format(
				"Player: id=%s, name=%s, bet=%d, betType=%s, points=%d", 
				this.playerId, this.playerName, this.bet,
				this.betType, this.points);
	}
}
