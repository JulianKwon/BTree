package bTree;

import java.io.*;
public class MkTree
{
	void read(String input)
	{
		try{
			File aFile = new File(input);
			FileReader fileReader = new FileReader(aFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
			String line = null;
			
			reader.close();
		} catch(Exception ex){
			
		}
	}
	void insert()
	{
		
	}
}
