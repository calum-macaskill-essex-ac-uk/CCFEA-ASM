package CcFEA;

/*     */ import jas.engine.SimEngine;
/*     */ import jas.engine.SimModel;
/*     */ import jas.engine.gui.JAS;
/*     */// import jas.events.EventList;
/*     */ import jas.graphics.plot.CollectionBarPlotter;
/*     */ import jas.graphics.plot.TimeSeriesPlotter;
import jas.statistics.CrossSection;
/*     */ //import jas.statistics.CrossSection.Double;
import jas.statistics.functions.MaxArrayFunction;
/*     */ import jas.statistics.functions.MeanArrayFunction;
import jas.statistics.functions.MinArrayFunction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ASMObserverJAS
/*     */   extends SimModel
/*     */ {
/*     */   ASMModelJas asmModel;
/*     */   private TimeSeriesPlotter pricePlot;
/*     */   private TimeSeriesPlotter volumePlot;
/*     */   private TimeSeriesPlotter RetrainingAgentsPlot;
/*     */   private TimeSeriesPlotter averageWealthPlot;
/*     */   private CollectionBarPlotter agentPositionBarPlot;
/*     */   private CollectionBarPlotter agentWealthBarPlot;
/*     */   private CrossSection.Double agentsWealth;
/*     */   private CrossSection.Double agentsPosition;
/*     */   
/*     */   public static void main(String[] args)
/*     */   {
/*  37 */     SimEngine eng = new SimEngine();
/*  38 */     JAS jas = new JAS(eng);
/*  39 */     jas.setVisible(true);
/*     */     
/*  41 */     ASMModelJas m = new ASMModelJas();
/*  42 */     eng.addModel(m);
/*  43 */     m.setParameters();
/*     */     
/*  45 */     ASMObserverJAS o = new ASMObserverJAS();
/*  46 */     eng.addModel(o);
/*  47 */     o.setParameters();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setParameters()
/*     */   {
/*  57 */     this.asmModel = new ASMModelJas();
/*     */     
/*  59 */     if (this.asmModel == null) {
/*  60 */       throw new IllegalStateException("The model has not loaded yet.");
/*     */     }
			System.out.println(System.getProperty("user.dir"));
			System.out.println(asmModel.getClass().getName());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void buildModel()
/*     */   {
/*  68 */     this.agentsWealth = new CrossSection.Double(ASMModelJas.agentList, 0);
/*  69 */     this.agentsPosition = new CrossSection.Double(ASMModelJas.agentList, 1);
/*     */     
/*     */ 
/*  72 */     this.pricePlot = new TimeSeriesPlotter("Price");
/*  73 */     this.pricePlot.addSeries("Marker Price", this.asmModel, 0);
/*  74 */     this.pricePlot.addSeries("Rational Expectation", this.asmModel, 1);
/*  75 */     this.pricePlot.addSeries("Risk Neutral", this.asmModel, 2);
/*     */     
/*     */ 
/*  78 */     this.RetrainingAgentsPlot = new TimeSeriesPlotter("Retraining Agents");
/*  79 */     this.RetrainingAgentsPlot.addSeries("Number", this.asmModel, 4);
/*     */     
/*  81 */     this.averageWealthPlot = new TimeSeriesPlotter("Wealth");
/*  82 */     this.averageWealthPlot.addSeries("max", new MaxArrayFunction.Double(this.agentsWealth));
/*  83 */     this.averageWealthPlot.addSeries("min", new MinArrayFunction.Double(this.agentsWealth));
/*  84 */     this.averageWealthPlot.addSeries("mean", new MeanArrayFunction(this.agentsWealth));
/*     */     
/*  86 */     this.volumePlot = new TimeSeriesPlotter("Negotiation Volume");
/*  87 */     this.volumePlot.addSeries("Volume", this.asmModel, 3);
/*     */     
/*  89 */     this.agentPositionBarPlot = new CollectionBarPlotter("Agent's Position");
/*  90 */     this.agentPositionBarPlot.addSeries("Position", this.agentsPosition);
/*     */     
/*  92 */     this.agentWealthBarPlot = new CollectionBarPlotter("Agent's Wealth");
/*  93 */     this.agentWealthBarPlot.addSeries("Wealth", this.agentsWealth);
/*     */     
/*     */ 
/*  96 */     this.pricePlot.setSize(360, 250);
/*  97 */     this.RetrainingAgentsPlot.setSize(360, 250);
/*  98 */     this.volumePlot.setSize(360, 250);
/*  99 */     this.averageWealthPlot.setSize(360, 250);
/* 100 */     this.agentPositionBarPlot.setSize(360, 250);
/* 101 */     this.agentWealthBarPlot.setSize(360, 250);
/*     */     
/*     */ 
/* 104 */     addSimWindow(this.pricePlot);
/* 105 */     addSimWindow(this.RetrainingAgentsPlot);
/* 106 */     addSimWindow(this.volumePlot);
/* 107 */     addSimWindow(this.averageWealthPlot);
/* 108 */     addSimWindow(this.agentPositionBarPlot);
/* 109 */     addSimWindow(this.agentWealthBarPlot);
/*     */     
/*     */ 
/* 112 */     this.eventList.scheduleSimple(0L, 1, this.pricePlot, 10003);
/* 113 */     this.eventList.scheduleSimple(0L, 1, this.RetrainingAgentsPlot, 10003);
/* 114 */     this.eventList.scheduleSimple(0L, 1, this.volumePlot, 10003);
/* 115 */     this.eventList.scheduleSimple(0L, 1, this.averageWealthPlot, 10003);
/* 116 */     this.eventList.scheduleSimple(0L, 1, this.agentPositionBarPlot, 10003);
/* 117 */     this.eventList.scheduleSimple(0L, 1, this.agentWealthBarPlot, 10003);
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\ASMObserverJAS.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */