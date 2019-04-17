/* C343 Summer 2018
 * homework 01
 * Ian Polito
 * ipolito
 */

public class DNADist {
	
	//takes two input strings (DNA strings) and computes their
	//hamming distance
	public int hammingDistance(String dna1, String dna2) {
		int result = 0;
		for (int i = 0; i < dna1.length(); i++) {
			if (dna1.charAt(i) != dna2.charAt(i)) {
				result++;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		//Testing DNADist and RandomDNA
		RandomDNA tester = new RandomDNA();
		DNADist tester2 = new DNADist();
		
		String dna1 = tester.getDNA(100);
		String dna2 = tester.getDNA(100);
		System.out.println(dna1);
		System.out.println(dna2);
		System.out.println(tester2.hammingDistance(dna1,dna2));
		
		dna1 = tester.getDNA(100);
		dna2 = tester.getDNA(100);
		System.out.println(dna1);
		System.out.println(dna2);
		System.out.println(tester2.hammingDistance(dna1,dna2));
		
		dna1 = tester.getDNA(100);
		dna2 = tester.getDNA(100);
		System.out.println(dna1);
		System.out.println(dna2);
		System.out.println(tester2.hammingDistance(dna1,dna2));
	}
}
