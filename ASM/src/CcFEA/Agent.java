package CcFEA;
import jas.graph.RelationalAgent;
import org._3pq.jgrapht.Graph;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Agent
/*     */   extends RelationalAgent
/*     */ {
/*     */   public double demand;
/*     */   public double profit;
/*     */   public double wealth;
/*     */   public double position;
/*     */   public double cash;
/*     */   public double initialcash;
/*     */   public double minholding;
/*     */   public double mincash;
/*     */   public double intrate;
/*     */   public double intratep1;
/*     */   public double price;
/*     */   public double dividend;
/*     */   public int myID;
/*     */   public static World worldForAgent;
/*     */   
/*     */   Agent(Graph g)
/*     */   {
/*  35 */     super(g);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setWorld(World aWorld)
/*     */   {
/*  43 */     worldForAgent = aWorld;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setID(int iD)
/*     */   {
/*  51 */     this.myID = iD;
/*  52 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setPosition(double aDouble)
/*     */   {
/*  59 */     this.position = aDouble;
/*  60 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setintrate(double rate)
/*     */   {
/*  67 */     this.intrate = rate;
/*  68 */     this.intratep1 = (this.intrate + 1.0D);
/*  69 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setminHolding$minCash(double holding, double minimumcash)
/*     */   {
/*  77 */     this.minholding = holding;
/*  78 */     this.mincash = minimumcash;
/*  79 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setInitialCash(double initcash)
/*     */   {
/*  86 */     this.initialcash = initcash;
/*  87 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setInitialHoldings()
/*     */   {
/*  98 */     this.profit = 0.0D;
/*  99 */     this.wealth = 0.0D;
/* 100 */     this.cash = this.initialcash;
/* 101 */     this.position = 0.0D;
/*     */     
/* 103 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getPriceFromWorld()
/*     */   {
/* 111 */     this.price = worldForAgent.getPrice();
/* 112 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object getDividendFromWorld()
/*     */   {
/* 118 */     this.dividend = worldForAgent.getDividend();
/* 119 */     return this;
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
/*     */   public Object creditEarningsAndPayTaxes()
/*     */   {
/* 144 */     getPriceFromWorld();
/* 145 */     getDividendFromWorld();
/*     */     
/*     */ 
/* 148 */     this.cash -= (this.price * this.intrate - this.dividend) * this.position;
/* 149 */     if (this.cash < this.mincash) {
/* 150 */       this.cash = this.mincash;
/*     */     }
/*     */     
/* 153 */     this.wealth = (this.cash + this.price * this.position);
/*     */     
/* 155 */     return this;
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
/*     */   public double constrainDemand(double slope, double trialprice)
/*     */   {
/* 175 */     if (this.demand > 0.0D) {
/* 176 */       if (this.demand * trialprice > this.cash - this.mincash)
/*     */       {
/* 178 */         if (this.cash - this.mincash > 0.0D) {
/* 179 */           this.demand = ((this.cash - this.mincash) / trialprice);
/* 180 */           slope = -this.demand / trialprice;
/*     */         }
/*     */         else
/*     */         {
/* 184 */           this.demand = 0.0D;
/* 185 */           slope = 0.0D;
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 192 */     else if ((this.demand < 0.0D) && (this.demand + this.position < this.minholding))
/*     */     {
/*     */ 
/* 195 */       this.demand = (this.minholding - this.position);
/* 196 */       slope = 0.0D;
/*     */     }
/*     */     
/*     */ 
/* 200 */     return this.demand;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getAgentPosition()
/*     */   {
/* 206 */     return this.position;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getWealth()
/*     */   {
/* 212 */     return this.wealth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getCash()
/*     */   {
/* 219 */     return this.cash;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object prepareForTrading()
/*     */   {
/* 230 */     return this;
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
/*     */   public double getDemandAndSlope$forPrice(double slope, double p)
/*     */   {
/* 252 */     return 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object updatePerformance()
/*     */   {
/* 261 */     return this;
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\Agent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */