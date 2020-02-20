package IDMA_Libraries_Alpha.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * /////////////////////////////////////////////////////////////////////////////
 * - Find Random Numbers
 * - Clamping a value to a specific range (even with delay)
 * - Square and inverse square (for different type)
 * /////////////////////////////////////////////////////////////////////////////
 */

public abstract class MathHelper {

	/**
	 * Returns a random integer between 2 integers.
	 *
	 * @param int min, int max
	 * @return integer
	 */
	public static int randomInt(int min, int max) {
		if (min <= max) {
			return ThreadLocalRandom.current().nextInt(min, max + 1);
		} else {
			return ThreadLocalRandom.current().nextInt(max, min + 1);

		}
	}

	/**
	 * Returns a random double between 2 doubles.
	 *
	 * @param double min, double max
	 * @return double
	 */
	public static double randomDouble(double min, double max) {
		if (min <= max) {
			return ThreadLocalRandom.current().nextDouble(min, max + 1);
		} else {
			return ThreadLocalRandom.current().nextDouble(max, min + 1);
		}
	}

	/**
	 * Clamps num between min and max.
	 *
	 * @param int num, int min, int max
	 * @return int num
	 */
	public static int clamp(int num, int min, int max) {
		num = Math.max(min, Math.min(max, num));
		return num;
	}

	/**
	 * Clamps num between min and max with a delay (???)
	 *
	 * @param long delayPreClamp, int min, int max
	 * @return max
	 */
	public static long delayedClamp(long delayPreClamp, int min, int max) {
		// v = delayPreClamp
		// if v < min, returns the greater between min and v, thus min
		// if v > max, returns the greater between min and max, thus max
		// if v is between min and max, returns the greater between min and v, thus v
		return Math.max(min, Math.min(delayPreClamp, max));
	}

	/**
	 * fast Square function
	 * 
	 * @param double x
	 * @return double x
	 */
	// to be check
	public static double sqrtDouble(double x) {
		double d = 289358932.0;
		double sqrt = Double.longBitsToDouble(((Double.doubleToLongBits(d) - (1l << 52)) >> 1) + (1l << 61));
		return x = (sqrt + d / sqrt) / 2.0; // newton step
	}

	// Algorithm: http://ilab.usc.edu/wiki/index.php/Fast_Square_Root
	public static float sqrtFloat(float x) {
		float xhalf = 0.5f * x;
		float u = x;
		int i = 0;
		i = MathHelper.SQRT_MAGIC - (i >> 1); // gives initial guess y0
		return x * u * (1.5f - xhalf * u * u); // Newton step, repeating increases accuracy
	}

	/**
	 * fast inverse sqrt function - OBSOLETE
	 *
	 * @param float x
	 * @return float x
	 */
	// by Carmack - Quake III
	private static final int SQRT_MAGIC = 0x5f3759df;

	// Inverse Square Root for Float
	public static float invSqrtFloat(float x) {
		float xhalf = 0.5f * x;
		int i = Float.floatToIntBits(x);
		i = 0x5f3759df - (i >> 1);
		x = Float.intBitsToFloat(i);
		x *= (1.5f - xhalf * x * x);
		return x;
	}

	// Inverse Square Root for DOUBLE - MORE ACCURACY!!!!!!!!!
	public static double invSqrtDouble(double x) {
		double number = x;
		double xhalf = 0.5d * x;
		long i = Double.doubleToLongBits(x);
		i = 0x5fe6ec85e7de30daL - (i >> 1);
		x = Double.longBitsToDouble(i);
		for (int it = 0; it < 4; it++) { // loop for more accuracy
			x = x * (1.5d - xhalf * x * x);
		}
		x *= number;
		return x;
	}

//  Original script
//	public static double invSqrt(double x) {
//		double xhalf = 0.5d * x;
//		long i = Double.doubleToLongBits(x); // evil floating point bit level hacking
//		i = 0x5fe6ec85e7de30daL - (i >> 1); // what the fuck?
//		x = Double.longBitsToDouble(i);
//		x *= (1.5d - xhalf * x * x); // 1st iteration
////		x *= (1.5d - xhalf * x * x); // 2nd iteration, this can be removed
//		return x;
//	}

	/**
     * Generates a number between [min, minUpperBound) U (maxLowerBound, max)
     *
     * For instance to generate a number between -10 and 10, but no lower than
     * -5 or 5, do randBounds( -10, -5, 5, 10). Precision doesn't really matter;
     *
     * @param double min, double minUpperBound, double maxLowerBound, double max
     *
     * In the end, min leq x leq minUpperBound OR maxLowerBound leq x leq max;
     * @return
     */
    public static double randBounds ( double min, double minUpperBound, double maxLowerBound, double max )
    {
        double n;

        do
        {
            n = MathHelper.randomDouble( min, max );

        }
        while ( ( n < min || n > minUpperBound ) && ( n < maxLowerBound || n > max ) );

        return n;
    }
    
    
}
