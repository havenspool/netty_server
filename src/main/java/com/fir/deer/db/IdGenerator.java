package com.fir.deer.db;

/**
 *
 *
 *  ID generator
 *
 * Created by havens on 15-8-12.
 */
public interface IdGenerator {

    String generateStringId();

    Long generateLongId();
}
