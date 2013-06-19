package genCode.constant_struct;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class CONSTANT_Utf8_info extends Cp_info {
	private short length;
	private ArrayList<Byte> bytes; // length

	public CONSTANT_Utf8_info() {
		this.tags = 1;
		bytes = new ArrayList<Byte>();
	}

	public short getLength() {
		return length;
	}
	
	public void addByte(byte[] bt) {
		for (byte b : bt) {
			length ++ ;
			this.bytes.add(b);
		}
	}

	public void outCp_infoByte(FileOutputStream out) throws Exception {
		out.write(tags);
		byte[] b1 = new byte[2];
		for (int i = 0; i < 2; i++) {
			b1[1 - i] = (byte) ((length >> i * 8) & 0xff);
		}
		out.write(b1);
		for (byte b : bytes) {
			out.write(b);
		}
	}
}
