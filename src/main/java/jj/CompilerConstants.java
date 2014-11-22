package jj;

/**
 * Created by NinetuOne on 2014/11/22.
 */
public interface CompilerConstants {
    /** LOAD PROGRAM File. */
    int Load = 0;
    /** GENERATE TOKEN STREAM */
    int  GE_Token= 1;

    /** GENERATE TREE. */
    int GE_Tree = 3;
    /*QUIT */
    int Quit = 4;


    /** Literal token values. */
    String[] ChoiceImage = {
            "<LOAD>",
            "<GE_TOKEN>",
            "<GE_TREE>",
            "<QUIT>",
    };
}
