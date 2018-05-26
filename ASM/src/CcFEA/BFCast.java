package CcFEA;

/*     */ //import java.io.PrintStream;
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
/*     */ public class BFCast
/*     */ {
/*     */   double forecast;
/*     */   double lforecast;
/*     */   double variance;
/*     */   double strength;
/*     */   double a;
/*     */   double b;
/*     */   double c;
/*     */   double specfactor;
/*     */   double bitcost;
/*     */   BitVector conditions;
/*     */   int lastactive;
/*     */   int specificity;
/*     */   int count;
/*     */   int condwords;
/*     */   int condbits;
/*     */   int nnulls;
/*     */   
/*     */   public Object createEnd()
/*     */   {
/*  38 */     if ((this.condwords == 0) || (this.condbits == 0)) { System.out.println("Must have condwords to create BFCast.0");
/*     */     }
/*  40 */     this.forecast = 0.0D;
/*  41 */     this.count = 0;
/*  42 */     this.lastactive = 1;
/*  43 */     this.specificity = 0;
/*  44 */     this.variance = 9.99999999E8D;
/*  45 */     this.conditions = new BitVector();
/*  46 */     this.conditions.setCondwords(this.condwords);
/*  47 */     this.conditions.setCondbits(this.condbits);
/*  48 */     this.conditions.createEnd();
/*  49 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void init() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCondwords(int x)
/*     */   {
/*  65 */     this.condwords = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCondbits(int x)
/*     */   {
/*  71 */     this.condbits = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setNNulls(int x)
/*     */   {
/*  77 */     //this.nnulls = this.nnulls;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBitcost(double x)
/*     */   {
/*  83 */     this.bitcost = x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setConditions(int[] x)
/*     */   {
/*  91 */     this.conditions.setConditions(x);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int[] getConditions()
/*     */   {
/*  98 */     return this.conditions.getConditions();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BitVector getConditionsObject()
/*     */   {
/* 106 */     return this.conditions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setConditionsWord$To(int i, int value)
/*     */   {
/* 113 */     this.conditions.setConditionsWord$To(i, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getConditionsWord(int x)
/*     */   {
/* 120 */     return this.conditions.getConditionsWord(x);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setConditionsbit$To(int bit, int x)
/*     */   {
/* 126 */     this.conditions.setConditionsbit$To(bit, x);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setConditionsbit$FromZeroTo(int bit, int x)
/*     */   {
/* 133 */     this.conditions.setConditionsbit$FromZeroTo(bit, x);
/*     */   }
/*     */   
/*     */ 
/*     */   public int getConditionsbit(int bit)
/*     */   {
/* 139 */     return this.conditions.getConditionsbit(bit);
/*     */   }
/*     */   
/*     */ 
/*     */   public void maskConditionsbit(int bit)
/*     */   {
/* 145 */     this.conditions.maskConditionsbit(bit);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void switchConditionsbit(int bit)
/*     */   {
/* 153 */     this.conditions.switchConditionsbit(bit);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setAval(double x)
/*     */   {
/* 159 */     this.a = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setBval(double x)
/*     */   {
/* 165 */     this.b = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setCval(double x)
/*     */   {
/* 171 */     this.c = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getAval()
/*     */   {
/* 177 */     return this.a;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getBval()
/*     */   {
/* 183 */     return this.b;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getCval()
/*     */   {
/* 189 */     return this.c;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void updateSpecfactor()
/*     */   {
/* 199 */     this.specfactor = ((this.condbits - this.nnulls - this.specificity) * this.bitcost);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setSpecfactor(double x)
/*     */   {
/* 206 */     this.specfactor = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getSpecfactor()
/*     */   {
/* 212 */     return this.specfactor;
/*     */   }
/*     */   
/*     */ 
/*     */   public void incrSpecificity()
/*     */   {
/* 218 */     this.specificity += 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void decrSpecificity()
/*     */   {
/* 224 */     this.specificity -= 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setSpecificity(int x)
/*     */   {
/* 230 */     this.specificity = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getSpecificity()
/*     */   {
/* 236 */     return this.specificity;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setVariance(double x)
/*     */   {
/* 242 */     this.variance = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getVariance()
/*     */   {
/* 248 */     return this.variance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLastactive(int x)
/*     */   {
/* 255 */     this.lastactive = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getLastactive()
/*     */   {
/* 261 */     return this.lastactive;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getCnt()
/*     */   {
/* 268 */     return this.count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setCnt(int x)
/*     */   {
/* 275 */     this.count = x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int incrCount()
/*     */   {
/* 282 */     return ++this.count;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getStrength()
/*     */   {
/* 288 */     return this.strength;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setStrength(double x)
/*     */   {
/* 295 */     this.strength = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLforecast(double x)
/*     */   {
/* 301 */     this.lforecast = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getLforecast()
/*     */   {
/* 307 */     return this.lforecast;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setForecast(double x)
/*     */   {
/* 314 */     this.forecast = x;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getForecast()
/*     */   {
/* 320 */     return this.forecast;
/*     */   }
/*     */   
/*     */ 
/*     */   public double updateForecastPrice$Dividend(double price, double dividend)
/*     */   {
/* 326 */     this.lforecast = this.forecast;
/* 327 */     this.forecast = (this.a * (price + dividend) + this.b * dividend + this.c);
/* 328 */     return this.forecast;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object copyEverythingFrom(BFCast from)
/*     */   {
/* 336 */     this.forecast = from.getForecast();
/* 337 */     this.lforecast = from.getLforecast();
/* 338 */     this.variance = from.getVariance();
/* 339 */     this.strength = from.getStrength();
/* 340 */     this.a = from.getAval();
/* 341 */     this.b = from.getBval();
/* 342 */     this.c = from.getCval();
/* 343 */     this.specfactor = from.getSpecfactor();
/* 344 */     this.lastactive = from.getLastactive();
/* 345 */     this.specificity = from.getSpecificity();
/* 346 */     this.count = from.getCnt();
/* 347 */     setConditions(from.getConditions());
/* 348 */     return this;
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
/*     */   public Object printcond(int word)
/*     */   {
/* 368 */     return this;
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\BFCast.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */