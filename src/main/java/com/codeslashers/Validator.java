package com.codeslashers;

@FunctionalInterface
interface Validator<T> {
	
	boolean validates(T t);
}