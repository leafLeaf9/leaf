package com.gousade.util;


import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author woxigousade
 * @date 2021/6/11
 */
public class CollectionUtil {

    /**
     * 将集合拆分为loopCount组，每组splitSize个元素，返回结果集
     *
     * @param collection collection
     * @param loopCount  the max size you need
     * @param splitSize  the size of per collection in result
     * @param <T>        <T>
     * @return List<Collection < T>>
     * @see cn.hutool.core.collection.CollUtil
     */
    public static <T> List<Collection<T>> splitList(Collection<T> collection, int loopCount, int splitSize) {
        if (CollectionUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return Stream.iterate(0, i -> i + 1)
                .limit(loopCount)
                .parallel()
                .map(t -> collection.parallelStream().skip((long) t * splitSize).limit(splitSize).collect(Collectors.toList()))
                .filter(b -> !b.isEmpty())
                .collect(Collectors.toList());
    }

    public static <T> List<List<T>> split(Collection<T> collection, int size) {
        final List<List<T>> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(collection)) {
            return result;
        }
        ArrayList<T> subList = new ArrayList<>(size);
        for (T t : collection) {
            if (subList.size() >= size) {
                result.add(subList);
                subList = new ArrayList<>(size);
            }
            subList.add(t);
        }
        result.add(subList);
        return result;
    }
}
