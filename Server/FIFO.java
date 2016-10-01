package Server;

import java.util.LinkedList;


class FIFO<E>{
	private LinkedList<E> fifoList = new LinkedList<E>();
	
	public void add(E item){
		fifoList.addLast(item);
	}
	
	public E getFirst(){
		return fifoList.pollFirst();
	}
	
	private boolean isEmpty(){
		return fifoList.isEmpty();
	}
	
	public int size(){
		return fifoList.size();
	}
	

}