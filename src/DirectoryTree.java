/**
 * Dan Peterson
 * 109091561
 * daniel.peterson@stonybrook.edu
 * Homework #5
 * CSE 214 Recitation #5
 * Sun Lin
 * @author Dan
 */

/**
 * This class creates an object of the type DirectoryTree. This class has two DirectoryNode objects in it: <code>root</code>
 * and <code>cursor</code>, both are DirectoryNode objects. <code>Root</code> is the original (root) node of the tree.
 * <code>Cursor</code> is used to traverse the tree and points to different nodes within the tree. This class contains
 * a method to move the cursor back to the root, a method to print out all the children of the node current being pointed
 * to by the cursor, a method to print the path from the root to the node currently pointed at by the cursor, a method to
 * recursively print the tree from the node which the cursor is currently pointing to, a method to move the cursor to the
 * node with a particular name, and methods to create new directories and files as DirectoryNode objects. 
 */
public class DirectoryTree {
	
	private DirectoryNode root;
	private DirectoryNode cursor;
	
	/**
	 * Creates an instance of <code>DirectoryTree</code> - Constructor
	 * @param root = The first node (root) of a binary tree - DirectoryNode
	 * @param cursor = Cursor to traverse the binary tree - DirectoryNode
	 */
	public DirectoryTree()
	{
		root = new DirectoryNode(null, null, null, "root", false);
		cursor = root;
	}
	
	/**
	 * Sets the value of <code>root</code> to the DirectoryNode value passed in.
	 * @param root = The first node (root) of a binary tree - DirectoryNode
	 */
	public void setRoot(DirectoryNode root)
	{
		this.root = root;
	}
	
	/**
	 * Sets the value of <code>cursor</code> to the DirectoryNode value passed in.
	 * @param cursor = Cursor to traverse the binary tree - DirectoryNode
	 */
	public void setCursor(DirectoryNode cursor)
	{
		this.cursor = cursor;
	}
	
	/**
	 * Returns the value of <code>root</code> - DirectoryNode
	 * @return root - DirectoryNode
	 */
	public DirectoryNode getRoot()
	{
		return root;
	}
	
	/**
	 * Returns the value of <code>cursor</code> - DirectoryNode
	 * @return cursor - DirectoryNode
	 */
	public DirectoryNode getCursor()
	{
		return cursor;
	}
	
	/**
	 * Moves the cursor back to root.
	 */
	public void resetCursor()
	{
		cursor = root;
	}
	
	/**
	 * Moves the cursor from its current node to one of the children of the current node.
	 * The child is determined by the string value which is passed into the function.
	 * If there is a child with the given name, then the cursor is moved.
	 * @param name = Name of the directory which the cursor should move to - String
	 * @throws Exception = Indicates that the child is a file, and not a directory.
	 * @throws Exception = Indicates that no child has the provided name and therefore the cursor cannot move.
	 */
	public void changeDirectory(String name) throws Exception
	{
		DirectoryNode temp = cursor;
		if(root.getName().equals(name))
		{
			cursor = root;
		}
		if(cursor.getLeft() != null)
		{
			if(cursor.getLeft().getName().equals(name))
			{
				cursor = cursor.getLeft();
				if(cursor.getIsFile() == true)
				{
					cursor = temp;
					throw new Exception("This name references a file, not a directory");
				}
			}
		}
		if(cursor.getMiddle() != null)
		{
			if(cursor.getMiddle().getName().equals(name))
			{
				cursor = cursor.getMiddle();
				if(cursor.getIsFile() == true)
				{
					cursor = temp;
					throw new Exception("This name references a file, not a directory");
				}
			}
		}
		if(cursor.getRight() != null)
		{
			if(cursor.getRight().getName().equals(name))
			{
				cursor = cursor.getRight();
				if(cursor.getIsFile() == true)
				{
					cursor = temp;
					throw new Exception("This name references a file, not a directory");
				}
			}
		}
		if((cursor == temp) && !(cursor.getName().equals(name)))
		{
			throw new Exception("Directory not found. Could not move cursor.");
		}
		
	}
	/**
	 * Prints the current working Directory and the path from the root to the DirectoryNode.
	 * Recursively finds the target from root. Once found, the name of the node is returned
	 * and the previous node (parent) has its name appended to the front of the string
	 * until the root is the current node. Then the string is returned.
	 * @param n = The target node (DirectoryNode currently being pointed to by the cursor) - DirectoryNode
	 * @param root = The root node of the DirectoryTree - DirectoryNode
	 * @return temp = The String representation of the current path from the root to the current node - String
	 */
	public String presentWorkingDirectory(DirectoryNode n, DirectoryNode root)
	{
		if(root.getName().equals(n.getName()))
			return n.getName();
		
		if(root.getLeft() != null)
		{
			String temp = presentWorkingDirectory(n, root.getLeft());
			if(temp != null)
				return root.getName() + "/" + temp;
		}
		
		if(root.getMiddle() != null)
		{
			String temp = presentWorkingDirectory(n, root.getMiddle());
			if(temp != null)
				return root.getName() + "/" + temp;
		}
		
		if(root.getRight() != null)
		{
			String temp = presentWorkingDirectory(n, root.getRight());
			if(temp != null)
				return root.getName() + "/" + temp;
		}
		
		return null;
	}

