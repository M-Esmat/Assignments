import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.BorderFactory;
 class Process {
	private String Pname;
	private String Pcolor;
	private int At; //process arrival time
	private int Bt; //process burst time
	private int PriorNumber;//process priority number
	boolean complete;
	
	public Process() {
		complete=false;
	}
	
	public Process(String Pname, int At, int Bt,String Pcolor) {
		this.Pname=Pname;
		//this.Pcolor=Pcolor;
		this.At=At;
		this.Bt=Bt;
		this.PriorNumber=0;
		this.Pcolor=Pcolor;
		complete=false;
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
	}

	
 class GraphPanel extends JPanel{
	int PanelWidth;
	int PanelLength;
	int scaleUnit=30;
	Process[] p;
	int processID[];
	int numbOfProcess;
	int startX[];
	
	GraphPanel(Process[]p,int[] processID,int[]X,int Pnumber,int lenght,int width){
		
		int totalBurstTime=0;
		for(int i=0;i<Pnumber;i++) {
			totalBurstTime+=p[i].getBurstTime();
		}
		PanelWidth=width;
		PanelLength=lenght;
		
		this.numbOfProcess=Pnumber;
		this.p=p;
		this.startX=X; //array of start time of each process
		this.processID=processID;
		this.setLayout(null);
	}
	public int getWidth() {
		return PanelWidth;
	}
	public int getLength() {
		return PanelLength;
	}
	
	@Override
	public void paint(Graphics g) {
		int RectLength1[]=new int[numbOfProcess];
		//Rectlenght=Process bursttime;
		for(int i=0;i<numbOfProcess;i++) {
			RectLength1[i]=p[processID[i]].getBurstTime()*scaleUnit;
		}
		
		int Rectheight=10;
		int RectX=50;
		int RectY=125;
		String color;
		
		
		
        
		Graphics2D g2D=(Graphics2D)g;
	
		for(int i=0;i<=PanelWidth;i+=scaleUnit) {
			 //horizontal lines
		
			g2D.drawLine(0,i,PanelWidth+scaleUnit*10,i);
		}
		g2D.drawLine(0,PanelWidth-1,PanelLength,PanelWidth-1);
		
		for(int i=0;i<PanelWidth;i+=scaleUnit) {
			g2D.drawLine(i,0,i,(PanelLength));//vertical lines
		
		}
		//g2D.drawLine(PanelLength-121,0,PanelLength-121,(PanelWidth));
		
		//g2D.fillRect(RectX,RectY,RectLength,Rectheight);
		for(int i=0;i<numbOfProcess;i++) {
			color=p[processID[i]].getProcessColor();
			color=color.toUpperCase();
			Color c=null;
			try {
				c = (Color)Color.class.getField(color).get(null);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g2D.setColor(c);
			g2D.fillRect(startX[i]*scaleUnit, (i+1)*scaleUnit-5 , RectLength1[i] , Rectheight);
		}
	}
	
}
		
		


 class ProcInfoPanel extends JPanel {
	int n;
	Process[]p;
	int length,width;
	ProcInfoPanel(Process[]p, int numbOfProc,int length, int width){
		this.n=numbOfProc;
		this.p=p;
		
		this.length=length;
		this.width=width;
		this.setLayout(null);
		AddLabels(p,n);
		
		
	}
	public void AddLabels(Process[]p, int n) {
		
		
		
		JLabel Pname=new JLabel();
		Pname.setText("NAME");
		Pname.setBounds(70,20,50,50);
		Pname.setForeground(Color.red);
		
		
		
		JLabel Pcolor=new JLabel();
		Pcolor.setText("COLOR");
		Pcolor.setBounds(length-150,20,50,50);
		Pcolor.setForeground(Color.red);
		
		JLabel PId[]=new JLabel[n];
		for(int i=0;i<n;i++) {
			PId[i]=new JLabel();
			PId[i].setText(p[i].getProcessName());
			PId[i].setBounds(70,(i+1)*20+30,20,20);
			
		}
		
		JLabel color[]=new JLabel[n];
		String col;
		Color c=null;
		for(int i=0;i<n;i++) {
			color[i]=new JLabel();
			col=p[i].getProcessColor();
			col=col.toUpperCase();
			try {
				c = (Color)Color.class.getField(col).get(null);
			} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			color[i].setForeground(c);
			color[i].setText(p[i].getProcessColor());
			color[i].setBounds(length-140,(i+1)*20+30,40,20);
			
		}
		for(int i=0;i<n;i++) {
			this.add(PId[i]);
			this.add(color[i]);
		}
		
		this.add(Pname);
		
		this.add(Pcolor);
	}
	
	
	
	
}
 class MyFrame extends JFrame {
	int FrameWidth;
	int FrameLength;
	int scaleUnit=30;
	Process[] p;
	int processID[];
	int numbOfProcess;
	int startX[];
	MyFrame(Process[]p,int[] processID,int[]X,int Pnumber){
		int totalBurstTime=0,Pwidth,Plength;
		
		for(int i=0;i<Pnumber;i++) {
			totalBurstTime+=p[i].getBurstTime();
		}
		System.out.println("tot busttime= "+totalBurstTime);
		FrameWidth=(Pnumber+5)*scaleUnit+300;
		FrameLength=(totalBurstTime+X[0])*scaleUnit+300;
		
		int GPLength=(totalBurstTime+X[0])*scaleUnit;
		int GPWidth=(Pnumber+5)*scaleUnit;
		GraphPanel panel=new GraphPanel(p,processID,X,Pnumber,GPLength,GPWidth);
		panel.setBorder(LineBorder.createBlackLineBorder());
		Pwidth=FrameWidth-panel.PanelWidth-20;
		Plength=FrameLength-50;
		ProcInfoPanel P_info=new ProcInfoPanel(p,Pnumber,Plength,Pwidth);
		
		
		
		
		panel.setBounds(50,0,FrameLength-50,panel.PanelWidth); //positionning panel
		P_info.setBounds(0,panel.PanelWidth+50,FrameLength-50,FrameWidth-panel.PanelWidth-20); //positionning P_info
		
		JLabel label[]=new JLabel[Pnumber];
		for(int i=0;i<Pnumber;i++) {
			label[i]=new JLabel();
			label[i].setText(p[processID[i]].getProcessName());
			label[i].setBounds(scaleUnit/2,(i+1)*scaleUnit-10,20,20);
			
		}
		JScrollPane scrollpane=new JScrollPane(panel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		
		
		this.numbOfProcess=Pnumber;
		this.p=p;
		this.startX=X; 
		this.processID=processID;
		this.setVisible(true);
		this.setSize(FrameLength,FrameWidth);
		this.setTitle("NonPreemptive - Shortest Job First");
		this.setLayout(null);
		//this.setUndecorated(true);
		this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.add(leftP);
		
		this.add(panel);
		this.add(scrollpane);
		this.add(P_info);
		for(int i=0;i<Pnumber;i++) {
			this.add(label[i]);
		}
		
	}
	}

	public class SJF {
	
		public static void main(String[] args) throws NumberFormatException, IOException {
			// TODO Auto-generated method stub
		
			int n,arrivalT,burstTime;
			String Pname,color;
			BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter number of processes: ");
			
			n=Integer.parseInt(sc.readLine());
			Process P[]=new Process[n];
			//initialize processes
			for(int i=0;i<n;i++) {
				System.out.println("Enter name of process["+(i+1)+"]: ");
				Pname=sc.readLine();
				System.out.println("Enter arrival time of process["+(i+1)+"]: ");
				arrivalT=Integer.parseInt(sc.readLine());
				System.out.println("Enter burst time of process["+(i+1)+"]: ");
				burstTime=Integer.parseInt(sc.readLine());
				System.out.println("Enter COLOR of process["+(i+1)+"]: ");
				color=sc.readLine();
				P[i]=new Process(Pname, arrivalT, burstTime,color);
				
			}
			
			int startTime=0, TotalComplete=0;
			int completionTime[] = new int[n];
			int turnArTime[] = new int[n];
			int waitTime[] = new int[n];
			int processIDinOrder[]=new int[n];
			int AvergWT=0,AvergTurnT=0;
			while(true)
			{
				int c=n, min = 999999;
	
				if (TotalComplete == n)
					break;
				
				//get uncompleted process with minimum burst time
				for (int i=0; i<n; i++)
				{
	
					if ((P[i].getArrivalTime() <= startTime) && (!P[i].complete) && (P[i].getBurstTime()<min))
					{
						min=P[i].getBurstTime();
						c=i;
					}
				}
				
				if (c==n) {
					startTime++;}
				else
				{
					completionTime[c]=startTime+P[c].getBurstTime();
					startTime+=P[c].getBurstTime();
					System.out.println("StartTime= "+startTime);
					System.out.println("completionTime= "+completionTime[c]);
					turnArTime[c]=completionTime[c]-P[c].getArrivalTime();
					waitTime[c]=turnArTime[c]-P[c].getBurstTime();
					P[c].complete=true;
					processIDinOrder[TotalComplete] = c;
					TotalComplete++;
				}
				
			
			}
			
			
			System.out.println("\npName  arrival brust  complete turn waiting");
		 
		 for(int i=0;i<n;i++) {
			 AvergWT+= waitTime[i];
			 AvergTurnT+= turnArTime[i];
			 System.out.println(P[processIDinOrder[i]].getProcessName()+"\t"+P[processIDinOrder[i]].getArrivalTime()+"\t"+P[processIDinOrder[i]].getBurstTime()+"\t"+
					 completionTime[processIDinOrder[i]]+"\t"+turnArTime[processIDinOrder[i]]+"\t"+waitTime[processIDinOrder[i]]);
		 }  
		 
		 System.out.println ("\naverage tat is "+ (float)(AvergTurnT/n));
		 System.out.println ("average wt is "+ (float)(AvergWT/n));
			 int X[]=new int[n]; //X=completion time-bursttime
			 for(int i=0;i<n;i++) {
				 X[i]=completionTime[processIDinOrder[i]]-P[processIDinOrder[i]].getBurstTime();
			 }
			 MyFrame f=new MyFrame(P,processIDinOrder,X,n);
		 sc.close();
	}}
	