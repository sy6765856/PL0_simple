package lexical;
public class Symbol {
	private int type;
	private String valueType;
	private String name;
	private Object value;

	//type key word : 1 , const : 2 , function : 3 , procedure : 4 ,var : 5 function arg_var:6
	//valueType : integer , real , boolean , char , void
	public int getType(){return type;}
	public void setType(int type){this.type = type;}
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	public Object getValue(){return value;}
	public void setValue(Object value){this.value = value;}
	public String getValueType(){return valueType;}
	public void setValueType(String valueType){this.valueType = valueType;}
}