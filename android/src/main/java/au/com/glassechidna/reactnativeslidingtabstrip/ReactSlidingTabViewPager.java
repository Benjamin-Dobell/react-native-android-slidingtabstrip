package au.com.glassechidna.reactnativeslidingtabstrip;

import android.content.Context;
import android.support.v4.view.ViewPager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.view.ReactViewGroup;

/* package */ class ReactSlidingTabViewPager extends ReactViewGroup
{
	private ReactSlidingTabStrip slidingTabStrip;
	private ViewPager viewPager;

	public ReactSlidingTabViewPager(final ThemedReactContext reactContext)
	{
		super(reactContext);
	}

	public void setSlidingTabStrip(final ReactSlidingTabStrip slidingTabStrip)
	{
		if (this.slidingTabStrip != slidingTabStrip)
		{
			if (this.slidingTabStrip != null)
			{
				this.slidingTabStrip.setViewPager(null);
			}

			this.slidingTabStrip = slidingTabStrip;

			if (slidingTabStrip != null && viewPager != null)
			{
				slidingTabStrip.setViewPager(viewPager);
			}
		}
	}

	public void setViewPager(final ViewPager viewPager)
	{
		this.viewPager = viewPager;

		if (slidingTabStrip != null)
		{
			slidingTabStrip.setViewPager(viewPager);
		}
	}
}
