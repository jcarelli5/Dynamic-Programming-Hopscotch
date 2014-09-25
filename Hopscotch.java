// Jonathan Carelli
// COP 3503-0001
// Assignment #6 - Dynamic Programming Hopscotch
// 4/12/2014

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hopscotch {

	public static void main(String[] args) throws FileNotFoundException {
		
		File input = new File("hopscotch.in");
		Scanner sc = new Scanner(input);
		
		int numCases = sc.nextInt();
		int result = 0;
		
		for(int i = 0; i < numCases; i++ ){
			int n = sc.nextInt();
			int[] array = new int[n+1];
			result = findBestScore(array);
			System.out.println("Game #" + (i+1) + ": " + result);
		}
		
		sc.close();
	}
	
	// This algorithm, similar to the CoinRow problem algorithm, will use a dynamic programming approach to find the smallest score possible using the assignments restrictions.
	// Input: F[0..n]
	// Output: Smallest score possible
	public static int findBestScore(int[] F){
			
			int MAX = 2147483647;
			int ZERO = 0;
			int n_1, n_2, n_3, n_4;
			int score_1, score_2, score_3, score_4;
			int number = 0;
			int minArray[] = new int[4];
		
			for(int i = 1; i < F.length; i++){
				n_1 = 1;
				n_2 = ZERO;
				n_3 = ZERO;
				n_4 = ZERO;
				
				score_1 = 1;
				score_2 = MAX;
				score_3 = MAX;
				score_4 = MAX;
				
				// Separates the integer into single digits
				number = i;
			    List<Integer> digits = new ArrayList<Integer>();
			    while(number > 0) {
			        digits.add(number % 10);
			        number /= 10;
			    }
				
			    // Checks if the index is prime
			    if(isPrime(i) == true){
			    	n_2 = digits.get(0);
			    	score_2 = 3;
			    }
				
			    // Checks if the index is a multiple of 11
			    if(isMultipleOf11(i) == true){
			    	n_3 = sumOfDigits(digits);
			    	score_3 = 4;
			    }
			    
			    // Checks if the index is a multiple of 7
			    if(isMultipleOf7(i) == true){
			    	n_4 = 4;
			    	score_4 = 2;
			    }

			    // This is where the algorithm determines what the smallest move should be.
			    minArray[0] = (F[i - n_1] + score_1);
			    minArray[1] = (F[i - n_2] + score_2);
			    minArray[2] = (F[i - n_3] + score_3);
			    minArray[3] = (F[i - n_4] + score_4);
			    
			    // Finds the minimum of the four values in the array.
			    int min = MAX;
			    for(int j = 0; j < minArray.length; j++){
			    	if(min > minArray[j]){
			    		min = minArray[j];
			    	}
			    }
			    
			    // Setting the value correctly in index i to the minimum value.
			    F[i] = min; 
			}

			return F[F.length-1];
	}
	
	// This sums up all of the digits for the multiple of 11 operation
	public static int sumOfDigits (List<Integer> digits){
		
		int retVal = 0;
		for(int i = 0; i < digits.size(); i++){
			
			retVal += digits.get(i);
		}
		return retVal;
	}
	
	// Returns true if the given integer that is greater then 10 is a prime number.
	public static boolean isPrime (int n){
		
		if(n <= 10){
			return false;
		}

	    for (int i = 2; i <= n / 2; i++) {
	        if (n % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	// Returns true if the given integer is a multiple of 11.
	public static boolean isMultipleOf11 (int n) {
		if(n % 11 == 0){
			return true;
		}
		return false;
	}
	
	// Returns true if the given integer is a multiple of 7.
	public static boolean isMultipleOf7 (int n) {
		if(n % 7 == 0){
			return true;
		}
		return false;
	}
}
