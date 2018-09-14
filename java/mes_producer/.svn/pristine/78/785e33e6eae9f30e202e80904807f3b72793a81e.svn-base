package jp.co.tmeic.mespd.utils;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void testToDate() throws ParseException {

		Date date = DateUtil.toDate("2015/01/01");
		Date expected = new SimpleDateFormat("yyyy/MM/dd").parse("2015/01/01");

		assertEquals(expected, date);

		assertNull(DateUtil.toDate(""));

		String dateNull = null;
		assertNull(DateUtil.toDate(dateNull));
	}

	@Test
	public void testEqDate() throws ParseException {
		assertTrue(DateUtil.eqDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/01")));
		assertFalse(DateUtil.eqDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/02")));
	}

	@Test
	public void testNeDate() throws ParseException {
		assertTrue(DateUtil.neDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/02")));
		assertFalse(DateUtil.neDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/01")));
	}

	@Test
	public void testGtDate() throws ParseException {
		assertTrue(DateUtil.gtDate(DateUtil.toDate("2015/01/02"), DateUtil.toDate("2015/01/01")));
		assertFalse(DateUtil.gtDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/01")));
	}

	@Test
	public void testLtDate() throws ParseException {
		assertTrue(DateUtil.ltDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/02")));
		assertFalse(DateUtil.ltDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/01")));
	}

	@Test
	public void testGeDate() throws ParseException {
		assertTrue(DateUtil.geDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/01")));
		assertTrue(DateUtil.geDate(DateUtil.toDate("2015/01/02"), DateUtil.toDate("2015/01/01")));
		assertFalse(DateUtil.geDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/02")));
	}

	@Test
	public void testLeDate() throws ParseException {
		assertTrue(DateUtil.leDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/01")));
		assertTrue(DateUtil.leDate(DateUtil.toDate("2015/01/01"), DateUtil.toDate("2015/01/02")));
		assertFalse(DateUtil.leDate(DateUtil.toDate("2015/01/02"), DateUtil.toDate("2015/01/01")));
	}

}
