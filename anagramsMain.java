package isu.edu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class anagramsMain {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter 6 letters: ");
		String ogletters = input.nextLine();
		
		String[] letters = new String[6];
		letters[0] = ogletters.substring(0,1);
		letters[1] = ogletters.substring(1,2);
		letters[2] = ogletters.substring(2,3);
		letters[3] = ogletters.substring(3,4);
		letters[4] = ogletters.substring(4,5);
		letters[5] = ogletters.substring(5,6);
		
		sixLetterWords(ogletters);
		fiveLetterWords(letters);
		fourLetterWords(letters);
		threeLetterWords(letters);
	}
	
	
	public static void sixLetterWords(String ogletters) {
		String letterNum = countLetters(ogletters);
		
		callFile("sixLetterWords.txt", letterNum);
	}
	
	
	public static void fiveLetterWords(String[] letters) {
		String letterNum = "";
		String letterz = "";
		for(int i = 0; i < 6; i++) {
			letterz += letters[0];
			letterz += letters[1];
			letterz += letters[2];
			letterz += letters[3];
			letterz += letters[4];
			
			letterNum = countLetters(letterz);
			
			callFile("fiveLetterWords.txt", letterNum);
			
//			Rotates array
			String temp = letters[0];
			for(int l = 0; l < 5; l++) {
				letters[l] = letters[l + 1];
			}
			letters[5] = temp;
			letterNum = "";
			letterz = "";
		}
	}

	
	public static void fourLetterWords(String[] letters) {
		String letterNum = "";
		String letterz = "";
		for(int i = 0; i < 5; i++) {
			for(int j = i + 1; j < 6; j++) {
				for(int k = 0; k < 6; k++) {
					if(k != i && k != j) {
						letterz += letters[k];
					}
				}
				
				letterNum = countLetters(letterz);
				
				callFile("fourLetterWords.txt", letterNum);
				
				letterNum = "";
				letterz = "";
			}
		}
	}

	
	public static void threeLetterWords(String[] letters) {
		String letterNum = "";
		String letterz = "";
		for(int i = 0; i < 4; i++) {
			for(int j = i + 1; j < 5; j++) {
				for(int a = j + 1; a < 6; a++) {
					for(int k = 0; k < 6; k++) {
						if(k != i && k != j && k != a) {
							letterz += letters[k];
						}
					}
				
					letterNum = countLetters(letterz);
				
					callFile("threeLetterWords.txt", letterNum);
				
					letterNum = "";
					letterz = "";
				}
			}
		}
	}
	
	
	public static void callFile(String fileName, String letterNum) {
		try {
			Scanner fileReader = new Scanner(new File(fileName));
		
			while(fileReader.hasNext()) {	//Reads the .txt file line by line
				String line = fileReader.nextLine();
				Scanner input = new Scanner(line);
				input.useDelimiter(" ");
				String currWord;
				String freshWordNum;
				
				while(input.hasNext()) {
					currWord = input.next();
					freshWordNum = countLetters(currWord);
					if(freshWordNum.equals(letterNum)) {
						System.out.println(currWord);
					}
				}
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String countLetters(String letters) {
		int[] letterArray = new int[26];
		String countNum = "";
		int index = 0;
		for(int i = 0; i < letters.length(); i++) {
			index = letters.substring(i, i + 1).compareTo("a");
			letterArray[index]++;
		}
		
		for(int i = 0; i < 26; i++) {
			countNum += Integer.toString(letterArray[i]);
		}
		
		return countNum;
	}
}
