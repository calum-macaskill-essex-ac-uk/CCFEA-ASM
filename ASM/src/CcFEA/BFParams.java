package CcFEA;

/*     */ //import java.io.PrintStream;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class BFParams
/*     */ {
/*   6 */   public static int numfcasts = 100;
/*     */   public static int condwords;
/*   8 */   public static int condbits = 16;
/*   9 */   public static int mincount = 5;
/*  10 */   public static int gafrequency = 250;
/*  11 */   public static int firstgatime = 250;
/*  12 */   public static int longtime = 250;
/*  13 */   public static int individual = 0;
/*  14 */   public static double tauv = 75.0D;
/*  15 */   public static double lambda = 0.5D;
/*  16 */   public static double maxbid = 10.0D;
/*  17 */   public static double bitprob = 0.1D;
/*  18 */   public static double subrange = 0.5D;
/*  19 */   public static double a_min = 0.7D; public static double a_max = 1.2D;
/*  20 */   public static double b_min = 0.0D; public static double b_max = 0.0D;
/*  21 */   public static double c_min = -7.293691545D; public static double c_max = 21.70630846D;
/*     */   public static double a_range;
/*  23 */   public static double b_range; public static double c_range; public static double newfcastvar = 3.999769641D;
/*  24 */   public static double initvar = 3.999769641D;
/*  25 */   public static double bitcost = 0.01D;
/*  26 */   public static double maxdev = 100.0D;
/*  27 */   public static double poolfrac = 0.1D;
/*  28 */   public static double newfrac = 0.05D;
/*  29 */   public static double pcrossover = 0.3D;
/*  30 */   public static double plinear = 0.333D;
/*  31 */   public static double prandom = 0.333D;
/*  32 */   public static double pmutation = 0.01D;
/*  33 */   public static double plong = 0.05D;
/*  34 */   public static double pshort = 0.2D;
/*  35 */   public static double nhood = 0.05D;
/*  36 */   public static double genfrac = 0.1D;
/*  37 */   public static double gaprob = 1.0D;
/*     */   public static int npool;
/*     */   public static int nnew;
/*  40 */   public static int nnulls = 0;
/*  41 */   public static int[] bitlist = new int[condbits];
/*  42 */   public static double[] problist = new double[condbits];
/*     */   
/*  44 */   public static int npoolmax = -1;
/*  45 */   public static int nnewmax = -1;
/*  46 */   public static int ncondmax = -1;
/*     */   
/*     */   public static final int MAXCONDBITS = 80;
/*     */   
/*     */   public static final int ENDLIST = -2;
/*     */   
/*     */   public static final int ALL = -3;
/*     */   
/*     */   public static final int SETPROB = -4;
/*     */   public static final int BADINPUT = -5;
/*     */   public static final int NOTFOUND = -6;
/*     */   public static final int EQ = 0;
/*     */   public static final int NULLBIT = -1;
/*  59 */   public static KeyTable[] specialbits = new KeyTable[9];
/*     */   
/*     */ 
/*     */ 
/*     */   public int WORD(int bit)
/*     */   {
/*  65 */     int a = bit >> 4;
/*  66 */     return a;
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
/*     */   public Object init()
/*     */   {
/* 143 */     int[] bits = new int[80];
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 150 */     boolean USEALLBITS = false;
/*     */     
/* 152 */     if (!USEALLBITS)
/*     */     {
/* 154 */       bits[0] = ReadBitname("pr/d>1/4", specialbits);
/* 155 */       bits[1] = ReadBitname("pr/d>1/2", specialbits);
/* 156 */       bits[2] = ReadBitname("pr/d>3/4", specialbits);
/* 157 */       bits[3] = ReadBitname("pr/d>7/8", specialbits);
/* 158 */       bits[4] = ReadBitname("pr/d>1", specialbits);
/* 159 */       bits[5] = ReadBitname("pr/d>9/8", specialbits);
/* 160 */       bits[6] = ReadBitname("pr/d>5/4", specialbits);
/* 161 */       bits[7] = ReadBitname("pr/d>3/2", specialbits);
/* 162 */       bits[8] = ReadBitname("pr/d>2", specialbits);
/* 163 */       bits[9] = ReadBitname("pr/d>4", specialbits);
/* 164 */       bits[10] = ReadBitname("p>p5", specialbits);
/* 165 */       bits[11] = ReadBitname("p>p20", specialbits);
/* 166 */       bits[12] = ReadBitname("p>p100", specialbits);
/* 167 */       bits[13] = ReadBitname("p>p500", specialbits);
/* 168 */       bits[14] = ReadBitname("on", specialbits);
/* 169 */       bits[15] = ReadBitname("off", specialbits);
/*     */     }
/*     */     else
/*     */     {
/* 173 */       condbits = 60;
/*     */       
/* 175 */       for (int i = 0; i < condbits; i++) { bits[i] = i;
/*     */       }
/*     */     }
/* 178 */     for (int i = 0; i < condbits; i++)
/*     */     {
/* 180 */       bitlist[i] = bits[i];
/*     */       
/* 182 */       problist[i] = bitprob;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 187 */     condwords = (condbits + 15) / 16;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 195 */     if (1.0D + bitcost * (condbits - nnulls) <= 0.0D) {
/* 196 */       System.out.println("The bitcost is too negative.");
/*     */     }
/*     */     
/*     */ 
/* 200 */     a_range = a_max - a_min;
/* 201 */     b_range = b_max - b_min;
/* 202 */     c_range = c_max - c_min;
/*     */     
/* 204 */     npool = (int)(numfcasts * poolfrac + 0.5D);
/* 205 */     nnew = (int)(numfcasts * newfrac + 0.5D);
/*     */     
/*     */ 
/* 208 */     if (npool > npoolmax) npoolmax = npool;
/* 209 */     if (nnew > nnewmax) nnewmax = nnew;
/* 210 */     if (condwords > ncondmax) { ncondmax = condwords;
/*     */     }
/* 212 */     return this;
/*     */   }
/*     */   
/*     */   public int[] getBitListPtr()
/*     */   {
/* 217 */     return bitlist;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void copyBitList$Length(int[] x, int size)
/*     */   {
/* 225 */     for (int i = 0; i < size; i++)
/*     */     {
/* 227 */       bitlist[i] = x[i];
/*     */     }
/*     */   }
/*     */   
/*     */   public double[] getProbListPtr()
/*     */   {
/* 233 */     return problist;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void copyProbList$Length(double[] p, int size)
/*     */   {
/* 241 */     for (int i = 0; i < size; i++)
/*     */     {
/* 243 */       problist[i] = p[i];
/*     */     }
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
/*     */   public int ReadBitname(String variable, KeyTable[] table)
/*     */   {
/* 257 */     int n = World.bitNumberOf(variable);
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
/* 269 */     return n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BFParams copy()
/*     */   {
/* 277 */     BFParams bfParams = new BFParams(true);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ // Calum 21/05/2018 these are causing warnings
/* 286 */   //  numfcasts = numfcasts;
/* 287 */  //   condwords = condwords;
/* 288 */  //   condbits = condbits;
/* 289 */  //   mincount = mincount;
/* 290 */  //   gafrequency = gafrequency;
/* 291 */ //    firstgatime = firstgatime;
/* 292 */  //   longtime = longtime;
/* 293 */  //   individual = individual;
/* 294 */  //   tauv = tauv;
/* 295 */   //  lambda = lambda;
/* 296 */  //   maxbid = maxbid;
/* 297 */  //   bitprob = bitprob;
/* 298 */  //   subrange = subrange;
/* 299 */  //   a_min = a_min;
/* 300 */  //   a_max = a_max;
/* 301 */  //   b_min = b_min;
/* 302 */  //   b_max = b_max;
/* 303 */    // c_min = c_min;
/* 304 */   //  c_max = c_max;
/* 305 */   //  a_range = a_range;
/* 306 */   //  b_range = b_range;
/* 307 */   //  c_range = c_range;
/* 308 */   //  newfcastvar = newfcastvar;
/* 309 */  //   initvar = initvar;
/* 310 */   //  bitcost = bitcost;
/* 311 */   //  maxdev = maxdev;
/* 312 */   //  poolfrac = poolfrac;
/* 313 */   //  newfrac = newfrac;
/* 314 */   //  pcrossover = pcrossover;
/* 315 */   //  plinear = plinear;
/* 316 */   //  prandom = prandom;
/* 317 */   //  pmutation = pmutation;
/* 318 */   //  plong = plong;
/* 319 */   //  pshort = pshort;
/* 320 */   //  nhood = nhood;
/* 321 */   //  genfrac = genfrac;
/*     */     
/* 323 */   ///  npool = npool;
/* 324 */   //  nnew = nnew;
/* 325 */   //  nnulls = nnulls;
/* 326 */   //  npoolmax = npoolmax;
/* 327 */   //  nnewmax = nnewmax;
/* 328 */   //  ncondmax = ncondmax;
/*     */     
/* 330 */     bfParams.copyBitList$Length(bitlist, condbits);
/* 331 */     bfParams.copyProbList$Length(problist, condbits);
/* 332 */     return bfParams;
/*     */   }
/*     */   
/*     */   BFParams()
/*     */   {
/* 337 */     specialbits[0] = new KeyTable("null", -1);
/* 338 */     specialbits[1] = new KeyTable("end", -2);
/* 339 */     specialbits[2] = new KeyTable(".", -2);
/* 340 */     specialbits[3] = new KeyTable("all", -3);
/* 341 */     specialbits[4] = new KeyTable("allbits", -3);
/* 342 */     specialbits[5] = new KeyTable("p", -4);
/* 343 */     specialbits[6] = new KeyTable("P", -4);
/* 344 */     specialbits[7] = new KeyTable("???", -5);
/* 345 */     specialbits[8] = new KeyTable(null, -6);
/*     */   }
/*     */   
/*     */   BFParams(boolean a) {}
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\BFParams.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */