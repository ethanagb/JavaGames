import java.util.Arrays;
import java.util.Scanner;

public class MastermindController{
	public MastermindController(MastermindModel model){
		int guessNumber = 1;
		//char [] colorSolutionArray = model.getSolution();
		String solutionString = Arrays.toString(model.getSolution());
		//System.out.println(solutionString);
		//////////////////////////
		java.util.Scanner input = new java.util.Scanner(System.in);
		boolean stopPlaying = false;
		while (stopPlaying==false){
			if (guessNumber > 10){ //board only has 10 rows (tries)
				System.out.println("***********************");
				System.out.println("Game over. You lose.");
				System.out.println("The correct code was: " + solutionString); //reveals the answer 
				System.out.println("***********************\n");
				break;
			}
			System.out.println("\nEnter guess number " + guessNumber + ": ");
			String guess = input.nextLine();
			if (guess.length() ==4){ //check length of guess
				//check for valid colors
				if (!(guess.contains("a") || guess.contains("c") || guess.contains("d") ||guess.contains("e") || guess.contains("f") || guess.contains("h") || guess.contains("i") || guess.contains("j") ||guess.contains("k") || guess.contains("l") || guess.contains("m") || guess.contains("n") || guess.contains("q") ||guess.contains("s") || guess.contains("t") || guess.contains("u") || guess.contains("w") ||guess.contains("x") ||guess.contains("z"))){

					stopPlaying = isCorrect(guess, model.getSolution()); //stop playing when you guess correctly
					int rightColorRightPlace = getRightColorRightPlace(guess, model.getSolution());
					int rightColorWrongPlace = getRightColorWrongPlace(guess, model.getSolution());
					int correctedRightColorWrongPlace = rightColorWrongPlace - rightColorRightPlace;
					System.out.println("\nColors in the correct place: " + rightColorRightPlace);
					System.out.println("Colors correct but in wrong position: " + correctedRightColorWrongPlace); //this might not be the write value, come back and check this
					guessNumber++;
				}
				else{
					System.out.println("You've included an invalid color. Try that again.");
				}
			}
			else if (guess.length() != 4){
				System.out.println("Your guess must have 4 colors.");
			}
		}
		if (stopPlaying==true){ //this means you've won
			System.out.println("***********************");
			System.out.println("You win!");
			System.out.println("***********************\n");
		}
	}
	public boolean isCorrect(String guess, char[] solution){
		boolean guessIsCorrect = false;
		char[] guessArray = guess.toCharArray();
		if (Arrays.equals(guessArray, solution)) {
			guessIsCorrect=true;
		}
		return guessIsCorrect;
	}

	public int getRightColorRightPlace(String guess,char[] solution){
		int rightColorRightPlace = 0;
		char[] guessArray = guess.toCharArray();
		for (int i=0; i<guessArray.length;i++){
			////////////For debugging. 
			//System.out.println("Guess:" + guessArray[i]);
			//System.out.println("Solution:" + solution[i]);
			/////////////
			if (guessArray[i] == solution[i]){
				rightColorRightPlace++;
			}
		}
		return rightColorRightPlace;
	}
	public int getRightColorWrongPlace(String guess, char[] solution){
		char[] guessArray = guess.toCharArray();
		int totalMalpositioned = 0;
		int [] guessArrayCounts = new int[6];
		int [] solutionArrayCounts = new int[6];
		char guessValue = 'y';
		char solutionValue = 'x';
		for (int i=0; i < 4; i++){
			guessValue = guessArray[i];
			solutionValue = solution[i];
			int guessColorAsInt = colorChartoInt(guessValue);
			int solutionColorAsInt = colorChartoInt(solutionValue);
			guessArrayCounts[guessColorAsInt-1]+=1;// subtract one as a correction from the numbering scheme I used to array counting from 0
			solutionArrayCounts[solutionColorAsInt-1]+=1;
		}
		for (int j=0; j< solutionArrayCounts.length; j++) {
			totalMalpositioned += Math.min(guessArrayCounts[j], solutionArrayCounts[j]);
		}
		
		//the above code ignores anything that is also in the right position...just has counted correct colors.
		//fixed this by subtracting the number that are correct color and position in the main method. 
		return totalMalpositioned;
	}

	//converts char back to corresponding int to use in an array position. Numbers are arbitrary. 
	private int colorChartoInt(char colorChar){
		int colorInt = 0; //0 unassigned to any color.
		if (colorChar == 'r'){
			colorInt = 1;
		}
		else if (colorChar == 'o'){
			colorInt = 2;
		}
		else if (colorChar == 'y'){
			colorInt = 3;
		}
		else if (colorChar == 'g'){
			colorInt = 4;
		}
		else if (colorChar == 'b'){
			colorInt = 5;
		}
		else if (colorChar == 'p'){
			colorInt = 6;
		}
		return colorInt;
	}
}