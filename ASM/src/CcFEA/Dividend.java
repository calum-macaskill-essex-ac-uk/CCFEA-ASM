package CcFEA;

/*     */ import jas.engine.Sim;
/*     */ //import jas.random.RandomGenerator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Dividend
/*     */ {
/*     */   double baseline;
/*     */   double amplitude;
/*     */   double period;
/*     */   double mindividend;
/*     */   double maxdividend;
/*     */   double deviation;
/*     */   double rho;
/*     */   double gauss;
/*     */   double dvdnd;
/*     */   
/*     */   public Object initNormal()
/*     */   {
/*  53 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setBaseline(double theBaseline)
/*     */   {
/*  59 */     this.baseline = theBaseline;
/*  60 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setmindividend(double minimumDividend)
/*     */   {
/*  66 */     this.mindividend = minimumDividend;
/*  67 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setmaxdividend(double maximumDividend)
/*     */   {
/*  73 */     this.maxdividend = maximumDividend;
/*  74 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double setAmplitude(double theAmplitude)
/*     */   {
/*  86 */     this.amplitude = theAmplitude;
/*  87 */     if (this.amplitude < 0.0D)
/*  88 */       this.amplitude = 0.0D;
/*  89 */     if (this.amplitude > 1.0D)
/*  90 */       this.amplitude = 1.0D;
/*  91 */     this.amplitude = (1.0E-4D * (int)(10000.0D * this.amplitude));
/*  92 */     return this.amplitude;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double setPeriod(double thePeriod)
/*     */   {
/* 102 */     this.period = thePeriod;
/* 103 */     if (this.period < 2.0D)
/* 104 */       this.period = 2.0D;
/* 105 */     return this.period;
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
/*     */   public Object setDerivedParams()
/*     */   {
/* 118 */     this.deviation = (this.baseline * this.amplitude);
/*     */     
/* 120 */     this.rho = Math.exp(-1.0D / this.period);
/* 121 */     this.rho = (1.0E-4D * (int)(10000.0D * this.rho));
/* 122 */     this.gauss = (this.deviation * Math.sqrt(1.0D - this.rho * this.rho));
/*     */     
/*     */ 
/* 125 */     this.dvdnd = (this.baseline + this.gauss * Sim.getRnd().getDblFromTo(0.0D, 1.0D));
/* 126 */     return this;
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
/*     */   public double dividend()
/*     */   {
/* 141 */     this.dvdnd = (this.baseline + this.rho * (this.dvdnd - this.baseline) + this.gauss * Sim.getRnd().getDblFromTo(0.0D, 1.0D));
/* 142 */     if (this.dvdnd < this.mindividend)
/* 143 */       this.dvdnd = this.mindividend;
/* 144 */     if (this.dvdnd > this.maxdividend) {
/* 145 */       this.dvdnd = this.maxdividend;
/*     */     }
/*     */     
/*     */ 
/* 149 */     return this.dvdnd;
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\Dividend.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */