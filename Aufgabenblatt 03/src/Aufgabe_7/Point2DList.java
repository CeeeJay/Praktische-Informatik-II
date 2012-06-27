package Aufgabe_7;

public class Point2DList {
	private Point2DElement first, last;

	public Point2DList() {
		last = new Point2DElement(null, first, null);
		first = new Point2DElement(null, null, last);
	}
	
	public boolean isEmpty(){
		if (first.getAfter() == last) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getLength(){
		Point2DElement iterator = first.getAfter();
		int counter = 0;
		while (iterator != last) {
			counter++;
			iterator = iterator.getAfter();
		}
		return counter;
	}
	
	public Point2DElement getElementAt(int index){
		Point2DElement iterator = first.getAfter();
		if (index > this.getLength()) {
			return last.getBefore();
		} else {
			for (int i = 0; i < index; i++) {
				iterator = iterator.getAfter();
			}
		}
		return iterator;
	}
	
	public void add(Point2D pnt){
		last.getBefore().setAfter(new Point2DElement(pnt, last.getBefore(), last));
	}
	
	public boolean removeElementAt(int index){
		if(index < this.getLength())return false;
		Point2DElement delete = this.getElementAt(index);
		delete.getBefore().setAfter(delete.getAfter());
		return true;
	}
	
	private void change(int index1, int index2){
		Point2DElement element1 = this.getElementAt(index1);
		Point2DElement element2 = this.getElementAt(index2);
		Point2DElement before = element1.getBefore();
		Point2DElement after = element1.getAfter();
		element1.setBefore(element2.getAfter());
		element2.setBefore(before);
		element2.setAfter(after);
		element1.getBefore().setAfter(element1);
		element2.getBefore().setAfter(element2);
		element1.getAfter().setBefore(element1);
		element2.getAfter().setBefore(element2);
	}
	
	public void sort(){
		int step = this.getLength();
		boolean changed;
		do {
			changed = false;
			if(step > 1) step = (int) (step/1.3);
			for (int i = 0; i < this.getLength() - step; i++) {
				if (this.getElementAt(i).getElement().getX() > this.getElementAt(i + step).getElement().getX()) {
					this.change(i, i + step);
					changed = true;
				}else{
					if (this.getElementAt(i).getElement().getX() == this.getElementAt(i + step).getElement().getX()) {
						if (this.getElementAt(i).getElement().getY() > this.getElementAt(i + step).getElement().getY()) {
							this.change(i, i + step);
							changed = true;
						}
					}
				}
			}
		} while (changed == true || step > 1);
	}
}
