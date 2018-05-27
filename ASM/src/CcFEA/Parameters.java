package CcFEA;

/*     */ //import java.io.PrintStream;
/*     */ import org.apache.log4j.Logger;
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
/*     */ public class Parameters
/*     */ {
/*     */   public ASMModelParams asmModelParams;
/*     */   public BFParams bfParams;
/*     */   int run;
/*     */   
/*     */   Parameters()
/*     */   {
/*  43 */     this.asmModelParams = new ASMModelParams();
/*  44 */     this.bfParams = new BFParams();
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
/*     */   public Object init()
/*     */   {
/*  86 */     this.bfParams.init();
/*  87 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ASMModelParams getModelParams()
/*     */   {
/*  95 */     return this.asmModelParams;
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
/*     */   public BFParams getBFParams()
/*     */   {
/* 108 */     return this.bfParams;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRunArg()
/*     */   {
/* 116 */     return this.run;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object sayHello()
/*     */   {
/* 123 */     System.out.println("Lo que hacemos en vida se refleja en la eternidad");
/* 124 */     return this;
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\Parameters.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */