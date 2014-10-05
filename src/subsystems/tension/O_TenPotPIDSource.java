/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsystems.tension;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author afiol-mahon
 */
public class O_TenPotPIDSource implements PIDSource{

    double initValue;
    AnalogChannel pot;
    public O_TenPotPIDSource(double initValue, int AnalogInChannel){
    this.initValue = initValue;
    this.pot = new AnalogChannel(AnalogInChannel);
    }
    public double pidGet() {
        return initValue-pot.getVoltage();
    }
    
}
