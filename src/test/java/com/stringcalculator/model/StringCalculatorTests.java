package com.stringcalculator.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StringCalculatorTests {

	@InjectMocks
	StringCalculator stringCalculator;

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
	public void addTestForNewLines() throws Exception {
		assertEquals(stringCalculator.add("1\n2,3\n1"), 7);
	}

	@Test
	public void addTestNegative() throws Exception {

		Exception exception = assertThrows(RuntimeException.class, () -> {
			stringCalculator.add("-6,5");
		});
		String expectedMessage = "negatives not allowed-6";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void addTestNegativeForMultiple() throws Exception {

		Exception exception = assertThrows(RuntimeException.class, () -> {
			stringCalculator.add("-6,5,-8,9");
		});
		String expectedMessage = "negatives not allowed-6-8";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));

	}
}
