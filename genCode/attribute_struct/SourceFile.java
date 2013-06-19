package genCode.attribute_struct;

import java.io.FileOutputStream;

public class SourceFile extends Attribute_info {

	private short sourcefile_index;

	public short getSourcefile_index() {
		return sourcefile_index;
	}

	public void setSourcefile_index(short sourcefile_index) {
		this.attribute_length += 2;
		this.sourcefile_index = sourcefile_index;
	}

	public void outAttributeByte(FileOutputStream out) throws Exception {
		out.write(short2bytes(attribute_name_index));
		out.write(int2bytes(attribute_length));
		out.write(short2bytes(sourcefile_index));
	}
}
