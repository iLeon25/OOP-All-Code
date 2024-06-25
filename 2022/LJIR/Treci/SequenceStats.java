package hr.fer.oop.ljir.z3;

import java.util.LinkedList; 
import java.util.List; 
import java.util.stream.Collectors;
import java.util.stream.Stream;
 
public class SequenceStats {
	
	public static int totalSeqLength(List<Sequence> seqList) {
		List<String> seqs = new LinkedList<String>();
		seqs = seqList.stream().map((a) -> a.getSequence()).toList();
		
		int counter = 0;
		for (String s : seqs) {
			counter += s.length();
		}
		return counter;
	}
	
	public static List<String> seqNames(Stream<Sequence> seqList) {
		return seqList.map((a) -> a.getName()).collect(Collectors.toList());
	}
	
	public static double avgQuality(List<Sequence> seqList) {
		List<Double> listOfAverages = seqList.stream().map((a) -> a.avgSeqQuality()).toList();
		
		double counter = 0, sum = 0;
		for (Double d : listOfAverages) {
			sum += d;
			counter++;
		}
		return sum / counter;
	}
	
	// Sequences with Quality above threshold
	public static Stream<Sequence> usableSequences(List<Sequence> seqList, double thqual) {
		List<Sequence> oldButFiltered = seqList.stream().filter((a) -> a.avgSeqQuality() > thqual).toList();
		List<Sequence> newList = new LinkedList<Sequence>();
		
		for (Sequence sequence : oldButFiltered) {
			newList.add(new Sequence(sequence.getName(), sequence.getSequence(), ""));
		}
		return newList.stream();
	}

}
