

public abstract class Instruction {
	// data fields
	protected int code;
    protected  String mnemonic;
    
	public Instruction(int code, String mnemonic) {
		super();
		this.code = code;
		this.mnemonic = mnemonic;
	}
    
	public String toString() {
		return mnemonic;
	}
    
}
