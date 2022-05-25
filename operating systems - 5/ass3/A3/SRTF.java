import java.util.Scanner;

public class Process {

    private String Pname;
    private String Pcolor;
    private int At; //process arrival time
    private int Bt; //process burst time
    private int CBt; // current burst time
    private int Ct; //complete time
    private int starttime;
    private int turnaroundtime;
    private int waitingtime;
    private int PriorNumber;//process priority number
    private int waitingcount; // counter to change priority;
    private boolean complete = false;

    public Process() {
    }

    public Process(String Pname, String Pcolor, int At, int Bt, int Pnumber) {
        this.Pname = Pname;
        this.Pcolor = Pcolor;
        this.At = At;
        this.Bt = Bt;
        this.CBt = Bt;
        this.PriorNumber = Pnumber;
        this.waitingcount = 0;


    }

    public void setProcessName(String name) {
        this.Pname = name;
    }

    public String getProcessName() {
        return Pname;
    }


    public void setProcesscolor(String color) {
        this.Pcolor = color;
    }

    public String getProcessColor() {
        return Pcolor;
    }


    public void setArrivalTime(int t) {
        this.At = t;
    }

    public int getArrivalTime() {
        return At;
    }

    public void setBurstTime(int t) {
        this.Bt = t;
    }

    public int getBurstTime() {
        return this.Bt;
    }

    public void setcurrentBurstTime(int t) {
        this.CBt = t;
    }

    public int getcurrentBurstTime() {
        return this.CBt;
    }

    public void setCompletetime(int p) {
        this.Ct = p;
    }

    public int getcompletetime() {
        return this.Ct;
    }

    public void setstarttime(int p) {
        this.starttime = p;
    }

    public int getstarttime() {
        return this.starttime;
    }
    public void setTurnaroundtime(int p) {
        this.turnaroundtime = p;
    }

    public int getTurnaroundtime() {
        return this.turnaroundtime;
    }
    public void setwaitingtime(int p) {
        this.waitingtime = p;
    }
    public int getWaitingtime() {
        return this.waitingtime;
    }

    public int setwaiting() {
        return this.starttime;
    }

    public void setPriorNumb(int p) {
        this.PriorNumber = p;
    }

    public int getPriorNumb() {
        return this.PriorNumber;
    }

    public void setwaitingcount(int p) {
        this.waitingcount = p;
    }

    public int getwaitingcount() {
        return this.waitingcount;
    }

    public void setcomplete(boolean p) {
        this.complete = p;
    }

    public boolean getcomplete() {
        return this.complete;
    }

    //public

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        String name, color;
        int arrival_time, burst_time;
        int minburst = 1000;
        int order =0;
        int total_turn_around_time = 0;
        int total_waiting_time = 0;



        System.out.print("enter number of processes: ");
        int num_of_processes = input.nextInt();
        System.out.print("enter context switch: ");
        int cntxt_switch = input.nextInt();
        int prior = 2 * num_of_processes; // standard number for aging
        int current_time = 0;
        int complete_process_count = num_of_processes; // count complete processes in reverse



        System.out.println(prior);

        Process P[] = new Process[num_of_processes];

        for (int i = 0; i < num_of_processes; i = i + 1) // take processes input
        {
            System.out.println("enter process's; name, color, arrival time, burst time: ");
            name = input.next();
            color = input.next();
            arrival_time = input.nextInt();
            burst_time = input.nextInt();
            P[i] = new Process(name, color, arrival_time, burst_time, i + 1);
//            if (burst_time < minburst)
//            {
//                minburst = burst_time;
//            }
            order += burst_time;

        }

        String process_order[] = new String[order];
        int order_indx = 0;

        System.out.println("finished");
        String curr = P[0].getProcessName();



        while (complete_process_count != 0) {
            for (int i = 0; i < num_of_processes; i++) // to find smallest burst time
            {
                if (!P[i].getcomplete()) {
                    if (P[i].getwaitingcount() > prior) {
                        minburst = P[i].getcurrentBurstTime();

                        P[i].setwaitingcount(0);
                        break;
                    } else if (P[i].getArrivalTime() <= current_time) {
                        if (P[i].getcurrentBurstTime() < minburst) {
                            minburst = P[i].getcurrentBurstTime();

//                            System.out.printf("current minimum burst: %d, %s \n", minburst, P[i].getProcessName());
                        }
                    }
                }
            }

            for (int j = 0; j < num_of_processes; j++) {
                if (P[j].getcomplete()) {
                    continue;
                } else if (P[j].getcurrentBurstTime() == minburst) {
                    P[j].setcurrentBurstTime(P[j].getcurrentBurstTime() - 1);
                    process_order[order_indx] = P[j].getProcessName(); order_indx++;
                    P[j].setwaitingcount(0);

                    if(!P[j].getProcessName().equals(curr))
                    {
                        current_time = current_time + cntxt_switch;

                        curr = P[j].getProcessName();

                    }
                    current_time++;

//p1 t 0 8
                    //p2 n 2 3
                    //p3 p 7 7
                    //p4 u 10 2
                    System.out.printf("current time: %d, P name: %s, time burst left: %d \n", current_time, P[j].getProcessName(), P[j].getcurrentBurstTime());
                    if (P[j].getcurrentBurstTime() == 0) {
                        P[j].setCompletetime(current_time + cntxt_switch);
                        P[j].setcomplete(true);
                        complete_process_count--;

                        P[j].setTurnaroundtime(P[j].getcompletetime() - P[j].getArrivalTime());
                        P[j].setwaitingtime(P[j].getTurnaroundtime() - P[j].getBurstTime());

                        total_turn_around_time += P[j].getTurnaroundtime();
                        total_waiting_time += P[j].getWaitingtime();

                        System.out.printf("P name: %s, complete time: %s \n",P[j].getProcessName(), P[j].getcompletetime());
                    }
                    minburst = 1000;
                } else if (P[j].getArrivalTime() <= current_time) {
                    P[j].setwaitingcount(P[j].getwaitingcount() + 1);

                }
            }
        }

        for(int i = 0; i < order_indx; i++)
        {
            System.out.print(process_order[i] + "||");
        }

        System.out.println("\n waiting time, turnaround time for each process: ");

        for (int i = 0; i < num_of_processes;i++)
        {
            System.out.printf("Process name: %s, turn around time: %d , total waiting time: %d \n", P[i].getProcessName(), P[i].getTurnaroundtime(), P[i].getWaitingtime());
        }

        float avg_turnaround_time = (float) total_turn_around_time / num_of_processes;
        float avg_waiting_time = (float) total_waiting_time / num_of_processes;

        System.out.printf("average turn around time: %f , average waiting time: %f \n", avg_turnaround_time, avg_waiting_time);


    }
}