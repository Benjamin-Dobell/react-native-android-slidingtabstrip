package au.com.glassechidna.reactnativeslidingtabstrip;

import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

public class ReactSlidingTabStripManager extends ViewGroupManager<ReactSlidingTabStrip>
{
	public static final String REACT_CLASS = "RCTSlidingTabStripAndroid";

	@Override
	public String getName()
	{
		return REACT_CLASS;
	}

	@Override
	protected ReactSlidingTabStrip createViewInstance(final ThemedReactContext reactContext)
	{
		return new ReactSlidingTabStrip(reactContext);
	}

	@ReactProp(
		name = "removeClippedSubviews"
	)
	public void setRemoveClippedSubviews(final ReactSlidingTabStrip view, final boolean removeClippedSubviews)
	{
		view.setRemoveClippedSubviews(removeClippedSubviews);
	}

	@ReactProp(
		name = "endFillColor",
		defaultInt = 0,
		customType = "Color"
	)
	public void setBottomFillColor(final ReactSlidingTabStrip view, final int color)
	{
		view.setEndFillColor(color);
	}

	@ReactProp(
		name = "scrollOffset",
		defaultFloat = 52.0f
	)
	public void setScrollOffset(final ReactSlidingTabStrip view, final float scrollOffset)
	{
		view.setScrollOffset(Math.round(PixelUtil.toPixelFromDIP(scrollOffset)));
	}

	@ReactProp(
		name = "indicatorHeight",
		defaultFloat = 8.0f
	)
	public void setIndicatorHeight(final ReactSlidingTabStrip view, final float indicatorHeight)
	{
		view.setIndicatorHeight(PixelUtil.toPixelFromDIP(indicatorHeight));
	}

	@ReactProp(
		name = "dividerInset",
		defaultFloat = 12.0f
	)
	public void setDividerInset(final ReactSlidingTabStrip view, final float dividerInset)
	{
		view.setDividerInset(PixelUtil.toPixelFromDIP(dividerInset));
	}

	@ReactProp(
		name = "dividerWidth",
		defaultFloat = 1.0f
	)
	public void setDividerWidth(final ReactSlidingTabStrip view, final float dividerWidth)
	{
		view.setDividerWidth(PixelUtil.toPixelFromDIP(dividerWidth));
	}

	@ReactProp(
		name = "indicatorColor",
		defaultInt = 0xFF666666,
		customType = "Color"
	)
	public void setIndicatorColor(final ReactSlidingTabStrip view, final int indicatorColor)
	{
		view.setIndicatorColor(indicatorColor);
	}

	@ReactProp(
		name = "dividerColor",
		defaultInt = 0x1A000000,
		customType = "Color"
	)
	public void setDividerColor(final ReactSlidingTabStrip view, final int dividerColor)
	{
		view.setDividerColor(dividerColor);
	}
}
