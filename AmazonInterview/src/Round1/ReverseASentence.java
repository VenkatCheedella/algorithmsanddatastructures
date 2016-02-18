package Round1;

public class ReverseASentence {
	
	public ReverseASentence()
	{
		this.testFromConstrucor();
	}
	
	public void testFromConstrucor()
	{
		System.out.println("A call from Construtor");
	}
	
	
	public static void main(String[] args) {
		String s = "This is Venkat Cheedella";
		String[] words = s.split(" ");
		for(int i=0; i< words.length; i++)
		{
			System.out.print(words[i] +" ");
			StringBuffer next_word = new StringBuffer(words[i].length());			
			
		}
		// Store the data on Stack and print
		ReverseASentence rev_sent = new ReverseASentence();
	}

}
