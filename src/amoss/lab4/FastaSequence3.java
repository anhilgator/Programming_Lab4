package amoss.lab4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

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
	BufferedReader textin = new BufferedReader(new FileReader(FastaFile));
	String line;
	Boolean present=true;
	List<FastaSequence3> fastaList= new ArrayList<FastaSequence3>();
	while((line=textin.readLine()) !=null)
	{	
		FastaSequence3 fs = new FastaSequence3(line, line);
		if(line.startsWith(">"))//pulling lines with the '>' 
			fs.append(line); 
			if(present)
				continue;
		else
			fs.append(line);
	}
	textin.close();
	return fastaList;
	
}



	private void append(String line) //method to append to string? 
	{
	 
		
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
		String countingline= getSequence();
		int GCnumber=0;
		float GCratio=0;
		String G =null;
		String C=null;//per Eclipse...null initializes these variables...
		for(int i=0; i< sequence.length(); i++)
		{
			if(G.equals(countingline) || C.equals(countingline))
			{
				GCnumber++; //keeping count of the number of G and Cs in the string of sequence
			}
			GCratio=(GCnumber/sequence.length())*100;
		}
		return GCratio;
	} 
public static void main(String[] args) throws Exception
	{
		List<FastaSequence3>fastaList=FastaSequence3.readFastaFile("/home/anh/samplechimera.fasta");
		for(FastaSequence3 fs:fastaList)
		{
			System.out.println(fs.getHeader());
			System.out.println(fs.getSequence());
			System.out.println(fs.getGCratio());
		}
	}

}

//Credit for help: brainstorming and collaboration/feedback with Anju, Fareeha, Dr. Fodor, help from forums like www.biostars.org/p/68342, and stackoverflow, and www.beginnersbook.com/2013/03/constructors-in-java/ 