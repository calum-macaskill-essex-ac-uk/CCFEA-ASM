package CcFEA;

/*     */ import jas.engine.Sim;
/*     */ //import jas.random.RandomGenerator;
/*     */ //import java.io.PrintStream;
/*     */ import java.util.LinkedList;
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
/*     */ public class World
/*     */ {
/*     */   public static final int UPDOWNLOOKBACK = 5;
/*     */   public static final int NMAS = 4;
/*     */   public static final int MAXHISTORY = 500;
/*     */   public static int NWORLDBITS;
/*     */   public static final int NULLBIT = -1;
/*     */   public static final int PUPDOWNBITNUM = 42;
/*  37 */   public static final double[] ratios = { 0.25D, 0.5D, 0.75D, 0.875D, 1.0D, 1.125D, 1.25D, 1.5D, 2.0D, 4.0D };
/*     */   
/*     */ 
/*  40 */   public static final int NRATIOS = ratios.length;
/*     */   
/*     */ 
/*     */   public static final int EQ = 0;
/*     */   
/*     */   public double intrate;
/*     */   
/*     */   public double dividendscale;
/*     */   
/*  49 */   public int[] pupdown = new int[5];
/*     */   
/*  51 */   public int[] dupdown = new int[5];
/*     */   
/*     */ 
/*     */   public int history_top;
/*     */   
/*     */ 
/*     */   public int updown_top;
/*     */   
/*     */   public static double price;
/*     */   
/*     */   public double oldprice;
/*     */   
/*     */   public double dividend;
/*     */   
/*     */   public static double AverageWealth;
/*     */   
/*     */   public double olddividend;
/*     */   
/*     */   public double saveddividend;
/*     */   
/*     */   public double savedprice;
/*     */   
/*     */   public static double riskNeutral;
/*     */   
/*     */   public static double rationalExpectations;
/*     */   
/*     */   public double rea;
/*     */   
/*     */   public double reb;
/*     */   
/*     */   public double profitperunit;
/*     */   
/*     */   public double returnratio;
/*     */   
/*  85 */   public int[] malength = new int[4];
/*     */   
/*     */ 
/*     */   public int nworldbits;
/*     */   
/*     */ 
/*     */   int[] realworld;
/*     */   
/*     */ 
/*     */   public boolean exponentialMAs;
/*     */   
/*  96 */   public MovingAverage[] priceMA = new MovingAverage[4];
/*     */   
/*     */ 
/*  99 */   public MovingAverage[] divMA = new MovingAverage[4];
/*     */   
/*     */ 
/* 102 */   public MovingAverage[] oldpriceMA = new MovingAverage[4];
/*     */   
/*     */ 
/* 105 */   public MovingAverage[] olddivMA = new MovingAverage[4];
/*     */   
/*     */ 
/* 108 */   public static LinkedList<BitName> bitnameList = new LinkedList<BitName>();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[] divhistory;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[] pricehistory;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void createBitnameList()
/*     */   {
/* 135 */     bitnameList.add(new BitName("on", "dummy bit -- always on"));
/* 136 */     bitnameList.add(new BitName("off", "dummy bit -- always off"));
/* 137 */     bitnameList.add(new BitName("random", "random on or off"));
/*     */     
/* 139 */     bitnameList.add(new BitName("dup", "dividend went up this period"));
/* 140 */     bitnameList.add(new BitName("dup1", "dividend went up one period ago"));
/* 141 */     bitnameList.add(new BitName("dup2", "dividend went up two periods ago"));
/* 142 */     bitnameList.add(new BitName("dup3", "dividend went up three periods ago"));
/* 143 */     bitnameList.add(new BitName("dup4", "dividend went up four periods ago"));
/*     */     
/* 145 */     bitnameList.add(new BitName("d5up", "5-period MA of dividend went up"));
/* 146 */     bitnameList.add(new BitName("d20up", "20-period MA of dividend went up"));
/* 147 */     bitnameList.add(new BitName("d100up", "100-period MA of dividend went up"));
/* 148 */     bitnameList.add(new BitName("d500up", "500-period MA of dividend went up"));
/*     */     
/* 150 */     bitnameList.add(new BitName("d>d5", "dividend > 5-period MA"));
/* 151 */     bitnameList.add(new BitName("d>d20", "dividend > 20-period MA"));
/* 152 */     bitnameList.add(new BitName("d>d100", "dividend > 100-period MA"));
/* 153 */     bitnameList.add(new BitName("d>d500", "dividend > 500-period MA"));
/*     */     
/* 155 */     bitnameList.add(new BitName("d5>d20", "dividend: 5-period MA > 20-period MA"));
/* 156 */     bitnameList.add(new BitName("d5>d100", "dividend: 5-period MA > 100-period MA"));
/* 157 */     bitnameList.add(new BitName("d5>d500", "dividend: 5-period MA > 500-period MA"));
/* 158 */     bitnameList.add(new BitName("d20>d100", "dividend: 20-period MA > 100-period MA"));
/* 159 */     bitnameList.add(new BitName("d20>d500", "dividend: 20-period MA > 500-period MA"));
/* 160 */     bitnameList.add(new BitName("d100>d500", "dividend: 100-period MA > 500-period MA"));
/*     */     
/* 162 */     bitnameList.add(new BitName("d/md>1/4", "dividend/mean dividend > 1/4"));
/* 163 */     bitnameList.add(new BitName("d/md>1/2", "dividend/mean dividend > 1/2"));
/* 164 */     bitnameList.add(new BitName("d/md>3/4", "dividend/mean dividend > 3/4"));
/* 165 */     bitnameList.add(new BitName("d/md>7/8", "dividend/mean dividend > 7/8"));
/* 166 */     bitnameList.add(new BitName("d/md>1", "dividend/mean dividend > 1  "));
/* 167 */     bitnameList.add(new BitName("d/md>9/8", "dividend/mean dividend > 9/8"));
/* 168 */     bitnameList.add(new BitName("d/md>5/4", "dividend/mean dividend > 5/4"));
/* 169 */     bitnameList.add(new BitName("d/md>3/2", "dividend/mean dividend > 3/2"));
/* 170 */     bitnameList.add(new BitName("d/md>2", "dividend/mean dividend > 2"));
/* 171 */     bitnameList.add(new BitName("d/md>4", "dividend/mean dividend > 4"));
/*     */     
/* 173 */     bitnameList.add(new BitName("pr/d>1/4", "price*interest/dividend > 1/4"));
/* 174 */     bitnameList.add(new BitName("pr/d>1/2", "price*interest/dividend > 1/2"));
/* 175 */     bitnameList.add(new BitName("pr/d>3/4", "price*interest/dividend > 3/4"));
/* 176 */     bitnameList.add(new BitName("pr/d>7/8", "price*interest/dividend > 7/8"));
/* 177 */     bitnameList.add(new BitName("pr/d>1", "price*interest/dividend > 1"));
/* 178 */     bitnameList.add(new BitName("pr/d>9/8", "price*interest/dividend > 9/8"));
/* 179 */     bitnameList.add(new BitName("pr/d>5/4", "price*interest/dividend > 5/4"));
/* 180 */     bitnameList.add(new BitName("pr/d>3/2", "price*interest/dividend > 3/2"));
/* 181 */     bitnameList.add(new BitName("pr/d>2", "price*interest/dividend > 2"));
/* 182 */     bitnameList.add(new BitName("pr/d>4", "price*interest/dividend > 4"));
/*     */     
/* 184 */     bitnameList.add(new BitName("pup", "price went up this period"));
/* 185 */     bitnameList.add(new BitName("pup1", "price went up one period ago"));
/* 186 */     bitnameList.add(new BitName("pup2", "price went up two periods ago"));
/* 187 */     bitnameList.add(new BitName("pup3", "price went up three periods ago"));
/* 188 */     bitnameList.add(new BitName("pup4", "price went up four periods ago"));
/*     */     
/* 190 */     bitnameList.add(new BitName("p5up", "5-period MA of price went up"));
/* 191 */     bitnameList.add(new BitName("p20up", "20-period MA of price went up"));
/* 192 */     bitnameList.add(new BitName("p100up", "100-period MA of price went up"));
/* 193 */     bitnameList.add(new BitName("p500up", "500-period MA of price went up"));
/*     */     
/* 195 */     bitnameList.add(new BitName("p>p5", "price > 5-period MA"));
/* 196 */     bitnameList.add(new BitName("p>p20", "price > 20-period MA"));
/* 197 */     bitnameList.add(new BitName("p>p100", "price > 100-period MA"));
/* 198 */     bitnameList.add(new BitName("p>p500", "price > 500-period MA"));
/*     */     
/* 200 */     bitnameList.add(new BitName("p5>p20", "price: 5-period MA > 20-period MA"));
/* 201 */     bitnameList.add(new BitName("p5>p100", "price: 5-period MA > 100-period MA"));
/* 202 */     bitnameList.add(new BitName("p5>p500", "price: 5-period MA > 500-period MA"));
/* 203 */     bitnameList.add(new BitName("p20>p100", "price: 20-period MA > 100-period MA"));
/* 204 */     bitnameList.add(new BitName("p20>p500", "price: 20-period MA > 500-period MA"));
/* 205 */     bitnameList.add(new BitName("p100>p500", "price: 100-period MA > 500-period MA"));
/*     */     
/* 207 */     NWORLDBITS = bitnameList.size();
/*     */   }
/*     */   
/*     */ 
/*     */   public int irand(int x)
/*     */   {
/* 213 */     return Sim.getRnd().getIntFromTo(0, x - 1);
/*     */   }
/*     */   
/*     */ 
/*     */   public double GETMA(MovingAverage[] x, int j)
/*     */   {
/* 219 */     return this.exponentialMAs ? x[j].getEWMA() : x[j].getMA();
/*     */   }
/*     */   
/*     */   public int ChangeBooleanToInt(boolean a) {
/* 223 */     if (a)
/* 224 */       return 1;
/* 225 */     return 0;
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
/*     */   public static String descriptionOfBit(int n)
/*     */   {
/* 246 */     if (n == -1)
/* 247 */       return "(Unused bit for spacing)";
/* 248 */     if ((n < 0) || (n >= NWORLDBITS))
/* 249 */       return "(Invalid world bit)";
/* 250 */     return ((BitName)bitnameList.get(n)).description;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String nameOfBit(int n)
/*     */   {
/* 261 */     if (n == -1)
/* 262 */       return "null";
/* 263 */     if ((n < 0) || (n >= NWORLDBITS))
/* 264 */       return "";
/* 265 */     return ((BitName)bitnameList.get(n)).name;
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
/*     */   public static int bitNumberOf(String name)
/*     */   {
	int n;
/* 278 */     for ( n = 0; n < NWORLDBITS; n++)
/* 279 */       if (name.compareTo(((BitName)bitnameList.get(n)).name) == 0)
/*     */         break;
/* 281 */     if ( n >= NWORLDBITS) {
				n = -1;
/*     */     }
/* 283 */     return n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setintrate(double rate)
/*     */   {
/* 290 */     this.intrate = rate;
/* 291 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setExponentialMAs(boolean aBool)
/*     */   {
/* 300 */     this.exponentialMAs = aBool;
/* 301 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public int getNumWorldBits()
/*     */   {
/* 307 */     return this.nworldbits;
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
/*     */   public Object initWithBaseline(double baseline)
/*     */   {
/* 320 */     if (nameOfBit(42).compareTo("pup") != 0) {
/* 321 */       System.out.println("PUPDOWNBITNUM is incorrect");
/*     */     }
/*     */     
/* 324 */     this.dividendscale = baseline;
/* 325 */     double initprice = baseline / this.intrate;
/* 326 */     double initdividend = baseline;
/* 327 */     this.saveddividend = (this.dividend = initdividend);
/* 328 */     setDividend(initdividend);
/* 329 */     this.savedprice = (price = initprice);
/* 330 */     setPrice(initprice);
/*     */     
/*     */ 
/* 333 */     this.returnratio = this.intrate;
/* 334 */     this.profitperunit = 0.0D;
/*     */     
/*     */ 
/* 337 */     this.nworldbits = NWORLDBITS;
/*     */     
/* 339 */     this.malength[0] = 5;
/* 340 */     this.malength[1] = 20;
/* 341 */     this.malength[2] = 100;
/* 342 */     this.malength[3] = 500;
/*     */     
/* 344 */     this.history_top = 0;
/* 345 */     this.updown_top = 0;
/*     */     // Calum 22/05/2018 quick fix?
/* 347 */     this.divhistory = new double[100000];
/* 348 */     this.pricehistory = new double[100000];
/*     */     
/* 350 */     this.realworld = new int[NWORLDBITS];
/*     */     
/*     */ 
/* 353 */     for (int i = 0; i < 5; i++)
/*     */     {
/* 355 */       this.pupdown[i] = 0;
/* 356 */       this.dupdown[i] = 0;
/*     */     }
/*     */     
/* 359 */     for (int i = 0; i < 500; i++)
/*     */     {
/* 361 */       this.pricehistory[i] = initprice;
/* 362 */       this.divhistory[i] = initdividend;
/*     */     }
/*     */     
/* 365 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 367 */       MovingAverage a = new MovingAverage();
/* 368 */       this.priceMA[i] = a;
/* 369 */       this.priceMA[i].initWidth$Value(this.malength[i], initprice);
/*     */       
/* 371 */       MovingAverage b = new MovingAverage();
/* 372 */       this.divMA[i] = b;
/* 373 */       this.divMA[i].initWidth$Value(this.malength[i], initdividend);
/*     */       
/* 375 */       MovingAverage c = new MovingAverage();
/* 376 */       this.oldpriceMA[i] = c;
/* 377 */       this.oldpriceMA[i].initWidth$Value(this.malength[i], initprice);
/*     */       
/* 379 */       MovingAverage d = new MovingAverage();
/* 380 */       this.olddivMA[i] = d;
/* 381 */       this.olddivMA[i].initWidth$Value(this.malength[i], initdividend);
/*     */     }
/*     */     
/*     */ 
/* 385 */     makebitvector();
/*     */     
/* 387 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setPrice(double p)
/*     */   {
/* 398 */     if (price != this.savedprice) {
/* 399 */       System.out.println("Price was changed illegally");
/*     */     }
/* 401 */     this.oldprice = price;
/* 402 */     price = p;
/*     */     
/* 404 */     this.profitperunit = (price - this.oldprice + this.dividend);
/* 405 */     if (this.oldprice <= 0.0D) {
/* 406 */       this.returnratio = (this.profitperunit * 1000.0D);
/*     */     } else {
/* 408 */       this.returnratio = (this.profitperunit / this.oldprice);
/*     */     }
/* 410 */     this.savedprice = price;
/*     */     
/* 412 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getPrice()
/*     */   {
/* 419 */     return price;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getProfitPerUnit()
/*     */   {
/* 426 */     return this.profitperunit;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setDividend(double d)
/*     */   {
/* 435 */     if (this.dividend != this.saveddividend) {
/* 436 */       System.out.println("Dividend was changed illegally.");
/*     */     }
/* 438 */     this.olddividend = this.dividend;
/* 439 */     this.dividend = d;
/*     */     
/* 441 */     this.saveddividend = this.dividend;
/* 442 */     riskNeutral = this.dividend / this.intrate;
/* 443 */     rationalExpectations = this.rea * this.dividend + this.reb;
/* 444 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getDividend()
/*     */   {
/* 451 */     return this.dividend;
/*     */   }
/*     */   
/*     */ 
/*     */   public Object setAverageWealth(double avr)
/*     */   {
/* 457 */     AverageWealth = avr;
/*     */     
/* 459 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public double getAverageWealth()
/*     */   {
/* 465 */     return AverageWealth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getRiskNeutral()
/*     */   {
/* 472 */     return riskNeutral;
/*     */   }
/*     */   
/*     */   public double getRationalExpectations()
/*     */   {
/* 477 */     return rationalExpectations;
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
/*     */   public Object updateWorld()
/*     */   {
/* 498 */     this.updown_top = ((this.updown_top + 1) % 5);
/* 499 */     this.pupdown[this.updown_top] = ChangeBooleanToInt(price > this.oldprice);
/* 500 */     this.dupdown[this.updown_top] = ChangeBooleanToInt(this.dividend > this.olddividend);
/*     */     
/*     */ 
/* 503 */     this.history_top = (this.history_top + 1 + 500);
/*     */     
/*     */ 
/*     */ 
/* 507 */     for (int i = 0; i < 4; i++)
/*     */     {
/* 509 */       int rago = (this.history_top - this.malength[i]) % 500;
/*     */       
/* 511 */       this.priceMA[i].addValue(price);
/* 512 */       this.divMA[i].addValue(this.dividend);
/*     */       
/* 514 */       this.oldpriceMA[i].addValue(this.pricehistory[rago]);
/* 515 */       this.olddivMA[i].addValue(this.divhistory[rago]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 520 */     this.history_top %= 500;
/* 521 */     this.pricehistory[this.history_top] = price;
/* 522 */     this.divhistory[this.history_top] = this.dividend;
/*     */     
/*     */ 
/* 525 */     makebitvector();
/*     */     
/* 527 */     return this;
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
/*     */   private Object makebitvector()
/*     */   {
/* 541 */     int i = 0;
/*     */     
/* 543 */     this.realworld[(i++)] = 1;
/* 544 */     this.realworld[(i++)] = 0;
/* 545 */     this.realworld[(i++)] = irand(2);
/*     */     
/*     */ 
/* 548 */     int temp = this.updown_top + 5;
/* 549 */     for (int j = 0; j < 5; temp--) {
/* 550 */       this.realworld[(i++)] = this.dupdown[(temp % 5)];j++;
/*     */     }
/*     */     
/* 553 */     for (int j = 0; j < 4; j++) {
/* 554 */       this.realworld[(i++)] = ChangeBooleanToInt(GETMA(this.divMA, j) > GETMA(this.olddivMA, j));
/*     */     }
/*     */     
/* 557 */     for (int j = 0; j < 4; j++) {
/* 558 */       this.realworld[(i++)] = ChangeBooleanToInt(this.dividend > GETMA(this.divMA, j));
/*     */     }
/*     */     
/* 561 */     for (int j = 0; j < 3; j++) {
/* 562 */       for (int k = j + 1; k < 4; k++) {
/* 563 */         this.realworld[(i++)] = ChangeBooleanToInt(GETMA(this.divMA, j) > GETMA(this.divMA, k));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 568 */     double multiple = this.dividend / this.dividendscale;
/* 569 */     for (int j = 0; j < NRATIOS; j++) {
/* 570 */       this.realworld[(i++)] = ChangeBooleanToInt(multiple > ratios[j]);
/*     */     }
/*     */     
/*     */ 
/* 574 */     multiple = price * this.intrate / this.olddividend;
/* 575 */     for (int j = 0; j < NRATIOS; j++) {
/* 576 */       this.realworld[(i++)] = ChangeBooleanToInt(multiple > ratios[j]);
/*     */     }
/*     */     
/* 579 */     temp = this.updown_top + 5;
/* 580 */     for (int j = 0; j < 5; temp--) {
/* 581 */       this.realworld[(i++)] = this.pupdown[(temp % 5)];j++;
/*     */     }
/*     */     
/* 584 */     for (int j = 0; j < 4; j++) {
/* 585 */       this.realworld[(i++)] = ChangeBooleanToInt(GETMA(this.priceMA, j) > GETMA(this.oldpriceMA, j));
/*     */     }
/*     */     
/*     */ 
/* 589 */     for (int j = 0; j < 4; j++) {
/* 590 */       this.realworld[(i++)] = ChangeBooleanToInt(price > GETMA(this.priceMA, j));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 595 */     for (int j = 0; j < 3; j++) {
/* 596 */       for (int k = j + 1; k < 4; k++) {
/* 597 */         this.realworld[(i++)] = ChangeBooleanToInt(GETMA(this.priceMA, j) > GETMA(this.priceMA, k));
/*     */       }
/*     */     }
/* 600 */     if (i != NWORLDBITS) {
/* 601 */       System.out.println("Bits calculated != bits defined.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 608 */     for (i = 0; i < NWORLDBITS; i++) {
/* 609 */       this.realworld[i] = (2 - this.realworld[i]);
/*     */     }
/* 611 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object getRealWorld(int[] anArray)
/*     */   {
/* 619 */     System.arraycopy(this.realworld, 0, anArray, 0, NWORLDBITS);
/* 620 */     return this;
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
/*     */   public int pricetrend(int n)
/*     */   {
	         int trend;
/* 634 */     if (n > 5)
/* 635 */       System.out.println("argument " + n + " to pricetrend() exceeds " + 5);
/* 636 */     int i = 0; for (trend = 0; i < n; i++) {
/* 637 */       trend |= this.realworld[(i + 42)];
/*     */     }
/* 639 */     if (trend == 1)
/* 640 */       return 1;
/* 641 */     if (trend == 2) {
/* 642 */       return -1;
/*     */     }
/* 644 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setRea$Reb(double rea1, double reb1)
/*     */   {
/* 651 */     this.rea = rea1;
/* 652 */     this.reb = reb1;
/* 653 */     return this;
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\World.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */