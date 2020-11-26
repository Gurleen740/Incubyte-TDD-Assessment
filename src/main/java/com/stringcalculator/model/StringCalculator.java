package com.stringcalculator.model;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

public class StringCalculator {

	public int add(String numbers) throws Exception {

		if (StringUtils.isEmpty(numbers)) {

			return 0;
		}

		int[] numbersArray = Arrays.stream(numbers.split(",|\\n|;")).mapToInt(Integer::parseInt).toArray();
		String negNumbers = "" ;

		for (int num : numbersArray) {
			if (num < 0) {
				negNumbers = negNumbers.concat(String.valueOf(num));
			}

		}

		if (!StringUtils.isEmpty(negNumbers)) {
			throw new RuntimeException("negatives not allowed" + negNumbers);
		} else {
			int sumOfNumbers = IntStream.of(numbersArray).sum();
			return sumOfNumbers;
		}
	}
}
