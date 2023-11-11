package adapter.home_special;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fragment.home_special.FragmentSpecialAllMenu;
import fragment.home_special.FragmentSpecialFood;
import fragment.home_special.FragmentSpecialPiza;
import fragment.home_special.FragmentSpecialPopular;
import fragment.home_special.FragmentSpecialTop;
import fragment.HomeFragmentSpecail;

public class AdapterHomeSpecial extends FragmentStateAdapter {
    public AdapterHomeSpecial(@NonNull HomeFragmentSpecail fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new FragmentSpecialPopular();
            case 1: return new FragmentSpecialPiza();
            case 2: return new FragmentSpecialTop();
            case 3: return new FragmentSpecialAllMenu();
            case 4: return new FragmentSpecialFood();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
