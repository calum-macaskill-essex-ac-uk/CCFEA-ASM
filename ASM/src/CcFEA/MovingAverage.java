package CcFEA;

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
/*     */ public class MovingAverage
/*     */ {
/*     */   int width;
/*     */   int numInputs;
/*     */   double[] maInputs;
/*     */   int arrayPosition;
/*     */   double sumOfInputs;
/*     */   double uncorrectedSum;
/*     */   double expWMA;
/*     */   double aweight;
/*     */   double bweight;
/*     */   
/*     */   public Object initWidth(int w)
/*     */   {
/*  37 */     this.width = w;
/*     */     
/*  39 */     this.maInputs = new double[w];
/*  40 */     for (int i = 0; i < w; i++)
/*     */     {
/*  42 */       this.maInputs[i] = 0.0D;
/*     */     }
/*  44 */     this.numInputs = 0;
/*  45 */     this.sumOfInputs = 0.0D;
/*  46 */     this.arrayPosition = 0;
/*  47 */     this.uncorrectedSum = 0.0D;
/*     */     
/*  49 */     this.bweight = (1.0D - Math.exp(-1.0D / w));
/*  50 */     this.aweight = (1.0D - this.bweight);
/*     */     
/*  52 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object initWidth$Value(int w, double val)
/*     */   {
/*  59 */     this.width = w;
/*  60 */     this.maInputs = new double[w];
/*  61 */     for (int i = 0; i < w; i++)
/*     */     {
/*  63 */       this.maInputs[i] = val;
/*     */     }
/*  65 */     this.numInputs = w;
/*  66 */     this.sumOfInputs = (w * val);
/*  67 */     this.arrayPosition = 0;
/*  68 */     this.uncorrectedSum = (w * val);
/*     */     
/*  70 */     this.bweight = (1.0D - Math.exp(-1.0D / w));
/*  71 */     this.aweight = (1.0D - this.bweight);
/*  72 */     this.expWMA = val;
/*     */     
/*  74 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getNumInputs()
/*     */   {
/*  81 */     return this.numInputs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMA()
/*     */   {
/*  89 */     if (this.numInputs == 0) return 0.0D;
/*  90 */     double movingAverage; 
				if (this.numInputs < this.width)
/*     */     {
/*  92 */       movingAverage = this.sumOfInputs / this.numInputs;
/*     */     }
/*     */     else
/*     */     {
/*  96 */       movingAverage = this.sumOfInputs / this.width;
/*     */     }
/*  98 */     return movingAverage;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getAverage()
/*     */   {
/* 104 */     if (this.numInputs == 0) return 0.0D;
/* 105 */     return this.uncorrectedSum / this.numInputs;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getEWMA()
/*     */   {
/* 111 */     return this.expWMA;
/*     */   }
/*     */   
/*     */ 
/*     */   public void addValue(double x)
/*     */   {
/* 117 */     this.arrayPosition = ((this.width + this.numInputs) % this.width);
/* 118 */     if (this.numInputs < this.width)
/*     */     {
/* 120 */       this.sumOfInputs += x;
/* 121 */       this.maInputs[this.arrayPosition] = x;
/*     */     }
/*     */     else
/*     */     {
/* 125 */       this.sumOfInputs = (this.sumOfInputs - this.maInputs[this.arrayPosition] + x);
/* 126 */       this.maInputs[this.arrayPosition] = x;
/*     */     }
/* 128 */     this.numInputs += 1;
/* 129 */     this.uncorrectedSum += x;
/* 130 */     this.expWMA = (this.aweight * this.expWMA + this.bweight * x);
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\MovingAverage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */