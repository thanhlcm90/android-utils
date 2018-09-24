package com.mauriciotogneri.androidutils.uibinder.containers;

import androidx.annotation.IdRes;
import android.view.View;

public interface UiContainer
{
    View findViewById(@IdRes int id);
}