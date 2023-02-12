package com.TrichromaticFire.elecasm.core.beans;

public class OriginalCode {

	private String   operand;
	private String[] opcodes;
	
	private int index;
	private int opcode_size;
	
	public OriginalCode(){
		setOpcode_size(2);
	}
	
	public OriginalCode(int n){
		setOpcode_size(n);
	}
	
	private void opcodesInit(){
		index = 0;
		opcodes = new String[opcode_size];
	}
	
	public boolean add(String opcode){
		if(index < opcode_size){
			opcodes[index] = opcode;
			index++;
			return true;
		}else{
			return false;
		}
	}
	
	public String pop(){
		if(!isEmpty()){
			return opcodes[opcode_size - (index--)];
		}else{
			return null;
		}
	}
	
	public boolean isEmpty(){
		return !(index > 0);
	}
	
	public String getOperand() {
		return operand;
	}

	public void setOperand(String operand) {
		this.operand = operand;
	}
	
	public String[] getOpcodes() {
		return opcodes;
	}

	public void setOpcodes(String[] opcodes) {
		this.opcodes = opcodes;
	}

	public int getOpcode_size() {
		return opcode_size;
	}

	public void setOpcode_size(int opcode_size) {
		this.opcode_size = opcode_size;
		opcodesInit();
	}

}
