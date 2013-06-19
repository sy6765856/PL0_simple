package genCode.method_struct;

import genCode.attribute_struct.Code;
import java.io.FileOutputStream;

import java.util.ArrayList;

public class Method_info {
	private short access_flags;
	private short name_index;
	private short descriptor_index;
	private short attributes_count;
	private ArrayList<Code> attributes;

	public Method_info(){
		this.attributes = new ArrayList<Code>();
	}
	public void addAttributes(Code code) {
		this.attributes_count++;
		this.attributes.add(code);
	}

	/*
	 * ACC_PUBLIC 0x0001 
	 * ACC_PRIVATE 0x0002 
	 * ACC_PROTECTED 0x0004 
	 * ACC_STATIC 0x0008 
	 * ACC_FINAL 0x0010 
	 * ACC_SYNCHRONIZED 0x0020 
	 * ACC_NATIVE 0x0100
	 * ACC_ABSTRACT 0x0400 
	 * ACC_STRICT 0x0800
	 * 
	 */
	public short getAccess_flags() {
		return access_flags;
	}

	public void setAccess_flags(short access_flags) {
		this.access_flags = access_flags;
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

	public short getAttributes_count() {
		return attributes_count;
	}
	
	public byte[] short2bytes(short sh) {
		byte[] b = new byte[2];
		for (int i = 0; i < 2; i++) {
			b[1 - i] = (byte) ((sh >> i * 8) & 0xff);
		}
		return b;
	}

	public void outMethodBytes(FileOutputStream out) throws Exception {
		out.write(short2bytes(access_flags));
		out.write(short2bytes(name_index));
		out.write(short2bytes(descriptor_index));
		out.write(short2bytes(attributes_count));
		for (int i = 0; i < this.attributes_count; i++) {
			attributes.get(i).outAttributeByte(out);
		}
	}
}
