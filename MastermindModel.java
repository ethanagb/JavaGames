class MastermindModel{
	private int[] solutionArray = new int[4]; //an integer solution array
	private char[] colorSolutionArray = new char[4]; //a char solution array (colors)
	
	public MastermindModel(){
		solutionArray[1] = (int)(Math.random()*6+1);
		solutionArray[2] = (int)(Math.random()*6+1);
		solutionArray[3] = (int)(Math.random()*6+1);
		solutionArray[0] = (int)(Math.random()*6+1);
		colorSolutionArray[0] = getColorAt(0);
		colorSolutionArray[1] = getColorAt(1);
		colorSolutionArray[2] = getColorAt(2);
		colorSolutionArray[3] = getColorAt(3);
	}
	public char[] getSolution(){
		return colorSolutionArray;
	}
	public char getColorAt(int i){
		char color='x'; //variable initializd to x as it doesn't corrrespond to any color.
		if (solutionArray[i]==1){
			color = 'r' ;//red
		}
		else if (solutionArray[i]==2){
			color = 'o'; //orange
		}
		else if (solutionArray[i]==3){
			color = 'y'; //yellow
		}
		else if (solutionArray[i]==4){
			color = 'g'; //green
		}
		else if (solutionArray[i]==5){
			color = 'b'; //blue
		}
		else if (solutionArray[i] ==6){
			color = 'p'; //purple
		}	
		return color;
	}
}