package com.nutrition.nutritionservice.converter;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Converter;
import com.google.common.collect.Lists;

/**
 * 封装一些转换集合和逆向操作
 *
 * @author heng.liu
 * @since 2020/12/22
 */
public abstract class BaseConverter<A, B> extends Converter<A, B> {

    public final List<B> convertAllToList(final Iterable<? extends A> fromIterable) {
        if (fromIterable == null) {
            return Collections.emptyList();
        }
        return Lists.newArrayList(convertAll(fromIterable));
    }

    public final A reverseConvert(final B b) {
        return reverse().convert(b);
    }

    public final Iterable<A> reverseConvertAll(final Iterable<? extends B> fromIterable) {
        if (fromIterable == null) {
            return Collections.emptyList();
        }
        return reverse().convertAll(fromIterable);
    }

    public final List<A> reverseConvertAllToList(final Iterable<? extends B> fromIterable) {
        if (fromIterable == null) {
            return Collections.emptyList();
        }
        return Lists.newArrayList(reverse().convertAll(fromIterable));
    }
}
