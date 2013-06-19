package genCode.attribute_struct;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class Code extends Attribute_info {
	private short max_stack;
	private short max_locals;
	private int code_length;
	private ArrayList<Integer> code;
	private short exception_table_length;
	private short attributes_count;

	// private ArrayList<Attribute_info> attributes;// not used

	public Code() {
		exception_table_length = 0;
		code_length = 0;
		attributes_count = 0;
		this.attribute_length += 8; // exception_table_length + attributes_count
		// code_length
		code = new ArrayList<Integer>();
		// attributes = new ArrayList<Attribute_info>();
	}

	public void addCodeByte(int[] b) {
		for (int i : b) {
			this.code_length++;
			this.attribute_length++;
			code.add(i);
		}
	}

	public int removeLastCode() {
		this.code_length--;
		this.attribute_length--;
		return code.remove(code.size() - 1);
	}

	public ArrayList<Integer> getCodes() {
		return code;
	}

	public void addMaxStack() {
		this.max_stack++;
	}

	public void addMaxLocals() {
		this.max_locals++;
	}

	public void addAttributes(Attribute_info a) {
		// this.attributes_count++;
		// this.attribute_length += a.getAttribute_length() + 2 + 6;
		// attributes.add(a);
	}

	public short getMax_stack() {
		return max_stack;
	}

	public void setMax_stack(short max_stack) {
		this.attribute_length += 2;
		this.max_stack = max_stack;
	}

	public short getMax_locals() {
		return max_locals;
	}

	public void setMax_locals(short max_locals) {
		this.attribute_length += 2;
		this.max_locals = max_locals;
	}

	public int getCode_length() {
		return code_length;
	}

	public short getAttributes_count() {
		return attributes_count;
	}

	public void setIndexCode(int index, int code) {
		this.code.set(index, code);
	}

	public void backSetCode(int index) {
		short back = (short) (this.code_length - index + 1);
		this.code.set(index, (back >> 8 & 0xff));
		this.code.set(index + 1, back & 0x0ff);
	}

	public void outAttributeByte(FileOutputStream out) throws Exception {
		out.write(short2bytes(attribute_name_index));
		out.write(int2bytes(attribute_length));
		out.write(short2bytes(max_stack));
		out.write(short2bytes(max_locals));
		out.write(int2bytes(code_length));
		for (int i = 0; i < code_length; i++) {
			out.write(code.get(i));
		}
		out.write(short2bytes(exception_table_length));
		out.write(short2bytes(attributes_count));
		// for (int i = 0; i < attributes_count; i++) {
		// attributes.get(i).outAttributeByte(out);
		// }
	}
}
