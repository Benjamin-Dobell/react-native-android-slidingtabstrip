package au.com.glassechidna.reactnativeslidingtabstrip;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class SlidingTabStripModule extends ReactContextBaseJavaModule
{
	@Override
	public String getName()
	{
		return "RNSlidingTabStripModule";
	}

	public SlidingTabStripModule(final ReactApplicationContext reactContext)
	{
		super(reactContext);
	}
}
