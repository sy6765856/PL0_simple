package genCode.constant_struct;

import java.io.FileOutputStream;

public class CONSTANT_String_info extends Cp_info {
	private short string_index;

	public CONSTANT_String_info() {
		this.tags = 8;
	}

	public short getString_index() {
		return string_index;
	}

	public void setString_index(short string_index) {
		this.string_index = string_index;
	}

	public void outCp_infoByte(FileOutputStream out) throws Exception {
		out.write(tags);
		byte[] b1 = new byte[2];
		for (int i = 0; i < 2; i++) {
			b1[1 - i] = (byte) ((string_index >> i * 8) & 0xff);
		}
		out.write(b1);
	}
}
