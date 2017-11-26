package ir.prog1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.tartarus.snowball.ext.PorterStemmer;

//This program is used to stem all the text documents in the given folder

public class Stemmer {
		public static void main(String[] args) throws IOException
	   {
			/**
			 * "BufferedReader" is used Reads text from a character-input stream, 
			 * buffering characters so as to provide for the efficient reading of characters,
			 * arrays, and lines. 
			 * "System.in" is used to access to externally defined properties and 
			 * environment variables; a means of loading files and libraries; 
			 * and a utility method for quickly copying a portion of an array.
			 * @param str String
             * @return
			 */
			BufferedReader findTestDataPath=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter the path of Dataset: ");
			// docsPath contains the given path
			String docsPath = findTestDataPath.readLine();
			final Path docDir = Paths.get(docsPath);
	 
			if (!Files.isReadable(docDir)) 
			{
			  System.out.println("This path " +docDir.toAbsolutePath()+ " doesn't exist.");
			  System.exit(1);
			}
			
			File folder = new File(docsPath);
			File[] listOfFiles = folder.listFiles();
			
			for (int i = 0; i < listOfFiles.length; i++) 
			{
				File file = listOfFiles[i];
				if (file.isFile() && file.getName().endsWith(".txt")) 
		        {
					char[] w = new char[501];
					/**
					 * PorterStemmer() is the predefined class.
					 */
					PorterStemmer s = new PorterStemmer();
					try
					{
						//FileInputStream class obtains input bytes from a file.
						FileInputStream in = new FileInputStream(file);
						try
						{ 
							while(true)
							{  
					        	  int ch = in.read();
					              if (Character.isLetter((char) ch))
					              {
					                 int j = 0;
					                 while(true)
					                 {  ch = Character.toLowerCase((char) ch);
					                    w[j] = (char) ch;
					                    if (j < 500) j++;
					                    ch = in.read();
					                    if (!Character.isLetter((char) ch))
					                    {
					                       s.setCurrent(w, j);
					                       s.stem();
					                       System.out.println(s.getCurrent());
					                       break;
					                    }
					                 }
					              }
					              if (ch < 0) break;
					              System.out.print((char)ch);
					         }
	         }
	         catch (IOException e)
	         {  System.out.println("error reading " + args[i]);
	            break;
	         }
	      }
	      catch (FileNotFoundException e)
	      {  System.out.println("file " + args[i] + " not found");
	         break;
	      }
	   }
	}
  }
}
