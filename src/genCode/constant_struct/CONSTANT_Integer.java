package genCode.constant_struct;

import java.io.FileOutputStream;

public class CONSTANT_Integer extends Cp_info {
	private int bytes;

	public CONSTANT_Integer() {
		this.tags = 3;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public void outCp_infoByte(FileOutputStream out) throws Exception {
		out.write(tags);
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			b[3 - i] = (byte) ((bytes >> i * 8) & 0xff);
		}
		out.write(b);
	}
}
