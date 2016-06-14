package au.com.glassechidna.reactnativeslidingtabstrip;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class SlidingTabStripAndroidModule extends ReactContextBaseJavaModule
{
	public static final String REACT_CLASS = "RNSlidingTabStripAndroidModule";

	@Override
	public String getName()
	{
		return REACT_CLASS;
	}

	public SlidingTabStripAndroidModule(final ReactApplicationContext reactContext)
	{
		super(reactContext);
	}
}
