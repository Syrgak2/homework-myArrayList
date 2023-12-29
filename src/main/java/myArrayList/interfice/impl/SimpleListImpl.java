package myArrayList.interfice.impl;

import myArrayList.exception.ElementNotFoundException;
import myArrayList.exception.ExitStorageLengthException;
import myArrayList.exception.NullPointException;
import myArrayList.interfice.SimpleList;

import java.util.Arrays;

public class SimpleListImpl<E> implements SimpleList<E> {

    private E[] storage;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;

    public SimpleListImpl(int initCapacity) {
        if (initCapacity > 0) {
            this.storage = (E[]) new Object[initCapacity];
        } else if (initCapacity == 0) {
            this.storage = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("передан не правильный размер " + initCapacity);
        }
    }

    public SimpleListImpl(E[] storage) {
        if ((size = storage.length) != 0) {
            this.storage =storage;
        } else {
            this.storage = (E[]) new Object[DEFAULT_CAPACITY];
        }
    }

    public SimpleListImpl() {
        this.storage = (E[]) new Object[DEFAULT_CAPACITY];
    }


    private E getElementInE(int index) {
        return storage[index];
    }


//    увеличивает размер массива
    private E[] grow() {
        int oldCapacity = storage.length;
        if (oldCapacity > 0) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;

            return storage = Arrays.copyOf(storage, newCapacity);
        } else {
            return storage = (E[]) new Object[DEFAULT_CAPACITY];
        }
    }



    //  Метод для удаления элемента по индексу
//    Возвращает удаленный элемент
    private E removeElement(E[] array, int i) {
        int newSize = size - 1;

        E oldValue = array[i];

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
            storage = (E[]) grow();
        }

        storage[size] = item;
        size += 1;
        return item;
    }

    @Override
    public E add(int index, E item) {
        checkRangeIndex(index);
        validateItem(item);

        if (size == storage.length) {
            storage = grow();
        }
        if (index == size) {
            storage[size ++] = item;

        }

        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size += 1;

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
    public boolean contains(int item) {
        E[] arr =  storage;

        if (size == 0) {
            return false;
        }

        quickSort(arr, 0, size - 1);
        return binarySearch(arr, item) >= 0;
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
        storage = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public E[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    public int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minElementIndex]) {
                    minElementIndex = j;
                }
            }

            int tmp = array[i];
            array[i] = array[minElementIndex];
            array[minElementIndex] = tmp;
        }
        return array;
    }

    private void quickSort(E[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);

        }
    }

    private int partition(E[] arr, int begin, int end) {
        Integer pivot = (Integer) arr[end];

        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if ((Integer) arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);

        return i + 1;
    }

    private void swapElements(E[] arr, Integer left, Integer right) {
        E tmp =  arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }


    private int binarySearch(E[] arr, int item) {
        int min = 0;
        int max = size - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == (Integer) arr[mid]) {
                return mid;
            }

            if (item < (Integer) arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
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
