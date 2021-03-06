
package org.usfirst.frc.team6027.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    //Smart Dashboard Crap
	final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
	
    //Create new objects
    RobotDrive merlin; //Create New Robot Drive
	Joystick stick; //Create a new stick
	DoubleSolenoid solOne = new DoubleSolenoid(1, 2);
	boolean buttonValue;
	boolean inverted;
	
    public void robotInit() {
        
    	//Smart Dashboard Crap
    	chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        //Assign objects
    	merlin = new RobotDrive(0,1); //Assign to robodrive on PWM 0 and 1
    	stick = new Joystick(0); //Assign to a joystick on port 0
    
    }
    
    public void autonomousInit() {
    	//Smart Dashboard Crap
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
		
    }

    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:

            break;
    	case defaultAuto:
    	default:

            break;
    	}
    }


    public void teleopPeriodic() {
    	//Vars
    	double stickY = stick.getY() * 0.56;
    	double stickX = stick.getX() * 0.48;
    	
    	//Drivetrain
    	merlin.arcadeDrive(stickY, stickX);
    	
    }
    

    public void testPeriodic() {
    	//Vars
    	double stickY = stick.getY() * 0.56;
    	double stickX = stick.getX() * 0.48;
    	
    	//Compressor
    	Compressor c = new Compressor(0);
    	c.setClosedLoopControl(true);
    	
    	//Drivetrain
    	merlin.arcadeDrive(stickY, stickX);
    	
    	//Piston On Trigger
    	buttonValue = stick.getRawButton(1);
    	if(buttonValue == true){
    		solOne.set(DoubleSolenoid.Value.kForward);
    	}
    	else{
    		solOne.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
}
