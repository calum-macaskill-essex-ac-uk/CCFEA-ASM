package CcFEA;

/*     */ //import java.io.PrintStream;
/*     */ import org.apache.log4j.Logger;

/*     */ public class BitVector
/*     */ {
/*     */   int condwords;
/*     */   int condbits;
/*     */   int[] conditions;
/*     */   public static final int MAXCONDBITS = 80;
/*   9 */   public static int[] SHIFT = new int[80];
/*  10 */   public static int[] MASK = new int[80];
/*  11 */   public static int[] NMASK = new int[80];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int WORD(int bit)
/*     */   {
/*  21 */     int a = bit >> 4;
/*  22 */     return a;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object createEnd()
/*     */   {
/* 115 */     if (this.condwords == 0) { System.out.println("Must have condwords to create BFCast. 1");
/*     */     }
/* 117 */     this.conditions = new int[this.condwords];
/* 118 */     for (int i = 0; i < this.condwords; i++)
/* 119 */       this.conditions[i] = 0;
/* 120 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void init() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCondwords(int x)
/*     */   {
/* 133 */     this.condwords = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCondbits(int x)
/*     */   {
/* 139 */     this.condbits = x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setConditions(int[] x)
/*     */   {
/* 150 */     for (int i = 0; i < this.condwords; i++) { this.conditions[i] = x[i];
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int[] getConditions()
/*     */   {
/* 157 */     return this.conditions;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setConditionsWord$To(int i, int value)
/*     */   {
/* 163 */     this.conditions[i] = value;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getConditionsWord(int i)
/*     */   {
/* 169 */     return this.conditions[i];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setConditionsbit$To(int bit, int x)
/*     */   {
/* 176 */     this.conditions[WORD(bit)] = (this.conditions[WORD(bit)] & NMASK[bit] | x << SHIFT[bit]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setConditionsbit$FromZeroTo(int bit, int x)
/*     */   {
/* 183 */     this.conditions[WORD(bit)] |= x << SHIFT[bit];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getConditionsbit(int bit)
/*     */   {
/* 190 */     int value = this.conditions[WORD(bit)] >> SHIFT[bit] & 0x3;
/* 191 */     return value;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setConditionsbitToThree(int bit)
/*     */   {
/* 197 */     this.conditions[WORD(bit)] |= MASK[bit];
/*     */   }
/*     */   
/*     */   public void maskConditionsbit(int bit)
/*     */   {
/* 202 */     this.conditions[WORD(bit)] &= NMASK[bit];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void switchConditionsbit(int bit)
/*     */   {
/* 209 */     this.conditions[WORD(bit)] ^= MASK[bit];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void makebittables()
/*     */   {
/* 279 */     for (int bit = 0; bit < 80; bit++)
/*     */     {
/* 281 */       SHIFT[bit] = (bit % 16 * 2);
/* 282 */       MASK[bit] = (3 << SHIFT[bit]);
/* 283 */       MASK[bit] ^= 0xFFFFFFFF;
/*     */     }
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\BitVector.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */