package CcFEA;

/*     */ import jas.engine.Sim;
/*     */ import jas.engine.SimModel;
/*     */// import jas.events.EventList;
/*     */ import jas.events.SimGroupEvent;
/*     */ import jas.graph.GraphViewer;
/*     */ import jas.graph.layout.RegularCircleLayout;
/*     */ //import jas.random.RandomGenerator;
/*     */ import jas.statistics.IDoubleSource;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.io.BufferedWriter;
          //  import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ //import java.io.PrintStream;
/*     */ import java.util.LinkedList;
/*     */ import org._3pq.jgrapht.Graph;
/*     */ import org._3pq.jgrapht.graph.DirectedWeightedMultigraph;
import org.apache.log4j.Logger;


/*     */ 
/*     */ public class ASMModelJas
/*     */   extends SimModel
/*     */   implements IDoubleSource
/*     */ {
/*     */   public static final int MARKET_PRICE = 0;
/*     */   public static final int RATIONAL_EXPECTATIONS = 1;
/*     */   public static final int RISK_NEUTRAL = 2;
/*     */   public static final int VOLUME = 3;
/*     */   public static final int RETRAINING_AGENTS = 4;
/*     */   int modelTime;
/*  29 */   public static LinkedList<BFagent> agentList = new LinkedList<BFagent>();
/*     */   
/*  31 */   public Specialist specialist = new Specialist();
/*     */   
/*     */   public Dividend dividendProcess;
/*     */   
/*  35 */   public World world = new World();
/*     */   
/*     */ 
/*     */   public Output output;
/*     */   
/*  40 */   public BFParams bfParams = new BFParams();
/*     */   
/*  42 */   public ASMModelParams asmModelParams = new ASMModelParams();
/*     */   
/*     */   public AverageWealth averageWealth;
/*     */   
/*     */   public BufferedWriter out;
/*     */   
/*     */   public Graph graph;
/*     */   
/*     */   private GraphViewer graphViewer;

/*     */   final static Logger logger = Logger.getLogger(ASMModelJas.class);
			public static int count;
/*     */ 
/*     */   public void setParameters()
/*     */   {
  				logger.info(count++  + "  About to setWotld : " + "ASMModelJas");
/*  55 */     Sim.openProbe(this.asmModelParams, "Model Parameters");
/*  56 */     Sim.openProbe(this.bfParams, "BF Parameters");
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setParamsModel$BF(ASMModelParams modelParams, BFParams bfp)
/*     */   {
/*  62 */     this.bfParams = bfp;
/*  63 */     this.asmModelParams = modelParams;
/*  64 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setOutputObject(Output obj)
/*     */   {
/*  70 */     this.output = obj;
/*  71 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getNumBFagents()
/*     */   {
/*  77 */     return ASMModelParams.numBFagents;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getInitialCash()
/*     */   {
/*  83 */     return ASMModelParams.initialcash;
/*     */   }
/*     */   
/*     */ 
/*     */   public LinkedList<BFagent> getAgentList()
/*     */   {
/*  89 */     return agentList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public World getWorld()
/*     */   {
/*  99 */     if (this.world == null) System.out.println("Empty world!");
/* 100 */     return this.world;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Specialist getSpecialist()
/*     */   {
/* 108 */     return this.specialist;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Output getOutput()
/*     */   {
/* 118 */     return this.output;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getModelTime()
/*     */   {
/* 129 */     return this.modelTime;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setBatchRandomSeed(int newSeed)
/*     */   {
/* 138 */     ASMModelParams.randomSeed = newSeed;
/* 139 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void buildModel()
/*     */   {
/* 146 */     this.modelTime = 0;
/*     */     
/*     */ 
/*     */ 
/* 150 */     this.asmModelParams = new ASMModelParams();
/*     */     
/* 152 */     Sim.getRnd().setSeed(ASMModelParams.randomSeed);
/*     */     
/*     */ 
/* 155 */     this.dividendProcess = new Dividend();
/* 156 */     this.dividendProcess.initNormal();
/* 157 */     this.dividendProcess.setBaseline(ASMModelParams.baseline);
/* 158 */     this.dividendProcess.setmindividend(ASMModelParams.mindividend);
/* 159 */     this.dividendProcess.setmaxdividend(ASMModelParams.maxdividend);
/* 160 */     this.dividendProcess.setAmplitude(ASMModelParams.amplitude);
/* 161 */     this.dividendProcess.setPeriod(ASMModelParams.period);
/* 162 */     this.dividendProcess.setDerivedParams();
/*     */     
/*     */ 
/*     */ 
/* 166 */     this.world.createBitnameList();
/* 167 */     this.world.setintrate(ASMModelParams.intrate);
/* 168 */     if (ASMModelParams.exponentialMAs == 1) this.world.setExponentialMAs(true); else
/* 169 */       this.world.setExponentialMAs(false);
/* 170 */     this.world.initWithBaseline(ASMModelParams.baseline);
/* 171 */     this.world.setRea$Reb(ASMModelParams.rea, ASMModelParams.reb);
/*     */     
/*     */ 
/*     */ 
/* 175 */     this.specialist.setMaxPrice(ASMModelParams.maxprice);
/* 176 */     this.specialist.setMinPrice(ASMModelParams.minprice);
/* 177 */     this.specialist.setTaup(ASMModelParams.taup);
/* 178 */     this.specialist.setSPtype(ASMModelParams.sptype);
/* 179 */     this.specialist.setMaxIterations(ASMModelParams.maxiterations);
/* 180 */     this.specialist.setMinExcess(ASMModelParams.minexcess);
/* 181 */     this.specialist.setETA(ASMModelParams.eta);
/* 182 */     this.specialist.setREA(ASMModelParams.rea);
/* 183 */     this.specialist.setREB(ASMModelParams.reb);
/*     */     
/*     */ 
/* 186 */     this.output = new Output();
/*     */     
/* 188 */     this.output.setWorld(this.world);
/* 189 */     this.output.setSpecialist(this.specialist);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 195 */     BFagent.init();
/* 196 */     BFagent.setBFParameterObject(this.bfParams);
/* 197 */     BFagent.setWorld(this.world);
/*     */     
/*     */ 
/*     */ 
/* 201 */     this.graph = new DirectedWeightedMultigraph();
/*     */     
/*     */ 
/*     */ 
/* 205 */     for (int i = 0; i < ASMModelParams.numBFagents; i++)
/*     */     {
/*     */ 
/* 208 */       BFagent agent = new BFagent(this.graph);
/* 209 */       agent.setID(i); 
/* 210 */       agent.setLabel(i+"");
/* 211 */       agent.setColor(Color.BLUE);
/* 212 */       agent.setintrate(ASMModelParams.intrate);
/* 213 */       agent.setminHolding$minCash(ASMModelParams.minholding, ASMModelParams.mincash);
/* 214 */       agent.setInitialCash(ASMModelParams.initialcash);
/* 215 */       agent.setInitialHoldings();
/* 216 */       agent.setPosition(ASMModelParams.initholding);
/* 217 */       agent.initForecasts2();
/* 218 */       agentList.add(agent);
/*     */     }
/*     */     
/*     */ 
/* 222 */     this.averageWealth = new AverageWealth();
/* 223 */     this.averageWealth.InitList(agentList, ASMModelParams.numBFagents);
/*     */     
/* 225 */     for (int c = 0; c < 502; c++) { doWarmupStep();
/*     */     }
/*     */     
/* 228 */     new RegularCircleLayout(this.graph, new Dimension(500, 500)).init();
/*     */     
/*     */ 
/* 231 */     this.graphViewer = new GraphViewer();
/* 232 */     this.graphViewer.setMinMaxWeightValue(0.0D, 10.0D);
/* 233 */     this.graphViewer.setDrawNodeLabel(true);
/* 234 */     this.graphViewer.setGraph(this.graph, new Dimension(500, 500));
/*     */     
/* 236 */     addSimWindow(this.graphViewer);
/*     */     
/*     */ 
/* 239 */     buildAction();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object writeParams()
/*     */   {
/* 249 */     if ((this.asmModelParams != null) && (this.bfParams != null))
/* 250 */       this.output.writeParams$BFAgent$Time(this.asmModelParams, this.bfParams, this.modelTime);
/* 251 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void buildAction()
/*     */   {
/* 260 */     SimGroupEvent periodActions = this.eventList.scheduleGroup(0L, 1);
/*     */     
/* 262 */     this.eventList.scheduleSimple(0L, 1, this, "periodStepDividend");
/* 263 */     periodActions.addCollectionEvent(agentList, BFagent.class, "creditEarningsAndPayTaxes");
/* 264 */     this.eventList.scheduleSimple(0L, 1, this.world, "updateWorld");
/* 265 */     periodActions.addCollectionEvent(agentList, BFagent.class, "prepareForTrading2");
/* 266 */     this.eventList.scheduleSimple(0L, 1, this, "periodStepPrice");
/* 267 */     this.eventList.scheduleSimple(0L, 1, this, "completeTrades$Market");
/* 268 */     periodActions.addCollectionEvent(agentList, BFagent.class, "updatePerformance2");
/*     */     
/* 270 */     if (ASMModelParams.batch)
/*     */     {
/* 272 */       this.eventList.scheduleSimple(ASMModelParams.numOfIterations - 10000, 1, this, "printResultsPrice");
/* 273 */       this.eventList.scheduleSimple(ASMModelParams.numOfIterations - 1000, 1, this, "printResultsAgentWealth");
/*     */     }
/*     */     
/*     */ 
/* 277 */     this.eventList.scheduleSimple(0L, 1, this, "agentColor");
/* 278 */     this.eventList.scheduleSimple(0L, 1, this.graphViewer, 10003);
/*     */     
/* 280 */     this.eventList.scheduleSystem(ASMModelParams.numOfIterations, 10000);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object doWarmupStep()
/*     */   {
/* 291 */     double div = this.dividendProcess.dividend();
/* 292 */     this.world.setDividend(div);
/* 293 */     this.world.updateWorld();
/* 294 */     this.world.setPrice(div / ASMModelParams.intrate);
/* 295 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object periodStepDividend()
/*     */   {
/* 307 */     this.modelTime += 1;
/* 308 */     this.world.setDividend(this.dividendProcess.dividend());
/*     */     
/* 310 */     this.world.setAverageWealth(this.averageWealth.averageWealth());
/*     */     
/* 312 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object periodStepPrice()
/*     */   {
/* 319 */     this.world.setPrice(this.specialist.performTrading$Market(agentList, this.world));
/* 320 */     return this;
/*     */   }
/*     */   
/*     */   public Object completeTrades$Market()
/*     */   {
/* 325 */     this.specialist.completeTrades$Market(agentList, this.world);
/* 326 */     return this;
/*     */   }
/*     */   
/*     */   public void printPrice()
/*     */   {
/* 331 */     System.out.println(this.world.getPrice() + "  " + this.world.getRationalExpectations() + 
/* 332 */       "  " + this.world.getRiskNeutral() + "  " + this.specialist.getVolume());
/*     */   }
/*     */   
/*     */ 
/*     */   public void printBatch()
/*     */   {
/* 338 */     System.out.println("Step... " + Sim.getAbsoluteTime());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDoubleValue(int valueId)
/*     */   {
/* 346 */     switch (valueId) {
/*     */     case 0: 
/* 348 */       return this.world.getPrice();
/* 349 */     case 1:  return this.world.getRationalExpectations();
/* 350 */     case 2:  return this.world.getRiskNeutral();
/* 351 */     case 3:  return this.specialist.getVolume();
/* 352 */     case 4:  return numRetrainingAgents(); }
/* 353 */     throw new UnsupportedOperationException("Bad argument");
/*     */   }
/*     */   
/*     */ 
/*     */   public void printResultsPrice()
/*     */   {
/*     */     try
/*     */     {
/* 361 */       this.out = new BufferedWriter(new FileWriter("Price.txt", true));
/* 362 */       this.out.write("\n" + Sim.getAbsoluteTime() + "\t");
/* 363 */       this.out.write(this.world.getPrice() + "\t");
/* 364 */       this.out.write(this.world.getRationalExpectations() + "\t");
/* 365 */       this.out.write(this.world.getRiskNeutral() + "\t");
/* 366 */       this.out.close();
/*     */     }
/*     */     catch (IOException localIOException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void printResultsAgentWealth()
/*     */   {
/*     */     try
/*     */     {
/* 378 */       this.out = new BufferedWriter(new FileWriter("AgentWealth.txt", true));
/* 379 */       this.out.write("\n" + Sim.getAbsoluteTime() + "\t");
/*     */       
/* 381 */       for (int i = 0; i < ASMModelParams.numBFagents; i++)
/*     */       {
/* 383 */         BFagent agent = (BFagent)agentList.get(i);
/* 384 */         this.out.write(agent.getWealth() + "\t");
/*     */       }
/*     */       
/* 387 */       this.out.close();
/*     */     }
/*     */     catch (IOException localIOException) {}
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void agentColor()
/*     */   {
/* 398 */     for (int i = 0; i < ASMModelParams.numBFagents; i++)
/*     */     {
/* 400 */       BFagent agent = (BFagent)agentList.get(i);
/* 401 */       if (agent.retrainig) agent.setColor(Color.RED); else {
/* 402 */         agent.setColor(Color.BLUE);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public int numRetrainingAgents()
/*     */   {
/* 409 */     int count = 0;
/*     */     
/* 411 */     for (int i = 0; i < ASMModelParams.numBFagents; i++)
/*     */     {
/* 413 */       BFagent agent = (BFagent)agentList.get(i);
/* 414 */       if (agent.retrainig) { count++;
/*     */       }
/*     */     }
/* 417 */     return count;
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\ASMModelJas.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */