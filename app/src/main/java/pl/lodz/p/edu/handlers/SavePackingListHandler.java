package pl.lodz.p.edu.handlers;

import androidx.fragment.app.FragmentActivity;
import pl.lodz.p.edu.fragments.CreatedPackingListFragment;

public class SavePackingListHandler extends AbstractPackingListHandler implements ClickHandler {

    private static final String TAG = "SaveHandler";

    public SavePackingListHandler(CreatedPackingListFragment fragment) {
        super(fragment);
    }

    @Override
    public void onClick() {
        super.onClick();
        final FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
