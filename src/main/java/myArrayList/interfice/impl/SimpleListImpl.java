package myArrayList.interfice.impl;

import myArrayList.exception.ElementNotFoundException;
import myArrayList.exception.ExitStorageLengthException;
import myArrayList.exception.NullPointException;
import myArrayList.interfice.SimpleList;

import java.util.Arrays;

public class SimpleListImpl<E> implements SimpleList<E> {

    private Object[] storage;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;

    public SimpleListImpl(int initCapacity) {
        if (initCapacity > 0) {
            this.storage = new Object[initCapacity];
        } else if (initCapacity == 0) {
            this.storage = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("передан не правильный размер " + initCapacity);
        }
    }

    public SimpleListImpl(String[] storage) {
        if ((size = storage.length) != 0) {
            this.storage = storage;
        } else {
            this.storage = new Object[DEFAULT_CAPACITY];
        }
    }

    public SimpleListImpl() {
        this.storage = new String[DEFAULT_CAPACITY];
    }


    @SuppressWarnings("unchecked")
    private E getElementInE(int index) {
        return (E) storage[index];
    }


//    увеличивает размер массива
    private Object[] grow(int minGrowth) {
        int oldCapacity = storage.length;
        if (oldCapacity > 0) {
            int newCapacity = newLength(oldCapacity, minGrowth);
            return storage = Arrays.copyOf(storage, newCapacity);
        } else {
            return storage = new String[Math.max(DEFAULT_CAPACITY, minGrowth)];
        }
    }

//  вычитывает новый размер массива
    private int newLength(int oldCapacity, int minGrowth) {
        int prefCapacity = (oldCapacity * 3) / 2 + 1;
        int minCapacity = oldCapacity + minGrowth;
        return Math.max(prefCapacity, minCapacity);
    }


    //  Метод для удаления элемента по индексу
//    Возвращает удаленный элемент
    private E removeElement(Object[] array, int i) {
        int newSize = size - 1;

        @SuppressWarnings("unchecked")
        E oldValue = (E)array[i];

        if (newSize > i) {
            System.arraycopy(array, i + 1, array, i, newSize - i);
            size -= 1;
        }

        array[size = newSize] = null;

        return oldValue;
    }



    @Override
    public E add(E item) {
        validateItem(item);

        if (size == storage.length) {
            storage = grow(size + 10);
        }

        storage[size] = item;
        size += 1;
        return item;
    }

    @Override
    public E add(int index, E    item) {
        checkRangeIndex(index);
        validateItem(item);

        final int s = size;
        Object[] storage = this.storage;

        if (index == s) {
            add(item);
        }

        System.arraycopy(storage, index, storage, index + 1, s - index);
        storage[index] = item;
        size = s + 1;

        return getElementInE(index);
    }

    @Override
    public E set(int index, E item) {
        validateItem(item);
        checkRangeIndex(index);

        storage[index] = item;
        return getElementInE(index);
    }


//    Метод для удаления элемента
//    Находить индекс элемента и вызывает метод removeElement
    @Override
    public E remove(E item) {
        validateItem(item);

        int index = indexOf(item);

        if (index >= 0) {
            return removeElement(storage, index);
        } else {
            throw new ElementNotFoundException("Элемент не найден");
        }

    }


    //    Метод для удаления элемента
//    вызывает метод removeElement для удаления элемента
    @Override
    public E remove(int index) {
        checkRangeIndex(index);

        return removeElement(storage, index);
    }

    @Override
    public boolean contains(E item) {
        validateItem(item);

        return indexOf(item) >= 0;

    }

    @Override
    public int indexOf(E item) {
        validateItem(item);

        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }

        return -1;

    }

    @Override
    public int lastIndexOf(E item) {
        validateItem(item);

        for (int i = size - 1; i >=0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public E get(int index) {
        checkRangeIndex(index);

        if (storage[index] != null) {
            return getElementInE(index);
        }

        throw new ElementNotFoundException("Объект не найден");
    }

    @Override
    public boolean equals(SimpleList<E> otherList) {
        if (otherList == null) {
            throw new NullPointException("Передан Null");
        }

        Object[] otherStorage = otherList.toArray();

        if (otherList == this) {
            return true;
        }

        if (this.size == otherList.size()) {
            for (int i = 0; i < size; i++) {
                if (!this.storage[i].equals(otherStorage[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return storage == null;
    }

    @Override
    public void clear() {
        size = 0;
        storage = new String[DEFAULT_CAPACITY];
    }

    @Override
    public E[] toArray() {
        @SuppressWarnings("unchecked")
        E[] tmp = (E[]) Arrays.copyOf(storage, size);
        return tmp;
    }


//    Выбрасывает исключение если передан пустой item
    private void validateItem(E item) {
        if (item == null) {
            throw new NullPointException("Передан пустой объект");
        }
    }

//    Выбрасывает исключение если index выходить за приделы массива
    private void checkRangeIndex(int index) {
        if (index > size || index < 0) {
            throw new ExitStorageLengthException("Индекс выходит за пределы фактической длины массива или элемент не найден");
        }
    }



}
