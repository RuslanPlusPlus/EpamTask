package by.me.model;

import java.util.Random;

public class NumberUtil {

	public static boolean allDigitsUnique(String s) {
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isInvalidInput(String input) {
		if (input.length() != 4)
			return true;
		try {
			int test = Integer.parseInt(input);
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid input");
			return true;
		}
		return false;
	}
	
	public static String generateStringNumber() {
		Random rnd = new Random();
		int a;
		a = 1000 + rnd.nextInt(10000 - 1000);
		String s = Integer.toString(a);
		boolean t = allDigitsUnique(s);
		while(!t) {
		    a = 1000 + rnd.nextInt(10000 - 1000);
			s = Integer.toString(a);
			t = allDigitsUnique(s);
		}
		return s;
	}
}
