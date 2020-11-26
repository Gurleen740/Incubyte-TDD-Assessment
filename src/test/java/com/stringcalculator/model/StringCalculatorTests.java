package com.stringcalculator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StringCalculatorTests {

	@InjectMocks
	StringCalculator stringCalculator;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@AfterAll
	public static void getTestCount() throws Exception {
		StringCalculator.getCalledCount();
	}

	@Test
	public void addTestForEmptyValues() throws Exception {
		assertEquals(stringCalculator.add(""), 0);
	}

	@Test
	public void addTest() throws Exception {
		assertEquals(stringCalculator.add("6,5"), 11);
	}

	@Test
	public void addTest01() throws Exception {
		assertEquals(stringCalculator.add("100,100,100,100,100,100"), 600);
	}

	@Test
	public void addForNewLinesTest() throws Exception {
		assertEquals(stringCalculator.add("1\n2,3\n1"), 7);
	}

	@Test
	public void negativeNumTest() throws Exception {

		Exception exception = assertThrows(RuntimeException.class, () -> {
			stringCalculator.add("-6,5");
		});
		String expectedMessage = "negatives not allowed-6";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void negativeMultipleNumTest() throws Exception {

		Exception exception = assertThrows(RuntimeException.class, () -> {
			stringCalculator.add("-6,5,-8,9");
		});
		String expectedMessage = "negatives not allowed-6-8";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void diffDelimiterTest() throws Exception {
		assertEquals(stringCalculator.add("//;\\n1;2"), 3);
	}

	@Test
	public void numberBiggerThanThousandTest() throws Exception {
		assertEquals(stringCalculator.add("2,1001"), 2);
	}
	

	@Test
	public void delimiterOfAnyLengthTest() throws Exception {
		assertEquals(stringCalculator.add("//[***]\\n1***2***3"), 6);
	}

}
