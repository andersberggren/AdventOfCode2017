package dec20;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dec20 {
	public static void findLongTermClosestParticle(String[] input) {
		Map<Particle, Integer> particleIndices = new HashMap<Particle, Integer>();
		Set<Particle> collidedParticles = new HashSet<Particle>();
		
		// Create particles
		List<Particle> particles = new LinkedList<Particle>();
		for (int i = 0; i < input.length; i++) {
			Particle p = new Particle(input[i]);
			particles.add(p);
			particleIndices.put(p, (Integer) i);
		}
		
		// Update until each particles position/velocity/acceleration is in the same direction
		boolean posVelAccInSameDirection = true;
		do {
			posVelAccInSameDirection = true;
			
			// Update particles
			for (Particle particle : particles) {
				particle.update();
			}
			
			// Check for collisions
			Set<Point3D> allPositions = new HashSet<Point3D>();
			Set<Point3D> collisionPositions = new HashSet<Point3D>();
			for (Particle particle : particles) {
				if (!allPositions.add(particle.position)) {
					collisionPositions.add(particle.position);
				}
			}
			
			// Remove colliding particles
			for (Particle particle : new LinkedList<Particle>(particles)) {
				if (collisionPositions.contains(particle.position)) {
					collidedParticles.add(particle);
				}
			}
			
			for (Particle particle : particles) {
				if (!particle.isPositionVelocityAccelerationInSameDirection()) {
					posVelAccInSameDirection = false;
					break;
				}
			}
		} while (!posVelAccInSameDirection);

		Collections.sort(particles);
		int indexOfClosestParticle = particleIndices.get(particles.get(0));
		System.out.println("Dec 20 (1): " + indexOfClosestParticle);
		
		// TODO We can't be sure that all collisions have occurred at this point.
		//      The exit condition for the while loop needs to be changed.
		int particlesLeft = particles.size() - collidedParticles.size();
		System.out.println("Dec 20 (2): " + particlesLeft);
	}
}
