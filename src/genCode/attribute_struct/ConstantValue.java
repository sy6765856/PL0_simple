package genCode.attribute_struct;

import java.io.FileOutputStream;

public class ConstantValue extends Attribute_info {
	private short constantvalue_index;
	public short getConstantvalue_index() {
		return constantvalue_index;
	}

	public void setConstantvalue_index(short constantvalue_index) {
		this.attribute_length+=2;
		this.constantvalue_index = constantvalue_index;
	}

	public void outAttributeByte(FileOutputStream out) throws Exception {
		out.write(short2bytes(attribute_name_index));
		out.write(int2bytes(attribute_length));
		out.write(short2bytes(constantvalue_index));
	}
}
