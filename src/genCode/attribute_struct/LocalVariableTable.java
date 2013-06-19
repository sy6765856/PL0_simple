package genCode.attribute_struct;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class LocalVariableTable extends Attribute_info {

	private short local_variable_table_length;
	private ArrayList<LocalVar> local_variable_table;

	public LocalVariableTable() {
		local_variable_table = new ArrayList<LocalVar>();
	}

	public short getLocal_variable_table_length() {
		return local_variable_table_length;
	}

	public void addLcalVar(LocalVar local) {
		this.attribute_length += 2;
		this.local_variable_table_length++;
		this.attribute_length += 10;
		this.local_variable_table.add(local);
	}

	public void outAttributeByte(FileOutputStream out) throws Exception {
		out.write(short2bytes(attribute_name_index));
		out.write(int2bytes(attribute_length));
		out.write(short2bytes(local_variable_table_length));
		for (int i = 0; i < this.local_variable_table_length; i++) {
			this.local_variable_table.get(i).outVarBytes(out);
		}
	}
}
