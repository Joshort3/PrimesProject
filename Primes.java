import java.util.ArrayList; 
import java.util.Iterator;
import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class Primes {
	
	// Pair class implementation.
	// Pair class holds two variables of type T
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	private ArrayList<BigInteger> primeList; // Holds the list of prime numbers represented as a BigInteger
	private ArrayList<Pair<BigInteger>> twinList; // Holds the list of twin primes represented as a Pair<BigInteger>
	//private ArrayList<Pair<Pair<BigInteger>>> hexList; // Holds the list of hexagon cross primes represented as a Pair<Pair<BigInteger>>
	private ArrayList<Pair<BigInteger>> hexList;
	// Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
	public void addPrime(BigInteger x)
	{
		if (!primeList.contains(x))
			primeList.add(x);
	}
	// Add a pair to the pair list 
	public void addPair(Pair<BigInteger> pair)
	{
		if (!twinList.contains(pair))
			twinList.add(pair);
	}
	
	// Adds a pair of BigIntegers that represent a Hexagonal Cross.
	public void addCross(Pair<BigInteger> pair)
	{
		if (!hexList.contains(pair))
			hexList.add(pair);
	}
		
	// Empties the list of primes.
	public void clearPrimes()
	{
		primeList.clear();
	}
		
	// Empties the list of crosses.
	public void clearCrosses()
	{
		hexList.clear();
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		// Iterates through primeList to print prime numbers
		for(int i = 0; i < primeList.size(); ++i) {
			System.out.println(primeList.get(i));
		}
		System.out.println("Total Primes: " + primeList.size());
	}
		
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
		// Iterates through twinList then gets the BigInteger values stored in the Pair
		for(int i = 0; i < twinList.size(); ++i) {
			System.out.println(twinList.get(i).p1 + ", " + twinList.get(i).p2);
		}
		System.out.println("Total Twins: " + twinList.size());
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
		// Iterates through HexList then gets the BigInteger values stored in the Pair<Pair>. 4 total BigInteger value need to be processed per iteration.
		for(int i = 0; i < hexList.size(); ++i) {
			BigInteger lowPair1 = hexList.get(i).p1.subtract(BigInteger.valueOf(1));
			BigInteger lowPair2 = hexList.get(i).p1.add(BigInteger.valueOf(1));
			BigInteger highPair1 = hexList.get(i).p2.subtract(BigInteger.valueOf(1));
			BigInteger highPair2 = hexList.get(i).p2.add(BigInteger.valueOf(1));
			BigInteger cross1 = hexList.get(i).p1;
			BigInteger cross2 = hexList.get(i).p2;
			System.out.println("Prime Pairs: " + lowPair1 + ", " + lowPair2 + " and " + highPair1 + ", " + highPair2 + " separated by " + cross1 + ", " + cross2);
		}
		System.out.println("Total Hexes: " + hexList.size());
	}
		
	// Generate and store a list of primes.
	public void generatePrimes(int count)
	{
		// Update PrimeList to hold "count" amount
		primeList = new ArrayList<BigInteger>(count);
		BigInteger B;
		
		// 2 and 3 are special prime numbers that don't work with the algorithm I built and must be stored by default  
		if(count >= 1) {
			primeList.add(BigInteger.valueOf(2));
		}
		if(count >= 2) {
			primeList.add(BigInteger.valueOf(3));
		}
		if (count >= 3) {
			int maxSize = 2; // represents the amount of data stored in primeList
			boolean isPrime = true; // Boolean used for checking if a number is prime.
			count = count -2; // Already stored two values so count is decreased by 2
			int i = 0; // iterator for while loop
			int j = 1; // used for the algorithm and is incremented every iteration of the while loop
			
			// This loop is the Prime checking algorithm and has two major parts two it
			// PART 1: Every Prime number can be represented as (6*j-1) or (6*j-1), where j is a positive integer. However,  some composite numbers can be represented as well.
			// (6*j-1) or (6*j-1) - this doesn't mean any integer for j will produce a prime number, but a prime number has't to be represent like this for it to be prime.
			// I used this theory to lower the amount of numbers my while loop/for loops use to check if a number is prime.
			// PART 2: The second part of the algorithm is to check whether (6*j-1) and (6*j-1) are truly prime.
			// To check if a number is Prime, I check if lower prime numbers can be denominators of the number generated.
			// This is done by iterating through the primeList, which already contains some primes and checking with modulus.
			while(i < count) {
				B = BigInteger.valueOf(6*j-1);
				for(int k = 0; k <maxSize; k++) {
					if(B.remainder(primeList.get(k)).equals(BigInteger.valueOf(0))) {
						isPrime = false;
						k = maxSize;
					}
				}
				if(isPrime) {
					primeList.add(B);
					maxSize++;
					i++;
				}
				else
					isPrime = true;
				B = BigInteger.valueOf(6*j+1);
				if(i < count) {
					for(int k = 0; k <maxSize; k++) {
						if(B.remainder(primeList.get(k)).equals(BigInteger.valueOf(0))) {
							isPrime = false;
							k = maxSize;
						}
					}
					if(isPrime) {
						primeList.add(B);
						maxSize++;
						i++;
					}
					else
						isPrime = true;
				}
				++j;
			}
		}
	}
	
	public void generatePrimes(BigInteger candidate, int count) {
		ArrayList<BigInteger> tempList = new ArrayList<BigInteger>(candidate.intValue() + count);
		int maxSize = 0;
		int j = 1;
		boolean stopped = false;
		if (count < 1) return;
		BigInteger B, C;
		//System.out.println("Beginning");
		if(candidate.compareTo(BigInteger.valueOf(2))> -1 ) {
			tempList.add(BigInteger.valueOf(2));
			maxSize++;
			//System.out.println("First if");
		}
		if(candidate.compareTo(BigInteger.valueOf(3))> -1 ) {
			tempList.add(BigInteger.valueOf(3));
			maxSize++;
			//System.out.println("2nd if");
		}
		if (candidate.compareTo(BigInteger.valueOf(5))> -1 ) {
			//System.out.println("3rd if");
			int i = 0; // iterator for while loop
			while(candidate.compareTo(BigInteger.valueOf(6*j-1))> -1) {
				B = BigInteger.valueOf(6*j-1);
				if(NaiveTest.isPrime(tempList,maxSize,B))
				{
					tempList.add(B);
					maxSize++;
					//found = true;
				}
				B = BigInteger.valueOf(6*j+1);
				stopped = true;
				if(NaiveTest.isPrime(tempList,maxSize,B) && (candidate.compareTo(B)> -1))
				{
					tempList.add(B);
					maxSize++;
					stopped = false;
				}
			}
			j++;
		}
		
		int i = 0;
		primeList = new ArrayList<BigInteger>(count);
		if(!tempList.contains(BigInteger.valueOf(2))) {
			tempList.add(BigInteger.valueOf(2));
			primeList.add(BigInteger.valueOf(2));
			maxSize++;
			++i;
			//System.out.println("l if");
		}
		if(!tempList.contains(BigInteger.valueOf(3))) {
			tempList.add(BigInteger.valueOf(3));
			primeList.add(BigInteger.valueOf(3));
			maxSize++;
			++i;
			//System.out.println("2 if");
		}
		if(tempList.contains(candidate)) {
			if(!primeList.contains(candidate))
				primeList.add(candidate);
			++i;
		}
		while(i < count) {
			if(stopped) {
				B = BigInteger.valueOf(6*j+1);
				if(NaiveTest.isPrime(tempList,maxSize,B))
				{
					primeList.add(B);
					tempList.add(B);
					++i;
				}
				if(i >= count)
					break;
			}
			B = BigInteger.valueOf(6*j-1);
			if(NaiveTest.isPrime(tempList,maxSize,B))
			{
				primeList.add(B);
				tempList.add(B);
				maxSize++;
				++i;
			}
			B = BigInteger.valueOf(6*j+1);
			if (i < count) {
				//System.out.println(6*j+1);
				if(NaiveTest.isPrime(tempList,maxSize,B))
				{
					primeList.add(B);
					tempList.add(B);
					maxSize++;
					++i;
				}
				j++;
			}
		}
	}
	
	// Generate and store a list of twin primes.
	public void generateTwinPrimes()
	{
		// Set size of twinList to have the primeList because twinList is storing pairs and at max would be half of primeList's size
		// In reality, we will use less than this but just in case since we don't know the number of twin primes there are.
		twinList = new ArrayList<Pair<BigInteger>>(primeList.size()/2);
		BigInteger C = BigInteger.valueOf(2); // used to check the difference of two BigIntegers
		
		// checks every prime number next to each other in the ArrayList if there difference is 2 if yes then add to twinList
		for(int i = 1; i < primeList.size(); ++i) {
			BigInteger A = primeList.get(i-1);
			BigInteger B = primeList.get(i);
			if(B.subtract(A).equals(BigInteger.valueOf(2))) {
				Pair<BigInteger> twin  = new Pair<BigInteger>(A,B);
				twinList.add(twin);
			}
		}
		// Lower's the size of the list to what was used
		twinList.trimToSize();
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
		//
		//hexList = new ArrayList<Pair<Pair<BigInteger>>>(twinList.size()/2);
		hexList = new ArrayList<Pair<BigInteger>>(twinList.size()/2);
		for(int i = 0; i < twinList.size(); ++i) {
			Pair<BigInteger> A = twinList.get(i);
			for(int j = i+1 ; j < twinList.size(); ++j) {
				Pair<BigInteger> B = twinList.get(j);
				BigInteger hex1 = A.p1.add(BigInteger.valueOf(1));
				BigInteger hex2 = B.p1.add(BigInteger.valueOf(1));
				if(hex2.equals(hex1.multiply(BigInteger.valueOf(2)))) {
					//Pair<Pair<BigInteger>> doublePairs = new Pair<Pair<BigInteger>>(A,B);
					Pair<BigInteger> hexPair = new Pair<BigInteger>(hex1,hex2);
					hexList.add(hexPair);
					j=twinList.size();
				}
			}
		}
		hexList.trimToSize();
	}
	public int sizeofLastPrime()
	{
		BigInteger last = primeList.get(primeList.size()-1);
		String bigInt = last.toString();
		System.out.println("BigInteger: "+ last);
		return bigInt.length();
	}
	public Pair<Integer> sizeofLastCross()
	{
		Pair<BigInteger> lastCross = hexList.get(hexList.size()-1);
		String bigInt1 = lastCross.p1.toString();
		String bigInt2 = lastCross.p2.toString();
		int length1 = bigInt1.length();
		int length2 = bigInt2.length();
		Pair<Integer> digits = new Pair<Integer>(length1,length2);
		return digits;
	}
	
	// Return the number of primes
	public int primeCount()
	{
		return primeList.size();
	}
	
	// Return the number of crosses.
	public int crossesCount()
	{
		return hexList.size();
	}
	
	public class IterablePrimes implements Iterable<BigInteger>
	{

		@Override
		public Iterator<BigInteger> iterator() {
			return primeList.iterator();
		}	
		
		
	}
	
	public IterablePrimes iteratePrimes() { return new IterablePrimes();}

	public class IterableCrosses implements Iterable<Pair<BigInteger>>
	{

		@Override
		public Iterator<Pair<BigInteger>> iterator() {
			// TODO Auto-generated method stub
			return hexList.iterator();
		}		
		
	}
	
	public IterableCrosses iterateCrosses() { return new IterableCrosses();}
}
