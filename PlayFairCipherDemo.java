//EX.NO.2 PlayFair Cipher algorithm
import java.util.Scanner;
class Pair<T>
{
	public T L,R;
	public Pair(T L,T R)
	{
		this.L=L;
		this.R=R;
	}
	public static Pair with(Pair<Integer> P,Pair<Integer> Q,boolean isDecryption)
	{
		//During encryption +1 but during decryption -1
		int adder=isDecryption?4:1;
		if(P.L==Q.L)
		{			
			//condition no.2
			return new Pair<Pair>(new Pair<Integer>(P.L,(P.R+adder)%5),new Pair<Integer>(Q.L,(Q.R+adder)%5));
		}
		if(P.R==Q.R)
		{
			//condition no.3
			return new Pair<Pair>(new Pair<Integer>((P.L+adder)%5,P.R),new Pair<Integer>((Q.L+adder)%5,Q.R));
		}
		//condition no.4
		return new Pair<Pair>(new Pair<Integer>(P.L,Q.R),new Pair<Integer>(Q.L,P.R));
	}
}
public class PlayFairCipherDemo
{
	static char matrix[][]=new char[5][5];
	static char charAtIndex(Pair p)
	{
		return matrix[(int)p.L][(int)p.R];
	}
	static Pair indexOfChar(char c)
	{
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				if(matrix[i][j]==c)
				{
					return new Pair<Integer>(i,j);
				}
			}
		}
		return new Pair<Integer>(4,4);
	}
	//sender side
	static String encrypt(String plainText)
	{
		StringBuilder cipherText = new StringBuilder();
		char prev=0;
		boolean pair=false;
		for(char c:plainText.toCharArray())
		{
			if(pair)
				if(prev==c)
					cipherText.append('x');
				else
					pair=false;
			else
				pair=true;
			cipherText.append(c);
			prev=c;
		}
		//if length is odd, add z at last
		if(pair)cipherText.append('z');
		char cipherTextC[]=cipherText.toString().toCharArray();
		for(int i=0;i<cipherTextC.length;i+=2)
		{
			Pair<Pair> cpair=Pair.with(indexOfChar(cipherTextC[i]),indexOfChar(cipherTextC[i+1]),false);
			cipherTextC[i] = charAtIndex(cpair.L);
			cipherTextC[i+1] =charAtIndex(cpair.R);
		}
		return new String(cipherTextC);
	}

	//receiver side
	static String decrypt(String cipherText)
	{
		char plainTextC[]=cipherText.toCharArray();
		for(int i=0;i<plainTextC.length;i+=2)
		{
			Pair<Pair> cpair=Pair.with(indexOfChar(plainTextC[i]),indexOfChar(plainTextC[i+1]),true);
			plainTextC[i] = charAtIndex(cpair.L);
			plainTextC[i+1] =charAtIndex(cpair.R);
		}
		return new String(plainTextC);
	}

	//driver program
	public static void main(String args[])
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("PlayFair Cipher Algorithm Demo:");
		System.out.print("Enter a encryption keyword (please use only lower cases and non-repeating letters):");
		String keyword=sc.next();

		char alpha='a';
		int k=0,keyLen=(int)keyword.length();
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				if(k<keyLen)
					{
						matrix[i][j]=keyword.charAt(k);
						k++;
					}
				else
				{
					while(keyword.indexOf(alpha)!=-1)
						alpha+=1;
					matrix[i][j]=alpha;
					alpha+=1;
				}
			}
		}
		while(keyword.indexOf(alpha)!=-1)
				alpha+=1;
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				System.out.print(" "+matrix[i][j]);
			}
			System.out.println();
		}
		System.out.print("Enter the plain text (please use only lower cases):");
		String plainText=sc.next();
		String cipherText=encrypt(plainText);
		System.out.println("Cipher Text-After encryption: "+cipherText);
		plainText=decrypt(cipherText);
		System.out.println("Plain Text-After decryption: "+plainText);
	}

}

/*Sample i/o:
E:\Java\Network_security_lab>java PlayFairCipherDemo
PlayFair Cipher Algorithm Demo:
Enter a encryption keyword (please use only lower cases and non-repeating letters):democrazy
 d e m o c
 r a z y b
 f g h i j
 k l n p q
 s t u v w
Enter the plain text (please use only lower cases):balloonflieshigh
Cipher Text-After encryption: rzqtpempgkgoufjhnh
Plain Text-After decryption: balwloonflieshighz
*/