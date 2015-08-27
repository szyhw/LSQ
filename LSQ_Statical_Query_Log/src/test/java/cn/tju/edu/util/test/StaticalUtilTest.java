package cn.tju.edu.util.test;

import cn.tju.edu.util.StaticalUtil;
import org.junit.Test;

public class StaticalUtilTest {

	@Test
	public void test() {
//		StaticalUtil.statical("/home/hanxingwang/Data/SearchResult/TriplePatternsStatistical-SWDF-num", "/home/hanxingwang/Data/SearchResult/TriplePatternsStatistical-SWDF-Result");
		StaticalUtil.staticalFeature("/home/hxw/Data/SearchResult/QueryWithFeaturesMap", "/home/hxw/Data/SearchResult/FeaturesMap");
//		StaticalUtil.CountFeature("/home/hxw/Data/SearchResult/FeaturesMap", "/home/hxw/Data/SearchResult/FeaturesStatical");
	}

}
