package cn.tju.edu.util.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.tju.edu.util.LSQLocalUtil;

public class LSQLocalUtilTest {
	private LSQLocalUtil lsq = new LSQLocalUtil();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		lsq.CountTriplePattern("E:\\LSQ");
	}

}
