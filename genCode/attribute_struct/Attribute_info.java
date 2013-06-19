package genCode.attribute_struct;

import java.io.FileOutputStream;

public class Attribute_info {
	protected short attribute_name_index;
	protected int attribute_length;

	public short getAttribute_name_index() {
		return attribute_name_index;
	}

	public void setAttribute_name_index(short attribute_name_index) {
		this.attribute_name_index = attribute_name_index;
	}

	public int getAttribute_length() {
		return attribute_length;
	}

	public byte[] short2bytes(short sh) {
		byte[] b = new byte[2];
		for (int i = 0; i < 2; i++) {
			b[1 - i] = (byte) ((sh >> i * 8) & 0xff);
		}
		return b;
	}

	public byte[] int2bytes(int i) {
		byte[] b = new byte[4];
		for (int j = 0; j < 4; j++) {
			b[3 - j] = (byte) ((i >> j * 8) & 0xff);
		}
		return b;
	}

	public void outAttributeByte(FileOutputStream out) throws Exception {
	}
}
