package genCode.fields_struct;

import java.io.FileOutputStream;
import java.util.ArrayList;
import genCode.attribute_struct.Attribute_info;

public class Fields_info {
	private short access_flags;
	private short name_index;
	private short descriptor_index; // constant_pool index
	private short attributes_count;
	private ArrayList<Attribute_info> attributes;

	public void addAttributes(Attribute_info a) {
		this.attributes_count++;
		this.attributes.add(a);
	}

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

	public void setAttributes_count(short attributes_count) {
		this.attributes_count = attributes_count;
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
