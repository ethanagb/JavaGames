import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Boggle{
	public static void main(String[] args){
		//Read the dictionary
		ArrayList<String> dictionary = new ArrayList<>();
		try{
			Scanner infile = new Scanner(new File("dict.txt")); //dict.txt is the provided list of accepted words
			while (infile.hasNextLine()){ //keep going until no more lines to the file
				dictionary.add(infile.nextLine()); //one word per line, so add the next line
			}
			//close the dictionary file
			infile.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Dictionary file (dict.txt) was not found in the present working directory.");
			System.out.println("Exiting...");
			System.exit(1);
		}

		//Make a 4 x 4 board
		System.out.println("Welcome to Boggle!");
		BoggleModel board = new BoggleModel();
		System.out.println(board.toString());

		//A variable to count the running score.
		int runningScore = 0;

		//Prompt the player to enter words
		System.out.println("Type words below. Type 'Q' to end the game.");
		Scanner keyboard = new Scanner(System.in);
		
		boolean continuePlaying = true;
		ArrayList<String> usedWords = new ArrayList<>();
		while (continuePlaying = true){
			String playerWord = keyboard.nextLine();
			//Does the player want to quit?
			ArrayList<Integer> highScores = new ArrayList<>();
			if (playerWord.equals("q") || playerWord.equals("Q")){
				continuePlaying = false;
				//Check for high score
				try{
					Scanner highScoresFile = new Scanner(new File("highscores.txt"));
					a:
					while (highScoresFile.hasNextLine()){
						try{
							highScores.add(highScoresFile.nextInt());
						}
						catch(Exception e){
							break a;
						}
					}
					highScoresFile.close();
				}
				catch (FileNotFoundException e){
					System.out.println("No highscores.txt file exists.");
					System.exit(1);
				}
				//Tell user their final score.
				System.out.println("Your score is " + runningScore);
				//If it's a top 10 score have them input their intials and write those + score to outfile
				boolean newHighScore = false;
				highscoreloop:
				for (int k =0; k < highScores.size();k++){
					if (runningScore > highScores.get(k)){
						newHighScore = true;
						break highscoreloop;
					}
				}
				if(highScores.size()==0){
					newHighScore = true;
				}

				if (newHighScore == true){
					System.out.println("New top 10 score! Enter your initials:");
					String initials = keyboard.nextLine();
					highScores.add(runningScore);
					if (highScores.size()>10){
						int minIndex = highScores.indexOf(Collections.min(highScores));
						//drop the lowest score
						highScores.remove(minIndex);
					}

					Collections.sort(highScores);
					
					//write the new output file 
					
					try{
						String outFileName = "highscores.txt";
						PrintWriter outputFile = new PrintWriter(outFileName);
						for (int i = highScores.size()-1; i>=0; i--){
							outputFile.println(highScores.get(i));
						}
						outputFile.close();
					}
					catch(FileNotFoundException e){
						System.out.println("No output file found.");
						System.exit(1);
					}
				}
				break;
			}

			else{

				//Check if the player's word is in the dictionary.
				String[] dictArray = new String[dictionary.size()];
				dictArray = dictionary.toArray(dictArray);
				Arrays.sort(dictArray);
				if(Arrays.binarySearch(dictArray, playerWord.toLowerCase()) >=0){
					//System.out.println("word found in dictionary");
					//word was found in the dictionary, now have to check if it's valid given the board.
					boolean validWord = board.wordSearcher(playerWord.toUpperCase());
					if (validWord == true){
						//System.out.print("word found on board");
						//Check if this word has already been guessed. 
						String[] usedWordsArray = new String[usedWords.size()];
						usedWordsArray = usedWords.toArray(usedWordsArray);
						Arrays.sort(usedWordsArray);
						if(usedWords.size()==0){
							usedWords.add(playerWord.toLowerCase());
							int wordScore = scoreWord(playerWord);
							runningScore += wordScore;
							System.out.println("The word " + playerWord + " is worth " + wordScore + " points. Your current score is " + runningScore + ".\nKeep entering words or press Q to end the game:");
						}
						else if(Arrays.binarySearch(usedWordsArray, playerWord.toLowerCase())>=0){
							System.out.println("You've already guessed that word!");
							System.out.println("Keep entering words or press Q to end the game:");
						}
						else{
							usedWords.add(playerWord.toLowerCase());
							int wordScore = scoreWord(playerWord);
							runningScore += wordScore;
							System.out.println("The word " + playerWord + " is worth " + wordScore + " points. Your current score is " + runningScore + ".\nKeep entering words or press Q to end the game:");
						}
						
					}
					else{
						System.out.println("Word not found on board!");
						System.out.println("Keep entering words or press Q to end the game:");
					}
				}
				else{
					//word not found
					System.out.println("That's not a valid word in the Boggle dictionary.");
					System.out.println("Keep entering words or press Q to end the game:");
				}
			}	
		}
		
	}
	public static int scoreWord(String playerWord){
		//scoring method
		int wordScore = 0;
		for (int i=0; i<playerWord.length(); i++){
			char letterToScore = playerWord.toLowerCase().charAt(i);
			if (letterToScore == 'a' ||letterToScore == 'e'||letterToScore == 'i' ||letterToScore == 'l'||letterToScore == 'n'||letterToScore == 'o'||letterToScore == 'r'||letterToScore == 's'||letterToScore == 't'||letterToScore == 'u'){
				wordScore += 1;
			}
			else if (letterToScore == 'd'||letterToScore == 'g'){
				wordScore+=2;
			}
			else if(letterToScore=='b'||letterToScore == 'c'||letterToScore == 'm'||letterToScore == 'p'){
				wordScore += 3;
			}
			else if(letterToScore == 'f'||letterToScore == 'h'||letterToScore == 'v'||letterToScore == 'w'||letterToScore == 'y'){
				wordScore +=4;
			}
			else if(letterToScore == 'k'){
				wordScore += 5;
			}
			else if (letterToScore == 'x' || letterToScore == 'j'){
				wordScore += 8;
			}
			else if (letterToScore == 'q'||letterToScore == 'z'){
				wordScore += 10;
			}
		}
		return wordScore;
	}
}
