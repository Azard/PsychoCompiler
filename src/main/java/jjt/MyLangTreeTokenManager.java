/* Generated By:JJTree&JavaCC: Do not edit this line. MyLangTreeTokenManager.java */
package jjt;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/** Token Manager. */
public class MyLangTreeTokenManager implements MyLangTreeConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x1fffffff80L) != 0L)
         {
            jjmatchedKind = 37;
            return 16;
         }
         if ((active0 & 0x1000000000000000L) != 0L)
            return 4;
         return -1;
      case 1:
         if ((active0 & 0xddea5fb80L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 37;
               jjmatchedPos = 1;
            }
            return 16;
         }
         if ((active0 & 0x12215a0400L) != 0L)
            return 16;
         return -1;
      case 2:
         if ((active0 & 0x5de87e980L) != 0L)
         {
            jjmatchedKind = 37;
            jjmatchedPos = 2;
            return 16;
         }
         if ((active0 & 0x800601200L) != 0L)
            return 16;
         return -1;
      case 3:
         if ((active0 & 0xe008000L) != 0L)
            return 16;
         if ((active0 & 0x5d0876980L) != 0L)
         {
            jjmatchedKind = 37;
            jjmatchedPos = 3;
            return 16;
         }
         return -1;
      case 4:
         if ((active0 & 0x140836080L) != 0L)
         {
            jjmatchedKind = 37;
            jjmatchedPos = 4;
            return 16;
         }
         if ((active0 & 0x490040900L) != 0L)
            return 16;
         return -1;
      case 5:
         if ((active0 & 0x100832080L) != 0L)
         {
            jjmatchedKind = 37;
            jjmatchedPos = 5;
            return 16;
         }
         if ((active0 & 0x40004000L) != 0L)
            return 16;
         return -1;
      case 6:
         if ((active0 & 0x2000L) != 0L)
         {
            jjmatchedKind = 37;
            jjmatchedPos = 6;
            return 16;
         }
         if ((active0 & 0x100830080L) != 0L)
            return 16;
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0, long active1)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 65;
         return jjMoveStringLiteralDfa1_0(0x200000000000L);
      case 37:
         return jjStopAtPos(0, 61);
      case 38:
         return jjMoveStringLiteralDfa1_0(0x80000000000L);
      case 40:
         return jjStopAtPos(0, 54);
      case 41:
         return jjStopAtPos(0, 55);
      case 42:
         return jjStopAtPos(0, 59);
      case 43:
         return jjStopAtPos(0, 57);
      case 44:
         return jjStopAtPos(0, 51);
      case 45:
         return jjStopAtPos(0, 58);
      case 46:
         return jjStopAtPos(0, 64);
      case 47:
         return jjStartNfaWithStates_0(0, 60, 4);
      case 58:
         jjmatchedKind = 53;
         return jjMoveStringLiteralDfa1_0(0x4000000000000L);
      case 59:
         return jjStopAtPos(0, 52);
      case 60:
         jjmatchedKind = 49;
         return jjMoveStringLiteralDfa1_0(0x800000000000L);
      case 61:
         jjmatchedKind = 56;
         return jjMoveStringLiteralDfa1_0(0x100000000000L);
      case 62:
         jjmatchedKind = 48;
         return jjMoveStringLiteralDfa1_0(0x400000000000L);
      case 91:
         return jjStopAtPos(0, 62);
      case 93:
         return jjStopAtPos(0, 63);
      case 95:
         return jjStopAtPos(0, 41);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x240000L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x10800L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x100L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x20000000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0xc801000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x100002000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x201020400L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x1000400000L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x180000L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x400000080L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x40004000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x2008000L);
      case 117:
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x200L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x10000000L);
      case 121:
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 124:
         return jjMoveStringLiteralDfa1_0(0x40000000000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, 0L);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x80000000000L) != 0L)
            return jjStopAtPos(1, 43);
         break;
      case 61:
         if ((active0 & 0x100000000000L) != 0L)
            return jjStopAtPos(1, 44);
         else if ((active0 & 0x200000000000L) != 0L)
            return jjStopAtPos(1, 45);
         else if ((active0 & 0x400000000000L) != 0L)
            return jjStopAtPos(1, 46);
         else if ((active0 & 0x800000000000L) != 0L)
            return jjStopAtPos(1, 47);
         else if ((active0 & 0x4000000000000L) != 0L)
            return jjStopAtPos(1, 50);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x840004800L);
      case 102:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(1, 19, 16);
         else if ((active0 & 0x1000000L) != 0L)
            return jjStartNfaWithStates_0(1, 24, 16);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x12000000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0xc000100L);
      case 110:
         if ((active0 & 0x200000000L) != 0L)
         {
            jjmatchedKind = 33;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x80221000L);
      case 111:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(1, 29, 16);
         else if ((active0 & 0x1000000000L) != 0L)
         {
            jjmatchedKind = 36;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x100410000L);
      case 114:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(1, 20, 16);
         return jjMoveStringLiteralDfa2_0(active0, 0x400040080L);
      case 115:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(1, 10, 16);
         break;
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000L);
      case 124:
         if ((active0 & 0x40000000000L) != 0L)
            return jjStopAtPos(1, 42);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0, 0L);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, 0L);
      return 2;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x100L);
      case 100:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(2, 12, 16);
         else if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(2, 21, 16);
         break;
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000000L);
      case 103:
         return jjMoveStringLiteralDfa3_0(active0, 0x800L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x414000000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x10080L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x40008000L);
      case 114:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(2, 9, 16);
         return jjMoveStringLiteralDfa3_0(active0, 0x100040000L);
      case 115:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(2, 35, 16);
         return jjMoveStringLiteralDfa3_0(active0, 0x8000000L);
      case 116:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(2, 22, 16);
         return jjMoveStringLiteralDfa3_0(active0, 0x80824000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0, 0L);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, 0L);
      return 3;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000L);
      case 101:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(3, 15, 16);
         else if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(3, 27, 16);
         return jjMoveStringLiteralDfa4_0(active0, 0x140820000L);
      case 102:
         if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(3, 26, 16);
         break;
      case 103:
         return jjMoveStringLiteralDfa4_0(active0, 0x80L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000800L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x10010000L);
      case 110:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(3, 25, 16);
         return jjMoveStringLiteralDfa4_0(active0, 0x400000000L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x100L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, 0L);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, 0L);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x140000000L);
      case 101:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(4, 28, 16);
         return jjMoveStringLiteralDfa5_0(active0, 0x10000L);
      case 103:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000L);
      case 108:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(4, 31, 16);
         break;
      case 110:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(4, 11, 16);
         return jjMoveStringLiteralDfa5_0(active0, 0x800000L);
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x4080L);
      case 115:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(4, 8, 16);
         break;
      case 116:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(4, 34, 16);
         return jjMoveStringLiteralDfa5_0(active0, 0x2000L);
      case 121:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(4, 18, 16);
         break;
      default :
         break;
   }
   return jjStartNfa_0(3, active0, 0L);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0, 0L);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x10080L);
      case 99:
         return jjMoveStringLiteralDfa6_0(active0, 0x100000000L);
      case 100:
         return jjMoveStringLiteralDfa6_0(active0, 0x800000L);
      case 101:
         return jjMoveStringLiteralDfa6_0(active0, 0x20000L);
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000L);
      case 110:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(5, 14, 16);
         break;
      case 116:
         if ((active0 & 0x40000000L) != 0L)
            return jjStartNfaWithStates_0(5, 30, 16);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0, 0L);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0, 0L);
      return 6;
   }
   switch(curChar)
   {
      case 104:
         if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(6, 32, 16);
         break;
      case 109:
         if ((active0 & 0x80L) != 0L)
            return jjStartNfaWithStates_0(6, 7, 16);
         break;
      case 110:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(6, 16, 16);
         break;
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x2000L);
      case 114:
         if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(6, 17, 16);
         break;
      case 115:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(6, 23, 16);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0, 0L);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0, 0L);
      return 7;
   }
   switch(curChar)
   {
      case 110:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(7, 13, 16);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0, 0L);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 16;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(10, 11);
                  else if (curChar == 47)
                     jjCheckNAddStates(0, 2);
                  break;
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 39)
                        kind = 39;
                     jjCheckNAdd(15);
                  }
                  else if (curChar == 47)
                     jjAddStates(3, 4);
                  break;
               case 16:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 37)
                        kind = 37;
                     jjCheckNAdd(2);
                  }
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(1, 2);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(1, 2);
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  jjCheckNAdd(2);
                  break;
               case 3:
                  if (curChar == 47)
                     jjAddStates(3, 4);
                  break;
               case 5:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 6:
                  if ((0x2400L & l) != 0L && kind > 5)
                     kind = 5;
                  break;
               case 7:
                  if (curChar == 10 && kind > 5)
                     kind = 5;
                  break;
               case 8:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 9:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 10:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 11:
                  if (curChar == 42)
                     jjAddStates(5, 6);
                  break;
               case 12:
                  if ((0xffff7fffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(13, 11);
                  break;
               case 13:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(13, 11);
                  break;
               case 14:
                  if (curChar == 47 && kind > 6)
                     kind = 6;
                  break;
               case 15:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 39)
                     kind = 39;
                  jjCheckNAdd(15);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  jjCheckNAddTwoStates(1, 2);
                  break;
               case 16:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(1, 2);
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 37)
                        kind = 37;
                     jjCheckNAdd(2);
                  }
                  break;
               case 1:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjCheckNAddTwoStates(1, 2);
                  break;
               case 2:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 37)
                     kind = 37;
                  jjCheckNAdd(2);
                  break;
               case 5:
                  jjAddStates(0, 2);
                  break;
               case 10:
                  jjCheckNAddTwoStates(10, 11);
                  break;
               case 12:
               case 13:
                  jjCheckNAddTwoStates(13, 11);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 5:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(0, 2);
                  break;
               case 10:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(10, 11);
                  break;
               case 12:
               case 13:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjCheckNAddTwoStates(13, 11);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 16 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   5, 6, 8, 4, 9, 12, 14, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, "\160\162\157\147\162\141\155", 
