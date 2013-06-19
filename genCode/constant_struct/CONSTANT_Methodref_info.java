package genCode.constant_struct;

import java.io.FileOutputStream;

public class CONSTANT_Methodref_info extends Cp_info {
	protected short class_index;
	protected short name_and_type_index;

	public CONSTANT_Methodref_info() {
		this.tags = 10;
	}

	public short getClass_index() {
		return class_index;
	}

	public void setClass_index(short class_index) {
		this.class_index = class_index;
	}

	public short getName_and_type_index() {
		return name_and_type_index;
	}

	public void setName_and_type_index(short name_and_type_index) {
		this.name_and_type_index = name_and_type_index;
	}

	public void outCp_infoByte(FileOutputStream out) throws Exception {
		out.write(tags);
		byte[] b1 = new byte[2];
		byte[] b2 = new byte[2];
		for (int i = 0; i < 2; i++) {
			b1[1 - i] = (byte) ((class_index >> i * 8) & 0xff);
			b2[1 - i] = (byte) ((name_and_type_index >> i * 8) & 0xff);
		}
		out.write(b1);
		out.write(b2);
	}

}
