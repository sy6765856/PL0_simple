package genCode.attribute_struct;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class LineNumberTable1 extends Attribute_info {
	private short line_number_table_length;
	private ArrayList<LineNum> lines;

	public LineNumberTable1() {
		lines = new ArrayList<LineNum>();
	}

	public short getLine_number_table_length() {
		return line_number_table_length;
	}

	public void addLineNUm(LineNum[] line) {
		this.attribute_length += 2;
		for (LineNum l : line) {
			this.attribute_length += 4;
			this.line_number_table_length++;
			this.lines.add(l);
		}
	}

	public void outAttributeByte(FileOutputStream out) throws Exception {
		out.write(short2bytes(attribute_name_index));
		out.write(int2bytes(attribute_length));
		out.write(short2bytes(line_number_table_length));
		for(LineNum l:lines){
			l.OutLineNumByte(out);
		}
	}
}
