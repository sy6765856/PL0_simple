package lexical;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import parse.Parsing;
import main.Plc;
public class Lexical
{
	private HashMap<String, Symbol> symbol_tb;
	private RandomAccessFile randomFile; // file stream
	private StringBuffer wordbuf;
	public int line;
	private HashMap<String, ArrayList<String>> funArgs;
	public boolean isChar;

	public Lexical(RandomAccessFile randomFile)
    {
		symbol_tb = new HashMap<String, Symbol>();
		funArgs = new HashMap<String, ArrayList<String>>();
		this.randomFile = randomFile;
		line = 0;
		this.isChar = false;
		new Parsing(this);
	}

	public void addFuncArgs(String funcname, ArrayList<String> args) {
		this.funArgs.put(funcname, args);
	}

	public ArrayList<String> getFuncArgs(String funcname) {
		return this.funArgs.get(funcname);
	}

	public RandomAccessFile getRandomFile() {
		return randomFile;
	}

	public void setRandomFile(RandomAccessFile randomFile) {
		this.randomFile = randomFile;
	}

	public StringBuffer getWordbuf() {
		return wordbuf;
	}

	public void setWordbuf(StringBuffer wordbuf) {
		this.wordbuf = wordbuf;
	}

	/*
	 * read next word from pl0 source file
	 */
	public String readWord() {
		// read a char once a time . add it to wordbuf .
		// if the char is ' '|','|';'|'('|')' ...break read.
		byte tmp;
		boolean p = false;
		wordbuf = new StringBuffer();
		while (true) {
			try {
				tmp = randomFile.readByte();
				if (((char) tmp) == ' ') {
					if (wordbuf.length() != 0) {
						break;
					} else {
						continue;// ignore space
					}
				}
				// if comments?
				if ((char) tmp == '/') {
					tmp = randomFile.readByte();
					if (tmp == '/') {
						// ignore this line
						randomFile.readLine();
						line++;
						if (wordbuf.length() == 0) {
							continue;
						} else {
							break;
						}
					} else if (tmp == '*') {
						// /* ... */
						tmp = randomFile.readByte();
						while (true) {
							if (tmp == '\n') {
								line++;
							}
							if (tmp == '*') {
								tmp = randomFile.readByte();
								if (tmp == '/') {
									break;
								} else {
									continue;
								}
							}
							tmp = randomFile.readByte();
						}
						continue;
					} else { // not comment
						randomFile.seek(randomFile.getFilePointer() - 1);
						tmp = '/';
					}
				}
				if (isSeparator((char) tmp)) {
					if (wordbuf.length() > 1) {
						randomFile.seek(randomFile.getFilePointer() - 1);
						break;
					} else if (wordbuf.length() == 1) {
						if (p) {
							if (tmp == '=' || tmp == '>') {
								wordbuf.append((char) tmp);
							} else {
								randomFile
										.seek(randomFile.getFilePointer() - 1);
							}
						} else {
							randomFile.seek(randomFile.getFilePointer() - 1);
						}
						break;
					} else {
						p = true;
						if (tmp == '\n' || tmp == '\t' || tmp == '\'') {
							line++;
							p = false;
						} else {
							wordbuf.append((char) tmp);
						}
						continue;
					}
				}
				if (p) {
					randomFile.seek(randomFile.getFilePointer() - 1);
					break;
				}
				wordbuf.append((char) tmp);
			} catch (Exception e) {
				return null;
			}
		}
		return wordbuf.toString();
	}

	/*
	 * build symbol table
	 */
	public void addElem2Symbol(Symbol symbol) {
		this.symbol_tb.put(symbol.getName(), symbol);
	}

	public Symbol getSymbol(String key) {
		return this.symbol_tb.get(key);
	}

	public void setSymbolValue(String name, Object o) {
		Symbol s = this.symbol_tb.get(name);
		if (s == null) {
			System.out.println("Error<" + this.line
					+ " set symbol value failed , symbol " + name
					+ " does not exist.");
			return;
		}
		this.symbol_tb.remove(s);
		s.setValue(o);
		this.symbol_tb.put(name, s);
	}

