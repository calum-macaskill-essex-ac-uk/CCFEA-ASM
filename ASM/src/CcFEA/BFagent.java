import org.apache.log4j.Logger;package CcFEA;

/*      */ import jas.engine.Sim;
/*      */ //import jas.random.RandomGenerator;
/*      */ import jas.statistics.IDoubleSource;
/*      */ //import java.io.PrintStream;
/*      */ import java.util.LinkedList;
/*      */ import org._3pq.jgrapht.Graph;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BFagent
/*      */   extends Agent
/*      */   implements IDoubleSource
/*      */ {
/*      */   public static final int WEALTH = 0;
/*      */   public static final int POSITION = 1;
/*      */   public int currentTime;
/*      */   public int lastgatime;
/*      */   public double avspecificity;
/*      */   public double forecast;
/*      */   public double lforecast;
/*      */   public double global_mean;
/*      */   public double realDeviation;
/*      */   public double variance;
/*      */   public double pdcoeff;
/*      */   public double offset;
/*      */   public double divisor;
/*      */   public int gacount;
/*   42 */   public boolean retrainig = false;
/*      */   
/*      */ 
/*      */   public BFParams privateParams;
/*      */   
/*   47 */   public LinkedList<BFCast> fcastList = new LinkedList<BFCast>();
/*   48 */   public LinkedList<BFCast> activeList = new LinkedList<BFCast>();
/*   49 */   public LinkedList<BFCast> oldActiveList = new LinkedList<BFCast>();
/*      */   
/*   51 */   public World worldForAgent = new World();
/*   52 */   public ASMModelParams modelParams = new ASMModelParams();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   BFagent(Graph g)
/*      */   {
/*  233 */     super(g);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public final double drand()
/*      */   {
/*  256 */     return Sim.getRnd().getDblFromTo(0.0D, 1.0D);
/*      */   }
/*      */   
/*      */   public final double urand()
/*      */   {
/*  261 */     return Sim.getRnd().getDblFromTo(-1.0D, 1.0D);
/*      */   }
/*      */   
/*      */   public int irand(int x)
/*      */   {
/*  266 */     return Sim.getRnd().getIntFromTo(0, x - 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  273 */   public final double WEIGHTED = 0.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static BFParams params;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static double minstrength;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setBFParameterObject(BFParams x)
/*      */   {
/*  350 */     params = x;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void init() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object initForecasts()
/*      */   {
/*  376 */     int sumspecificity = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  391 */     this.privateParams = params.copy();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  397 */     int numfcasts = BFParams.numfcasts;
/*      */     
/*  399 */     this.avspecificity = 0.0D;
/*  400 */     this.gacount = 0;
/*      */     
/*  402 */     this.variance = BFParams.initvar;
/*  403 */     getPriceFromWorld();
/*  404 */     getDividendFromWorld();
/*  405 */     this.global_mean = (this.price + this.dividend);
/*  406 */     this.forecast = (this.lforecast = this.global_mean);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  412 */     this.fcastList.add(0, createNewForecast());
/*      */     
/*      */ 
/*  415 */     for (int i = 1; i < numfcasts; i++)
/*      */     {
/*  417 */       BFCast aForecast = createNewForecast();
/*  418 */       setConditionsRandomly(aForecast);
/*  419 */       this.fcastList.add(i, aForecast);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     int i;
/*  426 */     for (i = 1; i < numfcasts; i++)
/*      */     {
/*  428 */       BFCast aForecast = (BFCast)this.fcastList.get(i);
/*  429 */       sumspecificity += aForecast.getSpecificity();
/*      */     }
/*      */     
/*  432 */     this.avspecificity = (sumspecificity / numfcasts);
/*  433 */     return this;
/*      */   }
/*      */   
/*      */   public Object initForecasts2()
/*      */   {
/*  438 */     int sumspecificity = 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  453 */     this.privateParams = new BFParams();
/*  454 */     this.privateParams.init();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  460 */     int numfcasts = BFParams.numfcasts;
/*      */     
/*  462 */     this.avspecificity = 0.0D;
/*  463 */     this.gacount = 0;
/*      */     
/*  465 */     this.variance = BFParams.initvar;
/*  466 */     getPriceFromWorld();
/*  467 */     getDividendFromWorld();
/*  468 */     this.global_mean = (this.price + this.dividend);
/*  469 */     this.forecast = (this.lforecast = this.global_mean);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  475 */     this.fcastList.add(0, createNewForecast());
/*      */     
/*      */ 
/*  478 */     for (int i = 1; i < numfcasts; i++)
/*      */     {
/*  480 */       BFCast aForecast = createNewForecast();
/*  481 */       setConditionsRandomly(aForecast);
/*  482 */       this.fcastList.add(i, aForecast);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  489 */     for  (int i = 1; i < numfcasts; i++)
/*      */     {
/*  491 */       BFCast aForecast = (BFCast)this.fcastList.get(i);
/*  492 */       sumspecificity += aForecast.getSpecificity();
/*      */     }
/*      */     
/*  495 */     this.avspecificity = (sumspecificity / numfcasts);
/*  496 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BFCast createNewForecast()
/*      */   {
/*  508 */     double abase = BFParams.a_min + 0.5D * (1.0D - BFParams.subrange) * BFParams.a_range;
/*  509 */     double bbase = BFParams.b_min + 0.5D * (1.0D - BFParams.subrange) * BFParams.b_range;
/*  510 */     double cbase = BFParams.c_min + 0.5D * (1.0D - BFParams.subrange) * BFParams.c_range;
/*  511 */     double asubrange = BFParams.subrange * BFParams.a_range;
/*  512 */     double bsubrange = BFParams.subrange * BFParams.b_range;
/*  513 */     double csubrange = BFParams.subrange * BFParams.c_range;
/*      */     
/*  515 */     BFCast aForecast = new BFCast();
/*  516 */     aForecast.setCondwords(BFParams.condwords);
/*  517 */     aForecast.setCondbits(BFParams.condbits);
/*  518 */     aForecast.setNNulls(BFParams.nnulls);
/*  519 */     aForecast.setBitcost(BFParams.bitcost);
/*  520 */     aForecast.createEnd();
/*  521 */     aForecast.setForecast(0.0D);
/*  522 */     aForecast.setLforecast(this.global_mean);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  531 */     aForecast.setVariance(BFParams.newfcastvar);
/*  532 */     aForecast.setStrength(0.0D);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  540 */     aForecast.setAval(abase + drand() * asubrange);
/*  541 */     aForecast.setBval(bbase + drand() * bsubrange);
/*  542 */     aForecast.setCval(cbase + drand() * csubrange);
/*      */     
/*  544 */     return aForecast;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object setConditionsRandomly(BFCast fcastObject)
/*      */   {
/*  555 */     double[] problist = new double[BFParams.condbits];
/*  556 */     int[] bitlist = new int[BFParams.condbits];
/*      */     
/*  558 */     System.arraycopy(this.privateParams.getBitListPtr(), 0, bitlist, 0, BFParams.condbits);
/*  559 */     System.arraycopy(this.privateParams.getProbListPtr(), 0, problist, 0, BFParams.condbits);
/*      */     
/*  561 */     for (int bit = 0; bit < BFParams.condbits; bit++)
/*      */     {
/*  563 */       if (bitlist[bit] < 0)
/*      */       {
/*  565 */         fcastObject.setConditionsbit$FromZeroTo(bit, 3);
/*      */       }
/*  567 */       else if (drand() < problist[bit])
/*      */       {
/*  569 */         fcastObject.setConditionsbit$FromZeroTo(bit, irand(2) + 1);
/*      */         
/*  571 */         fcastObject.incrSpecificity();
/*  572 */         fcastObject.updateSpecfactor();
/*      */       }
/*      */     }
/*  575 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object prepareForTrading2()
/*      */   {
/*  592 */     double forecastvar = 0.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  598 */     BitVector myworld = new BitVector();
/*  599 */     myworld.setCondwords(BFParams.condwords);
/*  600 */     myworld.setCondbits(BFParams.condbits);
/*  601 */     myworld.createEnd();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  615 */     this.currentTime = ((int)Sim.getAbsoluteTime());
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  621 */     if ((ASMModelParams.RedQueen) && (getWealth() < this.worldForAgent.getAverageWealth()) && (this.currentTime >= BFParams.firstgatime))
/*      */     {
/*  623 */       performGA();
/*  624 */       this.retrainig = true;
/*      */     }
/*  626 */     else if ((!ASMModelParams.RedQueen) && (this.currentTime >= BFParams.firstgatime) && (this.currentTime % BFParams.gafrequency == 0) && (drand() < BFParams.gaprob))
/*      */     {
/*  628 */       performGA();
/*  629 */       this.retrainig = true;
/*      */     } else {
/*  631 */       this.retrainig = false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  639 */     this.lforecast = this.forecast;
/*      */     
/*  641 */     myworld = collectWorldData2();
/*      */     
/*  643 */     updateActiveList(myworld);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  708 */     double maxstrength = -1.0E50D;
/*  709 */     BFCast bestForecast = null;
/*  710 */     int nactive = 0;
/*  711 */     int mincount = BFParams.mincount;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  733 */     for (int i = 0; i < this.activeList.size(); i++)
/*      */     {
/*  735 */       BFCast aForecast = (BFCast)this.activeList.get(i);
/*  736 */       aForecast.setLastactive(this.currentTime);
/*  737 */       if (aForecast.incrCount() >= mincount)
/*      */       {
/*  739 */         double strength = aForecast.getStrength();
/*  740 */         nactive++;
/*  741 */         if (strength > maxstrength)
/*      */         {
/*  743 */           maxstrength = strength;
/*  744 */           bestForecast = aForecast;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  757 */     if (nactive != 0)
/*      */     {
/*  759 */       this.pdcoeff = bestForecast.getAval();
/*  760 */       this.offset = (bestForecast.getBval() * this.dividend + bestForecast.getCval());
/*  761 */       if (BFParams.individual != 0) {
/*  762 */         forecastvar = this.variance;
/*      */       }
/*      */       else {
/*  765 */         forecastvar = bestForecast.getVariance();
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  774 */       double countsum = 0.0D;
/*  775 */       this.pdcoeff = 0.0D;
/*  776 */       this.offset = 0.0D;
/*  777 */       mincount = BFParams.mincount;
/*      */       
/*  779 */       for (int i = 0; i < this.fcastList.size(); i++)
/*      */       {
/*  781 */         BFCast aForecast = (BFCast)this.fcastList.get(i);
/*  782 */         if (aForecast.getCnt() >= mincount) {
/*      */           double weight;
/*  784 */           countsum += (weight = aForecast.getStrength());
/*  785 */           this.offset += (aForecast.getBval() * this.dividend + aForecast.getCval()) * weight;
/*  786 */           this.pdcoeff += aForecast.getAval() * weight;
/*      */         }
/*      */         
/*  789 */         if (countsum > 0.0D)
/*      */         {
/*  791 */           this.offset /= countsum;
/*  792 */           this.pdcoeff /= countsum;
/*      */         }
/*      */         else
/*      */         {
/*  796 */           this.offset = this.global_mean;
/*      */         }
/*  798 */         forecastvar = this.variance;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  803 */     this.divisor = (BFParams.lambda * forecastvar);
/*      */     
/*  805 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BitVector collectWorldData2()
/*      */   {
/*  821 */     int nworldbits = Agent.worldForAgent.getNumWorldBits();
/*  822 */     int[] bitlist = new int[BFParams.condbits];
/*  823 */     int[] myRealWorld = new int[nworldbits];
/*      */     
/*  825 */     BitVector world = new BitVector();
/*      */     
/*      */ 
/*  828 */     world.setCondwords(BFParams.condwords);
/*  829 */     world.setCondbits(BFParams.condbits);
/*  830 */     world.createEnd();
/*      */     
/*  832 */     bitlist = this.privateParams.getBitListPtr();
/*      */     
/*  834 */     Agent.worldForAgent.getRealWorld(myRealWorld);
/*      */     
/*  836 */     for (int i = 0; i < BFParams.condbits; i++) {
/*      */       int n;
/*  838 */       if ((n = bitlist[i]) >= 0)
/*      */       {
/*  840 */         world.setConditionsbit$To(i, myRealWorld[n]);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  845 */     return world;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean changeIntToBoolean(int a)
/*      */   {
/*  852 */     if (a != 0)
/*  853 */       return true;
/*  854 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object updateActiveList(BitVector worldvalues)
/*      */   {
/*  872 */     copyList$To(this.activeList, this.oldActiveList);
/*      */     
/*      */ 
/*  875 */     this.activeList.clear();
/*      */     
/*      */ 
/*      */ 
/*  879 */     for (int i = 0; i < this.oldActiveList.size(); i++)
/*      */     {
/*  881 */       BFCast aForecast = (BFCast)this.oldActiveList.get(i);
/*  882 */       aForecast.setLforecast(aForecast.getForecast());
/*      */     }
/*      */     
/*  885 */     switch (BFParams.condwords)
/*      */     {
/*      */     case 1: 
/*  888 */       for (int i = 0; i < this.fcastList.size(); i++)
/*      */       {
/*  890 */         BFCast aForecast = (BFCast)this.fcastList.get(i);
/*  891 */         if (!changeIntToBoolean(aForecast.getConditionsWord(0) & worldvalues.getConditionsWord(0)))
/*      */         {
/*      */ 
/*      */ 
/*  895 */           this.activeList.add(aForecast);
/*      */         }
/*      */       }
/*  898 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2: 
/*  911 */       for (int i = 0; i < this.fcastList.size(); i++)
/*      */       {
/*  913 */         BFCast aForecast = (BFCast)this.fcastList.get(i);
/*  914 */         if (!changeIntToBoolean(aForecast.getConditionsWord(0) & worldvalues.getConditionsWord(0)))
/*      */         {
/*      */ 
/*      */ 
/*  918 */           if (!changeIntToBoolean(aForecast.getConditionsWord(1) & worldvalues.getConditionsWord(1)))
/*      */           {
/*      */ 
/*      */ 
/*  922 */             this.activeList.add(aForecast); }
/*      */         }
/*      */       }
/*  925 */       break;
/*      */     
/*      */     case 3: 
/*  928 */       for (int i = 0; i < this.fcastList.size(); i++)
/*      */       {
/*  930 */         BFCast aForecast = (BFCast)this.fcastList.get(i);
/*  931 */         if (!changeIntToBoolean(aForecast.getConditionsWord(0) & worldvalues.getConditionsWord(0)))
/*      */         {
/*      */ 
/*      */ 
/*  935 */           if (!changeIntToBoolean(aForecast.getConditionsWord(1) & worldvalues.getConditionsWord(1)))
/*      */           {
/*      */ 
/*      */ 
/*  939 */             if (!changeIntToBoolean(aForecast.getConditionsWord(2) & worldvalues.getConditionsWord(2)))
/*      */             {
/*      */ 
/*      */ 
/*  943 */               this.activeList.add(aForecast); } }
/*      */         }
/*      */       }
/*  946 */       break;
/*      */     
/*      */ 
/*      */     case 4: 
/*  950 */       for (int i = 0; i < this.fcastList.size(); i++)
/*      */       {
/*  952 */         BFCast aForecast = (BFCast)this.fcastList.get(i);
/*  953 */         if (!changeIntToBoolean(aForecast.getConditionsWord(0) & worldvalues.getConditionsWord(0)))
/*      */         {
/*      */ 
/*      */ 
/*  957 */           if (!changeIntToBoolean(aForecast.getConditionsWord(1) & worldvalues.getConditionsWord(1)))
/*      */           {
/*      */ 
/*      */ 
/*  961 */             if (!changeIntToBoolean(aForecast.getConditionsWord(2) & worldvalues.getConditionsWord(2)))
/*      */             {
/*      */ 
/*      */ 
/*  965 */               if (!changeIntToBoolean(aForecast.getConditionsWord(3) & worldvalues.getConditionsWord(3)))
/*      */               {
/*      */ 
/*      */ 
/*  969 */                 this.activeList.add(aForecast); } } }
/*      */         }
/*      */       }
/*  972 */       break;
/*      */     
/*      */ 
/*      */     case 5: 
/*  976 */       for (int i = 0; i < this.fcastList.size(); i++)
/*      */       {
/*  978 */         BFCast aForecast = (BFCast)this.fcastList.get(i);
/*  979 */         if (!changeIntToBoolean(aForecast.getConditionsWord(0) & worldvalues.getConditionsWord(0)))
/*      */         {
/*      */ 
/*      */ 
/*  983 */           if (!changeIntToBoolean(aForecast.getConditionsWord(1) & worldvalues.getConditionsWord(1)))
/*      */           {
/*      */ 
/*      */ 
/*  987 */             if (!changeIntToBoolean(aForecast.getConditionsWord(2) & worldvalues.getConditionsWord(2)))
/*      */             {
/*      */ 
/*      */ 
/*  991 */               if (!changeIntToBoolean(aForecast.getConditionsWord(3) & worldvalues.getConditionsWord(3)))
/*      */               {
/*      */ 
/*      */ 
/*  995 */                 if (!changeIntToBoolean(aForecast.getConditionsWord(4) & worldvalues.getConditionsWord(4)))
/*      */                 {
/*      */ 
/*      */ 
/*  999 */                   this.activeList.add(aForecast); } }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 1005 */   //  if (80 > 80) {
/* 1006 */   //    System.out.println("error Too many condition bits (MAXCONDBITS)");
/*      */   //  }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1048 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getInputValues()
/*      */   {
/* 1054 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object feedForward()
/*      */   {
/* 1060 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDemandAndSlope$forPrice(double slope, double trialprice)
/*      */   {
/* 1080 */     this.forecast = ((trialprice + this.dividend) * this.pdcoeff + this.offset);
/*      */     
/*      */ 
/* 1083 */     if (this.forecast >= 0.0D)
/*      */     {
/* 1085 */       this.demand = (-((trialprice * this.intratep1 - this.forecast) / this.divisor + this.position));
/* 1086 */       slope = (this.pdcoeff - this.intratep1) / this.divisor;
/*      */     }
/*      */     else
/*      */     {
/* 1090 */       this.forecast = 0.0D;
/* 1091 */       this.demand = (-(trialprice * this.intratep1 / this.divisor + this.position));
/* 1092 */       slope = -this.intratep1 / this.divisor;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1098 */     if (this.demand > BFParams.maxbid)
/*      */     {
/* 1100 */       this.demand = BFParams.maxbid;
/* 1101 */       slope = 0.0D;
/*      */     }
/* 1103 */     else if (this.demand < -BFParams.maxbid)
/*      */     {
/* 1105 */       this.demand = (-BFParams.maxbid);
/* 1106 */       slope = 0.0D;
/*      */     }
/*      */     
/* 1109 */     super.constrainDemand(slope, trialprice);
/* 1110 */     return this.demand;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getRealForecast()
/*      */   {
/* 1117 */     return this.forecast;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object updatePerformance2()
/*      */   {
/* 1144 */     double tauv = BFParams.tauv;
/* 1145 */     double a = 1.0D / tauv;
/* 1146 */     double b = 1.0D - a;
/*      */     
/*      */ 
/*      */ 
/* 1150 */     double av = 0.01D;
/* 1151 */     double bv = 1.0D - av;
/*      */     
/*      */ 
/* 1154 */     if (tauv == 100000.0D)
/*      */     {
/* 1156 */       a = 0.0D;
/* 1157 */       b = 1.0D;
/* 1158 */       av = 0.0D;
/* 1159 */       bv = 1.0D;
/*      */     }
/* 1161 */     double maxdev = BFParams.maxdev;
/*      */     
/*      */ 
/* 1164 */     getPriceFromWorld();
/* 1165 */     double ftarget = this.price + this.dividend;
/*      */     
/*      */     double deviation;
/*      */     
/* 1169 */     this.realDeviation = (deviation = ftarget - this.lforecast);
/* 1170 */     if (Math.abs(deviation) > maxdev) deviation = maxdev;
/* 1171 */     this.global_mean = (b * this.global_mean + a * ftarget);
/*      */     
/* 1173 */     this.currentTime = ((int)Sim.getAbsoluteTime());
/* 1174 */     if (this.currentTime < 1) {
/* 1175 */       this.variance = BFParams.initvar;
/*      */     } else {
/* 1177 */       this.variance = (bv * this.variance + av * deviation * deviation);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1185 */     for (int i = 0; i < this.activeList.size(); i++)
/*      */     {
/* 1187 */       BFCast aForecast = (BFCast)this.activeList.get(i);
/* 1188 */       aForecast.updateForecastPrice$Dividend(this.price, this.dividend);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1217 */     if (this.currentTime > 0)
/*      */     {
/* 1219 */       for (int i = 0; i < this.oldActiveList.size(); i++)
/*      */       {
/* 1221 */         BFCast aForecast = (BFCast)this.oldActiveList.get(i);
/* 1222 */         double lastForecast = aForecast.getLforecast();
/* 1223 */         deviation = (ftarget - lastForecast) * (ftarget - lastForecast);
/*      */         
/* 1225 */         if (deviation > maxdev) deviation = maxdev;
/* 1226 */         if (aForecast.getCnt() > tauv) {
/* 1227 */           aForecast.setVariance(b * aForecast.getVariance() + a * deviation);
/*      */         }
/*      */         else {
/* 1230 */           double c = 1.0D / (1.0D + aForecast.getCnt());
/* 1231 */           aForecast.setVariance((1.0D - c) * aForecast.getVariance() + 
/* 1232 */             c * deviation);
/*      */         }
/*      */         
/* 1235 */         aForecast.setStrength(BFParams.maxdev - 
/* 1236 */           aForecast.getVariance() + 
/* 1237 */           aForecast.getSpecfactor());
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1257 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double getDeviation()
/*      */   {
/* 1264 */     return Math.abs(this.realDeviation);
/*      */   }
/*      */   
/*      */   public double getError()
/*      */   {
/* 1269 */     return this.divisor / BFParams.lambda;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object updateWeights()
/*      */   {
/* 1276 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int nbits()
/*      */   {
/* 1286 */     return BFParams.condbits;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int nrules()
/*      */   {
/* 1295 */     return BFParams.numfcasts;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int lastgatime()
/*      */   {
/* 1304 */     return this.lastgatime;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object performGA()
/*      */   {
/* 1519 */     double madv = 0.0D;
/* 1520 */     double meanv = 0.0D;
/*      */     
/*      */ 
/*      */ 
/* 1524 */     LinkedList<BFCast> newList = new LinkedList<BFCast>();
/* 1525 */     int[] bitlist = new int[BFParams.condbits];
/* 1526 */     LinkedList<BFCast> rejectList = new LinkedList<BFCast>();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1532 */     this.gacount += 1;
/* 1533 */     this.currentTime = ((int)Sim.getAbsoluteTime());
/*      */     
/*      */ 
/*      */ 
/* 1537 */     this.lastgatime = this.currentTime;
/*      */     
/* 1539 */     bitlist = BFParams.bitlist;
/*      */     
/*      */ 
/* 1542 */     MakePool$From(rejectList, this.fcastList);
/*      */     double sumc;
/*      */     double avc;
/*      */     double avb;
/* 1546 */     double ava; double avstrength = ava = avb = avc = sumc = 0.0D;
/* 1547 */     minstrength = 1.0E20D;
/*      */     
/* 1549 */     for (int f = 0; f < BFParams.numfcasts; f++)
/*      */     {
/* 1551 */       BFCast aForecast = (BFCast)this.fcastList.get(f);
/* 1552 */       double varvalue = 0.0D;
/*      */       
/* 1554 */       varvalue = aForecast.getVariance();
/* 1555 */       meanv += varvalue;
/* 1556 */       if (aForecast.getCnt() > 0)
/*      */       {
/* 1558 */         if (varvalue != 0.0D)
/*      */         {
/* 1560 */           avstrength += ((BFCast)this.fcastList.get(f)).getStrength();
/* 1561 */           sumc += 1.0D / varvalue;
/* 1562 */           ava += aForecast.getAval() / varvalue;
/* 1563 */           avb += aForecast.getBval() / varvalue;
/* 1564 */           avc += aForecast.getCval() / varvalue; }
/*      */         double temp;
/* 1566 */         if ((temp = aForecast.getStrength()) < minstrength) {
/* 1567 */           minstrength = temp;
/*      */         }
/*      */       }
/*      */     }
/* 1571 */     meanv /= BFParams.numfcasts;
/*      */     
/* 1573 */     for (int f = 0; f < BFParams.numfcasts; f++)
/*      */     {
/* 1575 */       madv += Math.abs(((BFCast)this.fcastList.get(f)).getVariance()) - meanv;
/*      */     }
/*      */     
/* 1578 */     madv /= BFParams.numfcasts;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1589 */     ((BFCast)this.fcastList.get(0)).setAval(ava / sumc);
/* 1590 */     ((BFCast)this.fcastList.get(0)).setBval(avb / sumc);
/* 1591 */     ((BFCast)this.fcastList.get(0)).setCval(avc / sumc);
/*      */     
/* 1593 */     avstrength /= BFParams.numfcasts;
/*      */     
/*      */ 
/* 1596 */     for (int new2 = 0; new2 < BFParams.nnew; new2++)
/*      */     {
/*      */ 
/*      */ 
/* 1600 */       boolean changed = false;
/*      */       
/*      */ 
/*      */ 
/* 1604 */       double altvarvalue = 9.99999999E8D;
/*      */       
/*      */ 
/* 1607 */       BFCast aNewForecast = createNewForecast();
/* 1608 */       aNewForecast.updateSpecfactor();
/* 1609 */       aNewForecast.setStrength(avstrength);
/*      */       
/*      */ 
/* 1612 */       aNewForecast.setLastactive(this.currentTime);
/*      */       
/* 1614 */       double varvalue = BFParams.maxdev - avstrength + aNewForecast.getSpecfactor();
/*      */       
/* 1616 */       aNewForecast.setVariance(varvalue);
/* 1617 */       altvarvalue = ((BFCast)this.fcastList.get(0)).getVariance() - madv;
/* 1618 */       if (varvalue < altvarvalue)
/*      */       {
/* 1620 */         aNewForecast.setVariance(altvarvalue);
/* 1621 */         aNewForecast.setStrength(BFParams.maxdev - altvarvalue + aNewForecast.getSpecfactor());
/*      */       }
/* 1623 */       aNewForecast.setLastactive(this.currentTime);
/*      */       
/* 1625 */       newList.add(aNewForecast);
/*      */       
/*      */       BFCast parent1;
/*      */       do
/*      */       {
/* 1630 */         parent1 = Tournament(this.fcastList);
/*      */       }
/* 1632 */       while (parent1 == null);
/*      */       
/*      */ 
/* 1635 */       if (drand() < BFParams.pcrossover) {
/*      */         BFCast parent2;
/*      */         do {
/* 1638 */           parent2 = Tournament(this.fcastList);
/*      */         }
/* 1640 */         while ((parent2 == parent1) || (parent2 == null));
/*      */         
/* 1642 */         Crossover$Parent1$Parent2(aNewForecast, parent1, parent2);
/* 1643 */       //  if (aNewForecast == null) System.out.println("got nil back from crossover");
/* 1644 */         changed = true;
/*      */       }
/*      */       else
/*      */       {
/* 1648 */         CopyRule$From(aNewForecast, parent1);
/* 1649 */       //  if (aNewForecast == null) System.out.println("got nil back from CopyRule");
/* 1650 */         changed = Mutate$Status(aNewForecast, changed);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1660 */     TransferFcastsFrom$To$Replace(newList, this.fcastList, rejectList);
/*      */     
/*      */ 
/* 1663 */     Generalize$AvgStrength(this.fcastList, avstrength);
/*      */     
/*      */ 
/*      */ 
/* 1667 */     int specificity = 0;
/*      */     
/*      */ 
/*      */ 
/* 1671 */     for (int f = 0; f < BFParams.numfcasts; f++)
/*      */     {
/* 1673 */       BFCast parent1 = (BFCast)this.fcastList.get(0);
/* 1674 */       specificity += parent1.getSpecificity();
/*      */     }
/* 1676 */     this.avspecificity = (specificity / BFParams.numfcasts);
/*      */     
/*      */ 
/*      */ 
/* 1680 */     newList.clear();
/*      */     
/* 1682 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BFCast CopyRule$From(BFCast to, BFCast from)
/*      */   {
/* 1697 */     to.setForecast(from.getForecast());
/* 1698 */     to.setLforecast(from.getLforecast());
/* 1699 */     to.setVariance(from.getVariance());
/* 1700 */     to.setStrength(from.getStrength());
/* 1701 */     to.setAval(from.getAval());
/* 1702 */     to.setBval(from.getBval());
/* 1703 */     to.setCval(from.getCval());
/* 1704 */     to.setSpecfactor(from.getSpecfactor());
/* 1705 */     to.setLastactive(from.getLastactive());
/* 1706 */     to.setSpecificity(from.getSpecificity());
/* 1707 */     to.setConditions(from.getConditions());
/* 1708 */     to.setCnt(from.getCnt());
/* 1709 */     if (from.getCnt() == 0)
/* 1710 */       to.setStrength(minstrength);
/* 1711 */     return to;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void MakePool$From(LinkedList<BFCast> rejects, LinkedList<BFCast> list)
/*      */   {
/* 1721 */     int j = 0;
/*      */     
/*      */ 
/*      */ 
/* 1725 */     int top = -1;
/*      */     
/* 1727 */     for (int i = 1; i < BFParams.npool; i++)
/*      */     {
/* 1729 */       BFCast aForecast = (BFCast)list.get(i);
/* 1730 */       BFCast aReject; for (j = top; (j >= 0) && ((aReject = (BFCast)rejects.get(j)) != null) && (aForecast.getStrength() < aReject.getStrength()); j--) {
/*      */        // BFCast aReject;
/* 1732 */         rejects.add(j + 1, aReject);
/*      */       }
/* 1734 */       rejects.add(j + 1, aForecast);
/* 1735 */       top++;
/*      */     }
/* 1738 */     for (int i=0; i < BFParams.numfcasts; i++)
/*      */     {
/* 1740 */       BFCast aForecast = (BFCast)list.get(i);
/* 1741 */       if (aForecast.getStrength() < ((BFCast)rejects.get(top)).getStrength()) {
/*      */         BFCast aReject;
/* 1743 */         for (j = top - 1; (j >= 0) && ((aReject = (BFCast)rejects.get(j)) != null) && (aForecast.getStrength() < aReject.getStrength()); j--) {
/*      */           //BFCast aReject;
/* 1745 */           rejects.add(j + 1, aReject);
/*      */         }
/*      */       }
/* 1748 */       rejects.add(j + 1, aForecast);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BFCast Tournament(LinkedList<BFCast> list)
/*      */   {
/* 1762 */     int numfcasts = list.size();
/* 1763 */     BFCast candidate1 = (BFCast)list.get(irand(numfcasts));
/*      */     BFCast candidate2;
/*      */     do
/*      */     {
/* 1767 */       candidate2 = (BFCast)list.get(irand(numfcasts));
/* 1768 */     } while (candidate2 == candidate1);
/*      */     
/* 1770 */     if (candidate1.getStrength() > candidate2.getStrength()) {
/* 1771 */       return candidate1;
/*      */     }
/* 1773 */     return candidate2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean Mutate$Status(BFCast new2, boolean changed)
/*      */   {
/* 1816 */     boolean bitchanged = false;
/* 1817 */     int[] bitlist = new int[BFParams.condbits];
/*      */     
/* 1819 */     bitlist = this.privateParams.getBitListPtr();
/*      */     
/* 1821 */     bitchanged = changed;
/* 1822 */     if (BFParams.pmutation > 0.0D)
/*      */     {
/* 1824 */       for (int bit = 0; bit < BFParams.condbits; bit++)
/*      */       {
/* 1826 */         if ((bitlist[bit] >= 0) && 
/* 1827 */           (drand() < BFParams.pmutation))
/*      */         {
/*      */ 
/*      */ 
/* 1831 */           if (new2.getConditionsbit(bit) > 0)
/*      */           {
/* 1833 */             if (irand(3) > 0)
/*      */             {
/*      */ 
/*      */ 
/* 1837 */               new2.maskConditionsbit(bit);
/* 1838 */               new2.decrSpecificity();
/*      */             }
/*      */             else
/*      */             {
/* 1842 */               new2.switchConditionsbit(bit);
/*      */             }
/* 1844 */             bitchanged = changed = true;
/*      */           }
/* 1846 */           else if (irand(3) > 0)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 1851 */             new2.setConditionsbit$FromZeroTo(bit, irand(2) + 1);
/* 1852 */             new2.incrSpecificity();
/* 1853 */             bitchanged = changed = true;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1860 */     double choice = drand();
/* 1861 */     if (choice < BFParams.plong)
/*      */     {
/*      */ 
/* 1864 */       new2.setAval(BFParams.a_min + BFParams.a_range * drand());
/* 1865 */       changed = true;
/*      */     }
/* 1867 */     else if (choice < BFParams.plong + BFParams.pshort)
/*      */     {
/*      */ 
/* 1870 */       double temp = new2.getAval() + BFParams.a_range * BFParams.nhood * urand();
/* 1871 */       new2.setAval(
/* 1872 */         temp < BFParams.a_min ? BFParams.a_min : temp > BFParams.a_max ? BFParams.a_max : temp);
/* 1873 */       changed = true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1878 */     choice = drand();
/* 1879 */     if (choice < BFParams.plong)
/*      */     {
/*      */ 
/* 1882 */       new2.setBval(BFParams.b_min + BFParams.b_range * drand());
/* 1883 */       changed = true;
/*      */     }
/* 1885 */     else if (choice < BFParams.plong + BFParams.pshort)
/*      */     {
/*      */ 
/* 1888 */       double temp = new2.getBval() + BFParams.b_range * BFParams.nhood * urand();
/* 1889 */       new2.setBval(
/* 1890 */         temp < BFParams.b_min ? BFParams.b_min : temp > BFParams.b_max ? BFParams.b_max : temp);
/* 1891 */       changed = true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1896 */     choice = drand();
/* 1897 */     if (choice < BFParams.plong)
/*      */     {
/*      */ 
/* 1900 */       new2.setCval(BFParams.c_min + BFParams.c_range * drand());
/* 1901 */       changed = true;
/*      */     }
/* 1903 */     else if (choice < BFParams.plong + BFParams.pshort)
/*      */     {
/*      */ 
/* 1906 */       double temp = new2.getCval() + BFParams.c_range * BFParams.nhood * urand();
/* 1907 */       new2.setCval(
/* 1908 */         temp < BFParams.c_min ? BFParams.c_min : temp > BFParams.c_max ? BFParams.c_max : temp);
/* 1909 */       changed = true;
/*      */     }
/*      */     
/*      */ 
/* 1913 */     new2.setCnt(0);
/*      */     
/* 1915 */     if (changed)
/*      */     {
/* 1917 */       new2.updateSpecfactor();
/*      */     }
/* 1919 */     return changed;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BFCast Crossover$Parent1$Parent2(BFCast newForecast, BFCast parent1, BFCast parent2)
/*      */   {
/* 1948 */     newForecast.setSpecificity(0);
/*      */     
/* 1950 */     for (int word = 0; word < BFParams.condwords; word++) {
/* 1951 */       newForecast.setConditionsWord$To(word, 0);
/*      */     }
/* 1953 */     for (int bit = 0; bit < BFParams.condbits; bit++)
/*      */     {
/* 1955 */       if (irand(2) == 0)
/*      */       {
/* 1957 */         int value = parent1.getConditionsbit(bit);
/* 1958 */         newForecast.setConditionsbit$FromZeroTo(bit, value);
/* 1959 */         if (value > 0) newForecast.incrSpecificity();
/*      */       }
/*      */       else
/*      */       {
/* 1963 */         int value = parent2.getConditionsbit(bit);
/* 1964 */         newForecast.setConditionsbit$FromZeroTo(bit, value);
/* 1965 */         if (value > 0) { newForecast.incrSpecificity();
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1970 */     double choice = drand();
/* 1971 */     if (choice < BFParams.plinear)
/*      */     {
/*      */ 
/* 1974 */       double weight1 = parent1.getStrength() / (parent1.getStrength() + 
/* 1975 */         parent2.getStrength());
/* 1976 */       double weight2 = 1.0D - weight1;
/* 1977 */       newForecast.setAval(weight1 * parent1.getAval() + weight2 * parent2.getAval());
/* 1978 */       newForecast.setBval(weight1 * parent1.getBval() + weight2 * parent2.getBval());
/* 1979 */       newForecast.setCval(weight1 * parent1.getCval() + weight2 * parent2.getCval());
/*      */     }
/* 1981 */     else if (choice < BFParams.plinear + BFParams.prandom)
/*      */     {
/*      */ 
/* 1984 */       if (irand(2) != 0)
/* 1985 */         newForecast.setAval(parent1.getAval()); else newForecast.setAval(parent2.getAval());
/* 1986 */       if (irand(2) != 0)
/* 1987 */         newForecast.setBval(parent1.getBval()); else newForecast.setBval(parent2.getBval());
/* 1988 */       if (irand(2) != 0)
/* 1989 */         newForecast.setCval(parent1.getCval()); else { newForecast.setCval(parent2.getCval());
/*      */       }
/*      */       
/*      */ 
/*      */     }
/* 1994 */     else if (irand(2) != 0)
/*      */     {
/* 1996 */       newForecast.setAval(parent1.getAval());
/* 1997 */       newForecast.setBval(parent1.getBval());
/* 1998 */       newForecast.setCval(parent1.getCval());
/*      */     }
/*      */     else
/*      */     {
/* 2002 */       newForecast.setAval(parent2.getAval());
/* 2003 */       newForecast.setBval(parent2.getBval());
/* 2004 */       newForecast.setCval(parent2.getCval());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2010 */     int specificity = 0;
/* 2011 */     newForecast.setCnt(0);
/*      */     
/* 2013 */     newForecast.updateSpecfactor();
/*      */     
/* 2015 */     newForecast.setStrength(0.5D * (parent1.getStrength() + parent2.getStrength()));
/*      */     
/*      */ 
/*      */ 
/* 2019 */     BitVector newcond = newForecast.getConditionsObject();
/*      */     
/* 2021 */     for (int bit = 0; bit < BFParams.condbits; bit++)
/*      */     {
/*      */ 
/* 2024 */       if (newcond.getConditionsbit(bit) != 0)
/*      */       {
/* 2026 */         specificity++;
/*      */       }
/*      */     }
/*      */     
/* 2030 */     return newForecast;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void TransferFcastsFrom$To$Replace(LinkedList<BFCast> newList, LinkedList<BFCast> forecastList, LinkedList<BFCast> rejects)
/*      */   {
/* 2045 */     for (int i = 0; i < newList.size(); i++)
/*      */     {
/* 2047 */       BFCast aForecast = (BFCast)newList.get(i);
/*      */       
/*      */ 
/* 2050 */       BFCast toDieForecast = GetMort$Rejects(aForecast, rejects);
/* 2051 */       toDieForecast = CopyRule$From(toDieForecast, aForecast);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BFCast GetMort$Rejects(BFCast new2, LinkedList<BFCast> rejects)
/*      */   {
/* 2075 */     int[] cond1 = new int[BFParams.condwords];
/* 2076 */     int[] cond2 = new int[BFParams.condwords];
/* 2077 */     int[] newcond = new int[BFParams.condwords];
/*      */     
/* 2079 */     int bitmax = 0;
/*      */     
/*      */ 
/*      */ 
/* 2083 */     int numrejects = BFParams.npool;
/*      */     
/*      */     int r1;
/*      */     do
/*      */     {
/* 2088 */       r1 = irand(numrejects);
/*      */     }
/* 2090 */     while (rejects.get(r1) == null);
/*      */     
/*      */     int r2;
/*      */     do
/*      */     {
/* 2095 */       r2 = irand(numrejects);
/*      */     }
/* 2097 */     while ((r1 == r2) || (rejects.get(r2) == null));
/*      */     
/*      */ 
/* 2100 */     cond1 = ((BFCast)rejects.get(r1)).getConditions();
/* 2101 */     cond2 = ((BFCast)rejects.get(r2)).getConditions();
/*      */     
/* 2103 */     newcond = new2.getConditions();
/*      */     
/* 2105 */     int different1 = 0;
/* 2106 */     int different2 = 0;
/* 2107 */     bitmax = 16;
/* 2108 */     for (int word = 0; word < BFParams.condwords; word++)
/*      */     {
/* 2110 */       int temp1 = cond1[word] ^ newcond[word];
/* 2111 */       int temp2 = cond2[word] ^ newcond[word];
/* 2112 */       if (word == BFParams.condwords - 1)
/* 2113 */         bitmax = (BFParams.condbits - 1 & 0xF) + 1;
/* 2114 */       for (int bit = 0; bit < bitmax; bit++)
/*      */       {
/* 2116 */         if ((temp1 & 0x3) != 0)
/* 2117 */           different1++;
/* 2118 */         if ((temp2 & 0x3) != 0) {
/* 2119 */           different2++;
/*      */         }
/* 2114 */         temp1 >>= 2;temp2 >>= 2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     BFCast aReject;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2129 */     if (different1 < different2)
/*      */     {
/* 2131 */        aReject = (BFCast)rejects.get(r1);
/* 2132 */       rejects.add(r1, null);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2137 */       aReject = (BFCast)rejects.get(r2);
/* 2138 */       rejects.add(r2, null);
/*      */     }
/* 2140 */     return aReject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void Generalize$AvgStrength(LinkedList<BFCast> list, double avgstrength)
/*      */   {
/* 2160 */     int[] bitlist = new int[BFParams.condbits];
/* 2161 */     bitlist = this.privateParams.getBitListPtr();
/*      */     
/* 2163 */     this.currentTime = ((int)Sim.getAbsoluteTime());
/*      */     
/* 2165 */     for (int f = 0; f < BFParams.numfcasts; f++)
/*      */     {
/*      */ 
/* 2168 */       BFCast aForecast = (BFCast)list.get(f);
/* 2169 */       if (this.currentTime - aForecast.getLastactive() > BFParams.longtime)
/*      */       {
/* 2171 */         boolean changed = false;
/* 2172 */         int j = (int)Math.ceil(aForecast.getSpecificity() * BFParams.genfrac);
/* 2173 */         while (j > 0)
/*      */         {
/* 2175 */           int bit = irand(BFParams.condbits);
/* 2176 */           if (bitlist[bit] >= 0)
/*      */           {
/* 2178 */             if (aForecast.getConditionsbit(bit) > 0)
/*      */             {
/*      */ 
/* 2181 */               aForecast.maskConditionsbit(bit);
/* 2182 */               aForecast.decrSpecificity();
/* 2183 */               changed = true;
/* 2184 */               j--;
/*      */             } }
/*      */         }
/* 2187 */         if (changed)
/*      */         {
/*      */ 
/* 2190 */           aForecast.setCnt(0);
/* 2191 */           aForecast.setLastactive(this.currentTime);
/* 2192 */           aForecast.updateSpecfactor();
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2199 */           double varvalue = BFParams.maxdev - avgstrength + aForecast.getSpecfactor();
/* 2200 */           if (varvalue > 0.0D) {
/* 2201 */             aForecast.setVariance(varvalue);
/*      */           }
/*      */           
/* 2204 */           aForecast.setStrength(avgstrength);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object copyList$To(LinkedList<BFCast> list, LinkedList<BFCast> outputList)
/*      */   {
/* 2238 */     outputList.clear();
/* 2239 */     for (int i = 0; i < list.size(); i++)
/*      */     {
/* 2241 */       outputList.add(i, list.get(i));
/*      */     }
/* 2243 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDoubleValue(int valueId)
/*      */   {
/* 2252 */     switch (valueId) {
/*      */     case 0: 
/* 2254 */       return getWealth();
/* 2255 */     case 1:  return this.position; }
/* 2256 */     throw new UnsupportedOperationException("Bad argument");
/*      */   }
/*      */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\BFagent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */