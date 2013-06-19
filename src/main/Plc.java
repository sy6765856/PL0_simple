package main;

import java.io.IOException;
import java.io.RandomAccessFile;

import lexical.Lexical;

public class Plc {
	
	public static String[] key_words;
	public static char[] separator; 
	
	/*
	 *	build key words table 
	 */
	public static void init(){
		//key words : sort by abc . good for binary search.
		key_words = new String[]{
				"begin","boolean","call","case","char","const","do","downto","else","end","for","if","integer","odd",
				"of","procedure","program","read","real","repeat","then","to","until","var","while","write"
		};
		//separator : used by read word function
		// + - * / : > < , ( ) = ; \n \t { } ' [ ]
		separator = new char[]{
				'\t','\n','\'','(',')','*','+',',','-','/',':',';','<','=','>'
		};
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length != 1) {
			System.out.println("usage:Plc <pl0 source file path>");
			return;
		}
		Plc.init();
		// read pl0 file
		RandomAccessFile randomFile = null;
		try {
			randomFile = new RandomAccessFile(args[0], "r");
			new Lexical(randomFile);//enter lexical module
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
