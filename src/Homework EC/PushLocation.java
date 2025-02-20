

public class PushLocation extends Instruction {
	// data fields
	private int address;

	
	public PushLocation(int code, String mnemonic, int address) {
		super(code,mnemonic);
		this.address = address;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}
	
	public String toString() {
		return super.toString()+" m"+address;
	}
	
	
}
