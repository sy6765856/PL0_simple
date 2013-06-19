package genCode.constant_struct;

import java.io.FileOutputStream;

public class CONSTANT_Class_info extends Cp_info {

	private short name_index;

	public CONSTANT_Class_info() {
		this.tags = 7;
	}

	public short getName_index() {
		return name_index;
	}

	public void setName_index(short name_index) {
		this.name_index = name_index;
	}

	public void outCp_infoByte(FileOutputStream out) throws Exception {
		out.write(tags);
		byte[] b1 = new byte[2];
		for (int i = 0; i < 2; i++) {
			b1[1 - i] = (byte) ((name_index >> i * 8) & 0xff);
		}
		out.write(b1);
	}
}
