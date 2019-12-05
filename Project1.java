import java.math.BigInteger;

public class Project1 {
	public static void main(String[] args) 
	{
		Primes p = new Primes();
		
		MainWindow mw = new MainWindow(Config.APPLICATIONNAME, p);
		// Instantiate Primes Class
		/*Primes testOne = new Primes();
		
		// Generate Primes and test.
		testOne.generatePrimes(21);
		testOne.printPrimes();
		Primes testOne1 = new Primes();
		testOne1.generatePrimes(BigInteger.valueOf(3),20);
		testOne1.printPrimes();
		MainWindow help;*/
		// Generate and test Twin Primes
		/*Primes testTwo = new Primes();
		testTwo.generatePrimes(100);
		testTwo.generateTwinPrimes();
		testTwo.printTwins();
		
		// Generate and test Hexagonal crosses
		Primes testThree = new Primes();
		testThree.generatePrimes(2000);
		testThree.generateTwinPrimes();
		testThree.generateHexPrimes();
		testThree.printHexes();*/
	}
}
