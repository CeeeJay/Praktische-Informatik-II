
public class Point2DListe {
	private Point2DElement first;
	private Point2DElement last;

	public Point2DListe(){
		first = new Point2DElement(null, null, null);
		last = new Point2DElement(null, null, first);
		first.setNext(last);
	}

	public boolean isEmpty(){
		return first.getNext().getNext() == null;
	}

	public int getLength(){
		int counter = -1;
		Point2DElement current = first;
		while(current.getNext() != null){
			counter++;
			current = current.getNext();
		}
		return counter;
	}

	public Point2DElement getElementAt(int index){
		if(isEmpty()||index >= getLength()||index < 0)
			return null;
		Point2DElement returnElement = first;
		for (int i = -1; i < index; i++) 
			returnElement = returnElement.getNext();
		return returnElement;
	}

	public void add(Point2D pnt){
		Point2DElement newElement = new Point2DElement(pnt, last, last.getPrev());
		last.getPrev().setNext(newElement);
		last.setPrev(newElement);
	}

	public boolean removeElementAt(int index){
		if(isEmpty()||index >= getLength()||index < 0)
			return false;
		Point2DElement removeElement = getElementAt(index);
		removeElement.getPrev().setNext(removeElement.getNext());
		removeElement.getNext().setPrev(removeElement.getPrev());
		return true;
	}

	public void sort(){
		int step = getLength();
		boolean switched;
		do{
			switched = false;
			if(step > 1)
				step = (int)(step/1.3);
			for (int i = 0; i < getLength() - step; i++) {
				if(getElementAt(i).getIns().x > getElementAt(i + step).getIns().x){
					switchElements(i, i+step);
					switched = true;
				}else if(getElementAt(i).getIns().x == getElementAt(i + step).getIns().x){
					if(getElementAt(i).getIns().y > getElementAt(i + step).getIns().y){
						switchElements(i, i+step);
						switched = true;
					}
				}
			}
		}while(switched||step > 1);
	}

	private void switchElements(int a, int b){
		Point2DElement elementA = getElementAt(a);
		Point2DElement elementB = getElementAt(b);
		if(Math.abs(a-b) > 1){
			Point2DElement tempBP = elementB.getPrev();
			Point2DElement tempBN = elementB.getNext();
			elementA.getPrev().setNext(elementB);
			elementA.getNext().setPrev(elementB);
			elementB.getPrev().setNext(elementA);
			elementB.getNext().setPrev(elementA);
			elementB.setPrev(elementA.getPrev());
			elementB.setNext(elementA.getNext());
			elementA.setPrev(tempBP);
			elementA.setNext(tempBN);
		}else{
			elementA.setNext(elementB.getNext());
			elementB.setPrev(elementA.getPrev());
			elementB.setNext(elementA);
			elementA.setPrev(elementB);
			elementA.getNext().setPrev(elementA);
			elementA.getPrev().setNext(elementA);
			elementB.getNext().setPrev(elementB);
			elementB.getPrev().setNext(elementB);
		}
	}
}
