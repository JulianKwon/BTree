package bTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.Random;

public class Main extends NodeCreate
{

	public static Node root;

	public static String choice;
	public static int input;

	public static Scanner s = new Scanner(System.in);

	public static boolean isStringDouble(String s)
	{
		try
		{
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e)
		{
			return false;
		}
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		while (true)
		{
			System.out.println("1.삽입");
			System.out.println("2.삭제");
			System.out.println("3.검색");
			System.out.println("4.출력");
			System.out.println("9.일괄삽입");
			System.out.println("0.종료");
			System.out.print("선택 : ");
			choice = s.next();
			if (!isStringDouble(choice))
			{
				System.out.println("다시 입력하세요.");
			} else
			{
				input = Integer.parseInt(choice);

				if (input == 0)
					break;
				else
					operation(input);
			}
		}
	}

	public static void operation(int c)
	{
		int input = 0;

		if (c == 1)
		{
			while (true)
			{
				System.out.print("삽입할 키 (종료: -1): ");
				input = s.nextInt();
				if (input == -1)
					break;
				else if (Search.searchkey(1, input, root, false))
					System.out.println("키가 이미 존재합니다.");
				else
					Insert.insert(input);
			}

		} else if (c == 2)
		{
			while (true)
			{
				System.out.print("삭제할 키 (종료: -1): ");
				input = s.nextInt();
				if (input == -1)
					break;
				else if (!Search.searchkey(1, input, root, false))
					System.out.println("삭제할 키가 존재하지 않습니다.");
				else
					Delete.deletekey(input, root);
			}
		} else if (c == 3)
		{
			while (true)
			{
				System.out.print("검색할 키 (종료: -1): ");
				input = s.nextInt();
				if (input == -1)
					break;
				else
					Search.searchkey(1, input, root, true);
			}
		}

		else if (c == 4)
			PrintTree.print(1, root);

		else if (c == 9)
		{
			System.out.print("데이터 파일: ");
			String str = s.next();
			read(str);
			System.out.println("삽입 완료");
		}
	}

	public static void read(String input)
	{
		try
		{
			File aFile = new File(input);
			FileReader fileReader = new FileReader(aFile);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;
			while ((line = reader.readLine()) != null)
				// read line then insert number
				Insert.insert(Integer.parseInt(line));
			reader.close();
		} catch (Exception ex)
		{
		}
	}
}
