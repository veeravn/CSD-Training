import java.util.ArrayList;
import java.util.Random;

public class DamageAllocator {

	public void takeHit(ArrayList<SubSystem> subsystems, Shields shields, int hitEnergy) {
		int remainingEnergy = shields.hit(hitEnergy);
		
		if (remainingEnergy <= 0)
			return; // hit absorbed by shields

		SubSystem subSystem = chooseRandomSubsystem(subsystems);
		
		damageRandomSubsystem(subSystem, hitEnergy);	
	}
	
	private SubSystem chooseRandomSubsystem(ArrayList<SubSystem> subsystems) {
		Random rand = new Random();
		int randIndex = rand.nextInt(subsystems.size());
		SubSystem subsystem = subsystems.get(randIndex);
		return subsystem;
	}
	
	private void damageRandomSubsystem(SubSystem subsystem, int energy) {
		subsystem.applyDamage(energy);
	}
	
}
