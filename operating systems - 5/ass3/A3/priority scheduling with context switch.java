import java.util.Arrays;
import java.util.Scanner;
public class Process {
            private String Pname;
            private String Pcolor;
            private int  sw ;//switch context
            private int At; //process arrival time
            private int Bt; //process burst time
            private int PriorNumber;//process priority number
            public Process() {}
            public Process(String Pname, String Pcolor, int At, int Bt, int Pnumber) {
                        this.Pname=Pname;
	this.Pcolor=Pcolor;
	this.At=At;
	this.Bt=Bt;
                        this.PriorNumber=Pnumber;
	}
	public void setProcessName(String name) {
                        this.Pname=name;
	}
	public String getProcessName() {
	return Pname;
	}
	public void setProcesscolor(String color) {
	this.Pcolor=color;
	}
	public String getProcessColor() {
	return Pcolor;
	}
	public void setArrivalTime(int t) {
	this.At=t;
	}
	public int getArrivalTime() {
	return At;
	}
	public void setBurstTime(int t) {
	this.Bt=t;
	}
	public int getBurstTime() {
	return this.Bt;
	}
	public void setPriorNumb(int p) {
	this.PriorNumber=p;
	}
	public int getPriorNumb() {
	return this.PriorNumber;
	}
                        public  void Priority_Scheduling_sw(){
                        System.out.println("* Priority Scheduling *");
                        System.out.print("Enter Number of Process: ");
                        Scanner sc = new Scanner(System.in);
                        int numberOfProcess = sc.nextInt();
                        String process[] = new String[numberOfProcess];
                        int p = 1;
                        for (int i = 0; i < numberOfProcess; i++) {
                        process[i] = "P" + p;
                        p++;
                        }
                        System.out.println(Arrays.toString(process));
                        System.out.print("Enter Priority for " + numberOfProcess + " process: ");
                        int priority[] = new int[numberOfProcess];
                        for (int i = 0; i < numberOfProcess; i++) {
                        priority[i] = sc.nextInt();
                        }
                        System.out.println(Arrays.toString(priority));
                        System.out.print("Enter Burst Time for " + numberOfProcess + " process: ");
                        int burstTime[] = new int[numberOfProcess];
                        for (int i = 0; i < numberOfProcess; i++) {
                        burstTime[i] = sc.nextInt();
                        }
                        System.out.println(Arrays.toString(burstTime));
                        System.out.print("Enter ArrivalTime for " + numberOfProcess + " process: ");
                        int At[] = new int[numberOfProcess];
                        for (int i = 0; i < numberOfProcess; i++) {
                        At[i] = sc.nextInt();
                        }
                        System.out.println(Arrays.toString(At));
                        System.out.println("Enter context switching: ");
                        sw=sc.nextInt();
                        
                        int tempAt;
                        int tempburst;
                        int tempPriority;
                        String tempProcess;
                        
                        for(int i=0; i<numberOfProcess-1;i++){
                        for (int j = 0; j < numberOfProcess - 1; j++) {
                        if (At[j]>At[j+1]){
                        tempAt = At[j];
                        At[j] = At[j+1 ];
                        At[j+1 ] = tempAt;
                        
                        tempPriority = priority[j];
                        priority[j] = priority[j+1];
                        priority[j+1] = tempPriority;
                        
                        tempburst = burstTime[j];
                        burstTime[j] = burstTime[j+1];
                        burstTime[j+1] = tempburst;
                        
                        tempProcess = process[j];
                        process[j] = process[j+1];
                        process[j+1] = tempProcess;
                        }}}
                        for(int i=1; i<numberOfProcess-1;i++){
                        for(int j = 1; j < numberOfProcess - 1; j++) {
                        if(priority[j]<priority[j+1]){
                        if(At[j]>At[j+1]){
                        priority[j+1]=priority[j+1]+1;                                
                        }
                        tempAt = At[j];
                        At[j] = At[j+1 ];
                        At[j+1 ] = tempAt;
                        
                        tempPriority = priority[j];
                        priority[j] = priority[j+1];
                        priority[j+1] = tempPriority;
                        
                        tempburst = burstTime[j];
                        burstTime[j] = burstTime[j+1];
                        burstTime[j+1] = tempburst;
                        
                        tempProcess = process[j];
                        process[j] = process[j+1];
                        process[j+1] = tempProcess;                                
                        }}}
             
                        
                        int TAT[] = new int[numberOfProcess + 1];
                        int waitingTime[] = new int[numberOfProcess + 1];
                        
                        // Calculating Waiting Time & Turn Around Time
                        for (int i = 0; i < numberOfProcess; i++) {
                        TAT[i] = burstTime[i] + waitingTime[i]+sw;
                        waitingTime[i + 1] = TAT[i];
                        }
                        int totalWT = 0;
                        int totalTAT = 0;
                        double avgWT;
                        double avgTAT;
                        System.out.println("Process:pri         At      BT       WT      TAT");
                        for (int i = 0; i < numberOfProcess; i++) {
                        System.out.println(process[i] + "           " + priority[i] + "           " + At[i] + "           " + burstTime[i] + "        " + waitingTime[i] + "        " + (TAT[i]));
                        totalTAT += (waitingTime[i] + burstTime[i]+sw);
                        totalWT += waitingTime[i];
                        }
                        avgWT = totalWT / (double) numberOfProcess;
                        avgTAT = totalTAT / (double) numberOfProcess;
                        System.out.println("\n Average Wating Time: " + avgWT);
                        System.out.println(" Average Turn Around Time: " + avgTAT);
                        }
                        
                        
                        public static void main(String[] args) {
                        Process myObj = new Process();
                        //Priority_Scheduling_sw
                        System.out.print("Enter type of Scheduling Algorithm: ");
                        Scanner Algorithm = new Scanner(System.in);
                        String algorithm = Algorithm.nextLine();  // Read Algorithm input
                        if(algorithm.equals("PrioritySchedulingsw")){
                         myObj.Priority_Scheduling_sw();
            }
    }  
}