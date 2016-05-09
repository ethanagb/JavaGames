public class BoggleModel{
	private char[][] board = new char[4][4]; 

	public BoggleModel(){
		//Assign a random letter to each position in that array
		for (int r = 0; r < 4; r ++){ //iterate over rows
			for (int c=0; c < 4; c++){ //interate over columns
				board[r][c] = randomLetter();
			}
		} 
		System.out.println();
	}

	//Generates a random letter.
	public static char randomLetter(){
		//generates a random letter for the board.
		int randomLetterInt = (int)(Math.random()*26); //there are 26 letters, but I've put them in an array, so 0-25 is better numbering system. 
		char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		return alphabet[randomLetterInt];
	}

	//A toString override to format the board properly. 
	public String toString(){
		//String outString = "";
		StringBuilder boardAsString = new StringBuilder();
		for (int r = 0; r < 4; r ++){ //iterate over rows
				if(r>0){
					boardAsString.append("\n");
				}
			for (int c=0; c < 4; c++){
				boardAsString.append(board[r][c]);
				boardAsString.append(" ");
			}
		}
		return boardAsString.toString();
	}

	//A recursive search to see if the word is on the board
	//note for later - probably easier to pass row col numbers and then call this function in Boggle.java in a double for loop
	//actually did this in the wordOnBoard function
	public boolean wordSearcher(String playerWord, int r, int c){
		boolean wordFound = false; 
		if (playerWord.length() == 0){
			return true;
		}
		if(r<0||r>=4||c<0||c>=4){
			//Catch out of bounds exceptions
			return false;
		}
		if (playerWord.charAt(0)!=board[r][c]){
			//System.out.println("X - R = " + r +" C = " + c + " Char: " + board[r][c] );
			return false;
		}
		
		else {
			char placeholder = board[r][c]; 
			//System.out.println("R = " + r +" C = " + c + " Char: " + board[r][c] );
			board[r][c] = '.'; //this is the marker of where the search happens from
			String wordRemainder = playerWord.substring(1,playerWord.length()); // the first letter was replaced by . so this represents what's left
			wordFound = wordSearcher(wordRemainder,r, c+1) || wordSearcher(wordRemainder,r, c-1) || wordSearcher(wordRemainder,r+1, c+1) || wordSearcher(wordRemainder,r+1, c-1) || wordSearcher(wordRemainder,r+1, c) || wordSearcher(wordRemainder,r-1, c+1) || wordSearcher(wordRemainder,r-1, c-1) || wordSearcher(wordRemainder,r-1, c);
			board[r][c] = placeholder; //restore the board
			
			//System.out.println(wordFound);
			
		}
		return wordFound;
	}

	/*public boolean firstLetterSearch(String playerWord){
		for (int r = 0; r < 4; r++){
			for (int c=0; c < 4; c++){
				if (playerWord.charAt(0)==board[r][c]){
					System.out.println("HeRE");
					return true;
				}
			}		
		}
		return false;
	}*/

	public boolean wordSearcher(String playerWord){
		boolean b = false;
		for (int r = 0; r < 4; r++){
			for (int c=0; c < 4; c++){
				if(wordSearcher(playerWord,r,c)){
					b = true;	
					return true;
				}
			}
		}
		return b;
	}
	
}