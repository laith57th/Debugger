package interpreter.bytecode;

import java.util.ArrayList;

import interpreter.VirtualMachine;

public abstract class BranchCode extends ByteCode {
 public abstract void init(ArrayList<String> args);

 public abstract void execute(VirtualMachine vm);

 public abstract int getTarget();

 public abstract void setTarget(int n);

 public abstract String getLabel();

 public abstract void setLabel(String label);
}
