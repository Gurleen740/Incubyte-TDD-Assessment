package com.stringcalculator.model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

public class StringCalculator {

	public static int count = 0;

	public int add(String numbers) throws RuntimeException {

		count++;

		int[] numbersArray;

		if (StringUtils.isEmpty(numbers)) {

			return 0;
		}
		Matcher m = Pattern.compile("(.*)\\\\n(.*)")
				.matcher(numbers.replaceAll("[// \\[ \\] ]", "").replaceAll("[\\*]", ","));
		if (m.find()) {
			String delimiter = m.group(1);
			String numberFromMatcher = m.group(2);
			numbersArray = Arrays.stream(numberFromMatcher.split(delimiter)).mapToInt(Integer::parseInt).toArray();

		} else {
			numbersArray = Arrays.stream(numbers.split(",|\\n")).mapToInt(Integer::parseInt).toArray();
		}
		String negNumbers = "";
		int index = 0;

		for (int num : numbersArray) {
			if (num < 0) {
				negNumbers = negNumbers.concat(String.valueOf(num));
			} else if (num > 1000) {
				numbersArray[index] = 0;

			}
			index++;
		}

		if (!StringUtils.isEmpty(negNumbers)) {
			throw new RuntimeException("negatives not allowed" + negNumbers);
		} else {
			int sumOfNumbers = IntStream.of(numbersArray).sum();
			return sumOfNumbers;
		}
	}

	public static int getCalledCount() {

		return count;

	}

}
