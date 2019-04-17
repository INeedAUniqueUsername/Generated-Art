package generation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alphanumerator implements Comparator<String> {
	/*
	public static void main(String[] args) {
		List<String> a = Arrays.asList(new String[] {
				"1a",
				"2a",
				"11a",
				"12a",
				"123a",
				"12134a",
				"abc",
				"abcd",
				"abc1",
				"abc11",
				"abc2",
				"abc123",
				"abc124",
		});
		Collections.sort(a, new Alphanumerator());
		System.out.println(a);
	}
	*/
    public Alphanumerator() {
    }

    public int compare(String s1, String s2) {
    	s1 = s1.toUpperCase();
    	s2 = s2.toUpperCase();
    	int end = Math.max(s1.length(), s2.length());
    	for(int i = 0; i < end;) {
    		char c1 = i < s1.length() ? s1.charAt(i) : Character.MIN_VALUE;
    		char c2 = i < s2.length() ? s2.charAt(i) : Character.MIN_VALUE;
    		
    		
    		if(Character.isDigit(c1) && Character.isDigit(c2)) {
    			int n1 = c1 - '0';
    			int n2 = c2 - '0';
    			i++;
    			
    			
    			for(int j = i; j < s1.length(); j++) {
    				c1 = i < s1.length() ? s1.charAt(j) : 0;
    				if(Character.isDigit(c1)) {
    					n1 = (n1 * 10) + (c1 - '0');
    				}
    			}
    			for(int j = i; j < s2.length(); j++) {
    				c2 = i < s2.length() ? s2.charAt(j) : 0;
    				if(Character.isDigit(c2)) {
    					n2 = (n2 * 10) + (c2 - '0');
    				}
    			}
    			if(n1 < n2) {
    				return -1;
    			} else if(n2 < n1) {
    				return 1;
    			}
    		} else if(c1 < c2) {
    			return -1;
    		} else if(c2 < c1) {
    			return 1;
    		} else {
    			i++;
    		}
    	}
    	return 0;
    }
}