package genCode.attribute_struct;

import java.io.FileOutputStream;

public class LocalVar {
	private short start_pc;
	private short length; // 当解释到代码数组的[start_pc,start_pc+length] 时变量必须被赋值??
	private short name_index;
	private short descriptor_index;
	private short index; // 到本地变量数组的一个索引

	public short getStart_pc() {
		return start_pc;
	}

	public void setStart_pc(short start_pc) {
		this.start_pc = start_pc;
	}

	public short getLength() {
		return length;
	}

	public void setLength(short length) {
		this.length = length;
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

	public short getIndex() {
		return index;
	}

	public void setIndex(short index) {
		this.index = index;
	}

	public byte[] short2bytes(short sh) {
		byte[] b = new byte[2];
		for (int i = 0; i < 2; i++) {
			b[1 - i] = (byte) ((sh >> i * 8) & 0xff);
		}
		return b;
	}

	public void outVarBytes(FileOutputStream out) throws Exception {
		out.write(short2bytes(start_pc));
		out.write(short2bytes(length));
		out.write(short2bytes(name_index));
		out.write(short2bytes(descriptor_index));
		out.write(short2bytes(index));
	}
}
