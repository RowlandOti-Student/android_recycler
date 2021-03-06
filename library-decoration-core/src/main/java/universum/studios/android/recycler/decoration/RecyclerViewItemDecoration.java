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
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import universum.studios.android.recycler.R;

/**
 * A {@link RecyclerView.ItemDecoration} implementation that is used as base class by all item
 * decorations from the Recycler library and is also encouraged to be used as base class by custom
 * decoration implementations.
 *
 * <h3>Xml attributes</h3>
 * {@link R.styleable#Recycler_ItemDecoration ItemDecoration Attributes}
 *
 * <h3>Default style attribute</h3>
 * {@code none}
 *
 * @author Martin Albedinsky
 * @see RecyclerView#addItemDecoration(RecyclerView.ItemDecoration)
 */
public abstract class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {

	/*
	 * Constants ===================================================================================
	 */

	/**
	 * Log TAG.
	 */
	// private static final String TAG = "RecyclerViewItemDecoration";

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
	 * Boolean flag indicating whether this decoration should be skipped for the first item from the
	 * data set or not.
	 */
	boolean mSkipFirst;

	/**
	 * Boolean flag indicating whether this decoration should be skipped for the last item from the
	 * data set or not.
	 */
	boolean mSkipLast;

	/*
	 * Constructors ================================================================================
	 */

	/**
	 * Same as {@link #RecyclerViewItemDecoration(Context)} with {@code null} <var>context</var>.
	 */
	public RecyclerViewItemDecoration() {
		this(null);
	}

	/**
	 * Same as {@link #RecyclerViewItemDecoration(Context, AttributeSet)} with {@code null} <var>attrs</var>.
	 */
	public RecyclerViewItemDecoration(@Nullable final Context context) {
		this(context, null);
	}

	/**
	 * Same as {@link #RecyclerViewItemDecoration(Context, AttributeSet, int)} with {@code 0}
	 * <var>defStyleAttr</var>.
	 */
	public RecyclerViewItemDecoration(@Nullable final Context context, @Nullable final AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * Same as {@link #RecyclerViewItemDecoration(Context, AttributeSet, int, int)} with {@code 0}
	 * <var>defStyleRes</var>.
	 */
	public RecyclerViewItemDecoration(@Nullable final Context context, @Nullable final AttributeSet attrs, @AttrRes final int defStyleAttr) {
		this(context, attrs, defStyleAttr, 0);
	}

	/**
	 * Creates a new instance of RecyclerViewItemDecoration for the given context.
	 *
	 * @param context      Context in which will be the new decoration presented.
	 * @param attrs        Set of Xml attributes used to configure the new instance of this decoration.
	 * @param defStyleAttr An attribute which contains a reference to a default style resource for
	 *                     this decoration within a theme of the given context.
	 * @param defStyleRes  Resource id of the default style for the new decoration.
	 */
	public RecyclerViewItemDecoration(@Nullable final Context context, @Nullable final AttributeSet attrs, @AttrRes final int defStyleAttr, @StyleRes final int defStyleRes) {
		super();
		if (context != null) {
			final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.Recycler_ItemDecoration, defStyleAttr, defStyleRes);
			for (int i = 0; i < attributes.getIndexCount(); i++) {
				final int index = attributes.getIndex(i);
				if (index == R.styleable.Recycler_ItemDecoration_recyclerItemDecorationSkipFirst) {
					this.mSkipFirst = attributes.getBoolean(index, false);
				} else if (index == R.styleable.Recycler_ItemDecoration_recyclerItemDecorationSkipLast) {
					this.mSkipLast = attributes.getBoolean(index, false);
				}
			}
			attributes.recycle();
		}
	}

	/*
	 * Methods =====================================================================================
	 */

	/**
	 * Sets a boolean flag indicating whether this decoration should be skipped for the first item
	 * from data set of the associated {@link RecyclerView}.
	 * <p>
	 * Default value: {@code false}
	 *
	 * @param skip {@code True} to not apply this decoration for the first item, {@code false} to
	 *             apply it.
	 * @see #skipsFirst()
	 * @see #setSkipLast(boolean)
	 */
	public void setSkipFirst(final boolean skip) {
		this.mSkipFirst = skip;
	}

	/**
	 * Checks whether this decoration is skipped for the first item.
	 *
	 * @return {@code True} if this decoration is not applied for the first item, {@code false} if
	 * it is.
	 * @see #setSkipFirst(boolean)
	 */
	public boolean skipsFirst() {
		return mSkipFirst;
	}

	/**
	 * Sets a boolean flag indicating whether this decoration should be skipped for the last item
	 * from data set of the associated {@link RecyclerView}.
	 * <p>
	 * Default value: {@code false}
	 *
	 * @param skip {@code True} to not apply this decoration for the last item, {@code false} to
	 *             apply it.
	 * @see #skipsFirst()
	 * @see #setSkipFirst(boolean)
	 */
	public void setSkipLast(final boolean skip) {
		this.mSkipLast = skip;
	}

	/**
	 * Checks whether this decoration is skipped for the last item.
	 *
	 * @return {@code True} if this decoration is not applied for the last item, {@code false} if
	 * it is.
	 * @see #setSkipLast(boolean)
	 */
	public boolean skipsLast() {
		return mSkipLast;
	}

	/**
	 * Checks whether any subsequent decoration algorithm should be applied by this decoration for
	 * the given <var>parent</var> RecyclerView and its current <var>state</var>.
	 * <p>
	 * This implementation checks whether the given RecyclerView has its {@link RecyclerView.LayoutManager}
	 * specified by {@code RecyclerView.getLayoutManager() != null} and if there are any items to be
	 * decorated by {@code RecyclerView.State.getItemCount() > 0}. If both conditions are met, this
	 * method returns {@code true}, if not, {@code false} is returned.
	 *
	 * @param parent The RecyclerView into which is this decoration added.
	 * @param state  Current state of the parent RecyclerView.
	 * @return {@code True} if decorating should be performed, {@code false} otherwise.
	 */
	protected boolean shouldDecorate(@NonNull final RecyclerView parent, @NonNull final RecyclerView.State state) {
		return parent.getLayoutManager() != null && state.getItemCount() > 0;
	}

	/*
	 * Inner classes ===============================================================================
	 */
}
