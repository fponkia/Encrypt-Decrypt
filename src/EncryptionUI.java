import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * 
 */

/**
 * @author Foram Patel
 * Date: May 16, 2021
 * Description: This is the User Interface class for the Summative project - The Super Spy.
 * Methods: public static boolean checkKey (int encryptKey): checks whether the value of the encryption Key is within the accepting range or not. Returns true if in range else false.
 * 	public static int putKeyInRange(int encryptKey): converts and return the value of the encryption Key between 0 to 26, inclusive
 * 	public static void storeNdisplay(String array[], String fileName) throws IOException: stores the new phrase(s) in the new file and displays it as well.
 * 	public static void main(String[] args) throws IOException: This method communicates with the user and performs all the necessary steps that are required to fulfill the user needs.
 *
 */
public class EncryptionUI {

	//checks whether the value of encryption Key is within the accepting range or not.
	public static boolean checkKey (int encryptKey) {
		if(encryptKey<=32768 && encryptKey>=-32767) {
			return true;	//returns true if value is within the accepting range.
		}
		else {
			return false;
		}
	}

	public static int putKeyInRange(int encryptKey) {
		return encryptKey%26;	//returns the value that is between the range of 0 to 26, inclusive.
	}

	public static void storeNdisplay(String array[], String fileName) throws IOException {
		Loader.storeinFile(array, fileName);	//store the encrypted or decrypted phrases in the new file
		String output = "", outputarray[];
		outputarray = Loader.Load(fileName);	//reads and load each line of the new file

		for(int i=1;i<outputarray.length;i++) {
			output = output + outputarray[i] + "\n";	//only store the encoded or decoded phrases from the file
		}

		//displays only the encoded or decoded phrases, that were loaded from the file.
		JTextArea area = new JTextArea();
		area.setText(output);
		area.setEditable(false);
		JOptionPane.showMessageDialog(null, area);
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String phrase = "", newphrase = "", fileName="", ask ="";
		String codedarray[] = null;
		boolean index = true;
		char command = ' ';
		int encryptKey = 0;

		try {
			do {
				//user enters if he wants to encrypt or decrypt the phrase manually or takes the phrases from a file
				ask = JOptionPane.showInputDialog(null, "Enter 'Live' to perform the encryption or decryption manually\n"
						+ "Enter 'File' to perform the encryption or decryption from file","File");

				//checks whether the user entered the correct word or not.
				if(ask.equalsIgnoreCase("file")||ask.equalsIgnoreCase("live")) {
					command = JOptionPane.showInputDialog(null, "Please Enter 'E' to encypt the file or\nEnter 'D' to decrypt the file.").charAt(0);

					//checks whether the user entered the correct character or not
					if(command=='e'||command=='E'||command=='d'||command=='D') {

						if(ask.equalsIgnoreCase("File")) {

							//ask for the file name from the user that he wants to encrypt or decrypt
							fileName = JOptionPane.showInputDialog(null, "Please Enter the file to import the phrases from.", "Phrase.txt");
							String recordarray[];

							recordarray = Loader.Load(fileName);	//stores lines of the file in an array
							encryptKey = Integer.parseInt(recordarray[0]);	//stores the value of encryptionKey
							boolean range = EncryptionUI.checkKey(encryptKey);	//returns the answer whether the encryptionKey value is in accepted range or not
							index = false;	//makes the program to run for one time only

							if(range == true) {
								codedarray = new String[recordarray.length];
								codedarray[0] = recordarray[0];	//stores the encryption value at the index of 0, in the new array
								encryptKey = EncryptionUI.putKeyInRange(encryptKey);	//get the value of the encryptKey between the range of 0 to 26.

								//extract each phrases from the file and encrypt or decrypt as per the user enters and store it in new array
								for(int i=1;i<recordarray.length;i++) {
									phrase = recordarray[i];
									newphrase = Encryption.encryptdecrypt(phrase, encryptKey, command);//stores encoded or decoded phrases.
									codedarray[i]=newphrase;	//stores the coded phrases into the new array
									newphrase = "";		//reinitializing the String newphrase to empty.
								}

								//checks whether the user entered to encrypt or decrypt
								//Since the encrypted phrases and decrypted phrases get stored into different files.
								//commands to call a method to store and display the file that has stored the encrypted or decrypted phrases.
								if(command == 'e'|| command =='E') {
									EncryptionUI.storeNdisplay(codedarray, "Output.txt");
								}
								else {
									EncryptionUI.storeNdisplay(codedarray, "TextPhrase.txt");
								}
							}

							//gets displayed when the value of encryption Key is out of the accepting range
							else {
								JOptionPane.showMessageDialog(null, "Value for encryption Key is out of Accepting range");
							}

						}

						//gets performed when the user wishes to enter the phrase manually and get the encrypted or decrypted phrase
						//also the new phrase gets displayed and stored in a new file.
						else if(ask.equalsIgnoreCase("live")) {

							index = false;	//makes sure that a program runs for only one time.
							phrase = JOptionPane.showInputDialog(null, "Enter the line to get encoded or decoded");	//stores the phrase that the user enters
							encryptKey = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the value of Encryption"));	//stores the value of encryption Key
							boolean range = EncryptionUI.checkKey(encryptKey); //checks whether the Key entered is in accepting range or not.

							//only gets run when the entered value for encryption Key is in accepting range
							if(range == true) {
								codedarray = new String[2];	//declaring the size of the array
								codedarray[0] = "" + encryptKey;	//stores the value of encryption Key in the index 0 of the array
								encryptKey = EncryptionUI.putKeyInRange(encryptKey);	//converts the value of the encryption Key between 0 to 26, inclusive.
								newphrase = Encryption.encryptdecrypt(phrase, encryptKey, command);	//stores the encrypted or decrypted phrase
								codedarray[1] = newphrase;	//stores new phrase in the array

								//call the method to store the new phrases in the new file and displays it as well.
								if(command == 'e'|| command =='E') {
									EncryptionUI.storeNdisplay(codedarray, "Output.txt");
								}
								else {
									EncryptionUI.storeNdisplay(codedarray, "TextPhrase.txt");
								}
							}

							//gets printed if the entered value for encryption Key is not in accepting range.
							else {
								JOptionPane.showMessageDialog(null, "Value for encryption Key is out of Accepting range");
							}
						}

					}

					//gets displayed when the user entered any other character other than 'E' or 'D'.
					else {
						JOptionPane.showMessageDialog(null, "Please Enter 'E' or 'D'\nApologize, but you need to start from first.");
					}
				}

				//gets displayed when the user entered and other word other than 'File' or 'Live'.
				else {
					JOptionPane.showMessageDialog(null, "Please enter either 'file' or 'live'");
				}

			}while(index == true);	//loops gets run for infinite times until and unless the value of index turns to false.
		}

		//all the possible errors that can get into attention when running the program
		catch(FileNotFoundException error) {
			JOptionPane.showMessageDialog(null, "Error! File "+ fileName+ " Not Found.");
		}
		catch(NumberFormatException error) {
			if(ask.equalsIgnoreCase("File")) {
				JOptionPane.showMessageDialog(null, "Error! File corruption.");
			}
			else {
				if(!Character.isDigit(encryptKey)) {	//gets displayed only when the encryption Value in the live mode is not integer
					JOptionPane.showMessageDialog(null,"Error! You haven't written integer value in the box of Encryption Value\nOnly Integer Value is accepted (no decimal or String).");
				}
				else {
					JOptionPane.showMessageDialog(null, "Error! character box of entering 'E' or 'D' is empty.");
				}
			}
		}
		catch(StringIndexOutOfBoundsException error) {
			JOptionPane.showMessageDialog(null, "Error! character box of entering 'E' or 'D' is empty.");
		}
		catch(NullPointerException error) {
			if(fileName == ""||fileName == null) {
				JOptionPane.showMessageDialog(null, "You have clicked the cancel button :(");
			}
			else {
				JOptionPane.showMessageDialog(null, "The file "+ fileName +" is empty");
			}
		}
		catch(Exception error) {
			JOptionPane.showMessageDialog(null, "Unkown Error!");
		}
	}
}