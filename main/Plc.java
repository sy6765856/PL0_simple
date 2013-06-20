package main;

import java.io.IOException;
import java.io.RandomAccessFile;

import lexical.Lexical;

public class Plc {
	
	public static String[] key_words;
	public static char[] separator; 
	public static void init()
    {
		key_words = new String[]{
				"begin","boolean","call","case","char","const","do","downto","else","end","for","if","integer","odd",
				"of","procedure","program","read","real","repeat","then","to","until","var","while","write"
		};
		separator = new char[]{'\t','\n','\'','(',')','*','+',',','-','/',':',';','<','=','>'};
	}
	public static void main(String[] args)
    {
		if (args.length != 1)
        {
			System.out.println("Please input source_file name!");
			return;
		}
        System.out.println("Success!");
		Plc.init();
		RandomAccessFile randomFile = null;
		try
        {
			randomFile = new RandomAccessFile(args[0], "r");
			new Lexical(randomFile);
		} catch (IOException e) {e.printStackTrace();
		} catch (Exception e) {e.printStackTrace();}
        finally {
			if (randomFile != null) {
				try
                {
					randomFile.close();
				} catch (Exception e) {e.printStackTrace();}
			}
		}
	}

}
