

public class PushLiteral extends Instruction {
	// data fields
	private Double literal;

	
	public PushLiteral(int code, String mnemonic, Double literal) {
		super(code, mnemonic);
		this.literal = literal;
	}

	public Double getLiteral() {
		return literal;
	}

	public void setLiteral(Double literal) {
		this.literal = literal;
	}
	
	public String toString() {
		return super.toString()+" "+literal;
	}
	
	
}
