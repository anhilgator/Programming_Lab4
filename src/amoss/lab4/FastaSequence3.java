package amoss.lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class FastaSequence3
{
	private String header;
	private String sequence;

	private FastaSequence3 (String header, String sequence)
	{
		this.header=header;
		this.sequence=sequence;
	}
	public static List<FastaSequence3> readFastaFile(String FastaFile)throws Exception //creates the .readFastaFile method
{
	BufferedReader textin = new BufferedReader(new FileReader(new File(FastaFile)));
	String firstline = textin.readLine();
	List<FastaSequence3> fastaList= new ArrayList<FastaSequence3>();
	String header = firstline;
	String sequence= ""; 
	for(String item=textin.readLine(); item != null; item=textin.readLine())
	{	
		if(item.startsWith(">"))//pulling lines with the '>' 
		{
			FastaSequence3 fs = new FastaSequence3(header, sequence);
			fastaList.add(fs);
			header=item; 
			sequence="";
		}
		
		else
		{	sequence += item; }
	
	}
	FastaSequence3 fs = new FastaSequence3(header, sequence);
	fastaList.add(fs);
	textin.close();
	return fastaList;

}

	public String getHeader() //returns the seq header 
	{

		return this.header;
	
	}

	public String getSequence() //returns the dna seq from fasta file
	{
		return this.sequence;
	}
	public float getGCratio()//returns GC content of each sequence object
	{
		
		int GCnumber=0;
		float GCratio=0;
		for(int i=0; i< sequence.length(); i++)
		{
			if (sequence.charAt(i) =='C' || sequence.charAt(i) =='G')
			{
				GCnumber++; //keeping count of the number of G and Cs in the string of sequence
			}
			GCratio= (float) GCnumber/sequence.length();
		}
		return GCratio;
	} 
	
	public static void writeUnique(String string, String string2) throws Exception 
	{
		List<FastaSequence3>fastaList=FastaSequence3.readFastaFile(string);
		List<String> newseqlist= new ArrayList<>();
		for(FastaSequence3 fs:fastaList)
		{
			newseqlist.add(fs.getSequence()); //a list of just the sequences
		}
		HashMap<String, Integer> countingmap = new HashMap<String, Integer>();	//this is the unsorted map
		for (int x=0; x<newseqlist.size(); x++)
		{
			Integer count = countingmap.get(newseqlist.get(x)); 
			if (count == null)
				count=0; 
			count++;
			countingmap.put(newseqlist.get(x).toString(), count);
		
		}
		System.out.println(countingmap.toString());
	}
		//Citation for help with this code is: https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values, https://beginnersbook.com/2013/12/how-to-sort-hashmap-in-java-by-keys-and-values/
		public LinkedHashMap <String, Integer> sortedByValues(HashMap<String, Integer> countingmap) throws IOException
		{
			List<String> sortedmap_keys = new ArrayList<>(countingmap.keySet());
			List<Integer> sortedmap_values = new ArrayList<>(countingmap.values());
			Collections.sort(sortedmap_keys);
			Collections.sort(sortedmap_values);
			LinkedHashMap<String, Integer> sortedmap = new LinkedHashMap<>();
			Iterator<Integer> iteratedValue = sortedmap_values.iterator();
			while (iteratedValue.hasNext())
				{
					Integer intValue = iteratedValue.next();
					Iterator<String> iteratedKey = sortedmap_keys.iterator();
					while(iteratedKey.hasNext())
					{
						String stringKey = iteratedKey.next();
						Integer firstcompare = countingmap.get(stringKey);
						Integer secondcompare = intValue;
						
						if (firstcompare.equals(secondcompare))
						{
							iteratedValue.remove();
							sortedmap.put(stringKey, intValue);
							break;
						}
				
				}
			}
		
		File outfile = new File ("outfile.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(outfile));
		bw.close();
		return sortedmap;
		
}
		

	//Citation for help: Anju, and stackoverflow.com/questions/8119366/sorting-hashmap-by-values
	
public static void main(String[] args) throws Exception
	{
	String infile = "C:\\Users\\anhil\\Desktop\\samplechimera.fasta";	
	String outfile = "C:\\Users\\anhil\\Desktop\\outfile.txt";
	writeUnique(infile, outfile);
	List<FastaSequence3>fastaList=FastaSequence3.readFastaFile("C:\\Users\\anhil\\Desktop\\samplechimera.fasta");
		for(FastaSequence3 fs:fastaList)
		{
			System.out.println(fs.getHeader());
			System.out.println(fs.getSequence());
			System.out.println(fs.getGCratio());

		}
	}

}

//Credit for help: brainstorming and collaboration/feedback with Anju, Fareeha, Dr. Fodor, help from forums like www.biostars.org/p/68342, and stackoverflow, and www.beginnersbook.com/2013/03/constructors-in-java/ 