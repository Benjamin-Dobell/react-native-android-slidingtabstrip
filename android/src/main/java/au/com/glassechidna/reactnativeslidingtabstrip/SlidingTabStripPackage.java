package au.com.glassechidna.reactnativeslidingtabstrip;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SlidingTabStripPackage implements ReactPackage
{
	@Override
	public List<NativeModule> createNativeModules(final ReactApplicationContext reactContext)
	{
		List<NativeModule> modules = new ArrayList<NativeModule>();
		modules.add(new SlidingTabStripModule(reactContext));
		return modules;
	}

	@Override
	public List<Class<? extends JavaScriptModule>> createJSModules()
	{
		return Collections.emptyList();
	}

	@Override
	public List<ViewManager> createViewManagers(final ReactApplicationContext reactContext)
	{
		return Collections.emptyList();
	}
}
