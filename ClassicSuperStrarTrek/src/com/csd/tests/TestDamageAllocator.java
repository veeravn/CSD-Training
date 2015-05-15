import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestDamageAllocator {

	private DamageAllocator damageAllocator;
	private ArrayList<SubSystem> subsystems;
	private Shields shields;
	
	@Before
	public void initializeTest() {
		subsystems = new ArrayList<SubSystem>();
		subsystems.add(new SubSystem(SubSystem.subsystem_type.WARP_ENGINES));
		subsystems.add(new SubSystem(SubSystem.subsystem_type.PHASERS));
		subsystems.add(new SubSystem(SubSystem.subsystem_type.PHOTON_TORPEDOES));
		
		shields = new Shields();
		damageAllocator = new DamageAllocator();
	}
	
	@Test
	public void testTakeHitWithShieldsHolding() {
		int hitEnergy = Shields.DEFAULT_SHIELD_LEVEL;
		shields.raiseShields();
		damageAllocator.takeHit(subsystems, shields, hitEnergy);
		for (SubSystem subsystem : subsystems) {
			assertTrue(!subsystem.isDamaged());
		}
	}
	
	@Test
	// Note that this test does not verify the quality of the randomness
	// of the selection of the subsystem damage.
	public void testTakeHitWithShieldsBuckling() {
		int hitEnergy = Shields.DEFAULT_SHIELD_LEVEL + 1;
		shields.raiseShields();
		damageAllocator.takeHit(subsystems, shields, hitEnergy);
		int countOfDamagedSubsystems = 0;
		for (SubSystem subsystem : subsystems) {
			if (subsystem.isDamaged()) {
				countOfDamagedSubsystems++;
			}
		}
		
		assertEquals(1, countOfDamagedSubsystems);	
	}
}
