package com.bnrg.bnrg07;

import android.support.v4.app.Fragment;

/**
 * Created by palance on 2017/10/21.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
