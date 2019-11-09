package com.takusemba.multisnaprecyclerview.internal

import android.view.View
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

internal class StartLayoutPositionHelper : LayoutPositionHelper {

  override fun getDistance(
      layoutManager: RecyclerView.LayoutManager, targetView: View,
      helper: OrientationHelper
  ): Int {
    val childStart = getChildPosition(targetView, helper)
    val containerStart = getContainerPosition(layoutManager, helper)
    return childStart - containerStart
  }

  override fun getContainerPosition(
      layoutManager: RecyclerView.LayoutManager,
      helper: OrientationHelper
  ): Int {
    return helper.startAfterPadding
  }

  override fun getChildPosition(targetView: View, helper: OrientationHelper): Int {
    return helper.getDecoratedStart(targetView)
  }

  override fun shouldSkipTarget(
      targetView: View, layoutManager: RecyclerView.LayoutManager,
      helper: OrientationHelper, forwardDirection: Boolean
  ): Boolean {
    return if (forwardDirection)
      getDistance(layoutManager, targetView, helper) < 0
    else
      getDistance(layoutManager, targetView, helper) > 0
  }
}