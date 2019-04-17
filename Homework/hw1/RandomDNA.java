/* C343 Summer 2018
 * Lab mini-assignment 01
 * Ian Polito
 * ipolito
 */

import java.util.*;

public class RandomDNA {
	
	//returns a random DNA sequence of length 42 as a string
	public String getDNA() {
		String result = "";
		Random rand = new Random();
		for (int i = 0; i < 42; i++) {
			int temp = rand.nextInt(4);
			switch (temp) {
				case 0:
					result += "A";
					break;
				case 1:
					result += "T";
					break;
				case 2:
					result += "C";
					break;
				case 3:
					result += "G";
					break;
			}
		}
		return result;
	}
	
	//returns a random DNA sequence of length size as a string
	public String getDNA(int size) {
		String result = "";
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			int temp = rand.nextInt(4);
			switch (temp) {
				case 0:
					result += "A";
					break;
				case 1:
					result += "T";
					break;
				case 2:
					result += "C";
					break;
				case 3:
					result += "G";
					break;
			}
		}
		return result;
	}
}
