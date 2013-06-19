package genCode.attribute_struct;

import java.io.FileOutputStream;

public class LineNum {
	private short start_pc; // 代码数组中的开始处
	private short line_number; // 源文件中的行号(对于每一非空行都有这么一项) 

	public short getStart_pc() {
		return start_pc;
	}

	public void setStart_pc(short start_pc) {
		this.start_pc = start_pc;
	}

	public short getLine_number() {
		return line_number;
	}

	public void setLine_number(short line_number) {
		this.line_number = line_number;
	}

	public byte[] short2bytes(short sh) {
		byte[] b = new byte[2];
		for (int i = 0; i < 2; i++) {
			b[1 - i] = (byte) ((sh >> i * 8) & 0xff);
		}
		return b;
	}

	public void OutLineNumByte(FileOutputStream out) throws Exception {
		out.write(short2bytes(start_pc));
		out.write(short2bytes(line_number));
	}
}
