package genCode.constant_struct;

import java.io.FileOutputStream;

public class CONSTANT_Float_info extends Cp_info {
	private byte[] bytes;

	public CONSTANT_Float_info() {
		this.tags = 4;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public void outCp_infoByte(FileOutputStream out) throws Exception {
		out.write(tags);
		out.write(bytes);
	}
}
