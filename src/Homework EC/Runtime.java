//John Graham
//I pledge my honor that I have abided by the Stevens Honor System.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Runtime {
	// data fields
	private ArrayList<Instruction> pgm;
	private int pc;
	private Stack<Double> stack;
	private ArrayList<Double> memory;

	Runtime() {
		stack = new Stack<>();
		memory = new ArrayList<>(10);	
	}

	/**
	 * Set SBM to initial state
	 */
	private void initialize() {
		// empty stack
		while (!stack.isEmpty()) {
			stack.pop();
		}
		// reset all memory locations
		for (int i=0; i<10; i++) {
			memory.add(0.0);
		}
		// set pc to first instruction
		pc=1;
	}
	
	/**Returns the line of code where a given label is declared
	 * @param label, the name of the label that is being located
	 * @return int, the line of code at which the label was found*/
	private int jumpToLabel(String label) {
		int x=1;
		boolean found=false;
		for(Instruction i:pgm) {
			if(i instanceof Label && ((Label)i).getName().equals(label)) {
				found=true;
				break;
			}
			x++;
		}
		if(!found) {
			throw new IllegalStateException("runtime: label not found");
		}
		return x;
	}

	/**Processes an instruction based on its type.
	 * @param i, the instruction to be processed
	 * @return boolean, true if an instruction is successfully processed, false if the given instruction is an exit statement*/
	private boolean processInstruction(Instruction i) {
		if(i instanceof PushLiteral) {
			stack.push(((PushLiteral)i).getLiteral());
			return true;
		}
		else if(i instanceof PushLocation) {
			stack.push(memory.get(((PushLocation)i).getAddress()));
			return true;
		}
		else if(i instanceof Pop) {
			memory.set(((Pop)i).getAddress(), stack.pop());
			return true;
		}
		else if(i instanceof Add) {
			double x=stack.pop();
			double y=stack.pop();
			stack.push(x+y);
			return true;
		}
		else if(i instanceof Sub) {
			double x=stack.pop();
			double y=stack.pop();
			stack.push(x-y);
			return true;
		}
		else if(i instanceof Mul) {
			double x=stack.pop();
			double y=stack.pop();
			stack.push(x*y);
			return true;
		}
		else if(i instanceof Div) {
			double x=stack.pop();
			double y=stack.pop();
			stack.push(x/y);
			return true;
		}
		else if(i instanceof Label) {
			return true;
		}
		else if(i instanceof Jmpz) {
			if(stack.peek()==0.0) {
				pc=jumpToLabel(((Jmpz)i).getTargetLabel());
			}
			return true;
		}
		else if(i instanceof Jmp) {
			pc=jumpToLabel(((Jmp)i).getTargetLabel());
			return true;
		}
		else if(i instanceof Dec) {
			double x=stack.pop();
			x--;
			stack.push(x);
			return true;
		}
		else { //i instance of Exit
			return false;
		}
	}

	/**Initializes the machine and processes each instruction until an exit statement is reached.*/
	public void run() {
		initialize();
		boolean run=true;
		while(run) {
			run=processInstruction(pgm.get(pc-1));
			pc++;
		}
	}

	private Instruction parseInstruction(String str, int line) {
		String[] p = str.split("[ ]+"); // delimiters are non-empty sequences of spaces
		Instruction i=null;

		switch (p[0]) {
		case "exit":
			i = new Exit(0,"exit");
			break;
		case "push": 
			if (p.length==1) {
				throw new IllegalStateException("parseInstruction: syntax error at line "+line);
			}
			try {
				if (p[1].charAt(0)=='m') {
					int loc = Integer.parseInt(p[1].substring(1));
					if (loc<0 || loc>9) {
						throw new IllegalStateException("parseInstruction: syntax error at line "+line);
					}
					i = new PushLocation(2,"push",loc);
				} else {
					i = new PushLiteral(1,"push",Double.parseDouble(p[1]));
				}
			} catch (NumberFormatException e) {
				throw new IllegalStateException("parseInstruction: syntax error at line "+line);
			}
			break;
		case "pop": 
			if (p.length==1) {
				throw new IllegalStateException("parseInstruction: syntax error at line "+line);
			}
			int loc = Integer.parseInt(p[1].substring(1));
			if (loc<0 || loc>9) {
				throw new IllegalStateException("parseInstruction: syntax error at line "+line);
			}
			i = new Pop(3,"pop",loc);
			break;
		case "add": 
			i = new Add(4,"add");
			break;
		case "sub": 
			i = new Sub(5,"sub");
			break;
		case "mul": 
			i = new Mul(6,"mul");
			break;
		case "div": 
			i = new Div(7,"div");
			break;
		case "label":
			i = new Label(8,"label",p[1]);
			break;
		case "jmpz":
			i = new Jmpz(9,"jmpz",p[1]);
			break;
		case "jmp":
			i = new Jmp(10,"jmp",p[1]);
			break;
		case "dec":
			i = new Dec(11,"dec");
			break;
		case "":
			break;
		default:
			throw new IllegalStateException("parseInstruction: syntax error at line "+line);
		}
		return i;
	}

	public void readFromFile(String name)  {
		pgm = new ArrayList<>();
		File f = new File(name);
		try {
			Scanner s = new Scanner(f);
			int line=1;

			while (s.hasNext()) {
				Instruction i = parseInstruction(s.nextLine(),line);
				if (i!=null) {
					pgm.add(i);
				}
				line++;
			}
			s.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		StringBuilder r = new StringBuilder();
		r.append("Pgm   : ");
		r.append(pgm==null?"null":pgm.toString());
		r.append("\nPc    : "+pc);
		r.append("\nStack : ");
		r.append(stack.toString());
		r.append("\nMemory: ");
		r.append(memory.toString());
		r.append("\n------------------------------------------------\n");

		return r.toString();
	}

	public static void main(String[] args) {
		Runtime r = new Runtime();
		r.readFromFile("eg2.pgm"); // Load and parse mini-bytecode program
		r.run(); // execute program
		System.out.println(r);  // print resulting state of the SBM
	}
}
