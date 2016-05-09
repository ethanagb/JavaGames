import java.util.Scanner;
class Mastermind{
	public static void main(String[] args){
		System.out.println("***********************");
		System.out.println("Welcome to Mastermind!");
		System.out.println("***********************");
		System.out.println();
		while(true){
			System.out.println("Would you like to play? (Yes/No)");
			java.util.Scanner input = new java.util.Scanner(System.in);
			String playVar = input.nextLine();
			if (playVar.equalsIgnoreCase("yes")){
				System.out.println("Try to guess a code of 4 colors.");
				System.out.println("Red (r), Orange (o), Yellow (y), Green (g), Blue (b), Purple(p)");
				MastermindModel gameModel = new MastermindModel();
				MastermindController gameController = new MastermindController(gameModel);
				//gameController.MastermindController(gameModel);

			}
			else if (playVar.equalsIgnoreCase("no")){
				System.out.println("Goodbye.");
				break;
			}
		}
	}
}