import java.math.BigInteger;
import java.util.ArrayList; 
public class NaiveTest
{
	
	public static boolean isPrime(BigInteger candidate)
	{
		ArrayList<BigInteger> primes =new ArrayList<BigInteger>();
		if (!candidate.isProbablePrime((100))) return false; // Weed out the likely not primes.
		return false;
	}
	public static boolean isPrime(ArrayList<BigInteger> primes,int size, BigInteger candidate) {
		if (!candidate.isProbablePrime((100))) return false;
		for(int k = 0; k < size; k++) {
			if(candidate.remainder(primes.get(k)).equals(BigInteger.valueOf(0))) {
				return false;
			}
		}
		return true;
	}
	
}
