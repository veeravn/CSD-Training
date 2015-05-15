
public class Shields {
	
	private boolean raised = false;
	
	
	public static final int DEFAULT_SHIELD_LEVEL = 5000;
	public static final int MAX_SHIELD_LEVEL = 10000;
	public static final int MIN_SHIELD_LEVEL = 0;
	
	private int shieldEnergyLevel = DEFAULT_SHIELD_LEVEL;
	
	public boolean isRaised() {
		return raised;
	}
	
	public void raiseShields() {
		raised = true;
	}
	
	public void dropShields() {
		raised = false;
	}
	
	
	public int getShieldEnergyLevel() {
		return shieldEnergyLevel;
	}
	
	// positive values increase shield levels, negative values reduce them
	public void changeShieldEnergyLevelBy(int energyUnitDelta) {
		
		shieldEnergyLevel = shieldEnergyLevel + energyUnitDelta;
		
		if (shieldEnergyLevel < MIN_SHIELD_LEVEL) {
			shieldEnergyLevel = MIN_SHIELD_LEVEL;
			return;
		}
		
		if (shieldEnergyLevel > MAX_SHIELD_LEVEL) {
			shieldEnergyLevel = MAX_SHIELD_LEVEL;
			return;
		}
	}
	/**
	 * Calculate the amount of energy that is absorbed and the amount left over.
	 * @param hitEnergy the amount of energy hit
	 * @return the amount of energy not absorbed.
	 */
	public int hit(int hitEnergy) {
		int energyNotAbsorbed = 0;
		if(isRaised() == false) {
			energyNotAbsorbed = hitEnergy;
		} else {
			if(shieldEnergyLevel < hitEnergy) {
				energyNotAbsorbed = hitEnergy - shieldEnergyLevel;
			}
			changeShieldEnergyLevelBy(-hitEnergy);
		}
		return energyNotAbsorbed;
	}
}