"\143\154\141\163\163", "\166\141\162", "\151\163", "\142\145\147\151\156", "\145\156\144", 
"\146\165\156\143\164\151\157\156", "\162\145\164\165\162\156", "\164\171\160\145", 
"\142\157\157\154\145\141\156", "\151\156\164\145\147\145\162", "\141\162\162\141\171", "\157\146", 
"\157\162", "\141\156\144", "\156\157\164", "\145\170\164\145\156\144\163", "\151\146", 
"\164\150\145\156", "\145\154\151\146", "\145\154\163\145", "\167\150\151\154\145", "\144\157", 
"\162\145\160\145\141\164", "\165\156\164\151\154", "\146\157\162\145\141\143\150", "\151\156", 
"\160\162\151\156\164", "\171\145\163", "\156\157", null, null, null, null, "\137", "\174\174", 
"\46\46", "\75\75", "\41\75", "\76\75", "\74\75", "\76", "\74", "\72\75", "\54", "\73", 
"\72", "\50", "\51", "\75", "\53", "\55", "\52", "\57", "\45", "\133", "\135", "\56", 
"\41", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xffffffbfffffff81L, 0x3L, 
};
static final long[] jjtoSkip = {
   0x7eL, 0x0L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[16];
private final int[] jjstateSet = new int[32];
protected char curChar;
/** Constructor. */
public MyLangTreeTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public MyLangTreeTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 16; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
