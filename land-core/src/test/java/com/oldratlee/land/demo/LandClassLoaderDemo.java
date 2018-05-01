package com.oldratlee.land.demo;

import static com.oldratlee.land.util.Utils.invokeMain;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oldratlee.land.Constants;
import com.oldratlee.land.DelegateType;
import com.oldratlee.land.LandClassLoader;

/**
 * @author ding.lid
 */
public class LandClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        Map<DelegateType, List<String>> delegateConfig = new HashMap<>();
		delegateConfig.put(DelegateType.CHILD_ONLY, Arrays.asList("com.foo.p1.*", "com.foo.p3.."));
		LandClassLoader parentLoader = new LandClassLoader(new URL[] { Constants.lib_common }, delegateConfig);
		parentLoader.setName("parentLoader");
		LandClassLoader classLoader = new LandClassLoader(new URL[] { Constants.lib_common }, delegateConfig,
				parentLoader);
		classLoader.setName("child");
		invokeMain(classLoader.loadClass("com.foo.Foo"));
		invokeMain(classLoader.loadClass("com.foo.p1.P1C1"));
		// try {
		// invokeMain(classLoader.loadClass("com.foo.p2.P2C1"));
		// fail();
		// } catch (ClassNotFoundException e) {
		// assertThat(e.getMessage(), containsString("(PARENT_ONLY) not found in parent
		// class loader"));
		// }
    }
}
