/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.modules.titanium.ui.widget;

import java.util.ArrayList;

import org.appcelerator.titanium.TiDict;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.Log;
import org.appcelerator.titanium.util.TiAnimationFactory;
import org.appcelerator.titanium.util.TiAnimationPair;
import org.appcelerator.titanium.util.TiConfig;
import org.appcelerator.titanium.util.TiEventHelper;
import org.appcelerator.titanium.view.TiCompositeLayout;

import ti.modules.titanium.ui.ScrollableViewProxy;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.OnGestureListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ViewAnimator;

public class TiScrollableView extends TiCompositeLayout
{
	private static final String LCAT = "TiUIScrollableView";
	private static final boolean DBG = TiConfig.LOGD;
	private static final int ANIM_DURATION = 500;

	private static final int PAGE_LEFT = 200;
	private static final int PAGE_RIGHT = 201;

	protected RelativeLayout pager;
	protected View glass;
	protected boolean showPagingControl;

	protected ViewAnimator gallery;
	protected TiAnimationPair animPrev;
	protected TiAnimationPair animNext;
	final protected TiScrollableView me;

	protected ArrayList<TiViewProxy> views;
	protected ScrollableViewProxy proxy;
	protected Handler handler;

	class ViewWrapper extends FrameLayout
	{
		private int position;
		private View view;

		public ViewWrapper(Context context, int position) {
			super(context);
			this.position = position;
		}

		public void doAttachView() {
			if (view == null) {
				view = views.get(position).getView(null).getNativeView();
				addView(view);
			}
		}

		public void doDetachView() {
			if (view != null) {
				removeView(view);
				view = null;
				views.get(position).releaseViews();
			}
		}
	}

