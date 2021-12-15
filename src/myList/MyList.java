package myList;

public interface MyList<E> {

  int size();

  boolean add(E e);

  void add(int index, E element);

  E remove(int index);
  public void removeAll();

  E get(int index);

  E set(int index, E element);

  boolean isEmpty();

  int indexOf(E e);

  boolean contains(E e);

  Object[] toArray();

  <T> T[] toArray(T[] a);


}
