package jp.co.tmeic.mespd.utils;

import static org.junit.Assert.*;

import java.sql.Time;
import java.text.ParseException;

import org.junit.Test;

public class TimeUtilTest {

	@Test
	public void testGetHours() {
	}

	@Test
	public void testGetMinutes() {
	}

	@Test
	public void testGetSeconds() {
	}

	@Test
	public void testisTime() {

		assertTrue(TimeUtil.isTime("01:23"));
		assertTrue(TimeUtil.isTime("01:23:45"));
		assertFalse(TimeUtil.isTime("99:99"));
		assertFalse(TimeUtil.isTime(":"));
	}

	@Test
	public void testToTime() throws ParseException {

		Time time;

		time = TimeUtil.toTime("01:23");
		assertEquals("01:23:00", time.toString());

		time = TimeUtil.toTime("01:23:45");
		assertEquals("01:23:45", time.toString());
	}

	@Test
	public void testOf() {

		Time time;

		time = TimeUtil.of(1, 23);
		assertEquals("01:23:00", time.toString());

		time = TimeUtil.of(1, 23, 45);
		assertEquals("01:23:45", time.toString());
	}

	@Test
	public void testEq() {
	}

	@Test
	public void testNe() {
	}

	@Test
	public void testGt() {
	}

	@Test
	public void testLt() {
	}

	@Test
	public void testGe() {
	}

	@Test
	public void testLe() {
	}

}
