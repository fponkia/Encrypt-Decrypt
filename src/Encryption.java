/**
 * 
 */

/**
 * @author Foram Patel
 * Date: May 16, 2021
 * Description: This class encrypt or decrypt the letters from the phrases, character by character.
 * Methods: public static boolean isALetter (char character): Checks and return true if the character passed is letter, else it will return false.
 * 	public static char encode (char letter, int encryptKey): Takes character and a encryption Key value as a parameter and returns the encoded character.
 * 	public static char decode (char letter, int decryptKey): Takes character and a encryption Key value as a parameter and returns the decoded character.
 * 	public static String encryptdecrypt(String phrase, int encryptKey, char command): encrypt or decrypt the phrase inputed, and returns the new phrase.
 * 	public static void main(String[] args): A self testing method, to see that all the methods written above are working properly or not.
 *
 */
public class Encryption {
	
	//returns true if the character passed is letter(means A to Z or a to z, etc.) else returns false.
	public static boolean isALetter (char character) {
		if(Character.isLetter(character)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//takes the character and the encryption key and returns the encrypted character
	public static char encode (char letter, int encryptKey) {
		
		/**
		 * Checks whether the passed character is Uppercase letter or not.
		 * If yes then determines the difference between the passed character and A.
		 * Then the difference is added to the encryption key passed as parameter. 
		 * Modulus 26 is added so the added value does not get over 26. And starts from 0 again when the added number gets more than 26.
		 * If the added value is negative. It adds 26 to the number. 
		 * So, the new answer got from the calculation determines on how many positions the character has to jump from A to reach the encrypted value.
		 * Returns the encrypted character
		 * Same explanation goes to the else part as well, for the character that is in lowercase.
		 */
		if(Character.isUpperCase(letter)) {
			int difference = letter - 'A';
			int newLetter = (difference + encryptKey)%26;
			if(newLetter <0) {
				newLetter = 26 + newLetter;
			}
			char newCharacter = (char) ('A' + newLetter);
			return newCharacter;
		}
		else {
			int difference = letter - 'a';
			int newLetter = (difference + encryptKey)%26;
			if(newLetter <0) {
				newLetter = 26 + newLetter;
			}
			char newCharacter = (char) ('a' + newLetter);
			return newCharacter;
		}
	}
	
	/**
	 * Takes the character and the decryption key and returns the decrypted character.
	 * The parameter are then passed on to the method encode, to get the decrypted character.
	 * The decryption key is changed in such as way that the changed value is same to the encryption key needed to get the same character.
	 * For example. The decryption key 3 is similar to the encytption key 23. They both returns the same character.
	 * Because the positive decryption key goes backward and positive encryption key goes forward and vice-versa for negative value of the key.
	 */
	public static char decode (char letter, int decryptKey) {
		return encode(letter, (26-(decryptKey)));
		}
	
	public static String encryptdecrypt(String phrase, int encryptKey, char command) {
		String newphrase = "";

		for(int j=0;j<phrase.length();j++) {
			char character = phrase.charAt(j);	//extracting the character from the phrase
			boolean alphabet = Encryption.isALetter(character);	//checking whether a character of a given phrase is letter or not.

			//if the given character of a phrase is a letter than the character get encrypted or decrypted, based on the command given by user.
			if(alphabet == true) {
				if(command =='E' || command =='e') {
					char processedchar = Encryption.encode(character, encryptKey);
					newphrase = newphrase + processedchar;	//encrypted character gets added to the String newphrase.
				}
				else {
					char processedchar = Encryption.decode(character, encryptKey);
					newphrase = newphrase + processedchar;	//decrypted character gets added to the String newphrase.
				}
			}

			else {
				newphrase = newphrase + character;	//character other than a letter gets added to the String newphrase as it is.
			}
		}
		return newphrase;
	}

	/**
	 * @param args
	 * A self testing main method
	 */
	public static void main(String[] args) {
		
		//declaring and initializing various variables.
		String phrase="Well, all 2567 spies liked the encrypting program.";
		String phrase2 = "Ckrr, grr 2567 yvoky roqkj znk ktixevzotm vxumxgs.";
		String encoded="", decoded ="";
		int Key = 6;
		
		//stores the encoded or decoded phrases
		encoded = Encryption.encryptdecrypt(phrase, Key, 'e');
		decoded = Encryption.encryptdecrypt(phrase2, Key, 'd');
		
		//prints both encrypted and decrypted phrase.
		System.out.println(encoded);
		System.out.println(decoded);
	}

}
