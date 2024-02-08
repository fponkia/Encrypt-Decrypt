import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author Foram Patel
 * Date: May 9, 2021
 * Description: This is the loader class which reads and writes to files
 *
 *Method List:
 *
 *Load(String fileName) // Method to load contents of a txt file into a array and return it
 *
 *storeinFile (String linecoded[], String output) // method to take the contents of an array and write it into a txt file
 */

public class Loader {

	public static String [] Load(String fileName) throws IOException {

		String line="";
		int lineCount = 0;
		
		//Opening the file to read
		FileReader fr = new FileReader(fileName);
		BufferedReader input = new BufferedReader(fr);

		line = input.readLine();
		
		// Keep reading the file and counting the lines until a 'EOF' is found
		while (!line.equals("EOF"))		
		{
			lineCount ++;
			line = input.readLine();
		}

		//Closing the opened file
		input.close();
		
		
		BufferedReader input1 = new BufferedReader(new FileReader(fileName));
		String phrases [] = new String [lineCount];
		
		for(int i=0;i<phrases.length;i++) {
			phrases[i] = input1.readLine();
		}

		input1.close();//close the file stream
		return phrases;//returns the contents of the array
	}
	
	public static void storeinFile (String linecoded[], String output) throws IOException{
		//Opening a file to write into
		PrintWriter fw = new PrintWriter(new FileWriter(output));
		
		//Storing each analyzed phrases in the new file
		for(int i=0;i<linecoded.length;i++) {
			fw.println(linecoded[i]);
		}
		
		fw.println("EOF");
		
		//Closing the file to save
		fw.close();
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String output [];

		output = Loader.Load("Phrase.txt");

		//display the contents of the output array
		for(int i=0;i<output.length;i++) {
			System.out.println(output[i]);

		}

	}
}
