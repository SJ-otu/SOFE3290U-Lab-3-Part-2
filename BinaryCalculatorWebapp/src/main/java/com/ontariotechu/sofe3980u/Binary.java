package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		// uncomment the following code
		if (this.number.isEmpty()) { // replace empty strings with a single zero
			this.number = "0";
		}
	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}
	
	// Design (the 3 functions)
	
	/**
	* OR'ing two binary variables.
	*
	* @param num1 The first Binary object number that will be AND'd
	* @param num2 The second Binary object number that will be AND'd
	* @return A binary variable with a value of <i>num1 OR num2</i>.
	*/
	public static Binary or(Binary num1, Binary num2) {
		String b1 = num1.getValue();
		String b2 = num2.getValue();
		int len1 = b1.length();
		int len2 = b2.length();
		// pad the shorter string with leading 0's
		if (len1 < len2) {
			String zeros = "";
			for (int i = 0; i < len2 - len1; i++) { zeros += "0"; }
			b1 = zeros + b1;
		} else if (len2 < len1) {
			String zeros = "";
			for (int i = 0; i < len1 - len2; i++) { zeros += "0"; }
			b2 = zeros + b2;
		}
		String result = "";
		// iterate bits left to right and do OR bit by bit
		for (int i = 0; i < b1.length(); i++) {
			char bit1 = b1.charAt(i);
			char bit2 = b2.charAt(i);
			if (bit1 == '1' || bit2 == '1') { result += '1'; }
			else { result += '0'; }
		}
		return new Binary(result);
	}
	/**
	* AND'ing two binary variables.
	*
	* @param num1 The first Binary object number that will be AND'd
	* @param num2 The second Binary object number that will be AND'd
	* @return A binary variable with a value of <i>num1 AND num2</i>.
	*/
	public static Binary and(Binary num1, Binary num2) {
		String b1 = num1.getValue();
		String b2 = num2.getValue();
		int len1 = b1.length();
		int len2 = b2.length();
		// pad the shorter string with leading 0's
		if (len1 < len2) {
			String zeros = "";
			for (int i = 0; i < len2 - len1; i++){ zeros += "0"; }
			b1 = zeros + b1;
		} else if (len2 < len1) {
			String zeros = "";
			for (int i = 0; i < len1 - len2; i++){ zeros += "0"; }
			b2 = zeros + b2;
		}
		String result = "";
		// iterate bits left to right and do AND bit by bit
		for (int i = 0; i < b1.length(); i++) {
			char bit1 = b1.charAt(i);
			char bit2 = b2.charAt(i);
			if (bit1 == '1' && bit2 == '1') { result += '1'; }
			else { result += '0'; }
		}
		return new Binary(result);
	}
	/**
	* Multiplying two binary variables.
	*
	* @param num1 The Binary object number that will be multiplied
	* @param num2 The Binary object number that num1 will be multiplied by
	* @return A binary variable with a value of <i>num1 * num2</i>.
	*/
	public static Binary multiply(Binary num1,Binary num2)
	{
		String b2 = num2.getValue();
		Binary result = new Binary("0");
		int shift = 0;
		// iterate from bits from right to left
		for (int i = b2.length() - 1; i >= 0; i--) {
			if (b2.charAt(i) == '1') {
				// shift num1 left by `shift` bits
				String shifted = num1.getValue();
				for (int j = 0; j < shift; j++){ shifted += "0"; }
				result = Binary.add(result, new Binary(shifted));
			}
			shift++;
		}
		return result;
	}

}	
