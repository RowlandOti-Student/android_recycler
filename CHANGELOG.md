Change-Log
===============

## Version 1.x ##

### [1.0.1](https://github.com/universum-studios/android_recycler/releases/tag/v1.0.1) ###
> 21.05.2017

- `ItemSwipeHelper.OnSwipeListener.onSwipeCanceled(...)` is now not being called if the associated
  `RecyclerView` is **computing layout** a the time when `ItemSwipeHelper.Interactor.clearView(...)`
  is requested.
- Updated **samples** to provide also implementation of `SwipeViewHolder` which may respond to the
  swipe gesture via two approaches:
    1) using `SwipeViewHolder.onDraw(...)` method to **draw** appropriate action components 
       (background + icon) on the provided `Canvas` depending on the current offset of the swiped
       item view,
    2) using a **separate swipe view** with child views for available actions, which is a child view
       of the primary **item view**, where the appropriate action and background color are changed 
       depending on the current offset of the swiped interaction view.
  

### [1.0.0](https://github.com/universum-studios/android_recycler/releases/tag/v1.0.0) ###
> 29.04.2017

- First production release.
- Finalized `ItemDividerDecoration`.
- Finalized `ItemSpaceDecoration`.
- Finalized `ItemSwipeHelper`.
- Implemented `ItemDragHelper`.