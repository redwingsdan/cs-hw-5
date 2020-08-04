/**
 * Dan Peterson
 * 109091561
 * daniel.peterson@stonybrook.edu
 * Homework #5
 * CSE 214 Recitation #5
 * Sun Lin
 * @author Dan
 */

import java.util.Scanner;

public class BashTerminal {
	public static void main(String[] args)
	{
		DirectoryTree t = new DirectoryTree();
		Scanner input = new Scanner(System.in);
		Boolean flag = false;
		Boolean flag2 = false;
		Boolean flag3 = false;
		String command = null;
		int num = 0;
		System.out.print("Command Line Terminal Instructions:\n\npwd = Print the present working directory of the cursor\nls = List the names of the child directories and files of the cursor");
		System.out.print("\nls -R = Prints a tree of the directory tree, starting from the cursor\ncd {dir} = Move the cursor to the directory indicated(only children of the current directory are permitted)");
		System.out.print("\ncd / = Move the cursor to the root directory\nmkdir {name} = Creates a new directory with the indicated name if there is room\ntouch {name} = Creates a new file with the indicated name if there is room");
		System.out.print("\nexit = Ends the program\nfind {name} = Finds the directory with the given name and prints out the path\ncd .. = Moves the cursor up to the parent node\n\n");
		System.out.print("Enter 1 if you would you like to see where the cursor is after every command: ");
		if(input.hasNextInt())
		{
			num = input.nextInt();
			if(num == 1)
				flag3 = true;
		}
		while(flag == false)
		{
			flag2 = false;
			if(flag3 == true)
				System.out.println("Cursor is currently at: " + t.getCursor().getName());
			System.out.print("[dapeterson@CSE214]: $ ");
			command = input.nextLine();
			if(command.contains("cd "))
			{
				String[] array = command.split(" ");
				if(array[1].equals("/"))
				{
					try 
					{
						t.changeDirectory("root");
						flag2 = true;
					} 
					catch (Exception e) 
					{
						flag2 = true;
					}
				}
				else if(array[1].equals(".."))
				{
					try
					{
						//t.setCursor(t.getParent(t.getCursor(), t.getRoot()));
						String msg = t.presentWorkingDirectory(t.getCursor(), t.getRoot());
						String[] array1 = msg.split("/");
						String test = array1[(array1.length - 2)];
						t.setCursor(t.getParent(test, t.getRoot()));
						flag2 = true;
					}
					catch(Exception e)
					{
						flag2 = true;
					}
				}
				else
				{
					try 
					{
						t.changeDirectory(array[1]);
						flag2 = true;
					} 
					catch (Exception e) 
					{
						System.out.print(e.getMessage());
						flag2 = true;
					}
				}
			}
			
			if(command.contains("mkdir "))
			{
				String[] array = command.split(" ");
					try 
					{
						t.makeDirectory(array[1]);
						flag2 = true;
					} 
					catch (Exception e) 
					{
						System.out.print(e.getMessage());
						flag2 = true;
					}
			}
		
			if(command.contains("touch "))
			{
				String[] array = command.split(" ");
					try 
					{
						t.makeFile(array[1]);
						flag2 = true;
					} 
					catch (Exception e) 
					{
						System.out.print(e.getMessage());
						flag2 = true;
					}
			}
			
			if(command.contains("find "))
			{
				String[] array = command.split(" ");
				try 
				{
					DirectoryNode temp = t.find(t.getRoot(), array[1]);
					System.out.println(t.presentWorkingDirectory(temp, t.getRoot()));
					flag2 = true;
				} 
				catch (Exception e) 
				{
					System.out.print(e.getMessage());
					flag2 = true;
				}
			}
			
			if(flag2 == false)
			{
				switch(command)
				{
				
					case "pwd":
						System.out.print(t.presentWorkingDirectory(t.getCursor(),t.getRoot()));
						break;
					case "ls":
						System.out.print(t.listDirectory());
						break;
					case "ls -R":
						t.printDirectoryTree(t.getCursor(), 0);
						break;
					case "exit":
						flag = true;
						break;
						
					default:
						System.out.println("Invalid command.");				
				}
			}
			System.out.println();
		}
		System.out.println("Program terminating normally...");
	}
}