	public TiScrollableView(ScrollableViewProxy proxy, Handler handler)
	{
		super(proxy.getContext());

		this.proxy = proxy;
		this.handler = handler;
		me = this;
		showPagingControl = true;

		//below this was in "doOpen"
		//setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		setFocusable(true);
		setFocusableInTouchMode(true);
		setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

		animPrev = TiAnimationFactory.getAnimationFor("slide-from-left", ANIM_DURATION);
		animPrev.setAnimationListener(proxy);

		animNext = TiAnimationFactory.getAnimationFor("slide-from-right", ANIM_DURATION);
		animNext.setAnimationListener(proxy);

		gallery = new ViewAnimator(proxy.getContext());
		gallery.setFocusable(false);
		gallery.setFocusableInTouchMode(false);
		gallery.setClickable(false);

		TiCompositeLayout.LayoutParams p = new TiCompositeLayout.LayoutParams();
		p.autoFillsHeight = true;
		p.autoFillsWidth = true;

		addView(gallery, p);
		//gallery.setOnItemSelectedListener(this);

		pager = new RelativeLayout(proxy.getContext());
		pager.setFocusable(false);
		pager.setFocusableInTouchMode(false);

		TiArrowView left = new TiArrowView(proxy.getContext());
		left.setVisibility(View.INVISIBLE);
		left.setId(PAGE_LEFT);
		left.setMinimumWidth(80);
		left.setMinimumHeight(80);
		left.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				doMovePrevious();
			}});
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		pager.addView(left, params);

		TiArrowView right = new TiArrowView(proxy.getContext());
		right.setLeft(false);
		right.setVisibility(View.INVISIBLE);
		right.setId(PAGE_RIGHT);
		right.setMinimumWidth(80);
		right.setMinimumHeight(80);
		right.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				TiScrollableView.this.proxy.moveNext();
			}});
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		pager.addView(right, params);

		pager.setVisibility(View.GONE);

		p = new TiCompositeLayout.LayoutParams();
		p.autoFillsHeight = true;
		p.autoFillsWidth = true;
		addView(pager, p);

		final GestureDetector detector = new GestureDetector(new OnGestureListener(){

			@Override
			public boolean onDown(MotionEvent arg0) {
				return true;
			}

			@Override
			public boolean onFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

				if(Math.abs(me2.getY() - me1.getY()) > 100) {
					return false; // Keep it relatively level
				}

				Log.e(LCAT, "FLING IT");

				if (me1.getX() > me2.getX()) {
					doMoveNext();
				} else {
					doMovePrevious();
				}

				return true;
			}

			@Override
			public void onLongPress(MotionEvent arg0) {
			}

			@Override
			public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {
				return false;
			}

			@Override
			public void onShowPress(MotionEvent arg0) {
			}

			@Override
			public boolean onSingleTapUp(MotionEvent arg0) {
				return false;
			}});

		glass = new View(getContext()) {

			@Override
			public boolean onTouchEvent(MotionEvent event) {
				boolean handled = detector.onTouchEvent(event);

				if (event.getAction() == MotionEvent.ACTION_DOWN && showPagingControl) {
					if (pager.getVisibility() != View.VISIBLE) {
						gallery.onTouchEvent(event);
					}
				}

				if (!handled) {
					handled = super.onTouchEvent(event);
				}
				return handled;
			}

			@Override
			public boolean onTrackballEvent(MotionEvent event) {
				Log.w(LCAT, "TRACKBALL");
				return super.onTrackballEvent(event);
			}
		};
		glass.setBackgroundColor(Color.argb(0, 0, 0, 255));
		glass.setFocusable(false);
		glass.setFocusableInTouchMode(false);

		addView(glass);
	}

	public int getSelectedItemPosition() {
//		synchronized(gallery) {
			return gallery.getDisplayedChild();
//		}
	}

	public boolean hasPrevious() {
		return getSelectedItemPosition() > 0;
	}

	public boolean hasNext() {
//		synchronized (gallery) {
			return getSelectedItemPosition() < gallery.getChildCount() - 1;
//		}
	}

	public void doMovePrevious() {
//		synchronized(gallery) {
			int pos = getSelectedItemPosition();
			if (pos > 0) {
				int from = pos;
				int to = pos - 1;
				TiEventHelper.fireFocused(views.get(from));
				final ViewWrapper fromWrapper = (ViewWrapper) gallery.getChildAt(from);
				ViewWrapper toWrapper = (ViewWrapper) gallery.getChildAt(to);
				animPrev.apply(gallery);
				animPrev.setAnimationListener(new AnimationListener(){
					@Override
					public void onAnimationEnd(Animation arg0) {
						fromWrapper.doDetachView();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
					}

					@Override
					public void onAnimationStart(Animation arg0) {
					}});
				toWrapper.doAttachView();
				gallery.setDisplayedChild(to);
				TiEventHelper.fireUnfocused(views.get(to));
				onScrolled(from, to);
				if (pager.getVisibility() == View.VISIBLE) {
					proxy.setPagerTimeout();
				}
			}
//		}
	}

	public void doMoveNext() {
//		synchronized(gallery) {
			int pos = getSelectedItemPosition();
			if (pos < gallery.getChildCount() - 1) {
				int from = pos;
				int to = pos + 1;
				TiEventHelper.fireFocused(views.get(from));
				final ViewWrapper fromWrapper = (ViewWrapper) gallery.getChildAt(from);
				ViewWrapper toWrapper = (ViewWrapper) gallery.getChildAt(to);
				toWrapper.doAttachView();
				animNext.apply(gallery);
				animNext.setAnimationListener(new AnimationListener(){
					@Override
					public void onAnimationEnd(Animation arg0) {
						fromWrapper.doDetachView();
					}

					@Override
					public void onAnimationRepeat(Animation arg0) {
					}

					@Override
					public void onAnimationStart(Animation arg0) {
					}});
				gallery.setDisplayedChild(to);
				TiEventHelper.fireUnfocused(views.get(to));
				onScrolled(from, to);
				if (pager.getVisibility() == View.VISIBLE) {
					proxy.setPagerTimeout();
				}
			}
//		}
	}

	public void showPager() {
		View v = null;
		v = findViewById(PAGE_LEFT);
		if (v != null) {
			v.setVisibility(hasPrevious() ? View.VISIBLE : View.INVISIBLE);
		}

		v = findViewById(PAGE_RIGHT);
		if (v != null) {
			v.setVisibility(hasNext() ? View.VISIBLE : View.INVISIBLE);
		}

		pager.setVisibility(View.VISIBLE);
	}

	public void hidePager() {
		pager.setVisibility(View.INVISIBLE);
	}

	public void setViews(Object viewsObject)
	{
		if (DBG) {
			Log.d(LCAT, "Views: " + viewsObject);
		}
		if (views != null) {
			views.clear();
		} else {
			views = new ArrayList<TiViewProxy>();
		}

		if (viewsObject instanceof Object[]) {
			Object[] views = (Object[])viewsObject;
			gallery.removeAllViews();
			for (int i = 0; i < views.length; i++) {
				if (views[i] instanceof TiViewProxy) {
					TiViewProxy tv = (TiViewProxy)views[i];
					this.views.add(tv);
					gallery.addView(new ViewWrapper(getContext(), i));
					//gallery.addView(tv.getView(null).getNativeView());
				}
			}
			if (views.length >= 0) {
				((ViewWrapper) gallery.getChildAt(0)).doAttachView();
				((TiViewProxy)views[0]).show(new TiDict());
			}
		}
	}

	public void addView(TiViewProxy proxy)
	{
		if (proxy != null) {
			this.views.add(proxy);
			//gallery.addView(proxy.getView(null).getNativeView());
		}
	}

	public void setShowPagingControl(boolean showPagingControl) {
		this.showPagingControl = showPagingControl;
	}

	public void doScrollToView(int position) {
		if(position < gallery.getChildCount()) {
			int current = getSelectedItemPosition();
			if (current < position) {
				while(getSelectedItemPosition() < position) {
					doMoveNext();
				}
			} else if (current > position) {
				while(getSelectedItemPosition() > position) {
					doMovePrevious();
				}
			}
		}
	}

	public void doScrollToView(TiViewProxy view)
	{
		if (views.contains(view)) {
			doScrollToView(views.indexOf(view));
		}
	}

	public void doSetCurrentPage(int position) {
		if(position < gallery.getChildCount()) {
			int from = getSelectedItemPosition();
			final ViewWrapper fromWrapper = (ViewWrapper) gallery.getChildAt(from);
			ViewWrapper toWrapper = (ViewWrapper) gallery.getChildAt(position);
			toWrapper.doAttachView();
			gallery.setDisplayedChild(position);
			fromWrapper.doDetachView();
		}
	}

	public void doSetCurrentPage(TiViewProxy view) {
		if (views.contains(view)) {
			doSetCurrentPage(views.indexOf(view));
		}
	}

	public ArrayList<TiViewProxy> getViews() {
		return views;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		boolean handled = false;

		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (event.getKeyCode()) {
				case KeyEvent.KEYCODE_DPAD_LEFT : {
					proxy.movePrevious();
					handled = true;
					break;
				}
				case KeyEvent.KEYCODE_DPAD_RIGHT : {
					proxy.moveNext();
					handled = true;
					break;
				}
			}
		}

		if (!handled) {
			handled = super.dispatchKeyEvent(event);
		}

		return handled;
	}

	@Override
	public boolean dispatchTrackballEvent(MotionEvent event) {
		boolean handled = false;

		if (showPagingControl) {
			if (pager.getVisibility() != View.VISIBLE) {
				handler.sendEmptyMessage(ScrollableViewProxy.MSG_SHOW_PAGER);
			}
			proxy.setPagerTimeout();
		}

		handled = super.dispatchTrackballEvent(event);
		return handled;
	}

	public void onScrolled(int from, int to)
	{
		View v = null;

		proxy.fireScroll(to);

		v = findViewById(PAGE_LEFT);
		if (v != null) {
			v.setVisibility(hasPrevious() ? View.VISIBLE : View.INVISIBLE);
		}

		v = findViewById(PAGE_RIGHT);
		if (v != null) {
			v.setVisibility(hasNext() ? View.VISIBLE : View.INVISIBLE);
		}
	}

	public void onNothingSelected(AdapterView<?> view)
	{
	}
}