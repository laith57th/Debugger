package interpreter;

import java.util.HashMap;
import java.util.Vector;
import interpreter.bytecode.*;

public class Program {
  private Vector<ByteCode> program;
  private HashMap<String, Integer> addresses = new HashMap<>();

  public Program() {
    program = new Vector<>();
  }

  public ByteCode getCode(int programCounter) {
    ByteCode code = this.program.get(programCounter);
    return code;
  }

  public Vector<ByteCode> getVector(){
    return this.program;
  }

  public void addCode(ByteCode byteCode) {
    if (byteCode instanceof LabelCode) {
      LabelCode label = (LabelCode) byteCode;
      addresses.put(label.getLabel(), program.size());
    }
    program.add(byteCode);
  }

  public void resolveAddresses(Program source) {
    Integer jumpAddress;
    for(ByteCode code: program){
      if (code instanceof BranchCode) {
        BranchCode temp = (BranchCode) code;
        jumpAddress = Integer.valueOf(addresses.get(temp.getLabel()));
        temp.setTarget(jumpAddress.intValue());
      }
    }
  }
}