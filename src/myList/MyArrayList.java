package myList;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<E> implements MyList<E> {

  transient Object[] elementData;
  private int size = 0;
  private static final int DEFAULT_CAPACITY = 10;
  private static final Object[] EMPTY_ELEMENT_DATA = {};
  private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
  private int modCount = 0;


  public MyArrayList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
      this.elementData = EMPTY_ELEMENT_DATA;
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }
  }

  public MyArrayList() {
    this.elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
  }

  public MyArrayList(MyList<? extends E> c) {
    Object[] a = c.toArray();
    if ((size = a.length) != 0) {
      if (c.getClass() == MyArrayList.class) {
        elementData = a;
      } else {
        elementData = Arrays.copyOf(a, size, Object[].class);
      }
    } else {
      elementData = EMPTY_ELEMENT_DATA;
    }
  }

  private Object[] grow(int minCapacity) {
    return elementData = Arrays.copyOf(elementData,
        newCapacity(minCapacity));
  }

  private Object[] grow() {
    return grow(size + 1);
  }

  private int newCapacity(int minCapacity) {

    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);

    if (newCapacity - minCapacity <= 0) {
      if (elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
        return Math.max(DEFAULT_CAPACITY, minCapacity);
      }
      if (minCapacity < 0) {
        throw new OutOfMemoryError();
      }
      return minCapacity;
    }
    return (newCapacity - MAX_ARRAY_SIZE <= 0) ? newCapacity : hugeCapacity(minCapacity);
  }

  private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) {
      throw new OutOfMemoryError();
    }
    return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
  }


  @SuppressWarnings("unchecked")
  E elementData(int index) {
    return (E) elementData[index];
  }

  @SuppressWarnings("unchecked")
  static <E> E elementAt(Object[] es, int index) {
    return (E) es[index];
  }

  @Override
  public int size() {
    return size;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean add(Object e) {
    modCount++;
    add((E) e, elementData, size);
    return true;
  }

  private void add(E e, Object[] elementData, int s) {
    if (s == elementData.length) {
      elementData = grow();
    }
    elementData[s] = e;
    size = s + 1;
  }

  @Override
  public void add(int index, Object element) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    modCount++;
    final int s;
    Object[] elementData;
    if ((s = size) == (elementData = this.elementData).length) {
      elementData = grow();
    }
    System.arraycopy(elementData, index, elementData, index + 1, s - index);
    elementData[index] = element;
    size = s + 1;

  }

  @Override
  public E get(int index) {
    Objects.checkIndex(index, size);
    return elementData(index);
  }

  @Override
  public E set(int index, Object element) {
    Objects.checkIndex(index, size);
    E oldValue = elementData(index);
    elementData[index] = element;
    return oldValue;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E remove(int index) {
    Objects.checkIndex(index, size);
    final Object[] es = elementData;

    E oldValue = (E) es[index];
    fastRemove(es, index);
    return oldValue;
  }

  @Override
  public void removeAll() {
   elementData = EMPTY_ELEMENT_DATA;
   size = 0;
  }

  private void fastRemove(Object[] es, int i) {
    modCount++;
    final int newSize;
    if ((newSize = size - 1) > i) {
      System.arraycopy(es, i + 1, es, i, newSize - i);
    }
    es[size = newSize] = null;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int indexOf(E e) {
    return indexOfRange(e, 0, size);
  }

  @Override
  public boolean contains( E e) {
    return indexOf(e) >= 0;
  }

  private int indexOfRange(Object o, int start, int end) {
    Object[] es = elementData;
    if (o == null) {
      for (int i = start; i < end; i++) {
        if (es[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = start; i < end; i++) {
        if (o.equals(es[i])) {
          return i;
        }
      }
    }
    return -1;
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elementData, size);
  }

  @Override
  public Object[] toArray(Object[] a) {
    if (a.length < size) {
      return Arrays.copyOf(elementData, size, a.getClass());
    }
    System.arraycopy(elementData, 0, a, 0, size);
    if (a.length > size) {
      a[size] = null;
    }
    return a;
  }

  @Override
  public String toString() {
    int nullCount = 0;
    StringBuilder sb = new StringBuilder();

    if (isEmpty()) {
      return "[]";
    }

    for (int i = elementData.length - 1; i > 0; i--) {
      if (elementData(i) == null) {
        nullCount++;
      } else {
        break;
      }
    }

    for (int i = 0; i < elementData.length - (nullCount + 1); i++) {
      sb.append(elementData(i)).append(",");
    }
    sb.append(elementData(elementData.length - nullCount - 1)).append("]");
    return "[" + sb;
  }
}
