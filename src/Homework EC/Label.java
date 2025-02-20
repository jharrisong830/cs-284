

public class Label extends Instruction {
	private String name;
	
	Label(int code, String mnemonic, String name) {
		super(code,mnemonic);
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return super.toString()+" "+name;
	}
	
	
	

}
