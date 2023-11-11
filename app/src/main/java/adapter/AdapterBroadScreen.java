package adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fragment.broad_screen.Fragment1;
import fragment.broad_screen.Fragment2;
import fragment.broad_screen.Fragment3;


public class AdapterBroadScreen extends FragmentStateAdapter {
    public AdapterBroadScreen(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:return new Fragment1();
            case 1:return new Fragment2();
            case 2:return new Fragment3();

        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
