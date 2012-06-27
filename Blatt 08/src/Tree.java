//Abgabe von Ewald Bayer, Jan-Philipp Bamberger, Leon Peulings und Stephan Malzkorn

import java.util.Stack;



public class Tree {

	Tree child1;
	Tree child2;
	private String thisObject;

	public Tree(String object){
		this.child1 = null;
		this.child2 = null;
		this.thisObject = object;
	}

	public Tree(String object, Tree child1, Tree child2){
		this.child1 = child1;
		this.child2 = child2;
		this.thisObject = object;
	}

	public int getOptreeDepth(){
		if(child1==null||child2==null){
			return 1;
		}else{
			int add = 0;
			int temp;
			if((temp=child1.getOptreeDepth()) > add)
				add = temp;
			if((temp=child2.getOptreeDepth()) > add)
				add = temp;
			return add+1;
		}
	}

	public String getObject(){
		return thisObject;
	}

	public String getInorder(){
		Stack<Tree> trees = new Stack<Tree>();
		trees.push(new Tree(""));
		Tree currentTree = this;
		String returnString = "";
		while(!trees.isEmpty()){
			while(currentTree.child1 != null){
				trees.push(currentTree);
				currentTree = currentTree.child1;
			}
			returnString += currentTree.thisObject + " ";
			currentTree = trees.pop();
			returnString += currentTree.thisObject + " ";
			currentTree = currentTree.child2;
		}
		return returnString;
	}

	public String getPreorder(){
		Stack<Tree> trees = new Stack<Tree>();
		trees.push(new Tree(""));
		Tree currentTree = this;
		String returnString = "";
		while(!trees.isEmpty()){
			while(currentTree.child1 != null){
				trees.push(currentTree);
				returnString += currentTree.thisObject + " ";
				currentTree = currentTree.child1;
			}
			returnString += currentTree.thisObject + " ";
			currentTree = trees.pop().child2;
		}
		return returnString;
	}

	public String getPostorder(){
		Stack<Tree> trees = new Stack<Tree>();
		trees.push(new Tree(""));
		Stack<String> opLater = new Stack<String>();
		String returnString = "";
		Tree currentTree = this;
		boolean isChild2 = false;
		while(!trees.isEmpty()){
			while(currentTree.child1 != null){
				switch (currentTree.thisObject.charAt(0)) {
				case '+':
				case '-':
				case '*':
				case '/':
				case '%':
					opLater.push(currentTree.thisObject);
					break;

				default:						
					break;
				}
				trees.push(currentTree);
				currentTree = currentTree.child1;
				isChild2 = false;
			}
			if(isChild2)
				returnString += opLater.pop() + " ";
			returnString += currentTree.thisObject + " ";
			currentTree = trees.pop().child2;
			isChild2 = true;
		}
		while(!opLater.isEmpty())
			returnString += opLater.pop() + " ";
		return returnString;
	}

}
