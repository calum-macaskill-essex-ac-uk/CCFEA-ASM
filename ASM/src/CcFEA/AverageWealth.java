package CcFEA;

/*    */ import java.util.LinkedList;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AverageWealth
/*    */ {
/*    */   LinkedList<BFagent> agentList;
/*    */   int NumAgents;
/*    */   
/*    */   public Object InitList(LinkedList<BFagent> list, int numAgents)
/*    */   {
/* 20 */     this.agentList = list;
/* 21 */     this.NumAgents = numAgents;
/*    */     
/* 23 */     return this;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double averageWealth()
/*    */   {
/* 30 */     int average = 0;
/*    */     
/* 32 */     for (int i = 0; i < this.NumAgents; i++)
/*    */     {
/* 34 */       BFagent agent = (BFagent)this.agentList.get(i);
/* 35 */       average = (int)(average + agent.getWealth());
/*    */     }
/*    */     
/* 38 */     return average / this.NumAgents;
/*    */   }
/*    */ }


/* Location:              M:\pc\downloads\sCCFEA-ASM_beta1.jar!\AverageWealth.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */