package com.codeslashers;

class Chapter3 {

	public static void main(String[] args) {

		Runnable r1 = new Runnable(){
			public void run(){
				for (int i = 0; i <= 1000; i++) {
					System.out.println(i);
				}
			}
		};
		new Thread(r1).start();
		
		Runnable r2 = () -> {
			for (int i = 0; i <= 1000; i++) {
				System.out.println(i);
			}
		};
		new Thread(r2).start();
		
		new Thread(() -> {
			for (int i = 0; i <= 1000; i++) {
				System.out.println(i);
			}
		}).start();
		
		Validator<String> zipCodeValidator = new Validator<String>() {
			public boolean validates(String value) {
				return value.matches("[0-9]{5}-?([0-9]{4})?");
			}	
		};
		
		Validator<String> zipCodeValidatorAsLambda = 
				value -> value.matches("[0-9]{5}-?([0-9]{4})?");
		
		Runnable o = () -> {
			System.out.println("Which lambda am I?");
		};

		System.out.println(o);
		System.out.println(o.getClass());
	}
}
