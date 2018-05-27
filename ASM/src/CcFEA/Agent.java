package CcFEA;
import jas.graph.RelationalAgent;
import org._3pq.jgrapht.Graph;
import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
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
/*     */   final static Logger logger = Logger.getLogger(Agent.class);
			private static int count;
/*     */   Agent(Graph g)
/*     */   {
/*  35 */     	super(g);
				count = 0;
/*     */   }
/*     */   
/*     */ 
/*     */
/*     */ 
/*     */   public static void setWorld(World aWorld)
/*     */   {
	          	logger.info(count++  + "  About to setWotld : " + "Agent");
/*  43 */     	worldForAgent = aWorld;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setID(int iD)
/*     */   {
				logger.info(count++  + " About to setId : " + "Agent");
/*  51 */     	this.myID = iD;
/*  52 */     	return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setPosition(double aDouble)
/*     */   {
			   	logger.info(count++  + " About to setPosition : " + "Agent");
/*  59 */     	this.position = aDouble;
/*  60 */     	return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setintrate(double rate)
/*     */   {
				logger.info(count++  + " About to setintrate : " + "Agent");
/*  67 */     	this.intrate = rate;
/*  68 */     	this.intratep1 = (this.intrate + 1.0D);
/*  69 */     	return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setminHolding$minCash(double holding, double minimumcash)
/*     */   {
				logger.info(count++  + " About to setminHolding$minCash : " + "Agent");
/*  77 */     	this.minholding = holding;
/*  78 */     	this.mincash = minimumcash;
/*  79 */     	return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setInitialCash(double initcash)
/*     */   {
				logger.info(count++  + " About to setInitialCash : " + "Agent");
/*  86 */     	this.initialcash = initcash;
/*  87 */     	return this;
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
				logger.info (count++  +  "  About to setInitialHoldings : " + "Agent");
/*  98 */     	this.profit = 0.0D;
/*  99 */     	this.wealth = 0.0D;
/* 100 */     	this.cash = this.initialcash;
/* 101 */     	this.position = 0.0D;
/*     */     
/* 103 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getPriceFromWorld()
/*     */   {
				logger.info(count++  + "  About to getPriceFromWorld : " + "Agent");
/* 111 */     	this.price = worldForAgent.getPrice();
/* 112 */     	return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object getDividendFromWorld()
/*     */   {
				logger.info(count++  + " About to getDividendFromWorld : " + "Agent");
/* 118 */     	this.dividend = worldForAgent.getDividend();
/* 119 */     	return this;
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
				logger.info(count++  + " About to setInitialCash : " + "Agent");
/* 144 */    	getPriceFromWorld();
/* 145 */     	getDividendFromWorld();
/*     */     
/*     */ 
/* 148 */     	this.cash -= (this.price * this.intrate - this.dividend) * this.position;
/* 149 */     	if (this.cash < this.mincash) {
/* 150 */     	this.cash = this.mincash;
/*     */     }	
/*     */     
/* 153 */     	this.wealth = (this.cash + this.price * this.position);
/*     */     
/* 155 */     	return this;
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
				logger.info(count++  + " About to constrainDemand : " + "Agent");
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
				logger.info(count++  + " About to getAgentPosition : " + "Agent");
/* 206 */     return this.position;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getWealth()
/*     */   {
				logger.info(count++  + " About to getWealth : " + "Agent");
/* 212 */     return this.wealth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getCash()
/*     */   {
				logger.info(count++  + " About to getCash : " + "Agent");
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
				logger.info(count++  + " About to prepareForTrading : " + "Agent");
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
				logger.info(count++  + " About to getDemandAndSlope$forPrice : " + "Agent");
/* 252 */     return 0.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object updatePerformance()
/*     */   {
				logger.info(count++  + " About to updatePerformance : " + "Agent");
/* 261 */     return this;
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\Agent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */