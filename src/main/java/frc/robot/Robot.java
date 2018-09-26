package frc.robot;

/*Team coding work to post later, look into code with basic Java FRC Drive Code
Will be added to GitHub, but all edits and comments should be made on here.*/

//Comment like this or like I did above
import java.util.concurrent.TimeUnit;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;


public class Robot extends IterativeRobot 
{
	
	//MAC XBOX
	Spark Lift1 = new Spark(2);
	Spark Lift2 = new Spark(3);
	Spark Climb1 = new Spark(5);
	Spark Climb2 = new Spark(4);
	Spark dR = new Spark(0);
	Spark dL = new Spark(1);
	
	public boolean pneu = false;
	
	DifferentialDrive driveT = new DifferentialDrive(dL, dR);
	SpeedControllerGroup lift = new SpeedControllerGroup(Lift1, Lift2);
	SpeedControllerGroup climb = new SpeedControllerGroup(Climb1, Climb2);
	
	Joystick tempR, tempL;
	XboxController nsc;
	PowerDistributionPanel power;
	boolean z = false;
	
	//AnalogInput encoder = new AnalogInput(1);
	Compressor c;
	DoubleSolenoid fullExt;
	
	Encoder encode = new Encoder(8, 9);
	Encoder encodeUnder = new Encoder(6, 7);
	
	Timer time; 
	double num = -5.0;
	String test;
	
	double speedR;
	double speedL;
	
	public double driveSpeed = .6;
	public String liftDVoltage;
	
	public AnalogInput jEncL = new AnalogInput(0);
	public AnalogInput jEncR = new AnalogInput(1);
	
    public void robotInit() 
    {
    	driveT.setDeadband(0);
    	
    	tempL = new Joystick(1);	 		//The value in Joystick() is the order in which the Joysticks are plugged in. It can be checked on the USB section of the Drive Station
    	tempR = new Joystick(2);
    	nsc = new XboxController(0);
    	
    	power = new PowerDistributionPanel();
    	power.clearStickyFaults();
    	
    	time = new Timer();
    	
    	c = new Compressor(0); //default for PCM
    	c.setClosedLoopControl(true);
    	
    	fullExt = new DoubleSolenoid(0, 1);
    	
    	//SmartDashboard.putNumber("current time", time.getFPGATimestamp());
    	
    	//encode.setDistancePerPulse(1);
    	encode.reset();
    	encode.setReverseDirection(true);
    	
    	//encodeUnder.setDistancePerPulse(1);
    	encode.reset();
		
		speedR=.8;
		speedL=.8;
    	
    	CameraServer.getInstance().startAutomaticCapture();
    	 
    }
    
    
    
    public void autonomousInit()
    {
    	time.reset();
    	time.start();
    	
    }

    /*all our autonomous stuffs
    public void autonomousPeriodic() 
    {
    	
    	
	    {
	    	driveSpeed = .6;
	    	while(time.get() < 5.0)
	    	{
	    		driveT.tankDrive(-driveSpeed, -driveSpeed);
	    	}
	    	
	    }

    
    DriverStation ds = DriverStation.getInstance();
    String gameData = ds.getGameSpecificMessage(); //LEFT DRIVER STATION | Position To The Far Left RED ALI
	
    	
       
          		
		/*if(gameData.charAt(0) == 'L') //If our side of the switch is on the left
	  	{
	     driveT.tankDrive(0.8,.8);
        while(time.get() < 1)
	    {
			climb.set(0.8); //Lifts arm
		}
        while(time.get() < 1.2)
		{
			climb.set(0.0); //Stops lifting arm
        }
		while(time.get() < 2.2)
		{
			driveT.tankDrive(-0.7,-0.7); //Drives Robot forward
		}
		while(time.get() < 3.2)
		{
			driveT.tankDrive(0.6,-0.6); //Turns robot left
		}
		while(time.get() < 4.2)
		{
         driveT.tankDrive(-0.5,-0.5); //Drive forward
		}
		while(time.get() < 4.3)
        {
	     fullExt.set(DoubleSolenoid.Value.kReverse); //Releases cube
        }
        }
		/*If our side of the switch is on the right RED ALI
		if(gameData.charAt(0)== 'R' ) 
        {
        while(time.get() < )
        {
        	driveT.tankDrive(-.8,-.8); //Drives past switch
  	    }
        while(time.get() < )
        {
        	driveT.tankDrive(-0.6,0.6); //Turns to the right
        }
        while(time.get() < )
        {
        	driveT.tankDrive(-.8,-.8); // Drives to the right half of the field
  	    }
        while(time.get() < )
		{
        	driveT.tankDrive(-0.6,0.6); // Turns arm to be facing alliance station
	  	}
		while(time.get() < )
        {
			driveT.tankDrive(-0.6,-0.6); // Drives towards the alliance Station	
        }
        while(time.get() < )
		{
			climb.set(0.8); //Lifts arm
		}
		while(time.get() < ) 
		{
			climb.set(0.0); // Stops lifting arm
		}
        while(time.get() < )
		{
 	    driveT.set(-0.6,-0.6); // Drives forward to the switch
	  	}
        while(time.get() < )
        {  
         pneumat1.set(DoubleSolenoid.Value.kReverse); //Releases cube
        }
        }	


//RIGHT DRIVE STATION
	else if(gameData.charAt(0) == 'R') //If our side of the switch is on the right
	{
		speedR=.6;
		speedL=.6;
	while(time.get() < )
     {
		driveT.tankDrive(-.8 ,-.8 ); //Drive Forward
     }	
    while(time.get() < )	
	{  
    	driveT.tankDrive(0.6,-0.6); //Turns Left
	}
    while(time.get() < )	
	{
    	driveT.tankDrive(-0.6,-0.6);//Drive to the switch
	}
	while(time.get() < )
	{
     pneumat1.set(DoubleSolenoid.Value.kReverse); //Releases cube
    }
	}
	
	//If our side of the switch is on the left
	else if(gameData.charAt(0)=='L')
     {	
     while(time.get() < )
     {	
    	 driveT.tankDrive(-0.8,-0.8); //Drive Forward
     }
     while(time.get() < )
     {
    	 driveT.tankDrive(.6 , -.6); //Turn left
     } 
      while(time.get() < )
     {
    	  driveT.tankDrive(-.8 ,-.8 ); //Drive Forward
     }
      while(time.get() < )
     {
    	  driveT.tankDrive(.6 , -.6); //Turn facing drive station
     }
     while(time.get() < )
      {
    	 driveT.tankDrive(-.6 ,-.6 ); //Drive Forward
      }
     while(time.get() < )
      {
    	 driveT.tankDrive(.6 , -.6); //Turn facing Switch}
      }
     while(time.get() < )
	 {
		climb.set(0.8); //Lifts arm
	 }
	while(time.get() < )
	{
		climb.set(0.0); //Stops lifting arm
	}
    while(time.get() < )
    {
    	driveT.tankDrive(-.6 ,-.6 ); //Drive Forward
    }
    while(time.get() < )
    {
    pneumat1.set(DoubleSolenoid.Value.kReverse); //Releases Cube
    }
    }


//George Washington needs to stop the process of eating my CPU*/
	
//CENTER DRIVE STATION
         
	  /*if(gameData.charAt(0) == 'L') //If our side of the switch is on the left from center
     {
		speedL = .6;
		speedR= .6;
	 while(time.get() < )
	 {
		climb.set(0.8); //Lifts arm
     }
	while(time.get() < )
     {
		climb.set(0.0); //Stops lifting arm
     }
	while(time.get() < )
	 {
		driveT.tankDrive(-0.7,-0.7); //Drives Robot forward
	 }
	while(time.get() < )
	 {
		driveT.tankDrive(0.6,-0.6); //Turns robot to the switch
	 }
    while(time.get() < )			
     {                            
    	driveT.tankDrive(-0.7,-0.7); //Drives Robot forward
	 }
    while(time.get() < )		
     {
    	driveT.tankDrive(-0.6,0.6); //Turn robot to the switch
	 }
    while(time.get() < )   
     {                
    	driveT.tankDrive(-0.7,-0.7); //Drive robot forward
     }
    while(time.get() < )		
     { 
	  pneumat1.set(DoubleSolenoid.Value.kReverse); //Releases cube
     }
     }
		
//If our side of the switch is on the right
	else if(gameData.charAt(0)=='R')
	{
     driveT.setMaxOutput(0.8);
	while(time.get() < )
	{
		climb.set(0.8); //Lifts arm
	}
	while(time.get() < )
	{
		climb.set(0.0); //Stops lifting arm
    }
	while(time.get() < )
	{
		driveT.tankDrive(-0.7,-0.7); //Drives Robot forward
	}
	while(time.get() < )
	{
		driveT.tankDrive(-0.6,0.6); //Turns robot to the switch
	}
    while(time.get() < )		
    {                            
    	driveT.tankDrive(-0.7,-0.7); //Drives Robot forward
	}
    while(time.get() < )		
    {
    	driveT.tankDrive(0.6,-0.6); //Turn robot to the switch
    }
    while(time.get() < )
    {                
    	driveT.tankDrive(-0.7,-0.7); //Drive robot forward
	}
    while(time.get() < )		
    { 
		pneumat1.set(DoubleSolenoid.Value.kReverse); //Releases cube
    }
    }
    }*/

	
	
	//public void teleopInit()
	/*{
	    double lastInput = 0.0;
	    
		time.reset();
	    time.start();
	}
	
	String REnc, LEnc, encodeStr, compressorState, encodeDist, encodeDist2, leftY, rightY;*/
	
    public void teleopPeriodic() 
    {
    	
    	
    	
    	
    	SmartDashboard.putData("Encoder", encode);
    	//System.out.println(encode.getDistance());
    	
    	//REnc = jEncR.getVoltage() + "";
    	//LEnc = jEncL.getVoltage() + "";
    	//SmartDashboard.putString("Left pneumatic", LEnc);
    	//SmartDashboard.putString("Right pneumatic", REnc);
    	
    	
    	
    	/* Joystick Tank Drive
    	leftY = "" + tempL.getY();
    	SmartDashboard.putString("Left Y", leftY);
    	
    	rightY = "" + tempR.getY();
    	SmartDashboard.putString("Rigth Y", rightY);
    	
    	System.out.println("Left Encoder"+jEncL.getVoltage());
    	System.out.println("Right Encoder" + jEncR.getVoltage());
    	*/
    	
    	
    		//System.out.println(encode.getDistance());
    	
    /*	if((tempL.getY() > .1 && tempR.getY() < -.1) || (tempL.getY() < -.1 && tempR.getY() > .1))
    	{
    		driveT.tankDrive(tempR.getY(), tempL.getY());
    	}
    	else
    	{
    		driveT.tankDrive(tempL.getY(), tempR.getY());*/
    	
    		//Xbox
    		
    		/*if(nsc.getRawAxis(5) > .1) {
				driveT.tankDrive(driveSpeed, driveSpeed);
			}
			else if(nsc.getRawAxis(5) < -.1) {
				driveT.tankDrive(-driveSpeed, -driveSpeed);
			}
			if(nsc.getRawAxis(1) > .1) {
    			driveT.tankDrive(driveSpeed, -driveSpeed);
    		}
    		else if(nsc.getRawAxis(1) < -.1) {
    			driveT.tankDrive(-driveSpeed, driveSpeed);
    		} */
			
			   
			    	if((nsc.getRawAxis(1) > .1 && nsc.getRawAxis(5) < -.1) || (nsc.getRawAxis(1) < -.1 && nsc.getRawAxis(5) > .1))
			    	{
			    		driveT.tankDrive(nsc.getRawAxis(5), nsc.getRawAxis(1));
			    	}
			    	else
			    	{
			    		driveT.tankDrive(nsc.getRawAxis(1), nsc.getRawAxis(5));
			    	}
			
			
			
			
			
    		
    		if(nsc.getRawButton(4))
    		{
    			driveT.setMaxOutput(1);
    		}
    		else if(nsc.getRawButton(3))
    		{
    			driveT.setMaxOutput(.8);
    		}
    		else if(nsc.getRawButton(2))
    		{
    			driveT.setMaxOutput(.6);
    		}
    	
    			//arm
    		//if(tempR.getRawButton(3) == true)//down
    		//{
    			//lift.set(1);
    			//climb.set(.7);
    		//}


    			//SmartDashboard.putData("Encoder", encode);
    		
    		
    		if(tempR.getRawButton(2) == true)//up/
    		{
    			
    			lift.set(-.5);
    			//climb.set(-.7);
    			
    		}
    		else if(tempR.getRawButton(3) == false && tempR.getRawButton(2) == false)// Stop when let go
    		{
    				lift.set(0);
        			climb.set(0);
    		}
    			
    			//SmartDashboard.putData("Encoder", encode);
    		
    		/*else if(tempR.getRawButton(2) == true)//down/climbing
    		{
    			lift.set(0);
    			climb.set(0);*\
    			//SmartDashboard.putData("Encoder", encode);
    		}
    		/*else if(tempR.getRawButton(4) == true)
    		{
    			lift.set(0);
    			climb.set(0);
    		}
    		else if(tempR.getRawButton(5) == true)
    		{
    			lift.set(.5);
    			//climb.set(0);
    		}
    		else if(tempR.getRawButton(2) == true)
    		{
    			lift.set(-.5);
    			//climb.set(0);
    		}
    		else if(tempL.getRawButton(4) == true)
    		{
    			//lift.set(-.5);
    			climb.set(.5);
    		}*/
    		if(tempR.getRawButtonPressed(1) == true)//was 4 now open
    		{
    			pneu = !pneu;
    		}
    		
    		if(pneu)
    		{
    			fullExt.set(DoubleSolenoid.Value.kReverse);
    		}
    		else
    		{
    			fullExt.set(DoubleSolenoid.Value.kForward);
    		}
    		
    		//switch height 
    		//max height 2145
    		
    		/*if(c.getPressureSwitchValue())
    		{
    			compressorState = "Low Pressure";
    		}
    		else
    		{
    			compressorState = "Pressure ok";
    		}
    		//SmartDashboard.putString("Pressure State: ", compressorState);
    		
    		test = "" + tempL.getY();
    		//SmartDashboard.putString("Y-axis value: ", test);
    		
    		//SmartDashboard.putString("X-axis Value", ""+tempR.getX());
    		encodeDist = "" + encode.getDistance();
    		//liftDVoltage = "" + (5*(liftD.getVoltage()/.004883))/1000; */
    		
    		//SmartDashboard.putString("Lift Height (meters): ", liftDVoltage);
    		//SmartDashboard.putString("Encoder Output: ", );
    		//SmartDashboard.putString("Encoder 1 (back)", encodeDist);
    		
    		//encodeDist2 = "" + encodeUnder.getDistance();
    		//SmartDashboard.putString("Encoder 2 (under)", encodeDist2);
    		
    		//System.out.println("encode1\t" + encode.getRaw());
    		//System.out.println("encode2\t" + encodeUnder.getRaw());
    		/*(if(tempL.getRawButton(1))
    		{
    	//		lift.set(-1.0);
    		}
    		if(tempL.getRawButton(3))
    		{
    		//	lift.set(0.0);
    		}
    		else if(tempL.getRawButton(8))
    		{
    		//	lift.set(-0.6);
    		}
    		if(tempR.getRawButton(1))
    		{
    			myRobot.setMaxOutput(0.5);
    		}
    		else if(tempR.getRawButton(2))
    		{
    			myRobot.setMaxOutput(0.7);
    		}
    		else if(tempR.getRawButton(7))
    		{
    			myRobot.setMaxOutput(0.9);
    		}*/
    		/*if(tempL.getRawButton(1))
    		{
    			//fullExt.set(DoubleSolenoid.Value.kForward);
    			try
    			{
    				Thread.sleep(200);
    				fullExt.set(DoubleSolenoid.Value.kForward);
    				midStop.set(DoubleSolenoid.Value.kForward);//open loop
    				Thread.sleep(40);
    				fullExt.set(DoubleSolenoid.Value.kOff);
        			midStop.set(DoubleSolenoid.Value.kReverse);
    				
    			}
    			catch(Exception InterruptedException)
    			{
    				
    			}
    			//open loop
    			//midStop.set(DoubleSolenoid.Value.kOff);
    		}
    		if(tempL.getRawButton(2)) 
    		{
    			//fullExt.set(DoubleSolenoid.Value.kReverse);
    			midStop.set(DoubleSolenoid.Value.kReverse);//close loop
    			
    			//midStop.set(DoubleSolenoid.Value.kOff);
    		}
    		if(tempL.getRawButton(4))
    		{
    			fullExt.set(DoubleSolenoid.Value.kReverse);
    			midStop.set(DoubleSolenoid.Value.kForward);
    			//midStop.set(DoubleSolenoid.Value.kOff);
    		}
    		if(tempL.getRawButton(5))
    		{
    			midStop.set(DoubleSolenoid.Value.kOff);
    		}
    		if(tempR.getRawButton(3))
    		{
    			c.start();
    		}
    		if(tempR.getRawButton(5))
    		{
    			c.stop();
    		}
    		if(tempR.getRawButton(1))
    		{
    			try
    			{
    				Thread.sleep(200);
    			}
    			catch(Exception InterruptedException)
    			{
    				
    			}
    			fullExt.set(DoubleSolenoid.Value.kForward);
    			if(c.getPressureSwitchValue())
    			{
    				try
        			{
        				midStop.set(DoubleSolenoid.Value.kForward);//open loop
        				Thread.sleep(25);
        				fullExt.set(DoubleSolenoid.Value.kOff);
            			midStop.set(DoubleSolenoid.Value.kReverse);
        			}
        			catch(Exception InterruptedException)
        			{
        				
        			}
    			}
    			else
    			{
	    			while(!(liftD.getVoltage() > 2))
	    			{
	    		     	
	        			try
	        			{
	        				midStop.set(DoubleSolenoid.Value.kForward);//open loop
	        				Thread.sleep(50);
	        				fullExt.set(DoubleSolenoid.Value.kOff);
	            			midStop.set(DoubleSolenoid.Value.kReverse);
	            			Thread.sleep(100);
	            			if(tempR.getRawButton(4))
	            			{
	            				break;
	            			}
	        			}
	        			catch(Exception InterruptedException)
	        			{
	        				
	        			}
	        			
	    			}
    			}
    			//fullExt.set(DoubleSolenoid.Value.kForward);
    			//midStop.set(DoubleSolenoid.Value.kForward);//open loop
    			/*try
    			{
    				Thread.sleep(25);
    			}
    			catch(Exception InterruptedException)
    			{
    				
    			}
    			fullExt.set(DoubleSolenoid.Value.kForward);
    			//fullExt.set(DoubleSolenoid.Value.kOff);
    			midStop.set(DoubleSolenoid.Value.kReverse);//close loop
    			//midStop.set(DoubleSolenoid.Value.kOff);
    			
    			
    			//pneumat1.set(DoubleSolenoid.Value.kOff);
    		}
    		if(tempR.getRawButton(2))
    		{
    			try
    			{
    				Thread.sleep(200);
    			}
    			catch(Exception InterruptedException)
    			{
    				
    			}
    			
    			fullExt.set(DoubleSolenoid.Value.kReverse);
    			midStop.set(DoubleSolenoid.Value.kForward);//open loop
    			try
    			{
    				Thread.sleep(25);
    			}
    			catch(Exception InterruptedException)
    			{
    				
    			}
    			//fullExt.set(DoubleSolenoid.Value.kForward);
    			//fullExt.set(DoubleSolenoid.Value.kOff);
    			midStop.set(DoubleSolenoid.Value.kReverse);//close loop
    			//midStop.set(DoubleSolenoid.Value.kOff);
    		}
    		else if(tempL.getRawButton(3))
    		{
    			 fullExt.set(DoubleSolenoid.Value.kOff);
    		}
    		
    		

    			
    		
    		//if(liftD.get)
    		//{
    			
    		//}*/
    	
    		
   }
}