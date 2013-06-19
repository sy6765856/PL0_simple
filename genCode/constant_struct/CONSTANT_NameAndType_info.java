package genCode.constant_struct;

import java.io.FileOutputStream;

public class CONSTANT_NameAndType_info extends Cp_info {

	private short name_index;
	private short descriptor_index;

	public CONSTANT_NameAndType_info() {
		this.tags = 12;
	}

	public short getName_index() {
		return name_index;
	}

	public void setName_index(short name_index) {
		this.name_index = name_index;
	}

	public short getDescriptor_index() {
		return descriptor_index;
	}

	public void setDescriptor_index(short descriptor_index) {
		this.descriptor_index = descriptor_index;
	}

	public void outCp_infoByte(FileOutputStream out) throws Exception {
		out.write(tags);
		byte[] b1 = new byte[2];
		byte[] b2 = new byte[2];
		for (int i = 0; i < 2; i++) {
			b1[1 - i] = (byte) ((name_index >> i * 8) & 0xff);
			b2[1 - i] = (byte) ((descriptor_index >> i * 8) & 0xff);
		}
		out.write(b1);
		out.write(b2);
	}
}
