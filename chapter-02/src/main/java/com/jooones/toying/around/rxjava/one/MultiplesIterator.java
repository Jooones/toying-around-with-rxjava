package com.jooones.toying.around.rxjava.one;

import java.util.Iterator;

public class MultiplesIterator implements Iterator<Integer> {
    private Integer current = 0;
    private Integer factor;

    public MultiplesIterator(int factor) {
        this.factor = factor;
    }

    public boolean hasNext() {
        return current<=Integer.MAX_VALUE-factor;
    }

    @Override
    public Integer next() {
        int result = current;
        current = current+factor;
        return result;
    }
}
