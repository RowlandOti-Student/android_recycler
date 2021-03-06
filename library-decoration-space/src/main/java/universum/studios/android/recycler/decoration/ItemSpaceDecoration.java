/*
 * =================================================================================================
 *                             Copyright (C) 2017 Universum Studios
 * =================================================================================================
 *         Licensed under the Apache License, Version 2.0 or later (further "License" only).
 * -------------------------------------------------------------------------------------------------
 * You may use this file only in compliance with the License. More details and copy of this License 
 * you may obtain at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * You can redistribute, modify or publish any part of the code written within this file but as it 
 * is described in the License, the software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF ANY KIND.
 * 
 * See the License for the specific language governing permissions and limitations under the License.
 * =================================================================================================
 */
package universum.studios.android.recycler.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import universum.studios.android.recycler.R;

/**
 * A {@link RecyclerViewItemDecoration} implementation that may be used to add a <b>space</b>,
 * vertically and horizontally, between items displayed in a {@link RecyclerView}.
 *
 * <h3>Xml attributes</h3>
 * {@link R.styleable#Recycler_ItemDecoration_Space ItemSpaceDecoration Attributes}
 *
 * <h3>Default style attribute</h3>
 * {@code none}
 *
 * @author Martin Albedinsky
 * @see RecyclerView#addItemDecoration(RecyclerView.ItemDecoration)
 */
public class ItemSpaceDecoration extends RecyclerViewItemDecoration {

	/*
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "ItemSpaceDecoration";

	/*
	 * Interface ===================================================================================
	 */

	/*
	 * Static members ==============================================================================
	 */

	/*
	 * Members =====================================================================================
	 */

	/**
	 * Amount of space to apply at the start of an item in horizontal direction.
	 */
	private int mHorizontalStart;

	/**
	 * Amount of space to apply at the end of an item in horizontal direction.
	 */
	private int mHorizontalEnd;

	/**
	 * Amount of space to apply at the start of an item in vertical direction.
	 */
	private int mVerticalStart;

	/**
	 * Amount of space to apply at the end of an item in vertical direction.
	 */
	private int mVerticalEnd;

	/*
	 * Constructors ================================================================================
	 */

	/**
	 * Same as {@link #ItemSpaceDecoration(Context)} with {@code null} <var>context</var>.
	 */
	public ItemSpaceDecoration() {
		this(null);
	}

	/**
	 * Same as {@link #ItemSpaceDecoration(int, int, int, int)} where the specified <var>horizontal</var>
	 * amount will be used for both, horizontal start and horizontal end amounts, and the <var>vertical</var>
	 * amount will be used similarly for both, vertical start and vertical end amounts.
	 */
	public ItemSpaceDecoration(final int horizontal, final int vertical) {
		this(horizontal, horizontal, vertical, vertical);
	}

	/**
	 * Creates a new instance of ItemSpaceDecoration with the specified spacing amounts.
	 *
	 * @param horizontalStart Amount to be applied at the start of an item in horizontal direction.
	 * @param horizontalEnd   Amount to be applied at the end of an item in horizontal direction.
	 * @param verticalStart   Amount to be applied at the start of an item in vertical direction.
	 * @param verticalEnd     Amount to be applied at the end of an item in vertical direction.
	 */
	public ItemSpaceDecoration(final int horizontalStart, final int horizontalEnd, final int verticalStart, final int verticalEnd) {
		super();
		this.mHorizontalStart = horizontalStart;
		this.mHorizontalEnd = horizontalEnd;
		this.mVerticalStart = verticalStart;
		this.mVerticalEnd = verticalEnd;
	}

	/**
	 * Same as {@link #ItemSpaceDecoration(Context, AttributeSet)} with {@code null} <var>attrs</var>.
	 */
	public ItemSpaceDecoration(@Nullable final Context context) {
		this(context, null);
	}

