package com.lamarsan.netty.official;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * className: TimeEntity
 * description: TODO
 *
 * @author lamar
 * @version 1.0
 * @date 2020/2/7 0:30
 */
public class TimeEntity {
    private final long value;

    public TimeEntity() {
        this(System.currentTimeMillis());
    }

    public TimeEntity(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(value);
    }
}
