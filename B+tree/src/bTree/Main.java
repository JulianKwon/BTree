package bTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main extends NodeCreate
{

	public static Node root;

	public static int choice;
	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

		while (true)
		{
			System.out.println("1.����");
			System.out.println("2.����");
			System.out.println("3.�˻�");
			System.out.println("4.���");
			System.out.println("9.�ϰ�����");
			System.out.println("0.����");
			System.out.print("���� : ");
			choice = s.nextInt();
			if (choice == 0)
				break;
			else
				operation(choice);
		}
	}

	public static void operation(int c)
	{
		int input = 0;

		if (c == 1)
		{
			while (true)
			{
				System.out.print("������ Ű (����: -1): ");
				input = s.nextInt();
				if (input == -1)
					break;
				else if (Search.searchkey(1, input, root, false))
					System.out.println("Ű�� �̹� �����մϴ�.");
				else
					Insert.insert(input);
			}

		}
		else if (c == 2)
		{
			while (true)
			{
				System.out.print("������ Ű (����: -1): ");
				input = s.nextInt();
				if (input == -1)
					break;
				else if (!Search.searchkey(1, input, root, false))
					System.out.println("������ Ű�� �������� �ʽ��ϴ�.");
				else
					Delete.deletekey(input, root);
			}
		}
		else if (c == 3)
		{
			while (true)
			{
				System.out.print("�˻��� Ű (����: -1): ");
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
			System.out.print("������ ����: ");
			String str = s.next();
			read(str);
			System.out.println("���� �Ϸ�");
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