	/**
	 * Creates a String representation of the children of the current node pointed to by the cursor. 
	 * If there are no children, then nothing is added to the String.
	 * @return msg = The String representation of the child nodes of the current DirectoryNode object - String
	 */
	public String listDirectory()
	{
		DirectoryNode temp = new DirectoryNode();
		temp = temp.clone(cursor);
		String msg = "";
		if(cursor.getLeft() != null)
		{
			msg = msg + cursor.getLeft().getName();
		}
		if(cursor.getMiddle() != null)
		{
			msg = msg + " " + cursor.getMiddle().getName();
		}
		if(cursor.getRight() != null)
		{
			msg = msg + " " + cursor.getRight().getName();
		}
		cursor = temp;
		return msg;
	}
	
	/**
	 * Recursively prints a list representation of every node in the DirectoryTree object, starting from the cursor.
	 * Indents every time the level increases. Directories are indicated by the characters <code>'|-'</code>.
	 * Files are indicated by the character <code>'-'</code>.
	 * @param cursor = Current location in the DirectoryTree that the printing will begin from - DirectoryNode
	 * @param count = Value used in order to track the level of the DirectoryTree. Used to indent the output - int
	 */
	public void printDirectoryTree(DirectoryNode cursor, int count)
	{
		if(cursor == null)
			return;
		for(int i = 0; i < count; i++)
		{
			System.out.print("    ");
		}
		if(cursor.getIsFile() == false)
			System.out.print("|- " + cursor.getName());
		else
			System.out.print("- " + cursor.getName());
		System.out.println();
		printDirectoryTree(cursor.getLeft(), (count +1));
		printDirectoryTree(cursor.getMiddle(), (count +1));
		printDirectoryTree(cursor.getRight(), (count +1));
	}
	
	/**
	 * Creates a new DirectoryNode object with the value of <code>isFile</code> set to false and
	 * the value of <code>name</code> set to the passed in value.
	 * @param name = The name of the DirectoryNode object - String
	 * @throws IllegalArgumentException = Indicates that the passed in name is invalid.
	 * @throws Exception = Indicates that the object could not be added to the current DirectoryNode object.
	 */
	public void makeDirectory(String name) throws IllegalArgumentException, Exception
	{
		if(name.contains("/") || name.contains(" "))
		{
			throw new IllegalArgumentException("Invalid name.");
		}
		DirectoryNode node = new DirectoryNode(null, null, null, name, false);
		cursor.addChild(node);
	}
	
	/**
	 * Creates a new DirectoryNode object with the value of <code>isFile</code> set to true and
	 * the value of <code>name</code> set to the passed in value.
	 * @param name = The name of the DirectoryNode object - String
	 * @throws IllegalArgumentException = Indicates that the passed in name is invalid.
	 * @throws Exception = Indicates that the object could not be added to the current DirectoryNode object.
	 */
	public void makeFile(String name) throws IllegalArgumentException, Exception
	{
		if(name.contains("/") || name.contains(" "))
		{
			throw new IllegalArgumentException("Invalid name.");
		}
		DirectoryNode node = new DirectoryNode(null, null, null, name, true);
		cursor.addChild(node);
	}
	
	/**
	 * Returns the parent node of the node the cursor is currently pointing to.
	 * @param n = The name of node which is the parent of the cursor - String
	 * @param root = The root node of the tree.
	 * @returns The parent node of where the cursor was pointing to.
	 */
	public DirectoryNode getParent(String n, DirectoryNode root)
	{
		if(root.getName().equals(n))
			return root;
		
		if(root.getLeft() != null)
		{
			DirectoryNode temp = getParent(n, root.getLeft());
			return temp;
		}
		
		if(root.getMiddle() != null)
		{
			DirectoryNode temp = getParent(n, root.getMiddle());
			return temp;
		}
		
		if(root.getRight() != null)
		{
			DirectoryNode temp = getParent(n, root.getRight());
			return temp;
		}
		
		return null;
	}
	
	public DirectoryNode find(DirectoryNode cursor, String count)
	{
		DirectoryNode temp = root;
		if(cursor.getName().equals(count))
		{
			return cursor;
		}
		if(cursor.getLeft() != null)
		{
			if(temp == root)
			{
				temp = find(cursor.getLeft(), count);
			}
		}
		if(cursor.getMiddle() != null)
		{
			if(temp == root)
			{
				temp = find(cursor.getMiddle(), count);
			}
		}
		if(cursor.getRight() != null)
		{
			if(temp == root)
			{
				temp = find(cursor.getRight(), count);
			}
		}
		return temp;
	}
}
