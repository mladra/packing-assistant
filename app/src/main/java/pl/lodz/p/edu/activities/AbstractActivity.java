package pl.lodz.p.edu.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class AbstractActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T binding;

    protected void initBinding(int layoutId) {
        this.binding = DataBindingUtil.setContentView(this, layoutId);
    }

    protected void setHeaderTitle(int titleId) {
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(titleId);
        }
    }

    public T getBinding() {
        return binding;
    }
}
