// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
public class FileAccess {
  
  public static boolean loadPrimes(Primes primes, String filename) {
	  String path = Config.DATAPATH + filename;
	  primes.clearPrimes();
	  File file = new File(path);
	  try {
		  Scanner input = new Scanner(file);
		  while (input.hasNextLine()) {
			  BigInteger B = new BigInteger(input.nextLine());
			  primes.addPrime(B);
		  }
		  input.close();
	  }catch (IOException error)
	  {
		  return false;
	  }
	  
		return true;
		
  }
  
  public static boolean loadCrosses(Primes primes, String filename) {
	  String path = Config.DATAPATH + filename;
	  File file = new File(path);
	  try {
		  Scanner input = new Scanner(file);
	  
		  while (input.hasNextLine()) {
			  String line = input.nextLine();
			  int idx = line.indexOf(",");
			  BigInteger B = new BigInteger(line.substring(0, idx));
			  BigInteger C = new BigInteger(line.substring(idx+1));
			  Pair<BigInteger> pair = new Pair<BigInteger>(B,C);
			  primes.addCross(pair);
		  }
		  
		  input.close();
	  
	  }catch (IOException error)
	  {
		  return false;
	  }
		return true;
		
	}
  
  public static boolean savePrimes(Primes primes, String filename)
  {
	  String path = Config.DATAPATH + filename;
	try {
		FileWriter writer = new FileWriter(path);
		for(BigInteger x : primes.iteratePrimes())
	      {
	        writer.append(x.toString() + "\n");
	      }	
		writer.close();
	}catch (IOException error)
	  {
		  return false;
	  }
  	return true;
  }
  
  public static boolean saveCrosses(Primes primes, String filename)
  {
	  String path = Config.DATAPATH + filename;
		try {
			FileWriter writer = new FileWriter(path);
			for(Pair<BigInteger> cross : primes.iterateCrosses())
		    {
				 writer.append(cross.p1 + "," + cross.p2 + "\n");
			}	
		
			writer.close();
		}catch (IOException error)
		  {
			  return false;
		  }
	  	return true;
	  }
}