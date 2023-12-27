package myArrayList.interfice.impl;

import myArrayList.exception.ElementNotFoundException;
import myArrayList.exception.ExitStorageLengthException;
import myArrayList.exception.NullPointException;
import myArrayList.interfice.StringList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringListImpl implements StringList {

    List<String> test = new ArrayList<>();
    private String[] storage;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;

    public StringListImpl(int initCapacity) {
        if (initCapacity > 0) {
            this.storage = new String[initCapacity];
        } else if (initCapacity == 0) {
            this.storage = new String[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("передан не правильный размер " + initCapacity);
        }
    }

    public StringListImpl(String[] storage) {
        if ((size = storage.length) != 0) {
            this.storage = storage;
        } else {
            this.storage = new String[DEFAULT_CAPACITY];
        }
    }

    public StringListImpl() {
        this.storage = new String[DEFAULT_CAPACITY];
    }


//    увеличивает размер массива
    private String[] grow(int minGrowth) {
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
    private String removeElement(String[] array, int i) {
        int newSize = size - 1;

        String oldValue = array[i];

        if (newSize > i) {
            System.arraycopy(array, i + 1, array, i, newSize - i);
            size -= 1;
        }

        array[size = newSize] = null;

        return oldValue;
    }


    @Override
    public String add(String item) {
        validateItem(item);

        if (size == storage.length) {
            storage = grow(size + 10);
        }

        storage[size] = item;
        size += 1;
        return storage[size - 1];
    }

    @Override
    public String add(int index, String item) {
        checkRangeIndex(index);
        validateItem(item);

        final int s = size;
        String[] storage = this.storage;

        if (index == s) {
            add(item);
        }

        System.arraycopy(storage, index, storage, index + 1, s - index);
        storage[index] = item;
        size = s + 1;

        return storage[index];
    }

    @Override
    public String set(int index, String item) {
        validateItem(item);
        checkRangeIndex(index);

        storage[index] = item;
        return storage[index];
    }


//    Метод для удаления элемента
//    Находить индекс элемента и вызывает метод removeElement
    @Override
    public String remove(String item) {
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
    public String remove(int index) {
        checkRangeIndex(index);

        return removeElement(storage, index);
    }

    @Override
    public boolean contains(String item) {
        validateItem(item);

        return indexOf(item) >= 0;

    }

    @Override
    public int indexOf(String item) {
        validateItem(item);

        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }

        return -1;

    }

    @Override
    public int lastIndexOf(String item) {
        validateItem(item);

        for (int i = size - 1; i >=0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String get(int index) {
        checkRangeIndex(index);

        if (storage[index] != null) {
            return storage[index];
        }

        throw new ElementNotFoundException("Объект не найден");
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullPointException("Передан Null");
        }

        String[] otherStorage = otherList.toArray();

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
    public String[] toArray() {
        return Arrays.copyOf(storage, size);
    }


//    Выбрасывает исключение если передан пустой item
    private void validateItem(String item) {
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
