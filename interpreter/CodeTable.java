package interpreter;

import java.util.HashMap;

public class CodeTable {
  private CodeTable() {
  }

  private static HashMap<String, String> byteCodes;

  public static void init(boolean debug) {
    byteCodes = new HashMap<>();
    if (!debug) {
      byteCodes.put("LIT", "LitCode");
      byteCodes.put("CALL", "CallCode");
      byteCodes.put("POP", "PopCode");
      byteCodes.put("RETURN", "ReturnCode");
    }
    byteCodes.put("FALSEBRANCH", "FalseBranchCode");
    byteCodes.put("GOTO", "GotoCode");
    byteCodes.put("HALT", "HaltCode");
    byteCodes.put("STORE", "StoreCode");
    byteCodes.put("LOAD", "LoadCode");
    byteCodes.put("ARGS", "ArgsCode");
    byteCodes.put("BOP", "BopCode");
    byteCodes.put("READ", "ReadCode");
    byteCodes.put("WRITE", "WriteCode");
    byteCodes.put("LABEL", "LabelCode");
    byteCodes.put("DUMP", "DumpCode");
  }

  public static String getClass(String code) {
    return byteCodes.get(code);
  }
}