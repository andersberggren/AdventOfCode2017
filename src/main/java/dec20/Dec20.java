package dec20;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dec20 {
	public static int findLongTermClosestParticle(String[] input) {
		Map<Particle, Integer> particleIndices = new HashMap<Particle, Integer>();
		// Create particles
		Particle[] particles = new Particle[input.length];
		for (int i = 0; i < input.length; i++) {
			particles[i] = new Particle(input[i]);
			particleIndices.put(particles[i], (Integer) i);
		}
		
		// Update until each particles position/velocity/acceleration is in the same direction
		boolean posVelAccInSameDirection = true;
		do {
			posVelAccInSameDirection = true;
			for (Particle particle : particles) {
				particle.update();
			}
			
			for (Particle particle : particles) {
				if (!particle.isPositionVelocityAccelerationInSameDirection()) {
					posVelAccInSameDirection = false;
					break;
				}
			}
		} while (!posVelAccInSameDirection);
		
		Arrays.sort(particles, null);
		return particleIndices.get(particles[0]);
	}
}
