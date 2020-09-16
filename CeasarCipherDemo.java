import java.util.Scanner;
public class CeasarCipherDemo
{
	//sender side
	static String encrypt(String plainText, byte key)
	{
		StringBuilder cipherText=new StringBuilder(plainText.length());
		for(char c:plainText.toCharArray())
		{
			cipherText.append((char)('a'+(c-'a'+key)%26));
		}
		return cipherText.toString();
	}

	//receiver side
	static String decrypt(String cipherText , byte key)
	{
		StringBuilder plainText=new StringBuilder(cipherText.length());
		for(char c:cipherText.toCharArray())
		{
			//26+ is to handle negative case
			plainText.append((char)('a'+(26+c-'a'-key)%26));
		}
		return plainText.toString();
	}

	//driver program
	public static void main(String args[])
	{
		Scanner sc= new Scanner(System.in);
		System.out.println("Ceasar Cipher Algorithm Demo:");
		System.out.print("Enter a encryption key (1 to 25):");
		byte key=sc.nextByte();
		System.out.print("Enter the plain text (please use only lower cases):");
		String plainText=sc.next();
		String cipherText=encrypt(plainText,key);
		System.out.println("Cipher Text-After encryption: "+cipherText);
		plainText=decrypt(cipherText,key);
		System.out.println("Plain Text-After decryption: "+plainText);
	}

}

/*Sample i/o:

Ceasar Cipher Algorithm Demo:
Enter a encryption key (1 to 25):12
Enter the plain text (please use only lower cases):werulemachine
Cipher Text-After encryption: iqdgxqymotuzq
Plain Text-After decryption: werulemachine
*/