	public char getNextChar() {
		char c = '@';
		try {
			c = (char) randomFile.readByte();
			randomFile.seek(randomFile.getFilePointer() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	/*
	 * is the separator?
	 */
	public boolean isSeparator(Character word) {
		// binary search algorithm
		int begin = 0;
		int end = Plc.separator.length - 1;
		int mid = (begin + end) / 2;
		while (begin <= end) {
			if (word.compareTo(Plc.separator[mid]) == 0
					|| word.compareTo(Plc.separator[begin]) == 0
					|| word.compareTo(Plc.separator[end]) == 0) {
				return true;
			} else if (word.compareTo(Plc.separator[mid]) < 0) {
				end = mid - 1;
			} else if (word.compareTo(Plc.separator[mid]) > 0) {
				begin = mid + 1;
			} else {
				return false;
			}
			mid = (begin + end) / 2;
		}
		return false;
	}

	/*
	 * is the word already exist in the symbol table?
	 */
	public boolean isWordMarked(String word) {
		if (this.symbol_tb.containsKey(word)) {
			// the word alread exist in the symbol table
			return true;
		}
		return false;
	}

	/*
	 * is the word key_word?
	 */
	public boolean isKeyWord(String word)
    {
		int begin = 0;
		int end = Plc.key_words.length - 1;
		int mid = (begin + end) / 2;
		while (begin <= end)
        {
			if (word.compareToIgnoreCase(Plc.key_words[mid]) == 0
					|| word.compareToIgnoreCase(Plc.key_words[begin]) == 0
					|| word.compareToIgnoreCase(Plc.key_words[end]) == 0)return true;
			else if (word.compareToIgnoreCase(Plc.key_words[mid]) < 0)end = mid - 1;
			else if (word.compareToIgnoreCase(Plc.key_words[mid]) > 0)begin = mid + 1;
			else return false;
			mid = (begin + end) / 2;
		}
		return false;
	}

	/*
	 * is the word identity?
	 */
	public boolean isIdentity(String word) {
		if (this.isKeyWord(word))return false;
		if (!(word.charAt(0) >= 'a' && word.charAt(0) <= 'z'
				|| word.charAt(0) >= 'A' && word.charAt(0) <= 'Z' || word
				.charAt(0) == '_'))	return false;
		for (int i = 1; i < word.length(); i++) {
			if (!((word.charAt(0) >= 'a' && word.charAt(0) <= 'z')
					|| (word.charAt(0) >= 'A' && word.charAt(0) <= 'Z')
					|| word.charAt(0) == '_' || (word.charAt(0) >= '0' && word
					.charAt(0) <= '9'))) {
				return false;
			}
		}
		return true;
	}

	/*
	 * is the word arithmetic operator?
	 */
	public boolean isArithmeticOp(String word) {
		String[] temp = new String[] { "+", "-", "*", "/" };
		for (String str : temp) {
			if (str.equals(word)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * is the word Logical operator?
	 */
	public boolean isLogicalOp(String word) {
		String[] temp = new String[] { "||", "&&", "!" };
		for (String str : temp) {
			if (str.equals(word)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * is the word relational operator?
	 */
	public boolean isRelationOp(String word) {
		return false;
	}

	/*
	 * is the word feed?
	 */
	public boolean isFeed(String word) {
		String[] tmp = new String[] { " ", "\n", "\t", "(", ")", ";", "'", "{",
				"}", "[", "]" };
		for (int i = 0; i < tmp.length; i++) {
			if (word.equals(tmp[i])) {
				return true;
			}
		}
		return false;
	}
	public boolean isInt(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (!(word.charAt(i) >= '0' && word.charAt(i) <= '9')) {
				return false;
			}
		}
		return true;
	}

	/*
	 * is float?
	 */
	public boolean isFloat(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (!((word.charAt(i) >= '0' && word.charAt(i) <= '9') || word
					.charAt(i) == '.')) {
				return false;
			}
		}
		return true;
	}

	public boolean isChar(String word) {
		if (word.length() == 1) {
			return true;
		}
		return false;
	}

	public boolean isBool(String word) {
		if (word.equals("true") || word.equals("false")) {
			return true;
		}
		return false;
	}

	/*
	 * is array?
	 */
	public boolean isArray(String word) {
		return false;
	}

	/*
	 * is the word Bit operator?
	 */
	public boolean isBitOp(String word) {
		return false;
	}

	public void testReadWord() {
		String tmp = readWord();
		int i = 1;
		while (tmp != null) {
			System.out.println(i + ":'" + tmp + "'");
			tmp = readWord();
			i++;
		}
	}
}
