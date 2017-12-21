package dec20;

public class Point3D {
	public static final int DIMENSIONS = 3;
	
	public int[] coords = new int[DIMENSIONS];
	
	public Point3D(String input) {
		String[] coordsStr = input.replaceAll(".*<", "").replaceAll(">.*", "").split(",");
		for (int i = 0; i < DIMENSIONS; i++) {
			coords[i] = Integer.parseInt(coordsStr[i]);
		}
	}
	
	public void add(Point3D point) {
		for (int i = 0; i < DIMENSIONS; i++) {
			coords[i] += point.coords[i];
		}
	}
	
	public int getAbsoluteValue() {
		int abs = 0;
		for (int i = 0; i < DIMENSIONS; i++) {
			abs += Math.abs(coords[i]);
		}
		return abs;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Point3D) {
			Point3D p = (Point3D) o;
			for (int i = 0; i < DIMENSIONS; i++) {
				if (coords[i] != p.coords[i]) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int hashCode = 0;
		for (int i = 0; i < DIMENSIONS; i++) {
			hashCode *= 1000;
			hashCode += coords[i];
		}
		return hashCode;
	}
	
	@Override
	public String toString() {
		String s = this.getClass().getName() + "<";
		for (int i = 0; i < DIMENSIONS; i++) {
			if (i != 0) {
				s += ",";
			}
			s += coords[i];
		}
		s += ">";
		return s;
	}
}
