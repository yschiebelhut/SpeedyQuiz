package misc;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class InputHelper {

	public static double getDoubleInput(String output) {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println(output);
				return sc.nextDouble();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid value of type double.");
			} catch (Exception a) {
				System.out.println("different error");
			}

		}
	}

	//This Integer input method just checks if the input is a valid integer
	public static int getIntegerInput() {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				return sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid value of type int.");
			} catch (Exception a) {
				System.out.println("different error");
			}
		}
	}

	//This Integer input method just checks if the input is a valid integer and provides a descriptive string
	public static int getIntegerInput(String output) {
		while (true) {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println(output);
				return sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid value of type int.");
			} catch (Exception a) {
				System.out.println("different error");
			}
		}
	}

	//This Integer input method just checks if the input is in a special range of integer
	public static int getIntegerInput(String output, int lowerBorder, int upperBorder) {
		int input = upperBorder + 1;

		do {
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println(output);
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid value of type int.");
			} catch (Exception a) {
				System.out.println("different error");
			}
		} while (input < lowerBorder || input > upperBorder);

		return input;
	}

	public static String getStringInput(String output) {
		Scanner sc = new Scanner(System.in);
		System.out.println(output);
		return sc.next();
	}

	/**
	 * Returns a random integer between 0 (inclusive) and upperBound (inclusive)
	 *
	 * @param upperBound upper bound of random number (inclusive)
	 * @return random integer between 0 and upperBound (inclusive)
	 */
	public static int getRandomInt(int upperBound) {
		Random rand = new Random();
		return rand.nextInt(upperBound + 1);
	}

	/**
	 * Returns a random integer between lowerBound and upperBound (inclusive)
	 *
	 * @param lowerBound lower bound of random number (inclusive)
	 * @param upperBound upper bound of random number (inclusive)
	 * @return random integer between lowerBound and upperBound (inclusive)
	 */
	public static int getRandomInt(int lowerBound, int upperBound) {
		Random rand = new Random();
		return rand.nextInt(upperBound + 1 - lowerBound) + lowerBound;
	}
}
