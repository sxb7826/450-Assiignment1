/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SRICHARAN
 */
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SebestaScanner
{
	static int charClass;
	static char []lexeme = new char[100] ;
	static char nextChar;
	static int lexLen = 0;
	static int token;
	static int nextToken;

	static final int LETTER = 0;
	static final int DIGIT = 1;
	static final int UNKNOWN = 99;
	static final int EOF = -1;

	static final int INT_LIT = 10;
	static final int IDENT = 11;
	static final int ADD_OP = 21;
	static final int SUB_OP = 22;
	static final int MULT_OP = 23;
	static final int DIV_OP = 24;
	static final int LEFT_PAREN = 25;
	static final int RIGHT_PAREN = 26;

        static FileReader inputStream = null;

	public static void main (String[] args)
	{
		try
		{
			inputStream = new FileReader("E:\\Study Material\\450\\Assignment1\\front.in");
			getChar();
			do
			{
				lex();
			}
			while (nextToken!=EOF);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File open error.");
			System.out.println("Directiry: "+ System.getProperty("user.dir"));
		}
		finally
		{
			if (inputStream!=null)
			{
				try
				{
					inputStream.close();
				}
				catch (IOException e)
				{
					System.out.println("File close error.");
				}
			}
                }
        }
                
        public static void getChar()
        {
            int r;
            try 
            {
                if((r = inputStream.read()) != -1)
                {
                    nextChar = (char)r;
                    if(Character.isAlphabetic(nextChar))
                    {
                        charClass = LETTER;
                    }
                    else if (Character.isDigit(nextChar))
                    {
                        charClass = DIGIT;
                    }
                    else
                    {
                        charClass = UNKNOWN;
                    }	
		}
                else 
                {
                    charClass = EOF;
                    nextChar = '\0';
		}
            } 
            catch (IOException e) 
            {
		e.printStackTrace();
            }
	}
	
	public static void addChar()
        {
		if(lexLen <= 98)
                {
			lexeme[lexLen++] = nextChar;
			lexeme[lexLen] = 0;
		}else 
                {
			System.out.println("Error: lexeme is too long");
		}
	}
	
	public static void getNoNBlank()
        {
		while(Character.isWhitespace(nextChar))
                {
			getChar();
		}
	}
	
	public static int lookup(char ch)
        {
		switch(ch)
                {
		case '(' : addChar();
					nextToken = LEFT_PAREN;
					break;
		case ')' : addChar();
					nextToken = RIGHT_PAREN;
					break;
		case '+' : addChar();
					nextToken = ADD_OP;
					break;
		case '-' : addChar();
					nextToken = SUB_OP;
					break;
		case '*' : addChar();
					nextToken = MULT_OP;
					break;
		case '/' : addChar();
					nextToken = DIV_OP;
					break;
		default : addChar();
					nextToken = EOF;
					break;
		}
		return nextToken;
	}
	
	
	public static int lex()
        {
		lexLen = 0;
		lexeme = new char[100];
		getNoNBlank();
		switch(charClass)
                {
		case LETTER : addChar();
						getChar();
						while(charClass == LETTER)
                                                {
							addChar();
							getChar();
						}
						nextToken = IDENT;
						break;
		case DIGIT : addChar();
						getChar();
						while(charClass == DIGIT)
                                                {
							addChar();
							getChar();
						}
						nextToken = INT_LIT;
						break;
		case UNKNOWN : lookup(nextChar);
						getChar();
						break;
		case EOF : nextToken = EOF;
					lexeme[0] = 'E';
					lexeme[1] = 'O';
					lexeme[2] = 'F';
					lexeme[3] = '\0';
				
		
		}
		
		System.out.println("Next token is: " + nextToken + " Next lexeme is " + new String(lexeme));
		return nextToken;
        }
	
}	


