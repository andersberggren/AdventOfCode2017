package com.mountainbranch;

public class Dec15 {
	private Generator generatorA;
	private Generator generatorB;
	
	public long getNumberOfMatches(long iterations) {
		return getNumberOfMatches(iterations, false);
	}

	public long getNumberOfMatchesStrict(long iterations) {
		return getNumberOfMatches(iterations, true);
	}
	
	private long getNumberOfMatches(long iterations, boolean strictMode) {
		resetGenerators();
		long matches = 0L;
		for (long i = 0; i < iterations; i++) {
			generatorA.generateNext(strictMode);
			generatorB.generateNext(strictMode);
			if (generatorA.is16LsbMatch(generatorB)) {
				matches++;
			}
		}
		return matches;
	}

	private void resetGenerators() {
		generatorA = new Generator(16807, 4, 591);
		generatorB = new Generator(48271, 8, 393);
	}
	
	private class Generator {
		private final long divisor = 2147483647L;
		private final long multiplicationFactor;
		private final long strictMultiple;
		private long currentValue;
		
		public Generator(long multiplicationFactor, long strictMultiple, long startValue) {
			this.multiplicationFactor = multiplicationFactor;
			this.strictMultiple = strictMultiple;
			currentValue = startValue;
		}
		
		public void generateNext(boolean strict) {
			do {
				currentValue = (currentValue*multiplicationFactor) % divisor;
			} while (strict && currentValue % strictMultiple != 0);
		}
		
		/** Returns true iff the 16 least-significant-bits of the value held by
		 *  this generator and otherGenerator matches. */
		public boolean is16LsbMatch(Generator otherGenerator) {
			long divisor16Bit = 1L << 16;
			return (currentValue % divisor16Bit) == (otherGenerator.currentValue % divisor16Bit);
		}
	}
}