	/**
	 * Same as {@link #ItemSpaceDecoration(Context, AttributeSet, int)} with {@code 0} <var>defStyleAttr</var>.
	 */
	public ItemSpaceDecoration(@Nullable final Context context, @Nullable final AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * Same as {@link #ItemSpaceDecoration(Context, AttributeSet, int, int)} with {@code 0} <var>defStyleRes</var>.
	 */
	public ItemSpaceDecoration(@Nullable final Context context, @Nullable final AttributeSet attrs, @AttrRes final int defStyleAttr) {
		this(context, attrs, defStyleAttr, 0);
	}

	/**
	 * Creates a new instance of ItemSpaceDecoration for the given context.
	 *
	 * @param context      Context in which will be the new decoration presented.
	 * @param attrs        Set of Xml attributes used to configure the new instance of this decoration.
	 * @param defStyleAttr An attribute which contains a reference to a default style resource for
	 *                     this decoration within a theme of the given context.
	 * @param defStyleRes  Resource id of the default style for the new decoration.
	 * @see #ItemSpaceDecoration(int, int, int, int)
	 */
	public ItemSpaceDecoration(@Nullable final Context context, @Nullable final AttributeSet attrs, @AttrRes final int defStyleAttr, @StyleRes final int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		if (context != null) {
			final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.Recycler_ItemDecoration_Space, defStyleAttr, defStyleRes);
			for (int i = 0; i < attributes.getIndexCount(); i++) {
				final int index = attributes.getIndex(i);
				if (index == R.styleable.Recycler_ItemDecoration_Space_recyclerItemSpacingHorizontalStart) {
					this.mHorizontalStart = attributes.getDimensionPixelSize(index, 0);
				} else if (index == R.styleable.Recycler_ItemDecoration_Space_recyclerItemSpacingHorizontalEnd) {
					this.mHorizontalEnd = attributes.getDimensionPixelSize(index, 0);
				} else if (index == R.styleable.Recycler_ItemDecoration_Space_recyclerItemSpacingVerticalStart) {
					this.mVerticalStart = attributes.getDimensionPixelSize(index, 0);
				} else if (index == R.styleable.Recycler_ItemDecoration_Space_recyclerItemSpacingVerticalEnd) {
					this.mVerticalEnd = attributes.getDimensionPixelSize(index, 0);
				} else if (index == R.styleable.Recycler_ItemDecoration_Space_recyclerItemSpacingSkipFirst) {
					setSkipFirst(attributes.getBoolean(index, skipsFirst()));
				} else if (index == R.styleable.Recycler_ItemDecoration_Space_recyclerItemSpacingSkipLast) {
					setSkipLast(attributes.getBoolean(index, skipsLast()));
				}
			}
			attributes.recycle();
		}
	}

	/*
	 * Methods =====================================================================================
	 */

	/**
	 * Returns the amount of space that will be applied at the start of each item in horizontal direction.
	 *
	 * @return Amount of space in pixels.
	 * @see #getHorizontalEnd()
	 */
	public int getHorizontalStart() {
		return mHorizontalStart;
	}

	/**
	 * Returns the amount of space that will be applied at the end of each item in horizontal direction.
	 *
	 * @return Amount of space in pixels.
	 * @see #getHorizontalStart()
	 */
	public int getHorizontalEnd() {
		return mHorizontalEnd;
	}

	/**
	 * Returns the amount of space that will be applied at the start of each item in vertical direction.
	 *
	 * @return Amount of space in pixels.
	 * @see #getVerticalEnd()
	 */
	public int getVerticalStart() {
		return mVerticalStart;
	}

	/**
	 * Returns the amount of space that will be applied at the end of each item in vertical direction.
	 *
	 * @return Amount of space in pixels.
	 * @see #getVerticalStart()
	 */
	public int getVerticalEnd() {
		return mVerticalEnd;
	}

	/**
	 */
	@Override
	public void getItemOffsets(@NonNull final Rect rect, @NonNull final View view, @NonNull final RecyclerView parent, @NonNull final RecyclerView.State state) {
		if (mSkipFirst || mSkipLast) {
			final int position = parent.getChildAdapterPosition(view);
			if (position == RecyclerView.NO_POSITION) {
				return;
			}
			if ((mSkipFirst && position == 0) || (mSkipLast && position == state.getItemCount() - 1)) {
				rect.set(0, 0, 0, 0);
			} else {
				this.updateRectWithOffsets(rect);
			}
		} else {
			this.updateRectWithOffsets(rect);
		}
	}

	/**
	 * Updates the given <var>rect</var> with the current spacing amounts specified for this decoration.
	 *
	 * @param rect The rect to be updated.
	 */
	private void updateRectWithOffsets(final Rect rect) {
		rect.set(mHorizontalStart, mVerticalStart, mHorizontalEnd, mVerticalEnd);
	}

	/*
	 * Inner classes ===============================================================================
	 */
}
