package au.com.glassechidna.reactnativeslidingtabstrip;

import android.support.v4.view.ViewPager;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

public class ReactSlidingTabViewPagerManager extends ViewGroupManager<ReactSlidingTabViewPager>
{
	public static final String REACT_CLASS = "RCTSlidingTabViewPagerAndroid";

	@Override
	public String getName()
	{
		return REACT_CLASS;
	}

	@Override
	protected ReactSlidingTabViewPager createViewInstance(final ThemedReactContext reactContext)
	{
		return new ReactSlidingTabViewPager(reactContext);
	}

	@Override
	public void addView(final ReactSlidingTabViewPager parent, final View child, final int index)
	{
		super.addView(parent, child, index);

		if (child instanceof ViewPager)
		{
			parent.setViewPager((ViewPager) child);
		}
		else if (child instanceof ReactSlidingTabStrip)
		{
			parent.setSlidingTabStrip((ReactSlidingTabStrip) child);
		}
	}

	@Override
	public void removeViewAt(final ReactSlidingTabViewPager parent, final int index)
	{
		final View child = getChildAt(parent, index);

		super.removeViewAt(parent, index);

		if (child instanceof ViewPager)
		{
			parent.setViewPager(null);
		}
		else if (child instanceof ReactSlidingTabStrip)
		{
			parent.setSlidingTabStrip(null);
		}
	}

	@Override
	public void onDropViewInstance(final ReactSlidingTabViewPager view)
	{
		super.onDropViewInstance(view);

		view.setSlidingTabStrip(null);
		view.setViewPager(null);
	}
}
