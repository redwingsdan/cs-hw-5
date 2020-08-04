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
 * This class creates an object of the type DirectoryNode. This class gives each DirectoryNode object a total of
 * 3 children (left, middle and right). Each node is given a name to identify it and a status as a file or a directory.
 * Nodes which are files cannot have children, while nodes which are directories can. A method called <code>addChild</code>
 * is used to add nodes as the children of previously created nodes. Two other methods are present: one called <code>clone</code> 
 * which creates a copy of the current DirectoryNode object, and a second called <code>equals</code> which checks to see
 * if the current DirectoryNode object's values are all equivalent to a passed in DirectoryNode object.
 */
public class DirectoryNode {

	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private String name;
	private boolean isFile;
	
	public DirectoryNode()
	{
		left = null;
		middle = null;
		right = null;
		name = null;
		isFile = false;
	}
	
	/**
	 * Creates an instance of <code>DirectoryNode</code> - Constructor
	 * @param left = The left child of a node - DirectoryNode
	 * @param middle = The middle child of a node - DirectoryNode
	 * @param right = The right child of a node - DirectoryNode
	 * @param name = The name of particular node - String
	 * @param isFile = The status of whether or not the node is a file - Boolean
	 */
	public DirectoryNode(DirectoryNode left, DirectoryNode middle, DirectoryNode right, String name, Boolean isFile)
	{
		this.left = left;
		this.middle = middle;
		this.right = right;
		this.name = name;
		this.isFile = isFile;
	}
	
	/**
	 * Sets the value of <code>left</code> to the DirectoryNode value passed in.
	 * @param left = The left child of a node - DirectoryNode
	 */
	public void setLeft(DirectoryNode left)
	{
		this.left = left;
	}
	
	/**
	 * Sets the value of <code>middle</code> to the DirectoryNode value passed in.
	 * @param middle = The middle child of a node - DirectoryNode
	 */
	public void setMiddle(DirectoryNode middle)
	{
		this.middle = middle;
	}
	
	/**
	 * Sets the value of <code>right</code> to the DirectoryNode value passed in.
	 * @param right = The right child of a node - DirectoryNode
	 */
	public void setRight(DirectoryNode right)
	{
		this.right = right;
	}
	
	/**
	 * Sets the value of <code>name</code> to the String value passed in.
	 * @param name = The name of particular node - String
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Sets the value of <code>isFile</code> to the Boolean value passed in.
	 * @param isFile = The status of whether or not the node is a file - Boolean
	 */
	public void setIsFile(Boolean isFile)
	{
		this.isFile = isFile;
	}
	
	/**
	 * Returns the value of <code>left</code> - DirectoryNode
	 * @return left - DirectoryNode
	 */
	public DirectoryNode getLeft()
	{
		return left;
	}
	
	/**
	 * Returns the value of <code>middle</code> - DirectoryNode
	 * @return middle - DirectoryNode
	 */
	public DirectoryNode getMiddle()
	{
		return middle;
	}
	
	/**
	 * Returns the value of <code>right</code> - DirectoryNode
	 * @return right - DirectoryNode
	 */
	public DirectoryNode getRight()
	{
		return right;
	}
	
	/**
	 * Returns the value of <code>name</code> - String
	 * @return name - String
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the value of <code>isFile</code> - Boolean
	 * @return isFile - Boolean
	 */
	public Boolean getIsFile()
	{
		return isFile;
	}
	
	/**
	 * Creates a new child for the current <code>DirectoryNode</code> object. Starts at the leftmost child
	 * to see if there is room to add the child and moves right.
	 * @param newChild - The new node which will be added as the child of the current node
	 * @throws Exception - Indicates that the current node is a file and cannot have children.
	 * @throws Exception - Indicates that the current directory is full and a child cannot be added.
	 */
	public void addChild(DirectoryNode newChild) throws Exception
	{
		if(isFile == true)
		{
			throw new Exception("This is a file, not a directory. Cannot add child.");
		}
		else
		{
			if(left == null)
			{
				left = newChild;
			}
			else if(middle == null)
			{
				middle = newChild;
			}
			else if(right == null)
			{
				right = newChild;
			}
			else
			{
				throw new Exception("Current directory is full. Cannot add child.");
			}
		}
	}	
	
    /**
     * Generates a copy of this <code>DirectoryNode</code> object.
     * @return
     *    The return value is a copy of this <code>DirectoryNode</code>.
     */
	public DirectoryNode clone(DirectoryNode obj)
	{
		DirectoryNode n = new DirectoryNode();
		n.setIsFile(obj.getIsFile());
		n.setLeft(obj.getLeft());
		n.setRight(obj.getRight());
		n.setMiddle(obj.getMiddle());
		n.setName(obj.getName());
		return n;
	}
	
	/**
     * Compares this <code>DirectoryNode</code> to another object for equality.
     * @param obj
     *    an object to which this <code>DirectoryNode</code> is being compared with
     * @return
     *    A return value of <code>true</code> indicates that obj refers to a
     *    <code>DirectoryNode</code> object with the same elements as this <code>DirectoryNode</code>.
     *    Otherwise, the return value is <code>false</code>.
     */
	public Boolean equals(DirectoryNode obj)
	{
		if((this.left == obj.getLeft()) && (this.right == obj.getRight()) && (this.middle == obj.getMiddle()) && (this.isFile == obj.getIsFile()) && (this.name.equals(obj.getName())))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
