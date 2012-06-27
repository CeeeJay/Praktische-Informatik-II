
import java.awt.Graphics;
import java.util.Arrays;

class TTFNode {
	private int count;
	public int values[];
	public TTFNode parent;
	public TTFNode childNodes[];
	
	public TTFNode(TTFNode parent) {
		count = 0;
		values = new int[3];
		childNodes = new TTFNode[4];
		this.parent = parent;
	}
	
	public boolean isLeaf() {
		return (childNodes[0] == null);
	}
	
	public boolean isFull() {
		return (count >= 3);
	}
	
	
	public void incCount(){
		count=count+1;
	}
	
	public void decCount(){
		count=count-1;
	}
	
	public int getCount() {
		return count;
	}
	
	public void drawNode(Graphics g, int xPos, int yPos) {
		int value = 20;
		for (int i = 0; i < count; i++) value += Math.round((Integer.toString(values[i]).length()) * 7.3f);   
		g.drawRect(xPos-value/2, yPos-15, value, 20);
		
		String text = "";
		for (int i = 0; i < count-1; i++) text += Integer.toString(values[i])+"|";
		text += Integer.toString(values[count-1]);
		g.drawString(text, xPos+10-value/2, yPos);
	}
}

public class TTFTree {
	
	public TTFNode root;
	TTFNode cur=root;
	
	
	
	public TTFTree() {
		clear();
	}
	
	public void clear() {
		root = null;
	}
	
	public boolean isEmpty() {
		return (root == null);
	}
	
	void setValue(TTFNode node,int arraypos,int value){
		if(node==null) node=new TTFNode(node.parent);
		node.values[arraypos]=value;
		node.incCount();
	}
	
	/*********************************************************************************************************/
	/** Methoden zu Aufgabe 20 (Uebungsblatt10) **************************************************************/
	/*********************************************************************************************************/
	

	public TTFNode searchTree(int key, TTFNode node)
    {

        
        
        //Check if the node is empty... Need a better way to do this 
        //Only supposed to be true once, when we first deal with root node and 
        //this is where the 4th value is getting inserted in the wrong place. 
     
		if(isEmpty()){
			return node;
		}
            //if the key is smaller than the small key 
            //go to the left child of tree
            if (key < node.values[0])
            {
                if (node.childNodes[0] == null)
                    return node;
                //But if the left child doesn't exist return the node
                //otherwise continue searching down the tree
                else return searchTree(key, node.childNodes[0]);
            }
            //if key is bigger than the big key go to
            //the right child of the tree
            else if (key > node.values[0] && key < node.values[1])
            {
                if (node.childNodes[1] == null)
                    return node;
                
                else return searchTree(key, node.childNodes[1]);
            }
            //The last option is the key is inbetween big and small
            //so go the middle child
            
            else if (key > node.values[1] && key < node.values[2])
            {
                if (node.childNodes[2] == null)
                    return node;
                
                else return searchTree(key, node.childNodes[2]);
            }
            
            else if (key > node.values[1])
            {
                if (node.childNodes[3] == null)
                    return node;
                
                else return searchTree(key, node.childNodes[3]);
            }
        
        
        //Otherwise we don't have a tree and need to create one
                
        return node;
    }
	 
	public void insertNode(int value){
		cur = searchTree(value, root);
	
		if(isEmpty()){
			root=new TTFNode(null);
			System.out.println(root.getCount());
			setValue(root,0,value);
			cur=root;
			System.out.println(cur.getCount());
		}
		else if(cur.getCount()==1){
			countIsOne(value,cur);
		}else if(cur.getCount()==2){
			countIsTwo(value);
		}else if(cur.getCount()==3){
			System.out.println(cur.values[0]);
			split(value,cur);
		}
	}

	
	public void split(int value,TTFNode node){
		if(node.parent==null){
			TTFNode parent = new TTFNode(null);
			node.parent=parent;
			node.parent.values[0]=node.values[1];
			
			TTFNode tempNode = new TTFNode(node);
			setValue(tempNode,0,node.values[0]);
			TTFNode tempNode2=new TTFNode(cur);
			setValue(tempNode2,0,node.values[2]);
			
			node.childNodes[0]=tempNode;
			node.childNodes[2]=tempNode2;
			
			node.values[0]=node.values[1]+1;
			node.values[2]=0;
			swap(node);
			node.decCount();
	
			
			if(node.parent.values[0]>value){
				setValue(node.childNodes[0],1,value); swap(node.childNodes[0]);
			}			if(cur.parent.values[0]<value){
				setValue(node.childNodes[2],1,value); swap(node.childNodes[2]);}
			
			
			
		}else{
			//Wenn node.parent nur einen Wert im Knoten hat
			if(node.parent.values[0]!=0 &&node.parent.values[1]==0){
				if(node==node.parent.childNodes[2]){
					//If P = [p-lst, M, T], dann soll P, dass werden:  [p-lst, M, [lst, X, mlst], Y, [mrst, Z, rst]]. 
					//[lst, X, mst, Y, rst]
					setValue(node.parent,1,node.values[1]); swap(node.parent);
					
					
					TTFNode tempNode=new TTFNode(node.parent);
					tempNode.values[0]=node.values[0];
					TTFNode tempNode2=new TTFNode(node.parent);
					tempNode2.values[0]=node.values[2];
					
					node.childNodes[0]=new TTFNode(tempNode);
					node.childNodes[1]=new TTFNode(tempNode);
					
					node.childNodes[2]=new TTFNode(tempNode2);
					node.childNodes[3]=new TTFNode(tempNode2);
					
					node.parent.childNodes[2]=tempNode;
					node.parent.childNodes[3]=tempNode2;
				}else if(node==node.parent.childNodes[0]){
					//If P = [T, M, p-rst], then P becomes [[lst, X, mlst], Y, [mrst, Z, rst], M, p-rst]
					setValue(node.parent,1,node.values[1]); swap(node.parent);
					 
					
					TTFNode tempNode=new TTFNode(node.parent);
					tempNode.values[0]=node.values[0];
					TTFNode tempNode2=new TTFNode(node.parent);
					tempNode2.values[0]=node.values[2];
					
					node.childNodes[0]=new TTFNode(tempNode);
					node.childNodes[1]=new TTFNode(tempNode);
					
					node.childNodes[2]=new TTFNode(tempNode2);
					node.childNodes[3]=new TTFNode(tempNode2);
					
					node.parent.childNodes[0]=tempNode;
					node.parent.childNodes[1]=tempNode2;
					
				}
			}
		}
		
		
	}
	
	public void swap(TTFNode cur){
		int temp=0;
		for(int k=cur.getCount()-1;k>0;k--){
		for(int i=0;i<k;i++){
			if(cur.values[i]>cur.values[i+1]){
				temp=cur.values[i];
				cur.values[i]=cur.values[k];
				cur.values[k]=temp;
			}
		}}
	}
	
	public void countIsOne(int value,TTFNode cur){
		TTFNode tempNode=null;
		if(cur.isLeaf()){
			System.out.println(cur.getCount());
			//Füge Element in den Knoten ein
			setValue(cur,1,value);
			System.out.println(cur.values[0]+" \t"+cur.values[1]);
			System.out.println("++++++  "+cur.getCount());
		swap(cur);//Tausche die Elemente in die richtige Reigenfolge (i<i+1)
		System.out.println(cur.values[0]+" \t"+cur.values[1]);
		}else{
			
			tempNode=new TTFNode(cur);
			tempNode.values[0]=value;
			
			if(cur.values[0]>value){
			 cur.childNodes[0]=tempNode;
			}else{
				 cur.childNodes[1]=tempNode;
				 }
		}
	}
	
	public void countIsTwo(int value){
		TTFNode tempNode=null;
		if(cur.isLeaf()){
			System.out.println(cur.getCount());
			setValue(cur,2,value);
			swap(cur);
			System.out.println("****------------  "+cur.getCount());
		}else{
			
			tempNode=new TTFNode(cur);
			tempNode.values[0]=value;
			
			if(cur.values[0]>value){
			 cur.childNodes[0]=tempNode;
			}else if((cur.values[1]>value) &&(cur.values[0]<value)  ){
				 cur.childNodes[1]=tempNode;
			}else if(cur.values[1]<value){
				cur.childNodes[2]=tempNode;
			}
		}
	}
	
	public boolean isArrayFull(int [] a){
		if(a[0]==0){
			return false;
		}else if(a[1]==0){
			return false;
		}else if(a[2]==0){
			return false;
		}else{
		return true;
	}}

	
	/*********************************************************************************************************/
	/** Zusaetzliche Methoden zu Aufgabe 20 (Uebungsblatt10) *************************************************/
	/*********************************************************************************************************/
	
	public void drawTTFTree(Graphics g, int x, int y) {
		drawTTFTree_rek(g, root, x, y, x);
	}
	
	private void drawTTFTree_rek(Graphics g, TTFNode n, int xPos, int yPos, int middle) {
		if (n != null) {
			n.drawNode(g, xPos, yPos);
			
			int cnt = n.getCount()+1;
			middle /= cnt;
			if (cnt == 2) {
				if (n.childNodes[0] != null) {
					g.drawLine(xPos, yPos+5, xPos-middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[0], xPos-middle, yPos+50, middle);
				}
				if (n.childNodes[1] != null) {
					g.drawLine(xPos, yPos+5, xPos+middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[1], xPos+middle, yPos+50, middle);
				}
			} else if (cnt == 3) {
				if (n.childNodes[0] != null) {
					g.drawLine(xPos, yPos+5, xPos-2*middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[0], xPos-2*middle, yPos+50, middle);
				}
				if (n.childNodes[1] != null) {
					g.drawLine(xPos, yPos+5, xPos, yPos+35);
					drawTTFTree_rek(g, n.childNodes[1], xPos, yPos+50, middle);
				}
				if (n.childNodes[2] != null) {
					g.drawLine(xPos, yPos+5, xPos+2*middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[2], xPos+2*middle, yPos+50, middle);
				}
			} else if (cnt == 4) {
				if (n.childNodes[0] != null) {
					g.drawLine(xPos, yPos+5, xPos-3*middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[0], xPos-3*middle, yPos+50, middle);
				}
				if (n.childNodes[1] != null) {
					g.drawLine(xPos, yPos+5, xPos-middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[1], xPos-middle, yPos+50, middle);
				}
				if (n.childNodes[2] != null) {
					g.drawLine(xPos, yPos+5, xPos+middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[2], xPos+middle, yPos+50, middle);
				}
				if (n.childNodes[3] != null) {
					g.drawLine(xPos, yPos+5, xPos+3*middle, yPos+35);
					drawTTFTree_rek(g, n.childNodes[3], xPos+3*middle, yPos+50, middle);
				}
			}
		}
	}
}
