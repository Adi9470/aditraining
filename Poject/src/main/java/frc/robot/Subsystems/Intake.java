package frc.robot.Subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.estimator.AngleStatistics;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.DashBoardTab;
import frc.robot.MALog;


public class Intake extends SubsystemBase {
    private TalonFX motor16;
    private TalonFX motor18;

    private final StatusSignal<Voltage> voltageSignal;
    private final StatusSignal<Angle> positionSignal;
    private final StatusSignal<Voltage> voltageSignal1;
    private final StatusSignal<Angle> positionSignal1;


    private TalonFXConfiguration motor16Config;
    private TalonFXConfiguration motor18Config;

    private DashBoardTab intakeTab = new DashBoardTab("intake");

    public Intake() {
        motor16 = new TalonFX(16);
        motor16Config = new TalonFXConfiguration();
        motor18 = new TalonFX(18);
        motor18Config = new TalonFXConfiguration();
      

        voltageSignal = motor16.getMotorVoltage();
        positionSignal = motor16.getPosition();
        voltageSignal1 = motor18.getMotorVoltage();
        positionSignal1 = motor18.getPosition();

     
        motor16Config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        motor18Config.MotorOutput.NeutralMode = NeutralModeValue.Coast;

        motor16Config.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
        motor18Config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        
        
        motor16Config.Feedback.SensorToMechanismRatio = 5.0;
        motor18Config.Feedback.SensorToMechanismRatio = 5.0;

        motor16.getConfigurator().apply(motor16Config);
        motor18.getConfigurator().apply(motor18Config);

    
        motor16.getConfigurator().apply(motor16Config);
        motor18.getConfigurator().apply(motor18Config);
    }
    

    @Override
    public void periodic() {
        

        intakeTab.addBoolean("test2", false);
        intakeTab.addNum("test1", 2);

        intakeTab.getNum("test1");

        MALog.log( "/Subsystems/Intake/AverageRPM", getAverageRPM());
        MALog.log( "/Subsystems/Intake/Voltage16", getMotorVoltage16());
        MALog.log( "/Subsystems/Intake/Voltage18", getMotorVoltage18());
        MALog.log( "/Subsystems/Intake/Current16", getMotorCurrent16());
        MALog.log( "/Subsystems/Intake/Current18", getMotorCurrent18());
      
    }


    public void setVoltage(double voltage) {
        motor16.setVoltage(voltage);
        motor18.setVoltage(voltage);
    }

   
    public void stop() {
        motor16.set(0);
        motor18.set(0);
    }


    public double getAverageRPM() {
        double rpm16 = motor16.getVelocity().getValueAsDouble() * 60;
        double rpm18 = motor18.getVelocity().getValueAsDouble() * 60;
        return (rpm16 + rpm18) / 2.0;
    }

    
    public double getMotorVoltage16() {
        return motor16.getMotorVoltage().getValueAsDouble();
    }

    
    public double getMotorVoltage18() {
        return motor18.getMotorVoltage().getValueAsDouble();
    }

 
    public double getMotorCurrent16() {
        return motor16.getStatorCurrent().getValueAsDouble();
    }

  
    public double getMotorCurrent18() {
        return motor18.getStatorCurrent().getValueAsDouble();
    }
}