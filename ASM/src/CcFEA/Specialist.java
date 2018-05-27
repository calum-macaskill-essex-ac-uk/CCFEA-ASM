package CcFEA;

/*     */// import java.io.PrintStream;
/*     */ import java.util.LinkedList;
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
/*     */ public class Specialist
/*     */ {
/*  18 */   final int SP_RE = 0;
/*     */   
/*  20 */   final int SP_SLOPE = 1;
/*     */   
/*  22 */   final int SP_ETA = 2;
/*     */   
/*     */ 
/*     */ 
/*     */   double maxprice;
/*     */   
/*     */ 
/*     */ 
/*     */   double minprice;
/*     */   
/*     */ 
/*     */ 
/*     */   double eta;
/*     */   
/*     */ 
/*     */ 
/*     */   double minexcess;
/*     */   
/*     */ 
/*     */ 
/*     */   double rea;
/*     */   
/*     */ 
/*     */ 
/*     */   double reb;
/*     */   
/*     */ 
/*     */ 
/*     */   double bidfrac;
/*     */   
/*     */ 
/*     */   double offerfrac;
/*     */   
/*     */ 
/*     */   int maxiterations;
/*     */   
/*     */ 
/*     */   static double volume;
/*     */   
/*     */ 
/*     */   double taupdecay;
/*     */   
/*     */ 
/*     */   double taupnew;
/*     */   
/*     */ 
/*     */   int sptype;
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setMaxPrice(double maximumPrice)
/*     */   {
/*  74 */     this.maxprice = maximumPrice;
/*  75 */     return this;
/*     */   }
/*     */   
/*     */   public Object setMinPrice(double minimumPrice)
/*     */   {
/*  80 */     this.minprice = minimumPrice;
/*  81 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setTaup(double aTaup)
/*     */   {
/*  87 */     this.taupnew = (1.0D - Math.exp(-1.0D / aTaup));
/*  88 */     this.taupdecay = (1.0D - this.taupnew);
/*  89 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setSPtype(int i)
/*     */   {
/* 100 */     if ((i != 0) && (i != 1) && (i != 2))
/*     */     {
/* 102 */       System.out.println("The specialist type chosen is invalid.  Only 0, 1, or 2 are acceptable.  The Specialist will be set to Slope (i.e., 1).");
/* 103 */       i = 1;
/*     */     }
/* 105 */     this.sptype = i;
/*     */     
/* 107 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setMaxIterations(int someIterations)
/*     */   {
/* 114 */     this.maxiterations = someIterations;
/* 115 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setMinExcess(double minimumExcess)
/*     */   {
/* 122 */     this.minexcess = minimumExcess;
/* 123 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setETA(double ETA)
/*     */   {
/* 129 */     this.eta = ETA;
/* 130 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setREA(double REA)
/*     */   {
/* 136 */     this.rea = REA;
/* 137 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setREB(double REB)
/*     */   {
/* 143 */     this.reb = REB;
/* 144 */     return this;
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
/*     */   public double performTrading$Market(LinkedList agentList, World worldForSpec)
/*     */   {
/* 167 */     double slopetotal = 0.0D;
/* 168 */     double trialprice = 0.0D;
/* 169 */     double offertotal = 0.0D;
/* 170 */     double bidtotal = 0.0D;
/*     */     
/*     */ 
/* 173 */     LinkedList index = new LinkedList();
/*     */     
/* 175 */     volume = 0.0D;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 182 */     double dividend = worldForSpec.getDividend();
/*     */     
/* 184 */     int mcount = 0; for (boolean done = false; (mcount < this.maxiterations) && (!done); mcount++)
/*     */     {
/*     */ 
/* 187 */       switch (this.sptype)
/*     */       {
/*     */ 
/*     */ 
/*     */       case 0: 
/* 192 */         trialprice = this.rea * dividend + this.reb;
/* 193 */         done = true;
/* 194 */         break;
/*     */       
/*     */       case 1: 
/* 197 */         if (mcount == 0) {
/* 198 */           trialprice = worldForSpec.getPrice();
/*     */ 
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/* 205 */           double imbalance = bidtotal - offertotal;
/* 206 */           if ((imbalance <= this.minexcess) && (imbalance >= -this.minexcess))
/*     */           {
/* 208 */             done = true;
/* 209 */             continue;
/*     */           }
/*     */           
/* 212 */           if (slopetotal != 0.0D) {
/* 213 */             trialprice -= imbalance / slopetotal;
/*     */           } else
/* 215 */             trialprice *= (1.0D + this.eta * imbalance);
/*     */         }
/* 217 */         break;
/*     */       
/*     */ 
/*     */       case 2: 
/* 221 */         if (mcount == 0)
/*     */         {
/* 223 */           trialprice = worldForSpec.getPrice();
/*     */         }
/*     */         else
/*     */         {
/* 227 */           trialprice = worldForSpec.getPrice() * (1.0D + 
/* 228 */             this.eta * (bidtotal - offertotal));
/* 229 */           done = true;
/*     */         }
/*     */         
/*     */         break;
/*     */       }
/*     */       
/* 235 */       if (trialprice < this.minprice)
/* 236 */         trialprice = this.minprice;
/* 237 */       if (trialprice > this.maxprice) {
/* 238 */         trialprice = this.maxprice;
/*     */       }
/*     */       
/* 241 */       bidtotal = 0.0D;
/* 242 */       offertotal = 0.0D;
/* 243 */       slopetotal = 0.0D;
/* 244 */       index = agentList;
/* 245 */       for (int i = 0; i < index.size(); i++)
/*     */       {
/* 247 */         Agent agent = (Agent)index.get(i);
/* 248 */         double slope = 0.0D;
/* 249 */         double demand = agent.getDemandAndSlope$forPrice(slope, trialprice);
/* 250 */         slopetotal += slope;
/* 251 */         if (demand > 0.0D) {
/* 252 */           bidtotal += demand;
/* 253 */         } else if (demand < 0.0D) {
/* 254 */           offertotal -= demand;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 259 */       volume = bidtotal > offertotal ? offertotal : bidtotal;
/* 260 */       this.bidfrac = (bidtotal > 0.0D ? volume / bidtotal : 0.0D);
/* 261 */       this.offerfrac = (offertotal > 0.0D ? volume / offertotal : 0.0D);
/*     */     }
/*     */     
/* 264 */     return trialprice;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getVolume()
/*     */   {
/* 270 */     return volume;
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
/*     */   public Object completeTrades$Market(LinkedList agentList, World worldForSpec)
/*     */   {
/* 290 */     LinkedList index = new LinkedList();
/*     */     
/* 292 */     double price = 0.0D;
/*     */     
/* 294 */     price = worldForSpec.getPrice();
/* 295 */     double profitperunit = worldForSpec.getProfitPerUnit();
/*     */     
/*     */ 
/* 298 */     double bfp = this.bidfrac * price;
/* 299 */     double ofp = this.offerfrac * price;
/* 300 */     double tp = this.taupnew * profitperunit;
/*     */     
/*     */ 
/* 303 */     index = agentList;
/*     */     
/* 305 */     for (int i = 0; i < index.size(); i++)
/*     */     {
/* 307 */       Agent agent = (Agent)index.get(i);
/*     */       
/* 309 */       agent.profit = (this.taupdecay * agent.profit + tp * agent.position);
/*     */       
/*     */ 
/* 312 */       if (agent.demand > 0.0D)
/*     */       {
/* 314 */         agent.position += agent.demand * this.bidfrac;
/* 315 */         agent.cash -= agent.demand * bfp;
/*     */       }
/* 317 */       else if (agent.demand < 0.0D)
/*     */       {
/* 319 */         agent.position += agent.demand * this.offerfrac;
/* 320 */         agent.cash -= agent.demand * ofp;
/*     */       }
/*     */     }
/*     */     
/* 324 */     return this;
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\Specialist.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */