/* Generated By:JavaCC: Do not edit this line. MyLang.java */
package jj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyLang implements MyLangConstants {

  public static void main(String args[]) throws ParseException {
      FileInputStream ml_file = null;
      String parent_path = "I:\u005c\u005cJetBrains\u005c\u005cPsychoCompiler\u005c\u005csrc\u005c\u005cmain\u005c\u005cresources\u005c\u005cMyLang_code\u005c\u005c";
      String file_name = "MyLang_simple_2.ml";
      File file = new File(parent_path + file_name);
      if (file.isFile() && file.exists()) {
        System.out.println("find file");
      } else {
        System.out.println("not find file");
      }
      try {
        ml_file = new FileInputStream(file);
      }
      catch (FileNotFoundException e) {
        System.out.print("src file open failed.");
      }
      //MyLang parser = new MyLang(System.in);
      MyLang parser = new MyLang(ml_file);

      System.out.println("Please type the valid statement...");
      System.out.println("input>>");
      try {
        switch (MyLang.Start()){
          case 0:System.out.println("OK.You have typed the correct statements.");
          break ;
          case 1:System.out.println("Goodbye.Thank you for you testing!");
        }
      }
      catch (Exception e){
        System.out.println("Sorry! You have typed the invalid statements.");
        System.out.println(e.getMessage());
        //MyLang.ReInit(System.in);
      }
      catch (Error e){
        System.out.println("Oops.");
        System.out.println(e.getMessage());
      }
  }

  static final public int Start() throws ParseException {
    Program_declaration();
    jj_consume_token(0);
    {if (true) return 0;}
    throw new Error("Missing return statement in function");
  }

  static final public void Program_declaration() throws ParseException {
    Program_head();
    Program_body();
  }

  static final public void Program_head() throws ParseException {
    jj_consume_token(PROGRAM);
    Program_name();
    Program_parameter_list();
    Program_parameters_type();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case RETURN:
      Program_return_declaration();
      break;
    default:
      jj_la1[0] = jj_gen;
      ;
    }
  }

  static final public void Program_body() throws ParseException {
    Component_declarations();
    Program_variable_declarations();
    Program_process();
  }

  static final public void Program_name() throws ParseException {
    Identifier();
  }

  static final public void Program_parameter_list() throws ParseException {
    Parameter_list();
  }

  static final public void Program_parameters_type() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_1;
      }
      Program_parameter_type();
    }
  }

  static final public void Program_parameter_type() throws ParseException {
    Variable_declaration();
  }

  static final public void Program_return_declaration() throws ParseException {
    jj_consume_token(RETURN);
    Type();
    jj_consume_token(SEMICOLON);
  }

  static final public void Program_variable_declarations() throws ParseException {
    jj_consume_token(IS);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      Program_variable_declaration();
    }
  }

  static final public void Program_variable_declaration() throws ParseException {
    Variable_declaration();
  }

  static final public void Program_process() throws ParseException {
    jj_consume_token(BEGIN);
    Block();
    jj_consume_token(END);
  }

  static final public void Component_declarations() throws ParseException {
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FUNCTION:
      case TYPE:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      Component_declaration();
    }
  }

  static final public void Component_declaration() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case FUNCTION:
      Function_declaration();
      break;
    case TYPE:
      Type_declaration();
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Function_declaration() throws ParseException {
    Function_head();
    Function_body();
  }

  static final public void Function_head() throws ParseException {
    jj_consume_token(FUNCTION);
    Function_name();
    Function_parameter_list();
    Function_parameters_type();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case RETURN:
      Function_return_declaration();
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
  }

  static final public void Function_body() throws ParseException {
    Function_variable_declarations();
    Function_process();
  }

  static final public void Function_name() throws ParseException {
    Identifier();
  }

  static final public void Function_parameter_list() throws ParseException {
    Parameter_list();
  }

  static final public void Function_parameters_type() throws ParseException {
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        ;
        break;
      default:
        jj_la1[6] = jj_gen;
        break label_4;
      }
      Function_parameter_type();
    }
  }

  static final public void Function_parameter_type() throws ParseException {
    Variable_declaration();
  }

  static final public void Function_return_declaration() throws ParseException {
    jj_consume_token(RETURN);
    Type();
    jj_consume_token(SEMICOLON);
  }

  static final public void Function_variable_declarations() throws ParseException {
    jj_consume_token(IS);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VAR:
        ;
        break;
      default:
        jj_la1[7] = jj_gen;
        break label_5;
      }
      Function_variable_declaration();
    }
  }

  static final public void Function_variable_declaration() throws ParseException {
    Variable_declaration();
  }

  static final public void Function_process() throws ParseException {
    jj_consume_token(BEGIN);
    Block();
    jj_consume_token(END);
    jj_consume_token(FUNCTION);
    Function_name();
    jj_consume_token(SEMICOLON);
  }

  static final public void Variable_declaration() throws ParseException {
    jj_consume_token(VAR);
    Identifier();
    jj_consume_token(IS);
    Type();
    jj_consume_token(SEMICOLON);
  }

  static final public void Parameter_list() throws ParseException {
    jj_consume_token(LEFTPARENTHESES);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IDENTIFIER:
      Parameters();
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    jj_consume_token(RIGHTPARENTHESES);
  }

  static final public void Parameters() throws ParseException {
    Parameter();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_6;
      }
      jj_consume_token(COMMA);
      Parameter();
    }
  }

  static final public void Parameter() throws ParseException {
    Identifier();
  }

  static final public void Type_declaration() throws ParseException {
    jj_consume_token(TYPE);
    Identifier();
    jj_consume_token(IS);
    Type();
    jj_consume_token(SEMICOLON);
  }

// TODO class



// TODO reference_type
  static final public void Type() throws ParseException {
    Primitive_type();
  }

  static final public void Primitive_type() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
      Numeric_type();
      break;
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Numeric_type() throws ParseException {
    Integral_type();
  }

  static final public void Integral_type() throws ParseException {
    jj_consume_token(INTEGER);
  }

/*
void Reference_type():{}{
  Class_type()
  | Array_type()
}

void Class_type():{}{
  Type_name()
}

void Type_name():{}{
  Identifier()
}

void Array_type():{}{
  <ARRAY> <OF> Expression() Type()
}
*/
// TODO Expression
  static final public void Block() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VAR:
      jj_consume_token(VAR);
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
  }

  static final public void Identifier() throws ParseException {
    jj_consume_token(IDENTIFIER);
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public MyLangTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[12];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2000,0x100,0x100,0x5000,0x5000,0x2000,0x100,0x100,0x0,0x800000,0x18000,0x100,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x0,0x0,0x0,};
   }

  /** Constructor with InputStream. */
  public MyLang(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MyLang(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MyLangTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public MyLang(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MyLangTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public MyLang(MyLangTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(MyLangTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 12; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[35];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 12; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 35; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
