
/*
Tittle 						: Fermat’s Last Theorem Near Misses
Name Of the File 				: FermentLast.java
List of any externel files 			: N/A
List of externel file Creates			: N/A
Combaine Programmers 				: Shivarathri prasad , Benjamin Ayirifa
Programmer Email Address			: Prasadshivarathri@lewisu.edu , benjaminayirifa@lewisu.edu
Course Number 					: 60500
Section Number 					: 004
Date of Program Finished			: 11/10/2022
Explanation of Program 				: Fermat’s Last Theorem is used to calculate the near misses using the formula (x^n + y^n = z^n). 
										  Here n is the natural number whose value should be greater than 2 with a condition k greater than 10. 
										  Using this formula the near miss is calculated between (x^n + y^n), and (z^n) for some integers of x, y and z.
Resources Used for Complete the Program : N/A.

*/
import java.util.Scanner;

public class FermentLast {
	static int k, n;
	static float s_RelativeMiss = Float.MAX_VALUE;
	static float SmallMiss = Float.MAX_VALUE;
	static long smallestMiss = Long.MAX_VALUE;

	public static void main(String[] args) {
		GetInput();
		CombinationTest();
		System.out.println("\nSmall Miss: " + SmallMiss);
	}

	static void GetInput() { // Get user input use scanner
		Scanner inputValue = new Scanner(System.in);

		while (true) {
			System.out.println("\nPlease enter the K Value : k > 10");//Get value from user
			k = inputValue.nextInt();
			if (k > 10)
				break;
			System.out.println("\n You must enter for k value greater than 10");
		}

		while (true) {
			System.out.println("\nPlease enter the N Value : 2 < n < 12 ");//Get n value from user
			n = inputValue.nextInt();
			if (n > 2 && n < 12)
				break;
			System.out.println("You must enter for k value greater than 2 and less than 12:");
		}

	}

	static void CombinationTest() { // test the different x y z combinations

		int x = 10;

		while (x <= k) {

			int y = x;
			while (y <= k) {

				float nearmiss = Float.MAX_VALUE; // nearmiss calculate of x^n + y^n and z^n
				int z = (x + y) / 2;

				if (z > k) {
					z = k;
					PrintResults(x, y, z, RelativeMiss(x, y, z));
					continue;
				}
				for (z = (x + y) / 2; z <= k; z++) {

					float near = RelativeMiss(x, y, z);
					if (near > nearmiss)
						break;

					nearmiss = near;

				}
				z--;

				PrintResults(x, y, z, nearmiss);
				if (x != y)
					PrintResults(y, x, z, nearmiss);
				y++;

			}
			x++;
		}
	}

	// Print the final results
	static void PrintResults(int x, int y, int z, float nearmiss) {
		float relMiss = nearmiss;

		if (s_RelativeMiss > relMiss) {
			s_RelativeMiss = relMiss;
			System.out.println("\nLimit for X Y Z.");
			System.out.println("x:" + x + " y:" + y + " z:" + z);
			System.out.println("Relative Miss : " + (1 + nearmiss));
			Float Relative = 1 + nearmiss;
			if (Relative < SmallMiss) {
				SmallMiss = Relative;
			}
			System.out.println("Miss: " + Miss(x, y, z));

		}

	}

	// x,y,z,n relative miss
	public static float RelativeMiss(int x, int y, int z) {
		double CalcZvalue = Math.pow(z, n);
		double CalcXYvalue = Math.pow(x, n) + Math.pow(y, n);
		return (float) Math.abs(1.0 - CalcXYvalue / CalcZvalue);
	}

	// actual miss from x,y,z,n
	public static long Miss(int x, int y, int z) {
		double CalcZvalue = Math.pow(z, n);
		double CalcXYvalue = Math.pow(x, n) + Math.pow(y, n);
		return (long) (CalcXYvalue - CalcZvalue);
	}

}