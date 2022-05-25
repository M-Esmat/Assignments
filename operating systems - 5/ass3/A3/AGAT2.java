import java.util.Scanner;
import java.util.LinkedList;

import static java.lang.Math.*;


class Process {
    private String Pname;
    private String Pcolor;
    private int At; //process arrival time
    private int Bt; //process burst time
    private int CBt; // current burst time
    private int Ct; //complete time
    private int PriorNumber;//process priority number
    private int waitingcount; // counter to change priority;
    private boolean complete = false;
    private int Qt; //process quantum time
    private int TA; //process turnaround time
    private float V1;
    private float V2;
    private int AGAT_Factor;


    public Process(String Pname, String Pcolor, int At, int Bt, int priorNumber, int Qt) {
        this.Pname = Pname;
        this.Pcolor = Pcolor;
        this.At = At;
        this.Bt = Bt;
        this.CBt = Bt;
        this.PriorNumber = priorNumber;
        this.waitingcount = 0;
        this.Qt = Qt;
    }


    public String getProcessName() {
        return Pname;
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

    public void setQt(int t) {
        this.Qt = t;
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

    public int getQt() {
        return this.Qt;
    }


    public void setV1(float V1) {
        this.V1 = V1;
    }

    public void setV2(float V2) {
        this.V2 = V2;
    }

    public void setAGAT_Factor(int AGAT_Factor) {
        this.AGAT_Factor = AGAT_Factor;
    }

    public int getAGAT_Factor() {
        return this.AGAT_Factor;
    }

    public void setTA(int TA) {
        this.TA = TA;
    }

    public int getTA() {
        return this.TA;
    }
}

public class AGAT {
    static void setFactor(Process[] processes, int time, Process process, int workingtime) {
        int maxAT = 0, maxBT = 0, V1 = 1, V2 = 1;

        for (int i = 0; i < processes.length; i++) {
            if (processes[i].getArrivalTime() > maxAT && processes[i].getArrivalTime() <= time) {
                maxAT = processes[i].getArrivalTime();
            }
            if (processes[i].getcurrentBurstTime() > maxBT && processes[i].getArrivalTime() <= time) {
                maxBT = processes[i].getBurstTime();
            }
        }
        if (maxAT > 10) {
            V1 = maxAT / 10;

        }
        if (maxBT > 10) {
            V2 = maxBT / 10;
        }
        int AGAT_factor;
        for (int i = 0; i < processes.length; i++) {
            if (processes[i] == process && processes[i].getcurrentBurstTime() > 0) {
                processes[i].setV1(V1);
                processes[i].setV2(V2);
                AGAT_factor = (int) ((10 - processes[i].getPriorNumb()) +
                        ((processes[i].getArrivalTime() / ceil(V1))) +
                        (processes[i].getcurrentBurstTime() + workingtime / ceil(V2)));
                processes[i].setAGAT_Factor(AGAT_factor);
            } else if (processes[i].getArrivalTime() <= time && processes[i].getcurrentBurstTime() > 0) {
                processes[i].setV1(V1);
                processes[i].setV2(V2);
                AGAT_factor = (int) ((10 - processes[i].getPriorNumb()) +
                        ((processes[i].getArrivalTime() / ceil(V1))) +
                        (processes[i].getcurrentBurstTime() / ceil(V2)));
                processes[i].setAGAT_Factor(AGAT_factor);
            } else {
                processes[i].setAGAT_Factor(0);
            }
        }
    }

    static void addtoQueue(Process[] processes, int time, LinkedList<Process> processQueue, Process process) {
        for (int i = 0; i < processes.length; i++) {
            if (processes[i] == process) {
                continue;
            }
            if (processes[i].getcurrentBurstTime() == 0 && processQueue.contains(processes[i])) {
                processQueue.remove(processes[i]);
            }
            if (processes[i].getArrivalTime() <= time
                    && !processQueue.contains(processes[i])
                    && processes[i].getcurrentBurstTime() > 0) {
                processQueue.add(processes[i]);
            }
        }
    }

    static void showInfo(Process[] processes, int time, LinkedList<Process> processLinkedList) {
        System.out.println("Time is: " + time);
        System.out.println("PName, PArrival, PTimeLeft, PPriority, PQuantum, PAGAT-Factor:");
        for (int i = 0; i < processes.length; i++) {
            System.out.println(processes[i].getProcessName() +
                    "         " + processes[i].getArrivalTime() +
                    "         " + processes[i].getcurrentBurstTime() +
                    "         " + processes[i].getPriorNumb() +
                    "         " + processes[i].getQt() +
                    "         " + processes[i].getAGAT_Factor());
        }
    }

    static int nonPreemptive(Process[] processes, int time, Process process, LinkedList<Process> pLL, LinkedList<Process> dead, int con) {
        int starttime = time;
        System.out.println(process.getProcessName() + " is working");
        if (process.getcurrentBurstTime() > round(process.getQt() * 0.40)) {
            for (int i = time; i < time + round(process.getQt() * 0.40); i++) {
                addtoQueue(processes, i, pLL, process);
            }
            time += round(process.getQt() * 0.40);
            process.setcurrentBurstTime(process.getcurrentBurstTime() - (time - starttime));
        } else {
            time += process.getcurrentBurstTime();
            time += con;
            end(process, time, dead, pLL);
        }
        return time;

    }

    static int addcontextswitch(int time, Process[] processes, LinkedList<Process> pLL, int contextswitch, Process process) {
        for (int i = 0; i < contextswitch; i++) {
            addtoQueue(processes, time, pLL, process);
            time++;
        }
        return time;
    }

    static void end(Process process, int time, LinkedList<Process> dead, LinkedList<Process> processLinkedList) {
        process.setCompletetime(time);
        process.setwaitingcount(time - process.getArrivalTime() - process.getBurstTime());
        process.setTA(time - process.getArrivalTime());
        process.setQt(0);
        process.setBurstTime(0);
        process.setcurrentBurstTime(0);
        process.setAGAT_Factor(0);
        process.setPriorNumb(0);
        dead.add(process);
        processLinkedList.remove(process);
        System.out.println(process.getProcessName() + " is finished");
    }

    static void Do(Process[] processes, int contextSwitch) {
        LinkedList<Process> queueOfProcesses = new LinkedList<>();
        LinkedList<Process> deadList = new LinkedList<>();
        int time;
        int min = processes[0].getArrivalTime(), workingTime;
        boolean change = false;
        Process curProcess;
        int startTime;
        for (int i = 0; i < processes.length; i++) {
            if (processes[i].getArrivalTime() < min) {
                min = processes[i].getArrivalTime();
            }
        }
        time = min;
        for (int i = 0; i < processes.length; i++) {
            if (processes[i].getArrivalTime() <= time) {
                queueOfProcesses.add(processes[i]);
            }
        }
        curProcess = queueOfProcesses.remove();
        startTime = time;
        time = nonPreemptive(processes, time, curProcess, queueOfProcesses, deadList, contextSwitch);
        workingTime = time - startTime;
        while (true) {
            setFactor(processes, time, curProcess, workingTime);
            addtoQueue(processes, time, queueOfProcesses, curProcess);
            if (deadList.size() == processes.length) break;
            if (curProcess.getcurrentBurstTime() > 0) {
                min = curProcess.getAGAT_Factor();
                for (int i = 0; i < processes.length; i++) {
                    if (processes[i].getAGAT_Factor() < min && processes[i].getAGAT_Factor() != 0) {
                        min = processes[i].getAGAT_Factor();
                        change = true;
                    }
                }
                if (change) {
                    for (int i = 0; i < processes.length; i++) {
                        if (min == processes[i].getAGAT_Factor()) {
                            time = addcontextswitch(time, processes, queueOfProcesses, contextSwitch, curProcess);
                            curProcess.setQt(curProcess.getQt() + (curProcess.getQt() - workingTime));
                            showInfo(processes, time, queueOfProcesses);
                            addtoQueue(processes, time, queueOfProcesses, curProcess);
                            queueOfProcesses.add(curProcess);
                            curProcess = processes[i];
                            queueOfProcesses.remove(processes[i]);
                            startTime = time;
                            time = nonPreemptive(processes, time, curProcess, queueOfProcesses, deadList, contextSwitch);
                            workingTime = time - startTime;
                            change = false;
                            break;
                        }
                    }
                } else if (workingTime == curProcess.getQt()) {
                    curProcess.setQt(curProcess.getQt() + 2);
                    setFactor(processes, time, curProcess, workingTime);
                    addtoQueue(processes, time, queueOfProcesses, curProcess);
                    if (queueOfProcesses.size() > 0)
                        time = addcontextswitch(time, processes, queueOfProcesses, contextSwitch, curProcess);
                    showInfo(processes, time, queueOfProcesses);
                    if (curProcess.getcurrentBurstTime() > 0) {
                        queueOfProcesses.add(curProcess);
                    } else if (curProcess.getQt() != 0) {
                        time += contextSwitch;
                        end(curProcess, time, deadList, queueOfProcesses);
                    }
                    while (queueOfProcesses.isEmpty()) {
                        time++;
                        addtoQueue(processes, time, queueOfProcesses, curProcess);
                        if (deadList.size() == processes.length) break;
                    }
                    curProcess = queueOfProcesses.remove();
                    startTime = time;
                    time = nonPreemptive(processes, time, curProcess, queueOfProcesses, deadList, contextSwitch);
                    workingTime = time - startTime;
                } else {
                    time++;
                    workingTime++;
                    curProcess.setcurrentBurstTime(curProcess.getcurrentBurstTime() - 1);
                    addtoQueue(processes, time, queueOfProcesses, curProcess);
                }
            } else {
                if (curProcess.getQt() != 0) {
                    time += contextSwitch;
                    end(curProcess, time, deadList, queueOfProcesses);
                }
                while (queueOfProcesses.isEmpty()) {
                    time++;
                    addtoQueue(processes, time, queueOfProcesses, curProcess);
                    if (deadList.size() == processes.length) break;
                }
                if (!queueOfProcesses.isEmpty()) {
                    curProcess = queueOfProcesses.remove();
                    setFactor(processes, time, curProcess, workingTime);
                    showInfo(processes, time, queueOfProcesses);
                    startTime = time;
                    time = nonPreemptive(processes, time, curProcess, queueOfProcesses, deadList, contextSwitch);
                    workingTime = time - startTime;
                }
            }


        }
        System.out.println("End of program: " + time);
        double avgWaiting = 0, avgTurnaround = 0;
        System.out.println("PName, PFinishingTime,PWaitingTime, PTurnaroundTime: ");
        for (int i = 0; i < processes.length; i++) {
            avgWaiting += processes[i].getwaitingcount();
            avgTurnaround += processes[i].getTA();
            System.out.println(processes[i].getProcessName() +
                    "        " + processes[i].getcompletetime() +
                    "            " + processes[i].getwaitingcount() +
                    "             " + processes[i].getTA());
        }
        avgWaiting = avgWaiting / processes.length;
        avgTurnaround = avgTurnaround / processes.length;
        System.out.println("The Average Waiting time is: " + avgWaiting);
        System.out.println("The Average Turnaround timeis: " + avgTurnaround);
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int number = input.nextInt();
        Process[] processes = new Process[number];
        Process process;
        String pname, pcoloer;
        int At, Bt, priority, Qt;
        int contextSwitch;
        System.out.println("Enter context switch time: ");
        contextSwitch = input.nextInt();
        System.out.println("Enter process's; name, color, arrival time, burst time, priority ,Quantum time:");
        for (int i = 0; i < processes.length; i++) {
            pname = input.next();
            pcoloer = input.next();
            At = input.nextInt();
            Bt = input.nextInt();
            priority = input.nextInt();
            Qt = input.nextInt();
            process = new Process(pname, pcoloer, At, Bt, priority, Qt);
            process.setcurrentBurstTime(Bt);
            processes[i] = process;
        }
        Do(processes, contextSwitch);

    }
}

