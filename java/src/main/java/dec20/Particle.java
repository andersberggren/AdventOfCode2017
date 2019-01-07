package dec20;

public class Particle implements Comparable<Particle> {
	public final Point3D position;
	private final Point3D velocity;
	private final Point3D acceleration;
	
	public Particle(String input) {
		// input = "p=<x,y,z>, v=<x,y,z>, a=<x,y,z>"
		String[] pva = input.split("[ \t]+");
		position     = new Point3D(pva[0]);
		velocity     = new Point3D(pva[1]);
		acceleration = new Point3D(pva[2]);
	}
	
	public void update() {
		velocity.add(acceleration);
		position.add(velocity);
	}

	public boolean isPositionVelocityAccelerationInSameDirection() {
		for (int i = 0; i < Point3D.DIMENSIONS; i++) {
			int signum = Integer.signum(position.coords[i]);
			int velSignum = Integer.signum(velocity.coords[i]);
			if (signum == 0) {
				signum = velSignum;
			} else if (velSignum != 0 && signum != velSignum) {
				return false;
			}
			int accSignum = Integer.signum(acceleration.coords[i]);
			if (signum == 0) {
				signum = accSignum;
			} else if (accSignum != 0 && signum != accSignum) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int compareTo(Particle o) {
		Point3D[] points      = new Point3D[]{acceleration,   velocity,   position};
		Point3D[] otherPoints = new Point3D[]{o.acceleration, o.velocity, o.position};
		for (int i = 0; i < points.length; i++) {
			int compare = points[i].getAbsoluteValue() - otherPoints[i].getAbsoluteValue();
			if (compare != 0) {
				return compare;
			}
		}
		return 0;
	}
}
