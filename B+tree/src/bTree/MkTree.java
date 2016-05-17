package bTree;

import java.io.*;

public class MkTree extends Modify
{
	void read(String input)
	{
		try
		{
			File aFile = new File(input);
			FileReader fileReader = new FileReader(aFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			initialize();
			while ((line = reader.readLine()) != null)
				// read line then insert number
				insert(Integer.parseInt(line));
			reader.close();
		} catch (Exception ex)
		{
		}
	}
}