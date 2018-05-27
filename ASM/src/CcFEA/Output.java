package CcFEA;

/*     */ import jas.engine.Sim;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ //import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Date;
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
/*     */ public class Output
/*     */ {
/*     */   private boolean dataFileExists;
/*     */   World outputWorld;
/*     */   Specialist outputSpecialist;
/*     */   ASMModelParams asmModelParams;
/*  24 */   long runTime = System.currentTimeMillis();
/*     */   
/*     */ 
/*  27 */   Date today = new Date(this.runTime);
/*     */   
/*     */ 
/*  30 */   String timeString = new String(this.today.toString());
/*     */   
/*     */ 
/*     */   public int currentTime;
/*     */   
/*     */ 
/*     */   String outputFile;
/*     */   
/*     */ 
/*     */   String paramFileName;
/*     */   
/*     */ 
/*     */   FileWriter fw;
/*     */   
/*     */   BufferedWriter bw;
/*     */   
/*     */   PrintWriter salida;
/*     */   
/*     */   FileWriter fw2;
/*     */   
/*     */   BufferedWriter bw2;
/*     */   
/*     */   PrintWriter salida2;
/*     */   
/*  54 */   public LinkedList<?> agentList = new LinkedList();
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
/*     */   public Object createEnd()
/*     */   {
/*  70 */     this.asmModelParams = new ASMModelParams();
/*     */     
/*  72 */     this.dataFileExists = false;
/*     */     
/*  74 */     this.timeString = this.timeString.replace(':', '_');
/*  75 */     this.timeString = this.timeString.replace(' ', '_');
/*     */     
/*  77 */     if (!ASMModelParams.batch) {
/*  78 */       this.paramFileName = new String("guiSettings");
/*     */     }
/*     */     else {
/*  81 */       this.paramFileName = new String("batchSettings");
/*     */     }
/*     */     
/*  84 */     this.paramFileName = this.paramFileName.concat(this.timeString);
/*  85 */     this.paramFileName = this.paramFileName.concat(".scm");
/*     */     
/*  87 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object setSpecialist(Specialist theSpec)
/*     */   {
/*  94 */     this.outputSpecialist = theSpec;
/*  95 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object setWorld(World theWorld)
/*     */   {
/* 103 */     this.outputWorld = theWorld;
/* 104 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object writeParams$BFAgent$Time(ASMModelParams modelParam, BFParams bfParms, long t)
/*     */   {
/*     */     try
/*     */     {
/* 116 */       this.fw = new FileWriter(this.paramFileName);
/* 117 */       this.bw = new BufferedWriter(this.fw);
/* 118 */       this.salida = new PrintWriter(this.bw);
/*     */       
/* 120 */       this.salida.println("Parameters at " + t);
/*     */       
/* 122 */       this.salida.println("\nModel Parameters\n");
/* 123 */       this.salida.println("\tnumBFagents = " + ASMModelParams.numBFagents);
/* 124 */       this.salida.println("\tinitholding = " + ASMModelParams.initholding);
/* 125 */       this.salida.println("\tinitialcash = " + ASMModelParams.initialcash);
/* 126 */       this.salida.println("\tminholding = " + ASMModelParams.minholding);
/* 127 */       this.salida.println("\tmincash = " + ASMModelParams.mincash);
/* 128 */       this.salida.println("\tintrate = " + ASMModelParams.intrate);
/*     */       
/* 130 */       this.salida.println("\n\tDividend parameters\n");
/* 131 */       this.salida.println("\tbaseline = " + ASMModelParams.baseline);
/* 132 */       this.salida.println("\tmindividend = " + ASMModelParams.mindividend);
/* 133 */       this.salida.println("\tmaxdividend = " + ASMModelParams.maxdividend);
/* 134 */       this.salida.println("\tamplitude = " + ASMModelParams.amplitude);
/* 135 */       this.salida.println("\tperiod = " + ASMModelParams.period);
/* 136 */       this.salida.println("\texponentialMAs = " + ASMModelParams.exponentialMAs);
/* 137 */       this.salida.println("\n\tSpecialist parameters\n");
/* 138 */       this.salida.println("\tmaxprice = " + ASMModelParams.maxprice);
/* 139 */       this.salida.println("\tminprice = " + ASMModelParams.minprice);
/* 140 */       this.salida.println("\ttaup = " + ASMModelParams.taup);
/* 141 */       this.salida.println("\tsptype = " + ASMModelParams.sptype);
/* 142 */       this.salida.println("\tmaxiterations = " + ASMModelParams.maxiterations);
/* 143 */       this.salida.println("\tminexcess = " + ASMModelParams.minexcess);
/* 144 */       this.salida.println("\teta = " + ASMModelParams.eta);
/* 145 */       this.salida.println("\tetamax = " + ASMModelParams.etamax);
/* 146 */       this.salida.println("\tetamin = " + ASMModelParams.etamin);
/* 147 */       this.salida.println("\trea = " + ASMModelParams.rea);
/* 148 */       this.salida.println("\treb = " + ASMModelParams.reb);
/* 149 */       this.salida.println("\trandomSeed= " + ASMModelParams.randomSeed);
/*     */       
/* 151 */       this.salida.println("\n\tAgent parameters\n");
/*     */       
/* 153 */       this.salida.println("\ttauv = " + ASMModelParams.tauv);
/* 154 */       this.salida.println("\tlambda = " + ASMModelParams.lambda);
/* 155 */       this.salida.println("\tmaxbid = " + ASMModelParams.maxbid);
/* 156 */       this.salida.println("\tinitvar = " + ASMModelParams.initvar);
/* 157 */       this.salida.println("\tmaxdev = " + ASMModelParams.maxdev);
/* 158 */       this.salida.println("\tsetOutputForData = " + ASMModelParams.setOutputForData);
/*     */       
/* 160 */       this.salida.println("\n\nBF Agents Parameters\n");
/* 161 */       this.salida.println("\tnumfcasts = " + BFParams.numfcasts);
/* 162 */       this.salida.println("\tcondwords =" + BFParams.condwords);
/* 163 */       this.salida.println("\tcondbits = " + BFParams.condbits);
/* 164 */       this.salida.println("\tmincount = " + BFParams.mincount);
/* 165 */       this.salida.println("\tgafrequency = " + BFParams.gafrequency);
/* 166 */       this.salida.println("\tfirstgatime = " + BFParams.firstgatime);
/* 167 */       this.salida.println("\tlongtime = " + BFParams.longtime);
/* 168 */       this.salida.println("\tindividual = " + BFParams.individual);
/* 169 */       this.salida.println("\ttauv = " + BFParams.tauv);
/* 170 */       this.salida.println("\tlambda = " + BFParams.lambda);
/* 171 */       this.salida.println("\tmaxbid = " + BFParams.maxbid);
/* 172 */       this.salida.println("\tbitprob = " + BFParams.bitprob);
/* 173 */       this.salida.println("\tsubrange = " + BFParams.subrange);
/* 174 */       this.salida.println("\ta_min = " + BFParams.a_min);
/* 175 */       this.salida.println("\ta_max = " + BFParams.a_max);
/* 176 */       this.salida.println("\tb_min = " + BFParams.b_min);
/* 177 */       this.salida.println("\tb_max = " + BFParams.b_max);
/* 178 */       this.salida.println("\tc_min = " + BFParams.c_min);
/* 179 */       this.salida.println("\tc_max = " + BFParams.c_max);
/* 180 */       this.salida.println("\ta_range = " + BFParams.a_range);
/* 181 */       this.salida.println("\tb_range = " + BFParams.b_range);
/* 182 */       this.salida.println("\tc_range = " + BFParams.c_range);
/* 183 */       this.salida.println("\tnewfcastvar = " + BFParams.newfcastvar);
/* 184 */       this.salida.println("\tinitvar = " + BFParams.initvar);
/* 185 */       this.salida.println("\tbitcost = " + BFParams.bitcost);
/* 186 */       this.salida.println("\tmaxdev = " + BFParams.maxdev);
/* 187 */       this.salida.println("\tpoolfrac = " + BFParams.poolfrac);
/* 188 */       this.salida.println("\tnewfrac = " + BFParams.newfrac);
/* 189 */       this.salida.println("\tpcrossover = " + BFParams.pcrossover);
/* 190 */       this.salida.println("\tplinear = " + BFParams.plinear);
/* 191 */       this.salida.println("\tprandom = " + BFParams.prandom);
/* 192 */       this.salida.println("\tpmutation = " + BFParams.pmutation);
/* 193 */       this.salida.println("\tplong = " + BFParams.plong);
/* 194 */       this.salida.println("\tpshort = " + BFParams.pshort);
/* 195 */       this.salida.println("\tnhood = " + BFParams.nhood);
/* 196 */       this.salida.println("\tgenfrac = " + BFParams.genfrac);
/* 197 */       this.salida.println("\tgaprob = " + BFParams.gaprob);
/* 198 */       this.salida.println("\tnpool = " + BFParams.npool);
/* 199 */       this.salida.println("\tnnew = " + BFParams.nnew);
/* 200 */       this.salida.println("\tnnulls = " + BFParams.nnulls);
/* 201 */       this.salida.close();
/*     */     }
/*     */     catch (IOException e) {
/* 204 */       System.err.println("Exception writing Parameters");
/*     */     }
/* 206 */     return this;
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
/*     */   public Object prepareOutputFile()
/*     */   {
/* 221 */     if (this.dataFileExists) { return this;
/*     */     }
/*     */     
/*     */ 
/* 225 */     this.outputFile = new String("output.data");
/* 226 */     this.outputFile = this.outputFile.concat(this.timeString);
/*     */     try
/*     */     {
/* 229 */       this.fw2 = new FileWriter(this.outputFile);
/* 230 */       this.bw2 = new BufferedWriter(this.fw2);
/* 231 */       this.salida2 = new PrintWriter(this.bw2);
/* 232 */       this.salida2.println("currentTime\t\t price\t dividend\t volume\t\t agent's wealth \n\n");
/*     */     } catch (IOException e) {
/* 234 */       System.err.println("Exception writing data");
/*     */     }
/*     */     
/* 237 */     this.dataFileExists = true;
/*     */     
/*     */ 
/* 240 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object writeData(LinkedList list)
/*     */   {
/* 251 */     long t = (int)Sim.getAbsoluteTime();
/* 252 */   //  String worldName = new String("world");
/* 253 */  //   String specName = new String("specialist");
/*     */     
/*     */ 
/*     */     try
/*     */     {
/* 258 */       this.salida2.print(t);
/* 259 */       this.salida2.print("\t\t");
/* 260 */       this.salida2.print((float)this.outputWorld.getPrice());
/* 261 */       this.salida2.print("\t");
/* 262 */       this.salida2.print((float)this.outputWorld.getDividend());
/* 263 */       this.salida2.print("\t");
/* 264 */       this.salida2.print((float)this.outputSpecialist.getVolume());
/* 265 */       this.salida2.print("\t");
/*     */       
/* 267 */     //  ASMModelParams asmModelParam = new ASMModelParams();
/* 268 */       this.agentList = list;
/*     */       
/*     */ 
/* 271 */       for (int i = 0; i < ASMModelParams.numBFagents; i++)
/*     */       {
/*     */ 
/* 274 */         BFagent agent = (BFagent)this.agentList.get(i);
/* 275 */         this.salida2.print("\t");
/* 276 */         this.salida2.print((float)agent.getWealth());
/*     */       }
/*     */       
/* 279 */       this.salida2.print("\n");
/*     */     } catch (Exception e) {
/* 281 */       System.err.println("Exception dataOutputFile.writeChars: " + e.getMessage());
/*     */     }
/*     */     
/* 284 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drop()
/*     */   {
/* 293 */     if (this.salida2 != null) {
/* 294 */       this.salida2.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\Output.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */