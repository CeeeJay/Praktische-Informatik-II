
public class Point2DElement {
	private Point2D ins;
	private Point2DElement next;
	private Point2DElement prev;
	
	public Point2DElement(Point2D ins, Point2DElement next, Point2DElement prev){
		this.ins = ins;
		this.next = next;
		this.prev = prev;
	}

	public Point2D getIns() {
		return ins;
	}

	public void setIns(Point2D ins) {
		this.ins = ins;
	}

	public Point2DElement getNext() {
		return next;
	}

	public void setNext(Point2DElement next) {
		this.next = next;
	}

	public Point2DElement getPrev() {
		return prev;
	}

	public void setPrev(Point2DElement prev) {
		this.prev = prev;
	}
	
}
