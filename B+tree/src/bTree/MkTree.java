package bTree;

import java.io.*;

public class MkTree
{
	void read(String input)
	{
		try
		{
			File aFile = new File(input);
			FileReader fileReader = new FileReader(aFile);
			BufferedReader reader = new BufferedReader(fileReader);
			Modify mo = new Modify();
			String line = null;
			while ((line = reader.readLine()) != null)
				mo.insert(Integer.parseInt(line));
			reader.close();
		} catch (Exception ex)
		{
		}
	}
}
