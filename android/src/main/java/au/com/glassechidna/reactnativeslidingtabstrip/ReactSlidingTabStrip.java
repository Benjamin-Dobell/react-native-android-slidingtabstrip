package au.com.glassechidna.reactnativeslidingtabstrip;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.scroll.ReactHorizontalScrollView;
import com.facebook.react.views.view.ReactViewGroup;

/* package */ class ReactSlidingTabStrip extends ReactHorizontalScrollView
{
	private int scrollOffset = Math.round(PixelUtil.toPixelFromDIP(13));

	private int indicatorColor = 0xFF666666;
	private float indicatorHeight = PixelUtil.toPixelFromDIP(2);

	private int dividerColor = 0x1A000000;
	private float dividerInset = PixelUtil.toPixelFromDIP(3);
	private float dividerWidth = PixelUtil.toPixelFromDIP(1);

	private int currentPosition = 0;
	private float currentPositionOffset = 0.0f;
	private int selectedPosition = 0;

	private ViewPager viewPager;

	private final PageChangeListener pageChangeListener = new PageChangeListener();
	private final Paint dividerPaint = new Paint();
	private final Paint indicatorPaint = new Paint();

	public ReactSlidingTabStrip(final ThemedReactContext reactContext)
	{
		super(reactContext);

		dividerPaint.setAntiAlias(true);
		dividerPaint.setStrokeWidth(dividerWidth);
		dividerPaint.setColor(dividerColor);

		indicatorPaint.setAntiAlias(true);
		indicatorPaint.setStyle(Paint.Style.FILL);
		indicatorPaint.setColor(indicatorColor);

		setHorizontalScrollBarEnabled(false);
	}

	public void setViewPager(final ViewPager viewPager)
	{
		this.viewPager = viewPager;

		viewPager.addOnPageChangeListener(pageChangeListener);
	}

	public void setScrollOffset(final int scrollOffset)
	{
		this.scrollOffset = scrollOffset;
	}

	public void setIndicatorHeight(final float indicatorHeight)
	{
		this.indicatorHeight = indicatorHeight;
	}

	public void setDividerInset(final float dividerInset)
	{
		this.dividerInset = dividerInset;
	}

	public void setDividerWidth(final float dividerWidth)
	{
		this.dividerWidth = dividerWidth;

		dividerPaint.setStrokeWidth(dividerWidth);
	}

	public void setIndicatorColor(final int indicatorColor)
	{
		this.indicatorColor = indicatorColor;

		indicatorPaint.setColor(indicatorColor);
	}

	public void setDividerColor(final int dividerColor)
	{
		this.dividerColor = dividerColor;

		dividerPaint.setColor(dividerColor);
	}

	@Override
	protected void dispatchDraw(final Canvas canvas)
	{
		validateTabCount();

		super.dispatchDraw(canvas);

		setHorizontalFadingEdgeEnabled(false);
		setVerticalFadingEdgeEnabled(false);

		final int tabCount = getTabContainer().getChildCount();

		if (isInEditMode() || tabCount == 0)
		{
			return;
		}

		final int height = getHeight();

		// Tab dividers

		if (dividerWidth > 0)
		{
			for (int i = 0; i < tabCount - 1; i++)
			{
				final View tab = getTabContainer().getChildAt(i);
				canvas.drawLine(tab.getRight(), dividerInset, tab.getRight(), height - dividerInset, dividerPaint);
			}
		}

		// Indicator

		if (indicatorHeight > 0)
		{
			final View currentTab = getTabContainer().getChildAt(currentPosition);

			final float indicatorLeft;
			final float indicatorRight;

			// if there is an offset, start interpolating left and right coordinates between current and next tab
			if (currentPositionOffset > 0.0f && currentPosition < tabCount - 1)
			{
				final View followingTab = getTabContainer().getChildAt(currentPosition + 1);

				indicatorLeft = currentPositionOffset * followingTab.getLeft() + (1.0f - currentPositionOffset) * currentTab.getLeft();
				indicatorRight = currentPositionOffset * followingTab.getRight() + (1.0f - currentPositionOffset) * currentTab.getRight();
			}
			else
			{
				indicatorLeft = currentTab.getLeft();
				indicatorRight = currentTab.getRight();
			}

			canvas.drawRect(indicatorLeft, height - indicatorHeight, indicatorRight, height, indicatorPaint);
		}
	}

	private ReactViewGroup getTabContainer()
	{
		return (ReactViewGroup) getChildAt(0);
	}

	private void scrollToChild(final int index, final int offset)
	{
		validateTabCount();

		final int tabCount = getTabContainer().getChildCount();
		final int scrollX;

		if (index < 0 || tabCount == 0)
		{
			scrollX = 0;
		}
		else if (index >= tabCount)
		{
			scrollX = getTabContainer().getChildAt(tabCount - 1).getLeft() - scrollOffset;
		}
		else
		{
			scrollX = getTabContainer().getChildAt(index).getLeft() + offset - scrollOffset;
		}

		super.scrollTo(Math.max(0, scrollX), 0);
	}

	private void setSelectedPosition(final int position)
	{
		validateTabCount();

		if (selectedPosition >= 0 && selectedPosition < getTabContainer().getChildCount())
		{
			getTabContainer().getChildAt(selectedPosition).setSelected(false);
		}

		selectedPosition = position;

		if (position >= 0 && position < getTabContainer().getChildCount())
		{
			getTabContainer().getChildAt(position).setSelected(true);
		}
	}

	private void validateTabCount()
	{
		if (getTabContainer().getChildCount() != viewPager.getAdapter().getCount())
		{
			throw new IllegalStateException("Sliding tab count (" + getTabContainer().getChildCount() + ") does not match the associated view pager's page count (" + viewPager.getAdapter().getCount() + ")");
		}
	}

	private class PageChangeListener implements ViewPager.OnPageChangeListener
	{
		@Override
		public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels)
		{
			currentPosition = position;
			currentPositionOffset = positionOffset;

			scrollToChild(position, (int) (positionOffset * getTabContainer().getChildAt(position).getWidth()));

			invalidate();
		}

		@Override
		public void onPageSelected(final int position)
		{
			setSelectedPosition(position);
		}

		@Override
		public void onPageScrollStateChanged(final int state)
		{
			if (state == ViewPager.SCROLL_STATE_IDLE)
			{
				scrollToChild(viewPager.getCurrentItem(), 0);
			}
		}
	}
}
