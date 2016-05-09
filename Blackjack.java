/* Ethan Alexander Garcia Baker
CS 0401 - Project 2
21 Feb 2016 */

class Blackjack{
	public static void main(String[] args){
		System.out.println("Welcome to Ethan's Casino!");
		System.out.print("Please enter your name: ");
		java.util.Scanner input = new java.util.Scanner(System.in);
		String playerName = input.nextLine();
		int dealerSum = dealer();
		int playerSum = player();
		boolean dealerPlays = true;
		//player decision making phase, which will continue until stand or busting.
		while (playerSum <= 21){
			System.out.println("\n" + "Would you like to hit or stand?");
			String hitOrStand = input.nextLine();
			if (hitOrStand.equalsIgnoreCase("hit")){
				System.out.println("You have chosen to hit. Dealing another card...");
				System.out.println();
				int nextPlayerCard = dealCard();
				int newPlayerSum = playerSum +  nextPlayerCard;
				if (newPlayerSum > 21 && nextPlayerCard == 11){
					nextPlayerCard = 1;
					newPlayerSum -= 10; //adopt the lower value for the ace
				}
				if (newPlayerSum > 21 && nextPlayerCard != 11) {
					System.out.println("You:");
					System.out.println(playerSum + " + " + nextPlayerCard + " = " + newPlayerSum);
					System.out.println("You busted! Dealer wins!\n");
					dealerPlays = false;
					playerSum = newPlayerSum;
					break;
				}
				if (newPlayerSum <= 21) {
					System.out.println("You:\n");
					System.out.println(playerSum + " + " + nextPlayerCard + " = " + newPlayerSum);
					System.out.println();
					playerSum = newPlayerSum;
				}
			}
			if (hitOrStand.equalsIgnoreCase("stand")){
				System.out.println("You chose to stand. Now the dealer will play.");
				break;
			}
			else if (!hitOrStand.equalsIgnoreCase("stand") && !hitOrStand.equalsIgnoreCase("hit")){
				System.out.println("You must enter either 'hit' or 'stand'");
			}
		}
		//Dealer plays now that the player phase is over.
		int nextDealerCard = 0;
		int newDealerSum = 0;
		if (dealerSum < 17 && dealerPlays == true){
			System.out.println("Dealer:");
			while(dealerSum < 17){
				System.out.println("The dealer will hit.");
				System.out.println();
				nextDealerCard = dealCard();
				newDealerSum = dealerSum + nextDealerCard;
				//adopt the lower value for ace if you would have otherwise exceeded 21. 
				if (newDealerSum > 21 && nextDealerCard == 11){
					nextDealerCard = 1;
					newDealerSum -= 10; 
				}
				if (newDealerSum < 21){
					System.out.println("Dealer:");
					//System.out.println();
					System.out.println(dealerSum + " + " + nextDealerCard + " = " + newDealerSum);
					System.out.println();
				}
				dealerSum = newDealerSum;
			}
		}

		if (dealerSum >= 17 && dealerSum <=21 && dealerPlays == true){
			System.out.println("Dealer:");
			System.out.println("The dealer's current total is " + dealerSum);
			System.out.println("Dealer stands.");
			//check who won.
			if (playerSum > dealerSum){
				System.out.println("You win!");
			}
			if (playerSum == dealerSum){
				System.out.println("You tied this hand...");
			}
			if (playerSum < dealerSum){
				System.out.println("The dealer wins!");
			}
		}
		/* Confused about whether or not this is possible in this game...if you bust is the hand over and the dealer does not play?
		if (playerSum > 21 && dealerSum >21){
			System.out.println("Dealer:");
			System.out.println();
			System.out.println(dealerSum + " + " + nextDealerCard + " = " + newDealerSum);
			System.out.println("The dealer and you both busted. The dealer wins.");
		}*/
		else if (dealerSum > 21 && dealerPlays == true){
			System.out.println("Dealer:");
			System.out.println();
			System.out.println(dealerSum + " + " + nextDealerCard + " = " + newDealerSum);
			System.out.println("The dealer busted. You win.");
		}
	}
	public static int dealCard(){
		int dealtCard = (int)(Math.random()*13+1);
		if (dealtCard == 13 || dealtCard == 12 || dealtCard == 11){
			dealtCard = 10; //make all face cards the same value of 10. 
		}
		if (dealtCard == 1){
			dealtCard = 11; //by defualt an ace is 11 points 
		}
		return dealtCard;
	}
	public static int dealer(){
		boolean dealerHasAce = false;
		System.out.println("The dealer:");
		int dealerCard1 = dealCard(); //let this be the card shown to the player. 
		int dealerCard2 = dealCard();
		System.out.println("? + " + dealerCard1);
		if (dealerCard1 == 11 || dealerCard2 == 11){
			dealerHasAce = true;
		}
		int dealerSum = dealerCard1 + dealerCard2;
		if (dealerSum > 21 && dealerHasAce == true){
			//System.out.println("Ace is now worth 1 point.");
			if (dealerCard1 == 11 && dealerCard2 == 11){
				dealerCard2 = 1; //doesn't matter which ace gets reduced if you have 2 aces
			}
			else if (dealerCard1 == 11 && dealerCard2 != 11){
				dealerCard1 = 1;
			}
			else if (dealerCard2 == 11 && dealerCard1 != 11){
				dealerCard2 = 1;
			}
			dealerSum = dealerCard1 + dealerCard2;
		}
		return dealerSum;
	}
	public static int player(){
		boolean playerHasAce = false;
		int playerCard1 = dealCard();
		int playerCard2 = dealCard();
		if (playerCard2 == 11 || playerCard1 == 11){
			playerHasAce = true;
		}
		int playerSum = playerCard2 + playerCard1;
		if (playerSum > 21 && playerHasAce == true){
			//System.out.println("Ace is now worth 1 point.");
			if (playerCard1 == 11 && playerCard2 == 11){
				playerCard2 = 1; //doesn't matter which ace gets reduced if you have 2 aces
			}
			else if (playerCard1 == 11 && playerCard2 != 11){
				playerCard1 = 1;
			}
			else if (playerCard2 == 11 && playerCard1 != 11){
				playerCard2 = 1;
			}
			playerSum = playerCard1 + playerCard2;
		}
		System.out.println("You:");
		System.out.println(playerCard1 + " + " + playerCard2 + " = " + playerSum);
		return playerSum;
	}
}