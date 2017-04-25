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
package universum.studios.android.samples.recycler.ui.helper.swipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import universum.studios.android.recycler.helper.ItemSwipeHelper;
import universum.studios.android.recycler.helper.RecyclerViewItemHelper;
import universum.studios.android.samples.recycler.R;
import universum.studios.android.samples.recycler.data.AdapterItems;
import universum.studios.android.samples.recycler.ui.RecyclerSampleFragment;
import universum.studios.android.samples.recycler.ui.adapter.SampleViewHolder;
import universum.studios.android.ui.util.ResourceUtils;
import universum.studios.android.widget.adapter.holder.AdapterHolder;

/**
 * @author Martin Albedinsky
 */
public class SwipeHelperFragment extends RecyclerSampleFragment<SwipeableItemsAdapter>
		implements
		ItemSwipeHelper.OnSwipeListener {

	@SuppressWarnings("unused")
	private static final String TAG = "SwipeHelperFragment";

	private ItemSwipeHelper mSwipeHelper;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@NonNull
	@Override
	protected SwipeableItemsAdapter createAdapterWithHolderFactory(@NonNull AdapterHolder.Factory<SampleViewHolder> factory) {
		final SwipeableItemsAdapter adapter = new SwipeableItemsAdapter(getActivity(), AdapterItems.createSampleList(getResources()));
		adapter.setHolderFactory(factory);
		return adapter;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.collectionView.setItemAnimator(new ItemSwipeHelper.SwipeItemAnimator());
		this.mSwipeHelper = new ItemSwipeHelper();
		this.mSwipeHelper.attachAdapter(adapter);
		this.mSwipeHelper.addOnSwipeListener(this);
		this.mSwipeHelper.attachToRecyclerView(collectionView);
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.recycler_swipeable, menu);
		menu.findItem(R.id.menu_refresh).setIcon(ResourceUtils.getVectorDrawable(
				getResources(),
				R.drawable.vc_ic_refresh_24dp,
				getActivity().getTheme()
		));
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_refresh:
				adapter.changeItems(AdapterItems.createSampleList(getResources()));
				return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSwipeStarted(@NonNull ItemSwipeHelper swipeHelper, @NonNull RecyclerView.ViewHolder viewHolder) {
		Log.d(TAG, "Swipe started for position(" + viewHolder.getAdapterPosition() + ").");
	}

	@Override
	public void onSwipeFinished(@NonNull ItemSwipeHelper swipeHelper, @NonNull RecyclerView.ViewHolder viewHolder, @RecyclerViewItemHelper.Direction int direction) {
		final int itemPosition = viewHolder.getAdapterPosition();
		switch (direction) {
			// Delete item.
			case ItemSwipeHelper.START:
				adapter.removeItem(itemPosition);
				break;
			// Done with the item.
			case ItemSwipeHelper.END:
				adapter.removeItem(itemPosition);
				break;
		}
	}

	@Override
	public void onSwipeCanceled(@NonNull ItemSwipeHelper swipeHelper, @NonNull RecyclerView.ViewHolder viewHolder) {
		swipeHelper.restoreHolder(viewHolder, ItemSwipeHelper.START);
	}
}
