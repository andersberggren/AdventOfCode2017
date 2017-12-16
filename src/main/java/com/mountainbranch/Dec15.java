package com.mountainbranch;

import java.math.BigInteger;

public class Dec15 {
	private Generator generatorA;
	private Generator generatorB;
	
	public Dec15(long startValueA, long startValueB) {
		generatorA = new Generator(16807, startValueA);
		generatorB = new Generator(48271, startValueB);
	}
	
	public long getNumberOfMatches(long iterations) {
		long matches = 0L;
		for (long i = 0; i < iterations; i++) {
			generatorA.generateNext();
			generatorB.generateNext();
			if (generatorA.is16LsbMatch(generatorB)) {
				matches++;
			}
		}
		return matches;
	}
	
	private class Generator {
		private final BigInteger divisor = BigInteger.valueOf(2147483647L);
		private final BigInteger multiplicationFactor;
		private BigInteger currentValue;
		
		public Generator(long multiplicationFactor, long startValue) {
			this.multiplicationFactor = BigInteger.valueOf(multiplicationFactor);
			currentValue = BigInteger.valueOf(startValue);
		}
		
		public void generateNext() {
			currentValue = currentValue.multiply(multiplicationFactor).remainder(divisor);
		}
		
		public boolean is16LsbMatch(Generator otherGenerator) {
			BigInteger divisor16Bit = BigInteger.valueOf(1L << 16);
			BigInteger lsbA = currentValue.remainder(divisor16Bit);
			BigInteger lsbB = otherGenerator.currentValue.remainder(divisor16Bit);
			return lsbA.equals(lsbB);
		}
	}
}
