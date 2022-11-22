package interpreter.bytecode;

import java.util.ArrayList;
import interpreter.VirtualMachine;

public class BopCode extends ByteCode {
  private String operator;

  public BopCode() {
  }

  @Override
  public void execute(VirtualMachine vm) {

    int firstOperand = vm.getRunStack().pop();
    int secondOperand = vm.getRunStack().pop();
    int result = 0;

    switch (operator) {
      case "+":
        result = firstOperand + secondOperand;
        break;
      case "-":
        result = secondOperand - firstOperand;
        break;
      case "*":
        result = firstOperand * secondOperand;
        break;
      case "/":
        result = secondOperand / firstOperand;
        break;
      case "==":
        if (secondOperand == firstOperand) {
          result = 1;
        } else {
          result = 0;
        }
        break;
      case "!=":
        if (secondOperand != firstOperand) {
          result = 1;
        } else {
          result = 0;
        }
        break;
      case "<":
        if (secondOperand < firstOperand) {
          result = 1;
        } else {
          result = 0;
        }
        break;
      case ">":
        if (secondOperand > firstOperand) {
          result = 1;
        } else {
          result = 0;
        }
        break;
      case "<=":
        if (secondOperand <= firstOperand) {
          result = 1;
        } else {
          result = 0;
        }
        break;
      case ">=":
        if (secondOperand >= firstOperand) {
          result = 1;
        } else {
          result = 0;
        }
        break;
      case "&":
        if (secondOperand == 1 && firstOperand == 1) {
          result = 1;
        } else {
          result = 0;
        }
        break;
      case "|":
        if (secondOperand == 1 || firstOperand == 1) {
          result = 1;
        } else {
          result = 0;
        }
        break;
    }
    vm.getRunStack().push(result);
    if(vm.dumpIsOn()){
      System.out.println("BOP " + operator);
    }
  }

  @Override
  public void init(ArrayList<String> parameters) {
    operator = parameters.get(0);
  }
}